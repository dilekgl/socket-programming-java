package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        ServerSocket server = new ServerSocket(3674);
        while(true) {
            Socket client = server.accept();
            InputStreamReader isr = new InputStreamReader(client.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            PrintWriter printWriter = new PrintWriter(client.getOutputStream());
            System.out.println("android'ten gelen veri:" + br.readLine());
            printWriter.flush();

            client.close();
            printWriter.close();
            isr.close();
            br.close();
        }
    }
}
