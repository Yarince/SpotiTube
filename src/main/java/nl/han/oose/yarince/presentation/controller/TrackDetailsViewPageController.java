package nl.han.oose.yarince.presentation.controller;

import nl.han.oose.yarince.domain.Song;
import nl.han.oose.yarince.domain.Track;
import nl.han.oose.yarince.domain.Video;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 03/04/2017.
 */
@WebServlet("/trackDetails")
public class TrackDetailsViewPageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doExecute(req, resp);
        req.getRequestDispatcher("trackDetails.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doExecute(req, resp);
    }

    private void doExecute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId = req.getParameter("id");

        WebClient client = WebClient.create("http://localhost:8080/").path("/tracks/id/" + trackId).accept("application/json");

        Track track = client.get(Track.class);
        req.setAttribute("TRACK", track);

        switch (track.getTrackType()) {
            case Song:
                Song song = client.get(Song.class);
                req.setAttribute("SONG", song);
                break;
            case Video:
                Video video = client.get(Video.class);
                req.setAttribute("VIDEO", video);
                break;
        }
        }
}
