package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Port;
import model.Questionnaire;

public class QuestionnaireDAO {
	String JDBC_URL = Port.JDBC_URL;

	//アンケート情報をすべて取得
	public List<Questionnaire> findAll() {
		List<Questionnaire> questionnaireList = new ArrayList<>();
		Questionnaire questionnaire= null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "SELECT * FROM QUESTIONNAIRE";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int questionnaireId = rs.getInt("QUESTIONNAIRE_ID");
				int employeeId = rs.getInt("EMPLOYEE_ID");
				String content = rs.getString("CONTENT");
				questionnaire = new Questionnaire(questionnaireId, employeeId, content);
				questionnaireList.add(questionnaire);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return questionnaireList;
	}
	
	//指定した従業員IDをもつデータをすべて取得
	public List<Questionnaire> findByEmployeeId(int employeeId){
		List<Questionnaire> questionnaireList = new ArrayList<>();
		Questionnaire questionnaire= null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "SELECT * FROM QUESTIONNAIRE WHERE EMPLOYEE_ID = ?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, employeeId);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int questionnaireId = rs.getInt("QUESTIONNAIRE_ID");
				String content = rs.getString("CONTENT");
				questionnaire = new Questionnaire(questionnaireId, employeeId, content);
				questionnaireList.add(questionnaire);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return questionnaireList;
	}
	
	//新規アンケート情報を登録
	public boolean insertQuestionnaire(int employeeId, String content) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "INSERT INTO QUESTIONNAIRE (EMPLOYEE_ID, CONTENT) VALUES(?, ?)";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, employeeId);
			pStmt.setString(2, content);
			
			int result = pStmt.executeUpdate();
			
			if(result != 1) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
