import java.io.*;


/**
 * pass1assembler
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

}
public class pass1assembler {
    static int search(String s[],String find,int length)
    {
        for (int i = 0; i < length; i++) {
            if(s[i].equals(find))
            {
                return i;
            }
        }
        return -1;
    } 
    static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(Exception e) { 
            return false; 
        }
        // only got here if we didn't return false
        return true;
    }
    public static void main(String[] args) throws Exception{
        System.out.println(" PASS 1 ASSEMBLER ");
        String AD[]={"EQU","START","LTORG","END","ORIGIN"};
        String OPTAB[]={"ADD","SUB","MULT","DIV","MOVER","MOVEM","COMP","BC","STOP"};
        String REG[]={"AX","BX","CX","DX"};
        String CC[]= {"LT","LE","GT","GE","EQ","ANY"};
        int LC=0;
        PoolTable pt=new PoolTable();
        LiteralTable LT=new LiteralTable();
        SymbolTable symtb=new SymbolTable();
        BufferedReader input = new BufferedReader(new FileReader("input.asm"));
        BufferedWriter output = new BufferedWriter(new FileWriter("output.i"));
        BufferedWriter symtab = new BufferedWriter(new FileWriter("SYMTAB"));
        BufferedWriter lttab = new BufferedWriter(new FileWriter("LTTAB"));
        BufferedWriter pttab = new BufferedWriter(new FileWriter("PTTAB"));
        String inputLine ;
        while((inputLine=input.readLine())!=null)
        {
            //System.out.println(inputLine);
            inputLine=inputLine.replace(',',' ');
            String Tokenizeline[]=inputLine.split(" ");
            int loc;
            if((loc=search(OPTAB, Tokenizeline[0],OPTAB.length))!=-1)
            {
                
                int temp;
                System.out.print("(IS,"+loc+")\t");
                output.write("(IS,"+loc+")\t");
                if(Tokenizeline[0].equals("BC"))
                {
                    temp =search(CC, Tokenizeline[1],CC.length);
                    System.out.print("("+temp+")\t");
                    output.write("("+temp+")\t");

                }
                else
                {
                    for (int i = 1; i < Tokenizeline.length; i++) {
                        if(Tokenizeline[i].startsWith("=F"))
                        {
                            temp = LT.search(Tokenizeline[i]);
                            if(temp == -1)
                            {
                                temp=LT.count;
                                LT.Symbol[LT.count]=Tokenizeline[i];
                                LT.count++;    
                            }
                            System.out.print("(L,"+temp+")\t");
                            output.write("(L,"+temp+")\t");
                            
                        }
                        else if((temp=search(REG, Tokenizeline[i],REG.length))!=-1)
                        {
                            System.out.print("(R,"+temp+")\t");
                            output.write("(R,"+temp+")\t");
                        }
                        else if(isInteger(Tokenizeline[i]))
                        {
                            System.out.print("(C,"+Tokenizeline[i]+")\t");
                            output.write("(C,"+Tokenizeline[i]+")\t");
                        }
                        else
                        {
                            temp = symtb.search(Tokenizeline[i]);
                            if(temp == -1)
                            {
                                temp=symtb.count;
                                symtb.Symbol[symtb.count]=Tokenizeline[i];
                                symtb.count++;
                            }
                            System.out.print("(S,"+temp+")\t");
                            output.write("(S,"+temp+")\t");
                        }
                    }
                }
                LC++;
                System.out.print("\n");
                output.write("\n");
                continue;
            }
            else if((loc=search(AD, Tokenizeline[0],AD.length))!=-1)
            {
                System.out.print("(AD,"+loc+")\t");
                output.write("(AD,"+loc+")\t");
                if(Tokenizeline[0].equals("START")){
                    System.out.print("(C,"+Tokenizeline[1]+")\t");
                    output.write("(C,"+Tokenizeline[1]+")\t");
                    LC=Integer.parseInt(Tokenizeline[1]);
                }
                else if(Tokenizeline[0].equals("LTORG")){
                    for (int i = 0; i < LT.count; i++) {
                        if(LT.address[i]==0)
                        {
                            LT.address[i]=LC;
                            LC++;
                            pt.literalCount[pt.count]++;
                        }
                    }
                    pt.count++;
                }
                else if(Tokenizeline[0].equals("END")){
                    for (int i = 0; i < LT.count; i++) {
                        if(LT.address[i]==0)
                        {
                            LT.address[i]=LC;
                            LC++;
                            pt.literalCount[pt.count]++;
                        }
                    }
                    pt.count++;
                    break;
                }
                else if(Tokenizeline[0].equals("ORIGIN")){
                    System.out.print("(C,"+Tokenizeline[1]+")\t");
                    output.write("(C,"+Tokenizeline[1]+")\t");
                    LC=Integer.parseInt(Tokenizeline[1]);
                }
                System.out.print("\n");
                output.write("\n");
                continue;
            }
            else if(Tokenizeline.length>2)
            {
                int temp;
                if(Tokenizeline[1].equals("EQU")){
                    temp = symtb.search(Tokenizeline[0]);
                    if(temp == -1)
                    {
                        temp=symtb.count;
                        symtb.Symbol[temp]=Tokenizeline[0];
                        symtb.type[temp]="A";
                        symtb.value[temp]=Integer.parseInt(Tokenizeline[2]);
                        symtb.count++;
                    }
                    else
                    {
                        symtb.type[temp]="A";
                        symtb.value[temp]=Integer.parseInt(Tokenizeline[2]);
                    }
                    System.out.print("(AD,0)\t(S,"+temp+")\t(C,"+symtb.value[temp]+")\n");
                    output.write("(AD,0)\t(S,"+temp+")\t(C,"+symtb.value[temp]+")\n");
                }
                else if(Tokenizeline[1].equals("DC")){
                    temp = symtb.search(Tokenizeline[0]);
                    if(temp == -1)
                    {
                        temp=symtb.count;
                        symtb.Symbol[temp]=Tokenizeline[0];
                        symtb.type[temp]="R";
                        symtb.value[temp]=LC;
                        symtb.count++;
                    }
                    else
                    {
                        symtb.type[temp]="R";
                        symtb.value[temp]=LC;
                    }
                    LC++;
                    System.out.print("(DS,0)\t(S,"+temp+")\t(C,"+Tokenizeline[2]+")\n");
                    output.write("(DS,0)\t(S,"+temp+")\t(C,"+Tokenizeline[2]+")\n");
                }
                else if(Tokenizeline[1].equals("DS")){
                    temp = symtb.search(Tokenizeline[0]);
                    if(temp == -1)
                    {
                        temp=symtb.count;
                        symtb.Symbol[temp]=Tokenizeline[0];
                        symtb.type[temp]="R";
                        symtb.value[temp]=LC;
                        symtb.count++;
                    }
                    else
                    {
                        symtb.type[temp]="R";
                        symtb.value[temp]=LC;
                    }
                    LC=LC+Integer.parseInt(Tokenizeline[2]);
                    System.out.print("(DS,1)\t(S,"+temp+")\t(C,"+Tokenizeline[2]+")\n");
                    output.write("(DS,1)\t(S,"+temp+")\t(C,"+Tokenizeline[2]+")\n");
                }
            }
            else
            {
                int temp = symtb.search(Tokenizeline[0]);
                if(temp == -1)
                {
                    temp=symtb.count;
                    symtb.Symbol[temp]=Tokenizeline[0];
                    symtb.type[temp]="R";
                    symtb.value[temp]=LC;
                    symtb.count++;
                }
                else
                {
                    symtb.type[temp]="R";
                    symtb.value[temp]=LC;
                }
            }
        }
        System.out.println("\n\tLiteral Table\t\nIndex\tSymbol\taddress");
        lttab.write("Index\tSymbol\taddress\n");
        for (int i = 0; i < LT.count; i++) {
            System.out.println(i+"\t"+LT.Symbol[i]+"\t"+LT.address[i]);
            lttab.write(i+"\t"+LT.Symbol[i]+"\t"+LT.address[i]+"\n");
        }
        System.out.println("\n\tPOOL Table\t\nIndex\tLiteral count");
        pttab.write("Index\tLiteral count\n");
        for (int i = 0; i < LT.count; i++) {
            System.out.println(i+"\t"+pt.literalCount[i]);
            pttab.write(i+"\t"+pt.literalCount[i]+"\n");
        }
        System.out.println("\n\tSymbol Table\t\nIndex\tSymbol\tType\tValue");
        symtab.write("Index\tSymbol\tType\tValue\n");
        for (int i = 0; i < symtb.count; i++) {
            System.out.println(i+"\t"+symtb.Symbol[i]+"\t"+symtb.type[i]+"\t"+symtb.value[i]);
            symtab.write(i+"\t"+symtb.Symbol[i]+"\t"+symtb.type[i]+"\t"+symtb.value[i]+"\n");
        }
        input.close();
        lttab.close();
        pttab.close();
        symtab.close();
        output.close();

    }
}
/*
 PASS 1 ASSEMBLER 
(AD,1)  (C,100)
(IS,4)  (L,0)   (S,1)
(AD,2)
(IS,2)  (L,1)   (S,2)
(IS,7)  (5)
(DS,0)  (S,1)   (C,104)
(AD,0)  (S,3)   (C,10)
(DS,1)  (S,2)   (C,105)
(IS,6)  (R,1)   (C,2)
(AD,3)
        Literal Table
Index   Symbol  address
0       =F'5'   101
1       =F'6'   111

        POOL Table
Index   Literal count
0       1
1       1

        Symbol Table
Index   Symbol  Type    Value
0       Loop    R       100
1       M       R       104
2       N       R       105
3       R       A       10
*/
