import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Main extends JFrame {

    ArrayList<Expense> expenses = new ArrayList<>();
    DefaultTableModel model;

    public Main() {

        setTitle("Expense Tracker");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Amount", "Category", "Date"}, 0);
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 4, 10, 10));

        JButton addBtn = new JButton("Add Expense");
        JButton viewBtn = new JButton("View All");
        JButton totalBtn = new JButton("Total Spent");
        JButton filterBtn = new JButton("Filter");

        panel.add(addBtn);
        panel.add(viewBtn);
        panel.add(totalBtn);
        panel.add(filterBtn);

        add(panel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        addBtn.addActionListener(e -> addExpense());
        viewBtn.addActionListener(e -> loadAllExpenses());
        totalBtn.addActionListener(e -> showTotal());
        filterBtn.addActionListener(e -> filterCategory());

        setVisible(true);
    }

    void addExpense() {
        JTextField amountField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField dateField = new JTextField();

        Object[] fields = {
                "Amount:", amountField,
                "Category:", categoryField,
                "Date (DD-MM-YYYY):", dateField
        };

        int result = JOptionPane.showConfirmDialog(null, fields,
                "Add New Expense", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String cat = categoryField.getText();
                String date = dateField.getText();

                expenses.add(new Expense(amount, cat, date));
                JOptionPane.showMessageDialog(null, "Expense Added!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid Amount!");
            }
        }
    }

    void loadAllExpenses() {
        model.setRowCount(0);
        for (Expense e : expenses) {
            model.addRow(new Object[]{e.getAmount(), e.getCategory(), e.getDate()});
        }
    }

    void showTotal() {
        double sum = 0;
        for (Expense e : expenses) {
            sum += e.getAmount();
        }

        JOptionPane.showMessageDialog(null,
                "Total Spent: ₹" + sum,
                "Total Expense", JOptionPane.INFORMATION_MESSAGE);
    }

    void filterCategory() {
        String cat = JOptionPane.showInputDialog("Enter category:");

        if (cat == null) return;

        model.setRowCount(0);
        for (Expense e : expenses) {
            if (e.getCategory().equalsIgnoreCase(cat)) {
                model.addRow(new Object[]{e.getAmount(), e.getCategory(), e.getDate()});
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
