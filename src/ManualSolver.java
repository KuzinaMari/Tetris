import tetris.*;
import java.util.*;
//класс ручного решателя для экспериментов 
public class ManualSolver implements ISolver{
	public Move getMoves( Area area, List<Piece> pieces ){
		return new Move( 2, 0, 4, 
					new Move( 3, 0, 9,
					new Move( 1, 1, 16,
					new Move( -1, 1, 17,
					new Move( 0, 0, 22,
					new Move( 1, 1, 29 ) ) ) ) ) );
	}
	public void newPieceHandler( Area area, Piece newPiece, int x, int y, Move currentMove ){

	}
}