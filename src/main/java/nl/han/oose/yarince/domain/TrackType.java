package nl.han.oose.yarince.domain;

public enum TrackType {
        Song("SONG"),
        Video("VIDEO");

        private final String type;

        TrackType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
}