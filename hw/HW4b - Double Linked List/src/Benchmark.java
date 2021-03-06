import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Benchmark {

	static int size = 10000000;
	final static String ADD_FRONT = "ADD_FRONT";
	final static String ADD_BACK = "ADD_BACK";
	final static String REMOVE_FRONT = "REMOVE_FRONT";
	final static String REMOVE_BACK = "REMOVE_BACK";
	final static String OUTPUT = "OUTPUT";
	
	public static void main(String[] args) throws Exception {
//		test1();
		test2();

	}

	private static void test2() throws Exception {
		DoubleLinkedList list = new DoubleLinkedList();
		list.toString();
		Scanner in = new Scanner(new FileReader("HW4.test.txt"));
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
	private static void test1() {
		// TODO Auto-generated method stub
		DoubleLinkedList list = new DoubleLinkedList();
		
		long start = System.currentTimeMillis();
		for (int i = 1; i <= size; i++) {
			list.addFront(i);
		}
		System.out.println("addFront: " + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis();
		for (int i = 1; i <= size; i++) {
			list.addBack(i);
		}
		System.out.println("addBack: " + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			list.removeBack();
		}
		System.out.println("removeBack: " + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			list.removeFront();
		}
		System.out.println("removeFront: " + (System.currentTimeMillis() - start));
		
		list = new DoubleLinkedList();
		start = System.currentTimeMillis();
		for (int i = 1; i <= size; i++) {
			if (i%2 == 0)
				list.addFront(i);
			else
				list.addBack(i);
		}
		System.out.println("addFront & addBack: " + (System.currentTimeMillis() - start));
		System.out.println(list.size());
	}

}
