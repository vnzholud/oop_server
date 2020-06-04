package dz04062020;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientServer {

    public static void main(String[] args) {
        ServerSocket server = null;                //Ждущий сервер
        Socket socket = null;              // Клиентский комп

        try {
            server = new ServerSocket(8189); // открываем свободный порт и обрабатываем исключения если что
            System.out.println("сервер запущен");

            socket = server.accept(); //передаем клиенту сокет для подключения
            System.out.println("Клиент подключился");

            Scanner inServer = new Scanner(socket.getInputStream());  // Сканером слушаем ввод от сервера

            Scanner inClient = new Scanner(System.in);  // Сканером слушаем ввод от клиента

            PrintWriter out = new PrintWriter(socket.getOutputStream(),true); // Более удобный метод для ввода



            // Бесконечным циклом слушаем сервер и клиентский сокет

            while (true){

                String strServer = inServer.nextLine(); // Слушаем сервер

                String strClient = inClient.nextLine(); // Слушаем консоль


                if (strServer.equals("end")){
                    System.out.println("Сервер отключился");
                    break;

                }

                System.out.println("Сервер: "+ strServer);
                out.println("Клиент: "+strClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }   finally {   //Осуществляем закрытие сокета
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {  //Осуществляем закрытие сервера
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
