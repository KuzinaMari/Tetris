package tetris;

/**
 * Created by 1 on 24.11.2017.
 */
public class Piece {

    private final Type myType;

    public Piece(Type type) {
        myType = type;
    }

    public Type getType() {
        return myType;
    }

    public int fieldWidth(){
        return getType().getFields()[0].getWidth();
    }
    public int fieldHeight(){
        return getType().getFields()[0].getHeight();
    }
}
