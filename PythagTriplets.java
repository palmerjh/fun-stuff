import java.util.*;
public class PythagTriplets {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("A-value start? ");
		int aMin = keyboard.nextInt();
		System.out.print("A-value limit? ");
		int aMax = keyboard.nextInt();
		System.out.print("B-value start? ");
		int bMin = keyboard.nextInt();
		System.out.print("B-value limit? ");
		int bMax = keyboard.nextInt();
		System.out.println();
		
		int aRange = aMax - aMin, bRange = bMax - bMin;
		String[][] pythagTrips = new String[aRange + 1][bRange + 1];
		
		for (int a = aMin; a <= aMax; a++) {
			for (int b = bMin; b <= bMax; b++) {
				int r =  b * b + a * a;
				int x = Math.abs(r - 2 * a * a);
				int y = 2 * b * a;
				int[] triplet = {x, y, r};
				int num, denom;
				//r /= gcf; x /= gcf; y /= gcf;
				if (a == b) {
					triplet[0] = 0; triplet[1] = 1; triplet[2] = 1;
					num = 1;
					denom = 2 * a;
				} else {
					int gcf = getGCF(triplet);
					triplet[0] /= gcf; triplet[1] /= gcf; triplet[2] /= gcf;
					int[] numbers = {a, gcf};
					int g = getGCF(numbers);
					num = a / g; denom = gcf / g;					
				}
				
				pythagTrips[a - aMin][b - bMin] = String.format("(%d, %d, %d)", triplet[0], triplet[1], triplet[2]);
				System.out.printf("%d\t%d\t%s\t%d", a, b, pythagTrips[a - aMin][b - bMin], num);
				if (denom != 1) {
					System.out.println("/" + denom);
				} else {
					System.out.println();
				}
			}
			System.out.println();
		}
		
		String allUniqueTrips = pythagTrips[0][0];
	
		for (int i = 2; i <= (aRange + 1) * (bRange + 1); i++) {
			boolean unique = true;
			int a = (i - 1) / (bRange + 1) + aMin - 1, b = (i - 1) % (bRange + 1) + bMin - 1;
			if (!(a > b && a < bMax && a >= bMin - 1 && b < aMax && b >= aMin)) {
				for (int j = i - 1; j > 0; j--) {
					if (pythagTrips[a + 1 - aMin][b + 1 - bMin].equals(pythagTrips[(j - 1) / (bRange + 1)][(j - 1) % (bRange + 1)])) {
						unique = false;
					} 				
				}
			} else {
				unique = false;
			}
			if (unique) {
				allUniqueTrips +=  "-" + pythagTrips[a + 1 - aMin][b + 1 - bMin];
			}
		}
		
		
		String[] uniquePythagoreanTriplets = allUniqueTrips.trim().split("-");
		
		for (String x:uniquePythagoreanTriplets) {
			System.out.println(x);
		}
		System.out.print(uniquePythagoreanTriplets.length);
	}

	public static int getGCF(int[] numberSet) {
		Arrays.sort(numberSet);
		for (int i = numberSet[0]; i > 1; i--) {
			boolean divisible = true;
			for (int j = 0; j < numberSet.length; j++) {
				if (numberSet[j] % i != 0) {
					divisible = false;
				}
			}
			if (divisible) {
				return i;
			}
		}
		return 1;
	}

}
