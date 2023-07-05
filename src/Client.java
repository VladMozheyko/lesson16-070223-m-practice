import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    /*
    Для соединения с сервером нужно указать его ip-адрес и порт
    Укажем localhost, потому что мы работаем только в пределах текущего компьютера.
    localhost - 127.0.0.1
     */

    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket("127.0.0.1", 8015) ;  // Создаем сокет для работы с сервером


        OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());  // Создаем поток записи для отправки сообщений серверу

        Scanner scanner = new Scanner(System.in); // Создаем сканер для чтения ввода из консоли
        System.out.println("Введите строку");     // Приглашаем сделать ввод
        String str = scanner.nextLine();          // Читаем ввод
        writer.write(str+ "\n");                  // Отправляю сообщение серверу
        writer.flush();                           // Команда для отправки всех строк в буфере ( строку можно просто переписать)

        BufferedReader bufReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  // Создаем поток для чтения ответа от сервера
        String message = bufReader.readLine();  //  Принимаю сообщение от сервера
        System.out.println(message);       //  Вывожу его на консоль
        bufReader.close();          //  Закрываю потоки и соединение
        writer.close();
        clientSocket.close();
        scanner.close();          // Сканер тоже занимает ресурсы, поэтому, если он не нужен его необходимо закрыть
    }
}
