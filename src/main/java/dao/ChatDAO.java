package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Chat;
import model.Port;

public class ChatDAO {

    String JDBC_URL = Port.JDBC_URL;

    // メッセージ送信
    public boolean insert(Chat chat) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection conn =
                    DriverManager.getConnection(JDBC_URL);

            String sql =
                    "INSERT INTO CHAT(SENDER_ID, RECEIVER_ID, MESSAGE) "
                    + "VALUES(?,?,?)";

            PreparedStatement pStmt =
                    conn.prepareStatement(sql);

            pStmt.setInt(1, chat.getSenderId());
            pStmt.setInt(2, chat.getReceiverId());
            pStmt.setString(3, chat.getMessage());

            int result = pStmt.executeUpdate();

            conn.close();

            return result == 1;

        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // チャット履歴取得
    public List<Chat> findChatHistory(
            int senderId,
            int receiverId) {

        List<Chat> chatList =
                new ArrayList<>();

        try {

            Class.forName(
                    "com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection conn =
                    DriverManager.getConnection(JDBC_URL);

            String sql =
                    "SELECT * FROM CHAT "
                    + "WHERE "
                    + "(SENDER_ID=? AND RECEIVER_ID=?) "
                    + "OR "
                    + "(SENDER_ID=? AND RECEIVER_ID=?) "
                    + "ORDER BY CHAT_ID";

            PreparedStatement pStmt =
                    conn.prepareStatement(sql);

            pStmt.setInt(1, senderId);
            pStmt.setInt(2, receiverId);
            pStmt.setInt(3, receiverId);
            pStmt.setInt(4, senderId);

            ResultSet rs =
                    pStmt.executeQuery();

            while(rs.next()) {

                Chat chat = new Chat();

                chat.setChatId(
                        rs.getInt("CHAT_ID"));

                chat.setSenderId(
                        rs.getInt("SENDER_ID"));

                chat.setReceiverId(
                        rs.getInt("RECEIVER_ID"));

                chat.setMessage(
                        rs.getString("MESSAGE"));

                chatList.add(chat);
            }

            conn.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return chatList;
    }
    public List<Chat> findChat(
            int senderId,
            int receiverId) {

        List<Chat> chatList =
                new ArrayList<>();

        try {

            Class.forName(
            "com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection conn =
                    DriverManager.getConnection(
                            JDBC_URL);

            String sql =
            "SELECT * FROM CHAT " +
            "WHERE (SENDER_ID=? AND RECEIVER_ID=?) " +
            "OR (SENDER_ID=? AND RECEIVER_ID=?) " +
            "ORDER BY CHAT_ID";

            PreparedStatement pStmt =
                    conn.prepareStatement(sql);

            pStmt.setInt(1, senderId);
            pStmt.setInt(2, receiverId);
            pStmt.setInt(3, receiverId);
            pStmt.setInt(4, senderId);

            ResultSet rs =
                    pStmt.executeQuery();

            while (rs.next()) {

                Chat chat = new Chat();

                chat.setChatId(
                        rs.getInt("CHAT_ID"));

                chat.setSenderId(
                        rs.getInt("SENDER_ID"));

                chat.setReceiverId(
                        rs.getInt("RECEIVER_ID"));

                chat.setMessage(
                        rs.getString("MESSAGE"));

                chatList.add(chat);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return chatList;
    }
}