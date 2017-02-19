package board;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class ForName extends JDialog implements Runnable {
	private JTextField textField;
	Thread threadForHumanVsComputer = new Thread(new SubMenu());

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			ForName dialog = new ForName();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ForName() {
		setBounds(100, 100, 288, 192);
		getContentPane().setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u4F60\u7684\u540D\u5B57\uFF1A");
			lblNewLabel.setBounds(10, 31, 138, 30);
			getContentPane().add(lblNewLabel);
		}
		{
			textField = new JTextField(10);
			textField.setBounds(110, 32, 138, 30);
			getContentPane().add(textField);
			textField.setForeground(Color.BLACK);
			textField.setBackground(Color.WHITE);
		}
		{
			JLabel label = DefaultComponentFactory.getInstance().createLabel("");
			label.setBounds(0, 62, 272, 30);
			getContentPane().add(label);
		}
		{
			JLabel label = new JLabel("");
			label.setBounds(0, 92, 272, 30);
			getContentPane().add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 122, 272, 30);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("\u786E\u5B9A");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Board.player = textField.getText();
						threadForHumanVsComputer.start();
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);

			}
			{
				JButton cancelButton = new JButton("\u533F\u540D");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						threadForHumanVsComputer.start();
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
