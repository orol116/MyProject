package project.mini.jdbc.restaurant.view;

import project.mini.jdbc.restaurant.menu.service.MenuService;
import project.mini.jdbc.restaurant.menu.vo.Member;

import java.util.List;
import java.util.Scanner;

public class RestaurantView {

    private Scanner sc = new Scanner(System.in);
    private MenuService service = new MenuService();

    public void signUp() {

        System.out.println("\n[회원 가입]\n");

        try {

            String memberId;
            String memberPw;
            String memberPw2;
            String memberName;
            String memberSocialSecurityNumber;
            String phoneNumber;
            String memberAdress;

            while (true) {

                System.out.print("아이디 : ");
                memberId = sc.next();

                int result = service.duplicateCheck(memberId);

                if (result == 0) {
                    System.out.println("사용 가능한 아이디 입니다.");
                    break;
                } else System.out.println("중복되는 아이디가 있습니다. 다시 입력해주세요.");

            }
            while (true) {

                System.out.print("비밀번호 : ");
                memberPw = sc.next();

                System.out.print("비밀번호 확인 : ");
                memberPw2 = sc.next();

                if (memberPw.equals(memberPw2)) break;
                else System.out.println("비밀번호가 다릅니다. 다시 입력해주세요.");
            }

            System.out.print("이름 : ");
            memberName = sc.next();

            System.out.print("주소 : ");
            sc.nextLine();
            memberAdress = sc.nextLine();

            System.out.print("핸드폰번호 : ");
            phoneNumber = sc.next();

            while (true) {

                System.out.print("주민번호('-'를 포함한 14자리를 입력해주세요!!) : ");
                memberSocialSecurityNumber = sc.next();

                if (memberSocialSecurityNumber.length() != 14) System.out.println("14자리를 다시 입력해주세요");
                else break;
            }

            Member signUpMember = new Member(memberId, memberPw, memberName, memberSocialSecurityNumber, memberAdress, phoneNumber);

            int result = service.signUp(signUpMember);

            if (result > 0) System.out.println("\n회원가입 성공!!\n");
            else System.out.println("\n회원가입 실패 ㅠㅠ\n");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Member login() {
        System.out.println("[로그인]");

        System.out.print("아이디 : ");
        String memberId = sc.next();

        System.out.print("비밀번호 : ");
        String memberPw = sc.next();

        Member member = new Member();
        member.setMemberId(memberId);
        member.setMemberPw(memberPw);

        Member loginMember = null;

        try {

            loginMember = service.login(member);

            if (loginMember != null) System.out.println("\n*** " + loginMember.getMemberName() + "님 환영합니다. ***\n");
            else System.out.println("\n[아이디 또는 비밀번호가 일치하지 않습니다.]\n");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return loginMember;
    }

    public void selectMyInfo(Member loginMember) {
        System.out.println("\n[내 정보 조회]\n");

        System.out.println("회원 번호 : " + loginMember.getMemberNo());
        System.out.println("아이디 : " + loginMember.getMemberId());
        System.out.println("이름 : " + loginMember.getMemberName());
        System.out.println("핸드폰 번호 : " + loginMember.getPhoneNumber());
        System.out.println("주소 : " + loginMember.getMemberAdress()  + "\n");
    }

    public void updateMyInfoPw(Member loginMember) {
        System.out.println("\n[비밀번호 변경]\n");

        System.out.print("현재 비밀번호를 입력해주세요 : ");
        String currentPw = sc.next();

        String newPw1;
        String newPw2;

        while (true) {
            System.out.print("새 비밀번호 : ");
            newPw1 = sc.next();

            System.out.print("새 비밀번호 확인 : ");
            newPw2 = sc.next();

            if (newPw1.equals(newPw2)) break;
            else System.out.println("\n[새 비밀번호가 다릅니다. 다시 입력해주세요.]\n");
        }

        try {

            int result = service.updateMyInfoPw(loginMember.getMemberNo(), newPw1, currentPw);

            if (result > 0) System.out.println("\n비밀번호가 변경되었습니다.\n");
            else System.out.println("\n현재 비밀번호가 일치하지 않습니다.\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMyId(Member loginMember) {
        System.out.println("\n[내 정보 변경]\n");

        System.out.print("변경할 아이디 : ");
        String memberId = sc.next();

        System.out.print("변경할 이름 : ");
        String memberName = sc.next();

        System.out.print("변경할 주소 : ");
        sc.nextLine();
        String memberAdress = sc.nextLine();

        System.out.print("변경할 핸드폰 번호 : ");
        String phoneNumber = sc.next();

        Member updateMember = new Member();
        updateMember.setMemberId(memberId);
        updateMember.setMemberName(memberName);
        updateMember.setMemberNo(loginMember.getMemberNo());
        updateMember.setMemberAdress(memberAdress);
        updateMember.setPhoneNumber(phoneNumber);

        try {
            int result = service.updateMyId(updateMember);

            if (result > 0) {
                System.out.println("\n회원 정보가 수정되었습니다.\n");

                loginMember.setMemberId(memberId);
                loginMember.setMemberName(memberName);

            } else System.out.println("\n회원 정보 수정에 실패하였습니다.\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
