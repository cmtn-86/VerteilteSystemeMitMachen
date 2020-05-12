import java.io.*;
import java.net.*;
public class UDPFeuchtigkeit {
    public static void main(String[] args) throws IOException {

        //Die von Server geschickte Daten wird in BufferedReader gehalten.
        BufferedReader standartIn=new BufferedReader(new InputStreamReader(System.in));

        //Adress von Server, um Byte an Server zu senden.
        InetAddress IPaddress   = InetAddress.getByName("localhost");

        //UDP Socket erstellt.
        DatagramSocket socket   = new DatagramSocket();

        //Erstellung von DatangramPaket um die kommende und gehende Pakete zu verwalten.
        DatagramPacket inPacket, outPacket;

        //Deklaration der Pornummer
        int port=4711;

        //Data Groesse wird bestimmt
        byte data[]             = new byte[1024];

        //Client bleibt immer am Laufen bis wir es stoppen.
        int sinir=0;
        while (sinir<100){
            System.out.println("Geben Sie eine Nachricht ein, die der Server erhalten soll. " );
            //
            String userInput = standartIn.readLine();

            if ( userInput.equals( "." ) ) return;
            // else if not stop token...

            // schicken
            data = userInput.getBytes();
            outPacket = new DatagramPacket(data, data.length, IPaddress, port);
            socket.send(outPacket);
            outPacket.setLength(data.length);


            // erhalten
            inPacket = new DatagramPacket(data, data.length);
            socket.receive( inPacket);
            System.out.println("UDP Recieved from SERVER: "
                    + inPacket.getAddress().toString()
                    + " Port " + inPacket.getPort() + " :" );
            System.out.println( new String( inPacket.getData() ) );

            inPacket.setLength(data.length);//nur was geschrieben wird wird an  Server geschickt.
            sinir++;

        }
    }


    }


