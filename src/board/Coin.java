package board;

import java.io.Serializable;

public class Coin implements Serializable {

	private static final long serialVersionUID = 3L;
	public int X, Y;
	public String point_name;
	public boolean tiger = false;
	public boolean goat = false;
	public boolean vacant = true;
	public Coin left, right, top, bottom, left_top, left_bottom, right_top, right_bottom;

	Coin() {
	}

	Coin(Coin toBeCloned) {

		this.X = toBeCloned.X;
		this.Y = toBeCloned.Y;
		this.point_name = toBeCloned.point_name;
		this.tiger = toBeCloned.tiger;
		this.goat = toBeCloned.goat;
		this.vacant = toBeCloned.vacant;

	}

	Coin(int x, int y, String name) {// Constructor

		X = x;
		Y = y;
		point_name = name;

	}

	public void direction(Coin L, Coin R, Coin T, Coin B, Coin LT, Coin LB, Coin RT, Coin RB) {

		left = L;
		right = R;
		top = T;
		bottom = B;
		left_top = LT;
		left_bottom = LB;
		right_top = RT;
		right_bottom = RB;
	}

	@Override
	public String toString() {
		return "ID: " + point_name + "goat: " + goat + " tiger: " + tiger + " vacant: " + vacant;
	}

}