package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Group;
import model.Port;

public class GroupDAO {

    String JDBC_URL = Port.JDBC_URL;

    public boolean createGroup(Group group) {

        try {

            Connection conn =
                DriverManager.getConnection(JDBC_URL);

            String sql =
                "INSERT INTO CHAT_GROUP " +
                "(GROUP_NAME, CREATED_BY) " +
                "VALUES (?, ?)";

            PreparedStatement pStmt =
                conn.prepareStatement(sql);

            pStmt.setString(
                1,
                group.getGroupName());

            pStmt.setInt(
                2,
                group.getCreatedBy());

            int result =
                pStmt.executeUpdate();

            conn.close();

            return result == 1;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public List<Group> getAllGroups() {

        List<Group> list = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(JDBC_URL);

            String sql = "SELECT * FROM CHAT_GROUP";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Group g = new Group();
                g.setGroupId(rs.getInt("GROUP_ID"));
                g.setGroupName(rs.getString("GROUP_NAME"));
                g.setCreatedBy(rs.getInt("CREATED_BY"));

                list.add(g);
            }

            conn.close();

        } catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
    public Group getGroupById(int groupId) {

        Group group = null;

        try {

            Connection conn =
                DriverManager.getConnection(JDBC_URL);

            String sql =
                "SELECT * FROM CHAT_GROUP WHERE GROUP_ID=?";

            PreparedStatement ps =
                conn.prepareStatement(sql);

            ps.setInt(1, groupId);

            ResultSet rs =
                ps.executeQuery();

            System.out.println("Searching Group ID = " + groupId);
            

            if(rs.next()){

                System.out.println("Found Group = " + rs.getString("GROUP_NAME"));

                group = new Group();

                group.setGroupId(rs.getInt("GROUP_ID"));

                group.setGroupName(rs.getString("GROUP_NAME"));

                group.setCreatedBy(rs.getInt("CREATED_BY"));
            }
        else{
            System.out.println("Group Not Found");
        }

            conn.close();

        } catch(Exception e){
            e.printStackTrace();
        }

        return group;
    }
    
}