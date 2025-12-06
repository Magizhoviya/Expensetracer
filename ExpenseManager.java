
import java.util.ArrayList;
public class ExpenseManager {

    private ArrayList<Expense> expenses = new ArrayList<>();

    public void addExpense(Expense expense) {
        expenses.add(expense);
        System.out.println("Expense added successfully!\n");
    }

    public void showExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.\n");
            return;
        }
        System.out.println("\n--- All Expenses ---");
        for (Expense e : expenses) {
            System.out.println(e);
        }
        System.out.println();
    }

    public void totalExpense() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        System.out.println("Total Spent: ₹" + total + "\n");
    }

    public void filterByCategory(String category) {
        boolean found = false;
        System.out.println("\n--- Expenses in " + category + " ---");
        for (Expense e : expenses) {
            if (e.getCategory().equalsIgnoreCase(category)) {
                System.out.println(e);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No expenses found in this category.");
        }
        System.out.println();
    }
}
