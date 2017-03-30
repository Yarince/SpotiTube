package nl.han.oose.yarince.datasource;

import nl.han.oose.yarince.domain.Playlist;
import nl.han.oose.yarince.domain.PlaylistEntry;

import java.util.List;

public interface IPlaylistDAO {

    List<Playlist> getAllPlaylists();

    List<Playlist> findByOwner(String owner);

    Playlist findById(int id);

    void save(Playlist playlist);

    void delete(int playlist);

    void add(Playlist playlist);

    void addTrack(PlaylistEntry playlistEntry, int playlistId);

    void addTrack(Playlist playlist);
}
