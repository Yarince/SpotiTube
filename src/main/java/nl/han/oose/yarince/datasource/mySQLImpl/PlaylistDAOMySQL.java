package nl.han.oose.yarince.datasource.mySQLImpl;


import nl.han.oose.yarince.datasource.IPlaylistDAO;
import nl.han.oose.yarince.datasource.ITrackInPlaylistDAO;
import nl.han.oose.yarince.domain.Playlist;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 20/03/2017.
 */
@Default
public class PlaylistDAOMySQL implements IPlaylistDAO {
    /**
     * Set connection class
     */
    @Inject
    private IMySQLConnection connection;

    @Inject
    private ITrackInPlaylistDAO trackInPlaylistDAO;

    /**
     * @return List of all Playlists
     */
    @Override
    public List<Playlist> getAllPlaylists() {
        List<Playlist> resultList = new ArrayList<>();
        try (
                Connection con = connection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM PLAYLIST");
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                resultList.add(getPlaylistByResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * @param owner owner of playlist
     * @return List of all Playlists of owner
     */
    @Override
    public List<Playlist> findByOwner(String owner) {
        List<Playlist> resultList = new ArrayList<>();
        try (
                Connection con = connection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM PLAYLIST WHERE OWNER LIKE  ?")
        ) {
            preparedStatement.setString(1, '%' + owner + '%');
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultList.add(getPlaylistByResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * @param id id of playlist
     * @return List of all playlists of
     */
    @Override
    public Playlist findById(int id) {
        Playlist result = null;
        try (
                Connection con = connection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM PLAYLIST WHERE PLAYLIST_ID LIKE  ?")
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                result = getPlaylistByResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param playlist Playlist to be saved to database
     */
    @Override
    public void save(Playlist playlist) {
        try (
                Connection con = connection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("UPDATE playlist SET NAME = ? WHERE PLAYLIST_ID = ?")
        ) {
            preparedStatement.setString(1, playlist.getName());
            preparedStatement.setInt(2, playlist.getPlaylistId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param playlistId Playlist to be deleted from database
     */
    @Override
    public void delete(int playlistId) {
        try (
                Connection con = connection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM PLAYLIST WHERE PLAYLIST_ID = ?")
        ) {
            preparedStatement.setInt(1, playlistId);
            trackInPlaylistDAO.deleteByPlaylist(playlistId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param playlist Playlist to add to database
     */
    @Override
    public void add(Playlist playlist) {
        try (
                Connection con = connection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO PLAYLIST (OWNER,NAME) VALUES (?,?)")
        ) {
            preparedStatement.setString(1, playlist.getOwner());
            preparedStatement.setString(2, playlist.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Playlist getPlaylistByResultSet(ResultSet resultSet) throws SQLException {
        int playlistId = resultSet.getInt("PLAYLIST_ID");
        return new Playlist(playlistId, resultSet.getString("OWNER"), resultSet.getString("NAME"), trackInPlaylistDAO.getByPlaylistId(playlistId));
    }
}