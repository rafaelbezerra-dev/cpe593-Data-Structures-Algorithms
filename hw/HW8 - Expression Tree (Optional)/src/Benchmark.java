
public class Benchmark {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String input = "2 3 + 4 5 + +";
		final String[] arr = input.split(" ");
		
		Tree t = new Tree();
		
		for (int i = arr.length - 1; i > -1; i--) {
			t.add(arr[i]);
		}
	}

}
