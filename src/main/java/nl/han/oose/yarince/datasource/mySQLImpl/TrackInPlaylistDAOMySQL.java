package nl.han.oose.yarince.datasource.mySQLImpl;

import nl.han.oose.yarince.datasource.IConnection;
import nl.han.oose.yarince.datasource.ITrackInPlaylistDAO;
import nl.han.oose.yarince.domain.PlaylistEntry;

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
 * Created by Yarince on 24/03/2017.
 */
@Default
public class TrackInPlaylistDAOMySQL implements ITrackInPlaylistDAO {

    @Inject
    private IConnection connection;

    @Inject
    private TrackDAOMySQL trackDAOMySQL;

    @Override
    public void add(PlaylistEntry playlistEntry, int playlistID) {
        try (
                Connection con = connection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("INSERT IGNORE INTO track_in_playlist (PLAYLIST_ID, TRACK_ID,OFFLINE_AVAILABLE) VALUES (?,?,?)")
        ) {
            preparedStatement.setInt(1, playlistID);
            preparedStatement.setInt(2, playlistEntry.getTrack().getTrackId());
            preparedStatement.setBoolean(3, playlistEntry.isOfflineAvailable());
            preparedStatement.execute();
            System.out.println("Track in playlist Added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByPlaylist(int playlistID) {
        try (
                Connection con = connection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM track_in_playlist WHERE PLAYLIST_ID = ? ")
        ) {
            preparedStatement.setInt(1, playlistID);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PlaylistEntry> getALL() {
        List<PlaylistEntry> resultList = new ArrayList<>();
        try (
                Connection con = connection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("SELECT *FROM track_in_playlist");
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next())
                resultList.add(getPlaylistEntryByResultSet(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public List<PlaylistEntry> getByPlaylistId(int playlistID) {
        List<PlaylistEntry> resultList = new ArrayList<>();
        try (
                Connection con = connection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("SELECT *FROM track_in_playlist WHERE PLAYLIST_ID = ?")
        ) {
            preparedStatement.setInt(1, playlistID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                resultList.add(getPlaylistEntryByResultSet(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public void deleteTrack(PlaylistEntry playlistEntry, int playlistID) {
        try (
                Connection con = connection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM track_in_playlist WHERE TRACK_ID = ? AND PLAYLIST_ID = ?")
        ) {
            preparedStatement.setInt(1, playlistEntry.getTrack().getTrackId());
            preparedStatement.setInt(2, playlistID);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PlaylistEntry getPlaylistEntryByResultSet(ResultSet resultSet) throws SQLException {
        return new PlaylistEntry(trackDAOMySQL.findById(resultSet.getInt("TRACK_ID")), resultSet.getBoolean("OFFLINE_AVAILABLE"));
    }
}
