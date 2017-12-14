import tetris.*;

import java.util.ArrayList;

public class Solver {
	public static void main(String[] arg){
		//assert false;
		// System.out.println( "hell dsssssadsvdddfo" );

		// Tetris.Area area = new Tetris.Area( 5, 7 );
		// Canvas field = area.getField();
		// Piece piece = new Piece( Type.O );

		// piece.getType().getFields()[ 0 ].print();

		// area.getField().print();


		// int startX = ( field.getWidth() - piece.fieldWidth() ) / 2;
		// int startY = 1;
                    // Canvas field = piece.getType().getFields()[ rotation ]; //здесь rotation
                    //   //это поворот с которым появляется фигура, то есть
                    //   //его видимо нужно хранить в piece
                    // for( int s = -piece.fieldHeight(); s <=0; s++ ){
                    //     if( area.myField.canDraw( field, startX, s ) ){
                    //         startY = s;
                    //         break;
                    //     }
                    // }
                    // if( startY > 0 ){
                    //     throw new RuntimeException( "game over" );
                    // }
                    // area.getField().draw( field, startX, startY );

				ArrayList<Piece> pieces = new ArrayList<>();
				pieces.add( new Piece( Type.O ) );
				pieces.add( new Piece( Type.I ) );
				pieces.add( new Piece( Type.Z ) );
				pieces.add( new Piece( Type.S ) );
				pieces.add( new Piece( Type.T ) );
				Game game = new Game( pieces, new Move( 2, 0, 4, 
					new Move( 3, 0, 9,
					new Move( 1, 1, 16,
					new Move( -1, 1, 17,
					new Move( 0, 0, 22,
					new Move( 1, 1, 29 ) ) ) ) ) ) );
				game.play();

	}
}