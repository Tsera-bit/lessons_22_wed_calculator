package by.tms;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


class CalculatorHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();
        Map<String, String> parameters = getParameters(query);

        int firstNum = Integer.parseInt(parameters.get("firstNum"));
        int secondNum = Integer.parseInt(parameters.get("secondNum"));
        char operation = parameters.get("operation").charAt(0);

        String message  = switch (operation) {
            case '+' -> firstNum + "+" + secondNum + "=" + (firstNum + secondNum);
            case '-' -> firstNum + "-" + secondNum + "=" + (firstNum - secondNum);
            case '*' -> firstNum + "*" + secondNum + "=" + (firstNum * secondNum);
            case '/' -> firstNum + "/" + secondNum + "=" + String.format("%.2f",(double) firstNum / secondNum);
            default -> "Error";
        };

        exchange.sendResponseHeaders(200, message.length());
        exchange.getResponseBody().write(message.getBytes());
        exchange.getResponseBody().close();

    }

    Map<String, String> getParameters(String query) {
        Map<String, String> numbers = new HashMap<>();
        String[] split = query.split("&");
        for (String s : split) {
            String[] split1 = s.split("=");
            numbers.put(split1[0], split1[1]);
        }
        return numbers;
    }
}
