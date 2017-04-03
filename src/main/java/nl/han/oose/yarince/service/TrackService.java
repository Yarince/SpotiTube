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
 * Project name: SpotiTube.
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
    @Path("/notInPlaylist/{id}")
    @Produces("application/json")
    public List<Track> getUnusedTracks(@PathParam("id") String playlistId) {
        return trackDAO.getUnusedTracks(Integer.parseInt(playlistId));
    }

    @GET
    @Path("/notInPlaylist/search?title={title}&id={id}")
    @Produces("application/json")
    public List<Track> findUnusedTracks(@PathParam("id") String id, @PathParam("title") String title) {
        return trackDAO.findUnusedTracks(Integer.parseInt(id), title);
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

    @GET
    @Path("/id/{id}")
    @Produces("application/json")
    public Track findById(@PathParam("id") String id) {
        return trackDAO.findById(Integer.parseInt(id));
    }

}