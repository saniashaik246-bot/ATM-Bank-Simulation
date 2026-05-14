import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sbLogin extends JFrame {

    // Theme Colors from your HTML/CSS
    private final Color deepSpace = new Color(15, 5, 30);
    private final Color glassBg = new Color(12, 6, 25, 245);
    private final Color accentViolet = new Color(123, 44, 191);
    private final Color accentGlow = new Color(157, 78, 221);
    private final Color textMuted = new Color(192, 132, 252);

    public sbLogin() {
        setTitle("Secure Bank | LOG IN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 850); 
        setLocationRelativeTo(null);

        // Background Panel with dark plum violet gradient
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, deepSpace, 0, getHeight(), new Color(54, 9, 82));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        setContentPane(mainPanel);

        // Auth Card
        JPanel authCard = new JPanel();
        authCard.setBackground(glassBg);
        authCard.setPreferredSize(new Dimension(550, 650));
        authCard.setLayout(new BoxLayout(authCard, BoxLayout.Y_AXIS));
        authCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Padding and Border inside the card
        authCard.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(accentGlow, 2, true),
                new EmptyBorder(50, 50, 50, 50)
        ));

        // Header Section
        JLabel title = new JLabel("LOG IN");
        title.setFont(new Font("Segoe UI", Font.BOLD, 35));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Identity verification required.");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitle.setForeground(textMuted);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(0, 1, 0, 3));
        inputPanel.setOpaque(false);
        inputPanel.setMaximumSize(new Dimension(450, 200));

        JLabel accLabel = new JLabel("ACCOUNT NUMBER");
        accLabel.setForeground(new Color(233, 213, 255));
        accLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));

        // Fix: Use custom JTextField to prevent background ghosting/glitch
        JTextField accField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        styleField(accField);

        JLabel pinLabel = new JLabel("SECURE PIN");
        pinLabel.setForeground(new Color(233, 213, 255));
        pinLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));

        // Fix: Use custom JPasswordField to prevent background ghosting/glitch
        JPasswordField pinField = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        styleField(pinField);
        pinField.setHorizontalAlignment(JTextField.CENTER);

        inputPanel.add(accLabel);
        inputPanel.add(accField);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        inputPanel.add(pinLabel);
        inputPanel.add(pinField);

        // Unlock Button
        JButton loginBtn = new JButton("UNLOCK ACCOUNT");
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        loginBtn.setBackground(accentViolet);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setMaximumSize(new Dimension(450, 50));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String acc = accField.getText();
                String pin = new String(pinField.getPassword());

                if (!acc.isEmpty() && !pin.isEmpty()) {
                    dispose();
                    new Main().setVisible(true); 
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter all credentials.");
                }
            }
        });

        // Assemble the card
        authCard.add(title);
        authCard.add(Box.createRigidArea(new Dimension(0, 60)));
        authCard.add(subtitle);
        authCard.add(Box.createRigidArea(new Dimension(0, 70)));
        authCard.add(inputPanel);
        authCard.add(Box.createRigidArea(new Dimension(0, 40)));
        authCard.add(loginBtn);

        mainPanel.add(authCard);
    }

    private void styleField(JTextField field) {
        field.setOpaque(false); // CRITICAL: Fixes the transparency glitch
        field.setBackground(new Color(255, 255, 255, 20));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(accentGlow, 1),
                new EmptyBorder(10, 15, 10, 15)
        ));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new sbLogin().setVisible(true));
    }
}