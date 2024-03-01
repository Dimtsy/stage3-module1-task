package com.mjc.school;

import com.mjc.school.dto.NewsDto;
import com.mjc.school.impl.NewsServiceImpl;
import com.mjc.school.exeption.InfoException;

import java.util.Scanner;

import static java.lang.System.*;

public class MainMenu {

    NewsServiceImpl newsRun = new NewsServiceImpl();

    public void menuRun() {
        boolean status = true;
        while (status) {
            System.out.println("Enter the number of operation:\n" +
                    "1 - Get all news.\n" +
                    "2 - Get news by id.\n" +
                    "3 - Create news.\n" +
                    "4 - Update news.\n" +
                    "5 - Remove news by id.\n" +
                    "0 - Exit.");
            Scanner input = new Scanner(in);

            switch (input.nextLine()) {
                case "1" -> {
                    System.out.print("Operation: Get all news.\n");
                    System.out.println(newsRun.toString(newsRun.allNews()));
                }
                case "2" -> {
                    System.out.println("Operation: Get news by id.\n"+ "Enter news id:");
                    String newsId = input.nextLine();
                    try {
                        System.out.println(newsRun.toString(newsRun.newsBId(Long.parseLong(newsId) - 1)));
                    } catch (InfoException e) {
                        System.out.println(e.getErrorInfo());
                    }

                }
                case "3" -> {
                    System.out.println("Operation: Create news.\n" + "Enter news title:");
                    String title = input.nextLine();
                    System.out.println("Enter news content:");
                    String content = input.nextLine();
                    System.out.println("Enter author id:");
                    String authorId = input.nextLine();

                    NewsDto dto = new NewsDto(title, content, Long.parseLong(authorId));

                    try {
                        System.out.println(newsRun.toString(newsRun.createNews(dto)));
                    } catch (InfoException e) {
                        System.out.println(e.getErrorInfo());
                    }

                }
                case "4" -> {
                    System.out.println("Operation: Update news.\n" + "Enter news id:");
                    String newsId = input.nextLine();
                    System.out.println("Enter news title:");
                    String title = input.nextLine();
                    System.out.println("Enter news content:");
                    String content = input.nextLine();
                    System.out.println("Enter author id:");
                    String authorId = input.nextLine();

                    NewsDto dto = new NewsDto(Long.parseLong(newsId), title, content, Long.parseLong(authorId));
                    try {
                        System.out.println(newsRun.toString(newsRun.updateNewsId(dto)));
                    } catch (InfoException e) {
                        System.out.println(e.getErrorInfo());
                    }
                }
                case "5" -> {
                    System.out.println("Operation: Remove news by id.\n" + "Enter news id:");
                    String newsId = input.nextLine();
                    try {
                        System.out.println(newsRun.removeNewsId(Long.parseLong(newsId) - 1));
                    } catch (InfoException e) {
                        System.out.println(e.getErrorInfo());
                    }
                }
                case "0" -> status = false;
                default -> System.out.println("ERROR_CODE:XXXX1 ERROR_MESSAGE:Command not found.");
            }
        }
    }
}
