package ru.itsjava.services;

import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.net.Socket;

public class ClientServiceImpl implements ClientService {
    public final static int PORT = 8081;
    public final static String HOST = "localhost";
    boolean isExit = false;
    public int statusMenu = 0;
    public int statusMsgSrvAutho = 0;
    public int statusMsgSrvNotAutho = 0;
    public int statusMsgSrvReg = 0;
    public SocketRunnable socketRunnable;

    @SneakyThrows
    @Override
    public void start() {
        Socket socket = new Socket(HOST, PORT);
        if (socket.isConnected()) {
            new Thread(new SocketRunnable(socket)).start();

            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());
            MessageInputService messageInputService =
                    new MessageInputServiceImpl(System.in);

            MenuService menuService = new MenuServiceImpl(this);
            menuService.menu();
            String login = menuService.getLogin();
            String password = menuService.getPassword();

            if (statusMenu == 1) {
//            !autho!login:password
                serverWriter.println("!autho!" + login + ":" + password);
            } else if (statusMenu == 2) {
//            !reg!login:password
                serverWriter.println("!reg!" + login + ":" + password);
            }
            serverWriter.flush();

            Thread.sleep(3000);


//            while (socketRunnable.getCheckAutho() == 1 || socketRunnable.getCheckReg() == 1 || socketRunnable.getCheckNotAutho() == 1) {
//                if (socketRunnable.getCheckAutho() == 1) {
//                    System.out.println("Вы успешно авторизованы!" + "client");
//                    break;
//                } else if (socketRunnable.getCheckNotAutho() == 1) {
//                    System.out.println("Вы не авторизованы!" + "client");
//                    System.out.println("Начинаю повторную авторизацию");
//                    displayTheMenu();
//                    serverWriter.println("!autho!" + login + ":" + password);
//                    serverWriter.flush();
//                } else if (socketRunnable.getCheckReg() == 1) {
//                    System.out.println("Вы успешно зарегистрированы!" + "client");
//                    break;
//                }
//
//            }

            while (socketRunnable.getCheck()) {
                if (socketRunnable.checkClient == 1) {
                    System.out.println("Вы успешно авторизованы! - client");
//                    break;
                } else if (socketRunnable.checkClient == 2) {
                    System.out.println("Вы не авторизованы! - client");
                    System.out.println("Начинаю повторную авторизацию");
                    displayTheMenu();
                    serverWriter.println("!autho!" + login + ":" + password);
                    serverWriter.flush();
                } else if (socketRunnable.checkClient == 3) {
                    System.out.println("Вы успешно зарегистрированы! - client");
//                    break;
                }

            }


            while (true) {
                System.out.println();
                System.out.println("Можете начинать общение");
                String consoleMessage = messageInputService.getMessage();

                isExit = consoleMessage.equals("exit");
                // проверка ввода клиентом слова "exit"
                if (isExit) {
                    serverWriter.println("Всем пока!");
                    serverWriter.flush();
                    System.exit(0);
                }

                serverWriter.println(consoleMessage);
                serverWriter.flush();
            }
        }
    }


    @SneakyThrows
    @Override
    public void authorizationUser() {
        statusMenu = 1;
    }

    @SneakyThrows
    @Override
    public void registrationNewUser() {
        statusMenu = 2;
    }

    @Override
    public void displayTheMenu() {

    }

    @SneakyThrows
    @Override
    public int statusMsgSrvAutho() {
        statusMsgSrvAutho = 1;
        return statusMsgSrvAutho;
    }

    @SneakyThrows
    @Override
    public int statusMsgSrvNotAutho() {
        statusMsgSrvNotAutho = 1;
        return statusMsgSrvNotAutho;
    }

    @SneakyThrows
    @Override
    public int statusMsgSrvReg() {
        statusMsgSrvReg = 1;
        return statusMsgSrvReg;
    }
}
