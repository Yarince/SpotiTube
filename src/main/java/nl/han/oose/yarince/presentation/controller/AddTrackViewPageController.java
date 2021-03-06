package nl.han.oose.yarince.presentation.controller;

import com.mysql.cj.core.util.StringUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import nl.han.oose.yarince.domain.Playlist;
import nl.han.oose.yarince.domain.Track;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 30/03/2017.
 */
@WebServlet("/playlist/details/addTrack")
public class AddTrackViewPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    private void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playlistId = req.getParameter("playlistId");
        if (!StringUtils.isEmptyOrWhitespaceOnly(playlistId)) {
            WebClient webClient1 = WebClient.create("http://localhost:8080/").path("/playlists/id/" + playlistId).accept("application/json");
            req.setAttribute("PLAYLIST", webClient1.get(Playlist.class));


            String trackId = req.getParameter("addTrackId");
            String offlineAvailable = req.getParameter("offlineAvailable" + trackId);

            if (!StringUtils.isEmptyOrWhitespaceOnly(trackId)) {
                try {
                    Client client = Client.create();
                    WebResource webResource = client.resource("http://localhost:8080/playlistEntry/add");

                    String input = "{\"playlistId\":" + playlistId + ",\"playlistEntries\":[{\"track\":{\"trackId\":" + trackId + "},\"offlineAvailable\":" + Boolean.parseBoolean(offlineAvailable) + "}]}";
                    ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

                    System.out.println(trackId + " - Track added!");

                    //check if response is successful
                    if (response.getStatus() < 200 && response.getStatus() >= 300)
                        throw new RuntimeException("Failed: HTTP error code:" + response.getStatus());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            String searchTrack = req.getParameter("title");

            String reset = req.getParameter("Reset");
            if ("reset".equals(reset)) {
                searchTrack = null;
                System.out.println("yay?");
            }

            WebClient webClient2 = WebClient.create("http://localhost:8080/").accept("application/json");
            Collection<? extends Track> tracksUnassigned;

            if (!StringUtils.isEmptyOrWhitespaceOnly(searchTrack))
                webClient2.path("/tracks/notInPlaylist/search?title=" + searchTrack + "&id=" + playlistId);
            else
                webClient2.path("/tracks/notInPlaylist/" + playlistId);

            tracksUnassigned = webClient2.getCollection(Track.class);

            req.setAttribute("TRACKS", tracksUnassigned);

            req.getRequestDispatcher("../../addTrack.jsp").forward(req, resp);
        }
    }
}
