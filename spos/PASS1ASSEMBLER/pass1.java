package pass1Assembler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.*;

class ltTab{
	String literal;
	int location;
	ltTab()
	{
		literal = new String();
		location = 0;
	}
}

public class pass1 {
	
	
	public static void main(String str[]) throws Exception
	{
		System.out.println("pass 1 of assembler");
		
		int lc = 0;
		int ltlength=0;
		ltTab lttab[] = new ltTab[100] ;
		BufferedReader sourceCode = new BufferedReader(new FileReader("/home/ubuntu/TE/spos/PASS1ASSEMBLER/input.asm"));
		BufferedWriter symTab = new BufferedWriter(new FileWriter("/home/ubuntu/TE/spos/PASS1ASSEMBLER/symtab"));
	
		String sourceLine;
	    while ((sourceLine = sourceCode.readLine()) != null)
	    {
	      String tokens[] = sourceLine.split(" ");
	      //System.out.println(sourceLine);
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
	    		 
	    		  System.out.print("\t\t\t\t"+ltlength);
	    		  BufferedWriter ltfile = new BufferedWriter(new FileWriter("/home/ubuntu/TE/spos/PASS1ASSEMBLER/lttab"));
	    		  for(int j=0;j<ltlength;j++)
					 {
	    			  if(lttab[j].location == 0)
	    			  {
	    				  lttab[j].location=lc;
	    				  lc++;
	    			  }
	    			  String wrt = lttab[j].literal+" "+lttab[j].location+"\n";
	    			  ltfile.write(wrt);
	    			  System.out.print("\t"+wrt);
					 }
	    		  ltfile.close();
	    		  System.exit(0);
	    	  }
	    	  else if(tokens[0].equals("LTORG"))
	    	  {
	    		  for(int j=0;j<ltlength;j++)
					 {
	    			  if(lttab[j].location == 0)
	    			  {
	    				  lttab[j].location=lc;
	    				  lc++;
	    			  }
					 }
	    	  }
	      
	        while((opRow = opTab.readLine()) != null)
	      	{
	        	String opRowToken[] = opRow.split(" ");
	    	  	
	    	
	    		if(opRowToken[0].equals(tokens[0]))
	    		{	    			  
	    			 System.out.println(opRowToken[0]);
	    			 System.out.println(tokens[0]);
	    			 System.out.println(lc);
	    			  
	    			 lc++;
	    			 
	    			 if(tokens.length>1)
	    			 {
	    				 String Substr[]=tokens[1].split(",");
	    				 for(int i=0;i<Substr.length;i++)
	    				 {
	    					 if(Substr[i].startsWith("=F"))
	    					 {
	    						 for(int j=0;j<ltlength;j++)
	    						 {
	    							 if(lttab[j].literal == Substr[i])
	    							 {
	    								 break;
	    							 }
	    						 }
	    						 lttab[ltlength] = new ltTab();
	    						 lttab[ltlength].literal=Substr[i];
	    						 System.out.println("\t\t\t"+lttab[ltlength].literal);
	    						 ltlength++;
	    						 break;
	    					 
	    					 }
	    				 }
	    			 }
	    		}
	    		 
	    	  	
	    	  
	      	}
	      }
		  if(tokens.length > 2)
    	  {
    		  
    		  if(tokens[1].equals("EQU"))
    		  {
    			  String writeStr = tokens[0]+" "+"A"+" "+tokens[2]+"\n";
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



