package nl.han.oose.yarince.domain;

import java.util.List;

public class Playlist {

    private int playlistId;
    private String owner;
    private String name;
    private long totalLength;

    private List<PlaylistEntry> playlistEntries;

    public Playlist(int playlistId, String owner, String name, List<PlaylistEntry> playlistEntries) {
        this.playlistId = playlistId;
        this.owner = owner;
        this.name = name;
        this.playlistEntries = playlistEntries;
        totalLength = getTotalLength();
    }

    public Playlist() {
    }

    private long getTotalLength() {
        long totalLength = 0;
        for (PlaylistEntry playlistEntry : this.playlistEntries) {
            Track track = playlistEntry.getTrack();
            if (track != null)
                totalLength += track.getDuration();
        }
        return totalLength;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlaylistEntry> getPlaylistEntries() {
        return playlistEntries;
    }

    public String getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public int getPlaylistId() {
        return playlistId;
    }
}
