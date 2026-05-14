import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class savingaccount extends JFrame {

    // Standard Theme Colors
    private final Color deepSpace = new Color(15, 5, 30);
    private final Color plumBg = new Color(54, 9, 82);
    private final Color glassBg = new Color(20, 10, 35, 215);
    private final Color accentGlow = new Color(157, 78, 221);
    private final Color textMuted = new Color(216, 180, 254);

    public savingaccount() {
        setTitle("Secure Bank | Saving Account Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 850); 
        setLocationRelativeTo(null);

        // ROOT PANEL: Gradient Background
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
        backBtn.addActionListener(e -> navigateToBack());
        
        navBar.add(backBtn);
        mainPanel.add(navBar, BorderLayout.NORTH);

        // CENTER CONTAINER
        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.setOpaque(false);

        // Header Section
        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        headerPanel.setOpaque(false);
        headerPanel.setBorder(new EmptyBorder(10, 0, 40, 0));

        JLabel authLabel = new JLabel("AUTHENTICATION SUCCESSFUL", SwingConstants.CENTER);
        authLabel.setForeground(accentGlow);
        authLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel titleLabel = new JLabel("Select Your Saving Account Type", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 48));

        headerPanel.add(authLabel);
        headerPanel.add(titleLabel);
        centerContainer.add(headerPanel, BorderLayout.NORTH);

        // CARD GRID
        JPanel gridPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        gridPanel.setOpaque(false);
        gridPanel.setBorder(new EmptyBorder(0, 80, 80, 80));

        gridPanel.add(createAccountCard(
                "Savings Account",
                "<html><center>Ideal for individuals or students wanting to save while earning interest (2.5-7%). Includes easy deposits and online banking.</center></html>",
                "Select Saving",
                "saveacc.png"
        ));

        gridPanel.add(createAccountCard(
                "Salary Account",
                "<html><center>A zero-balance variant for employer salary credits. Includes perks like free transfers and easy loan access.</center></html>",
                "Select Salary",
                "salaryacc.png"
        ));

        centerContainer.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(centerContainer, BorderLayout.CENTER);
    }

    private JPanel createAccountCard(String title, String description, String btnText, String imgPath) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(400, 490)); 
        card.setBackground(glassBg);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        
        // Initial Border
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(157, 78, 221, 100), 2, false),
                new EmptyBorder(40, 30, 40, 30)));

        // Icon
        JLabel iconLabel = new JLabel();
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        try {
            ImageIcon icon = new ImageIcon(imgPath);
            Image img = icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
            iconLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            iconLabel.setText("[ Icon ]");
            iconLabel.setForeground(accentGlow);
        }

        JLabel cardTitle = new JLabel(title);
        cardTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        cardTitle.setForeground(Color.WHITE);
        cardTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel cardDesc = new JLabel(description);
        cardDesc.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        cardDesc.setForeground(textMuted);
        cardDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardDesc.setPreferredSize(new Dimension(300, 100));
        cardDesc.setMaximumSize(new Dimension(300, 100));

        JButton selectBtn = new JButton(btnText);
        selectBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        selectBtn.setForeground(Color.WHITE);
        selectBtn.setFocusPainted(false);
        selectBtn.setContentAreaFilled(false);
        selectBtn.setBorder(new LineBorder(accentGlow, 2, false));
        selectBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectBtn.setMaximumSize(new Dimension(180, 45));

        // Interaction Logic
        MouseAdapter interactionAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(accentGlow, 4, false),
                        new EmptyBorder(38, 28, 38, 28)));
                selectBtn.setContentAreaFilled(true);
                selectBtn.setBackground(accentGlow);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(157, 78, 221, 100), 2, false),
                        new EmptyBorder(40, 30, 40, 30)));
                selectBtn.setContentAreaFilled(false);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                navigateToMain();
            }
        };

        card.addMouseListener(interactionAdapter);
        selectBtn.addActionListener(e -> navigateToMain());

        card.add(iconLabel);
        card.add(Box.createVerticalStrut(25));
        card.add(cardTitle);
        card.add(Box.createVerticalStrut(15));
        card.add(cardDesc);
        card.add(Box.createVerticalStrut(35));
        card.add(selectBtn);

        return card;
    }

    // --- NAVIGATION LINKS ---
    
    private void navigateToMain() {
        dispose();
        new Main().setVisible(true); // Replace with your main page class name
    }

    private void navigateToBack() {
        dispose();
        new choiceaccount().setVisible(true); // Goes back to account selection
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new savingaccount().setVisible(true));
    }
}