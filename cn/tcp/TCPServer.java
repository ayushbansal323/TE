import java.io.*; 
import java.net.*; 

class TCPServer { 

  public static void main(String argv[]) throws Exception 
    { 
      String clientSentence; 
      String sentence; 

      ServerSocket welcomeSocket = new ServerSocket(8000); 
  
      while(true) { 
  
            Socket connectionSocket = welcomeSocket.accept(); 

           BufferedReader inFromClient =new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
     
           BufferedReader inFromUser =new BufferedReader(new InputStreamReader(System.in)); 

           DataOutputStream  outToClient = new DataOutputStream(connectionSocket.getOutputStream()); 

          while(true)
          {

           clientSentence = inFromClient.readLine(); 
          
         System.out.println("FROM CLIENT:"+clientSentence);
          // capitalizedSentence = clientSentence.toUpperCase() + '\n'; 

           //outToClient.writeBytes(capitalizedSentence); 
           
           sentence=inFromUser.readLine();
           outToClient.writeBytes(sentence + '\n');
           }
        } 
    } 
} 
