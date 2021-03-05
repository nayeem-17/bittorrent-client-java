package main.tasks;

public class GetPeers {
    private String tracker;
    private String[] peers;

    public GetPeers(String tracker) {
        this.tracker = tracker;
    }

    public String[] getPeers() {
        return peers;
    }

    public boolean sendMessage(String message) {

        return false;
    }
}
