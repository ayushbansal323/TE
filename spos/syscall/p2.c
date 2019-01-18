#include<stdio.h>
#include<unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

void main()
{
	printf("\n\nThis is process 2 ppid => %d\n\n",getppid());
}
