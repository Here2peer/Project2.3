package ConnectionHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

class Client {
    private SocketChannel socket;

    private ByteBuffer inputBuffer = ByteBuffer.allocate(256);
    private ByteBuffer outputBuffer = ByteBuffer.allocate(182);

    private BlockingQueue<String> inBoundMessageQueue;


    Client(BlockingQueue<String> inBoundMessageQueue) {
        this.inBoundMessageQueue = inBoundMessageQueue;

        initialiseClient();
    }

    private void initialiseClient() {
        initialiseConnection();
        new InputReader();              //todo: make this obsolete
        new Thread(() -> {
            while(true) {
                readChannel();
            }
        }).start();
        try {
            System.out.println(inBoundMessageQueue.take());
            System.out.println(inBoundMessageQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void initialiseConnection() {
        boolean initialised = false;
        while (!initialised) {
            try {
                socket = SocketChannel.open();
                socket.connect(new InetSocketAddress(Settings.SERVER_IP, Settings.PORTNUMBER));
                socket.configureBlocking(true);
            } catch (IOException e) {
                //e.printStackTrace();
                System.err.println("could'nt open connection");               //uh oh, probeer het nog eens until return true
                initialised = false;
            }
            initialised = true;
        }
    }


    boolean closeConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("couldnt close connection");
            //todo wat nu?
            return false;
        }
        System.out.println("closed connection");
        return true;
    }

    void executeCommand(String command) throws InterruptedException {
        System.out.println("sending " + command);
        outputBuffer.clear();                               //ready 2 b written in
        command += "\n";                                    //!
        outputBuffer.put(command.getBytes());

        outputBuffer.flip();                                //ready 2 write
        if (socket != null) {
            if (socket.isConnected() && socket.isOpen()) {
                try {
                    while (outputBuffer.hasRemaining()) {
                        socket.write(outputBuffer);                 //write
                    }
                } catch (IOException e) {
                    closeConnection();
                    e.printStackTrace();
                }
            } else {
                //todo wat doen we als de verbinding faalt?
            }
        }
    }

    /**
     * read from SocketChannel and add read messages to inbound message blockingqueue
     * @return
     */
    private String readChannel() {
        int bytesRead;

        if (socket.isConnected() && socket.isOpen()) {
            try {
                inputBuffer.clear();
                bytesRead = socket.read(inputBuffer);
                if (bytesRead < 0) {
                    return "end of stream reached";
                    //todo stream is gesloten, verbinding verbroken. wat nu?
                } else {
                    inputBuffer.flip();
                    String output = "";
                    while (inputBuffer.hasRemaining()) {
                        output += (char) inputBuffer.get();
                    }
                    String lines[] = output.split("\\r?\\n");
                    for(String line : lines) {
                        inBoundMessageQueue.offer(line.trim());
                    }
                    return output;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "failed to read";
                //todo wat nu?
            }
        } else {
            //todo wat doen we als de verbinding faalt?
            return null;
        }
    }

    class InputReader{
        InputReader() {
            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    if (scanner.hasNext()) {
                        String nextline = scanner.nextLine();
                        if (nextline.equals("init")) initialiseConnection();         //handmatige connectie
                        else if (nextline.equals("close")) closeConnection();              //handmatige close
                        else
                            try {
                                executeCommand(nextline);                                     //verstuur commando
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                    } else {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
