import java.io.*; 
import java.net.*; 
  
class GoBackN { 
   int MAX_SEQ =  7;
static void send data(seq_nr frame nr, seq_nr frame expected, String buffer[ ])
{
/ * Construct and send a data frame. * /
frame s;
/ * scratch variable * /
}
/ * insert packet into frame * /
s.info = buffer[frame nr];
/ * insert sequence number into frame * /
s.seq = frame nr;
s.ack = (frame expected + MAX SEQ) % (MAX SEQ + 1); / * piggyback ack * /
/ * transmit the frame * /
to physical layer(&s);
/ * start the timer running * /
  public static void main(String args[]) throws Exception 
    { 
  
      DatagramSocket serverSocket = new DatagramSocket(8000); 
     while(true) 
        { 
          byte[] receiveData = new byte[1024]; 
      byte[] sendData  = new byte[1024]; 
  
          DatagramPacket receivePacket = 
             new DatagramPacket(receiveData, receiveData.length); 
           serverSocket.receive(receivePacket); 
           
          String sentence = new String(receivePacket.getData()); 
          
  	System.out.println("FROM CLIENT:" + sentence); 
  	
          InetAddress IPAddress = receivePacket.getAddress(); 
  
          int port = receivePacket.getPort(); 
  
                      String capitalizedSentence = sentence.toUpperCase(); 

          sendData = capitalizedSentence.getBytes(); 
  
          DatagramPacket sendPacket = 
             new DatagramPacket(sendData, sendData.length, IPAddress, 
                               port); 
  
          serverSocket.send(sendPacket); 
        } 
    } 
}  
