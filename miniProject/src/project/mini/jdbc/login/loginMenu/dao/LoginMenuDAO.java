package project.mini.jdbc.login.loginMenu.dao;

import project.mini.jdbc.login.loginMenu.vo.Menu;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static project.mini.jdbc.common.JDBCTemplate.close;

public class LoginMenuDAO {

    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private Properties prop = null;

    public LoginMenuDAO() {
        try {
            prop = new Properties();
            prop.loadFromXML(new FileInputStream("menu_sql.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Menu> selectMenu(Connection conn, int menuNum) throws Exception {

        List<Menu> menuList = new ArrayList<>();

        try {

            String sql = null;

            if(menuNum == 3) sql = prop.getProperty("selectKorean");
            else if(menuNum == 4) sql = prop.getProperty("selectAmerican");
            else if(menuNum == 1) sql = prop.getProperty("selectChinese");
            else sql = prop.getProperty("selectJapanese");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int restaurantNum = rs.getInt("RESTAURANT_NUM");
                int menuNumber = rs.getInt("MENU_NUM");
                String menuName = rs.getString("MENU_NAME");
                String menuPrice = rs.getString("MENU_PRICE");

                Menu menu = new Menu(restaurantNum, menuNumber, menuName, menuPrice);
                menuList.add(menu);
            }

        } finally {
            close(rs);
            close(pstmt);
        }

        return menuList;
    }

    public int orderMenu(Connection conn, int restaurantNum, int foodNum) throws Exception {

        int price = 0;

        try {

            String sql = null;

            if(restaurantNum == 3) sql = prop.getProperty("orderKorean");
            else if(restaurantNum == 2) sql = prop.getProperty("orderJapanese");
            else if(restaurantNum == 1) sql = prop.getProperty("orderChinese");
            else sql = prop.getProperty("orderAmerican");

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, foodNum);

            rs = pstmt.executeQuery();

            if (rs.next()) price = Integer.parseInt(rs.getString("MENU_PRICE"));

        } finally {
            close(rs);
            close(pstmt);
        }

        return price;
    }

    public List<Menu> selectReview(Connection conn, int no) throws Exception {

        List<Menu> menuList = new ArrayList<>();

        try {

            String sql = null;

            if(no == 3) sql = prop.getProperty("reviewKorean");
            else if(no == 2) sql = prop.getProperty("reviewJapanese");
            else if(no == 1) sql = prop.getProperty("reviewChinese");
            else sql = prop.getProperty("reviewAmerican");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int reviewNo = rs.getInt("REVIEW_NO");
                String reviewContent = rs.getString("REVIEW_CONTENT");

                Menu menu = new Menu(reviewNo, reviewContent);
                menuList.add(menu);
            }

        } finally {
            close(rs);
            close(stmt);
        }

        return menuList;
    }

    public int insertReview(Connection conn, int no, String reviewContent) throws Exception {

        int result = 0;

        try {
            String sql;

            if(no == 3) sql = prop.getProperty("insertReviewKorean");
            else if(no == 2) sql = prop.getProperty("insertReviewJapanese");
            else if(no == 1) sql = prop.getProperty("insertReviewChinese");
            else sql = prop.getProperty("insertReviewAmerican");

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, reviewContent);

            result = pstmt.executeUpdate();

        } finally {
            close(pstmt);
        }

        return result;
    }
}
