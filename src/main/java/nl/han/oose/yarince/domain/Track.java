package nl.han.oose.yarince.domain;

public class Track {
    private String performer,
            title,
            url;
    private int trackId;
    private long duration;
    private TrackType trackType;

    public Track() {
    }

    public Track(int trackId, String performer, String title, String url, long duration, TrackType trackType) {
        this.trackId = trackId;
        this.performer = performer;
        this.title = title;
        this.url = url;
        this.duration = duration;
        this.trackType = trackType;
    }

    public int getTrackId() {
        return trackId;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public TrackType getTrackType() {
        return trackType;
    }
}
