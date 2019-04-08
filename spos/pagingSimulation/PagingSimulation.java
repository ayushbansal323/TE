import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * PagingSimulation
 */
public class PagingSimulation {
    static void setcount(int count[],int pages[],int reference[],int psize,int start, int end)
    {
        for (int j = 0; j < psize; j++) 
        {
            count[j]=0;    
        }
        for (int i = 0; i < psize; i++) 
        {
            for (int j = start; j < end; j++) 
            {
                if(reference[j] == pages[i])
                {
                    if(count[i] == 0)
                    {
                        count[i]=j;
                    }
                }    
            }    
        }
        for (int i = 0; i < psize; i++) 
        {
            if(count[i] == 0)
            {
                count[i]=999999;
            }    
        }
    }
    static int max(int count[],int psize)
    {
        int maximum=0;
        int maxp=0;
        for (int i = 0; i < psize; i++) {
            if(count[i]>maximum)
            {
                maximum=count[i];
                maxp=i;
            }
        }

        return maxp;
    }

    static int check(int ref,int pages[],int psize)
    {
        for (int i = 0; i < psize; i++) 
        {
            if(pages[i] == ref)
            {
                return i ;
            }    
        }
        return -1;
    }
    static void FIFO(int psize, int rcount,int reference[])
    {
        System.out.println("**************** FIFO *****************");
        int pages[] = new int[psize];
        int count = 0;
        int pagefault=0;
        for(int i=0;i<rcount ; i++)
        {
            if(check(reference[i],pages,psize) == -1)
            {
                pages[count%psize]=reference[i];
                pagefault++;
                //System.out.println("count : "+count+"\tcount%psize :"+count%psize);
                count++;
            }

            System.out.print(""+reference[i]+ " \t | " );
            for (int j = 0; j < psize; j++) 
            {
                System.out.print(""+pages[j]+"\t|");    
            }
            System.out.println("");
        }

        System.out.println("Page Fault : "+pagefault+"\tPagehit :" + (rcount-pagefault));
        
    }

    static void LRU(int psize, int rcount,int reference[])
    {
        System.out.println("**************** LRU *****************");
        int pages[] = new int[psize];
        int count[] = new int[psize];
        int pagefault=0;
        for(int i=0;i<rcount ; i++)
        {
            int position=check(reference[i],pages,psize);
            if( position == -1)
            {
                int maximum = max(count,psize);
                pages[maximum]=reference[i];
                pagefault++;
                for (int j = 0; j < psize; j++) 
                {
                    count[j]++;    
                }
                count[maximum]=0;
                //System.out.println("count : "+count+"\tcount%psize :"+count%psize);
                
            }
            else
            {
                for (int j = 0; j < psize; j++) 
                {
                    count[j]++;    
                }
                count[position]=0;
            }

            System.out.print(""+reference[i]+ " \t | " );
            for (int j = 0; j < psize; j++) 
            {
                System.out.print(""+pages[j]+"\t|");    
            }
            System.out.println("");
        }
        System.out.println("Page Fault : "+pagefault+"\tPagehit :" + (rcount-pagefault));
    }
    static void OPTIMAL(int psize, int rcount,int reference[])
    {
        System.out.println("**************** OPTIMAL *****************");
        int pages[] = new int[psize];
        int count[] = new int[psize];
        int pagefault=0;
        for(int i=0;i<rcount ; i++)
        {
            int position=check(reference[i],pages,psize);
            if( position == -1)
            {
                
                setcount(count,pages,reference,psize,i+1,rcount);
                /*for (int j = 0; j < psize; j++) 
                {
                    System.out.println("count["+j+"]\t:"+count[j]);    
                }*/
                int maximum = max(count,psize);
                pages[maximum]=reference[i];
                pagefault++;
                //System.out.println("count : "+count+"\tcount%psize :"+count%psize);
                
            }

            System.out.print(""+reference[i]+ " \t | " );
            for (int j = 0; j < psize; j++) 
            {
                System.out.print(""+pages[j]+"\t|");    
            }
            System.out.println("");
        }

        System.out.println("Page Fault : "+pagefault+"\tPagehit :" + (rcount-pagefault));
        
    }

    public static void main(String[] args) {
        System.out.println("Paging Simulation");
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter page size");
        int psize = sc.nextInt();

        System.out.println("Enter Number of page reference ");
        int rcount = sc.nextInt();
        
        int reference[]=new int[rcount];
        for(int i = 0 ; i < rcount ; i++ )
        {
            reference[i] = sc.nextInt();
        }

        FIFO(psize,rcount,reference);
        LRU(psize,rcount,reference);
        OPTIMAL(psize,rcount,reference);

    }
}