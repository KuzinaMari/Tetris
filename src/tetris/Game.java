package tetris;
import java.util.ArrayList;
// Класс динамики игры
//
public class Game{
	private ArrayList<Piece> myPieces;
	private Move currentMove;
	private Area area;
	private ISolver mySolver;

	public Game(ArrayList<Piece> pieces, Move move) {
		myPieces = pieces;
		currentMove = move;
		area = new Area( 5, 7 );//произвольные размеры стакана
	}

	public void setArea( Area area ){
		this.area = area;
	}

	public void setSolver( ISolver solver ){
		mySolver = solver;
	}
//метод удаления ряда со сдвигом всех деталей вниз, если ряд заполнен
	public void checkFullRows(){
		boolean wasFull = false;
		Canvas c = area.getField();
		do {
			wasFull = false;
			for( int row = c.getHeight() - 1; row >= 0; row-- ){
				if (c.isFull(row)) {
					wasFull = true;
					for (int row2 = row - 1; row2 >= 0; row2--) {
						c.putDownRow(row2);
					}
					c.clearRow(0);
				}
			}
		}while( wasFull );
	}

	public void play(){
		Piece piece = null;//фигурка
		int rotation = 0;// поворот кусочка
		int px = 0; int py = 0;
		Canvas lastField = null;
		boolean appeared = false;
		for( int time = 0; time < 40; time++ ){//установлено произвольное время падения фигурки
			if( piece == null ){
				if( myPieces.isEmpty() ){
					throw new RuntimeException( "end of pieces" );
				}
				piece = myPieces.remove( 0 );
				int startX = ( area.myField.getWidth() - piece.fieldWidth() ) / 2;//фигурка появляется в центре
				int startY = 1;// в центре наверху
				rotation = 0;//без поворота
				Canvas field = piece.getType().getFields()[ rotation ];
				for( int s = -piece.fieldHeight(); s <=0; s++ ){
					if( area.myField.canDraw( field, startX, s ) ){
						startY = s;
						break;
					}
				}
				if( startY > 0 ){//
					throw new RuntimeException( "game over" );
				}
				lastField = field;
				mySolver.newPieceHandler( area, piece, startX, startY, time, currentMove );
				currentMove = currentMove.getNext();
				area.getField().draw( field, startX, startY );
				px = startX;
				py = startY;
				appeared = true;
			}else{
				Canvas field = piece.getType().getFields()[ rotation ];
				area.getField().erase( lastField, px, py );
				while( currentMove.getNext() != null && currentMove.getTime() == time ){
					currentMove = currentMove.getNext();
				}
				rotation = currentMove.getRotation();
				px = currentMove.getShift();
				lastField = field;
				int newPy = appeared ? py : py + 1;
				if( area.getField().canDraw( field, px, newPy ) ){
					py = newPy;
					area.getField().draw( field, px, py );
				}else{
					area.getField().draw( field, px, py );
					checkFullRows();
					piece = null;
				}
				appeared = false;
			}
			System.out.println( time );
			area.getField().print();
		}
	}
}