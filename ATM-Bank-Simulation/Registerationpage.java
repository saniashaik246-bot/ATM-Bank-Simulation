import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

public class Registerationpage extends JFrame {
    private final Color deepSpace = new Color(15, 5, 30);
    private final Color glassBg = new Color(25, 12, 50, 230);
    private final Color accentViolet = new Color(123, 44, 191);
    private final Color accentGlow = new Color(157, 78, 221);
    private final Color textMuted = new Color(192, 132, 252);
    private final Font FORM_LABEL_FONT = new Font("Segoe UI", Font.BOLD, 12);
    private final Font FORM_INPUT_FONT = new Font("Segoe UI", Font.PLAIN, 13);
    private List<JTextField> textFields = new ArrayList<>();

    public Registerationpage() {
        setTitle("Secure Bank | Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 850);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, deepSpace, 0, getHeight(), new Color(54, 9, 82)));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        setContentPane(mainPanel);
        JPanel authCard = new JPanel(new BorderLayout(0, 20));
        authCard.setBackground(glassBg);
        authCard.setPreferredSize(new Dimension(900, 700));
        authCard.setBorder(new LineBorder(accentGlow, 1, true));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        ImageIcon logoIcon = new ImageIcon("banklogo.png");
        Image scaled = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        header.add(new JLabel(new ImageIcon(scaled), SwingConstants.CENTER), BorderLayout.NORTH);

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setOpaque(false);
        JLabel title = new JLabel("CREATE ACCOUNT", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(Color.WHITE);

        JLabel sub = new JLabel("Fill in your details to initialize your Account", SwingConstants.CENTER);
        sub.setForeground(textMuted);
        titlePanel.add(title);
        titlePanel.add(sub);
        header.add(titlePanel, BorderLayout.CENTER);
        authCard.add(header, BorderLayout.NORTH);

        JPanel formGrid = new JPanel(new GridLayout(0, 3, 20, 20));
        formGrid.setOpaque(false);
        formGrid.setBorder(new EmptyBorder(10, 50, 10, 50));
        addTextField(formGrid, "Full Name", "Legal Full Name");
        addTextField(formGrid, "Father's Name", "Father's Legal Name");
        addTextField(formGrid, "Card Number", "4800 XXXX XXXX XXXX");
        addTextField(formGrid, "Date of Birth", "YYYY-MM-DD");
        addRadioGroup(formGrid, "Gender", new String[]{"Male", "Female", "Other"});
        addRadioGroup(formGrid, "Marital Status", new String[]{"Married", "Single"});
        addTextField(formGrid, "Email Address", "client@securebank.com");
        addComboBox(formGrid, "Occupation", new String[]{"Salaried", "Business", "Student"});
        addComboBox(formGrid, "Annual Income", new String[]{"Under 5L", "5L - 15L", "Above 15L"});
        addTextField(formGrid, "Phone Number", "Enter Phone Number");
        addTextField(formGrid, "Address", "Full Address");
        addTextField(formGrid, "Aadhar Number", "12-Digit ID");
        addTextField(formGrid, "City", "Current City");
        addTextField(formGrid, "Old PIN", "4-digit PIN");
        addTextField(formGrid, "New PIN", "4-digit PIN");

        authCard.add(formGrid, BorderLayout.CENTER);
        JPanel footer = new JPanel(new GridBagLayout());
        footer.setOpaque(false);
        footer.setBorder(new EmptyBorder(7, 50, 30, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JButton btn = new JButton("INITIALIZE ACCOUNT");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setBackground(accentViolet);
        btn.setForeground(Color.WHITE);
        btn.setPreferredSize(new Dimension(0, 45));
        btn.setFocusPainted(false);
        btn.setBorder(null);

        btn.addActionListener(e -> {
            boolean allFilled = true;
            for (JTextField field : textFields) {
                // Check if field is empty or still contains the placeholder text
                if (field.getText().trim().isEmpty() || field.getForeground().equals(Color.GRAY)) {
                    allFilled = false;
                    break;
                }}
            if (!allFilled) {
                JOptionPane.showMessageDialog(this, "All details are mandatory. Please fill in all fields including Address.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Vault Initialized Successfully!");
                new choiceaccount().setVisible(true);
                dispose();
         }});
        footer.add(btn, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(15, 0, 0, 0);

        JLabel loginLink = new JLabel("Already have an account? Sign in here");
        loginLink.setForeground(textMuted);
        loginLink.setHorizontalAlignment(SwingConstants.CENTER);
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLink.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                loginLink.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                loginLink.setForeground(textMuted);
            }
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new sbLogin().setVisible(true);
                dispose();
         } });
        footer.add(loginLink, gbc);
        authCard.add(footer, BorderLayout.SOUTH);
        mainPanel.add(authCard);
    }

    private void addTextField(JPanel parent, String label, String placeholder) {
        JPanel wrap = new JPanel(new BorderLayout(0, 5));
        wrap.setOpaque(false);
        JLabel lbl = new JLabel(label.toUpperCase());
        lbl.setForeground(textMuted);
        lbl.setFont(FORM_LABEL_FONT);

        JTextField field = new JTextField(placeholder);
        field.setBackground(new Color(40, 20, 70));
        field.setForeground(Color.GRAY); // Placeholder color
        field.setCaretColor(Color.WHITE);
        field.setFont(FORM_INPUT_FONT);
        field.setBorder(new EmptyBorder(8, 10, 8, 10));

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.WHITE);
                }}
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(placeholder);
                } }});
        textFields.add(field); 
        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(new Color(40, 20, 70));
        holder.setBorder(new LineBorder(new Color(157, 78, 221, 60), 1));
        holder.add(field);
        wrap.add(lbl, BorderLayout.NORTH);
        wrap.add(holder, BorderLayout.CENTER);
        parent.add(wrap);
    }
    private void addComboBox(JPanel parent, String label, String[] items) {
        JPanel wrap = new JPanel(new BorderLayout(0, 5));
        wrap.setOpaque(false);
        JLabel lbl = new JLabel(label.toUpperCase());
        lbl.setForeground(textMuted);
        lbl.setFont(FORM_LABEL_FONT);

        JComboBox<String> combo = new JComboBox<>(items);
        combo.setBackground(new Color(40, 20, 70));
        combo.setForeground(Color.WHITE);
        combo.setFont(FORM_INPUT_FONT);
        combo.setBorder(null);
        combo.setFocusable(false);
        combo.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            protected JButton createArrowButton() {
                JButton b = new JButton("▼");
                b.setBorder(null);
                b.setContentAreaFilled(false);
                b.setFocusPainted(false);
                b.setForeground(accentViolet);
                return b;
            }
        });
        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(new Color(40, 20, 70));
        holder.setBorder(new LineBorder(new Color(157, 78, 221, 60), 1));
        holder.add(combo);
        wrap.add(lbl, BorderLayout.NORTH);
        wrap.add(holder, BorderLayout.CENTER);
        parent.add(wrap);
    }
    private void addRadioGroup(JPanel parent, String label, String[] opts) {
        JPanel wrap = new JPanel(new BorderLayout(0, 5));
        wrap.setOpaque(false);

        JLabel lbl = new JLabel(label.toUpperCase());
        lbl.setForeground(textMuted);
        lbl.setFont(FORM_LABEL_FONT);
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.setOpaque(false);

        ButtonGroup bg = new ButtonGroup();
        for (String o : opts) {
            JRadioButton rb = new JRadioButton(o);
            rb.setForeground(Color.WHITE);
            rb.setOpaque(false);
            rb.setFont(FORM_INPUT_FONT);
            bg.add(rb);
            p.add(rb);
        }
        wrap.add(lbl, BorderLayout.NORTH);
        wrap.add(p, BorderLayout.CENTER);
        parent.add(wrap);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new Registerationpage().setVisible(true));
    }
}