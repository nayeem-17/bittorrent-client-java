package main.tasks;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Random;

import main.util.HexConverter;
import main.util.UdpClient;
import main.util.UrlParser;

// stackOF link of a problem regarding this file https://stackoverflow.com/questions/15184376/torrent-related-tracker-response-on-udp-protocol-update-3-working
public class ConnectionRequest {
    private String magicConstant = "041727101980";
    public String tracker;

    public ConnectionRequest(String tracker) {
        this.tracker = tracker;
    }

    public CResponse getUdpConnectionId() throws UnknownHostException, Exception {

        byte[] messagebuff = makeConnectionMessage();

        UrlParser urlParser = new UrlParser(tracker);
        urlParser.parse();

        String TRACKER = urlParser.host;
        int PORT = urlParser.port;

        UdpClient udpClient = new UdpClient();
        byte[] response = null;

        // Have to implement this, if failed to catch the data package

        // for (int i = 1; i < 12; i++) {
        // response = udpClient.sendMessage(InetAddress.getByName(TRACKER), messagebuff,
        // PORT);
        // if (response == null) {
        // System.out.println("Failed to get data for " + i + " th time!!!");
        // Thread.sleep(5000 * i);
        // } else {
        // break;
        // }
        // }

        response = udpClient.sendMessage(InetAddress.getByName(TRACKER), messagebuff, PORT);

        CResponse res = new CResponse(response);
        return res;
    }

    private byte[] makeConnectionMessage() {
        byte[] messagebuff = new byte[16];
        /**
         * initialing array's first two elememt with 0, because the output arraylength
         * of HexConverter.HexToBytes is 6
         */

        messagebuff[0] = (byte) 0;
        messagebuff[1] = (byte) 0;

        // adding magicConstant

        byte[] arr = HexConverter.HexToBytes(magicConstant);
        System.arraycopy(arr, 0, messagebuff, 2, arr.length);

        // adding action

        byte[] action = ByteBuffer.allocate(4).putInt(0).array();
        System.arraycopy(action, 0, messagebuff, 8, action.length);

        // adding random bytes as transaction id

        Random random = new Random();
        byte[] transictionId = new byte[4];
        random.nextBytes(transictionId);
        System.arraycopy(transictionId, 0, messagebuff, 12, transictionId.length);

        return messagebuff;
    }

    public static void main(String[] args) {
        try {
            ConnectionRequest connectionRequest = new ConnectionRequest("udp://tracker.opentrackr.org:1337/announce");
            System.out.println(connectionRequest.getUdpConnectionId());
            /**
             * expexted output
             * 
             * Response [action=0, connectionId=[-63, 81, -29, 83, 75, 102, -50, -65],
             * transictionId=-1212769590]
             *
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
