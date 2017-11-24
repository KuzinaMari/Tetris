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

        ArrayList<Piece> pieces = new ArrayList<>();
        pieces.add( new Piece( Type.O ) );
        pieces.add( new Piece( Type.I ) );
        pieces.add( new Piece( Type.Z ) );
        pieces.add( new Piece( Type.S ) );
        pieces.add( new Piece( Type.T ) );
        Game game = new Game( pieces, null );
        game.play();

        if( hh == 1 )
            return;

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

        public Canvas getField() {
            return myField;
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
            Piece piece = null;
            int rotation = 0;
            int px = 0; int py = 0;
            for( int time = 0; time < 20; time++ ){
                if( piece == null ){
                    if( myPieces.isEmpty() ){
                        throw new RuntimeException( "end of pieces" );
                    }
                    piece = myPieces.remove( 0 );
                    int startX = ( area.myField.getWidth() - piece.fieldWidth() ) / 2;
                    int startY = 1;
                    Canvas field = piece.getType().getFields()[ rotation ]; //здесь rotation
                      //это поворот с которым появляется фигура, то есть
                      //его видимо нужно хранить в piece
                    for( int s = -piece.fieldHeight(); s <=0; s++ ){
                        if( area.myField.canDraw( field, startX, s ) ){
                            startY = s;
                            break;
                        }
                    }
                    if( startY > 0 ){
                        throw new RuntimeException( "game over" );
                    }
                    area.getField().draw( field, startX, startY );
                    px = startX;
                    py = startY;
                }else{
                    Canvas field = piece.getType().getFields()[ rotation ];
                    area.getField().erase( field, px, py );
                    if( area.getField().canDraw( field, px, py +1 ) ){
                        py++;
                        area.getField().draw( field, px, py );
                    }else{
                        area.getField().draw( field, px, py );
                        checkFullRows();
                        piece = null;
                    }
                }
                System.out.println( time );
                area.getField().print();
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
