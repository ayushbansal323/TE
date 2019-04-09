
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
class ALA
{
	int count;
	String arg[];
	String argval[];
	ALA(int length)
	{
		count=length;
		arg=new String[100];
		argval=new String[100];
	}
	 void display()
	{
		for(int i=0;i<count;i++) {
			System.out.println(arg[i]+"\t"+argval[i]+"\t");
		}
	}
	String search(String find)
	{
		for(int i=0;i<count;i++) {
			if(arg[i].equals(find)) {
				return argval[i];
			}
		}
		return find;
	}
}
public class MacroPass1 {
	static int MDTP,MNTP;
	public MacroPass1() {
		MDTP = MNTP = 0;
	}
	static void display(String a) throws Exception
	{
		BufferedReader input = new BufferedReader(new FileReader(a));
		String inputLine;
		while((inputLine = input.readLine()) != null )
		{
			System.out.println(inputLine);
		}
		input.close();
	}
	public static void main(String[] args) throws Exception {
		
		
		//File will be generated at your project folder
		BufferedReader input = new BufferedReader(new FileReader("input.asm"));
		BufferedWriter mnt = new BufferedWriter(new FileWriter("MNT"));
		mnt.write("INDEX\tNAME\tAddress in MDT\n");
		BufferedWriter mdt = new BufferedWriter(new FileWriter("MDT"));
		mdt.write("INDEX\t****MDT*****\n");
		BufferedWriter output = new BufferedWriter(new FileWriter("output.asm"));
		//output.write("TRY");
		String inputLine ;
		int Flag=0;
		ALA ala = null;
		while((inputLine = input.readLine()) != null )
		{
			//System.out.println(inputLine);
			String tokenLine[] = inputLine.split(" ");
			
			if(tokenLine[0].equalsIgnoreCase("MACRO")) {
				Flag=1;
				
				String macroNameLine = input.readLine();
				String macroTokenNameLine[] = macroNameLine.split(" ");
				mnt.write(MNTP+"\t"+macroTokenNameLine[0]+"\t\t"+MDTP+"\n");
				mdt.write(MDTP+"\t"+macroNameLine+"\n");
				String macroArgumentToken[]=macroTokenNameLine[1].split(",");
				ala = new ALA(macroArgumentToken.length);
				
				System.out.println("\nALA for "+macroTokenNameLine[0]);
				for(int i=0;i<macroArgumentToken.length;i++)
				{
					ala.arg[i]=macroArgumentToken[i];
					ala.argval[i]="#"+(i+1);
				}
				ala.display();
				MDTP++;
				MNTP++;
			}
			else if(Flag == 1) {
				mdt.write(MDTP+"\t"+tokenLine[0]+"\t");
				if(tokenLine.length>1)
				{
					String macroArgumentToken[]=tokenLine[1].split(",");
					for (int i = 0; i < macroArgumentToken.length; i++) {
						if(i==(macroArgumentToken.length-1))
						{
							mdt.write(ala.search(macroArgumentToken[i]));
						}
						else
						{
							mdt.write(ala.search(macroArgumentToken[i])+",");
						}
					}
				}
				mdt.write("\n");
				MDTP++;
			}
			if(Flag == 0) {
				output.write(inputLine+"\n");
			}
			if(tokenLine[0].equalsIgnoreCase("MEND")) {
				Flag=0;
				
			}	
		}
		input.close();
		mnt.close();
		mdt.close();
		output.close();
		System.out.println();
		display("MNT");
		System.out.println();
		display("MDT");
		System.out.println("\n output.asm");
		display("output.asm");
	}
}
/*
ALA for INCR
&X	#1	
&Y	#2	
&REG	#3	

ALA for DEC
&X	#1	
&Y	#2	
&REG	#3	

INDEX	NAME	Address in MDT
0	INCR		0
1	DEC		5

INDEX	****MDT*****
0	INCR &X,&Y,&REG
1	MOVER	#3,#1
2	ADD	#3,#2
3	MOVEM	#3,AREG
4	MEND	
5	DEC &X,&Y,&REG
6	MOVER	#3,#1
7	SUB	#3,#2
8	MOVEM	#3,#1
9	MEND	

 output.asm


START 100
INCR N1,N2,AREG
DEC N3,N4,BREG
END
*/
