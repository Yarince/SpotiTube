package nl.han.oose.yarince.service;

import nl.han.oose.yarince.datasource.IPlaylistDAO;
import nl.han.oose.yarince.domain.Playlist;
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
public class PlaylistServiceTest {

    @Mock
    private IPlaylistDAO playlistDAO;

    @InjectMocks
    private PlaylistService sut;

    @Test
    public void findByOwner() throws Exception {
        List<Playlist> playlists = new ArrayList<>();
        Playlist playlist = new Playlist(0, "Yarince", "Mooie liedjes", new ArrayList<>());
        playlists.add(playlist);

        Mockito.when(playlistDAO.findByOwner(Mockito.anyString())).thenReturn(playlists);

        assertEquals(playlists, sut.findByOwner("any String"));
    }

    @Test
    public void testGetAllPlaylists() throws Exception {
        sut.getAllPlaylists();
        verify(playlistDAO).getAllPlaylists();
    }

    @Test
    public void testFindById() throws Exception {
        Playlist playlist = new Playlist(0, "Yarince", "Mooie liedjes", new ArrayList<>());

        Mockito.when(playlistDAO.findById(Mockito.anyInt())).thenReturn(playlist);

        assertEquals(playlist, sut.findById("1"));
    }

    @Test
    public void testAdd() throws Exception {
        Playlist playlist = new Playlist(0, "Yarince", "Mooie liedjes", new ArrayList<>());

        assertEquals("Name: Mooie liedjes owner: Yarince",sut.add(playlist));
        verify(playlistDAO).add(Mockito.any(Playlist.class));
    }

    @Test
    public void testDelete() throws Exception {
        Playlist playlist = new Playlist(0, "Yarince", "Mooie liedjes", new ArrayList<>());

        assertEquals("Playlist 0 deleted ", sut.delete(playlist));
        verify(playlistDAO).delete(Mockito.anyInt());
    }

    @Test
    public void testSave() throws Exception {
        Playlist playlist = new Playlist(0, "Yarince", "Nieuwe naam", new ArrayList<>());

        assertEquals("Playlist 0 Name changed to: Nieuwe naam", sut.save(playlist));
        verify(playlistDAO).save(Mockito.any(Playlist.class));
    }

}