package assignment1;


public class Node{
	String item;
	Node next;

	public Node(String s) {
		// TODO Auto-generated constructor stub
		this.item = s;
		this.next = null;
		
	}
	
	public String getCurr() {
		return this.item;
		
	}
	public Node next() {
		return this.next;
	}
	
	public String toString() {
		return (this.item);
	}

}
