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
        int hh = 1;
        assert !Type.I.getFields()[0].canDraw( Type.O.getFields()[0], 0, 0 );
        assert Type.I.getFields()[0].canDraw( Type.O.getFields()[0], 2, 0 );
        assert !Type.I.getFields()[0].canDraw( Type.O.getFields()[0], -1, -1 );

        Canvas c = new Canvas( 4, 5 );
        byte[][] v3 = {
                { 0, 1, 1, 0 },
                { 0, 0, 1, 1 },
                { 2, 1, 1, 1 },
                { 2, 1, 1, 1 },
                { 2, 1, 0, 0 },
        };
        c.set( v3 );
        c.print();
        boolean wasFull = false;
        do {
            wasFull = false;
            for (int row = c.getHeight() - 1; row >= 0; row--) {
                if (c.isFull(row)) {
                    wasFull = true;
                    for (int row2 = row - 1; row2 >= 0; row2--) {
                        c.putDownRow(row2);
                    }
                    c.clearRow(0);
                }
            }
        }while( wasFull );
        c.print();

        if( hh == 1 )
            return;
        c = new Canvas( 4, 3 );
        byte[][] v = {
                { 0, 1, 1, 0 },
                { 0, 0, 0, 0 },
                { 2, 0, 0, 0 },
        };
        c.set( v );
        c.print();
        c.draw( Type.O.getFields()[0], 2, 1 );
        c.print();
        c.putDownRow(1);
        c.print();
        if( hh == 1 )
            return;
        for (Type t: Type.values()){
            System.out.println( t.name() );
            for( Canvas c2 : t.getFields() ) {
                System.out.println("");
                c2.print();
            }
        }
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

        c = new Canvas( 4, 3 );
        c.put( 1, 0 ); c.put( 2, 0 );
        c.put( 2, 1 ); c.put( 2, 2 );
        c.print();
        System.out.println("");
        c = new Canvas( 4, 3 );
        byte[][] v2 = {
                { 0, 1, 1, 0 },
                { 0, 0, 1, 0 },
                { 2, 0, 1, 0 },
        };
        c.set( v2 );
        c.print();
    }
    //public static void assert()


    public static class  Area{
        private Canvas myField;
        public Area( int width, int height ){
            myField = new Canvas( width, height );
        }
    }

    public static class Game{
        private ArrayList<Piece> myPieces;
        private Move currentMove;
        private Area area;

        public Game(ArrayList<Piece> pieces, Move move) {
            myPieces = pieces;
            currentMove = move;
            area = new Area( 5, 7 );
        }

        public void play(){
            for( int time = 0; time < 20; time++ ){
                
            }
        }
    }

    public static class Move{
        private int myShift;
        private int myRotation;
        private int myTime;
        private Move myNext;

        public Move(int shift, int rotation, int time) {
            myShift = shift;
            myRotation = rotation;
            myTime = time;
        }

        public int getShift() {
            return myShift;
        }

        public int getRotation() {
            return myRotation;
        }

        public int getTime() {
            return myTime;
        }

        public Move getNext() {
            return myNext;
        }

        public void setNext(Move myNext) {
            this.myNext = myNext;
        }
    }


}
