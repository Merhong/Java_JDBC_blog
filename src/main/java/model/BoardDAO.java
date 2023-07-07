package model;

import dto.BoardDetailDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	// C - create (insert)
	// R - read (select)
	// U - update (update)
	// D - delete (delete)
	
	private Connection connection;
	
	public BoardDAO(Connection connection) {
		this.connection = connection;
	}
	
	public BoardDetailDTO findByIdWithUser(Integer id) {
		BoardDetailDTO boardDetailDTO = null;
		
		String sql = "select bt.*, ut.u_username, ut.u_email\n"
			+ "from board_tb bt\n"
			+ "left outer join user_tb ut\n"
			+ "on bt.u_id = ut.u_id\n"
			+ "where b_id=?;\n";
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// 오브젝트 매핑 (테이블 데이터 -> 자바 오브젝트)
				boardDetailDTO = new BoardDetailDTO(
					rs.getInt("b_id"),
					rs.getInt("u_id"),
					rs.getString("u_username"),
					rs.getString("b_title"),
					rs.getString("b_content")
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return boardDetailDTO;
	}
	
	public void insert(Board board) {
		String sql = "insert into board_tb(b_title, b_content, u_id) values(?, ?, ?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, board.getBTitle());
			pstmt.setString(2, board.getBContent());
			pstmt.setInt(3, board.getUId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Integer id) {
		String sql = "delete from board_tb where b_id = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Board board) {
	}
	
	public List<Board> findAll() {
		List<Board> boardList = new ArrayList<>();
		String sql = "select * from board_tb order by b_id desc";
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// 오브젝트 매핑 (테이블 데이터 -> 자바 오브젝트)
				Board board = new Board(
					rs.getInt("b_id"),
					rs.getString("b_title"),
					rs.getString("b_content"),
					rs.getInt("u_id")
				);
				boardList.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return boardList;
	}
	
	public Board findById(Integer id) {
		Board board = null;
		String sql = "select * from board_tb where b_id = ?";
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// 오브젝트 매핑 (테이블 데이터 -> 자바 오브젝트)
				board = new Board(
					rs.getInt("b_id"),
					rs.getString("b_title"),
					rs.getString("b_content"),
					rs.getInt("u_id")
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return board;
	}
}
