Ticketing System - CLI
A simple CLI-based ticketing system that simulates multiple vendors releasing tickets and customers purchasing them concurrently using Java’s multi-threading features.

Features
Configuration Setup: Configure system parameters via CLI (total tickets, release rates, max capacity).
Producer-Consumer Pattern: Vendors (producers) release tickets and customers (consumers) purchase them.
Real-Time Monitoring: View ticket pool status and transaction logs in real-time.
Thread Safety: Uses synchronization to handle concurrent operations.
Logging: Logs all ticket sales and additions to the console and optional log files.

Installation
Clone the Repository:
bash
git clone 
cd ticketing-system

Build the Project:
mvn clean install
Run the Program:

Upon starting, the system will prompt you for the following parameters:

Total Tickets
Ticket Release Rate
Customer Retrieval Rate
Max Ticket Capacity

Commands
1: Start Vendor (Add tickets)
2: Start Customer (Purchase tickets)
3: Stop Vendor
4: Stop Customer
5: Stop All (Exit)
6: Show Ticket Pool Status

File Structure

├── src
│   ├── Configuration.java
│   ├── Logger.java
│   ├── Main.java
│   ├── TicketPool.java
│   ├── Vendor.java
│   ├── Customer.java
├── pom.xml
└── config.json (optional)


