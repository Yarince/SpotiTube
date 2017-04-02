package nl.han.oose.yarince.presentation.model;

import nl.han.oose.yarince.domain.Song;
import nl.han.oose.yarince.domain.Track;
import nl.han.oose.yarince.domain.Video;

import java.util.List;

public class PlaylistModel {
    List<Song> songs;
    List<Video> videos;

    public PlaylistModel(List<Song> songs, List<Video> videos) {
        this.songs = songs;
        this.videos = videos;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public Video getVideo(Track track) {
        return (Video) track;
    }

    public Song getSong(Track track) {
        return (Song) track;
    }
}
