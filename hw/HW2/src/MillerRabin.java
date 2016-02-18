import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

import javax.swing.text.html.InlineView;

public class MillerRabin {

	public static long powerMod(long x, long n, long m) {
		long prod = 1;
		while (n > 0) {
			if (n % 2 != 0) {
				prod = (prod * x) % m;
			}
			x = (x * x) % m;
			n /= 2;
		}

		return prod;
	}

	public static boolean millerRabin(long n, int k) {
		long ntemp = n % 2 == 0 ? n : n - 1;
		int s = Long.numberOfTrailingZeros(ntemp);
		long d = ntemp >> s;
		boolean doNextWitnessLoop = false;
		Random rnd = new Random();
		rnd.setSeed(Calendar.getInstance().getTimeInMillis());
		for (int i = 0; i < k; i++) {
			long a = Math.abs(rnd.nextLong() % ntemp) + 1;
			long x = powerMod(a, d, n);
			if (x == 1 || x == ntemp)
				continue;
			for (int j = 0; j < s - 1; j++) {
				x = powerMod(x, 2, n);
				if (x == 1)
					return false; // composite
				if (x == ntemp) {
					doNextWitnessLoop = true;
					break;
				}
			}
			if (!doNextWitnessLoop)
				return false; // composite
		}
		return true; // probably prime
	}


	public static void main(String[] args) throws FileNotFoundException {
		Scanner scn = new Scanner(new FileReader("hw2.dat"));
		while (scn.hasNext()) {
			long number = scn.nextLong();
			System.out.println(number + "    " + millerRabin(number, 10));			
		}
		scn.close();
	}

}
