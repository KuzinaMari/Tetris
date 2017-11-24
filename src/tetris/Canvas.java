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
}
