package nl.han.oose.yarince.datasource.noSQLImpl;

import nl.han.oose.yarince.datasource.ITrackDAO;
import nl.han.oose.yarince.domain.*;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 03/04/2017.
 */
@Alternative
public class TrackDAONoSQL implements ITrackDAO {

    static List<Track> tracks = new ArrayList<>();

    static {
        Song song = new Song(0, "singer", "title", "url", 1, "album");
        Video video = new Video(1, "singer", "title", "url", 1, 1, "10/1/2015", "desc");

        tracks.add(song);
        tracks.add(video);
    }

    @Override
    public List<Track> getAllTracks() {
        return tracks;
    }

    @Override
    public List<Track> getUnusedTracks(int playlistId) {
        //todo cheat
        return tracks;
    }

    @Override
    public List<Track> findUnusedTracks(int playlistId, String title) {
        //todo cheat
        return tracks;
    }

    @Override
    public List<Track> findByTitle(String title) {
        //todo cheat
        return tracks;
    }

    @Override
    public Track findById(int id) {
        return tracks.get(id);
    }

    @Override
    public List<Track> findByPlaylist(int id) {
        //todo cheat
        return tracks;
    }
}
