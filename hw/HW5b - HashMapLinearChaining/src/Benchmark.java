import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Benchmark {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		HashMap dict = new HashMap();
		Scanner in = new Scanner(new FileReader("dict.txt"));
		// Scanner in = new Scanner(new FileReader("hw8.dat"));=
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
//					System.out.print(size + " ");
					if (size > max) max = size;
				} else {
//					System.out.print("0 ");
					empty++;
				}
			} else {
//				System.out.print("0 ");
				empty++;
			}
//			if ((i + 1) % 100 == 0)
//				System.out.println("");
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

		// System.out.println(nlines + " = " + map.size());
	}

}
