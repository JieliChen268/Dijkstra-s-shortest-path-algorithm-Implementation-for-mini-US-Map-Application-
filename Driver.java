import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws IOException {
	
		Scanner input = new Scanner(System.in);
		String choice = " ";
		 
		 do {
			 System.out.println("\n");
			 System.out.println("-------------------------------");
			 System.out.println("Enter the test model you want to choose: \n"+ "1.toy test case \n" 
		 +  "2.USA Map test model\n" + "3.Exit\n");
			 
			   choice = input.next();
			   switch (choice) {
		     
			   case "1": 
				    System.out.println("******Toy test case model*********");
				    input6();
				    System.out.println();
					break;
			   case "2":
				   System.out.println("*******USA map model*************");
					usaMap();
					break;
			   case "3":
				    break;
			   default:
				 System.out.println("Invlaid selection, enter again!");
				
			   }
		 } while(!choice.equals("3"));
		 System.out.println("-------------------------------------");
		
		 System.out.println();
	     System.out.println("Good Luck!");
		
    }
	public static void input6 () {
		
		Map<String, Node> vertexMap = new HashMap<String, Node>();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("input62.txt"));
            String line = in.readLine();
            String[] parts = line.split(" ");
            int nodeCount = Integer.valueOf(parts[0]);
            int pathCount = Integer.valueOf(parts[1]);
            line = in.readLine();
            
            boolean inVertex = true;

            while ((line = in.readLine()) != null) {
            	  if (line.charAt(0) == '*') {
                      inVertex = false;
                      continue;
                  }
                  if (inVertex) {
            	
                	String[] parts2 = line.split(" ");
                	String name = parts2[0];
                	int x = Integer.valueOf(parts2[1]);
                    int y = Integer.valueOf(parts2[2]);
                    
                    //store the vertices
                    Node v = new Node(name,x,y);
                    vertexMap.put(name, v);
                  }else {
                	  
                    //store the edges
                    String[] parts3 = line.split(" ");
                    String vFrom = parts3[0];
                    String vTo = parts3[1];
                    
                    Node vertexFrom = vertexMap.get(vFrom);
                    Node vertexTo = vertexMap.get(vTo);
                    double weight = Math.sqrt ((vertexTo.y - vertexFrom.y) * (vertexTo.y - vertexFrom.y) 
                    		+ (vertexTo.x - vertexFrom.x) * (vertexTo.x - vertexFrom.x));
                    if (vertexFrom != null) {
                    	vertexFrom.addEdge(new Edge(vertexFrom, vertexTo, weight));
                    	vertexTo.addEdge(new Edge(vertexTo, vertexFrom, weight));
                    }
                  } 
            }
            
        } catch (IOException e) {
        	
            e.printStackTrace();
            return;
        }
        
        finally {
            if(in!= null)
                try {
                    in.close();
                } catch (IOException ignore) {
                }
        }

        //get a list of all the vertices
        Collection<Node> vertices =  vertexMap.values();
        DijkstraShortestPath p = new DijkstraShortestPath(); 

        Node source = vertices.iterator().next();
 
        
        Scanner input= new Scanner(System.in);
        System.out.println("Enter source Node id: ");
        String sid = input.next();
        Node s = vertexMap.get(sid);
        
        System.out.println("Enter target Node id: ");
        String tid = input.next();
        Node t = vertexMap.get(tid);
      
        List<Node> pa = p.computeShorestPaths(s,t);
        
        /*print out shortest path */
       System.out.println("Shorest Path From " + s.getName() +"  To  " + t.getName() + " : " );
        for (int i = 0; i < pa.size(); i++) {
        	 System.out.print( pa.get(i).getName() + " -> ");
        }
        System.out.println();
        System.out.println("The shortest distance between " + s.getName() + "  and  " + t.getName() 
        + " is : " + p.getDistance());
       
        
	}
	
	public static void usaMap () {
		
		Map<String, Node> vertexMap = new HashMap<String, Node>();
        BufferedReader in = null;
        try {
        	
            in = new BufferedReader(new FileReader("USA Map.txt"));
            String line = in.readLine();
            String[] parts = line.split(" ");
            
         
            int nodeCount = Integer.valueOf(parts[0]);
            
        
            int pathCount = Integer.valueOf(parts[1]);
            
         
            
            boolean inVertex = true;

            while ((line = in.readLine()) != null) {
            	  if (line.charAt(0) == '*') {
                      inVertex = false;
                      continue;
                  }
                  if (inVertex) {
            	
                	String[] parts2 = line.split(" ");
                	ArrayList<Integer> nodeArray1 = new ArrayList<Integer>();
                	
                	for (int i = 0; i < parts2.length; i++) {
                	
                	    if (!parts2[i].equals("")) {
                	    	
                			int value = Integer.valueOf(parts2[i]);
                		
                			nodeArray1.add(value);
                		} 
                	}
               
                	
                	String name = nodeArray1.get(0).toString();   
                	int x = nodeArray1.get(1);   
            
                	
                    int y = nodeArray1.get(2); 
               
                    
                    //store the vertices
                    Node v = new Node(name,x,y);
                    vertexMap.put(name, v);
                    
                  }  else {
                	  
                    //store the edges
                    String[] parts3 = line.split(" ");
                    ArrayList<Integer> nodeArray2 = new ArrayList<Integer>();
                	
                	for (int i = 0; i < parts3.length; i++) {
                		
                	    if (!parts3[i].equals("")) {
                	    	
                			int value = Integer.valueOf(parts3[i]);
                		
                			nodeArray2.add(value);
                		} 
                	}
                
                    String vFrom = nodeArray2.get(0).toString();
              
                    
                    String vTo =nodeArray2.get(1).toString();
               
                    
                    
                  
                    Node vertexFrom = vertexMap.get(vFrom);
                    Node vertexTo = vertexMap.get(vTo);
                    
                    double weight = Math.sqrt ((vertexTo.y - vertexFrom.y) * (vertexTo.y - vertexFrom.y) 
                    		+ (vertexTo.x - vertexFrom.x) * (vertexTo.x - vertexFrom.x));
                    
                    if (vertexFrom != null) {
                    	
                    	vertexFrom.addEdge(new Edge(vertexFrom, vertexTo, weight));
                    	vertexTo.addEdge(new Edge(vertexTo, vertexFrom, weight));
                    }
                  }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        finally {
            if(in!= null)
                try {
                    in.close();
                } catch (IOException ignore) {
                }
        }

        //get a list of all the vertices
        Collection<Node> vertices =  vertexMap.values();
        DijkstraShortestPath p = new DijkstraShortestPath(); 

        Node source = vertices.iterator().next();

        
        Scanner input= new Scanner(System.in);
        System.out.println("Enter source Node id: ");
        String sid = input.next();
        Node s = vertexMap.get(sid);
        
        System.out.println("Enter target Node id: ");
        String tid = input.next();
        Node t = vertexMap.get(tid);
      
        List<Node> pa = p.computeShorestPaths(s,t);
        
        /*print out shortest path */
       System.out.println("Shorest Path From " + s.getName() +"  To  " + t.getName() + " : " );
        for (int i = 0; i < pa.size(); i++) {
        	 System.out.print( pa.get(i).getName() + " -> ");
        }
        System.out.println();
        System.out.println("The shortest distance between " + s.getName() + "  and  " + t.getName() 
        + " is : " + t.minDistance);
	
}
}


