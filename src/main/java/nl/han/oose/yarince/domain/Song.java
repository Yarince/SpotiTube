package nl.han.oose.yarince.domain;

import javax.persistence.Entity;

@Entity
public class Song extends Track {
    private String album;

    public Song(int trackId, String performer, String title, String url, long duration, String album) {
        super(trackId, performer, title, url, duration, TrackType.Song);
        this.album = album;
    }

    public Song(){

    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
