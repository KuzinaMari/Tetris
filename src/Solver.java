import tetris.*;

import java.util.*;

public class Solver implements ISolver{

	public static void log( String msg ){
		System.out.println( msg );
	}

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
				ISolver solver = new Solver();
				Area area = new Area( 5, 7 );
				Game game = new Game( pieces, solver.getMoves( area, pieces ) );
				game.setArea( area );
				game.setSolver( solver );
				// Game game = new Game( pieces, new Move( 2, 0, 4, 
				// 	new Move( 3, 0, 9,
				// 	new Move( 1, 1, 16,
				// 	new Move( -1, 1, 17,
				// 	new Move( 0, 0, 22,
				// 	new Move( 1, 1, 29 ) ) ) ) ) ) );
				game.play();

	}

	public Solver(){
		myPositions = new Position[ myArea.getField().getWidth() +1 ][ myArea.getField().getHeight() +1 ];
		for( int x =0; x< myPositions.length; x++ ){
			for( int y =0; y< myPositions[ x ].length; y++ ){
				Position pos = new Position();
				pos.x = x -2;
				pos.y = y -2;
				myPositions[ x ][ y ] = pos;
			}
		}
		// for( int x =-2; x< myArea.getField().getWidth() -2; x++ ){
		// 	for( int y =-2; y< myArea.getField().getHeight() -2; y++ ){

		// 	}
		// }
		printPositions();
	}

	private Position getPosition( int x, int y ){
		return myPositions[ x +2 ][ y +2 ];
	}

	private Area myArea = new Area( 5, 7 );

	public Move getMoves( Area area, List<Piece> pieces ){
		myArea = area;
		return new Move( 2, 0, 4, 
					new Move( 3, 0, 9,
					new Move( 1, 1, 16,
					new Move( -1, 1, 17,
					new Move( 0, 0, 22,
					new Move( 1, 1, 29 ) ) ) ) ) );
	}

	public void printPositions(){
		StringBuilder sb = new StringBuilder();
		for( int y =0; y< myPositions[ 0 ].length; y++ ){
			for( int x =0; x< myPositions.length; x++ ){
				sb.append( myPositions[ x ][ y ].toString() + " " );
			}
			sb.append( "\n" );
		}
		System.out.print( sb.toString() );
	}

	public static class Position{
		int x;
		int y;
		boolean rotation1;
		boolean rotation2;
		boolean rotation3;
		boolean rotation4;
		public String toString(){
			return ( rotation1 ? "1" : "0" ) + ( rotation2 ? "1" : "0" ) 
				+ ( rotation3 ? "1" : "0" ) + ( rotation4 ? "1" : "0" ) ;
		}
		public boolean possible(){
			return rotation1 || rotation2 || rotation3 || rotation4;
		}
	}

	private Position[][] myPositions; //state of solver
	private Piece myPiece;
	private List<Position> myFinalPositions = new ArrayList<Position>();

	private boolean possibleCoordinates( int x, int y ){
		return ( x >= -2 ) && ( x < myArea.getField().getWidth() -1 )
			&& ( y >= -2 ) && ( y < myArea.getField().getHeight() -1 );
	}

	public void possiblePositions( Position current, Piece piece ){
		//log( ""+ current.x + " " + current.y + " " + current.possible() );
		if( current.possible() ){
			return;
		}
		if( myArea.getField().canDraw( piece.getType().getFields()[ 0 ], current.x, current.y ) ){
			current.rotation1 = true;
		}
		if( myArea.getField().canDraw( piece.getType().getFields()[ 1 ], current.x, current.y ) ){
			current.rotation2 = true;
		}
		if( myArea.getField().canDraw( piece.getType().getFields()[ 2 ], current.x, current.y ) ){
			current.rotation3 = true;
		}
		if( myArea.getField().canDraw( piece.getType().getFields()[ 3 ], current.x, current.y ) ){
			current.rotation4 = true;
		}
		if( current.possible() ){
			int nextX, nextY;
			nextX = current.x -1;
			nextY = current.y;
			if( possibleCoordinates( nextX, nextY ) ){
				possiblePositions( getPosition( nextX, nextY ), piece );
			}
			nextX = current.x +1;
			nextY = current.y;
			if( possibleCoordinates( nextX, nextY ) ){
				possiblePositions( getPosition( nextX, nextY ), piece );
			}
			nextX = current.x;
			nextY = current.y +1;
			if( possibleCoordinates( nextX, nextY ) ){
				possiblePositions( getPosition( nextX, nextY ), piece );
			}
		}
	}

	public void finalPositions(){
		myFinalPositions.clear();
		for( int x =0; x< myPositions.length; x++ ){
			for( int y =0; y< myPositions[ x ].length; y++ ){
				Position pos = myPositions[ x ][ y ];
				Position finalPos = new Position();
				finalPos.x = pos.x;
				finalPos.y = pos.y;
				boolean bottom = y == myPositions[ x ].length -1;
				Position below = bottom ? null : myPositions[ x ][ y +1 ];
				finalPos.rotation1 = pos.rotation1 && ( bottom || !below.rotation1 );
				finalPos.rotation2 = pos.rotation2 && ( bottom || !below.rotation2 );
				finalPos.rotation3 = pos.rotation3 && ( bottom || !below.rotation3 );
				finalPos.rotation4 = pos.rotation4 && ( bottom || !below.rotation4 );
				if( finalPos.possible() ){
					myFinalPositions.add( finalPos );
				}
			}
		}
		StringBuilder sb = new StringBuilder( "finals:\n" );
		for( Position pos : myFinalPositions ){
			String rating = "[";
			if( pos.rotation1 ) rating += rating( pos.x, pos.y, 0 ) +" ";
			if( pos.rotation2 ) rating += rating( pos.x, pos.y, 1 ) +" ";
			if( pos.rotation3 ) rating += rating( pos.x, pos.y, 2 ) +" ";
			if( pos.rotation4 ) rating += rating( pos.x, pos.y, 3 ) +" ";
			rating += "]";
			sb.append( pos.x +" "+ pos.y +" "+ rating +" "+ pos.toString() +"\n" );
		}
		log( sb.toString() );
	}


	public void newPieceHandler( Area area, Piece newPiece, int x, int y ){
		log( "new piece " +x +" "+ y );
		myArea = area;
		myPiece = newPiece;
		resetPositions();
		possiblePositions( getPosition( x, y ), newPiece );
		printPositions();
		finalPositions();
	}

	public void resetPositions(){
		for( int x =0; x< myPositions.length; x++ ){
			for( int y =0; y< myPositions[ x ].length; y++ ){
				Position pos = myPositions[ x ][ y ];
				pos.rotation1 = false;
				pos.rotation2 = false;
				pos.rotation3 = false;
				pos.rotation4 = false;
			}
		}
	}

	public int rating( int x, int y, int rotation ){
		Canvas field = myPiece.getType().getFields()[ rotation ];
		Canvas area = myArea.getField();
		int rows = 0;
		area.draw( field, x, y );
		for( int i =y; i < y+ field.getHeight(); i++ ){
			if( area.isFull( i ) ){
				rows++;
			}
		}
		int emptiness = 0;
		for( int px =0; px < field.getWidth(); px++ ){
			int pixelY = -1;
			for( int py = field.getHeight() -1; py >= 0; py-- ){
				if( field.pixel( px, py ) ){
					pixelY = py;
					//log( "pixelY "+ x +" "+ y +" "+ rotation +" "+ pixelY );
					break;
				}
			}
			if( pixelY >= 0 ){
				pixelY += y;
				if( ( pixelY < area.getHeight() -1 ) && !area.pixel( x + px, pixelY +1 ) ){
					emptiness++;
				}
			}
		}
		int neighbors = 0;
		for( int px =0; px < field.getWidth(); px++ ){
			for( int py =0; py < field.getHeight(); py++ ){
				if( field.pixel( px, py ) ){
					int pixelX = x + px;
					int pixelY = y + py;
					for( int nx = pixelX -1; nx <= pixelX +1; nx++ )
						for( int ny = pixelY -1; ny <= pixelY +1; ny++ )
							if( ( nx < 0 ) || ( nx >= area.getWidth() )
								|| ( ny < 0 ) || ( ny >= area.getHeight() ) 
								|| ( area.pixel( nx, ny ) ) ){
								neighbors++;
							}
				}
			}
		}
		area.erase( field, x, y );
		return rows *100 +y - emptiness *100 +neighbors;
	}

}