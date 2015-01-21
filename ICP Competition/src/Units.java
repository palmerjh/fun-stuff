import java.util.Scanner;


public class Units {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int nUnits = keyboard.nextInt();
		String[] unitNames = new String[nUnits];
		for(int i = 0; i < unitNames.length; i++) {
			unitNames[i] = keyboard.next();
		}
		
		//keyboard.nextLine();
		int[] coeffs = new int[nUnits - 1];
		for(int i = 0; i < coeffs.length; i++) {
			while(!keyboard.hasNextInt()) {
				keyboard.next();
			}
			coeffs[i] = keyboard.nextInt();
		}
		
		int[] relationships = new int[nUnits - 1];
		for(int i = 0; i < relationships.length; i++) {
			relationships[i] = 1;
			for (int j = i; j >= 0; j--) {
				relationships[i] *= coeffs[j];
			}
		}
		
		System.out.printf("1%s", unitNames[0]);
		for(int i = 0; i < nUnits - 1; i++) {
			System.out.printf(" = %d%s", coeffs[i], unitNames[i+1]);
		}

	}

}
