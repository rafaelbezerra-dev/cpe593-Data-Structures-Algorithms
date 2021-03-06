import java.io.FileReader;
import java.util.Scanner;

public class DoubleLinkedList {

	private Node head, tail;

	static class Node {
		Node prev, next;
		int val;

		public Node(Node prev, Node next, int val) {
			// super();
			this.prev = prev;
			this.next = next;
			this.val = val;
		}

		@Override
		public String toString() {
			return "Node " 
					+ "[prev=" + (prev != null ? prev.val : null) + ", "
					+ "val=" + val  + ", " 
					+ "next=" + (next != null ? next.val : null) + "]";
		}

	}

	final static String ADD_FRONT = "ADD_FRONT";
	final static String ADD_BACK = "ADD_BACK";
	final static String REMOVE_FRONT = "REMOVE_FRONT";
	final static String REMOVE_BACK = "REMOVE_BACK";
	final static String OUTPUT = "OUTPUT";

	public static void main(String[] args) throws Exception {
		DoubleLinkedList list = new DoubleLinkedList();
		list.toString();
		Scanner in = new Scanner(new FileReader("HW4b.txt"));
		while (in.hasNextLine()) {
			String commandLine = in.nextLine();
			if (commandLine != null && !commandLine.isEmpty()) {
				String[] splited = commandLine.split(" ");
				switch (splited[0]) {
				case ADD_FRONT:
					int[] values = new int[splited.length - 1];
					for (int i = 1; i < splited.length; i++) {
						int value = Integer.valueOf(splited[i]);
						// list.addFront(value);
						values[i - 1] = value;
					}
					for (int i = values.length - 1; i >= 0; i--)
						list.addFront(values[i]);
					break;
				case ADD_BACK:
					for (int i = 1; i < splited.length; i++) {
						int value = Integer.valueOf(splited[i]);
						list.addBack(value);
					}
					break;
				case REMOVE_FRONT:
					for (int i = 0; i < Integer.valueOf(splited[1]); i++) {
						list.removeFront();
					}
					break;
				case REMOVE_BACK:
					for (int i = 0; i < Integer.valueOf(splited[1]); i++) {
						list.removeBack();
					}
					break;
				case OUTPUT:
					int size = list.size();
					for (int i = 0; i < size; i++) {
						System.out.print(list.get(i) + " ");
					}
					System.out.println("");
					break;
				default:
					throw new Exception("Invalid input");
				}
			}
		}

	}

	public DoubleLinkedList() {
		head = tail = null;
	}

	public int get(int i) {
		// TODO Auto-generated method stub
		Node p = head;
		for (; i > 0; i--) {
			p = p.next;
		}
		return p.val;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder b = new StringBuilder("Linked List: ");
		for (Node p = head; p != null; p = p.next)
			b.append(p.val).append(" ");
		return b.toString();
	}

	public int size() {
		int i = 0;
		for (Node p = head; p != null; p = p.next)
			i++;
		return i;
	}

	public void removeBack() {
		// TODO Auto-generated method stub
		if (tail != null) {
			if (tail.prev != null) {
				tail = tail.prev;
				tail.next = null;
			}
			else{
				tail = null;
			}
		}

	}

	public void removeFront() {
		// TODO Auto-generated method stub
		if (head != null) {
			if (head.next != null) {
				head = head.next;
				head.prev = null;
			}
			else{
				head = null;
			}
		}
	}

	public void addBack(int v) {
		// TODO Auto-generated method stub
		tail = new Node(tail, null, v);
		if (tail.prev != null)
			tail.prev.next = tail;

		if (head == null)
			head = tail;
	}

	public void addFront(int v) {
		// TODO Auto-generated method stub
		head = new Node(null, head, v);
		if (head.next != null)
			head.next.prev = head;

		if (tail == null)
			tail = head;

	}

}
