import java.util.*;


public class Bfs {
   static HashMap<String,Integer> hm=new HashMap();
      static ArrayList<String> close=new ArrayList();
         static HashMap<String,Integer> open=new HashMap();
   static Scanner sc=new Scanner(System.in);
   static String arr[][]=new String[20][20];
   static int getVertex=0;
public static void accept(){
     
    System.out.println("enter the number of vertes");
    getVertex=sc.nextInt();
    for(int i=0;i<getVertex;i++){
     System.out.println("Enter the vertex and heursitic value");
     String ver=sc.next();
     int heu=sc.nextInt();
     hm.put(ver,heu);
     arr[i][0]=ver;
     System.out.println("Enter no of childrens");
     int child=sc.nextInt();
     for(int k=0;k<child;k++){
              System.out.println("Enter the child for "+ver);
              arr[i][k+1]=sc.next();
             }
    }
    for(Map.Entry m:hm.entrySet())
        System.out.println(m.getKey()+" "+m.getValue());
  for(int j=0;j<getVertex;j++){
      System.out.println();
    for(int k=0;k<getVertex;k++)  {
        if(arr[j][k]==null)
            continue;
        System.out.print(" "+arr[j][k]);}
   } 
 }
 
public static void bfs(){
    int i=1,j=0;
    System.out.println("Enter the starting and end node");
    String start=sc.next();
    String end=sc.next();
    close.add(start);
    while(!(start.equals(end))){
    for(int k=0;k<getVertex;k++){
     if(arr[k][0].equals(start) && arr[k][0]!=null){
      for(int h=1;h<=getVertex;h++){
        if(arr[k][h]!=null)  {
          open.put(arr[k][h],hm.get(arr[k][h]));
        }
      }
      break;
     }
    }
     start=min(); 
   }
   
}
 public static String min(){
     String start="";
     int k=0;
     int min=1000;
     for(Map.Entry m:open.entrySet()){
         System.out.print(m.getKey()+" "+m.getValue());
     if((int)m.getValue() < min ){
          min=(int)m.getValue();
          start=(String)m.getKey();}
     }
     close.add(start);
     open.remove(start);
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
