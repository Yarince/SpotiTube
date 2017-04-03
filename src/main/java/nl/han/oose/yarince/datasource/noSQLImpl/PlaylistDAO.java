package nl.han.oose.yarince.datasource.noSQLImpl;

import nl.han.oose.yarince.datasource.IPlaylistDAO;
import nl.han.oose.yarince.domain.*;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static nl.han.oose.yarince.datasource.noSQLImpl.TrackInPlaylistDAO.playlistEntries;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 03/04/2017.
 */
@Alternative
public class PlaylistDAO implements IPlaylistDAO {

    static List<Playlist> playlists = new ArrayList<>();

    static {
        playlists.add(new Playlist(0, "Yarince", "Mooie liedjes", new ArrayList<>(playlistEntries.values())));
        playlists.add(new Playlist(1, "Yarince", "Mooie liedjes2", new ArrayList<>()));
        playlists.add(new Playlist(2, "Yarince", "Mooie liedjes3", new ArrayList<>()));
        playlists.add(new Playlist(3, "Yarince", "Mooie liedjes4", new ArrayList<>()));
        playlists.add(new Playlist(4, "Yarince", "Mooie liedjes5", new ArrayList<>()));
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlists;
    }

    @Override
    public List<Playlist> findByOwner(String owner) {
        return playlists.stream().filter(p -> p.getOwner().equals(owner)).collect(Collectors.toList());
    }

    @Override
    public Playlist findById(int id) {
        return playlists.stream().filter(p -> p.getPlaylistId() == id).findFirst().orElse(null);
    }

    @Override
    public void save(Playlist playlist) {
        Playlist editedPlaylist = playlists.stream().filter(p -> p.getPlaylistId() == playlist.getPlaylistId()).findFirst().orElse(null);
        editedPlaylist.setName(playlist.getName());
    }

    @Override
    public void delete(int playlistId) {
        Playlist playlist = playlists.stream().filter(p -> p.getPlaylistId() == playlistId).findFirst().orElse(null);
        playlists.remove(playlist);
    }

    @Override
    public void add(Playlist playlist) {
        playlists.add(playlist);
    }
}
