package nl.han.oose.yarince.presentation.controller;

import com.mysql.cj.core.util.StringUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import nl.han.oose.yarince.domain.Playlist;
import nl.han.oose.yarince.domain.User;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 23/03/2017.
 */
@WebServlet("/home")
public class HomeViewPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    private void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("USER");
        if (user != null) {
            String newPlaylistName = req.getParameter("newPlaylistName");
            if (!StringUtils.isEmptyOrWhitespaceOnly(newPlaylistName)) {
                newPlaylist(user, newPlaylistName);
            }

            WebClient webClient = WebClient.create("http://localhost:8080/").path("/playlists/owner/" + user.getUsername()).accept("application/json");
            req.setAttribute("PLAYLIST_BY_OWNER", webClient.getCollection(Playlist.class));

            String deletePlaylistId = req.getParameter("deletePlaylist");
            if (!StringUtils.isEmptyOrWhitespaceOnly(deletePlaylistId))
                deletePlaylist(deletePlaylistId);
        }
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    private void newPlaylist(User user, String newPlaylistName) {
        try {
            Client client = Client.create();
            WebResource webResource = client.resource("http://localhost:8080/playlists");

            //this will be the input from the user
            String input = "{\"owner\":\"" + user.getUsername() + "\",\"name\":\"" + newPlaylistName + "\"}";

            ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

            //check if response is successful
            if (response.getStatus() > 200 && response.getStatus() < 300)
                throw new RuntimeException("Failed: HTTP error code:" + response.getStatus());

            System.out.println(input + "\n" + "Message recieved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deletePlaylist(String deletePlaylistId) {
        try {

            Client client = Client.create();
            WebResource webResource = client.resource("http://localhost:8080/playlists/delete");

            String input = "{\"playlistId\":" + deletePlaylistId + "}";

            ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

            //check if response is successful
            if (response.getStatus() < 200 && response.getStatus() >= 300)
                throw new RuntimeException("Failed: HTTP error code:" + response.getStatus());
            System.out.println(input + "\n" + "Message recieved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}