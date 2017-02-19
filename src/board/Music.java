package board;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;

import javax.swing.Action;
import javax.swing.JToggleButton;

public class Music extends JFrame implements Runnable {

	private JPanel contentPane;
	private static AudioClip ac;
	private boolean musicON = false;
	private final JToggleButton tglbtnNewToggleButton = new JToggleButton();
	private final Action action = new SwingAction();

	public void run() {
		try {
			File file = new File("Music/MySoul.wav");
			ac = Applet.newAudioClip(file.toURI().toURL());
			Music frame = new Music();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Music() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 213, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tglbtnNewToggleButton.setText("±≥æ∞“Ù¿÷ πÿ");
		tglbtnNewToggleButton.setAction(action);
		tglbtnNewToggleButton.setBounds(10, 35, 172, 33);
		contentPane.add(tglbtnNewToggleButton);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "±≥æ∞“Ù¿÷ πÿ");
			// putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			musicON = !musicON;
			if (musicON) {
				tglbtnNewToggleButton.setText("±≥æ∞“Ù¿÷ ø™");
				ac.loop();
			} else {
				ac.stop();
				tglbtnNewToggleButton.setText("±≥æ∞“Ù¿÷ πÿ");
			}
		}
	}

}
