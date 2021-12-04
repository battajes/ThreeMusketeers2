package assignment1;

public class ConcreteLinkedList<ArrayList> {
	Node curr;
	Node next;

	public ConcreteLinkedList(Node curr, Node next) {
		this.curr = curr;
		this.next = next;
	}
	
	public Object first() {
		return this.curr;
		
	}
	
	public Object next() {
		return this.next();
		
	}
	
	public Boolean hasNext() {
		if (this.curr.next == null) {
			return false;
		}
		return true;
	}
	
	public Object currentItem() {
		return this.curr.curr;
	}
}
