package tetris;
import java.util.ArrayList;

public class Move{
	private int myShift;
	private int myRotation;
	private int myTime;
	private Move myNext;

	public Move(int shift, int rotation, int time) { //сдвиг, поворот, время
		myShift = shift;
		myRotation = rotation;
		myTime = time;
	}

	public Move(int shift, int rotation, int time, Move next) {
		this( shift, rotation, time );
		myNext = next;
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

	public String toString(){
		return myTime +": " +myShift +" "+ myRotation;
	}
}