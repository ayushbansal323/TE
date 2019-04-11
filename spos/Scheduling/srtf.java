import java.io.*;
import java.util.*;

class process
{
	int pid;
	int at;
	int bt;
	int temp;
	int tt;
	int ct;
	int wt;
	process()
	{
		temp=pid=at=bt=tt=ct=wt=0;
	}
}
public class srtf
{
	static int allp(process p[],int t)
	{
		for(int i=0;i<t;i++)
		{
			if(p[i].temp != 0)
			{
				return 1;
			}
		}
		return 0;
	}
	static int alla(process p[],int t,int tct)
	{
		for(int i=0;i<t;i++)
		{
			if(p[i].at <= tct)
			{
				return 1;
			}
		}
		return 0;
	}
	public static void main(String s[])
	{
		System.out.println("Round Robin");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of process");
		int totalp = sc.nextInt();
		int tct=0;
		process p[]=new process[100];
		for(int i=0;i<totalp;i++)
		{
			p[i] = new process();
			System.out.println("Enter process number");
			p[i].pid = sc.nextInt();
			System.out.println("Enter arrival time");
			p[i].at = sc.nextInt();
			System.out.println("Enter burst time");
			p[i].bt = sc.nextInt();
			p[i].temp = p[i].bt ;
		}
		int i=0;
		while(allp(p,totalp)!=0)
		{
			int min=999,pmin=999;

			tct++;

			for(int j=0;j<totalp;j++)
			{
				if(p[j].at<tct &&	p[j].temp<min && p[j].temp!=0)
				{
					min=p[j].temp;
					pmin=j;
				}
			}
			for(int j=0;j<totalp;j++)
			{
				if(j==pmin)
				{
					p[j].ct++;
					p[j].temp--;
				}
				else
				{
					if(p[j].temp!=0)
					{
						p[j].ct++;
					}
				}
			}

			if(pmin<totalp)
			{
				System.out.println("min : "+pmin+" values : "+p[pmin].temp);
			}
		}
		
		for(int j=0;j<totalp;j++)
		{
			System.out.println("process number : "+p[j].pid);
			System.out.println("bt : "+p[j].bt);
			System.out.println("ct : "+p[j].ct);
			System.out.println("at : "+p[j].at);
			System.out.println("temp : "+p[j].temp);
		}
	}
};
