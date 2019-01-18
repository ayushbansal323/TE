#include<stdio.h>
#include<unistd.h>
#include <sys/types.h>
#include <sys/wait.h>



void main()
{
	int var;
	int status = 8000;
	if((var = fork())==0)
	{
	
		wait(&status);
		printf("\n\nThis is child process \nIn this case child process will always execute after parent \n\n");	
		printf("This is child process pid =>%d Its parent process pid => %d \n ",getpid(),getppid());
		
		execv("./p2",0);
	}
	else
	{
		printf("This is parent process pid =>%d Its child pid %d\nThis is parent process ppid =>%d",getpid(),var,getppid());
	}
	
}
