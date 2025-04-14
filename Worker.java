import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Worker implements Runnable {
    private final Socket socket;
    private final int fd;

    public Worker(Socket socket, int fd) {
        this.socket = socket;
        this.fd = fd;
    }

    @Override
    public void run() {
        long threadId = Thread.currentThread().getId();
        System.out.println("Worker [ID: " + threadId + "] now handling connection with FD hash " + fd);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            String request;
            while ((request = in.readLine()) != null) {
                request = request.trim();
                System.out.println("[Worker " + threadId + "] Received request: " + request);

                String response;
                if (request.contains("alien")) {
                    try {
                        response = Files.readString(Paths.get("alien.gmi"));
                    } catch (IOException e) {
                        response = "Error reading alien.gmi";
                    }
                } else {
                    response = "\u274C Path Not Found";
                }

                out.write(response + "\r\n.\r\n");
                out.flush();
            }

        } catch (IOException e) {
            System.err.println("[Worker " + threadId + "] Error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("[Worker " + threadId + "] Error closing socket: " + e.getMessage());
            }
        }
    }
}