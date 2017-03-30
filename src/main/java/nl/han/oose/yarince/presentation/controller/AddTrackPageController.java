package nl.han.oose.yarince.presentation.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import nl.han.oose.yarince.domain.Playlist;
import nl.han.oose.yarince.domain.Track;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.map.ObjectMapper;

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
@WebServlet("/playlist/details/addTrack")
public class AddTrackPageController extends HttpServlet {

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
        System.out.println("playlistId = " + playlistId);
        if (playlistId != null) {
            WebClient webClient1 = WebClient.create("http://localhost:8080/").path("/playlists/id/" + playlistId).accept("application/json");
            req.setAttribute("PLAYLIST", webClient1.get(Playlist.class));

            WebClient webClient2 = WebClient.create("http://localhost:8080/").path("/tracks").accept("application/json");
            req.setAttribute("TRACKS", webClient2.getCollection(Track.class));


            String trackId = req.getParameter("trackId");
            String offlineAvailable = req.getParameter("offlineAvailable");

            if (trackId != null) {

                Client client = Client.create();
                WebResource webResource = client.resource("http://localhost:8080/playlists/track");
                ObjectMapper mapper = new ObjectMapper();

                WebClient webClient3 = WebClient.create("http://localhost:8080/").path("/playlists/id/" + playlistId).accept("application/json");
                Playlist playlist = webClient3.get(Playlist.class);

                WebClient webClient4 = WebClient.create("http://localhost:8080/").path("/tracks/id/" + trackId).accept("application/json");
                Track track = webClient4.get(Track.class);

                String trackAsString = mapper.writeValueAsString(track);
                String playlistAsString = mapper.writeValueAsString(playlist);

                System.out.println("trackAsString = " + trackAsString);
                System.out.println("playlistAsString = " + playlistAsString);
                //TODO HIER BEN IK GEBLEVEN!

                String input = "{\"playlistId\":1,\"owner\":\"Yarince\",\"name\":\"Mooie liedjes\",\"totalLength\":1287," +
                        "\"playlistEntries\":[{" +
                        "\"track\":{\"performer\":\"BartTheAmazing\",\"title\":\"Best Song Ever Vol. 2\",\"url\":\"/Bart/swek-liedje2\",\"trackId\":2,\"duration\":154,\"trackType\":\"Video\"},\"offlineAvailable\":false}]\"}";
                ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
                System.out.println(trackId + " - Track added!");
                //check if response is successful
                if (response.getStatus() > 200 && response.getStatus() < 300) {
                    throw new RuntimeException("Failed: HTTP error code:" + response.getStatus());
                }


            }

            req.getRequestDispatcher("../../addTrack.jsp").forward(req, resp);
        }
    }
}
