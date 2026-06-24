package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Port;
import model.Position;

public class PositionDAO {

    private static final String JDBC_URL = Port.JDBC_URL;

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
    }

    // 役職一覧取得
    public List<Position> findAll() {

        List<Position> positionList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

            String sql = "SELECT * FROM POSITION";

            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {

                int positionId = rs.getInt("POSITION_ID");
                String positionName = rs.getString("POSITION_NAME");

                positionList.add(
                    new Position(positionId, positionName)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return positionList;
    }

    // IDから役職取得
    public Position findByPositionId(int positionId) {

        Position position = null;

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

            String sql =
                "SELECT POSITION_NAME " +
                "FROM POSITION " +
                "WHERE POSITION_ID = ?";

            PreparedStatement pStmt =
                conn.prepareStatement(sql);

            pStmt.setInt(1, positionId);

            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {

                String positionName =
                    rs.getString("POSITION_NAME");

                position =
                    new Position(positionId, positionName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return position;
    }

    // 新規登録
    public boolean insertPosition(int positionId, String positionName) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            String sql ="INSERT INTO POSITION " +
                "(POSITION_ID, POSITION_NAME) " +
                "VALUES (?, ?)";

            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, positionId);
            pStmt.setString(2, positionName);

            return pStmt.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 更新
    public boolean updatePosition(int positionId, String positionName) {

        try (Connection conn =
                DriverManager.getConnection(JDBC_URL)) {

            String sql =
                "UPDATE POSITION " +
                "SET POSITION_NAME = ? " +
                "WHERE POSITION_ID = ?";

            PreparedStatement pStmt =
                conn.prepareStatement(sql);

            pStmt.setString(1, positionName);
            pStmt.setInt(2, positionId);

            return pStmt.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 削除
    public boolean deletePosition(int positionId) {

        try (Connection conn =
                DriverManager.getConnection(JDBC_URL)) {

            String sql =
                "DELETE FROM POSITION " +
                "WHERE POSITION_ID = ?";

            PreparedStatement pStmt =
                conn.prepareStatement(sql);

            pStmt.setInt(1, positionId);

            return pStmt.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}