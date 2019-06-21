import java.util.Scanner;

public class playerInfo {
	public static void main(String[] args) {
		//System.out.println("Enter the no of player : ");
		Scanner inp = new Scanner(System.in);
		//int no= inp.nextInt();
		//inp.nextLine();
		player e[] = new player[4];
		e[0]=new player("Rohit","baller","30","India");
		e[1]=new player("Dhoni","batting","60","India");
		e[2]=new player("Sachin","Allrounder","90","India");
		e[3]=new player("Virat","batting","60","India");
		//for (int i = 0; i < e.length; i++) {
		//	e[i]=new player();
		//	System.out.println("Enter the player name : ");
		//	
		//	e[i].setname(inp.nextLine());
		//	System.out.println("Enter the player skill : ");
		//	e[i].setskill(inp.nextLine());
		//	System.out.println("Enter the player team : ");
		//	e[i].setteam(inp.nextLine());
		//	System.out.println("Enter the player exp : ");
		//	e[i].setexp(inp.nextLine());
		//}
		System.out.println("I am aware with:\n1)skill \n2)name ");
		int opt=inp.nextInt();
		inp.nextLine();
		if(opt==1)
		{
			System.out.println("Enter the skill to see all the info : ");
			String skill=inp.nextLine();
			System.out.println("Name \tteam \texp");
			for (int i = 0; i < e.length; i++) {
				if(e[i].getskill().equals(skill))
				{
					System.out.println(""+e[i].getname()+"\t"+e[i].getteam()+"\t"+e[i].getexp());
				}
			}
		}
		else if (opt==2)
		{
			System.out.println("Enter the name to see all the info : ");
			String name=inp.nextLine();
			System.out.println("Skill \tteam \texp");
			for (int i = 0; i < e.length; i++) {
				if(e[i].getname().equals(name))
				{
					System.out.println(""+e[i].getskill()+"\t"+e[i].getteam()+"\t"+e[i].getexp());
					break;
				}
			}
		}
		inp.close();
	}
}

class player
{
	
	private String name;
	private String skill;
	private String exp;
	private String team;
	player(String name,
		String skill,
		String exp,
		String team)
	{
		this.name=name;
		this.skill=skill;
		this.exp=exp;
		this.team=team;
	}
		
	String getskill()
	{
		return skill;
	}
	String getexp()
	{
		return exp;
	}
	String getname()
	{
		return name;
	}
	String getteam()
	{
		return team;
	}
	void setskill(String skill)
	{
		this.skill=skill;
	}
	void setexp(String exp)
	{
		this.exp=exp;
	}
	void setname(String name)
	{
		this.name=name;
	}
	void setteam(String team)
	{
		this.team=team;
	}
}
