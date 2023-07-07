import db.DBConnection;
import dto.BoardDetailDTO;
import model.Board;
import model.BoardDAO;
import model.User;
import model.UserDAO;

import java.sql.Connection;

public class    BlogApp {
    public static void main(String[] args) {
        Connection connection = DBConnection.getInstance();

        UserDAO userDAO = new UserDAO(connection);
        BoardDAO boardDAO = new BoardDAO(connection);

//        User user = new User(null, "love", "1234", "love@nate.com");
//        userDAO.insert(user);
        BoardDetailDTO boardDetailDTO = boardDAO.findByIdWithUser(1);
        System.out.println(boardDetailDTO);

        // Board board = boardDAO.findById(1);
        // System.out.println(board.toString());
    }
}
