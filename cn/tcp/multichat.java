import java.io.*; 
import java.net.*;

class input implements Runnable
{
        BufferedReader inFromClient;
        String clientSentence; 
      String sentence;
      Socket connectionSocket;
      
        public input(BufferedReader inFromC)
        {
                 inFromClient = inFromC;
        }
        public void run()
        {
        
        }
} 

class output implements Runnable
{
        String clientSentence; 
      String sentence;
      Socket connectionSocket;
      DataOutputStream  outToClient;
      BufferedReader inFromUser 
      
        public output(DataOutputStream  outToC)
        {
                outToClient = outToC;
        }
} 

class MultithreadingDemo implements Runnable
{

      String clientSentence; 
      String sentence;
      Socket connectionSocket;
      int id;
        
    public MultithreadingDemo(Socket connectionS)
    {
         connectionSocket = connectionS;
    }    
    
    public void run()
    {
        try
        {
            BufferedReader inFromClient =new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
     
           BufferedReader inFromUser =new BufferedReader(new InputStreamReader(System.in)); 

           DataOutputStream  outToClient = new DataOutputStream(connectionSocket.getOutputStream()); 

          while(true)
          {

           clientSentence = inFromClient.readLine(); 
          
         System.out.println("FROM CLIENT ("+
                                Thread.currentThread().getId()+") :" +clientSentence);
          // capitalizedSentence = clientSentence.toUpperCase() + '\n'; 

           //outToClient.writeBytes(capitalizedSentence); 
           System.out.println("ENTER id:");     
           id=Integer.parseInt(inFromUser.readLine())   ;
           
           sentence=inFromUser.readLine();
           if(id == Thread.currentThread().getId())
           {    
           outToClient.writeBytes(sentence + '\n');
           }
           }    
 
        }
        catch (Exception e)
        {
            // Throwing an exception
            System.out.println ("Exception is caught");
        }
    }
}

class multichat { 

  public static void main(String argv[]) throws Exception 
    { 
      String clientSentence; 
      String sentence; 

      ServerSocket welcomeSocket = new ServerSocket(8000); 
  
      while(true) { 
  
            Socket connectionSocket = welcomeSocket.accept(); 
            
            Thread object = new Thread(new MultithreadingDemo(connectionSocket));
            object.start();     

        
        } 
    } 
} 
