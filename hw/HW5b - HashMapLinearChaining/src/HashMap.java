import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class HashMap {

	static class DoubleLinkedList<T> {

		private Node<T> head, tail;

		static class Node<T> {
			Node<T> prev, next;
			T val;

			public Node(Node prev, Node next, T val) {
				// super();
				this.prev = prev;
				this.next = next;
				this.val = val;
			}

			@Override
			public String toString() {
				return "Node " + "[prev=" + (prev != null ? prev.val : null) + ", " + "val=" + val + ", " + "next="
						+ (next != null ? next.val : null) + "]";
			}

		}

		public DoubleLinkedList() {
			head = tail = null;
		}

		public T get(int i) {
			// TODO Auto-generated method stub
			Node<T> p = head;
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
			for (Node<T> p = head; p != null; p = p.next)
				i++;
			return i;
		}

		public void removeBack() {
			// TODO Auto-generated method stub
			if (tail != null) {
				if (tail.prev != null) {
					tail = tail.prev;
					tail.next = null;
				} else {
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
				} else {
					head = null;
				}
			}
		}

		public void addBack(T v) {
			// TODO Auto-generated method stub
			tail = new Node<T>(tail, null, v);
			if (tail.prev != null)
				tail.prev.next = tail;

			if (head == null)
				head = tail;
		}

		public void addFront(T v) {
			// TODO Auto-generated method stub
			head = new Node<T>(null, head, v);
			if (head.next != null)
				head.next.prev = head;

			if (tail == null)
				tail = head;
		}

	}

	final int BIN_SIZE = 150000;// 280000;
	DoubleLinkedList<String>[] bins;

	public HashMap() {
		bins = new DoubleLinkedList[BIN_SIZE];
	}

	public long getKey(final String value) {
		int sum = 0;
		char[] keyArr = value.toCharArray();
		for (int i = 0; i < keyArr.length; i++)
			sum = sum * 26 + (keyArr[i] - '@');
		return sum;
	}

	private int hash(final String value) {
		return (int) (Math.abs(getKey(value)) % BIN_SIZE);
	}

	public void add(String value) {
		int index = hash(value);
		if (bins[index] == null)
			bins[index] = new DoubleLinkedList<String>();

		bins[index].addFront(value);
	}

	public boolean contains(String value) {
		int index = hash(value);
		if (bins[index] == null)
			return false;
		else {
			int size = bins[index].size();
			for (int i = 0; i < size; i++) {
				if (value.equals(bins[index].get(i))){
					return true;
				}
			}
			return false;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		HashMap dict = new HashMap();
		Scanner in = new Scanner(new FileReader("dict.txt"));
		while (in.hasNextLine()) {
			String line = in.nextLine();
			dict.add(line);
		}
		
		in.close();

		in = new Scanner(new FileReader("hw8.dat"));
		while (in.hasNextLine()) {
			String line = in.nextLine();
			System.out.println(line + ":\t" + dict.contains(line));
		}
		in.close();

		long sum = 0;
		int empty = 0;
		int max = 0;
		for (int i = 0; i < dict.bins.length; i++) {
			if (dict.bins[i] != null) {
				int size = dict.bins[i].size();
				if (size > 0) {
					sum += size;
					if (size > max) max = size;
				} else {
					empty++;
				}
			} else {
				empty++;
			}
		}
		System.out.println("\n\n################## INFO ##################");
		
		System.out.println(""
				+ "   -------------------------------------------------------------\n"
				+ "  | NOTE: The assignment requires a histogran with number of    |\n"
				+ "  |       'steps' to insert each word.                          |\n"
				+ "  |       As this program implements a Linear Chaining HashMap. |\n"
				+ "  |       Every word takes 1 step to be inserted as it is       |\n"
				+ "  |       always inserted at the beginning of the list.         |\n"
				+ "   -------------------------------------------------------------\n");
		
		System.out.println("  Average size of bins: " + (sum / (double) (dict.bins.length - empty)));
		System.out.println("  Bigest bin: " + max);
		System.out.println("  Empty Bins: " + empty + " (" + (100*empty/dict.bins.length) + "%)");
		System.out.println("##########################################");
	}

}
