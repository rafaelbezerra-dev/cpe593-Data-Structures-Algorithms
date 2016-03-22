
public class Trie {

	char c;
	Trie[] children;
	boolean isWord;

	public Trie() {
		this((char) 0);
	}

	public Trie(char c) {
		this.c = c;
		this.children = new Trie[26];
		this.isWord = false;
	}

	public void load(char[] dictinary) {
		if (dictinary.length == 0) {
			this.isWord = true;
			return;
		}

		char letter = dictinary[0];
		int index = letter - 'a';

		if (this.children[index] == null) {
			this.children[index] = new Trie(letter);
		}

		char[] dict = new char[dictinary.length - 1];
		for (int i = 0; i < dict.length; i++) {
			dict[i] = dictinary[i];
		}

		this.children[index].load(dict);
	}

	public void load(String word) {
		if (word.isEmpty()) {
			this.isWord = true;
			return;
		}

		char letter = word.charAt(0);
		int index = letter - 'a';

		if (this.children[index] == null) {
			this.children[index] = new Trie(letter);
		}

		this.children[index].load(word.substring(1));
	}

	public boolean constains(String word) {
		if (word.isEmpty())
			if (this.isWord)
				return true;
			else
				return false;

		char letter = word.charAt(0);
		int index = letter - 'a';

		if (this.children[index] == null) {
			return false;
		}

		return this.children[index].constains(word.substring(1));
	}

	boolean startsWith(String prefix) {
		if (prefix.isEmpty()) {
			return true;
		}

		char letter = prefix.charAt(0);
		int index = letter - 'a';

		if (this.children[index] == null) {
			return false;
		}

		return this.children[index].constains(prefix.substring(1));
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(c);
	}

}