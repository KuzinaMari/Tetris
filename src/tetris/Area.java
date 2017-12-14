package tetris;

public class Area{
	Canvas myField;
	public Area( int width, int height ){
		myField = new Canvas( width, height );
	}

	public Canvas getField() {
		return myField;
	}
}