package board;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;

import javax.swing.JTable;

public class ShowData extends JFrame implements Runnable {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */

	public void run() {
		try {
			ShowData frame = new ShowData();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public ShowData() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		Connection conn = null;
		String sql;
		String url = "jdbc:mysql://localhost:3306/tigergame";
		ResultSet rs;
		DefaultTableModel dtm = new DefaultTableModel(null, new String[] { "ID", "姓名", "步数", "状态", "时间" });
		// +"user=root&password=root&useUnicode=true&characterEncoding=UTF8";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("成功加载MySQL驱动程序");
			conn = (Connection) DriverManager.getConnection(url, "root", "root");
			Statement stmt = conn.createStatement();
			sql = "select * from player";
			rs = stmt.executeQuery(sql);
			dtm.addRow(new String[] { "ID", "姓名", "步数", "状态", "时间" });

			while (rs.next()) {
				dtm.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5) });
			}

		} catch (SQLException e) {
			System.out.println("MySQL操作错误");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		table = new JTable(dtm);
		contentPane.add(table, BorderLayout.CENTER);
	}

}
