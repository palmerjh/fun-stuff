import java.util.Scanner;


public class Mosquito {

	int counter;
	Points location;
	
	public Mosquito(int x, int y)
	{
		location = new Points(x, y);
		counter = 0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner console = new Scanner(System.in);
		int numEntries = console.nextInt();
		for(int ii = 0; ii < numEntries; ii++){
			console.nextLine();
			String lineScan = console.nextLine();
		}
	}

}