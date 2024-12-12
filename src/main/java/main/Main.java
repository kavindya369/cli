package main;

import config.Configuration;
import core.TicketPool;
import logging.Logger;
import threads.Customer;
import threads.Vendor;
import ui.CLI;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Thread vendorThread;
    private static Thread customerThread;
    private static TicketPool ticketPool;
    private static boolean isRunning = false;

    public static void main(String[] args) {
        Configuration config = CLI.configureSystem();

        try {
            config.saveToFile("src/main/resources/config.json");
            Logger.log("Configuration saved to config.json.");
        } catch (IOException e) {
            Logger.log("Error saving configuration to config.json: " + e.getMessage());
        }

        ticketPool = new TicketPool(config.getMaxTicketCapacity());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Ticketing System Control ---");
            System.out.println("1. Start Vendor");
            System.out.println("2. Start Customer");
            System.out.println("3. Stop Vendor");
            System.out.println("4. Stop Customer");
            System.out.println("5. Stop All (Exit)");
            System.out.println("6. Show Ticket Pool Status");
            System.out.print("Enter command: ");

            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    startVendor(config);
                    break;
                case "2":
                    startCustomer(config);
                    break;
                case "3":
                    stopVendor();
                    break;
                case "4":
                    stopCustomer();
                    break;
                case "5":
                    stopAll();
                    return; // Exit the program
                case "6":
                    ticketPool.showPoolStatus();
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private static void startVendor(Configuration config) {
        if (isRunning) {
            System.out.println("System is already running.");
            return;
        }

        vendorThread = new Thread(new Vendor(ticketPool, config.getTicketReleaseRate(), 1));
        vendorThread.start();
        isRunning = true;
        Logger.log("Vendor started.");
    }

    private static void startCustomer(Configuration config) {
        if (isRunning) {
            System.out.println("System is already running.");
            return;
        }

        customerThread = new Thread(new Customer(ticketPool, 1, config.getCustomerRetrievalRate()));
        customerThread.start();
        isRunning = true;
        Logger.log("Customer started.");
    }

    private static void stopVendor() {
        if (vendorThread != null && vendorThread.isAlive()) {
            vendorThread.interrupt();
            Logger.log("Vendor stopped.");
        } else {
            System.out.println("Vendor is not running.");
        }
    }

    private static void stopCustomer() {
        if (customerThread != null && customerThread.isAlive()) {
            customerThread.interrupt();
            Logger.log("Customer stopped.");
        } else {
            System.out.println("Customer is not running.");
        }
    }

    private static void stopAll() {
        stopVendor();
        stopCustomer();
        System.out.println("System has been stopped.");
        Logger.log("System stopped.");
    }
}
