// GopherClient.java
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GopherClient {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 70;

        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("\u2705 Connected to Gopher server at " + HOST + ":" + PORT);

            while (true) {
                System.out.print("\n Enter Gopher selector (or type 'exit' to quit): ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                String request = input + "\r\n";
                out.write(request);
                out.flush();

                System.out.println("\uD83D\uDCE9 Response from server:");
                String line;
                while ((line = in.readLine()) != null && !line.equals(".")) {
                    System.out.println(line);
                    if (!in.ready()) break;
                }
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("Disconnected from server.");
    }
}
