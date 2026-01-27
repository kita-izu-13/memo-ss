package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Form;

public class AccountsUserDeleteDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/memo";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	
	//アカウント削除
	public Boolean userdelete(Form form) {
	//JDBCドライバを読み込む
	try {
		Class.forName("org.h2.Driver");
	}catch(ClassNotFoundException e) {
		throw new IllegalStateException("JDBCドライバを読み込めませんでした");
	}
	//データベースへ接続
	try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
		
		//DELETE文を準備
		String sql = "DELETE FROM ACCOUNTS WHERE USER_ID='"+form.getUserId()+"';";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		//DELETE文を実行
		pStmt.executeUpdate();
		
	}catch(SQLException e) {
		e.printStackTrace();
		return null;
	}
	return true;
	}
}
