package ru.itsjava.services;

public interface MenuService {
    void menu();
    void printMenu();
//    void displayTheMenu();
//    void displayTheMenu(String login, String password);
//    void getLogin(String login);
//    void getPassword(String password);
    String getLogin();
    String getPassword();
}
