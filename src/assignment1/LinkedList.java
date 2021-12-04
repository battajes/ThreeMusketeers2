package assignment1;

public abstract class LinkedList{
	Node curr;
	Node next;
	public LinkedList(Node curr, Node next) {
		this.curr = curr;
		this.next = next;
		
	}
	public abstract Object first();
	public abstract Node next();
	public abstract Object currItem();
	public abstract void addFirst(Object s);
	public abstract IconSelector list();
	
}
