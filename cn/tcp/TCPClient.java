import java.io.*; 
import java.net.*; 
import java.util.Scanner;
class TCPClient { 

    public static void main(String argv[]) throws Exception 
    { 
        String sentence; 
        String modifiedSentence; 
        

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 

        Socket clientSocket = new Socket("10.30.74.50", 8000); 

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); 

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 

       
      while(true)
      {
       
        sentence = inFromUser.readLine(); 

        if(sentence.equalsIgnoreCase("bye"))
            
            break;
       else
       {     
        outToServer.writeBytes(sentence + '\n'); 

        modifiedSentence = inFromServer.readLine(); 
       
        System.out.println("FROM SERVER: " + modifiedSentence);
       
       }       
          
     }
        clientSocket.close(); 
  }                 
    
} 
