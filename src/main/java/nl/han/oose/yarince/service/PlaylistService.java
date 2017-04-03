package nl.han.oose.yarince.service;

import nl.han.oose.yarince.datasource.IPlaylistDAO;
import nl.han.oose.yarince.domain.Playlist;

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

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public String add(Playlist playlist) {
        playlistDAO.add(playlist);
        return "Name: " + playlist.getName() + " owner: " + playlist.getOwner();
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
    @Path("/delete")
    @Consumes("application/json")
    @Produces("text/html")
    public String delete(Playlist playlist) {
        playlistDAO.delete(playlist.getPlaylistId());
        return "Playlist " + playlist.getPlaylistId() + " deleted ";
    }

    @POST
    @Path("/save")
    @Produces("application/json")
    @Consumes("application/json")
    public String save(Playlist playlist) {
        playlistDAO.save(playlist);
        return "Plalist " + playlist.getPlaylistId() + " Name changed to: " + playlist.getName();
    }
}