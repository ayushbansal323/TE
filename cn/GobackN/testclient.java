import java.io.*;
import java.net.*;
import java.math.*;
import java.util.*;

class testclient
{

public static void main(String args[])throws IOException
{
InetAddress addr=InetAddress.getByName("127.0.0.1");
System.out.println(addr);

Socket connection=new Socket(addr,5000);

BufferedInputStream in=new BufferedInputStream(connection.getInputStream());
DataOutputStream out=new DataOutputStream(connection.getOutputStream());
Scanner scr=new Scanner(System.in);// this will be used to accept i/p from console

System.out.println(" client is Connected to server" + addr);
System.out.println("Enter the number of frames to be requested to the server");
int c=scr.nextInt();

out.write(c); // write no of frames on client socket
out.flush();

System.out.println("Enter the type of trans. Error=1 ; No Error=0");
int choice=scr.nextInt();
out.write(choice);  //write choice on socket
int ferror=0;
if(choice  == 1 )
{
	System.out.println("Enter the frame no of Error:");
	ferror=scr.nextInt();
	out.write(ferror);
}

int check=0;
int i=0;
int j=0;

if(choice==0)
{
for(j=0;j<c;++j)
{
i=in.read();  //read all frames one by one from server
System.out.println("received frame no: "+i);
System.out.println("Sending acknowledgement for frame no: "+i);
out.write(i); //write ack  to socket
out.flush();
}
out.flush();
}
else
{
for(j=0;j<c;++j)
{
i=in.read();  //read 0,1,2,3 frame
if(i==check)
{
System.out.println("received frame no: "+i);
System.out.println("Sending acknowledgement for frame no: "+i);
out.write(i); //sent ack of frame 0,1
++check;
}
else
{
--j;
System.out.println("Discarded frame no: "+i);
System.out.println("Sending NEGATIVE ack");
out.write(-1);
}
out.flush();
}
}//end of else for error

in.close();
out.close();
System.out.println("Quiting");

}// end of main method
}// end of main class



/*
lab-a-26@laba26-Vostro-3669:~$ javac testserver.java
lab-a-26@laba26-Vostro-3669:~$ java testserver
server Waiting for connection....
Received request for sending frames
Sending....
sending frame number 0
Waiting for acknowledgement
received acknowledgement for frame 0 as 0
sending frame number 1
Waiting for acknowledgement
received acknowledgement for frame 1 as 1
sending frame number 2
Waiting for acknowledgement
received acknowledgement for frame 2 as 2
sending frame number 3
Waiting for acknowledgement
received acknowledgement for frame 3 as 3
Quiting
lab-a-26@laba26-Vostro-3669:~$ 


lab-a-26@laba26-Vostro-3669:~$ javac testclient.java
lab-a-26@laba26-Vostro-3669:~$ java testclient
Localhost/127.0.0.1
 client is Connected to serverLocalhost/127.0.0.1
Enter the number of frames to be requested to the server
4
Enter the type of trans. Error=1 ; No Error=0
0
received frame no: 0
Sending acknowledgement for frame no: 0
received frame no: 1
Sending acknowledgement for frame no: 1
received frame no: 2
Sending acknowledgement for frame no: 2
received frame no: 3
Sending acknowledgement for frame no: 3
Quiting
lab-a-26@laba26-Vostro-3669:~$ 


*/

