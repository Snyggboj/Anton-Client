/* CLIENT */
import java.io.*;
import java.net.*;
public class Client {
    public static void main(String args[]) {
        Socket client = null;

        // Default port number we are going to use
        int portNumber = 2000;
        if (args.length >= 1){
            portNumber = Integer.parseInt(args[0]);
        }

        for (int i = 0; i < 10; i++){
            try {
                String msg = "";

                // Create a client socket
                client = new Socket(InetAddress.getLocalHost(), portNumber);

                // Create an output stream of the client socket
                OutputStream clientOut = client.getOutputStream();
                PrintWriter pw = new PrintWriter(clientOut, true);

                // Create and input stream of the client socket
                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));

                // Create BufferedReader for standard input
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

                while(true) {
                    System.out.println("Skriv ett matte tal (ex. 5+8)");

                    // Read data from standard input device and write it
                    // to the output stream of the client socket.
                    msg = stdIn.readLine().trim();
                    String symbols = "+-*/";
                    if (!Character.isDigit(msg.toCharArray()[0]) || !Character.isDigit(msg.toCharArray()[2]) || symbols.indexOf(msg.toCharArray()[1]) == (-1)) {
                        System.out.println("Inte rätt form");
                    } else {
                        break;
                    }
                }
                pw.println(msg);

                // Read data from the input stream of the client socket
                System.out.println(br.readLine());
                pw.close();
                br.close();
                client.close();

                // Stop the operation
                if (msg.equalsIgnoreCase("Bye")){
                    break;
                }
            } catch (IOException ie) {
                System.out.println("I/O error " + ie);
            }
        }
    }
}