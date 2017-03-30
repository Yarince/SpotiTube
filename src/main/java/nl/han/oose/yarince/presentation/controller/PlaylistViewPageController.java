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


@WebServlet("/playlist/details")
public class PlaylistViewPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    private void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("USER", req.getSession().getAttribute("USER"));

        String playlistId = req.getParameter("playlistId");

        if (playlistId != null) {
            WebClient webClient = WebClient.create("http://localhost:8080/").path("/playlists/id/" + playlistId).accept("application/json");
            System.out.println("playlist: " + webClient.get(Playlist.class).getName());
            req.setAttribute("PLAYLIST", webClient.get(Playlist.class));

            WebClient webClient2 = WebClient.create("http://localhost:8080/").path("/tracks/playlist/" + playlistId).accept("application/json");
            req.setAttribute("TRACKS", webClient2.getCollection(Track.class));
        }
        req.getRequestDispatcher("../playlist.jsp").forward(req, resp);
    }
}
