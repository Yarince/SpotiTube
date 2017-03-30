package nl.han.oose.yarince.datasource;

import nl.han.oose.yarince.domain.PlaylistEntry;
import nl.han.oose.yarince.domain.Track;

import java.util.List;

/**
 * Created by Yarince on 24/03/2017.
 */
public interface ITrackInPlaylistDAO {

    void add(Track trackID, int playlistID);

    void deleteByPlaylist(int playlistID);

    List<PlaylistEntry> getALL();

    List<PlaylistEntry> getByPlaylistId(int playlistID);

}
