import java.util.ArrayList;
import java.util.Scanner;


public class Fractions {

	
	static Scanner console = new Scanner(System.in);
	
	static ArrayList<Integer> whole = new ArrayList<Integer>();

	static ArrayList<Integer> rem = new ArrayList<Integer>();

	static ArrayList<Integer> nums = new ArrayList<Integer>();

	static ArrayList<Integer> denoms = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter numbers (Enter 0 0 to finish entering): ");
		String input = console.nextLine();
		while(!input.equals("0 0")){
			Scanner lineScan = new Scanner(input);
			int num = lineScan.nextInt();
			int denom = lineScan.nextInt();
			
			nums.add(num);
			denoms.add(denom);
			whole.add(num/denom);
			rem.add(num % denom);
			input = console.nextLine();
		}
		
		for(int ii = 0; ii < nums.size(); ii++){
			System.out.println(whole.get(ii) + " " + rem.get(ii) + "/" + denoms.get(ii));
		}
		
	}

}
