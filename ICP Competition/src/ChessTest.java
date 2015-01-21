
public class ChessTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queens piece = new Queens(3,4);

		for(int ii = 0; ii < piece.potentialMoves.size(); ii++)
		{
		System.out.print(piece.potentialMoves.get(ii).x);
		System.out.println(piece.potentialMoves.get(ii).y);
		}
		
	}

}
