package nl.han.oose.yarince.service;

import nl.han.oose.yarince.datasource.IPlaylistDAO;
import nl.han.oose.yarince.datasource.ITrackInPlaylistDAO;
import nl.han.oose.yarince.domain.Playlist;
import nl.han.oose.yarince.domain.PlaylistEntry;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("/playlists")
public class PlaylistService {

    @Inject
    private IPlaylistDAO playlistDAO;

    @GET
    @Produces("application/json")
    public List<Playlist> getAllPlaylists() {
        return playlistDAO.getAllPlaylists();
    }

    @GET
    @Path("/owner/{owner}")
    @Produces("application/json")
    public List<Playlist> findByOwner(@PathParam("owner") String owner) {
        return playlistDAO.findByOwner(owner);
    }

    @GET
    @Path("/id/{id}")
    @Produces("application/json")
    public Playlist findById(@PathParam("id") String id) {
        int i = Integer.parseInt(id, 10);
        return playlistDAO.findById(i);
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public String add(Playlist playlist) {
        playlistDAO.add(playlist);
        return "Name: " + playlist.getName() + " owner: " + playlist.getOwner();
    }

    @POST
    @Path("/track")
    @Consumes("application/json")
    @Produces("application/json")
    public String addTrack(Playlist playlist) {
        playlistDAO.addTrack(playlist);
        return "Track added!";
    }

    //TODO TEMP
    @Inject
    ITrackInPlaylistDAO trackInPlaylistDAO;

    @GET
    @Path("/track")
    @Produces("application/json")
    public PlaylistEntry getTrack() {
        return trackInPlaylistDAO.getByPlaylistId(1).get(0);
    }

    @POST
    @Path("/delete")
    @Consumes("application/json")
    public void delete(Playlist playlist) {
        playlistDAO.delete(playlist.getPlaylistId());
    }
}