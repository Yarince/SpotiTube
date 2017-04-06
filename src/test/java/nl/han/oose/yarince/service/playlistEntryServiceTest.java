package nl.han.oose.yarince.service;

import nl.han.oose.yarince.datasource.ITrackInPlaylistDAO;
import nl.han.oose.yarince.domain.Playlist;
import nl.han.oose.yarince.domain.PlaylistEntry;
import nl.han.oose.yarince.domain.Track;
import nl.han.oose.yarince.domain.TrackType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 06/04/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class playlistEntryServiceTest {

    @Mock
    private ITrackInPlaylistDAO trackInPlaylistDAO;

    @InjectMocks
    private PlaylistEntryService sut;

    @Test
    public void deleteTrack() throws Exception {
        List<PlaylistEntry> playlistEntries = new ArrayList<>();
        playlistEntries.add(new PlaylistEntry(new Track(0, "", "", "", 0, TrackType.Song), false));
        Playlist playlist = new Playlist(0, "Yarince", "Mooie liedjes", playlistEntries);

        assertEquals("Track 0 deleted!", sut.deleteTrack(playlist));
        verify(trackInPlaylistDAO).deleteTrack(Mockito.any(PlaylistEntry.class), Mockito.anyInt());
    }

    @Test
    public void addTrack() throws Exception {
        List<PlaylistEntry> playlistEntries = new ArrayList<>();
        playlistEntries.add(new PlaylistEntry(new Track(0, "", "", "", 0, TrackType.Song), false));
        Playlist playlist = new Playlist(0, "Yarince", "Mooie liedjes", playlistEntries);

        assertEquals("Track 0 added!", sut.addTrack(playlist));
        verify(trackInPlaylistDAO).add(Mockito.any(PlaylistEntry.class), Mockito.anyInt());
    }


}