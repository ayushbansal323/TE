#include<iostream>
#include<omp.h>
using namespace std;
int average(int avg,int sum)
{

 avg=avg+sum;
 return avg;
}
int main()
{
//sum
int sum=0,i,Max=1000;
#pragma omp parallel reduction(+:sum)
for(i=0;i<Max;i++)
{
  
  sum+=i;
  //cout<<sum<<endl;
}
cout<<"Sum is "<<sum<<endl;
//max
    double arr[10];
    double max_val=0.0;
    for( i=0; i<10; i++)
    {
        arr[i] = 2.0 + i;
        cout<<arr[i]<<endl;
    }
    #pragma omp parallel for reduction(max : max_val)
    for( i=0;i<10; i++)
    {
        //cout<<"thread id ="+omp_get_thread_num()+" and i = "+i<<endl;
        if(arr[i] > max_val)
        {
            max_val = arr[i];   
        }
    }
   
    cout<<"Max Value is "<<max_val<<endl;
//min
    double arr1[10];
    for( i=0; i<10; i++)
    {
        arr1[i] = 2.0 + i;
        cout<<arr1[i]<<endl;
    }
    double min_val=arr1[i-1];
    #pragma omp parallel for reduction(min : min_val)
    for( i=0;i<10; i++)
    {
        //cout<<"thread id ="+omp_get_thread_num()+" and i = "+i<<endl;
        if(arr1[i] < min_val)
        {
            min_val = arr1[i];   
        }
    }
   
    cout<<"Min Value is "<<min_val<<endl;
//avg
int avg_val=0;
#pragma omp declare reduction(avg:int:omp_out=average(omp_out,omp_in))
#pragma omp parallel reduction(avg:avg_val)
for(i=0;i<10;i++)
{
  avg_val=average(avg_val,arr[i]);
} 
avg_val=avg_val/10;
cout<<"Average is "<<avg_val;
return 0;
}
