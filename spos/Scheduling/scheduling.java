import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Program
{
	int Pid;
	int AT;
	int BT;
	int CT;
	int TAT;
	int WT;
}


class Fcfs {
	public static void main(String str[]) throws Exception
	{
		System.out.println("This is Fcfs program ");
		BufferedReader reader =  
                new BufferedReader(new InputStreamReader(System.in)); 
		
		int n = 3;
		int tct = 0;
		Program p[] = new Program[100];
		for(int i=0;i<n;i++)
		{
			Random rand = new Random(); 
			p[i] = new Program();
			p[i].Pid = i;
			p[i].AT = rand.nextInt(10);
			p[i].BT = rand.nextInt(10);
		}
		
		bubbleSort(p,n);
		
		for(int i=0;i<n;i++)
		{
			tct=max(p[i].AT,tct)+p[i].BT;
			p[i].CT = tct;
			p[i].TAT = p[i].CT-p[i].AT;
			p[i].WT = p[i].TAT-p[i].BT;
			System.out.println("Ans :"+"\tpid -> "+p[i].Pid +"\tAT -> "+p[i].AT +"\tBT -> "+p[i].BT+"\tCT ->"+p[i].CT +"\tTAT ->"+p[i].TAT +"\tWT ->"+p[i].WT);
		}
	}
	public static int max(int a , int b)
	{
		if(a<b)
		{
			return b;
		}
		else
		{
			return a;
		}
		
	}
	
	public static void bubbleSort(Program p[],int n)
	{
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n-1;j++)
			{
				if(p[j].AT>p[j+1].AT)
				{
					int temp;
					
					temp = p[j].AT;
					p[j].AT=p[j+1].AT;
					p[j+1].AT=temp;
					
					temp = p[j].BT;
					p[j].BT=p[j+1].BT;
					p[j+1].BT=temp;
					
					temp = p[j].Pid;
					p[j].Pid=p[j+1].Pid;
					p[j+1].Pid=temp;
				}
			}
		}
	}
}


// Java program to implement Shortest Remaining 
// Time First 
  
class Processs 
{ 
    int pid; // Process ID 
    int bt; // Burst Time 
    int art; // Arrival Time 
      
    public Processs(int pid, int bt, int art) 
    { 
        this.pid = pid; 
        this.bt = bt; 
        this.art = art; 
    } 
} 
  
class GFG  
{ 
    // Method to find the waiting time for all 
    // processes 
    static void findWaitingTime(Processs proc[], int n, 
                                     int wt[]) 
    { 
        int rt[] = new int[n]; 
       
        // Copy the burst time into rt[] 
        for (int i = 0; i < n; i++) 
            rt[i] = proc[i].bt; 
       
        int complete = 0, t = 0, minm = Integer.MAX_VALUE; 
        int shortest = 0, finish_time; 
        boolean check = false; 
       
        // Process until all processes gets 
        // completed 
        while (complete != n) { 
       
            // Find process with minimum 
            // remaining time among the 
            // processes that arrives till the 
            // current time` 
            for (int j = 0; j < n; j++)  
            { 
                if ((proc[j].art <= t) && 
                  (rt[j] < minm) && rt[j] > 0) { 
                    minm = rt[j]; 
                    shortest = j; 
                    check = true; 
                } 
            } 
       
            if (check == false) { 
                t++; 
                continue; 
            } 
       
            // Reduce remaining time by one 
            rt[shortest]--; 
       
            // Update minimum 
            minm = rt[shortest]; 
            if (minm == 0) 
                minm = Integer.MAX_VALUE; 
       
            // If a process gets completely 
            // executed 
            if (rt[shortest] == 0) { 
       
                // Increment complete 
                complete++; 
                check = false; 
       
                // Find finish time of current 
                // process 
                finish_time = t + 1; 
       
                // Calculate waiting time 
                wt[shortest] = finish_time - 
                             proc[shortest].bt - 
                             proc[shortest].art; 
       
                if (wt[shortest] < 0) 
                    wt[shortest] = 0; 
            } 
            // Increment time 
            t++; 
        } 
    } 
       
    // Method to calculate turn around time 
    static void findTurnAroundTime(Processs proc[], int n, 
                            int wt[], int tat[]) 
    { 
        // calculating turnaround time by adding 
        // bt[i] + wt[i] 
        for (int i = 0; i < n; i++) 
            tat[i] = proc[i].bt + wt[i]; 
    } 
       
    // Method to calculate average time 
    static void findavgTime(Processs proc[], int n) 
    { 
        int wt[] = new int[n], tat[] = new int[n]; 
        int  total_wt = 0, total_tat = 0; 
       
        // Function to find waiting time of all 
        // processes 
        findWaitingTime(proc, n, wt); 
       
        // Function to find turn around time for 
        // all processes 
        findTurnAroundTime(proc, n, wt, tat); 
       
        // Display processes along with all 
        // details 
        System.out.println("Processes " + 
                           " Burst time " + 
                           " Waiting time " + 
                           " Turn around time"); 
       
        // Calculate total waiting time and 
        // total turnaround time 
        for (int i = 0; i < n; i++) { 
            total_wt = total_wt + wt[i]; 
            total_tat = total_tat + tat[i]; 
            System.out.println(" " + proc[i].pid + "\t\t"
                             + proc[i].bt + "\t\t " + wt[i] 
                             + "\t\t" + tat[i]); 
        } 
       
        System.out.println("Average waiting time = " + 
                          (float)total_wt / (float)n); 
        System.out.println("Average turn around time = " + 
                           (float)total_tat / (float)n); 
    } 
      
    // Driver Method 
    public static void main(String[] args) 
    { 
         Processs proc[] = { new Processs(1, 6, 1),  
                            new Processs(2, 8, 1), 
                            new Processs(3, 7, 2),  
                            new Processs(4, 3, 3)}; 
          
         findavgTime(proc, proc.length); 
    } 
} 

// Java program for implementation of RR scheduling 

class RR
{ 
	// Method to find the waiting time for all 
	// processes 
	static void findWaitingTime(int processes[], int n, 
				int bt[], int wt[], int quantum) 
	{ 
		// Make a copy of burst times bt[] to store remaining 
		// burst times. 
		int rem_bt[] = new int[n]; 
		for (int i = 0 ; i < n ; i++) 
			rem_bt[i] = bt[i]; 
	
		int t = 0; // Current time 
	
		// Keep traversing processes in round robin manner 
		// until all of them are not done. 
		while(true) 
		{ 
			boolean done = true; 
	
			// Traverse all processes one by one repeatedly 
			for (int i = 0 ; i < n; i++) 
			{ 
				// If burst time of a process is greater than 0 
				// then only need to process further 
				if (rem_bt[i] > 0) 
				{ 
					done = false; // There is a pending process 
	
					if (rem_bt[i] > quantum) 
					{ 
						// Increase the value of t i.e. shows 
						// how much time a process has been processed 
						t += quantum; 
	
						// Decrease the burst_time of current process 
						// by quantum 
						rem_bt[i] -= quantum; 
					} 
	
					// If burst time is smaller than or equal to 
					// quantum. Last cycle for this process 
					else
					{ 
						// Increase the value of t i.e. shows 
						// how much time a process has been processed 
						t = t + rem_bt[i]; 
	
						// Waiting time is current time minus time 
						// used by this process 
						wt[i] = t - bt[i]; 
	
						// As the process gets fully executed 
						// make its remaining burst time = 0 
						rem_bt[i] = 0; 
					} 
				} 
			} 
	
			// If all processes are done 
			if (done == true) 
			break; 
		} 
	} 
	
	// Method to calculate turn around time 
	static void findTurnAroundTime(int processes[], int n, 
							int bt[], int wt[], int tat[]) 
	{ 
		// calculating turnaround time by adding 
		// bt[i] + wt[i] 
		for (int i = 0; i < n ; i++) 
			tat[i] = bt[i] + wt[i]; 
	} 
	
	// Method to calculate average time 
	static void findavgTime(int processes[], int n, int bt[], 
										int quantum) 
	{ 
		int wt[] = new int[n], tat[] = new int[n]; 
		int total_wt = 0, total_tat = 0; 
	
		// Function to find waiting time of all processes 
		findWaitingTime(processes, n, bt, wt, quantum); 
	
		// Function to find turn around time for all processes 
		findTurnAroundTime(processes, n, bt, wt, tat); 
	
		// Display processes along with all details 
		System.out.println("Processes " + " Burst time " + 
					" Waiting time " + " Turn around time"); 
	
		// Calculate total waiting time and total turn 
		// around time 
		for (int i=0; i<n; i++) 
		{ 
			total_wt = total_wt + wt[i]; 
			total_tat = total_tat + tat[i]; 
			System.out.println(" " + (i+1) + "\t\t" + bt[i] +"\t " + 
							wt[i] +"\t\t " + tat[i]); 
		} 
	
		System.out.println("Average waiting time = " + 
						(float)total_wt / (float)n); 
		System.out.println("Average turn around time = " + 
						(float)total_tat / (float)n); 
	} 
	
	// Driver Method 
	public static void main(String[] args) 
	{ 
		// process id's 
		int processes[] = { 1, 2, 3}; 
		int n = processes.length; 
	
		// Burst time of all processes 
		int burst_time[] = {10, 5, 8}; 
	
		// Time quantum 
		int quantum = 2; 
		findavgTime(processes, n, burst_time, quantum); 
	} 
} 
//Priority Scheduling java
class priority{
        
    public static void main(String args[]) {
            Scanner s = new Scanner(System.in);
 
            int x,n,p[],pp[],bt[],w[],t[],awt,atat,i;
 
            p = new int[10];
            pp = new int[10];
            bt = new int[10];
            w = new int[10];
            t = new int[10];
 
   //n is number of process
   //p is process
   //pp is process priority
   //bt is process burst time
   //w is wait time
   // t is turnaround time
   //awt is average waiting time
   //atat is average turnaround time
 
   Random rand = new Random();
   n = 4;
 
   for(i=0;i<n;i++)
    {
       
      bt[i] = rand.nextInt(10);
      pp[i] = rand.nextInt(10);
      p[i]=i+1;
    }
 
//sorting on the basis of priority
  for(i=0;i<n-1;i++)
   {
     for(int j=i+1;j<n;j++)
     {
       if(pp[i]>pp[j])
       {
     x=pp[i];
     pp[i]=pp[j];
     pp[j]=x;
     x=bt[i];
     bt[i]=bt[j];
     bt[j]=x;
     x=p[i];
     p[i]=p[j];
     p[j]=x;
      }
   }
}
w[0]=0;
awt=0;
t[0]=bt[0];
atat=t[0];
for(i=1;i<n;i++)
 {
   w[i]=t[i-1];
   awt+=w[i];
   t[i]=w[i]+bt[i];
   atat+=t[i];
 }
 
//Displaying the process
 
  System.out.print("\n\nProcess \t Burst Time \t Wait Time \t Turn Around Time   Priority \n");
for(i=0;i<n;i++)
  System.out.print("\n   "+p[i]+"\t\t   "+bt[i]+"\t\t     "+w[i]+"\t\t     "+t[i]+"\t\t     "+pp[i]+"\n");
awt/=n;
atat/=n;
  System.out.print("\n Average Wait Time : "+awt);
  System.out.print("\n Average Turn Around Time : "+atat);
 
        }
}
public class scheduling
{
	public static void main(String[] args) throws Exception
	{
		while(true)
		{
			System.out.println("****************************MENU**********************************");
			System.out.print("1.FCFS \n2.SJF \n3.RR \n4.Priority \n5.Exit\nEnter your choice ->");
			BufferedReader reader =  
    	    new BufferedReader(new InputStreamReader(System.in)); 
    	    int iChoice = Integer.parseInt(reader.readLine());
    	    switch(iChoice)
   		     {
    	    	case 1: Fcfs fcfs = new Fcfs();
    	    			fcfs.main(args); 
    	    			break;
    	    	case 2: GFG  gfg = new GFG ();
    	    			gfg.main(args); 
    		    		break;
 		      	case 3:RR rr= new RR();
 		       			rr.main(args); 
 			       		break;
 		      	case 4:priority p= new priority();
 		       			p.main(args); 
 			       		break;
 		       	case 5: System.exit(0); 
 		       		break;
 		       	default: System.out.println("Wrong option");
        			break;
        	}
        }
	}
}

/*
ubuntu@PL-LAB:~/TEA02/spos/Scheduling$ javac scheduling.java 
ubuntu@PL-LAB:~/TEA02/spos/Scheduling$ java scheduling 
****************************MENU**********************************
1.FCFS 
2.SJF 
3.RR 
4.Priority 
5.Exit
Enter your choice ->1
This is Fcfs program 
Ans :	pid -> 0	AT -> 1	BT -> 6	CT ->7	TAT ->6	WT ->0
Ans :	pid -> 2	AT -> 4	BT -> 0	CT ->7	TAT ->3	WT ->3
Ans :	pid -> 1	AT -> 8	BT -> 9	CT ->17	TAT ->9	WT ->0
****************************MENU**********************************
1.FCFS 
2.SJF 
3.RR 
4.Priority 
5.Exit
Enter your choice ->2
Processes  Burst time  Waiting time  Turn around time
 1		6		 3		9
 2		8		 16		24
 3		7		 8		15
 4		3		 0		3
Average waiting time = 6.75
Average turn around time = 12.75
****************************MENU**********************************
1.FCFS 
2.SJF 
3.RR 
4.Priority 
5.Exit
Enter your choice ->3
Processes  Burst time  Waiting time  Turn around time
 1		10	 13		 23
 2		5	 10		 15
 3		8	 13		 21
Average waiting time = 12.0
Average turn around time = 19.666666
****************************MENU**********************************
1.FCFS 
2.SJF 
3.RR 
4.Priority 
5.Exit
Enter your choice ->4


Process 	 Burst Time 	 Wait Time 	 Turn Around Time   Priority 

   4		   0		     0		     0		     0

   3		   2		     0		     2		     1

   2		   3		     2		     5		     4

   1		   4		     5		     9		     6

 Average Wait Time : 1
 Average Turn Around Time : 4****************************MENU**********************************
1.FCFS 
2.SJF 
3.RR 
4.Priority 
5.Exit
Enter your choice ->5
*/

