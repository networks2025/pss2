# 🧪 Assignment: Gopher Server with Threading Support (Java Edition)

## 🎯 Goal

Create a basic **Gopher server** and **Gopher client** in **Java**. The server must support **threading**, meaning it should spawn a **new thread** for each incoming client connection.

---

## 📌 Requirements

### ✅ Gopher Server (`GopherServer.java`)

- Built using Java SE (no frameworks)
- Listens for TCP connections on a configurable port (default: `70`)
- For **each client connection**, it:
  - Accepts the connection using a `ServerSocket`
  - Spawns a **new thread** using the `Thread` class
  - Delegates the connection to a `Worker` instance

### ✅ Worker (`Worker.java`)

- Handles each client connection in its own thread
- Reads selector requests line-by-line from the client
- For each selector:
  - If the selector contains `"alien"`, it returns the content of `alien.gmi`
  - Otherwise, it returns `"❌ Path Not Found"`
- Sends responses back to the client, terminated with:
  - Each line ending in `\r\n`
  - The end of a response marked by a line containing only `.`
- Logs the **thread ID** and a **unique socket identifier** for debugging

### ✅ Gopher Client (`GopherClient.java`)

- Connects to the Gopher server via TCP
- Allows user to type Gopher selectors from the terminal
- Sends each selector to the server followed by `\r\n`
- Receives the response from the server and prints it
- Supports an `exit` command to close the connection

---

## 📁 Files

- `GopherServer.java` — main server accepting connections
- `Worker.java` — worker class handling each connection in a thread
- `GopherClient.java` — command-line client for testing
- `alien.gmi` — sample file returned for `/alien` request

---

## 🚀 How to Run

1. **Compile the code:**
   ```bash
   javac GopherServer.java Worker.java GopherClient.java
   ```
