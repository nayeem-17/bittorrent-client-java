package main.tasks;

import main.util.UdpClient;

/*
* TODOS
* 1.Send a connect request
* 2.Get the connect response and extract the connection id
* 3.Use the connection id to send an announce request - this is where we
* tell the tracker which files we’re interested in
* 4.Get the announce response and extract the peers list
*/
public class GetPeers {
    public final String tracker;
    private String[] peers;

    public GetPeers(String tracker) {
        this.tracker = tracker;
    }

    public String[] getPeers() {
        UdpClient udpClient = new UdpClient();

        return peers;
    }

}
