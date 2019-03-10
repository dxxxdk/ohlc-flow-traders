package com.arm;

import java.util.Scanner;

public class Main {
    private static final int OHLC_INTERVAL_SECONDS = 300; // 5 minutes

    public static void main(String[] args) {
        OrderBook orderBook = new OrderBook();
        Ohlc ohlc = new Ohlc(OHLC_INTERVAL_SECONDS);

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String inputRecord = scanner.nextLine();
                Command c = CommandFactory.parse(inputRecord);
                if (c == null) {
                    System.out.println("Invalid input record. Exiting...");
                    break;
                }
                c.execute(orderBook, ohlc);
            }
        }
    }
}
