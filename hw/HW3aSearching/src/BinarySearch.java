import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class BinarySearch {

	static final int POSITION = 0;
	static final int ITERATIONS = 1;
	static final String NOT_FOUND = "NOT FOUND";

	private static int[] search(int[] x, int target) {
		final int len = x.length;
		int[] res = { -1, 0 };
		// if (target > x[len - 1] || target < x[0]) {
		// return res;
		// }

		int i = 0, j = len - 1;

		while (i <= j) {
			res[ITERATIONS]++;
			int middle = ((i + j) / 2);
			if (x[middle] > target) {
				j = middle - 1;
			} else {
				if (x[middle] < target) {
					i = middle + 1;
				} else {
					res[POSITION] = middle;
					return res;
				}
			}

		}

		return res;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new FileReader("hw.dat"));
		final String pattern = "Cases:";
		int cases = 0;
		String firstLine = s.nextLine();
		if (firstLine.contains(pattern)) {
			cases = Integer.parseInt(firstLine.replace(pattern, "").trim());
		}

		for (int i = 0; i < cases; i++) {
			int n = s.nextInt();
			int[] x = new int[n];

			for (int j = 0; j < n; j++)
				x[j] = s.nextInt();

			int target = s.nextInt();

			int[] res = search(x, target);
			System.out.println((res[POSITION] != -1 ? x[res[POSITION]] : NOT_FOUND) + " " + res[ITERATIONS]);
		}

		s.close();
	}

}
