package nl.han.oose.yarince.domain;

/**
 * Project name: SpotiTube.
 * Package: nl.han.oose.yarince.domain.
 * Created by Yarince on 25/03/2017.
 */
public class PlaylistEntry {

    private Track track;
    private boolean offlineAvailable;

    public PlaylistEntry() {
    }

    public PlaylistEntry(Track track, boolean offlineAvailable) {
        this.track = track;
        this.offlineAvailable = offlineAvailable;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }

    public Track getTrack() {
                return  track;
    }
}
