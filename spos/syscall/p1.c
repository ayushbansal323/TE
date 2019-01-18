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
		printf("********************************Menu***************************\n");
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
						printf("This is parent process pid => %d \n",getpid());
					}
					break;
			case 2:
					if(fork==0)
					{
						execv("./p2",0);
					}
					break;
					wait(&status);
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



































