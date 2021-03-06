package nl.han.oose.yarince.presentation.controller;

import com.mysql.cj.core.util.StringUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import nl.han.oose.yarince.domain.Playlist;
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
        if (!StringUtils.isEmptyOrWhitespaceOnly(playlistId)) {

            String newPlaylistName = req.getParameter("newPlaylistName");
            String newPlaylistNameSave = req.getParameter("newPlaylistNameSave");
            if (!StringUtils.isEmptyOrWhitespaceOnly(newPlaylistName)
                    && "Save".equals(newPlaylistNameSave))
                rename(playlistId, newPlaylistName);


            WebClient webClient = WebClient.create("http://localhost:8080/").path("/playlists/id/" + playlistId).accept("application/json");
            Playlist playlist = webClient.get(Playlist.class);

            req.setAttribute("PLAYLIST", playlist);
            req.setAttribute("TRACKS", playlist.getPlaylistEntries());


            String deleteTrackId = req.getParameter("deleteTrackId");
            if (!StringUtils.isEmptyOrWhitespaceOnly(deleteTrackId))
                deleteTrack(playlistId, deleteTrackId);
        }
        req.getRequestDispatcher("../playlist.jsp").forward(req, resp);
    }

    private void deleteTrack(String playlistId, String deleteTrackId) {
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8080/playlistEntry/delete");

        //this will be the input from the user
        String input = "{\"playlistId\":" + playlistId + ",\"playlistEntries\":[{\"track\":{\"trackId\":" + deleteTrackId + "}}]}";

        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

        //check if response is successful
        if (response.getStatus() < 200 && response.getStatus() >= 300)
            throw new RuntimeException("Failed: HTTP error code:" + response.getStatus());
        System.out.println(input + "\n" + "Message recieved");
    }

    private void rename(String playlistId, String newPlaylistName) {
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8080/playlists/save");

        String input = "{\"playlistId\":" + playlistId + ",\"name\":\"" + newPlaylistName + "\"}";

        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

        //check if response is successful
        if (response.getStatus() < 200 && response.getStatus() >= 300)
            throw new RuntimeException("Failed: HTTP error code:" + response.getStatus());
        System.out.println(input + "\n" + "Message recieved");
    }
}
