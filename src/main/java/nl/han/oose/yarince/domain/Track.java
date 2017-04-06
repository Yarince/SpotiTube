package nl.han.oose.yarince.domain;

public class Track {

    private int trackId;
    private String performer,
            title,
            url;
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

    public String getTitle() {
        return title;
    }

    public TrackType getTrackType() {
        return trackType;
    }

    public long getDuration() {
        return duration;
    }

    public String getUrl() {
        return url;
    }
}
