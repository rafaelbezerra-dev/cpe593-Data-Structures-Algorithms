import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Benchmark {

	public static void main(String[] args) throws FileNotFoundException {

		Trie dict = new Trie();

		Scanner in = new Scanner(new FileReader("dict.txt"));
		while (in.hasNextLine()) {
			String line = in.nextLine();
//			dict.load(line.toCharArray());
			dict.load(line);
			// System.out.println(line);
		}
		in.close();

		in = new Scanner(new FileReader("hw7.dat"));
		while (in.hasNextLine()) {
			String line = in.nextLine();
			System.out.println(line + "\t" + dict.constains(line));
		}
		in.close();

	}

}
