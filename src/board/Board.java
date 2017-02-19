package board;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ai.MiniMax;

public class Board implements Serializable {

	private static final long serialVersionUID = 1L;

	MiniMax MX = new MiniMax();

	public Coin[] p = new Coin[31];
	public boolean tigers_move = false;
	public boolean goatWon = false;
	public boolean tigerWon = false;

	public boolean retracted = false;

	public int goatKilled = 0;
	public int totalNoOfGoatOnTheBoard = 0;

	public Board nowBoard;
	public Board lastBoard;

	public static String player = "ÄäÃû";
	public int step = 0;

	public Board(int i) {

		p[0] = new Coin(250, 10, "0");
		p[1] = new Coin(350, 10, "1");
		p[2] = new Coin(450, 10, "2");

		p[3] = new Coin(250, 110, "3");
		p[4] = new Coin(350, 110, "4");
		p[5] = new Coin(450, 110, "5");

		p[6] = new Coin(150, 210, "6");
		p[7] = new Coin(250, 210, "7");
		p[8] = new Coin(350, 210, "8");
		p[9] = new Coin(450, 210, "9");
		p[10] = new Coin(550, 210, "10");

		p[11] = new Coin(150, 310, "11");
		p[12] = new Coin(250, 310, "12");
		p[13] = new Coin(350, 310, "13");
		p[14] = new Coin(450, 310, "14");
		p[15] = new Coin(550, 310, "15");

		p[16] = new Coin(150, 410, "16");
		p[17] = new Coin(250, 410, "17");
		p[18] = new Coin(350, 410, "18");
		p[19] = new Coin(450, 410, "19");
		p[20] = new Coin(550, 410, "20");

		p[21] = new Coin(150, 510, "21");
		p[22] = new Coin(250, 510, "22");
		p[23] = new Coin(350, 510, "23");
		p[24] = new Coin(450, 510, "24");
		p[25] = new Coin(550, 510, "25");

		p[26] = new Coin(150, 610, "26");
		p[27] = new Coin(250, 610, "27");
		p[28] = new Coin(350, 610, "28");
		p[29] = new Coin(450, 610, "29");
		p[30] = new Coin(550, 610, "30");

		directions();
		initialCoinPlacement();
		// gameStatus = new GameStatus();
		lastBoard = setStatus();

	}

	void initialCoinPlacement() {

		add_tiger(p[18]);
		add_goat(p[6]);
		add_goat(p[7]);
		add_goat(p[8]);
		add_goat(p[9]);
		add_goat(p[10]);
		add_goat(p[11]);
		add_goat(p[15]);
		add_goat(p[16]);
		add_goat(p[20]);
		add_goat(p[21]);
		add_goat(p[25]);
		add_goat(p[26]);
		add_goat(p[27]);
		add_goat(p[28]);
		add_goat(p[29]);
		add_goat(p[30]);

	}

	void directions() {

		p[0].direction(null, p[1], null, p[3], null, null, null, p[4]);
		p[1].direction(p[0], p[2], null, p[4], null, null, null, null);
		p[2].direction(p[1], null, null, p[5], null, p[4], null, null);
		p[3].direction(null, p[4], p[0], p[7], null, null, null, null);
		p[4].direction(p[3], p[5], p[1], p[8], p[0], p[7], p[2], p[9]);
		p[5].direction(p[4], null, p[2], p[9], null, null, null, null);
		p[6].direction(null, p[7], null, p[11], null, null, null, p[12]);
		p[7].direction(p[6], p[8], p[3], p[12], null, null, p[4], null);
		p[8].direction(p[7], p[9], p[4], p[13], null, p[12], null, p[14]);
		p[9].direction(p[8], p[10], p[5], p[14], p[4], null, null, null);
		p[10].direction(p[9], null, null, p[15], null, p[14], null, null);
		p[11].direction(null, p[12], p[6], p[16], null, null, null, null);
		p[12].direction(p[11], p[13], p[7], p[17], p[6], p[16], p[8], p[18]);
		p[13].direction(p[12], p[14], p[8], p[18], null, null, null, null);
		p[14].direction(p[13], p[15], p[9], p[19], p[8], p[18], p[10], p[20]);
		p[15].direction(p[14], null, p[10], p[20], null, null, null, null);
		p[16].direction(null, p[17], p[11], p[21], null, null, p[12], p[22]);
		p[17].direction(p[16], p[18], p[12], p[22], null, null, null, null);
		p[18].direction(p[17], p[19], p[13], p[23], p[12], p[22], p[14], p[24]);
		p[19].direction(p[18], p[20], p[14], p[24], null, null, null, null);
		p[20].direction(p[19], null, p[15], p[25], p[14], p[24], null, null);
		p[21].direction(null, p[22], p[16], p[26], null, null, null, null);
		p[22].direction(p[21], p[23], p[17], p[27], p[16], p[26], p[18], p[28]);
		p[23].direction(p[22], p[24], p[18], p[28], null, null, null, null);
		p[24].direction(p[23], p[25], p[19], p[29], p[18], p[28], p[20], p[30]);
		p[25].direction(p[24], null, p[20], p[30], null, null, null, null);
		p[26].direction(null, p[27], p[21], null, null, null, p[22], null);
		p[27].direction(p[26], p[28], p[22], null, null, null, null, null);
		p[28].direction(p[27], p[29], p[23], null, p[22], null, p[24], null);
		p[29].direction(p[28], p[30], p[24], null, null, null, null, null);
		p[30].direction(p[29], null, p[25], null, p[24], null, null, null);
	}

	public Board(Board toBeCopied) {
		this.tigers_move = toBeCopied.tigers_move;
		this.goatWon = toBeCopied.goatWon;
		this.tigerWon = toBeCopied.tigerWon;
		this.totalNoOfGoatOnTheBoard = toBeCopied.totalNoOfGoatOnTheBoard;
		this.goatKilled = toBeCopied.goatKilled;
		for (int i = 0; i < 31; i++)
			this.p[i] = new Coin(toBeCopied.p[i]);
		directions();
	}

	public void takeDecision(int i, int j) {
		if (isOverJumping(p[i], p[j]) == true) {

		}

		else if (p[i].tiger == true && tigers_move == true) {
			try {

				if (move_coin(p[i], p[j]) == true) {
					tigers_move = false;
					step++;
					if (Game.AI) {
						if (Game.tigerAIOn) {
							retracted = false;
							setStatus();

						}
					} else {
						retracted = false;
						setStatus();

					}
				} else {

					return;
				}
			} catch (NullPointerException e) {

			}
		} else if (p[i].tiger == true && tigers_move == false) {

		} else if (tigers_move == false) { // checking "is it goat's turn"

			try {

				if (move_coin(p[i], p[j]) == true) {
					tigers_move = true; // moves goat
					step++;
					if (Game.AI) {
						if (!Game.tigerAIOn) {
							retracted = false;
							setStatus();
						}
					} else {
						retracted = false;
						setStatus();

					}
				} else
					return;
			} catch (NullPointerException e) {

			}
		}

		else if (p[i].goat == true && tigers_move == true) {

		}

	}

	boolean isOverJumping(Coin FROM, Coin TO) {
		if (FROM.equals(TO.left) || (FROM.equals(TO.right)) || (FROM.equals(TO.top)) || (FROM.equals(TO.bottom))
				|| (FROM.equals(TO.bottom)) || (FROM.equals(TO.left_top)) || (FROM.equals(TO.left_bottom))
				|| (FROM.equals(TO.right_top)) || (FROM.equals(TO.right_bottom)))

		{
			return false;

		}

		else
			return true;
	}

	public boolean isGoatWinner(Coin tiger_status) {
		if (tiger_status.left != null) {
			if (tiger_status.left.vacant)
				return false;
			else if (tiger_status.left.left != null) {
				if (tiger_status.left.left.vacant)
					return false;
			}
		}
		if (tiger_status.right != null) {
			if (tiger_status.right.vacant)
				return false;
			if (tiger_status.right.right != null) {
				if (tiger_status.right.right.vacant)
					return false;
			}
		}
		if (tiger_status.top != null) {
			if (tiger_status.top.vacant)
				return false;
			else if (tiger_status.top.top != null) {
				if (tiger_status.top.top.vacant)
					return false;
			}
		}
		if (tiger_status.bottom != null) {
			if (tiger_status.bottom.vacant)
				return false;
			else if (tiger_status.bottom.bottom != null) {
				if (tiger_status.bottom.bottom.vacant)
					return false;
			}
		}
		if (tiger_status.left_top != null) {
			if (tiger_status.left_top.vacant)
				return false;
			else if (tiger_status.left_top.left_top != null) {
				if (tiger_status.left_top.left_top.vacant)
					return false;
			}
		}
		if (tiger_status.left_bottom != null) {
			if (tiger_status.left_bottom.vacant)
				return false;
			else if (tiger_status.left_bottom.left_bottom != null) {
				if (tiger_status.left_bottom.left_bottom.vacant)
					return false;
			}
		}
		if (tiger_status.right_top != null) {
			if (tiger_status.right_top.vacant)
				return false;
			else if (tiger_status.right_top.right_top != null) {
				if (tiger_status.right_top.right_top.vacant)
					return false;
			}
		}
		if (tiger_status.right_bottom != null) {
			if (tiger_status.right_bottom.vacant)
				return false;
			else if (tiger_status.right_bottom.right_bottom != null) {
				if (tiger_status.right_bottom.right_bottom.vacant)
					return false;
			}
		}
		return true;

	}

	boolean isTigerWinner() {

		if (goatKilled == 10) {
			this.tigerWon = true;
			return true;
		} else
			return false;

	}

	void display() {

		Coin Tiger = new Coin();
		Coin[] Goat = new Coin[16];

		Game T_Game = new Game();
		int[] a = new int[2];

		for (int i = 0; i < 31; i++) {
			if (p[i].tiger == true) {

				Tiger = p[i];
				break;
			}

		}

		a[0] = Tiger.X;
		a[1] = Tiger.Y;

		T_Game.move_tiger_gui(a); // updates the gui function of tiger

		for (int i = 0, j = 0; i < 31; i++)
			if (p[i].goat == true)
				Goat[j++] = p[i];

		T_Game.move_goat_gui(Goat); // updates the gui function of goat

		if (isGoatWinner(Tiger) == true)
			goatWon = true;
		if (isTigerWinner() == true)
			tigerWon = true;

	}

	void add_goat(Coin positionForGoat) {
		positionForGoat.goat = true;
		positionForGoat.vacant = false;
		totalNoOfGoatOnTheBoard++;
		// noOfGoatsInserted++;
	}

	void add_tiger(Coin positionForTiger) {
		positionForTiger.tiger = true;
		positionForTiger.vacant = false;
	}

	boolean moveCoinByForce(Coin origin, Coin destination) {

		boolean moveByForceSuccess = false;

		if (origin.tiger == true) {

			origin.tiger = false;
			destination.tiger = true;
			moveByForceSuccess = true;
			return moveByForceSuccess;

		}

		else if (origin.goat == true) {

			origin.tiger = false;
			destination.tiger = true;
			moveByForceSuccess = true;
			return moveByForceSuccess;
		}

		else
			return moveByForceSuccess;

	}

	public boolean move_coin(Coin origin, Coin destination) {

		boolean moveSuccess = false;

		if (origin.vacant == true) {

			moveSuccess = false;
			return moveSuccess;
		}

		if (destination.tiger == false && destination.goat == false) {
			if (origin.tiger == true) {

				origin.tiger = false;
				destination.tiger = true;
				origin.vacant = true;
				destination.vacant = false;
				moveSuccess = true;
			} else if (origin.goat == true) {
				origin.goat = false;
				destination.goat = true;
				origin.vacant = true;
				destination.vacant = false;
				moveSuccess = true;
			}

		} else if (destination.tiger == true) {

			moveSuccess = false;
			return moveSuccess;
		} else if (destination.goat == true) {// checking eat condition

			if ((origin.equals(destination.right) && destination.left.tiger == false
					&& destination.left.goat == false)) {

				destination.goat = false;

				move_coin(origin, destination);

				move_coin(destination, destination.left);

				totalNoOfGoatOnTheBoard--; // static int for number of goat
				++goatKilled;
				moveSuccess = true;
			} else if ((origin.equals(destination.top) && destination.bottom.tiger == false
					&& destination.bottom.goat == false)) {
				destination.goat = false;

				move_coin(origin, destination);
				move_coin(destination, destination.bottom);

				totalNoOfGoatOnTheBoard--; // static int for number of goat
				++goatKilled;
				moveSuccess = true;
			} else if ((origin.equals(destination.bottom) && destination.top.tiger == false
					&& destination.top.goat == false)) {
				destination.goat = false;

				move_coin(origin, destination);
				move_coin(destination, destination.top);
				totalNoOfGoatOnTheBoard--; // static int for number of goat
				++goatKilled;
				moveSuccess = true;
			} else if ((origin.equals(destination.left) && destination.right.tiger == false
					&& destination.right.goat == false)) {
				destination.goat = false;

				move_coin(origin, destination);
				move_coin(destination, destination.right);

				totalNoOfGoatOnTheBoard--; // static int for number of goat
				++goatKilled;
				moveSuccess = true;
			} else if ((origin.equals(destination.left_top) && destination.right_bottom.tiger == false
					&& destination.right_bottom.goat == false)) {
				destination.goat = false;

				move_coin(origin, destination);
				move_coin(destination, destination.right_bottom);

				totalNoOfGoatOnTheBoard--; // static int for number of goat
				++goatKilled;
				moveSuccess = true;
			} else if ((origin.equals(destination.left_bottom) && destination.right_top.tiger == false
					&& destination.right_top.goat == false)) {
				destination.goat = false;

				move_coin(origin, destination);
				move_coin(destination, destination.right_top);

				totalNoOfGoatOnTheBoard--; // static int for number of goat
				++goatKilled;
				moveSuccess = true;
			} else if ((origin.equals(destination.right_top) && destination.left_bottom.tiger == false
					&& destination.left_bottom.goat == false)) {
				destination.goat = false;

				move_coin(origin, destination);
				move_coin(destination, destination.left_bottom);

				totalNoOfGoatOnTheBoard--; // static int for number of goat
				++goatKilled;
				moveSuccess = true;
			} else if ((origin.equals(destination.right_bottom) && destination.left_top.tiger == false
					&& destination.left_top.goat == false)) {
				destination.goat = false;

				move_coin(origin, destination);
				move_coin(destination, destination.left_top);

				totalNoOfGoatOnTheBoard--; // static int for number of goat
				++goatKilled;
				moveSuccess = true;
			} else {

				moveSuccess = false;
			}
		}

		return moveSuccess;
	}

	public Board setStatus() {
		lastBoard = nowBoard;
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bout);
			try {
				out.writeObject(this);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				out.close();
			}
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
			try {
				nowBoard = (Board) in.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nowBoard;

	}

	public Board getStatus() {
		return lastBoard;
	}

}
