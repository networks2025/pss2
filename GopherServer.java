import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GopherServer {
    private static final int PORT = 70;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Gopher server is listening on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                int fd = clientSocket.getInputStream().hashCode(); // Not exact fd but a unique ID
                System.out.println("Accepted connection from " + clientSocket.getInetAddress() + " | FD hash: " + fd);
                new Thread(new Worker(clientSocket, fd)).start();
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}