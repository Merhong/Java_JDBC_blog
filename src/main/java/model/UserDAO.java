package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // CRUD의 Create
    public void insert(User user){
        String sql = "insert into user_tb(u_username, u_password, u_email)  values(?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getUUsername());    // 첫번째 물음표
            pstmt.setString(2, user.getUPassword());    // 두번째 물음표
            pstmt.setString(3, user.getUEmail());       // 세번째 물음표
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    // CRUD의 Delete
    public void delete(Integer id){
        String sql = "delete from user_tb where u_id = ?";              // 테이블로부터 id(PK)를 받아서 튜플 삭제
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    // CRUD의 Update
    public void update(User user){}

    public List<User> findAll(){
        List<User> userList = new ArrayList<>();
        String sql = "select * from user_tb order by u_id desc"; // 테이블로부터 모든 컬럼을 id를 받아 내림차순으로 출력

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                // 오브젝트 매핑 (테이블 데이터 -> 자바 오브젝트)
                User user = new User(
                        rs.getInt("u_id"),
                        rs.getString("u_username"),
                        rs.getString("u_password"),
                        rs.getString("u_email")
                );
                userList.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return userList;
    }
    
    // CRUD의 Read
    public User findById(Integer id){
        User user = null;
        String sql = "select * from user_tb where u_id = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                // 오브젝트 매핑 (테이블 데이터 -> 자바 오브젝트)
                user = new User(
                        rs.getInt("u_id"),
                        rs.getString("u_username"),
                        rs.getString("u_password"),
                        rs.getString("u_email")
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }
}
