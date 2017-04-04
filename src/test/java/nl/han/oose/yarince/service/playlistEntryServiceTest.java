package nl.han.oose.yarince.service;

import nl.han.oose.yarince.datasource.noSQLImpl.TrackInPlaylistDAONoSQL;
import nl.han.oose.yarince.domain.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 04/04/2017.
 */
class playlistEntryServiceTest {

    private playlistEntryService sut;

    @BeforeEach
    void setUp() {
        sut = new playlistEntryService();
        sut.trackInPlaylistDAO = new TrackInPlaylistDAONoSQL();
    }

    @Test
    void deleteTrack() {

        sut.deleteTrack(new Playlist());
    }

    @Test
    void addTrack() {
    }

}