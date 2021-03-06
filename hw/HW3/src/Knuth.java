import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.WRITE;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Knuth {
	final static boolean DEBUG = false;
	final static Random rnd = new Random(Calendar.getInstance().getTimeInMillis());

	private static void swap(int[] x, int first, int second) {
		int temp = x[first];
		x[first] = x[second];
		x[second] = temp;
	}

	public static void insertionsort(int[] x) {
		final int length = x.length;
		for (int i = 1; i < length; i++) {
			// ------------------------------------------
			if (DEBUG) {
				if (i % (length / 100) == 0) {
					System.out.println("insertion sorting... " + (Math.round((((float) i) / x.length) * 100f)) + "%");
				}
			}
			// ------------------------------------------
			if (x[i] >= x[i - 1])
				continue;
			int temp = x[i];
			int j;
			for (j = i - 1; j >= 0; j--) {
				if (x[j] > temp)
					x[j + 1] = x[j];
				else {
					break;
				}
			}
			x[j + 1] = temp;
		}
	}

	public static void quicksort(int[] x, int left, int right, int k) {
		// System.out.println("left: " + left
		// + " right: " + right);

		if (right <= left)
			return;

		if ((right - left) < k) {
			if (x[left] > x[right])
				swap(x, left, right);
			return;
		}

		int pivotIndex = (Math.abs(rnd.nextInt()) % (right - left + 1)) + left;
		int pivot = x[pivotIndex];
		int i = left, j = right;

		while (i < j) {
			// System.out.println("Looking for i");
			while (x[i] <= pivot)
				i++;
			// System.out.println("Looking for j");
			while (x[j] > pivot)
				j--;
			if (i < j) {
				// System.out.println("swapping...");
				// System.out.println("x[" + i + "]:" + x[i]);
				// System.out.println("x[" + j + "]:" + x[j]);
				// System.out.println("");
				swap(x, i, j);
			}
		}

		if (left < j)
			quicksort(x, left, j, k);
		if (right > i)
			quicksort(x, i, right, k);

	}

	public static void knuthQuicksort(int[] x, int left, int right, int k) {
		quicksort(x, left, right, k);
		insertionsort(x);
	}

	public static int[] getRandomArray() {
		Random rnd = new Random(Calendar.getInstance().getTimeInMillis());
		final int n = 10000000;
		int[] x = new int[n];
		HashSet<Integer> set = new HashSet<>();
		while (set.size() < n) {
			set.add((Math.abs(rnd.nextInt()) % 100000000));
		}
		int j = 0;
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			x[j] = integer.intValue();
			j++;
		}
		return x;

	}

	public static void generateRandomNumbersFile(int size, int upperLimit, String filename) throws IOException {
		Random rnd = new Random(Calendar.getInstance().getTimeInMillis());

		Path path = Paths.get(filename);
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write(size + "\n");
			for (int i = 0; i < size; i++) {
				writer.write((Math.abs(rnd.nextInt()) % upperLimit) + " ");
				if (i % (size / 100) == 0) {
					System.out.println(
							"writing file " + filename + "... " + (Math.round(((float) i) / size * 100)) + "%");
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// int power = 8;
		ArrayList<String> files = new ArrayList<>();
		for (int power = 6; power <= 8; power++) {
			try {
				int x = ((Double) Math.pow(10, power)).intValue();
				String fileName = "extra_10to" + power + ".txt";
				files.add(fileName);
				generateRandomNumbersFile(x, x, fileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (String file : files) {
			System.out.println("Reading from file " + file + "...");
			Scanner s = new Scanner(new FileReader(file));
			int n = s.nextInt();
			int[] x = new int[n];

			for (int i = 0; i < n; i++) {
				x[i] = s.nextInt();
				// ------------------------------------------
				// if (DEBUG)
				if (i % (n / 100) == 0)
					System.out.println("reading " + file + "... " + (Math.round(((float) i) / x.length * 100)) + "%");
				// ------------------------------------------
			}
			s.close();
			System.out.println("Reading completed...\n" + "Starting Sorting...\n");

			List<String> lines = new ArrayList<>();
			lines.add("k,time");

			sort(x, 100);
			int eps = 5;
			for (int k = 1000; k <= 500000; k += 1000) {
				System.out.println("Setting K to " + k);
				long estimatedTime = 0;
				for (int i = 1; i <= eps; i++) {
					estimatedTime += sort(x, k);
				}
				estimatedTime = estimatedTime / eps;
				lines.add(k + "," + estimatedTime);
				System.out.println();
			}
			Path path = Paths.get("result_"+ file.replace(".txt", "") +".csv");
			Files.write(path, lines, UTF_8, APPEND, CREATE);
		}

	}

	public static long sort(int[] x, int k) throws IOException {
		long startTime = System.currentTimeMillis();
		knuthQuicksort(x, 0, x.length - 1, k);
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("The sorting took " + (estimatedTime / 1000f) + " seconds to run.");

		// for (int i = 0; i < x.length; i++) {
		// if (i % 20 == 0)
		// System.out.println("");
		// System.out.print(x[i] + " ");
		// }
		return estimatedTime;
	}

}
