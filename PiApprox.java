import java.util.*;
public class PiApprox {

	public static final int limit = 100000000;
	public static void main(String[] args) {
		Random rand = new Random();
		int circle = 0;
		for (int i = 1; i <= limit; i++) {
			double x = rand.nextDouble();
			double y = rand.nextDouble();
			if (x*x + y*y <= 1) {
				++circle;
			}
		}
		System.out.println(4 * (circle + 0.0)/limit);

	}

}
