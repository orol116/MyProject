package project.mini.jdbc.login.loginMenu.service;

import project.mini.jdbc.login.loginMenu.dao.LoginMenuDAO;
import project.mini.jdbc.login.loginMenu.vo.Menu;

import java.sql.Connection;
import java.util.List;

import static project.mini.jdbc.common.JDBCTemplate.*;

public class LoginMenuService {

    private LoginMenuDAO dao = new LoginMenuDAO();


    public List<Menu> selectMenu(int menuNum) throws Exception {
        Connection conn = getConnection();

        List<Menu> menuList = dao.selectMenu(conn, menuNum);

        close(conn);

        return menuList;
    }

    public int orderMenu(int restaurantNum, int foodNum) throws Exception {

        Connection conn = getConnection();

        int price = dao.orderMenu(conn, restaurantNum, foodNum);

        close(conn);

        return price;
    }

    public List<Menu> selectReview(int no) throws Exception {

        Connection conn = getConnection();

        List<Menu> menuList = dao.selectReview(conn, no);

        close(conn);

        return menuList;
    }

    public int insertReview(int no, String reviewContent) throws Exception {

        Connection conn = getConnection();

        int result = dao.insertReview(conn, no, reviewContent);

        if (result > 0) commit(conn);
        else rollback(conn);

        close(conn);

        return result;
    }
}
