# 🧪 Assignment: Gopher Server with Threading Support

## 🎯 Goal

Create a basic **Gopher server** and **Gopher client** in **Node.js with TypeScript**. The server must support **threading**, meaning it should spawn a **new thread** (using `worker_threads`) for each incoming connection.

---

## 📌 Requirements

### ✅ Gopher Server

- Built with Node.js and TypeScript
- Listens for TCP connections on a configurable port (default: `70`)
- For **each client connection**, it:
  - Spawns a **new thread** using the `worker_threads` module
  - Passes the Gopher **selector** (text line from client) to the worker
  - Receives a **response string** from the worker
  - Sends the response back to the client and closes the connection

### ✅ Gopher Worker

- Receives the selector string via `workerData`
- Constructs a valid **Gopher menu** response:
  - Each line ends with `\r\n`
  - The menu ends with a single dot on a line: `.`
- Sends the response to the main thread via `postMessage`

### ✅ Gopher Client

- Connects to the Gopher server
- Sends a selector string (or just a blank line `\r\n`)
- Receives and prints the response from the server

---

