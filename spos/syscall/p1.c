#include<stdio.h>
#include<unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main()
{
	int op;
	int status = 80000;
	while(1)
	{
		printf("\n\n********************************Menu***************************\n");
		printf("1.fork \n2.exec \n3.wait \n4.ps \n5.joint \n6.exit\nEnter choice =>");
	
		scanf("%d",&op);
	
		switch(op)
		{
			case 1:
					if(fork()==0)
					{
						printf("This is child process pid => %d \n",getpid());
						return 0;
					}
					else
					{
						wait(&status);
						printf("This is parent process pid => %d \n",getpid());
					}
					break;
			case 2:
					if(fork()==0)
					{
						
						execv("./p2",0);
					}
					wait(&status);
					break;
					
			case 3:
					wait(&status);
					printf("After waiting for 80000msec\n");
					break;
			case 4:
					system("ps");
					break;
			case 5:
					system("join f1.txt f2.txt -a 1\n");
					break;
			case 6:
					exit(0);
					break;
			default:
					printf("Wrong choice please try again\n");
					break;
		}	
	}
	return 0;
}
/*

********************************Menu***************************
1.fork 
2.exec 
3.wait 
4.ps 
5.joint 
6.exit
Enter choice =>1
This is child process pid => 5809 
This is parent process pid => 5808 


********************************Menu***************************
1.fork 
2.exec 
3.wait 
4.ps 
5.joint 
6.exit
Enter choice =>2


This is process 2 ppid => 5808



********************************Menu***************************
1.fork 
2.exec 
3.wait 
4.ps 
5.joint 
6.exit
Enter choice =>3
After waiting for 80000msec


********************************Menu***************************
1.fork 
2.exec 
3.wait 
4.ps 
5.joint 
6.exit
Enter choice =>4
  PID TTY          TIME CMD
 5639 pts/0    00:00:00 bash
 5808 pts/0    00:00:00 p1
 5812 pts/0    00:00:00 sh
 5813 pts/0    00:00:00 ps


********************************Menu***************************
1.fork 
2.exec 
3.wait 
4.ps 
5.joint 
6.exit
Enter choice =>5
join: f1.txt: No such file or directory


********************************Menu***************************
1.fork 
2.exec 
3.wait 
4.ps 
5.joint 
6.exit
Enter choice =>6
*/


































