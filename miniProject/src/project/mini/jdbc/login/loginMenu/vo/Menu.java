package project.mini.jdbc.login.loginMenu.vo;

public class Menu {

    private int restaurantNum;
    private int menuNum;
    private String menuName;
    private String menuPrice;

    private int reviewNo;
    private String reviewContent;

    public Menu() {}

    public Menu(int restaurantNum, int menuNum, String menuName, String menuPrice) {
        this.restaurantNum = restaurantNum;
        this.menuNum = menuNum;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }

    public Menu(int reviewNo, String reviewContent) {
        this.reviewNo = reviewNo;
        this.reviewContent = reviewContent;
    }

    public int getRestaurantNum() {
        return restaurantNum;
    }

    public void setRestaurantNum(int restaurantNum) {
        this.restaurantNum = restaurantNum;
    }

    public int getMenuNum() {
        return menuNum;
    }

    public void setMenuNum(int menuNum) {
        this.menuNum = menuNum;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public int getReviewNo() {
        return reviewNo;
    }

    public void setReviewNo(int reviewNo) {
        this.reviewNo = reviewNo;
    }
}
