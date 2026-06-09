package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.GroupMessage;
import model.Port;

public class GroupMessageDAO {

    String JDBC_URL = Port.JDBC_URL;

    public List<GroupMessage> getMessages(int groupId){

        List<GroupMessage> list = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(JDBC_URL);

            String sql =
            		"SELECT gc.*, e.NAME, e.PHOTO_PATH " +
            		"FROM GROUP_CHAT gc " +
            		"INNER JOIN EMPLOYEE e " +
            		"ON gc.SENDER_ID = e.ID " +
            		"WHERE gc.GROUP_ID=? " +
            		"ORDER BY gc.CHAT_ID";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, groupId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                GroupMessage gm = new GroupMessage();
                gm.setGroupId(rs.getInt("GROUP_ID"));
                gm.setSenderId(rs.getInt("SENDER_ID"));
                gm.setMessage(rs.getString("MESSAGE"));
                gm.setSenderName(rs.getString("NAME"));
                gm.setPhotoPath(rs.getString("PHOTO_PATH"));

                list.add(gm);
            }

            conn.close();

        } catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public void sendMessage(GroupMessage msg){

        try {
            Connection conn = DriverManager.getConnection(JDBC_URL);

            String sql = "INSERT INTO GROUP_CHAT (GROUP_ID, SENDER_ID, MESSAGE) VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, msg.getGroupId());
            ps.setInt(2, msg.getSenderId());
            ps.setString(3, msg.getMessage());

            ps.executeUpdate();

            conn.close();

        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
}