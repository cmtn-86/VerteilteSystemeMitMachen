import java.io.*;
import java.net.*;


public class UDPServer {
    public static void main(String[] args) throws IOException {
    int serverPort=4711;
    int dataLength =1024;

    DatagramSocket socket=new DatagramSocket(serverPort);

    DatagramPacket inPacket, outPacket;
    byte data[]=new byte[dataLength];
    System.out.println("UDP Server wird gestartet...");
        while (true){
            // get request
            inPacket           = new DatagramPacket(data, data.length);
            socket.receive( inPacket);

            // decode request, prepare answer
            InetAddress IPadd  = inPacket.getAddress();
            int port           = inPacket.getPort();
            String line        = new String ( inPacket.getData());
            line   = line.toUpperCase();
            data   = line.getBytes();

            outPacket = new DatagramPacket( data, data.length, IPadd, port);

            // Debug Print (Logging)
            System.out.println("Out Packet: IPadd " + outPacket.getAddress().toString()
                    + " port " + outPacket.getPort()
                    + " Data length " + outPacket.getLength()
                    + " : " + line );

            // send answer (echo)
            socket.send(outPacket);
            outPacket.setLength(data.length);
        }

    }


}
