#include<iostream>
#include<omp.h>
using namespace std;

class bfs{
int a[10][10],ver,e1,e2,arr[10],front,rear;
public:
 bfs(){ for(int i=0;i<10;i++)
          for(int j=0;j<10;j++)
            a[i][j]=0;

    front=0;
    rear=0;
  }
 void getMatrix();
 void disp();
 void bfsMethod();
};

void bfs::getMatrix(){
char ans;
cout<<"\nEnter the vertex";
cin>>ver;
do{
 cout<<"\nEnter edge";
 cin>>e1>>e2;
 if(e1<=ver && e2<=ver)
 { 
   a[e1][e2]=1;
   a[e2][e1]=1;
 }
 else
  cout<<"\nInvalid edges ";
 cout<<"\n continue(y/n)";
 cin>>ans;
 }while(ans=='y'); 

}

void bfs::disp(){
  for(int i=1;i<=ver;i++){
     cout<<"\n";
    for(int j=1;j<=ver;j++)
     cout<<" "<<a[i][j];}
}

void bfs::bfsMethod(){
int v,visit[10]={0};
cout<<"\n Enter the start vertex";
cin>>v;
arr[rear++]=v;
visit[v]=1;
do{
 #pragma omp parallel
 {
 for(int i=1;i<=ver ;i++){
  if(a[v][i]==1 && visit[i]==0){
   arr[rear++]=i;
   visit[i]=1;
  }
 } 
 }
 v=arr[front++];
}while(rear<ver);

cout<<"\n Matrix is";
for(int i=0;i<rear;i++)
 cout<<" "<<arr[i];  

}

int main(){
bfs b;
b.getMatrix();
b.disp();
b.bfsMethod();
return 0;
}
