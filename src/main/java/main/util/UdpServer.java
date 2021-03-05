package main.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServer {
    DatagramSocket datagramSocket;
    private int port;
    byte[] buf;

    public UdpServer(int port) {
        this.port = port;
        buf = new byte[128];
    }

    public void createServer() {
        try {
            datagramSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            System.out.println("Udp server socket exception : " + e.getMessage());
        }
    }

    public void recieveMessage() throws IOException {
        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            datagramSocket.receive(packet);
            System.out.println(new String(packet.getData()));
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Started to recieve....");
        UdpServer server = new UdpServer(12345);
        server.createServer();
        server.recieveMessage();
    }
}
