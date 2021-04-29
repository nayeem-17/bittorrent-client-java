package main.tasks;

public class AResponse {
    public int connection_id;
    public int action;
    public int transaction_id;
    public String info_hash;
    public String peer_id;
    public int downloaded;
    public int left;
    public int uploaded;
    public int event;
    public int iPAddress;
    public int key;
    public int num_want;
    public int port;

    public AResponse(byte[] message) {

    }
}
