#include<stdio.h>

int main()
{
    int status = 1;
    system("join f1.txt f2.txt -a 1");
    if(fork()==0)
    {
        sleep(1);
        printf("chaild %d",getpid());
    }
    else
    {
        int pid = wait(&status);
        printf("status  %d of %d",status,pid);
    }
    

}