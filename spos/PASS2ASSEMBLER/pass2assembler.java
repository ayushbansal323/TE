import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.*;

/**
 * pass2assembler
 */
class PoolTable
{
    int count;
    int literalCount[];
    PoolTable()
    {
        count=0;
        literalCount=new int[100];
    }
    void initialize(String file) throws Exception
    {
        BufferedReader input = new BufferedReader(new FileReader(file));
        String inputLine=input.readLine();
        while((inputLine = input.readLine())!=null)
        {
            String inputLineTokenize[]=inputLine.split(" ");
            literalCount[count]=Integer.parseInt(inputLineTokenize[1]);
            count++;
        }
        input.close();
    }
}
class SymbolTable{
    int count;
    int value[];
    String type[];
    String Symbol[];
    SymbolTable()
    {
        count=0;
        value=new int[100];
        type=new String[100];
        Symbol=new String[100];

    }
    int search(String s)
    {
        for (int i = 0; i < count; i++) {
            if(Symbol[i].equals(s))
            {
                return i;
            }
        }
        return -1;
    }
    void initialize(String file) throws Exception
    {
        BufferedReader input = new BufferedReader(new FileReader(file));
        String inputLine=input.readLine();
        while((inputLine = input.readLine())!=null)
        {
            String inputLineTokenize[]=inputLine.split(" ");
            value[count]=Integer.parseInt(inputLineTokenize[3]);
            type[count]=inputLineTokenize[2];
            Symbol[count]=inputLineTokenize[1];
            count++;
        }
        input.close();
    }
}
class LiteralTable{
    int count;
    int address[];
    String Symbol[];
    LiteralTable()
    {
        count=0;
        address=new int[100];
        Symbol=new String[100];
    }
    int search(String s)
    {
        for (int i = 0; i < count; i++) {
            if(Symbol[i].equals(s))
            {
                return i;
            }
        }
        return -1;
    }
    void initialize(String file) throws Exception
    {
        BufferedReader input = new BufferedReader(new FileReader(file));
        String inputLine=input.readLine();
        while((inputLine = input.readLine())!=null)
        {
            String inputLineTokenize[]=inputLine.split(" ");
            address[count]=Integer.parseInt(inputLineTokenize[2]);
            Symbol[count]=inputLineTokenize[1];
            count++;
        }
        input.close();
    }

}
public class pass2assembler {
    static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(Exception e) { 
            return false; 
        }
        // only got here if we didn't return false
        return true;
    }
    public static void main(String[] args) throws Exception {
    System.out.println(" PASS 2 ASSEMBLER ");
    String AD[]={"EQU","START","LTORG","END","ORIGIN"};
    String OPTAB[]={"ADD","SUB","MULT","DIV","MOVER","MOVEM","COMP","BC","STOP"};
    String REG[]={"AX","BX","CX","DX"};
    String CC[]= {"LT","LE","GT","GE","EQ","ANY"};
    int LC=0;
    int poolcount=0;
    int literalcount=0;
    BufferedReader input = new BufferedReader(new FileReader("input.i"));
    BufferedWriter output = new BufferedWriter(new FileWriter("output.o"));
    PoolTable pt=new PoolTable();
    LiteralTable LT=new LiteralTable();
    SymbolTable symtb=new SymbolTable();
    LT.initialize("LTTAB");
    symtb.initialize("SYMTAB");
    pt.initialize("PTTAB");
    
    String inputLine;
    while ((inputLine = input.readLine())!=null) {
        int Flag=0;
        inputLine = inputLine.replace("(","");
        inputLine = inputLine.replace(")","");
        inputLine = inputLine.replace(',', ' ');
        String tokenizeline[] = inputLine.split(" ");
        /*for (int i = 0; i < tokenizeline.length; i++) {
            System.out.println(tokenizeline[i]);
        }*/
        if(tokenizeline[0].equals("IS"))
        {
            System.out.print(LC+"\t+"+tokenizeline[1]);
            output.write(LC+"\t+"+tokenizeline[1]);
            Flag=1;
            for (int i = 2; i < tokenizeline.length; i=i+2) {
                if(tokenizeline[i].equals("C"))
                {
                    System.out.print("\t"+tokenizeline[i+1]);
                    output.write("\t"+tokenizeline[i+1]);
                }
                else if(tokenizeline[i].equals("R"))
                {
                    System.out.print("\t"+tokenizeline[i+1]);
                    output.write("\t"+tokenizeline[i+1]);
                }
                else if(tokenizeline[i].equals("L"))
                {
                    System.out.print("\t"+LT.address[Integer.parseInt(tokenizeline[i+1])]);
                    output.write("\t"+LT.address[Integer.parseInt(tokenizeline[i+1])]);
                }
                else if(tokenizeline[i].equals("S"))
                {
                    System.out.print("\t"+symtb.value[Integer.parseInt(tokenizeline[i+1])]);
                    output.write("\t"+symtb.value[Integer.parseInt(tokenizeline[i+1])]);
                }
                else if(isInteger(tokenizeline[i]))
                {
                    System.out.print("\t"+tokenizeline[i]);
                    output.write("\t"+tokenizeline[i]);
                }
            }
            LC++;
            System.out.println();
            output.write("\n");
        }
        else if(tokenizeline[0].equals("AD"))
        {
            if(AD[Integer.parseInt(tokenizeline[1])].equals("START"))
            {
                LC=Integer.parseInt(tokenizeline[3]);
            }
            else if(AD[Integer.parseInt(tokenizeline[1])].equals("ORIGIN"))
            {
                LC=Integer.parseInt(tokenizeline[3]);
            }
            else if(AD[Integer.parseInt(tokenizeline[1])].equals("LTORG"))
            {
                for (int i = 0; i < pt.literalCount[poolcount]; i++) {
                    System.out.print(LC+"\t"+LT.Symbol[literalcount]);
                    output.write(LC+"\t"+LT.Symbol[literalcount]);
                    System.out.println();
                    output.write("\n");
                    literalcount++;
                    LC++;
                }
                poolcount++;
            }
            else if(AD[Integer.parseInt(tokenizeline[1])].equals("END"))
            {
                for (int i = 0; i < pt.literalCount[poolcount]; i++) {
                    System.out.print(LC+"\t"+LT.Symbol[literalcount]);
                    output.write(LC+"\t"+LT.Symbol[literalcount]);
                    System.out.println();
                    output.write("\n");
                    literalcount++;
                    LC++;
                }
                break;
            }
        }
        else if(tokenizeline[0].equals("DS"))
        {
            if(Integer.parseInt(tokenizeline[1])==0)
            {
                System.out.print(LC+"\t"+tokenizeline[5]);
                output.write(LC+"\t"+tokenizeline[5]);
                System.out.println();
                output.write("\n");
                LC++;
            }
            if(Integer.parseInt(tokenizeline[1])==1)
            {
                LC=LC+Integer.parseInt(tokenizeline[5]);
            }
        }

    }
    System.out.println("\n\tLiteral Table\t\nIndex\tSymbol\taddress");
    for (int i = 0; i < LT.count; i++) {
        System.out.println(i+"\t"+LT.Symbol[i]+"\t"+LT.address[i]);
    }
    System.out.println("\n\tPOOL Table\t\nIndex\tLiteral count");
    for (int i = 0; i < LT.count; i++) {
        System.out.println(i+"\t"+pt.literalCount[i]);
    }
    System.out.println("\n\tSymbol Table\t\nIndex\tSymbol\tType\tValue");
    for (int i = 0; i < symtb.count; i++) {
        System.out.println(i+"\t"+symtb.Symbol[i]+"\t"+symtb.type[i]+"\t"+symtb.value[i]);
    }
    output.close();
    }
}