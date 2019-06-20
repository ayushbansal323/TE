
public class Objects {
	public static void main(String[] args) {
		Class<?> c;
		try {
			c = Class.forName("temp");
			temp t =(temp) c.newInstance();
			t.dis();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


class temp
{
	int a;
	static {
		System.out.println("inside temp static");
	}
	void dis()
	{
		System.out.println("inside temp class");
	}
}