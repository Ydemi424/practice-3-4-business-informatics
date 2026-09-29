package ru.edu.pr01;

/**
 * Вариант 6: порог бесплатной обработки = 35 руб., плата = 10 руб.
 */

public class OrderCalculator {


    public static final int    MIN_QTY   = 1;
    public static final int    MAX_QTY   = 10_000;

    public static final double MIN_PRICE = 0.01;
    public static final double MAX_PRICE = 5_000_000.00;

    public static final double MIN_DISC  = 0.0;
    public static final double MAX_DISC  = 30.0;

    public static final double MIN_VAT   = 0.0;
    public static final double MAX_VAT   = 100.0;


    public static final double FREE_LIMIT = 35.0; // порог бесплатной обработки
    public static final double FEE        = 10.0; // фиксированная плата за обработку

    /**
     * TODO 1. Проверка входных данных.
     * @return null, если всё корректно; иначе — текст ошибки.
     */
    public static String check(int qty, double price, double disc, double vat) {
        if (qty < MIN_QTY || qty > MAX_QTY) {
            return "Количество: от " + MIN_QTY + " до " + MAX_QTY + " шт.";
        }
        if (price < MIN_PRICE || price > MAX_PRICE) {
            return "Цена: от " + MIN_PRICE + " до " + MAX_PRICE + " руб.";
        }
        if (disc < MIN_DISC || disc > MAX_DISC) {
            return "Скидка: от " + MIN_DISC + " до " + MAX_DISC + " %.";
        }
        if (vat < MIN_VAT || vat > MAX_VAT) {
            return "НДС: от " + MIN_VAT + " до " + MAX_VAT + " %.";
        }
        return null;
    }

    /** Совместимость с шаблоном: boolean-версия проверки. */
    public static boolean isValid(int quantity, double unitPrice, double discountPercent) {
        return check(quantity, unitPrice, discountPercent, 0.0) == null;
    }

    /** TODO 2. Базовая стоимость: цена × количество. */
    public static double calculateBase(int quantity, double unitPrice) {
        return unitPrice * quantity;
    }

    /** TODO 3. Скидка: sum * (1 - disc/100). */
    public static double applyDiscount(double base, double discountPercent) {
        return base * (1.0 - discountPercent / 100.0);
    }

    /** Индивидуальная часть: порог бесплатной обработки. */
    public static double withFee(double sum) {
        return (sum < FREE_LIMIT) ? sum + FEE : sum;
    }

    /** TODO 4. НДС: sum * (1 + vat/100). */
    public static double calculateVat(double discounted, double vatPercent) {
        return discounted * (1.0 + vatPercent / 100.0);
    }

    /** TODO 5. Полный расчёт: база → скидка → порог → НДС. */
    public static double calculateTotal(int quantity, double unitPrice,
                                        double discountPercent, double vatPercent) {
        double base           = calculateBase(quantity, unitPrice);
        double afterDiscount  = applyDiscount(base, discountPercent);
        double afterFee       = withFee(afterDiscount);
        double total          = calculateVat(afterFee, vatPercent);
        return total;
    }
}