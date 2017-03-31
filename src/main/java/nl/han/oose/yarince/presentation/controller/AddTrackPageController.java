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
import java.util.ArrayList;
import java.util.Collection;

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
        if (playlistId != null) {
            WebClient webClient1 = WebClient.create("http://localhost:8080/").path("/playlists/id/" + playlistId).accept("application/json");
            req.setAttribute("PLAYLIST", webClient1.get(Playlist.class));

            WebClient webClient2 = WebClient.create("http://localhost:8080/").path("/tracks/notInPlaylist/" + playlistId).accept("application/json");
            Collection<? extends Track> tracksUnassigned = webClient2.getCollection(Track.class);
            Collection<Track> tracks = new ArrayList<>();

//            for (Track track : tracksUnassigned) {
//                switch (track.getTrackType()) {
//                    case Video:
//                        Video video = (Video) track;
//                        System.out.println("video.getPublicationDate() = " + video.getPublicationDate());
//                        break;
//                    case Song:
//                        Song song = (Song) track;
//                        System.out.println("video.getAlbum() = " + song.getAlbum());
//                        break;
//                }
//            }

            req.setAttribute("TRACKS", tracksUnassigned);
            String trackId = req.getParameter("trackId");
            String offlineAvailable = req.getParameter("offlineAvailable" + trackId);

            if (trackId != null) {

                Client client = Client.create();
                WebResource webResource = client.resource("http://localhost:8080/playlists/track");
                ObjectMapper mapper = new ObjectMapper();

                String input = "{\"playlistId\":" + playlistId + ",\"playlistEntries\":[{\"track\":{\"trackId\":" + trackId + "},\"offlineAvailable\":" + Boolean.parseBoolean(offlineAvailable) + "}]}";
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
