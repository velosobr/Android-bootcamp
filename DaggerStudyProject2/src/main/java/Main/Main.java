package Main;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandRouterFactory commandRouterFactory = CommandRouterFactory.create();
        CommandRouter commandRouter = commandRouterFactory.router();

        while (scanner.hasNextLine()) {
            commandRouter.route(scanner.nextLine());
        }
    }
}
