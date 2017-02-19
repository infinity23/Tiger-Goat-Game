package board;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	Thread threadForplayGame = new Thread(new Game());

	static MainMenu menu = new MainMenu();

	public static void main(String args[]) {

		menu.setVisible(true);
		menu.setResizable(false);
		menu.setSize(400, 400);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * Create the panel.
	 */
	public MainMenu() {
		super("主菜单");

		getContentPane().setLayout(null);
//
//		JButton btnNewButton = new JButton("对局规则");
//		btnNewButton.setBounds(105, 82, 193, 43);
//		getContentPane().add(btnNewButton);

		JButton btnHumanVsHuman = new JButton("双人对局");
		btnHumanVsHuman.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				threadForplayGame.start();
				menu.setVisible(false);
			}
		});
		btnHumanVsHuman.setBounds(105, 137, 193, 43);
		getContentPane().add(btnHumanVsHuman);

		JButton btnHumanVsComputer = new JButton("人机对局");
		btnHumanVsComputer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				new Thread(new ForName()).start();
				;
				menu.setVisible(false);
			}
		});
		btnHumanVsComputer.setBounds(105, 195, 193, 43);
		getContentPane().add(btnHumanVsComputer);

	}
}
