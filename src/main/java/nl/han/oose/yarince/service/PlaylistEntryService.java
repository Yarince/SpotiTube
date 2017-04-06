package nl.han.oose.yarince.service;

import nl.han.oose.yarince.datasource.ITrackInPlaylistDAO;
import nl.han.oose.yarince.domain.Playlist;
import nl.han.oose.yarince.domain.PlaylistEntry;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 02/04/2017.
 */
@Path("/playlistEntry")
public class PlaylistEntryService {

    @Inject
    ITrackInPlaylistDAO trackInPlaylistDAO;

    @POST
    @Path("/delete")
    @Consumes("application/json")
    @Produces("text/html")
    public String deleteTrack(Playlist playlist) {
        PlaylistEntry playlistEntry = playlist.getPlaylistEntries().get(0);
        trackInPlaylistDAO.deleteTrack(playlistEntry, playlist.getPlaylistId());
        return "Track " + playlistEntry.getTrack().getTrackId() + " deleted!";
    }

    //todo query parameter

    @POST
    @Path("/add")
    @Consumes("application/json")
    @Produces("text/html")
    public String addTrack(Playlist playlist) {
        PlaylistEntry playlistEntry = playlist.getPlaylistEntries().get(0);
        trackInPlaylistDAO.add(playlistEntry, playlist.getPlaylistId());
        return "Track "+playlistEntry.getTrack().getTrackId()+" added!";
    }
}
