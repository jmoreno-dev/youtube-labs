package com.josemorenodevs.modernswitch.demo;

import com.josemorenodevs.modernswitch.model.BankTransfer;
import com.josemorenodevs.modernswitch.model.CreditCard;
import com.josemorenodevs.modernswitch.model.CustomerType;
import com.josemorenodevs.modernswitch.model.Payment;

/**
 * "Switch vs if/else in Java: master modern Switch"
 */
public class SwitchDemo {

    // =========================================================================
    // [HOOK — 0:00]
    // =========================================================================

    // Classic switch with fall-through risk
    public static double classicDiscount(CustomerType customerType) {
        double discount;

        switch (customerType) {
            case VIP:
                discount = 0.20;
                break;
            case REGULAR:
                discount = 0.05;
                break;
            default:
                discount = 0.0;
        }

        return discount;
    }

    // [REMOVE break FROM CASE VIP]: Causes silent fall-through bug
    public static double classicDiscountWithoutBreak(CustomerType customerType) {
        double discount;

        switch (customerType) {
            case VIP:
                discount = 0.20;
                // Missing break! Falls through to case REGULAR
            case REGULAR:
                discount = 0.05;
                break;
            default:
                discount = 0.0;
        }

        return discount;
    }

    // Modern switch expression
    public static double modernDiscount(CustomerType customerType) {
        return switch (customerType) {
            case VIP -> 0.20;
            case REGULAR -> 0.05;
            default -> 0.0;
        };
    }

    // =========================================================================
    // [2. FIRST CHANGE: ARROW CASE (SWITCH STATEMENT) — 1:25]
    // =========================================================================

    public static void executeActions(CustomerType customerType) {
        // Switch statement: executes actions with arrow syntax without fall-through
        // risk
        switch (customerType) {
            case VIP -> sendVipNotification();
            case REGULAR -> logOperation();
            default -> ignoreOperation();
        }
    }

    private static void sendVipNotification() {
        System.out.println("Sending priority notification to VIP");
    }

    private static void logOperation() {
        System.out.println("Logging standard operation");
    }

    private static void ignoreOperation() {
        System.out.println("Operation ignored by default");
    }

    // =========================================================================
    // [3. SWITCH EXPRESSIONS: RETURNING A VALUE — 2:10]
    // =========================================================================

    public static double groupedSwitchExpression(CustomerType customerType) {
        // Case grouping with commas and immutable variable (final)
        final double discount = switch (customerType) {
            case VIP, FOUNDER -> 0.20;
            case REGULAR -> 0.05;
            case NEW -> 0.10;
        };

        return discount;
    }

    // =========================================================================
    // [4. MULTIPLE STATEMENTS IN A BRANCH: YIELD — 2:55]
    // =========================================================================

    public static double switchWithYield(CustomerType customerType) {
        final double discount = switch (customerType) {
            case VIP, FOUNDER -> 0.20;
            case REGULAR -> 0.05;
            case NEW -> {
                System.out.println("Log: Applying welcome promotion");
                yield 0.10; // Yields value from block
            }
        };

        return discount;
    }

    // =========================================================================
    // [5. THE TRICK WITH ENUMS: DO YOU NEED DEFAULT? — 3:40]
    // =========================================================================

    public static double exhaustiveSwitchWithoutDefault(CustomerType customerType) {
        // When covering all enum constants, omitting 'default' is a deliberate design
        // decision so the compiler warns us if a new constant is added to the enum.
        return switch (customerType) {
            case VIP, FOUNDER -> 0.20;
            case REGULAR -> 0.05;
            case NEW -> 0.10;
        };
    }

    // =========================================================================
    // [6. THE LEAP IN JAVA 21: PATTERN MATCHING — 4:30]
    // =========================================================================

    // Before Java 21: chaining if / instanceof
    public static String traditionalReceipt(Payment payment) {
        if (payment instanceof CreditCard c && c.fee() > 10) {
            return "Card with surcharge: " + c.number();
        } else if (payment instanceof CreditCard c) {
            return "Standard card: " + c.number();
        } else if (payment instanceof BankTransfer bt) {
            return "IBAN: " + bt.iban();
        } else if (payment == null) {
            return "Payment not available";
        }
        return "Unsupported payment method";
    }

    // In Java 21: Pattern Matching for switch with 'when' and 'case null'
    public static String receiptWithPatternMatching(Payment payment) {
        return switch (payment) {
            case null -> "Payment not available";

            case CreditCard c when c.fee() > 10 ->
                "Card with surcharge: " + c.number();

            case CreditCard c ->
                "Standard card: " + c.number();

            case BankTransfer bt ->
                "IBAN: " + bt.iban();

            default -> "Unsupported payment method";
        };
    }

    // =========================================================================
    // [7. WHEN TO USE IF, SWITCH OR POLYMORPHISM? — 5:25]
    // =========================================================================

    public static boolean isAdultAndActive(int age, boolean active) {
        // IF/ELSE is the natural choice for ranges and boolean conditions:
        if (age >= 18 && active) {
            return true;
        }
        return false;
    }

    // =========================================================================
    // MAIN METHOD TO RUN IN INTELLIJ
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("=== [HOOK — 0:00]: THE DANGER OF FALL-THROUGH ===");
        System.out.println("VIP discount (with break): " + classicDiscount(CustomerType.VIP));
        System.out.println("VIP discount (without break!): " + classicDiscountWithoutBreak(CustomerType.VIP));
        System.out.println("VIP discount (Modern Switch): " + modernDiscount(CustomerType.VIP));

        System.out.println("\n=== [2. ARROW CASE (STATEMENT) — 1:25] ===");
        executeActions(CustomerType.VIP);

        System.out.println("\n=== [4. USING YIELD — 2:55] ===");
        System.out.println("NEW discount: " + switchWithYield(CustomerType.NEW));

        System.out.println("\n=== [6. JAVA 21: PATTERN MATCHING — 4:30] ===");
        Payment highFeeCard = new CreditCard("4500-1234-5678-9010", 15.0);
        Payment standardCard = new CreditCard("4500-9876-5432-1098", 2.0);
        Payment transfer = new BankTransfer("ES9121000418450200051332");
        Payment nullPayment = null;

        System.out.println(receiptWithPatternMatching(highFeeCard));
        System.out.println(receiptWithPatternMatching(standardCard));
        System.out.println(receiptWithPatternMatching(transfer));
        System.out.println(receiptWithPatternMatching(nullPayment));
    }
}