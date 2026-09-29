package ru.edu.pr01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Введите количество шт : ");
            int qty = sc.nextInt();

            System.out.print("Введите цену за единицу руб : ");
            double price = sc.nextDouble();

            System.out.print("Введите скидку % : ");
            double disc = sc.nextDouble();

            System.out.print("Введите ставку НДС % : ");
            double vat = sc.nextDouble();

            String err = OrderCalculator.check(qty, price, disc, vat);
            if (err != null) {
                System.out.println("Ошибка входных данных: " + err);
                return;
            }

            double total = OrderCalculator.calculateTotal(qty, price, disc, vat);
            System.out.printf("Итоговая сумма заказа: %.2f руб.%n", total);

        } catch (InputMismatchException e) {
            System.out.println("Ошибка: введено не число.");
        } finally {
            sc.close();
        }
    }
}