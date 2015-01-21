import java.util.ArrayList;


public class Queens {

	public Snippet position;
	public ArrayList<Snippet> potentialMoves = new ArrayList<Snippet>();
	
	public Queens(double row, double col) {
		position = new Snippet(row, col);
		for(int ii = 1; ii <= 8; ii++){
			potentialMoves.add(new Snippet(row, ii));
		}
		for(int ii = 1; ii <= 8; ii++){
			potentialMoves.add(new Snippet(ii, col));

		}
		double tmpRow = row, tmpCol = col;
		for(; tmpRow < 9 && tmpCol < 9; tmpRow++, tmpCol++) {
			potentialMoves.add(new Snippet(tmpRow, tmpCol));
		}
		
		tmpRow = row ; tmpCol = col;
		for(; tmpRow < 9 && tmpCol < 9; tmpRow++, tmpCol--) {
			potentialMoves.add(new Snippet(tmpRow, tmpCol));
		}
		
		//tmpRow = row - 1; tmpCol = col -1;
		//for(; tmpRow < 9 && tmpCol < 9; tmpRow--, tmpCol--) {
			//potentialMoves.add(new Snippet(tmpRow, tmpCol));
		//}
		
		tmpRow = row; tmpCol = col;
		for(; tmpRow < 9 && tmpCol < 9; tmpRow--, tmpCol++) {
			potentialMoves.add(new Snippet(tmpRow, tmpCol));
		}
	}
		
}


