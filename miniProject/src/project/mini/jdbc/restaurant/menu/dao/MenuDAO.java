package project.mini.jdbc.restaurant.menu.dao;

import project.mini.jdbc.restaurant.menu.vo.Member;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

import static project.mini.jdbc.common.JDBCTemplate.close;

public class MenuDAO {

    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    private Properties prop = null;

    public MenuDAO() {
        try {
            prop = new Properties();
            prop.loadFromXML(new FileInputStream("member_sql.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


        public int duplicateCheck(Connection conn, String memberId) throws Exception {

            int result = 0;

            try {

                String sql = prop.getProperty("duplicateCheck");

                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, memberId);

                rs = pstmt.executeQuery();

                if (rs.next()) result = rs.getInt(1);


            } finally {
                close(rs);
                close(pstmt);
            }

            return result;
        }

    public int signUp(Connection conn, Member signUpMember) throws Exception {

        int result = 0;

        try {
            String sql = prop.getProperty("signUp");

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, signUpMember.getMemberId());
            pstmt.setString(2, signUpMember.getMemberPw());
            pstmt.setString(3, signUpMember.getMemberName());
            pstmt.setString(4, signUpMember.getMemberSocialSecurityNumber());
            pstmt.setString(5, signUpMember.getMemberAdress());
            pstmt.setString(6, signUpMember.getPhoneNumber());

            result = pstmt.executeUpdate();

        } finally {
            close(pstmt);
        }
        return result;
    }

    public Member login(Connection conn, Member member) throws Exception {

        Member loginMember = null;

        try {

            String sql = prop.getProperty("login");

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setString(2, member.getMemberPw());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                int memberNo = rs.getInt("MEMBER_NO");
                String memberId = rs.getString("MEMBER_ID");
                String memberName = rs.getString("MEMBER_NM");
                String memberSocialSecurityNumber = rs.getString("MEMBER_SSN");
                String memberAdress = rs.getString("MEMBER_ADRESS");
                String memberPhone = rs.getString("MEMBER_PHONE");

                loginMember = new Member();

                loginMember.setMemberNo(memberNo);
                loginMember.setMemberId(memberId);
                loginMember.setMemberName(memberName);
                loginMember.setMemberSocialSecurityNumber(memberSocialSecurityNumber);
                loginMember.setMemberAdress(memberAdress);
                loginMember.setPhoneNumber(memberPhone);
            }

        } finally {
            close(rs);
            close(pstmt);
        }

        return loginMember;
    }

    public int updateMyInfoPw(Connection conn, int memberNo, String newPw1, String currentPw) throws Exception {

        int result = 0;

        try {

            String sql = prop.getProperty("updateMyInfoPw");

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPw1);
            pstmt.setInt(2, memberNo);
            pstmt.setString(3, currentPw);

            result = pstmt.executeUpdate();

        } finally {
            close(pstmt);
        }

        return result;
    }

    public int updateMyId(Connection conn, Member updateMember) throws Exception {

        int result = 0;

        try {

            String sql = prop.getProperty("updateMyId");

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, updateMember.getMemberId());
            pstmt.setString(2, updateMember.getMemberName());
            pstmt.setString(3, updateMember.getMemberAdress());
            pstmt.setString(4, updateMember.getPhoneNumber());
            pstmt.setInt(5, updateMember.getMemberNo());

            result = pstmt.executeUpdate();

        } finally {
            close(pstmt);
        }

        return result;
    }
}


