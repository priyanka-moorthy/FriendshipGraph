import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author priyankamoorthy
 */
public class FriendshipGraph {

    private final int vertices; //number of vertices
    private int[][] adjacency_matrix;
    private LinkedList<Integer> adjListArray[]; // used this instead of Arr[]
    String[] namelist = new String[16];
    String[] namesarray ;
    
    
    
    public FriendshipGraph(int v) 
    {
        vertices = v;
        adjacency_matrix = new int[vertices + 1][vertices + 1];
        adjListArray = new LinkedList[v]; 
        
        for(int i = 0; i < v ; i++){ 
                adjListArray[i] = new LinkedList<>(); 
            } 
        
    }
    //Adjacency matrix
    /* public void formMatrix(HashMap<String,Integer> names, List<String> A,List<String> B){
        
         int len = names.size();
         //System.out.println(len);
        
        
             for(int j= 0;j< A.size();j++){
            //     System.out.println(A.get(j)+"["+names.get(A.get(j))+"]" + "-->" +B.get(j) +"["+names.get(B.get(j))+"]"   );
             adjacency_matrix[names.get(A.get(j))][names.get(B.get(j))] = 1;
              adjacency_matrix[names.get(B.get(j))][names.get(A.get(j))] = 1;
         }
              
     }*/
     //Adjacency List
     public void formList(HashMap<String,Integer> names, List<String> A,List<String> B){
    
         
            for(int j= 0;j< A.size();j++){
         addEdge(names.get(A.get(j)),(names.get(B.get(j)))); 
            }
            
            Set<String> set = names.keySet();

		namesarray = Arrays.copyOf(set.toArray(), set.size(), 
									String[].class);
          
     }
 
     //populating the list
     void addEdge( int src, int dest) 
    { 
        // Add an edge from src to dest.  
        adjListArray[src].add(dest); 
          
        // from dest to src 
        adjListArray[dest].add(src); 
    } 
     
     
     
      
       
    /* public void Printmatrix(){
         
         for(int i =0;i<16;i++){
            
             for(int j=0;j<16;j++){
               
                 System.out.print(adjacency_matrix[i][j]);
             }
             System.out.print("\n");
         }
         
            System.out.print("----------------------------------------\n");
         for(int i=0;i<16;i++){
             System.out.print(adjListArray[i]);
         }
         System.out.print("----------------------------------------\n");

         
     }*/
     
      
     public void FindFriend(String Frnd1, String Frnd2, HashMap<String,Integer> Names){
           
        int src = Names.get(Frnd1);
        int des = Names.get(Frnd2);
        
         boolean[] isVisited = new boolean[16];
        ArrayList pathList = new ArrayList<>();
         
        //add source to path[]
        pathList.add(src);
         
        //Call recursive utility
        printAllPathsUtil(src, des, isVisited, pathList, Names);
       }


     private void printAllPathsUtil(Integer u, Integer d,
                                    boolean[] isVisited,
                            List localPathList , HashMap<String,Integer> Names) {
         
        // Mark the current node
        isVisited[u] = true;
         
        if (u.equals(d)) 
        {
           // System.out.println(localPathList);
            Integer[] arrroute =  Arrays.copyOf(localPathList.toArray(), localPathList.size(), 
									Integer[].class);
            for(int i=0;i<localPathList.size();i++){
                int x = arrroute[i];
                System.out.print(namesarray[x]);
                if(i!=localPathList.size()-1)
                   System.out.print("->"); 
                   else
                   System.out.print("\n"); 
            }
        }
         
        // Recur for all the vertices
        // adjacent to current vertex
        for (Integer i : adjListArray[u]) 
        {
            if (!isVisited[i])
            {
                // store current node 
                // in path[]
                localPathList.add(i);
                printAllPathsUtil(i, d, isVisited, localPathList, Names);
                 
                // remove current node
                // in path[]
                localPathList.remove(i);
            }
        }
         
        // Mark the current node
        isVisited[u] = false;
    }
   
 // sorted the hashmap to get the names in order to it will match with adjacency list
    public HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Integer> > list = 
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    }
     
      public static void main(String[] args) {
        // TODO code application logic here
        
     
        //tags for each name
        HashMap<String,Integer> Names =new  HashMap<String,Integer>();
        
        List<String> SetA = new ArrayList<String>();
        SetA.add("Sam");
        SetA.add("Jack"); 
        SetA.add("Martin");
        SetA.add("Joe");
        SetA.add("Bill");
        SetA.add("John");
        SetA.add("Kim");
        SetA.add("Meg");
        SetA.add("Jimmy");
        SetA.add("Joe");
        SetA.add("Kim");
        SetA.add("Roger");
        SetA.add("Alex");
    
    List<String> SetB = new ArrayList<String>();

        SetB.add("John");
        SetB.add("Joe"); 
        SetB.add("Anna");
        SetB.add("Bill");
        SetB.add("Peter");
        SetB.add("Diana");
        SetB.add("Peter");
        SetB.add("Anna");
        SetB.add("David");
        SetB.add("Anthony");
        SetB.add("Roger");
        SetB.add("Alex");
        SetB.add("Kim");
        
         int lenght = SetA.size();
         int diff =0;
        for(int i=0;i<lenght;i++){
         
            if(!Names.containsKey(SetA.get(i))){
            Names.put(SetA.get(i),i-diff);
            }
           
            else{diff++;}
       
            }
        int maplen= Names.size();
      
        
        for(int i=maplen,j=0;i<lenght*2 && j<13;i++,j++){
           
            if(!Names.containsKey(SetB.get(j))){
            Names.put(SetB.get(j),i);
            }
            else{
                i--;
            }
            
        }
        
  
        
        FriendshipGraph graph = new FriendshipGraph(Names.size());
              HashMap<String,Integer> sortednames = graph.sortByValue(Names);
             // System.out.print(sortednames);
     //  graph.formMatrix(sortednames, SetA, SetB);
       graph.formList(sortednames, SetA, SetB);
      // graph.Printmatrix();
     //  graph.printGraph(sortednames);
       Scanner s = new Scanner(System.in);
       System.out.println("Enter Names");
       String friend1 = s.nextLine();
       String friend2 = s.nextLine();
       
       graph.FindFriend(friend1, friend2, sortednames);

      

       
    } 
}
