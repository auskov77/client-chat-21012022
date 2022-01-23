package ru.itsjava.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
@Getter
public class MenuServiceImpl implements MenuService {
    private final ClientService clientService;
    public int numMenu = 0;

    @Override
    public void menu() {
        printMenu();
        Scanner console = new Scanner(System.in);

        System.out.println("Выберите пункт из меню");
        numMenu = console.nextInt();

        if (numMenu == 1) {
            System.out.println("Вы выбрали авторизацию");
            clientService.authorizationUser();
        } else if (numMenu == 2) {
            System.out.println("Вы выбрали регистрацию");
            clientService.registrationNewUser();
        }
    }

    @Override
    public void printMenu() {
        System.out.println("1 - авторизация; 2 - регистрация");
    }


//    @Override
//    public void displayTheMenu(String login, String password) {
//        Scanner console = new Scanner(System.in);
//        System.out.println("Введите свой логин:");
//        login = console.nextLine();
//        System.out.println("Введите свой пароль:");
//        password = console.nextLine();
//    }

    @Override
    public String getLogin() {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите свой логин:");
        return console.nextLine();
    }

    @Override
    public String getPassword() {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите свой пароль:");
        return console.nextLine();
    }
}
