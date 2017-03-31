package nl.han.oose.yarince.presentation.controller;

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
        doExecution(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doExecution(req, resp);
    }

    private void doExecution(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebClient webClient;

        User user = (User) req.getSession().getAttribute("USER");

        if (user != null) {
            webClient = WebClient.create("http://localhost:8080/").path("/playlists/owner/" + user.getUsername()).accept("application/json");
            req.setAttribute("PLAYLIST_BY_OWNER", webClient.getCollection(Playlist.class));

            String playlistName = req.getParameter("playlistName");

            if (playlistName != null) {

                Client client = Client.create();
                WebResource webResource = client.resource("http://localhost:8080/playlists");

                String input = "{\"owner\":\"" + user.getUsername() + "\",\"name\":\"" + playlistName + "\",\"trackInPlaylist\":[]}"; //this will be the input from the user

                ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

                //check if response is successful
                if (response.getStatus() > 200 && response.getStatus() < 300) {
                    throw new RuntimeException("Failed: HTTP error code:" + response.getStatus());
                }
                System.out.println(input + "\n" + "Message recieved");


            }
            String deletePlaylistId = req.getParameter("deletePlaylist");

            if (deletePlaylistId != null) {

                Client client = Client.create();
                WebResource webResource = client.resource("http://localhost:8080/playlist/delete");

                String input = "{\"playlistId\":" + deletePlaylistId + "}";

                ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

                //check if response is successful
                if (response.getStatus() > 200 && response.getStatus() < 300) {
                    throw new RuntimeException("Failed: HTTP error code:" + response.getStatus());
                }
                System.out.println(input + "\n" + "Message recieved");
            }
        }
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}