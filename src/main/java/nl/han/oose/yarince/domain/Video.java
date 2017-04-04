package nl.han.oose.yarince.domain;

import javax.persistence.Entity;

@Entity
public class Video extends Track {

    private int playCount;
    private String publicationDate;
    private String description;

    public Video(int trackId, String performer, String title, String url, long duration, int playCount, String publicationDate, String description) {
        super(trackId, performer, title, url, duration, TrackType.Video);
        this.playCount = playCount;
        this.publicationDate = publicationDate;
        this.description = description;
    }

    public Video() {
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
