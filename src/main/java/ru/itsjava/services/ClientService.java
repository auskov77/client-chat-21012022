package ru.itsjava.services;

public interface ClientService {
    void start();
    void authorizationUser();
    void registrationNewUser();
    void displayTheMenu();
    int statusMsgSrvAutho();
    int statusMsgSrvNotAutho();
    int statusMsgSrvReg();
}