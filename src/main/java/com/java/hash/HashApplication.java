package com.java.hash;

import com.java.hash.vo.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class HashApplication {

    static Scanner in = new Scanner(System.in);
    static User user = new User();

    public static void main(String[] args) {
        String id;
        String pwd;
        while(true) {
            System.out.println("1 : 회원가입 \t 2 : 로그인 \t 3 : 종료 ");
            System.out.print(">>>>>> ");
            switch (in.nextLine()) {
                case "1":
                    System.out.print("id >> ");
                    id = in.nextLine();
                    System.out.print("pwd >> ");
                    pwd = in.nextLine();
                    user.create(id, pwd.getBytes());
                    break;
                case "2":
                    System.out.print("id >> ");
                    id = in.nextLine();
                    System.out.print("pwd >> ");
                    pwd = in.nextLine();
                    user.get(id, pwd.getBytes());
                    break;
                case "3":
                    log.info("exit bye bye");
                    break;
                case "1004":
                    user.get();
                    break;
                case "9999":
                    user.get();
                    break;
                default:
                    log.info("re write");
                    System.out.println();
                    break;
            }
        }
    }
}
