package pass1Assembler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.*;

public class pass1 {
	public static void main(String str[]) throws Exception
	{
		System.out.println("pass 1 of assembler");
		
		int lc = 0;
		BufferedReader sourceCode = new BufferedReader(new FileReader("/home/ubuntu/TE/spos/PASS1ASSEMBLER/input.asm"));
		BufferedWriter symTab = new BufferedWriter(new FileWriter("/home/ubuntu/TE/spos/PASS1ASSEMBLER/symtab"));
		
		String sourceLine;
	    while ((sourceLine = sourceCode.readLine()) != null)
	    {
	      String tokens[] = sourceLine.split(" ");
	      //System.out.println(line);
	      BufferedReader opTab = new BufferedReader(new FileReader("/home/ubuntu/TE/spos/PASS1ASSEMBLER/optab")); 
	      
	      
	      String opRow;
	      if(tokens.length>0)
	      {
	    	  if(tokens[0].equals("START"))
	    	  {
	    		  lc = Integer.parseInt( tokens[1]);
	    	  }
	    	  else if(tokens[0].equals("ORIGIN"))
	    	  {
	    		  lc = Integer.parseInt( tokens[1]);
	    	  }
	    	  else if(tokens[0].equals("END"))
	    	  {
	    		  opTab.close();
	    		  sourceCode.close();
	    		  symTab.close();
	    		  System.exit(0);
	    	  }
	      
	        while((opRow = opTab.readLine()) != null)
	      	{
	    	  	if(tokens.length > 0)
	    	  	{
	    		
	    	
	    		  	if(opRow.contains(tokens[0]))
	    		  	{	    			  
	    			  	System.out.println(opRow);
	    			  	System.out.println(tokens[0]);
	    			  	System.out.println(lc);
	    			  
	    			  	lc++;
	    		  	}
	    		 
	    	  	}
	    	  
	      	}
	      }
		  if(tokens.length > 2)
    	  {
    		  
    		  if(tokens[1].equals("EQU"))
    		  {
    			  String writeStr = tokens[0]+" "+"C"+" "+tokens[2]+"\n";
    			  System.out.println(writeStr);
    			  symTab.write(writeStr);
    		  }
    		  else if(tokens[1].equals("DC"))
    		  {
    			  String writeStr = tokens[0]+" "+"R"+" "+lc+"\n";
    			  System.out.println(writeStr);
    			  symTab.write(writeStr);
    			  lc++;
    		  }
    		  else if(tokens[1].equals("DS"))
    		  {
    			  String writeStr = tokens[0]+" "+"R"+" "+lc+"\n";
    			  System.out.println(writeStr);
    			  symTab.write(writeStr);
    			  lc=lc + Integer.parseInt( tokens[2]); 
    		  }
    
    	  }
	      opTab.close();
	    }
	    sourceCode.close();
	}
}
