package com.example.sales;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class salesReportApp {

    private static final Logger logger = LogManager.getLogger(salesReportApp.class);

    static class Sale {
        String id;
        LocalDate date;
        String customer;
        String product;
        int quantity;
        double unitPrice;
        String currency;

        Sale(String id, LocalDate date, String customer, String product,
             int quantity, double unitPrice, String currency) {
            this.id = id;
            this.date = date;
            this.customer = customer;
            this.product = product;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.currency = currency;
        }

        double total() {
            return quantity * unitPrice;
        }

        static Sale fromCsv(String line) {
            String[] parts = line.split(",");
            return new Sale(
                    parts[0].trim(),
                    LocalDate.parse(parts[1].trim()),
                    parts[2].trim(),
                    parts[3].trim(),
                    Integer.parseInt(parts[4].trim()),
                    Double.parseDouble(parts[5].trim()),
                    parts[6].trim()
            );
        }
    }

    public static void main(String[] args) {

        Path csvPath = Path.of("C:\\Users\\kunal\\chubb_workspace\\task-1\\src\\sales.txt");

        logger.info("Starting Sales Report Application...");
        logger.info("Reading data from: {}", csvPath);

        if (!Files.exists(csvPath)) {
            logger.error("CSV file not found at {}", csvPath);
            System.err.println("File not found: " + csvPath);
            return;
        }

        try (Stream<String> lines = Files.lines(csvPath)) {

            List<Sale> sales = lines
                    .filter(line -> !line.isBlank())
                    .map(Sale::fromCsv)
                    .collect(Collectors.toList());

            logger.info("Loaded {} sales records.", sales.size());

            // --- Total Revenue ---
            double totalRevenue = sales.stream()
                    .mapToDouble(Sale::total)
                    .sum();
            logger.info("Calculated total revenue: {}", totalRevenue);
            System.out.printf("Total Revenue (USD): %.2f%n", totalRevenue);

            // --- Revenue by Product ---
            Map<String, Double> revenueByProduct = sales.stream()
                    .collect(Collectors.groupingBy(s -> s.product,
                            Collectors.summingDouble(Sale::total)));
            logger.info("Generated revenue by product report.");
            System.out.println("\nRevenue by Product:");
            revenueByProduct.forEach((p, v) -> System.out.printf("  %s -> %.2f%n", p, v));

            // --- Revenue by Customer ---
            Map<String, Double> revenueByCustomer = sales.stream()
                    .collect(Collectors.groupingBy(s -> s.customer,
                            Collectors.summingDouble(Sale::total)));
            logger.info("Generated revenue by customer report.");
            System.out.println("\nRevenue by Customer:");
            revenueByCustomer.forEach((c, v) -> System.out.printf("  %s -> %.2f%n", c, v));

            // --- Average Unit Price ---
            Map<String, Double> avgUnitPrice = sales.stream()
                    .collect(Collectors.groupingBy(s -> s.product,
                            Collectors.averagingDouble(s -> s.unitPrice)));
            logger.info("Calculated average unit price per product.");
            System.out.println("\nAverage Unit Price:");
            avgUnitPrice.forEach((prod, avg) -> System.out.printf("  %s -> %.2f%n", prod, avg));

            // --- Top Selling Product ---
            sales.stream()
                    .collect(Collectors.groupingBy(s -> s.product,
                            Collectors.summingInt(s -> s.quantity)))
                    .entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .ifPresent(top -> {
                        logger.info("Top selling product: {} ({} units)", top.getKey(), top.getValue());
                        System.out.printf("\nTop Selling Product: %s (%d units)%n",
                                top.getKey(), top.getValue());
                    });

            logger.info("Report generation completed successfully.");

        } catch (IOException e) {
            logger.error("Error reading file: {}", e.getMessage());
            System.err.println("Error reading CSV: " + e.getMessage());
        }
    }
}
