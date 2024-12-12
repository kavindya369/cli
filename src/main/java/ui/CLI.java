package ui;

import config.Configuration;
import logging.Logger;

import java.util.Scanner;

public class CLI {
    public static Configuration configureSystem() {
        Scanner scanner = new Scanner(System.in);
        Logger.log("Starting system configuration...");

        // Gather input from the user
        int maxTicketCapacity = getInput(scanner, "Enter Max Ticket Capacity: ", true);

        int totalTickets;
        while (true) {
            totalTickets = getInput(scanner, "Enter Total Tickets: ", false);
            if (totalTickets <= maxTicketCapacity) {
                break;
            } else {
                System.out.println("Total Tickets cannot exceed Max Ticket Capacity. Please enter again.");
            }
        }

        int ticketReleaseRate;
        while (true) {
            ticketReleaseRate = getInput(scanner, "Enter Ticket Release Rate: ", false);
            if (ticketReleaseRate <= totalTickets) {
                break;
            } else {
                System.out.println("Ticket Release Rate cannot exceed Total Tickets. Please enter again.");
            }
        }

        int customerRetrievalRate;
        while (true) {
            customerRetrievalRate = getInput(scanner, "Enter Customer Retrieval Rate: ", false);
            if (customerRetrievalRate <= totalTickets) {
                break;
            } else {
                System.out.println("Customer Retrieval Rate cannot exceed Total Tickets. Please enter again.");
            }
        }

        Logger.log("System configured successfully.");

        return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
    }

    private static int getInput(Scanner scanner, String prompt, boolean isMaxTicketCapacity) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value > 0) {
                    if (isMaxTicketCapacity && value <= 0) {
                        System.out.println("Max Ticket Capacity should be a positive value greater than 0.");
                    } else {
                        return value;
                    }
                } else {
                    System.out.println("Value must be positive. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
