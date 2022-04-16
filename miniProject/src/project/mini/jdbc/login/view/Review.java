package project.mini.jdbc.login.view;

import project.mini.jdbc.login.loginMenu.service.LoginMenuService;
import project.mini.jdbc.login.loginMenu.vo.Menu;

import java.util.List;
import java.util.Scanner;

public class Review {

    private Scanner sc = new Scanner(System.in);
    private LoginMenuService service = new LoginMenuService();

    public void reviewMain() {

        System.out.println("\n--- 리뷰 메인 화면 ---\n");
        System.out.println("1. 리뷰 조회");
        System.out.println("2. 리뷰 작성");
        System.out.println("0. 돌아가기");
        System.out.print("원하시는 기능을 입력해주세요 >> ");
        int num = sc.nextInt();

        try {

            int No;

            switch (num) {
                case 1:
                    System.out.println("1. 중식");
                    System.out.println("2. 일식");
                    System.out.println("3. 한식");
                    System.out.println("4. 양식");
                    System.out.print("리뷰를 조회할 식당을 입력해주세요 >> ");
                    No = sc.nextInt();

                    List<Menu> menuList = service.selectReview(No);

                    if (menuList.isEmpty()) System.out.println("조회 결과가 없습니다.\n");
                    else {
                        for (Menu menu : menuList) System.out.printf("%3d  || %s\n", menu.getReviewNo(), menu.getReviewContent());
                    }
                    break;

                case 2:
                    System.out.println("\n1. 중식");
                    System.out.println("2. 일식");
                    System.out.println("3. 한식");
                    System.out.println("4. 양식");
                    System.out.print("리뷰를 작성할 식당을 입력해주세요 >> ");
                    No = sc.nextInt();
                    System.out.print("\n리뷰 글 내용을 작성해주세요 >> ");
                    sc.nextLine();
                    String reviewContent = sc.nextLine();
                    int result = service.insertReview(No, reviewContent);
                    break;
                case 0:
                    System.out.println("\n메인으로 돌아갑니다.\n");
                    break;
                default:
                    System.out.println("메뉴에 있는 번호만 입력해주세요.\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
