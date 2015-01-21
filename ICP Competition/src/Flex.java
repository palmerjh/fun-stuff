import java.util.*;
import java.io.*;
public class Flex {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int width = keyboard.nextInt();
		int nPartitions = keyboard.nextInt();
		
		int[] partitions = new int[nPartitions+1];
		for(int i = 0; i < nPartitions; i++) {
			partitions[i] = keyboard.nextInt();
		}
		partitions[nPartitions] = width;
		
		int nPermutations = (nPartitions + 1)*(nPartitions + 2)/2;
		int[] permutations = new int[nPermutations];
		
		int i = 0;
		for(; i < partitions.length; i++) {
			permutations[i] = partitions[i];
		}
		
		for(int curPart = 0; curPart < partitions.length - 1; curPart++) {
			for(int nextPart = curPart + 1; nextPart < partitions.length; nextPart++, i++) {
				permutations[i] = partitions[nextPart] - partitions[curPart];
			}
		}
		
		sort(permutations);
		
		for(int j = 0; j < permutations.length-1; j++) {
			boolean repeat = false;
			for(int k = j+1; k < permutations.length; k++) {
				if(permutations[j] == permutations[k]) {
					repeat = true;
				}
			}
			if (!repeat) {
				System.out.print(permutations[j] + " ");
			}
		}
		
		System.out.print(permutations[permutations.length-1]);

	}

	public static void sort(int[] permutations) {
		boolean sorted = false;
		while (!sorted) {
			sorted = true;
			for(int i = 0; i < permutations.length - 1; i++) {
				if (permutations[i] > permutations[i+1]) {
					int temp = permutations[i];
					permutations[i] = permutations[i+1];
					permutations[i+1] = temp;
					sorted = false;
				}
			}
		}
		
		
	}

}
