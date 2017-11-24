/**
 * Created by 1 on 26.10.2017.
 *
 * общий план
 *  - базовая механика игры
 *  - алгоритмы решателя
 *
 *  классы для механики игры
 *   - поле
 *   - фигурки
 *   - холстик для рисования
 *
 *
 *
 */

import tetris.Canvas;
import tetris.Piece;
import tetris.Type;

import java.util.ArrayList;

public class Tetris {
    public static void main(String[] arg){
        for (Type t: Type.values()){
            System.out.println( t.name() );
            for( Canvas c : t.getFields() ) {
                System.out.println("");
                c.print();
            }
        }
        int hh = 1;
        if( hh == 1 )
            return;
//        Area a = new Area (10, 20);
//        ArrayList<Piece> pieces = new ArrayList<>();
//        pieces.add( new Piece( Piece.Type.O ) );
//        pieces.add( new Piece( Piece.Type.I ) );
//        pieces.add( new Piece( Piece.Type.Z ) );
//        pieces.add( new Piece( Piece.Type.S ) );
//        pieces.add( new Piece( Piece.Type.T ) );
//        Game game = new Game( pieces );
//        int b = 5;

        Canvas c = new Canvas( 4, 3 );
        c.put( 1, 0 ); c.put( 2, 0 );
        c.put( 2, 1 ); c.put( 2, 2 );
        c.print();
        System.out.println("");
        c = new Canvas( 4, 3 );
        byte[][] v = {
                { 0, 1, 1, 0 },
                { 0, 0, 1, 0 },
                { 2, 0, 1, 0 },
        };
        c.set( v );
        c.print();
    }


    public static class  Area{
        private Canvas myField;
        public Area( int height, int width ){
            myField = new Canvas( width, height );
        }
    }

    public static class Game{
        private ArrayList<Piece> myPieces;

        public Game(ArrayList<Piece> myPieces) {
            this.myPieces = myPieces;
        }
    }


}
