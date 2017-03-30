package nl.han.oose.yarince.service;

import nl.han.oose.yarince.datasource.ITrackDAO;
import nl.han.oose.yarince.domain.Track;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by Yarince on 23/03/2017.
 */
@Path("/tracks")
public class TrackService {

    @Inject
    private ITrackDAO trackDAO;

    @GET
    @Produces("application/json")
    public List<Track> getAllTracks() {
        return trackDAO.getAllTracks();
    }

    @GET
    @Path("/playlist/{id}")
    @Produces("application/json")
    public List<Track> findByPlaylist(@PathParam("id") String id) {
        return trackDAO.findByPlaylist(Integer.parseInt(id));
    }

    @GET
    @Path("/title/{title}")
    @Produces("application/json")
    public List<Track> findByTitle(@PathParam("title") String title) {
        return trackDAO.findByTitle(title);
    }
}