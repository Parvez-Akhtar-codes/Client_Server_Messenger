import java.net.*;
import java.util.*;

public class Client {

    private DatagramSocket datagramSocket;
    private InetAddress inetAddress;
    private byte[] buffer;

    public Client(DatagramSocket datagramSocket, InetAddress inetAddress) {
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
    }

    public void sendThenReceive(){
        Scanner sc = new Scanner(System.in);
        while (true){
            try{
                String message = sc.nextLine();
                buffer = message.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buffer , buffer.length , inetAddress , 1234);
                datagramSocket.send(datagramPacket);
                datagramSocket.receive(datagramPacket);
                String messageFromServer = new String(datagramPacket.getData(), 0 , datagramPacket.getLength());
                System.out.println("The Server Says you said : "+messageFromServer);
            }catch (Exception e){
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket1 = new DatagramSocket();
        InetAddress inetAddress1 = InetAddress.getByName("localhost");
        Client client = new Client(datagramSocket1 , inetAddress1);
        System.out.println("Send datagram packet to server");
        client.sendThenReceive();
    }
}
