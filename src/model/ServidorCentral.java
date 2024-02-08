/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.*;
import java.net.*;
import java.util.Queue;
import java.util.concurrent.*;

public class ServidorCentral {
    private static final int UDP_PORT = 7879;
    private static Queue<String> partidaQueue = new ConcurrentLinkedQueue<>();
    private static ConcurrentHashMap<String, String> partides = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        DatagramSocket udpSocket = null;

        try {
            udpSocket = new DatagramSocket(UDP_PORT);
            System.out.println("Servidor central en funcionament.");

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                udpSocket.receive(request);

                String data = new String(request.getData(), 0, request.getLength());
                InetAddress clientAddress = request.getAddress();
                int clientPort = request.getPort();

                String response = processRequest(data, clientAddress, clientPort);
                DatagramPacket reply = new DatagramPacket(response.getBytes(), response.length(), clientAddress, clientPort);
                udpSocket.send(reply);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (udpSocket != null && !udpSocket.isClosed()) {
                udpSocket.close();
            }
        }
    }

    private static String processRequest(String data, InetAddress clientAddress, int clientPort) {
        String[] parts = data.split(" ");
        String clientIP = clientAddress.getHostAddress();

        if (parts[0].equals("CREAR")) {
            String port_joc = parts[1];
            int portJoc = Integer.parseInt(port_joc);
            return crearPartida(clientIP, portJoc);
        } else if (parts[0].equals("UNIR-ME")) {
            return unirSeAPartida(clientIP);
        } else {
            return "ERROR Comanda no vàlida";
        }
    }

    private static String crearPartida(String clientIP, int portJoc) {
        String key = clientIP + "::" + portJoc;

        if (!partides.containsKey(key)) {
            partidaQueue.add(key);
            partides.put(key, null);
            return "OK";
        } else {
            return "ERROR Ja hi ha una partida registrada amb aquesta IP i port";
        }
    }

    private static String unirSeAPartida(String clientIP) {
        if (!partidaQueue.isEmpty()) {
            String partidaKey = partidaQueue.poll();
            partides.put(partidaKey, clientIP);
            return partidaKey;
        } else {
            return "Esperant a que hi hagi partides disponibles.";
        }
    }
}

