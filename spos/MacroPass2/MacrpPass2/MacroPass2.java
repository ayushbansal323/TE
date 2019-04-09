import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

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
	String searchname(String find)
	{
		for(int i=0;i<count;i++) {
			if(argval[i].equals(find))
			{
				return arg[i];
			}
		}
		return find;
	}
}
public class MacroPass2 {
	static String searchmacro(String find) throws Exception
	{
		BufferedReader mnt = new BufferedReader(new FileReader("MNT"));
		String mntLine ;
		while((mntLine = mnt.readLine()) != null )
		{
			String mnttokenLine[] = mntLine.split(" ");
			if( mnttokenLine[1].equals(find))
			{
				return mnttokenLine[2];
			}
		}
		
		return "-1";
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
		// TODO Auto-generated method stub
		BufferedReader input = new BufferedReader(new FileReader("input.asm"));
		BufferedWriter output = new BufferedWriter(new FileWriter("output.asm"));
		String inputLine ;
		while((inputLine = input.readLine()) != null )
		{
			//System.out.println(inputLine);
			String tokenLine[] = inputLine.split(" ");
			String addinMDT;
			ALA ala = null;
			if((addinMDT=searchmacro(tokenLine[0]))!="-1")
			{
				//System.out.println(addinMDT);
				BufferedReader mdt = new BufferedReader(new FileReader("MDT"));
				String mdtLine ;
				int Flag=0;
				while((mdtLine = mdt.readLine()) != null )
				{
					
					String mdttokenLine[] = mdtLine.split(" ");
					//System.out.println(mdttokenLine[0]);
					//System.out.println(mdttokenLine[1]);
					if(mdttokenLine[1].equalsIgnoreCase("MEND"))
					{
						
						Flag=0;
						continue;
					}
					if(mdttokenLine[0].equalsIgnoreCase(addinMDT))
					{
						//System.out.println(mdtLine);
						Flag=1;
						String macroArgumentToken[]=tokenLine[1].split(",");
						ala = new ALA(macroArgumentToken.length);
						
						System.out.println("\nALA for "+mdttokenLine[1]);
						for(int i=0;i<macroArgumentToken.length;i++)
						{
							ala.arg[i]=macroArgumentToken[i];
							ala.argval[i]="#"+(i+1);
						}
						ala.display();
					}
					else if(Flag == 1) {
						output.write(mdttokenLine[1]+"\t");
						if(mdttokenLine.length>2)
						{
							String macroArgumentToken[]=mdttokenLine[2].split(",");
							for (int i = 0; i < macroArgumentToken.length; i++) {
								if(i==(macroArgumentToken.length-1))
								{
									output.write(ala.searchname(macroArgumentToken[i]));
								}
								else
								{
									output.write(ala.searchname(macroArgumentToken[i])+",");
								}
							}
						}
						output.write("\n");
					}
					
					
				}
			}
			else
			{
					output.write(inputLine+"\n");
			}
		}
		output.close();
		System.out.println("\n output.asm \n");
		display("output.asm");
	}

}
/*
ALA for INCR
N1	#1	
N2	#2	
AREG	#3	

ALA for DEC
N3	#1	
N4	#2	
BREG	#3	

 output.asm 

START 100
MOVER	AREG,N1
ADD	AREG,N2
MOVEM	AREG,AREG
MOVER	BREG,N3
SUB	BREG,N4
MOVEM	BREG,N3
END
*/
