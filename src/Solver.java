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
		public boolean rotation( int index ){
			boolean res = false;
			switch( index ){
				case 0: res = rotation1; break;
				case 1: res = rotation2; break;
				case 2: res = rotation3; break;
				case 3: res = rotation4; break;
				default: throw new RuntimeException( "illegal index" );
			}
			return res;
		}
		public void setRotation( int index, boolean value ){
			switch( index ){
				case 0: rotation1 = value; break;
				case 1: rotation2 = value; break;
				case 2: rotation3 = value; break;
				case 3: rotation4 = value; break;
				default: throw new RuntimeException( "illegal index" );
			}
		}
		public void resetRotations(){
			rotation1 = false;
			rotation2 = false;
			rotation3 = false;
			rotation4 = false;
		}
		public String toString(){
			return ( rotation1 ? "1" : "0" ) + ( rotation2 ? "1" : "0" ) 
				+ ( rotation3 ? "1" : "0" ) + ( rotation4 ? "1" : "0" ) ;
		}
		public boolean possible(){
			return rotation1 || rotation2 || rotation3 || rotation4;
		}
		public int rotationIndex(){
			if( rotation1 ){
				return 0;
			}else if( rotation2 ){
				return 1;
			}else if( rotation3 ){
				return 2;
			}else if( rotation4 ){
				return 3;
			}
			throw new RuntimeException( "impossible position" );
		}
	}

	private Position[][] myPositions; //state of solver
	private Piece myPiece;
	private List<Position> myFinalPositions = new ArrayList<Position>();
	private Position myFinalPosition;
	private List<Position> myWay;

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
		Position max = null;
		int maxRating = -1000000;
		for( Position pos : myFinalPositions ){
			if( max == null ){
				max = new Position();
				max.x = pos.x;
				max.y = pos.y;
			}
			int[] ratings = new int[ 4 ];
			for( int i =0; i < 4; i++ )
				if( pos.rotation( i ) ) ratings[ i ] = rating( pos.x, pos.y, i );
			String rating = "[";
			for( int i =0; i < 4; i++ )
				if( pos.rotation( i ) ){ 
					if( ratings[ i ] > maxRating ){
						maxRating = ratings[ i ];
						max.x = pos.x;
						max.y = pos.y;
						max.resetRotations();
						max.setRotation( i, true );
					}
					rating += ratings[ i ] +" ";
				}
			rating += "]";
			sb.append( pos.x +" "+ pos.y +" "+ rating +" "+ pos.toString() +"\n" );
		}
		myFinalPosition = max;
		log( sb.toString() );
		log( max.x +" "+ max.y +" "+ maxRating +" "+ max.toString() +"\n" );
	}


	public void newPieceHandler( Area area, Piece newPiece, int x, int y ){
		log( "new piece " +x +" "+ y );
		myArea = area;
		myPiece = newPiece;
		resetPositions();
		possiblePositions( getPosition( x, y ), newPiece );
		printPositions();
		finalPositions();
		Position start = new Position();
		start.x = x; start.y = y; start.rotation1 = true;
		buildWay( start );
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
		return rows *100 +y - emptiness *50 +neighbors +(int)Math.round( Math.random() *10 -5 );
	}

	private boolean wayFound;
	private void buildWay( Position start ){
		if( myWay == null ) myWay = new ArrayList<Position>();
		myWay.clear();
		myWay.add( start );
		wayFound = false;
		buildWay2();
		myWay.add( myFinalPosition );
		StringBuilder sb = new StringBuilder();
		sb.append( "way\n" );
		for( Position pos : myWay ){
			sb.append( pos.x +" "+ pos.y +" "+ pos.toString() +"\n" );
		}
		log( sb.toString() );
	}

	int[][] neighbors1 = {
		{ 0, 1 },
		{ -1, 0 },
		{ 1, 0 },
	};
	int[][] neighbors2 = {
		{ -1, 0 },
		{ 0, 1 },
		{ 1, 0 },
	};
	int[][] neighbors3 = {
		{ 1, 0 },
		{ 0, 1 },
		{ -1, 0 },
	};
	int[][] neighbors;
	private void buildWay2(){
		Position last = myWay.get( myWay.size() -1 );
		//log( last.x +" "+ last.y +" "+ last.toString() );
		if( last.x < myFinalPosition.x ){
			neighbors = neighbors3;
		}else if( last.x > myFinalPosition.x ){
			neighbors = neighbors2;
		}else{
			neighbors = neighbors1;
		}
		for( int[] shift : neighbors ){
			int x = last.x + shift[ 0 ];
			int y = last.y + shift[ 1 ];
			if( possibleCoordinates( x, y ) ){
				boolean wasHere = false;
				for( Position p : myWay ){
					if( p.x == x && p.y == y ){
						wasHere = true;
						break;
					}
				}
				if( wasHere ){
					continue;
				}
				Position pos = getPosition( x, y );
				//if( pos.rotation( last.rotationIndex() ) ){
				if( pos.possible() ){
					if( myFinalPosition.x == pos.x && myFinalPosition.y == pos.y ){
						wayFound = true;
						return;
					}
					myWay.add( pos );
					buildWay2();
					if( wayFound ){
						return;
					}else{
						myWay.remove( myWay.size() -1 );
					}
				}
			}
		}
	}

}