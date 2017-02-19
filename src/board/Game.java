package board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ai.MiniMax;

public class Game extends JPanel implements MouseListener, Runnable, Serializable {

	private static final long serialVersionUID = 2L;

	static int X, Y;

	public static boolean tigerAIOn = false;
	public static boolean AI = false;

	static int input_x1, input_y1, input_x2, input_y2;

	static MiniMax MX_onGame = new MiniMax();
	static Game game = new Game();
	static Board board = new Board(0);
	static int goat_coordinate[][] = new int[16][2];
	BufferedImage img, tiger_1, goat, background;

	// private ActionEvent arg0;

	void move_tiger_gui(int a_Game[]) {
		X = a_Game[0];
		Y = a_Game[1];

	}

	void move_goat_gui(Coin goat_c[]) {

		for (int i = 0; i < board.totalNoOfGoatOnTheBoard; i++) {

			goat_coordinate[i][0] = goat_c[i].X;
			goat_coordinate[i][1] = goat_c[i].Y;

		}

	}

	public Game() {
		try {
			background = ImageIO.read(new File("Project Pictures/background.jpg"));
			img = ImageIO.read(new File("Project Pictures/board3.png"));

			tiger_1 = ImageIO.read(new File("Project Pictures/Tiger-gnu.png"));
			goat = ImageIO.read(new File("Project Pictures/animals-gnu.png"));
		} catch (IOException e) {
			System.out.println("Image not loaded");
		}

	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.drawImage(background, 0, 0, null);

		g2d.drawImage(img, 150, 20, null);
		g2d.drawImage(tiger_1, (X - 20), (Y - 20), null);

		for (int l = 0; l < board.totalNoOfGoatOnTheBoard; l++) {

			g2d.drawImage(goat, goat_coordinate[l][0] - 20,
					goat_coordinate[l][1] - 20/* -20 for adjustment */, null);
		}

		displayWhoMoves(g2d);
		displayNoOfGoats(g2d);
		displayWhoWins(g2d);
		displayAIWait(g2d);
		displayName(g2d);

	}

	// new Function

	public void displayWhoMoves(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.BLACK);
		Font f = new Font("����", Font.CENTER_BASELINE, 25);
		setFont(f);

		if (board.tigers_move == true) {
			g2d.drawString("�ϻ��Ļغ�", 50, 100);
		} else {
			g2d.drawString("��Ļغ�", 50, 100);
		}
	}

	public void displayAIWait(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.BLACK);
		Font f = new Font("����", Font.CENTER_BASELINE, 25);
		setFont(f);

		if (AI && !MiniMax.done)
			g2d.drawString("�ȴ�����", 50, 150);

	}

	public void displayName(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.BLACK);
		Font f = new Font("����", Font.CENTER_BASELINE, 25);
		setFont(f);

		if (AI) {
			g2d.drawString("������ " + board.player, 600, 650);
			g2d.drawString("������ " + String.valueOf(board.step), 600, 700);
		}

	}

	// new function

	public void displayNoOfGoats(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		Font f = new Font("����", Font.CENTER_BASELINE, 25);
		setFont(f);

		g2d.drawString("���Ե����� : " + board.goatKilled, 500, 130);

		g2d.drawString("��ʣ�������  : " + board.totalNoOfGoatOnTheBoard, 480,
				100 /* 240,650 */ );

	}

	public void displayWhoWins(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		Font f = new Font("����", Font.CENTER_BASELINE, 25);
		setFont(f);
		if (board.goatWon) {
			g2d.setColor(Color.blue);
			g2d.fillRoundRect(170, 120, 430, 100, 140, 100);
			g2d.setColor(Color.white);
			g2d.drawString("��Ӯ��", 200, 180);
		} else if (board.tigerWon) {

			g2d.setColor(Color.blue);
			g2d.fillRoundRect(170, 120, 430, 100, 140, 100);
			g2d.setColor(Color.white);
			g2d.drawString("�ϻ�Ӯ", 200, 180);
		}
	}

	public static void playGame() { // Called by run() from the bottom of this
									// class

		JFrame frame = new JFrame("�ϻ���");
		MenuBar MB = new MenuBar();

		Menu helpMenu = new Menu("����");
		Menu gameMenu = new Menu("��Ϸ");

		MenuItem about = new MenuItem("����");

		MenuItem newGame = new MenuItem("����Ϸ");
		MenuItem retract = new MenuItem("����");
		MenuItem record = new MenuItem("��Ϸ��¼");
		MenuItem gameover = new MenuItem("������Ϸ");
		MenuItem music = new MenuItem("��������");

		helpMenu.add(about);

		gameMenu.add(newGame);
		gameMenu.add(retract);
		gameMenu.add(music);
		gameMenu.add(record);
		gameMenu.add(gameover);

		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				board = new Board(0);
				board.display();
			}

		});
		retract.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (board.retracted) {
					JOptionPane.showMessageDialog(null, "ֻ�ܻ���һ�Σ�");
					return;
				}
				if (AI && !MiniMax.done)
					return;
				board = board.getStatus();
				board.display();
				board.retracted = true;
			}

		});
		record.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MySQLConnector.getInfo();

			}
		});
		music.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Music()).start();

			}
		});
		gameover.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String win = board.goatWon ? "��Ӯ" : (board.tigerWon ? "��Ӯ" : "δ����");
				new MySQLConnector(board.player, board.step, win);
				System.exit(0);
			}
		});

		MB.add(gameMenu);
		MB.add(helpMenu);

		frame.add(game);

		frame.setMenuBar(MB);
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		board.display();
		game.repaint();

		MouseListener ml = new MouseAdapter() {

			public void mousePressed(MouseEvent evt1) {
				game.testmousePressed(evt1);
			}

			public void mouseReleased(MouseEvent evt2) {
				game.testmouseReleased(evt2);
			}

			public void mouseClicked(MouseEvent evt3) {
				game.testmouseClicked(evt3);
			}
		};

		while (true) {

			frame.addMouseListener(ml);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			frame.repaint(); // call paint()

			if (AI == true) {
				MX_onGame.startMiniMax(board, tigerAIOn); // This "false" refers
															// to onTigerAI

				board.display();
			}

			if (board.goatWon == true) {
				System.out.println("��Ӯ��");
				frame.removeMouseListener(ml);
				game.repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			} else if (board.tigerWon == true) {

				System.out.println("�ϻ�Ӯ");
				frame.removeMouseListener(ml);
				game.repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}

			frame.removeMouseListener(ml); // avoid the re adding error of
											// MouseListener

		}

	}

	public void testmouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void testmouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void testmousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		input_x1 = arg0.getX();
		input_y1 = arg0.getY();
		System.out.println(input_x1 + "," + input_y1);

	}

	public void testmouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		input_x2 = arg0.getX();
		input_y2 = arg0.getY();

		System.out.println(input_x2 + "," + input_y2);
		game.getInput_and_findPoint(input_x1, input_y1, input_x2, input_y2);
		System.out.println("No. of goat = " + board.totalNoOfGoatOnTheBoard);
		System.out.println("goat killed = " + board.goatKilled);
		// should be replaced by tiger_move

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() { // Main method of this thread
		// TODO Auto-generated method stub
		playGame();

	}

	void getInput_and_findPoint(int input_from_x, int input_from_y, int input_to_x, int input_to_y) {

		int i, j;

		i = gravity(input_from_x, input_from_y);
		j = gravity(input_to_x, input_to_y);

		System.out.println("i=" + i);
		System.out.println("j=" + j);

		if (i != 999 && j != 999) {
			if (AI && !MiniMax.done)
				return;
			board.takeDecision(i, j);
			board.display();

		} else {
			System.out.println("Invalid input points");
			return;
		}

	} // End of getInput_and_findPoint()

	int gravity(int x, int y) {

		for (int i = 0; i < 31; i++) {
			if ((x > (board.p[i].X - 50)) && ((x < (board.p[i].X + 50))) && (y > (board.p[i].Y - 10))
					&& ((y < (board.p[i].Y + 90)))) {
				return i;
			}
		}

		System.out.println("The inputed co-ordinates are invalid");
		return 999; // default error value stating invalid position

	}

}