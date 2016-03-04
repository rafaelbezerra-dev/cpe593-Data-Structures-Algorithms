import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;

import javax.management.InvalidAttributeValueException;

public class GrowArray {

	static class GrowableArray {
		private int[] p;
		private int size;
		private int used;

		public GrowableArray() { // O(n)
			this.size = 1;
			this.p = new int[this.size]; // O(n)
			this.used = 0;
		}

		public GrowableArray(int size) { // O(n)
			this.size = size;
			this.p = new int[this.size]; // O(n)
			this.used = 0;
		}

		private void grow() { // O(n)
			this.size *= 2;
			// System.out.println("Growing array from " + this.used + " to " +
			// this.size);
			int[] temp = this.p;
			this.p = new int[this.size];
			for (int i = 0; i < temp.length; i++)
				p[i] = temp[i];
			// System.out.println("done");
		}

		private void shiftLeft(int start, int count) { // O(n)
			for (int i = start; i < count - 1;) {
				this.p[i] = this.p[++i];
			}
			this.p[count - 1] = 0;
		}

		private void shiftRight(int start, int count) { // O(n)
			for (int i = count; i > start;) {
				this.p[i] = this.p[--i];
			}
		}

		public void addBack(int v) { // O(log n)
			if (this.size == this.used)
				grow();
			this.p[this.used++] = v;
		}

		public void addFront(int v) { // O(n log n)
			if (this.size == this.used)
				grow();
			this.shiftRight(0, this.used);
			this.used++;
			this.p[0] = v;
		}

		public void removeFront() {
			shiftLeft(0, this.used--);
			// this.used--;
		}

		public void removeBack() {// O(1)
			this.p[--this.used] = 0;
		}

		public int size() { // O(1)
			return this.size;
		}

		public int used() { // O(1)
			return this.used;
		}

		public int get(int i) { // O(1)
			if (i < 0 || i >= this.used)
				throw new IllegalArgumentException("Index out of bounds: " + i);
			return this.p[i];
		}

		void set(int i, int v) { // O(1)
			if (i < 0 || i >= this.used)
				throw new IllegalArgumentException("Index out of bounds: " + i);
			this.p[i] = v;
		}

	}

	public static void test() {
		long startTime = System.currentTimeMillis();
		BetterGrowableArray list = new BetterGrowableArray();
		final int n = 100000000;
		for (int i = 1; i <= n; i++) {
			if (i % 2 == 0) {
				list.addBack(i);
			} else {
				list.addFront(i);
			}
		}
		System.out.println("test >> took: " + ((System.currentTimeMillis() - startTime) / 1000f) + " sec \n");
		System.out.println("test >> n: " + n + " and list is " + list.used());
		System.out.println("test >> actual list size: " + list.size());
	}

	public static void test1() {

		final int n = 100000000;
		BetterGrowableArray list = new BetterGrowableArray();

		long startTime = System.currentTimeMillis();
		for (int i = 1; i <= n; i++)
			list.addBack(i);
		System.out.println("addBack took: " + ((System.currentTimeMillis() - startTime) / 1000f) + " sec \n");

		System.out.println("testing...");
		System.out.println("	n: " + n + " and list is " + list.used());
		System.out.println("	actual list size: " + list.size());
		for (int i = 0; i < n; i++)
			if (i + 1 != list.get(i))
				System.out.println("	 wrong value");
		System.out.println("");

		startTime = System.currentTimeMillis();
		for (int i = 1; i <= n; i++)
			list.removeBack();
		System.out.println("removeBack took: " + ((System.currentTimeMillis() - startTime) / 1000f) + " sec \n");

		startTime = System.currentTimeMillis();
		for (int i = 1; i <= n; i++)
			list.addFront(i);
		System.out.println("addFront took: " + ((System.currentTimeMillis() - startTime) / 1000f) + " sec \n");

		System.out.println("testing...");
		System.out.println("	n: " + n + " and list is " + list.used());
		System.out.println("	actual list size: " + list.size());
		for (int i = 0; i < n; i++)
			if (i + 1 != list.get(n - 1 - i))
				System.out.println("	 wrong value");
		System.out.println("");

		startTime = System.currentTimeMillis();
		for (int i = 1; i <= n; i++)
			list.removeFront();
		System.out.println("removeFront took: " + ((System.currentTimeMillis() - startTime) / 1000f) + " sec \n");

		System.out.print("\n======================\n");
	}
	
	public static void test2() {
		final int n = 100000000;
		BetterGrowableArray list = new BetterGrowableArray();

		long startTime = System.currentTimeMillis();		
		for (int i = 1; i <= n; i++)
			list.addFront(i);
		System.out.println("addFront took: " + ((System.currentTimeMillis() - startTime) / 1000f) + " sec \n");

		System.out.println("testing...");
		System.out.println("	n: " + n + " and list is " + list.used());
		System.out.println("	actual list size: " + list.size());
		for (int i = 0; i < n; i++)
			if (i + 1 != list.get(n - 1 - i))
				System.out.println("	 wrong value");
		System.out.println("");

		startTime = System.currentTimeMillis();
		for (int i = 1; i <= n; i++)
			list.removeFront();
		System.out.println("removeFront took: " + ((System.currentTimeMillis() - startTime) / 1000f) + " sec \n");

		startTime = System.currentTimeMillis();
		for (int i = 1; i <= n; i++)
			list.addBack(i);
		System.out.println("addBack took: " + ((System.currentTimeMillis() - startTime) / 1000f) + " sec \n");

		System.out.println("testing...");
		System.out.println("	n: " + n + " and list is " + list.used());
		System.out.println("	actual list size: " + list.size());
		for (int i = 0; i < n; i++)
			if (i + 1 != list.get(i))
				System.out.println("	 wrong value");
		System.out.println("");

		startTime = System.currentTimeMillis();
		for (int i = 1; i <= n; i++)
			list.removeBack();
		System.out.println("removeBack took: " + ((System.currentTimeMillis() - startTime) / 1000f) + " sec \n");

		System.out.print("\n======================\n");

	}

	

	final static String ADD_FRONT = "ADD_FRONT";
	final static String ADD_BACK = "ADD_BACK";
	final static String REMOVE_FRONT = "REMOVE_FRONT";
	final static String REMOVE_BACK = "REMOVE_BACK";
	final static String OUTPUT = "OUTPUT";
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 test();
		 test1();
		 test2();

		 System.out.print("\n\n");

		BetterGrowableArray list = new BetterGrowableArray();
		Scanner in = new Scanner(new FileReader("HW4a.txt"));
		while (in.hasNextLine()) {
			String commandLine = in.nextLine();
			if (commandLine != null && !commandLine.isEmpty()) {
				String[] splited = commandLine.split(" ");
				switch (splited[0]) {
				case ADD_FRONT:
					for (int i = 1; i < splited.length; i++) {
						int value = Integer.valueOf(splited[i]);
						list.addFront(value);
					}
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