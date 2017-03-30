package nl.han.oose.yarince.presentation.controller;

import nl.han.oose.yarince.domain.Playlist;
import nl.han.oose.yarince.domain.Track;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 30/03/2017.
 */
@WebServlet("/playlist/details/addtrack")
public class AddTrackPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebClient client2 = WebClient.create("http://localhost:8080/");
        String playlistId = req.getParameter("playlistId");
        if (playlistId != null) {
            WebClient webClient = WebClient.create("http://localhost:8080/").path("/playlists/id/" + playlistId).accept("application/json");
            System.out.println("playlist: " + webClient.get(Playlist.class).getName());
            req.setAttribute("PLAYLIST", webClient.get(Playlist.class));

            WebClient webClient2 = WebClient.create("http://localhost:8080/").path("/tracks").accept("application/json");
            req.setAttribute("TRACKS", webClient2.getCollection(Track.class));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
