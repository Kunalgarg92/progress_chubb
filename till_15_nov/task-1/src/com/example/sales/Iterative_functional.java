package com.example.sales;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Iterative_functional {
    static class Sale {
        String id, customer, product, currency;
        LocalDate date;
        int quantity;
        double unitPrice;

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

        double total() { return quantity * unitPrice; }

        static Sale fromCsv(String line) {
            String[] p = line.split(",");
            return new Sale(
                    p[0].trim(),
                    LocalDate.parse(p[1].trim()),
                    p[2].trim(),
                    p[3].trim(),
                    Integer.parseInt(p[4].trim()),
                    Double.parseDouble(p[5].trim()),
                    p[6].trim()
            );
        }
    }

    public static void main(String[] args) {
        Path path = Path.of("C:\\Users\\kunal\\chubb_workspace\\task-1\\src\\sales.txt");
        if (!Files.exists(path)) {
            System.out.println("File not found: " + path);
            return;
        }

        try (Stream<String> lines = Files.lines(path)) {
            List<Sale> sales = lines.filter(l -> !l.isBlank())
                    .map(Sale::fromCsv)
                    .collect(Collectors.toList());

            System.out.println("Loaded " + sales.size() + " records\n");
            System.out.println("Imperative Style");
            runImperative(sales);

            System.out.println("\nFunctional Style (Streams + Lambdas)");
            runFunctional(sales);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // IMPERATIVE STYLE
    private static void runImperative(List<Sale> sales) {
        double total = 0;
        for (Sale s : sales) {
            total += s.total();
        }
        System.out.printf("Total Revenue: %.2f%n", total);
        Map<String, Double> revByProduct = new HashMap<>();
        for (Sale s : sales) {
            revByProduct.put(s.product,
                    revByProduct.getOrDefault(s.product, 0.0) + s.total());
        }
        System.out.println("Revenue by Product:");
        for (var e : revByProduct.entrySet()) {
            System.out.printf("  %s -> %.2f%n", e.getKey(), e.getValue());
        }
    }

    //  FUNCTIONAL STYLE
    private static void runFunctional(List<Sale> sales) {
        double total = sales.stream()
                .mapToDouble(Sale::total)
                .sum();
        System.out.printf("Total Revenue: %.2f%n", total);
        Map<String, Double> revByProduct = sales.stream()
                .collect(Collectors.groupingBy(s -> s.product,
                        Collectors.summingDouble(Sale::total)));
        System.out.println("Revenue by Product:");
        revByProduct.forEach((p, r) ->
                System.out.printf("  %s -> %.2f%n", p, r));
    }
}
