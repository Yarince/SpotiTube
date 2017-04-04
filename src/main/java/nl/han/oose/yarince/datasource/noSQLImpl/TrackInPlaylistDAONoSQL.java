package nl.han.oose.yarince.datasource.noSQLImpl;

import nl.han.oose.yarince.datasource.ITrackInPlaylistDAO;
import nl.han.oose.yarince.domain.Playlist;
import nl.han.oose.yarince.domain.PlaylistEntry;

import javax.enterprise.inject.Alternative;
import java.util.HashMap;
import java.util.List;

import static nl.han.oose.yarince.datasource.noSQLImpl.PlaylistDAONoSQL.playlists;
import static nl.han.oose.yarince.datasource.noSQLImpl.TrackDAONoSQL.tracks;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 03/04/2017.
 */
@Alternative
public class TrackInPlaylistDAONoSQL implements ITrackInPlaylistDAO {
    static HashMap<Integer,PlaylistEntry> playlistEntries = new HashMap<>();

    static {
        PlaylistEntry playlistEntry = new PlaylistEntry(tracks.get(0), false);
        PlaylistEntry playlistEntry2 = new PlaylistEntry(tracks.get(1), false);

        playlistEntries.put(0,playlistEntry);
        playlistEntries.put(1,playlistEntry2);
    }


    @Override
    public void add(PlaylistEntry playlistEntry, int playlistId) {
        Playlist playlist = playlists.stream().filter(p -> p.getPlaylistId() == playlistId).findFirst().orElse(null);
        if (playlist != null)
            playlist.getPlaylistEntries().add(playlistEntry);
    }

    @Override
    public void deleteByPlaylist(int playlistID) {
        playlistEntries.remove(playlists.stream().filter(p-> p.getPlaylistId() == playlistID).findFirst());
    }

    @Override
    public List<PlaylistEntry> getALL() {
        return (List<PlaylistEntry>) playlistEntries.values();
    }

    @Override
    public List<PlaylistEntry> getByPlaylistId(int playlistID) {
        return (List<PlaylistEntry>) playlistEntries.values();
    }

    @Override
    public void deleteTrack(PlaylistEntry playlistEntry, int playlistID) {
        playlistEntries.remove(playlistID,playlistEntry);
    }
}
