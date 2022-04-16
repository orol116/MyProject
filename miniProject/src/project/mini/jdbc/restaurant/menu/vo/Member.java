package project.mini.jdbc.restaurant.menu.vo;

public class Member {

    private int memberNo;
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberSocialSecurityNumber;
    private String memberAdress;
    private String phoneNumber;

    public Member() {}

    public Member(String memberId, String memberPw, String memberName, String memberSocialSecurityNumber, String memberAdress, String phoneNumber) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberSocialSecurityNumber = memberSocialSecurityNumber;
        this.memberAdress = memberAdress;
        this.phoneNumber = phoneNumber;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPw() {
        return memberPw;
    }

    public void setMemberPw(String memberPw) {
        this.memberPw = memberPw;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberSocialSecurityNumber() {
        return memberSocialSecurityNumber;
    }

    public void setMemberSocialSecurityNumber(String memberSocialSecurityNumber) {
        this.memberSocialSecurityNumber = memberSocialSecurityNumber;
    }

    public String getMemberAdress() {
        return memberAdress;
    }

    public void setMemberAdress(String memberAdress) {
        this.memberAdress = memberAdress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
