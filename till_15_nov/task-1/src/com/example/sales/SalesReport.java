package com.example.sales;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SalesReport {

    static class SaleRecord {
        String id;
        LocalDate date;
        String customer;
        String product;
        int quantity;
        double unitPrice;
        String currency;

        SaleRecord(String id, LocalDate date, String customer, String product, int quantity, double unitPrice, String currency) {
            this.id = id;
            this.date = date;
            this.customer = customer;
            this.product = product;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.currency = currency;
        }

        static SaleRecord fromCsvLine(String line) {
            String[] parts = line.split(",");
            return new SaleRecord(
                    parts[0].trim(),
                    LocalDate.parse(parts[1].trim()),
                    parts[2].trim(),
                    parts[3].trim(),
                    Integer.parseInt(parts[4].trim()),
                    Double.parseDouble(parts[5].trim()),
                    parts[6].trim()
            );
        }

        double total() {
            return quantity * unitPrice;
        }
    }
    static class ExchangeRateService {
        private static final Map<String, Double> RATES = Map.of(
                "USD", 82.0,
                "EUR", 90.0,
                "INR", 1.0
        );
        static CompletableFuture<Double> getRateAsync(String currency) {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    long delay = 100 + ThreadLocalRandom.current().nextInt(300);
                    TimeUnit.MILLISECONDS.sleep(delay);
                    System.out.printf("[Thread: %s] Fetching rate for %s...%n",
                            Thread.currentThread().getName(), currency);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return RATES.getOrDefault(currency, 1.0);
            });
        }
    }

    public static void main(String[] args) {
        Path csvPath = Path.of("C:\\Users\\kunal\\chubb_workspace\\task-1\\src\\sales.txt");
        System.out.println("File exists: " + Files.exists(csvPath));

        try (Stream<String> lines = Files.lines(csvPath)) {

            List<SaleRecord> sales = lines
                    .filter(line -> !line.isBlank())
                    .map(SaleRecord::fromCsvLine)
                    .collect(Collectors.toList());

            // Report 1: Total Revenue
            double totalRevenue = sales.stream()
                    .mapToDouble(SaleRecord::total)
                    .sum();
            System.out.printf("Total Revenue (in USD): %.2f%n", totalRevenue);

            // Report 2: Revenue by Product
            Map<String, Double> revenueByProduct = sales.stream()
                    .collect(Collectors.groupingBy(r -> r.product,
                            Collectors.summingDouble(SaleRecord::total)));
            System.out.println("\nRevenue by Product:");
            revenueByProduct.forEach((p, v) -> System.out.printf("  %s -> %.2f USD%n", p, v));

            // Report 3: Revenue by Customer
            Map<String, Double> revenueByCustomer = sales.stream().collect(Collectors.groupingBy(r -> r.customer,Collectors.summingDouble(SaleRecord::total)));
            System.out.println("\nRevenue by Customer:");
            revenueByCustomer.forEach((c, v) -> System.out.printf("  %s -> %.2f USD%n", c, v));
            System.out.println("\nFetching exchange rates asynchronously...\n");
            List<CompletableFuture<Double>> futures = sales.stream()
                    .map(r -> ExchangeRateService.getRateAsync(r.currency)
                    		.thenApply(rate -> r.total() * rate))  
                    .collect(Collectors.toList());
            CompletableFuture<Void> allDone =CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
            double totalInINR = allDone.thenApply(v ->futures.stream().mapToDouble(CompletableFuture::join).sum()).join();
            System.out.printf("\n Total Revenue (converted to INR using async API): %.2f%n", totalInINR);
        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }
    }
}
