import java.util.Scanner;

public class myHw {
	public static void main(String[] args) {
		System.out.println("Enter the no of emp : ");
		Scanner inp = new Scanner(System.in);
		int no= inp.nextInt();
		Emp e[] = new Emp[no];
		
		for (int i = 0; i < e.length; i++) {
			e[i]=new Emp();
			System.out.println("Enter the emp name : ");
			inp.nextLine();
			e[i].setname(inp.nextLine());
			System.out.println("Enter the emp salary : ");
			e[i].setsalary(inp.nextInt());
			System.out.println("Enter the emp id : ");
			e[i].setid(inp.nextInt());
			System.out.println("Enter the emp dep : ");
			inp.nextLine();
			e[i].setdep(inp.nextLine());
		}
		inp.nextLine();
		System.out.println("Enter the dep to see all the info : ");
		String dept=inp.nextLine();
		System.out.println("Name \tId \tSalary");
		for (int i = 0; i < e.length; i++) {
			if(e[i].getdep().equals(dept))
			{
				System.out.println(""+e[i].getname()+"\t"+e[i].getid()+"\t"+e[i].getsalary());
			}
		}
		inp.close();
	}
}

class Emp
{

	private String name;
	private int id;
	private String dep;
	private int salary;
	int getid()
	{
		return id;
	}
	int getsalary()
	{
		return salary;
	}
	String getname()
	{
		return name;
	}
	String getdep()
	{
		return dep;
	}
	void setid(int id)
	{
		this.id=id;
	}
	void setsalary(int salary)
	{
		this.salary=salary;
	}
	void setname(String name)
	{
		this.name=name;
	}
	void setdep(String dep)
	{
		this.dep=dep;
	}
}