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

import java.util.ArrayList;

public class Tetris {
    public static void main(String[] arg){
        Area a = new Area (10, 20);
        ArrayList<Piece> pieces = new ArrayList<>();
        pieces.add( new Piece( Piece.Type.O ) );
        pieces.add( new Piece( Piece.Type.I ) );
        pieces.add( new Piece( Piece.Type.Z ) );
        pieces.add( new Piece( Piece.Type.S ) );
        pieces.add( new Piece( Piece.Type.T ) );
        Game game = new Game( pieces );
        int b = 5;

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

    public static class Canvas{
        private byte[][] myPixels;
        private int myWidth;
        private int myHeight;

        public Canvas(int width, int height) {
            myWidth = width;
            myHeight = height;
            myPixels = new byte[ myWidth ][ myHeight ];
        }

        public int getWidth() {
            return myWidth;
        }

        public int getHeight() {
            return myHeight;
        }

        public void put( int x, int y ){
            myPixels[ x ][ y ] = 1;
        }

        public void set( byte[][] values ){
            for( int i = 0; i < values.length; i++ ) {
                byte[] sub = values[i];
                for (int j = 0; j < sub.length; j++) {
                    myPixels[ j ][ i ] = sub[ j ];
                }
            }
        }

        public void print(){
            StringBuffer res = new StringBuffer();
            for( int i = 0; i < myHeight; i++ ){
                for( int j = 0; j < myWidth; j++ ){
                    res.append( myPixels[ j ][ i ] );
                    res.append( " " );
                }
                res.append( "\n" );
            }
            System.out.println( res.toString() );
        }
    }


    public static class  Area{
        private Canvas myField;
        public Area( int height, int width ){
            myField = new Canvas( width, height );
        }
    }
    public static class Piece{
        public enum Type{
            L {
                protected Canvas[] createFields(){
                    Canvas[] res = new Canvas[ 4 ];
                    Canvas c;
                    c = new Canvas( 3, 3 );
                    byte[][] v1 = {
                            { 0, 1, 0 },
                            { 0, 1, 0 },
                            { 0, 1, 1 },
                    };
                    c.set( v1 );
                    res[ 0 ] = c;
                    c = new Canvas( 3, 3 );
                    byte[][] v2 = {
                            { 0, 0, 0 },
                            { 1, 1, 1 },
                            { 1, 0, 0 },
                    };
                    c.set( v2 );
                    res[ 1 ] = c;
                    return res;
                }
            }, //фигура буквой г
            I {
                protected Canvas[] createFields(){
                    return null;
                }
            },
            Z {
                protected Canvas[] createFields(){
                    return null;
                }
            },
            T {
                protected Canvas[] createFields(){
                    return null;
                }
            },
            O {
                protected Canvas[] createFields(){
                    return null;
                }
            },//квадрат
            J {
                protected Canvas[] createFields(){
                    return null;
                }
            }, //обратная г
            S {
                protected Canvas[] createFields(){
                    return null;
                }
            }; // обратная z

            private final Canvas[] myFields;
            Type(){
                myFields = createFields();
            }
            protected abstract Canvas[] createFields();
        }
        private final Type myType;
        public Piece( Type type ){
            myType = type;
        }
    }
    public static class Game{
        private ArrayList<Piece> myPieces;

        public Game(ArrayList<Piece> myPieces) {
            this.myPieces = myPieces;
        }
    }


}
