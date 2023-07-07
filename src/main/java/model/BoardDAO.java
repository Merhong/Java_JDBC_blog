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
	
	// DB연결
	public BoardDAO(Connection connection) {
		this.connection = connection;
	}
	
	// DAO : 쿼리문을 불러와서 처리
	public BoardDetailDTO findByIdWithUser(Integer id) {
		BoardDetailDTO boardDetailDTO = null;                   // DTO 변수 초기화
		
		String sql = "select bt.*, ut.u_username, ut.u_email\n" // 구현할 쿼리문
			+ "from board_tb bt\n"
			+ "left outer join user_tb ut\n"
			+ "on bt.u_id = ut.u_id\n"
			+ "where b_id=?;\n";
		
		try { // 위의 sql문을 pstmt에 할당 후 rs에 넣어서 쿼리 실행
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { // 한줄씩 읽으면서 DTO에 값을 할당한다. (매핑)
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
		
		// 구현 끝나면 DTO를 리턴
		return boardDetailDTO;
	}
	
	// CRUD의 Create에 해당
	public void insert(Board board) {
		String sql = "insert into board_tb(b_title, b_content, u_id) values(?, ?, ?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, board.getBTitle());      // 첫번째 물음표
			pstmt.setString(2, board.getBContent());    // 두번째 물음표
			pstmt.setInt(3, board.getUId());            // 세번째 물음표
			pstmt.executeUpdate();  // 실행
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// CRUD의 Delete
	public void delete(Integer id) {    // PK값인 id를 이용해서 삭제 진행한다.
		String sql = "delete from board_tb where b_id = ?"; // 테이블로부터 id의 값을 얻어와서 삭제
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// CRUD의 Update
	public void update(Board board) {
	}
	
	// 모든 컬럼을 불러오는 메소드
	public List<Board> findAll() {
		List<Board> boardList = new ArrayList<>();
		String sql = "select * from board_tb order by b_id desc";   // 테이블에서 모든 컬럼을 내림차순으로 불러온다.
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			// "모든" 튜플을 돌때까지 반복
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
	
	// CRUD의 Read
	// id를 찾는 메소드
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
