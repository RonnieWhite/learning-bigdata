package practise_java.review.review_test;

import practise_java.review.review_dao.UserDao;
import practise_java.review.review_dao.impl.UserDaoImpl;
import practise_java.review.review_game.GuessNumber;
import practise_java.review.review_pojo.User;

import java.util.Scanner;

public class UserTest {
    public static void main(String[] args) {
        while (true) {
            System.out.println("-------欢迎光临-------");
            System.out.println("1 登录");
            System.out.println("2 注册");
            System.out.println("3 退出");
            System.out.println("请输入你的选择：");
            Scanner sc = new Scanner(System.in);
            String choiceString = sc.nextLine();
            UserDao ud = new UserDaoImpl();
            switch (choiceString) {
                case "1":
                    System.out.println("-------登录界面------");
                    System.out.println("请输入用户名：");
                    String username = sc.nextLine();
                    System.out.println("请输入密码：");
                    String password = sc.nextLine();
                    boolean flag = ud.login(username, password);
                    if (flag) {
                        System.out.println("登陆成功，可以开始玩游戏了");
                        System.out.println("你玩吗？y/n");
                        while (true) {
                            String resultString = sc.nextLine();
                            if (resultString.equalsIgnoreCase("y")) {
                                GuessNumber.start();
                                System.out.println("你还玩吗？y/n");
                            } else {
                                break;
                            }
                        }
                        System.out.println("谢谢使用，欢迎下次再来！");
                        System.exit(0);
                    } else {
                        System.out.println("用户名或者密码有误，登录失败");
                    }
                    break;
                case "2":
                    System.out.println("-------注册界面------");
                    System.out.println("请输入用户名：");
                    String newUsername = sc.nextLine();
                    System.out.println("请输入密码：");
                    String newPassword = sc.nextLine();
                    User user = new User();
                    user.setUsername(newUsername);
                    user.setPassword(newPassword);
                    ud.regist(user);
                    System.out.println("注册成功！");
                    break;
                case "3":
                default:
                    System.out.println("欢迎下次使用！");
                    System.exit(0);
                    break;
            }
        }
    }
}
