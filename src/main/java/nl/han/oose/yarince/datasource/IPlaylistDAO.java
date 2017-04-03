package nl.han.oose.yarince.datasource;

import nl.han.oose.yarince.domain.Playlist;

import java.util.List;

public interface IPlaylistDAO {

    List<Playlist> getAllPlaylists();

    List<Playlist> findByOwner(String owner);

    Playlist findById(int id);

    void save(Playlist playlist);

    void delete(int playlistId);

    void add(Playlist playlist);
}
