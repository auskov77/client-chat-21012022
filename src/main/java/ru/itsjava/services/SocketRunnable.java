package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.net.Socket;

@RequiredArgsConstructor
public class SocketRunnable implements Runnable {
    private final Socket socket;
    public int checkClient = 0;
//    public ClientService clientService;

    @SneakyThrows
    @Override
    public void run() {
        MessageInputService serverReader =
                new MessageInputServiceImpl(socket.getInputStream());

        while (true) {
            if (serverReader.getMessage().contains("autho")) {
                checkClient = 1;
                System.out.println("Вы успешно авторизовались! - server");
            } else if (serverReader.getMessage().contains("not autho")) {
                checkClient = 2;
                System.out.println("авторизация не прошла - server");
            } else if (serverReader.getMessage().contains("reg")) {
                checkClient = 3;
                System.out.println("вы зарегистрированы - server");
            }
            System.out.println(serverReader.getMessage());
        }
    }

    @SneakyThrows
    public boolean getCheck() {
        if (checkClient != 0) {
            return true;
        }
        return false;
    }
}