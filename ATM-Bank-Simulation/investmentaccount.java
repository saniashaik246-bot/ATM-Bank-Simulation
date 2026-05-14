import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class investmentaccount extends JFrame {

    private final Color deepSpace = new Color(15, 5, 30);
    private final Color plumBg = new Color(54, 9, 82);
    private final Color glassBg = new Color(20, 10, 35, 215);
    private final Color accentGlow = new Color(157, 78, 221);
    private final Color textMuted = new Color(216, 180, 254);

    public investmentaccount() {
        setTitle("Secure Bank | Investment Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 850);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setPaint(new GradientPaint(0, 0, deepSpace, 0, getHeight(), plumBg));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        setContentPane(mainPanel);

        // TOP NAVIGATION
        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        navBar.setOpaque(false);
        navBar.setBorder(new EmptyBorder(25, 40, 0, 0));

        JButton backBtn = new JButton("<~ BACK");
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setForeground(textMuted);
        backBtn.setContentAreaFilled(false);
        backBtn.setBorder(new LineBorder(textMuted, 1, true));
        backBtn.setFocusPainted(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.setPreferredSize(new Dimension(100, 35));
        backBtn.addActionListener(e -> {
            dispose();
            new choiceaccount().setVisible(true);
        });

        navBar.add(backBtn);
        mainPanel.add(navBar, BorderLayout.NORTH);

        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.setOpaque(false);

        // Header Section
        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        headerPanel.setOpaque(false);
        headerPanel.setBorder(new EmptyBorder(10, 0, 40, 0));

        JLabel authLabel = new JLabel("AUTHENTICATION SUCCESSFUL", SwingConstants.CENTER);
        authLabel.setForeground(accentGlow);
        authLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel titleLabel = new JLabel("Select Your Investment Account Type", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 48));

        headerPanel.add(authLabel);
        headerPanel.add(titleLabel);
        centerContainer.add(headerPanel, BorderLayout.NORTH);

        // CARD GRID
        JPanel gridPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        gridPanel.setOpaque(false);

        // All cards now link to Main.java through the createCard method
        gridPanel.add(createCard("Fixed Deposit (FD)", "<html><center>Lock in a lump sum for 7 days to 10 years to earn higher interest (5-8%). suitable for short- or long-term goals.</center></html>", "Select FD", "FD.png"));
        gridPanel.add(createCard("Recurring Deposit (RD)", "<html><center>Deposit a fixed amount monthly (6 months to 10 years). Great for building savings discipline with high returns.</center></html>", "Select RD", "RD.png"));
        gridPanel.add(createCard("NRI Accounts", "<html><center>Tax-free foreign earnings, manage Indian income, and foreign currency deposits.</center></html>", "Select NRI", "NRI.png"));

        centerContainer.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(centerContainer, BorderLayout.CENTER);
    }

    private JPanel createCard(String title, String content, String btnText, String imgPath) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(400, 490));
        card.setBackground(glassBg);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(157, 78, 221, 100), 1, false),
                new EmptyBorder(40, 30, 40, 30)));

        card.add(Box.createVerticalGlue());

        JLabel iconLabel = new JLabel();
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        try {
            ImageIcon icon = new ImageIcon(imgPath);
            Image img = icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
            iconLabel.setIcon(new ImageIcon(img));
        } catch(Exception e) {}

        JLabel cardTitle = new JLabel(title);
        cardTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        cardTitle.setForeground(Color.WHITE);
        cardTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel cardContent = new JLabel(content);
        cardContent.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        cardContent.setForeground(textMuted);
        cardContent.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardContent.setMaximumSize(new Dimension(300, 100));

        JButton selectBtn = new JButton(btnText);
        selectBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        selectBtn.setForeground(Color.WHITE);
        selectBtn.setFocusPainted(false);
        selectBtn.setContentAreaFilled(false);
        selectBtn.setBorder(new LineBorder(accentGlow, 2, false));
        selectBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Dimension btnDim = new Dimension(170, 45);
        selectBtn.setPreferredSize(btnDim);
        selectBtn.setMaximumSize(btnDim);

        // Action Listener for the button to link to Main.java
        selectBtn.addActionListener(e -> {
            dispose();
            new Main().setVisible(true);
        });

        // Hover effects and Card click logic
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(accentGlow, 4, false),
                        new EmptyBorder(37, 27, 37, 27)));
                selectBtn.setContentAreaFilled(true);
                selectBtn.setBackground(accentGlow);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(157, 78, 221, 100), 1, false),
                        new EmptyBorder(40, 30, 40, 30)));
                selectBtn.setContentAreaFilled(false);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Main().setVisible(true);
            }
        });

        card.add(iconLabel);
        card.add(Box.createVerticalStrut(25));
        card.add(cardTitle);
        card.add(Box.createVerticalStrut(15));
        card.add(cardContent);
        card.add(Box.createVerticalStrut(35));
        card.add(selectBtn);
        card.add(Box.createVerticalGlue());

        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new investmentaccount().setVisible(true));
    }
}