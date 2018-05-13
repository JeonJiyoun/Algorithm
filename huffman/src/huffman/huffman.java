package huffman;
import java.util.*;

 class node {
    int data;
    node left;
    node right;
    String code;
    node()
    {}
   public node(int freq){
	   this.data=freq;
        this.left = null;
        this.right = null;
    }
   public node(String code) {
	   this.code=code;
   }}
public class huffman {
 
   static ArrayList<node>Q=new ArrayList<node>(); 
   public static void main(String args[]){
       
       //Receiving Input   
       Scanner scan = new Scanner(System.in);
       int size = scan.nextInt();
       int[] q=new int[size+1];
       String[] code=new String[size+1];
        for(int i=1;i<=size;i++) {
          q[i]=scan.nextInt();
          Q.add(new node(q[i]));
          
       }
          
       build_min_heap(Q);
    
     
      node root= huffman(Q);
      gen_code(root,"",q,code);
      for(int i=1;i<=size;i++) {
    	  System.out.println(code[i]);
      }
   }

  
   public static int search(int key, int[]q) {
	   int i=1;
	   for(i=1;i<q.length;i++) {
		   if(key==q[i])
			   break;
	   }
	   return i;
   }
   public static void gen_code(node root,String s,int[]q,String[]code) {
	   
	   if(root!=null) {
		   if(root.right!=null) {
			 gen_code(root.right,s+"1",q,code);
			   		   }
		   if(root.left!=null) {
			   gen_code(root.left,s+"0",q,code);
		   }
		   if(root.left==null&&root.right==null) {
			    root.code=s;
			    code[search(root.data,q)]=s;
		   }
	   }
   }
public static node huffman(ArrayList<node> q) {
   int n=q.size();
   for(int i=1;i<=n-1;i++) {
      node left=extract_min(q);
   
      node right=extract_min(q);
     
      node z=new node(left.data+right.data);
      z.left=left;
      z.right=right;
     
      min_insert(q,z);
       
     
   }
   
   return extract_min(q);
}
public static void build_min_heap(ArrayList<node> A) {
	   for(int i=A.size()/2;i>=0;i--) {
	      min_heapify(A,i);
	   }
	}
	 public static int heap_min(ArrayList<node> A) {
	    return A.get(0).data;
	 }
	 public static void heap_decrease_key(ArrayList<node> A,int i,node key) {
	    if(key.data>A.get(i).data) {
	       System.out.println("New key is larger than original.");
	    }
	    A.set(i, key);
	    while (i>0&&A.get(parent(i)).data>A.get(i).data) {
	       node temp=A.get(i);
	       A.set(i,A.get(parent(i)));
	       A.set(parent(i),temp);
	       i=parent(i);
	       
	    }
	    
	 }
	 public static node extract_min(ArrayList<node> A) {
	    
	   if(A.size()<1) {
	      System.out.println("heap underflow");
	   }
	   
	   node min=A.get(0);
	   A.set(0, A.get(A.size()-1));
	   A.remove(A.size()-1);
	   min_heapify(A,0);
	   return min;
	 }
	 public static void min_insert(ArrayList<node> A,node key) {
	
	    A.add(new node(100000));
	    heap_decrease_key(A,A.size()-1,key);
	 }
	 public static int parent(int i) {
	    return i/2;
	 }
	 public static int left(int i) {
	    return i*2;
	 }
	 public static int right(int i) {
	    return i*2+1;
	 }
	 public static void min_heapify(ArrayList<node> a,int i) {
	   
	    int l=left(i);
	    int r=right(i);
	    int smallest;
	    if(l<a.size() &&a.get(l).data<a.get(i).data) {
	       smallest=l;
	    }
	    else smallest=i;
	    if(r<a.size()&&a.get(r).data<a.get(smallest).data) {
	       smallest=r;
	    }
	    if(smallest!=i) {
	       node tmp=a.get(i);
	       a.set(i, a.get(smallest));
	       a.set(smallest, tmp);
	       min_heapify(a,smallest);
	    }
	          
	 }}
