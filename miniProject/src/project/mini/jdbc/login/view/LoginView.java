package project.mini.jdbc.login.view;

import project.mini.jdbc.login.loginMenu.service.LoginMenuService;
import project.mini.jdbc.login.loginMenu.vo.Menu;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class LoginView {

    private Scanner sc = new Scanner(System.in);
    private LoginMenuService service = new LoginMenuService();

    public void selectMenu() {

        List<Menu> menuList = null;

        System.out.println("\n[메뉴 조회]\n");

        System.out.println("1. 중식 조회");
        System.out.println("2. 일식 조회");
        System.out.println("3. 한식 조회");
        System.out.println("4. 양식 조회");
        System.out.println("0. 메인 화면으로 돌아가기");

        System.out.print("원하시는 식당 메뉴를 골라주세요 >> ");
        int menuNum = sc.nextInt();
        int price = 0;

        try {
            switch (menuNum) {
                case 1: case 2: case 3: case 4:
                    menuList = service.selectMenu(menuNum);

                    if (menuList.isEmpty()) System.out.println("메뉴가 없습니다.\n");
                    else {
                        System.out.println("식당번호||메뉴번호||     메뉴이름     || 가격");
                        System.out.println("-------------------------------------------------");
                        for (Menu menu : menuList) {
                            System.out.printf("%-4d  || %-4d || %-15s || %-15s\n",
                                    menu.getRestaurantNum(), menu.getMenuNum(), menu.getMenuName(), menu.getMenuPrice());
                        }
                        System.out.println("-------------------------------------------------");
                        System.out.println();
                    }

                    break;
                case 0: System.out.println("메인으로 돌아갑니다.\n");break;
                default: System.out.println("잘못 입력하셨습니다. 메뉴에 있는 번호를 입력해주세요.\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int orderMenu() {
        int num = -1;
        int price = 0;

        while (num != 2) {
            System.out.println("주문하실 메뉴의 식당번호와 메뉴번호를 입력해주세요.");

            System.out.print("식당번호 : ");
            int restaurantNum = sc.nextInt();
            System.out.print("메뉴번호 : ");
            int foodNum = sc.nextInt();

            try {
                price += service.orderMenu(restaurantNum, foodNum);
            } catch (Exception e) {
                e.printStackTrace();
            }


            System.out.println("\n1. 추가 주문하기");
            System.out.println("2. 주문하기 종료");
            System.out.print("입력 >> ");
            num = sc.nextInt();
        }

        System.out.println("\n총 금액 : " + price + "원");
        System.out.print("주문하시겠습니까?(Y/N) >> ");
        char ch = sc.next().toUpperCase().charAt(0);

        if (ch == 'Y') {
            System.out.println("\n!!주문 완료!!\n");
            price = 0;
        } else System.out.println("메인으로 돌아갑니다.\n");

        return price;
    }
}
