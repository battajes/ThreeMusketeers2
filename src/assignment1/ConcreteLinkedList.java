package assignment1;

public class ConcreteLinkedList implements LinkedList {

	Node first;
	Node curr;

	public ConcreteLinkedList() {


		
		this.first = null;
	}
	
	public Object first() {
		return this.first.item;
		
	}
	
	public void add(Node s) {
		if (this.first == null) {
			this.first = s;
			this.curr = this.first;
		}
		else { 
			Node now = this.first;
			while (now.next != null) {
				now  = now.next;
			}
			now.next =  s;
		}
		
		
	}
	
	public Node next() {
		if (this.curr.next != null) {
			this.curr = this.curr.next;
		}
		else {
			this.curr = this.first;
		}
			return this.curr;
		
	}
	
	public Node currItem() {
		return this.curr;
	}
	
	public Boolean HasNext() {
		if (this.curr.next != null) {
			return true;
		}
		return false;
	}
	


	@Override
	public Object getFirstItem() {
		// TODO Auto-generated method stub
		return this.first.item;
	}
	
	public void delete() {
		if (this.curr == this.first) {
			this.curr = this.first.next;
			this.first = this.curr;
			
		}

		if (this.curr.next == null) {
			Node before = this.getBefore(this.curr);
			before.next = null;
			this.curr = this.first;
		}
		

		else {
			Node hi = curr.next;
			Node before = this.getBefore(this.curr);
			before.next = hi;
			this.curr = hi;
			
		}
	}
	
	private Node getBefore(Node node) {
		Node hello = this.first;
		while (hello.next != null) {
			if (hello.next ==  node) {
				return hello;
			}
			hello = hello.next;
			
		}
		return node;
	}

	
	

}
