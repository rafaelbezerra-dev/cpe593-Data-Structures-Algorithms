import java.io.FileReader;
import java.util.Scanner;

public class GrowableArray {

	static class BetterGrowableArray {
		private int[] p;
		private int size, head, tail;

		public BetterGrowableArray() { // O(n)
			size = 2;
			p = new int[size]; // O(n)
			head = 0;
			tail = 1;
		}

		public BetterGrowableArray(int size) { // O(n)
			this.size = size;
			p = new int[size]; // O(n)
			head = 0;
			tail = 1;
		}

		private void growRight() { // O(n)
			int used = used();
			if (head < used) {
				size += used;
				int[] temp = p;
				p = new int[size]; // O(n)
				for (int i = head + 1; i < tail; i++) // O(n)
					p[i] = temp[i];
			} else {
				int offset = (size / 2) - (used / 2);
				for (int i = head + 1; i < tail; i++) // O(n)
					p[i - offset] = p[i];
				head -= offset;
				tail -= offset;

			}
		}

		private void growLeft() { // O(n)
			int used = used();
			if ((size - tail) < used + 1) {
				size += used;
				int[] temp = p;
				p = new int[size];
				int offset = p.length - temp.length;
				for (int i = 0; i < tail; i++)
					p[offset + i] = temp[i];

				head = offset - 1;
				tail += offset;
			} else {
				int offset = (size / 2) - (used / 2);
				for (int i = tail - 1; i > head; i--) // O(n)
					p[i + offset] = p[i];
				head += offset;
				tail += offset;

			}
		}

		public void addBack(int v) { // O(2n)
			if (tail == p.length)
				growRight();

			p[tail++] = v;

		}

		public void addFront(int v) { // O(2n)
			if (head == -1)
				growLeft();

			p[head--] = v;
		}

		public void removeFront() { // O(1)
			if (used() > 0)
				++head;
			// p[++head] = 0;
		}

		public void removeBack() {// O(1)
			if (used() > 0)
				--tail;
			// p[--tail] = 0;
		}

		public int size() { // O(1)
			return size;
		}

		public int used() { // O(1)
			return (tail - head) - 1;
		}

		public int get(int i) { // O(1)
			if (i < 0 || i >= used())
				throw new IllegalArgumentException("Index out of bounds: " + i);
			return p[head + 1 + i];
		}

		void set(int i, int v) { // O(1)
			if (i < 0 || i >= used())
				throw new IllegalArgumentException("Index out of bounds: " + i);
			p[head + 1 + i] = v;
		}

	}


	final static String ADD_FRONT = "ADD_FRONT";
	final static String ADD_BACK = "ADD_BACK";
	final static String REMOVE_FRONT = "REMOVE_FRONT";
	final static String REMOVE_BACK = "REMOVE_BACK";
	final static String OUTPUT = "OUTPUT";
	
	public static void main(String[] args) throws Exception {
		BetterGrowableArray list = new BetterGrowableArray();
		Scanner in = new Scanner(new FileReader("HW4a.txt"));
		while (in.hasNextLine()) {
			String commandLine = in.nextLine();
			if (commandLine != null && !commandLine.isEmpty()) {
				String[] splited = commandLine.split(" ");
				switch (splited[0]) {
				case ADD_FRONT:
					int[] values = new int[splited.length - 1]; 
					for (int i = 1; i < splited.length; i++) {
						int value = Integer.valueOf(splited[i]);
//						list.addFront(value);
						values[i-1] = value;
					}
					for (int i = values.length-1; i >= 0 ; i--)
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
					for (int i = 0; i < list.used(); i++) {
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

	
}