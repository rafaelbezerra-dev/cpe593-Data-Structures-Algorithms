import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class BuildList {

	public static int[] run(int[] x, int[] y) {
		int[] zeros = new int[x.length];
		int[] res;
		int count = x.length;

		for (int i = 0; i < y.length; i++) {
			for (int j = 0; j < x.length; j++) {
				if (zeros[j] == 1)
					if (x[j] == y[i])
						break;
					else
						continue; 
				
				if (x[j] % y[i] == 0) {
					for (int l = j; l < x.length; l += y[i]) {
						if (zeros[l] == 1)
							continue;
						zeros[l] = 1;
						count--;
					}
					break;
				}
			}
		}

		res = new int[count];
		int res_index = 0;
		for (int i = 0; i < zeros.length; i++) {
			if (zeros[i] == 0) {
				res[res_index++] = x[i];
			}
		}
		return res;
	}

	public static int[] runLessRam(int[] x, int[] y) {
		int[] res;
		int count = x.length;

		for (int i = 0; i < y.length; i++) {
			for (int j = 0; j < x.length; j++) {
				if (x[j] == 0)
					if (x[j] == y[i])
						break;
					else
						continue; 
				
				if (x[j] % y[i] == 0) {
					for (int l = j; l < x.length; l += y[i]) {
						if (x[l] == 0)
							continue;
						x[l] = 0;
						count--;
					}
					break;
				}
			}
		}

		res = new int[count];
		int res_index = 0;
		for (int i = 0; i < x.length; i++) {
			if (x[i] != 0) {
				res[res_index++] = x[i];
			}
		}
		return res;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		/*
		 * THIS CODE WAS TESTED WITH LISTS UP TO 10^9.
		 * BEFORE RUNNIG, YOU SHOULD CHANGE THE MAXIMUM
		 * JAVA HEAP SIZE TO 12 GB IF YOU WANT TO LISTS
		 * OF THAT SIZE.
		 * 
		 * IF YOU DO NOT WANT TO USE THAT MUCH RAM, 
		 * YOU MAY ALSO USE THE METHOD runLessRam()
		 * BUT IT IS WAY SLOWER AND YOU STILL NEED TO
		 * USE AT LEAST 6 GB TO RUN IT FOR LISTS WITH
		 * SIZES OF 10^9. 
		 * */
		
		System.out.println("Max Memory: " + (Runtime.getRuntime().maxMemory()/(Math.pow(2, 20))) + " MB");
		
		final String pattern = "Cases:";
		final String source = "HW.dat";
		Scanner s = new Scanner(new FileReader(source));
		StringBuilder csv = new StringBuilder("list_size,miliseconds\n");
		
		int cases = 0;
		String firstLine = s.nextLine();
		if (firstLine.contains(pattern)) {
			cases = Integer.parseInt(firstLine.replace(pattern, "").trim());
		}

		for (int i = 0; i < cases; i++) {
			int start = s.nextInt();
			int end = s.nextInt();
			int[] arr = new int[end - start + 1];
			for (int j = 0; j < arr.length; j++)
				arr[j] = start++;

			int n = s.nextInt();
			int[] multi = new int[n];
			for (int j = 0; j < multi.length; j++)
				multi[j] = s.nextInt();

			long startTime = System.currentTimeMillis();
			int[] res = run(arr, multi);
			long sum = 0;
			for (int j = 0; j < res.length; j++)
				sum += res[j];
			long elapsedTime = System.currentTimeMillis() - startTime;
			System.out.println(res.length + " " + sum + " " + elapsedTime);
			csv.append(arr.length)
				.append(",")
				.append(elapsedTime)
				.append("\n");
//			System.gc();
		}
		s.close();
		
		try {
			System.out.print("Writing output to file...");
			Files.write(Paths.get(source.replace(".dat", "") + ".csv"), csv.toString().getBytes());
			System.out.println(" done.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
