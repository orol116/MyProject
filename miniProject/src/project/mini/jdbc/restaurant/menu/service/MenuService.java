package project.mini.jdbc.restaurant.menu.service;

import project.mini.jdbc.restaurant.menu.dao.MenuDAO;
import project.mini.jdbc.restaurant.menu.vo.Member;

import java.sql.Connection;

import static project.mini.jdbc.common.JDBCTemplate.*;

public class MenuService {

    private MenuDAO dao = new MenuDAO();


    public int duplicateCheck(String memberId) throws Exception {

        Connection conn = getConnection();

        int result = dao.duplicateCheck(conn, memberId);

        close(conn);

        return result;
    }


    public int signUp(Member signUpMember) throws Exception {

        Connection conn = getConnection();

        int result = dao.signUp(conn, signUpMember);

        if (result > 0) commit(conn);
        else rollback(conn);

        close(conn);

        return result;
    }

    public Member login(Member member) throws Exception {

        Connection conn = getConnection();

        Member loginMember = dao.login(conn, member);

        close(conn);

        return loginMember;
    }

    public int updateMyInfoPw(int memberNo, String newPw1, String currentPw) throws Exception {

        Connection conn = getConnection();

        int result = dao.updateMyInfoPw(conn, memberNo, newPw1, currentPw);

        if (result > 0) commit(conn);
        else rollback(conn);

        close(conn);

        return result;
    }

    public int updateMyId(Member updateMember) throws Exception {

        Connection conn = getConnection();

        int result = dao.updateMyId(conn, updateMember);

        if (result > 0) commit(conn);
        else rollback(conn);

        close(conn);

        return result;
    }
}
