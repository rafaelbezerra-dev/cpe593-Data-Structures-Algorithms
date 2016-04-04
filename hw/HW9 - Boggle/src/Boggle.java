/**
* 
* @author Rafael Bezerra
*
* Based on the code found on:
* 	http://www.comscigate.com/cs/IntroSedgewick/40adt/47st/Boggle.java.html
* from Robert Sedgewick and Kevin Wayne
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Boggle {

	static class BoggleBoard {
		char[][] board;
		boolean[][] visited;
		Trie dict;

		public BoggleBoard(int n) {
			board = new char[n][n];
			visited = new boolean[n][n];
		}

		public void setDict(Trie dict) {
			this.dict = dict;
		}

		public void addInline(int pos, char v) {
			int i = pos / board.length;
			int j = pos % board.length;
			board[i][j] = v;
		}

		public boolean canMoveToPos(int i, int j) {
			if (i < 0 || j < 0 || i >= board.length || j >= board.length)
				return false;
			return !visited[i][j];
		}

		// show all words, starting from each possible starting place
		public void showWords() {
			for (int i = 0; i < board.length; i++)
				for (int j = 0; j < board.length; j++)
					dfs("", i, j);
		}

		// run depth first search starting at cell (i, j)
		private void dfs(String prefix, int i, int j) {
			if (i < 0 || j < 0 || i >= board.length || j >= board.length)
				return;
			// can't visited a cell more than once
			if (visited[i][j])
				return;
			// key to efficiency of backtracking algorithm
			if (!dict.containsPrefix(prefix))
				return;

			// not allowed to reuse a letter
			visited[i][j] = true;

			// found a word
			prefix = prefix + board[i][j];
			if (prefix.length() > 2 && dict.contains(prefix))
				System.out.println(prefix);

			// consider all neighbors
			for (int ii = i-1; ii <= i+1; ii++)
				for (int jj = j-1; jj <= j+1; jj++)
					dfs(prefix, ii, jj);

			visited[i][j] = false;
		}

		@Override
		public String toString() {
			StringBuilder b = new StringBuilder();
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					b.append(board[i][j]).append(' ');
				}
				b.append("\n");
			}
			return b.toString();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {

		Trie dict = new Trie();
		BoggleBoard board;
		Scanner in = new Scanner(new FileReader("dictionary.txt"));
		while (in.hasNextLine()) {
			String line = in.nextLine();
			dict.add(line);
		}
		in.close();
//		
//		in = new Scanner(new FileReader("example.txt"));
//		java.util.ArrayList<String> list = new java.util.ArrayList<>();
//		while(in.hasNextLine()){
//			list.add(in.next());
//		}
//		java.util.Collections.sort(list);
//		for (String string : list) {
//			System.out.println(string);
//		}
		

		in = new Scanner(new FileReader("boggle.dat"));
		int n = in.nextInt();
		board = new BoggleBoard(n);
		board.setDict(dict);
		for (int i = 0; i < n * n; i++) {
			char c = in.next().toCharArray()[0];
			board.addInline(i, c);
			// System.out.println(in.next());
		}
		in.close();
		// System.out.println(board.toString());
		board.showWords();

	}

}