import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class Main extends JFrame {

    // --- THEME COLORS ---
    private final Color deepSpace = new Color(15, 5, 30);
    private final Color accentViolet = new Color(123, 44, 191);
    private final Color glassBg = new Color(26, 10, 46, 250);
    private final Color accentGlow = new Color(157, 78, 221);
    private final Color fieldBg = new Color(35, 15, 55); 

    // --- BANK STATE ---
    private double currentBalance = 20000.00;
    private final String correctPin = "1234"; 
    private final ArrayList<String> transactionHistory = new ArrayList<>();

    public Main() {
        setTitle("Secure Bank | Executive Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        // Main Container with Gradient Background
        JPanel container = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, deepSpace, 0, getHeight(), new Color(54, 9, 82));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        container.setLayout(new BorderLayout(0, 20));
        container.setBorder(new EmptyBorder(30, 50, 30, 50));
        setContentPane(container);

        // --- TOP NAVIGATION ---
        JPanel topNav = new JPanel(new BorderLayout());
        topNav.setOpaque(false);
        
        JLabel homeLink = new JLabel("<html><u><~ HOME</u></html>");
        homeLink.setForeground(accentGlow);
        homeLink.setFont(new Font("Segoe UI", Font.BOLD, 16));
        homeLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        homeLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Returning to Home Dashboard...");
                dispose();
                new choiceaccount().setVisible(true); // Linked to choiceaccount
            }
        });
        topNav.add(homeLink, BorderLayout.WEST);
        container.add(topNav, BorderLayout.NORTH);

        // --- CENTER PANEL ---
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);

        // --- HEADER SECTION (Proper Order) ---
        JPanel headerGroup = new JPanel();
        headerGroup.setLayout(new BoxLayout(headerGroup, BoxLayout.Y_AXIS));
        headerGroup.setOpaque(false);

        // Top Header in White
        JLabel header = new JLabel("SECURE BANK DASHBOARD", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 36));
        header.setForeground(Color.WHITE); 
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Sub-header in Purple
        JLabel authLabel = new JLabel("Please select a transaction to manage your account", SwingConstants.CENTER);
        authLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16)); 
        authLabel.setForeground(accentGlow); 
        authLabel.setBorder(new EmptyBorder(10, 0, 20, 0));
        authLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerGroup.add(header);
        headerGroup.add(authLabel);
        centerPanel.add(headerGroup, BorderLayout.NORTH);

        // --- SERVICE GRID ---
        JPanel grid = new JPanel(new GridLayout(2, 2, 40, 40));
        grid.setOpaque(false);
        grid.setBorder(new EmptyBorder(40, 100, 40, 100)); 

        grid.add(createServiceCard("WITHDRAW", () -> openTransaction("Withdraw")));
        grid.add(createServiceCard("DEPOSIT", () -> openTransaction("Deposit")));
        grid.add(createServiceCard("BALANCE INQUIRY", this::checkBalance));
        grid.add(createServiceCard("MINI STATEMENT", this::showHistory));

        centerPanel.add(grid, BorderLayout.CENTER);
        container.add(centerPanel, BorderLayout.CENTER);

        // --- BOTTOM NAVIGATION ---
        JPanel bottomNav = new JPanel(new BorderLayout());
        bottomNav.setOpaque(false);
        
        JLabel logoutLink = new JLabel("<html><u>LOGOUT ~></u></html>");
        logoutLink.setForeground(new Color(0x11A8CD)); 
        logoutLink.setFont(new Font("Segoe UI", Font.BOLD, 16));
        logoutLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION) {
                    dispose();
                    new Registerationpage().setVisible(true); // Linked to Registerationpage
                }
            }
        });
        bottomNav.add(logoutLink, BorderLayout.EAST);
        container.add(bottomNav, BorderLayout.SOUTH);
        
        transactionHistory.add("Initial Vault Credit: ₹20,000.00");
    }

    private JPanel createServiceCard(String title, Runnable action) {
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(glassBg);
        card.setBorder(new LineBorder(accentGlow, 2, true));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lbl = new JLabel(title);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lbl.setForeground(Color.WHITE);
        card.add(lbl);

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) { action.run(); }
            public void mouseEntered(java.awt.event.MouseEvent e) { 
                card.setBackground(new Color(60, 30, 90)); 
                card.setBorder(new LineBorder(Color.WHITE, 2, true));
            }
            public void mouseExited(java.awt.event.MouseEvent e) { 
                card.setBackground(glassBg); 
                card.setBorder(new LineBorder(accentGlow, 2, true));
            }
        });
        return card;
    }

    private void openTransaction(String type) {
        JDialog dialog = new JDialog(this, type, true);
        dialog.setSize(450, 500);
        dialog.setLocationRelativeTo(this);
        
        JPanel p = new JPanel();
        p.setBackground(new Color(25, 10, 40)); 
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(new EmptyBorder(40, 50, 40, 50));

        JLabel title = new JLabel("VAULT " + type.toUpperCase());
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(accentGlow);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField amtField = createField("Enter Amount (₹)", false);
        JPasswordField pinField = (JPasswordField) createField("Enter 4-Digit PIN", true);

        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        btnPanel.setOpaque(false);
        btnPanel.setMaximumSize(new Dimension(400, 45));

        JButton backBtn = new JButton("BACK");
        backBtn.setBackground(new Color(60, 60, 80));
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e -> dialog.dispose());

        JButton confirm = new JButton("CONFIRM");
        confirm.setBackground(accentViolet);
        confirm.setForeground(Color.WHITE);

        confirm.addActionListener(e -> {
            String pin = new String(pinField.getPassword());
            try {
                double amt = Double.parseDouble(amtField.getText());
                if (!pin.equals(correctPin)) {
                    JOptionPane.showMessageDialog(dialog, "Invalid PIN.");
                    return;
                }
                if (type.equals("Withdraw")) {
                    if (amt > currentBalance) JOptionPane.showMessageDialog(dialog, "Insufficient Funds.");
                    else {
                        currentBalance -= amt;
                        transactionHistory.add("Withdrawal: -₹" + amt);
                        showSuccess(dialog, "Success!");
                    }
                } else {
                    currentBalance += amt;
                    transactionHistory.add("Deposit: +₹" + amt);
                    showSuccess(dialog, "Success!");
                }
            } catch (Exception ex) { JOptionPane.showMessageDialog(dialog, "Invalid Amount."); }
        });

        btnPanel.add(backBtn);
        btnPanel.add(confirm);

        p.add(title); p.add(Box.createRigidArea(new Dimension(0, 30)));
        p.add(amtField); p.add(Box.createRigidArea(new Dimension(0, 15)));
        p.add(pinField); p.add(Box.createRigidArea(new Dimension(0, 30)));
        p.add(btnPanel);
        
        dialog.add(p);
        dialog.setVisible(true);
    }

    private void checkBalance() {
        JOptionPane.showMessageDialog(this, "Current Balance: ₹" + String.format("%.2f", currentBalance));
    }

    private void showHistory() {
        StringBuilder sb = new StringBuilder("TRANSACTION HISTORY:\n\n");
        for (String s : transactionHistory) sb.append(s).append("\n");
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void showSuccess(JDialog d, String msg) {
        JOptionPane.showMessageDialog(d, msg);
        d.dispose();
    }

    private JTextField createField(String hint, boolean isPass) {
        JTextField f = isPass ? new JPasswordField() : new JTextField();
        f.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        f.setBackground(fieldBg);
        f.setOpaque(true); 
        f.setForeground(Color.WHITE);
        f.setCaretColor(Color.WHITE);
        f.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        TitledBorder border = BorderFactory.createTitledBorder(new LineBorder(accentGlow, 1), hint);
        border.setTitleColor(accentGlow);
        f.setBorder(border);
        return f;
    }

    public static void main(String[] args) {
        UIManager.put("OptionPane.background", new Color(15, 5, 30));
        UIManager.put("Panel.background", new Color(15, 5, 30));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}