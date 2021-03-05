package main.util;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UdpClient {
    public InetAddress address;
    public DatagramSocket datagramSocket;

    public void sendMessage(InetAddress inetAddress, String message, int port) throws Exception {
        datagramSocket = new DatagramSocket();
        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, inetAddress, port);
        datagramSocket.send(packet);
    }

    public static void main(String[] args) throws UnknownHostException, Exception {
        UdpClient client = new UdpClient();
        for (int i = 0; i < 120; i++) {
            client.sendMessage(InetAddress.getLocalHost(), "Hello " + i * i + "there", 12345);
        }
        System.out.println("All messages sent");
    }
}
