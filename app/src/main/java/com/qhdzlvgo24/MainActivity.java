import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp {
    public static void main(String[] args) {
        // تشغيل واجهة المستخدم بشكل آمن
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("تطبيق إدارة المهام الموحد - Java");
            frame.setSize(450, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            // التصميم الأساسي
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout(10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // حقل الإدخال وزر الإضافة
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            JTextField taskField = new JTextField(20);
            JButton addButton = new JButton("إضافة مهمة");
            JButton deleteButton = new JButton("حذف المختار");

            topPanel.add(new JLabel("المهمة:"));
            topPanel.add(taskField);
            topPanel.add(addButton);
            topPanel.add(deleteButton);

            panel.add(topPanel, BorderLayout.NORTH);

            // جدول عرض المهام
            String[] columnNames = {"رقم", "اسم المهمة"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);

            panel.add(scrollPane, BorderLayout.CENTER);

            // أحداث الأزرار (Logic)
            final int[] counter = {1};

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String taskText = taskField.getText().trim();
                    if (!taskText.isEmpty()) {
                        tableModel.addRow(new Object[]{counter[0]++, taskText});
                        taskField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(frame, "الرجاء إدخال اسم المهمة أولاً!", "تنبيه", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        tableModel.removeRow(selectedRow);
                    } else {
                        JOptionPane.showMessageDialog(frame, "الرجاء تحديد مهمة للحذف!", "تنبيه", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
