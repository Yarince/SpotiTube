package nl.han.oose.yarince.datasource;

import nl.han.oose.yarince.domain.Track;

import java.util.List;

public interface ITrackDAO {

    List<Track> getAllTracks();

    List<Track> getUnusedTracks(int playlistId);

    List<Track> findByTitle(String title);

    Track findById(int id);

    List<Track> findByPlaylist(int id);
}
