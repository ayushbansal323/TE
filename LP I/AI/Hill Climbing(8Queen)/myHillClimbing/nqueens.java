import java.util.*;

class nqueens
{
	public static int n=32;
	public static void main(String args[])
	{
		System.out.println("n Queens");
		Board brd=new Board(new int[n],-1);
		
		for(int i=0;i<brd.b.length;i++)
		{
			brd.b[i]=(int)(Math.random()*n);
		}
		brd.display();
		
		System.out.println(brd.find_h());
		//System.out.println(brd.h);
		int attempt=0;
		while(true)
		{
			Board loc_brd=hillclimbing(brd);
			if(loc_brd.find_h()==brd.find_h())
			{
				brd.b[(int)(Math.random()*n)]=(int)(Math.random()*n);
			}
			else
			{
				for(int i=0;i<brd.b.length;i++)
				{
					brd.b[i]=loc_brd.b[i];
				}
			}
			
			System.out.println(++attempt);
			brd.display();
			System.out.println(brd.find_h());
			System.out.println();
			if(brd.find_h()==0)
			{
				break;
			}
		}
	}
	
	public static Board hillclimbing(Board brd)
	{
		Board loc_brd = new Board(new int[n],-1);
		for(int i=0;i<brd.b.length;i++)
		{
			loc_brd.b[i]=brd.b[i];
		}
		
		for(int i=0;i<brd.b.length;i++)
		{
			for(int j=0;j<brd.b.length;j++)
			{
				int temp=loc_brd.b[i];
				loc_brd.b[i]=j;
				if(brd.find_h()>loc_brd.find_h())
				{
					return loc_brd; 
				}
				loc_brd.b[i]=temp;
			}
		}
		return brd;
	}
}

class Board
{
	int[] b;
	int h;
	
	public Board(int[] b,int h)
	{
		this.b=b;
		this.h=h;	
	}
	
	public void display()
	{
		for(int i=0;i<b.length;i++)
		{
			for(int j=0;j<b.length;j++)
			{
				if(i==b[j])
				{
					System.out.print("Q ");
				}
				else
				{
					System.out.print("* ");
				}
				
			}
			System.out.println("");
		}
	}
	
	public int find_h()
	{
		h=0;
		for(int i=0;i<b.length;i++)
		{
			for(int j=0;j<i;j++)
			{
				if(b[i]==b[j])
				{
					h=h+1;
				}
				else
				{
					int offset=i-j;
					if((b[i]==b[j]-offset) || (b[i]==b[j]+offset))
					{
						h=h+1;
					}				}
				
			}
		}
		return h;
	}
}
