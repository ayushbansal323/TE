import java.io.*;
import java.io.Reader;
import java.lang.*;
import java.util.Scanner;

public class myba{

    static int check(int i,int need[][] , int available[],int rnum)
    {
        for(int j=0;j<rnum;j++)
        {
            //System.out.println(""+j);
            if(need[i][j]>available[j])
            {
                return 0;
            }
        }
        return 1;
    }
    public static void main(String[] args) {
        System.out.println("Bankers Algo");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of processes ");
        int pnum=0;
        pnum = sc.nextInt();
        System.out.println("Enter number of resources ");
        int rnum = sc.nextInt();  
        int allocated[][] = new int[pnum][rnum];
        int available[] = new int[rnum];
        int need[][] = new int[pnum][rnum];
        int max[][] = new int[pnum][rnum];
        int pallocated[] = new int[pnum];
        //Allocated matrix
        System.out.println("Enter Allocated matrix ");
        for(int i=0;i<pnum;i++)
        {
            System.out.println("\tP"+i+" : ");
            for (int j=0 ; j<rnum ; j++) 
            {
                allocated[i][j] = sc.nextInt();    
            }
        }

        
        System.out.println("Allocated matrix ");
        for(int i=0;i<pnum;i++)
        {
            System.out.print("\tP"+i+" | ");
            for (int j=0 ; j<rnum ; j++) 
            {
                System.out.print("\t"+allocated[i][j]+" | ");    
            }
            System.out.println("");
        }
        //max matrix
        System.out.println("Enter max matrix ");
        for(int i=0;i<pnum;i++)
        {
            System.out.println("\tP"+i+" : ");
            for (int j=0 ; j<rnum ; j++) 
            {
                max[i][j] = sc.nextInt();    
            }
        }

        
        System.out.println("max matrix ");
        for(int i=0;i<pnum;i++)
        {
            System.out.print("\tP"+i+" | ");
            for (int j=0 ; j<rnum ; j++) 
            {
                System.out.print("\t"+max[i][j]+" | ");    
            }
            System.out.println("");
        }
        //availabe matrix
        System.out.println("Enter availabe matrix ");

        for(int i=0;i<rnum;i++)
        {
            available[i] = sc.nextInt();   
        }

        System.out.print("Availabe matrix :[\t");
        for(int i=0;i<rnum;i++)
        {
           System.out.print(""+available[i]+"\t");   
        }
        System.out.println("]");

        for(int i=0;i<pnum;i++)
        {
            for (int j=0 ; j<rnum ; j++) 
            {
                need[i][j] = max[i][j]-allocated[i][j];    
            }
        }

        System.out.println("\t need matrix");
        for(int i=0;i<pnum;i++)
        {
            System.out.print("\tP"+i+" | ");
            for (int j=0 ; j<rnum ; j++) 
            {
                System.out.print("\t"+need[i][j]+" | ");    
            }
            System.out.println("");
        }

        int process = 1;

        while(process != 0)
        {
            process = 0;
            for(int i=0 ; i < pnum ;i++)
            {
                //System.out.println("Process P"+i+" allocated");
                if((check(i,need , available,rnum) == 1) && (pallocated[i] != 1) )
                {
                    for(int j=0 ; j < rnum ;j++)
                    {
                        available[j] = available[j] + allocated[i][j];
                    }
                    System.out.println("Process P"+i+" allocated");
                    pallocated[i]=1;
                    process = 1;
                }
            }
        }

        int flag =0;
        if(process == 0)
        {
            for(int i=0;i<pnum;i++)
            {
                if(pallocated[i] == 0)
                {
                    flag=1;
                }
            }
            if(flag == 1)
            {
                System.out.println("All process not allocated safely");
            }
            else
            {
                System.out.println("All process allocated safely");
            }
        }
    }
}