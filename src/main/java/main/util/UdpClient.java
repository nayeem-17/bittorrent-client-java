package main.util;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
    public InetAddress address;
    public DatagramSocket datagramSocket;

    public byte[] sendMessage(InetAddress inetAddress, byte[] message, int port) throws Exception {
        datagramSocket = new DatagramSocket();
        DatagramPacket packet = new DatagramPacket(message, message.length, inetAddress, port);
        datagramSocket.send(packet);
        byte[] response = new byte[16];
        DatagramPacket responsePacket = new DatagramPacket(response, response.length);
        datagramSocket.receive(responsePacket);
        return response;
    }

}
