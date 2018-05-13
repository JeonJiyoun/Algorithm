package dij;

import java.util.*;

class Vertex{
	int key;
	String name;
	Vertex p;
	ArrayList<Vertex>adj=new ArrayList<Vertex>();
	public Vertex(String name) {
		this.name=name;
		this.p=null;
	}
	public void input(int key) {
		this.key=key;}
}
class Graph{
	Vertex[] v;
	int maps[][];
	
	public Graph(Vertex[] str) {
	this.v=str;
	maps=new int[v.length+1][v.length+1];
	}
	public void input(String v1, String v2, int w) {
		maps[save(v1)][save(v2)]=w;
	} 
	public int save(String ver) {
		int i;
	  for(i=1;i<v.length;i++) {
		  if(v[i].name.equals(ver)) return i;
	  }	
		return -1;
	}
	public void add_adj(Vertex x) {
		x.adj.add(new Vertex("trash"));
		for(int i=1;i<v.length;i++) {
		if(maps[save(x.name)][i]>0)
			x.adj.add(v[i]);
			
		}
		sort(save(x.name),x.adj);
	}
	public void sort(int index,ArrayList<Vertex> ad) {
		for(int i=1;i<ad.size();i++) {
			for(int j=1;j<ad.size()-1;j++) {
				
				if(maps[index][save(ad.get(j).name)]>maps[index][save(ad.get(j+1).name)]) {
						Vertex temp=ad.get(j);
					    ad.set(j,ad.get(j+1));
					    ad.set(j+1,temp);								
				}
				else if(maps[index][save(ad.get(j).name)]==maps[index][save(ad.get(j+1).name)]) {
				if(ad.get(j).name.charAt(0)>ad.get(j+1).name.charAt(0)) {
					Vertex temp=ad.get(j);
				    ad.set(j,ad.get(j+1));
				    ad.set(j+1,temp);
				}
			}
		}
	}
}}
public class Main {
	static int numv;
	static Graph g;
		 //static ArrayList<String>V=new ArrayList<String>(); 
		   public static void main(String args[]){
		       
			   String vertex[];
			   String[] temp=new String[3];
		       //Receiving Input   
		       Scanner scan = new Scanner(System.in);
		      // Scanner in = new Scanner(System.in);
		      // Scanner wi=new Scanner(System.in);
		       String str=scan.next();
		       vertex = str.split(",");
		       numv=vertex.length+1;
		       Vertex[] V=new Vertex[vertex.length+1];
		       for(int i=0;i<vertex.length;i++) {
		    	   V[i+1]=new Vertex(vertex[i]);
		       }
		       g= new Graph(V);
		      
		       int edge=scan.nextInt();
		      String weight[]= new String[edge];
		       for(int i=0;i<edge;i++) {
		      // while(wi.hasNextLine()) {
		    	  weight[i]=scan.next();
		    	   //temp=weight.split(",");
		    	   //g.input(temp[0],temp[1],Integer.parseInt(temp[2]));
		    	   
		       } 
		       for(int i=0;i<edge;i++) {
	
				    	   temp=weight[i].split(",");
				    	   g.input(temp[0],temp[1],Integer.parseInt(temp[2]));
				    	   
				       } 
		       for(int i=1;i<numv;i++) {
		    	   
		    	   g.add_adj(g.v[i]);
		       }
		   // System.out.println(g.v[3].name+g.maps[2][3]);
		  //  System.out.println(g.v[2].adj.size());
		   /* for(int i=1;i<g.v[2].adj.size();i++) {
		    	System.out.println(g.v[2].adj.get(i).name);
		    }*/
		      // System.out.println(g.v[1].adj.get(3).name); 
		       dijk(g,g.v[1]);
		       for(int i=1;i<numv;i++) {
		       System.out.println(g.v[i].key);
		       }	
	}
		   public static void dijk(Graph g,Vertex s) {
		    	initial(g,s);
		    	ArrayList<Vertex>Q=new ArrayList<Vertex>();
		    	Q.add(new Vertex("trash"));
		    	for(int i=1;i<numv;i++) {
		    		Q.add(g.v[i]);
		    	}
		    	
		    	//for(int i=1;i<Q.size();i++) {System.out.println(Q.get(i).name);}
		    	for(int j=1;j<numv;j++) {
		    		Vertex u=extract_min(Q);
		    		//Vertex u=Q.get(1);
		    		//System.out.println("start"+" "+u.name);		    		
		    		//System.out.println(Q.get(5).name);
		    		for(int i=1;i<u.adj.size();i++) {
		    			//System.out.println(u.adj.get(i).name);
		    			relax(Q,u,u.adj.get(i),g.maps);
		    		}
		    		//
		    		//extract_min(Q);
		    		//System.out.println("queue");
		    		//for(int i=1;i<Q.size();i++) {System.out.println(Q.get(i).name+Q.get(i).key);}
		    		//System.out.println("한타임끝");
		    	}
		    	
		    }
		   public static int findindex(String vv,ArrayList<Vertex> Q) {
			   for (int i=1;i<Q.size();i++) {
				   if(Q.get(i).name.equals(vv)) {return i;}
			   }
			   return -1;
		   }
		   public static void relax(ArrayList<Vertex> Q,Vertex u, Vertex v,int[][] maps ) {
			   //System.out.println(u.name+v.name);
			   
			  // System.out.println(g.save(u.name)+" "+g.save(v.name));
			   
			   int ch=maps[g.save(u.name)][g.save(v.name)];
			   //System.out.println(ch);
			   if(v.key>u.key+ch) {
				   heap_decrease_key(Q,findindex(v.name,Q),u.key+ch);
				   v.p=u;
			   }
			   
			   
		   }
		   public static void initial(Graph g, Vertex s) {
			   s.input(0);
			   for(int i=2;i<numv;i++) {
				   //System.out.println(g.v[i].name);
				   g.v[i].key=9999999;
			   }
			   
		   }
	public static void build_min_heap(ArrayList<Vertex> A) {
		   for(int i=A.size()/2;i>=0;i--) {
		      min_heapify(A,i);
		   }
		}
		 public static Vertex heap_min(ArrayList<Vertex> A) {
		    return A.get(0);
		 }
		 public static void heap_decrease_key(ArrayList<Vertex> A,int i,int key) {
		    if(key>A.get(i).key) {
		       System.out.println("New key is larger than original.");
		    }
		    A.get(i).key=key;
		    while (i>0&&A.get(parent(i)).key>A.get(i).key) {
		       Vertex temp=A.get(i);
		       A.set(i,A.get(parent(i)));
		       A.set(parent(i),temp);
		       i=parent(i);
		       
		    }
		    
		 }
		 public static Vertex extract_min(ArrayList<Vertex> A) {
		    
		   if(A.size()<1) {
		      System.out.println("heap underflow");
		   }
		   
		   Vertex min=A.get(1);
		   A.set(1, A.get(A.size()-1));
		   A.remove(A.size()-1);
		   min_heapify(A,1);
		   return min;
		 }
		/* public static void min_insert(ArrayList<Vertex> A,Vertex key) {
		
		    A.add(new Vertex(100000));
		    heap_decrease_key(A,A.size()-1,key);
		 }*/
		 public static int parent(int i) {
		    return i/2;
		 }
		 public static int left(int i) {
		    return i*2;
		 }
		 public static int right(int i) {
		    return i*2+1;
		 }
		 public static void min_heapify(ArrayList<Vertex> a,int i) {
		   
		    int l=left(i);
		    int r=right(i);
		    int smallest;
		    if(l<a.size() &&a.get(l).key<a.get(i).key) {
		       smallest=l;
		    }
		    else smallest=i;
		    if(r<a.size()&&a.get(r).key<a.get(smallest).key) {
		       smallest=r;
		    }
		    if(smallest!=i) {
		       Vertex tmp=a.get(i);
		       a.set(i, a.get(smallest));
		       a.set(smallest, tmp);
		       min_heapify(
		    		   a,smallest);
		    }
		          
		 }}

