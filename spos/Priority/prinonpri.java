package scheduling;
import java.io.BufferedReader;
import java.io.InputStreamReader;





public class prinonpri {
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
			System.out.print("Enter pid,AT,BT,Pri");
			p[i].Pid = Integer.parseInt(reader.readLine());
			p[i].AT = Integer.parseInt(reader.readLine());
			p[i].BT = Integer.parseInt(reader.readLine());
			p[i].Pri = Integer.parseInt(reader.readLine());
		}
		
		bubbleSort(p,n);
		
		
		for (int j=0;j<n;j++)
		{
			Program temp = p[j];
			for(int i=0;i<n;i++)
			{
				if(p[i].AT<tct)
				{
					if(p[i].Pri < temp.Pri)
					{
						temp=p[i];
					}
				}
			}
		
			if(temp.BT != 0)
			{
				tct=max(temp.AT,tct)+temp.BT;
				temp.CT = tct;
				temp.TAT = temp.CT-temp.AT;
				temp.WT = temp.TAT-temp.BT;
				System.out.println("Ans :"+"\npid -> "+temp.Pid +"\nAT -> "+temp.AT +"\nBT -> "+temp.BT+"\nCT ->"+temp.CT +"\nTAT ->"+temp.TAT +"\nWT ->"+temp.WT+"\nPri ->"+temp.Pri);
				temp.BT = 0;
			}
			
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
					
					temp = p[j].Pri;
					p[j].Pri=p[j+1].Pri;
					p[j+1].Pri=temp;
				}
			}
		}
	}
	
	
}
