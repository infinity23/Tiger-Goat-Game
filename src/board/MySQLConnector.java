package board;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class MySQLConnector {
	String player;
	int step;
	String win;

	public MySQLConnector(String player, int step, String win) {
		this.win = win;
		this.player = player;
		this.step = step;

		Connection conn = null;
		String sql;
		String url = "jdbc:mysql://localhost:3306/tigergame";
		// +"user=root&password=root&useUnicode=true&characterEncoding=UTF8";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("成功加载MySQL驱动程序");
			conn = (Connection) DriverManager.getConnection(url, "root", "root");
			Statement stmt = conn.createStatement();
			sql = "create table if not exists player(id integer primary key auto_increment,name varchar(20),"
					+ "step int,win varchar(3),date timestamp)";
			stmt.executeUpdate(sql);
			sql = "insert into player (name,step,win) values ('" + player + "', " + step + ", '" + win + "')";
			stmt.executeUpdate(sql);

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

	}

	public static void getInfo() {
		new Thread(new ShowData()).start();
	}

}
