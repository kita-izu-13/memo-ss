package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Form;
import model.Memo;
import model.MemoUpdate;

public class MemosUpdateformDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/memo";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public MemoUpdate update(MemoUpdate memoupdate,Form form) {
		Memo memo = null;
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){			
			//UPDATE文を準備
			String sql = "UPDATE "+form.getMemos()+" SET DATE=?, TITLE=?, MEMO=? WHERE ID=?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);	
						
			pStmt.setDate(1,  memoupdate.getDate());
			pStmt.setString(2, memoupdate.getTitle());
			pStmt.setString(3, memoupdate.getMemo());
			pStmt.setInt(4, memoupdate.getId());
			
			int result =pStmt.executeUpdate();
			pStmt.close();
			conn.close();

		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return memoupdate;
	}
}