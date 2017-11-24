package tetris;

/**
 * Created by 1 on 24.11.2017.
 */
public class Canvas {
    private byte[][] myPixels;
    private int myWidth;
    private int myHeight;

    public Canvas(int width, int height) {
        myWidth = width;
        myHeight = height;
        myPixels = new byte[myWidth][myHeight];
    }

    public int getWidth() {
        return myWidth;
    }

    public int getHeight() {
        return myHeight;
    }

    public void put(int x, int y) {
        myPixels[x][y] = 1;
    }

    public void set(byte[][] values) {
        for (int i = 0; i < values.length; i++) {
            byte[] sub = values[i];
            for (int j = 0; j < sub.length; j++) {
                myPixels[j][i] = sub[j];
            }
        }
    }

    public void print() {
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                res.append(myPixels[j][i]);
                res.append(" ");
            }
            res.append("\n");
        }
        System.out.println(res.toString());
    }

    public boolean canDraw( Canvas c, int x, int y ){
        for( int xi = 0; xi < c.myPixels.length; xi++ ){
            for( int yi = 0; yi < c.myPixels[ xi ].length; yi++ ){
                if( c.myPixels[ xi ][ yi ] != 0 ){
                    int toX = x + xi;
                    int toY = y + yi;
                    if( ( toX <0 )||( toX >=getWidth() )||( toY <0 )||( toY >=getHeight() ) ){
                        return false;
                    }
                    if( myPixels[ toX ][ toY ] != 0 ){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void draw( Canvas c, int x, int y ){
        for( int xi = 0; xi < c.myPixels.length; xi++ ){
            for( int yi = 0; yi < c.myPixels[ xi ].length; yi++ ){
                if( c.myPixels[ xi ][ yi ] != 0 ){
                    int toX = x + xi;
                    int toY = y + yi;
                    if( ( toX <0 )||( toX >=getWidth() )||( toY <0 )||( toY >=getHeight() ) ){
                        throw new RuntimeException( "can't draw outside" );
                    }
                    if( myPixels[ toX ][ toY ] != 0 ){
                        throw new RuntimeException( "pixel already exist" );
                    }
                    myPixels[ toX ][ toY ] = c.myPixels[ xi ][ yi ];
                }
            }
        }
    }

    /*
    Вопрос. если над строкой остаётся "кусок" ни с чем не связанный, то он же
    должен "падать" вниз или он остаётся "подвешенным"?
     */

    //public boolean canDownRow()

    public void putDownRow( int row ){
        for( int xi = 0; xi < myPixels.length; xi++ ){
            myPixels[ xi ][ row +1 ] = myPixels[ xi ][ row ];
        }
    }

    public boolean isFull( int row ){
        for( int xi = 0; xi < myPixels.length; xi++ ){
            if( myPixels[ xi ][ row ] == 0 ){
                return false;
            }
        }
        return true;
    }

    public void clearRow( int row ){
        for( int xi = 0; xi < myPixels.length; xi++ ){
            myPixels[ xi ][ row ] = 0;
        }
    }
}
