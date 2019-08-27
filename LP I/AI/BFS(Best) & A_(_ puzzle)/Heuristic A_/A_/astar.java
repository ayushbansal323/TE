

import java.util.*;


public class astar {
   static HashMap<String,Integer> hm=new HashMap();
      static ArrayList<String> close=new ArrayList();
         static HashMap<String,Integer> open=new HashMap();
         static int mn=0;
   static Scanner sc=new Scanner(System.in);
   static String arr[][]=new String[20][20];
   static int getVertex=0;
public static void accept(){
     
    System.out.println("enter the number of vertices");
    getVertex=sc.nextInt();
    for(int i=0;i<getVertex;i++){
     System.out.println("Enter the vertex and heursitic value");
     String ver=sc.next();
     int heu=sc.nextInt();
     hm.put(ver,heu);
     arr[i][0]=ver;
     System.out.println("Enter no of children");
     int child=sc.nextInt();
     for(int k=1;k<=child*2;k++){
              System.out.println("Enter the child for "+ver);
              arr[i][k++]=sc.next();
              System.out.println("Enter the value for "+ver);
              arr[i][k]=sc.next();
             }
    }
    for(Map.Entry m:hm.entrySet())
        System.out.println(m.getKey()+" "+m.getValue());
  for(int j=0;j<getVertex;j++){
      System.out.println();
    for(int k=0;k<getVertex*2;k++)  {
        if(arr[j][k]==null)
            continue;
        System.out.print(" "+arr[j][k]);}
   } 
 }
 
public static void bfs(){
    int g=0,f=0,flag=0;
    System.out.println("Enter the starting and end node");
    String start=sc.next();
    String end=sc.next();
    f=g+hm.get(start);
    close.add(start);
   // open.put(start,f);
    while(!(start.equals(end))){
    for(int k=0;k<getVertex;k++){
         //System.out.println("outer loop "+k);
     if(arr[k][0].equals(start) && arr[k][0]!=null){
      for(int h=1;h<=getVertex*2;h=h+2){
        if(arr[k][h]!=null) {
            g=mn+Integer.parseInt(arr[k][h+1]);
             flag=1;
           System.out.println(g);
            f=g+hm.get(arr[k][h]);
             System.out.println(f);
          open.put(arr[k][h],f);
       }
      }
      break;
     }
    }
    if(flag==0){
    System.out.println("The "+start+" node has no child ...Plz reenter");
    break;
    }
     start=min(start); 
    
   }
   
}
 public static String min(String ma){
     String start="";
     int min=1000;
     for(Map.Entry m:open.entrySet()){
         System.out.print(m.getKey()+" "+m.getValue());
        
     if((int)m.getValue() < min ){
          min=(int)m.getValue();
          start=(String)m.getKey();}
     }
       open.clear();
       
     for(int i=0;i<getVertex;i++){
      if(arr[i][0]!=null && arr[i][0].equals(ma)){
       for(int j=1;j<=getVertex*2;j=j+2){
        if( arr[i][j]!=null && arr[i][j].equals(start)){
           mn=mn+Integer.parseInt(arr[i][j+1]);}
       }
      } 
     }
        System.out.println();
           System.out.println("mn is"+mn);
     close.add(start);
     //open.remove(start);
     System.out.println();
      System.out.println("min and start"+min+start);
     System.out.println("Open after removing");
     System.out.println();
     for(Map.Entry m:open.entrySet())
         System.out.print(m.getKey()+" "+m.getValue());
      System.out.println("closed after removing");
       System.out.print(close);
     return start;    
 }
  
    public static void main(String[] args) {    
    accept();  
    bfs();  
     System.out.println();
       System.out.print(close);
    }
   
}
