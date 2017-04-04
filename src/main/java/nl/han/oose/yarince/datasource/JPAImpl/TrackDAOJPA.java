package nl.han.oose.yarince.datasource.JPAImpl;

import nl.han.oose.yarince.datasource.ITrackDAO;
import nl.han.oose.yarince.domain.Song;
import nl.han.oose.yarince.domain.Track;
import nl.han.oose.yarince.domain.Video;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
public class TrackDAOJPA implements ITrackDAO {

    @Inject
    private IJPAConnection connection;

    @Override
    public List<Track> getAllTracks() {
        EntityManager entityManager = connection.getEntityManagerFactory().createEntityManager();

        return entityManager.createQuery("from Track", Track.class).getResultList();
    }

    @Override
    public List<Track> getUnusedTracks(int playlistId) {

        EntityManager entityManager = connection.getEntityManagerFactory().createEntityManager();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM TRACK WHERE TRACK_ID NOT IN (SELECT TRACK_ID FROM track_in_playlist WHERE PLAYLIST_ID = ?)")

        return entityManager.createQuery("where TRACK_ID NOT IN (SELECT TRACK_ID FROM track_in_playlist WHERE PLAYLIST_ID = ?)").getResultList();
    }

    @Override
    public List<Track> findUnusedTracks(int playlistId, String title) {

        List<Track> resultList = new ArrayList<>();
        try (
                Connection con = connection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM TRACK WHERE TRACK_ID NOT IN (SELECT TRACK_ID FROM track_in_playlist WHERE PLAYLIST_ID = ?) AND TITLE LIKE ?")
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setString(2, "%" + title + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                resultList.add(getTrackByResultSet(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public List<Track> findByTitle(String title) {
        List<Track> resultList = new ArrayList<>();
        try (
                Connection con = connection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM TRACK WHERE TITLE = ?")
        ) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultList.add(getTrackByResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public Track findById(int id) {
        Track track = null;
        try (
                Connection con = connection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM track WHERE TRACK_ID = ?")
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                track = getTrackByResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return track;
    }

    @Override
    public List<Track> findByPlaylist(int id) {
        List<Track> resultList = new ArrayList<>();
        try (
                Connection con = connection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM track WHERE TRACK_ID IN (SELECT TRACK_ID FROM track_in_playlist WHERE PLAYLIST_ID = ?)")
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                resultList.add(getTrackByResultSet(resultSet));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    private Track getTrackByResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.getString("ALBUM") != null) {
            return new Song(
                    resultSet.getInt("TRACK_ID"),
                    resultSet.getString("PERFORMER"),
                    resultSet.getString("TITLE"),
                    resultSet.getString("URL"), resultSet.getLong("DURATION"),
                    resultSet.getString("ALBUM")
            );
        } else {
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(resultSet.getDate("PUBLICATIONDATE"));
            return new Video(
                    resultSet.getInt("TRACK_ID"),
                    resultSet.getString("PERFORMER"),
                    resultSet.getString("TITLE"),
                    resultSet.getString("URL"),
                    resultSet.getLong("DURATION"),
                    resultSet.getInt("PLAYCOUNT"),
                    resultSet.getDate("PUBLICATIONDATE").toString(),
                    resultSet.getString("DESCRIPTION")
            );
        }
    }
}
