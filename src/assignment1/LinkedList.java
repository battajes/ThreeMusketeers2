package assignment1;

 abstract interface LinkedList{
	public static final Node first = null;
//	public static final Node next = new Node(null);
//	public LinkedList(Node curr, Node next) {
//		this.curr = curr;
//		this.next = next;
//		
//	}
	public abstract Object first();
	public abstract Object getFirstItem();
	public abstract Object next();
	public abstract Object currItem();
	public abstract void add(Node s); 
	public abstract void delete();


	
}
