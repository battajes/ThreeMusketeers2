package assignment1;



public class IconSelector {
	public LinkedList x;

	public IconSelector() {
		// TODO Auto-generated constructor stub
		Node l = new Node("\n"
				+ "            _\\|/^\n"
				+ "             (_oo /\n"
				+ "            /-|--/\n"
				+ "            \\ |\n"
				+ "              /--i\n"
				+ "             /   L\n"
				+ "             L");
		Node s = new Node(" /﹋\\\n"
				+ "(҂`_´)\n"
				+ "<,︻╦╤─ ҉ - -\n"
				+ "/﹋\\");
	
		Node j = new Node("  \n"
				+ "         (_O_\n"
				+ "           W )\n"
				+ "          /\"\\__\n"
				+ "        _|     '");
		Node m = new Node("o  /`\n"
				+ "/\\/\n"
				+ " <");
		Node d = new Node("     /\n"
		+ "o   o\n"
		+ " \\\n"
		+ "   0   o\n"
		+ "  /#\\ /\n"
		+ " + # +\n"
		+ "  / \\\n"
		+ " =   =");
		l.next = s;
		s.next = j;
		j.next = m;
		m.next = d;
		this.x = this.createLinkedList();
		this.x.add(l);
			
		}

	public LinkedList createLinkedList() {
		return new ConcreteLinkedList();
		
	}
	public  void addIcon(Node s) {
		this.x.add(s);
		
	}
	public Object getNext() {
		
		x.next();
		return this.getCurr();
		
	}
	public Object getCurr() {
		return this.x.currItem();
	}
	
	public void selected() {
		x.delete();
	}
		

	

}
