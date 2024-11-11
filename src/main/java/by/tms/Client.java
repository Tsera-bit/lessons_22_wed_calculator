package by.tms;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

/**
 * Author simonpirko
 * Created on 7.11.24
 */

public class Client {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        /*
        System.out.println("Enter your name: ");
        String name = scanner.next();
        System.out.println("Enter your age: ");
        int age = scanner.nextInt();

        HttpRequest request = HttpRequest.newBuilder()
               .uri(new URI("http://localhost:8080/greeting?name=%s&age=%s".formatted(name, age))).GET().build();
        */
        System.out.print("Enter the first number: ");
        int firstNum = scanner.nextInt();
        System.out.print("Enter the second number: ");
        int secondNum = scanner.nextInt();
        System.out.print("Enter the operator: ");
        char operator = scanner.next().charAt(0);
        scanner.close();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/calc?firstNum=%s&secondNum=%s&operator=%s"
                        .formatted(firstNum, secondNum, operator)))
                .GET().build();

        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
        String result = send.body();
        System.out.println(result);
    }
}
