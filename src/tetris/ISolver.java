package tetris;

import tetris.*;
import java.util.*;

public interface ISolver{
	Move getMoves( Area area, List<Piece> pieces );
	void newPieceHandler( Area area, Piece newPiece, int x, int y );
}