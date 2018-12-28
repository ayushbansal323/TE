import java.io.BufferedReader;
import java.io.InputStreamReader;

class Program
{
	int Pid;
	int AT;
	int BT;
	int CT;
	int TAT;
	int WT;
}


public class Fcfs {
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
			p[i] = new Program();
			System.out.print("Enter pid,AT,BT");
			p[i].Pid = Integer.parseInt(reader.readLine());
			p[i].AT = Integer.parseInt(reader.readLine());
			p[i].BT = Integer.parseInt(reader.readLine());
		}
		
		bubbleSort(p,n);
		
		for(int i=0;i<n;i++)
		{
			tct=max(p[i].AT,tct)+p[i].BT;
			p[i].CT = tct;
			p[i].TAT = p[i].CT-p[i].AT;
			p[i].WT = p[i].TAT-p[i].BT;
			System.out.println("Ans :"+"\npid -> "+p[i].Pid +"\nAT -> "+p[i].AT +"\nBT -> "+p[i].BT+"\nCT ->"+p[i].CT +"\nTAT ->"+p[i].TAT +"\nWT ->"+p[i].WT);
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
