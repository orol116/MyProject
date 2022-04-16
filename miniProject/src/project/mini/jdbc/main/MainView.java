package project.mini.jdbc.main;

import project.mini.jdbc.login.loginMenu.service.LoginMenuService;
import project.mini.jdbc.login.view.LoginView;
import project.mini.jdbc.login.view.Review;
import project.mini.jdbc.restaurant.menu.vo.Member;
import project.mini.jdbc.restaurant.view.RestaurantView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {

    private Scanner sc = new Scanner(System.in);
    private Member loginMember = null;

    private RestaurantView restaurantView = new RestaurantView();
    private LoginView loginView = new LoginView();
    private Review review = new Review();

    public void mainMenu() {
        int menuNum = -1;

        do {
            try {

                if (loginMember == null) {

                    System.out.println("\n************ 프로그램 접속 완료!! ************\n");

                    System.out.println("1. 로그인");
                    System.out.println("2. 회원 가입");
                    System.out.println("0. 프로그램 종료");

                    System.out.print("메뉴를 선택해주세요!! >> ");
                    menuNum = sc.nextInt();
                    sc.nextLine();

                    switch (menuNum) {
                        case 1: loginMember = restaurantView.login(); break;
                        case 2: restaurantView.signUp(); break;
                        case 0: System.out.println("\n[ 프로그램 종료 ]"); break;
                        default: System.out.println("\n메뉴에 작성된 번호를 입력해주세요.");
                    }

                } else {
                    System.out.println("\n----- 회원 메뉴 -----");

                    System.out.println("1. 내 정보 조회");
                    System.out.println("2. 비밀번호 변경");
                    System.out.println("3. 내 정보 변경");
                    System.out.println("4. 메뉴 조회");
                    System.out.println("5. 주문하기");
                    System.out.println("6. 리뷰");

                    System.out.println("9. 로그아웃");

                    System.out.print("\n메뉴를 선택해주세요 >> ");
                    menuNum = sc.nextInt();
                    sc.nextLine();
                    System.out.println();

                    switch (menuNum) {
                        case 1: restaurantView.selectMyInfo(loginMember); break;
                        case 2: restaurantView.updateMyInfoPw(loginMember); break;
                        case 3: restaurantView.updateMyId(loginMember); break;
                        case 4: loginView.selectMenu(); break;
                        case 5: loginView.orderMenu(); break;
                        case 6: review.reviewMain(); break;
                        case 9:
                            System.out.println("로그아웃 되었습니다\n");
                            loginMember = null;
                            break;
                        default:
                            System.out.println("메뉴에 작성된 번호만 입력해주세오.\n");
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("입력 형식이 잘못되었습니다.\n");
                sc.nextLine();
            }
        } while (menuNum != 0);
    }
}
