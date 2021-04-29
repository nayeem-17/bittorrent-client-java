package main.tasks;

import java.nio.ByteBuffer;
import java.util.Random;

import torrent.libs.TorrentFile;

public class AnnounceRequest {
    public CResponse cResponse;
    public TorrentFile torrentFile;

    public AnnounceRequest(CResponse cResponse, TorrentFile torrentFile) {
        this.cResponse = cResponse;
        this.torrentFile = torrentFile;
    }

    public byte[] makeAnnounceRequestMessage() {
        byte[] messageBuf = new byte[98];

        // adding connection id got from connection response
        System.arraycopy(cResponse.getConnectionId(), 0, messageBuf, 8, cResponse.getConnectionId().length);

        // action
        byte[] action = ByteBuffer.allocate(4).putInt(1).array();
        System.arraycopy(action, 0, messageBuf, 8, action.length);

        // transiction_id
        Random random = new Random();
        byte[] transiction_id = new byte[8];
        random.nextBytes(transiction_id);
        System.arraycopy(transiction_id, 0, messageBuf, 12, transiction_id.length);

        // info_hash
        byte[] infoHash = torrentFile.getInfoHash().getBytes();
        System.arraycopy(infoHash, 0, messageBuf, 16, 20);

        // peer_id
        byte[] peer_id = new byte[20];
        random.nextBytes(transiction_id);
        System.arraycopy(peer_id, 0, messageBuf, 36, 20);

        // downloaded

        byte[] downloaded = ByteBuffer.allocate(8).array();
        System.arraycopy(downloaded, 0, messageBuf, 56, 8);

        // left

        long[] length = torrentFile.getLengths();
        byte[] left;
        // uploaded

        // event

        // Ip address default 0

        // key

        // num_want default -1

        // port

        return messageBuf;
    }

    public AResponse getUdpAnnounceResponse() {
        return null;
    }

    public static void main(String[] args) {
        byte[] downloaded = ByteBuffer.allocate(8).array();
        System.out.println(1);
    }
}
