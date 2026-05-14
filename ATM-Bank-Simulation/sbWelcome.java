import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class sbWelcome extends JFrame {
    private final Color deepSpace = new Color(15, 5, 30);
    private final Color glassBg = new Color(25, 12, 50, 230);
    private final Color accentViolet = new Color(123, 44, 191);
    private final Color accentGlow = new Color(157, 78, 221);
    private final Color textMuted = new Color(192, 132, 252);
    public sbWelcome() {
        setTitle("Secure Bank | Welcome");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 850);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, deepSpace, 0, getHeight(),new Color(54, 9, 82));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        setContentPane(mainPanel);
        JPanel welcomeCard = new JPanel(new BorderLayout(0, 20));
        welcomeCard.setBackground(glassBg);
        welcomeCard.setPreferredSize(new Dimension(550, 500));
        welcomeCard.setBorder(new LineBorder(accentGlow, 1, true));
        welcomeCard.setOpaque(true);

        JPanel content = new JPanel(new BorderLayout(0, 15));
        content.setOpaque(false);
        content.setBorder(new EmptyBorder(30, 40, 10, 40));
        ImageIcon logoIcon = new ImageIcon("banklogo.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage), SwingConstants.CENTER);

        JPanel textWrapper = new JPanel(new GridLayout(2, 1, 0, 5));
        textWrapper.setOpaque(false);
        JLabel welcomeTitle = new JLabel("WELCOME TO SECURE BANK", SwingConstants.CENTER);
        welcomeTitle.setFont(new Font("Segoe UI", Font.BOLD, 34));
        welcomeTitle.setForeground(Color.WHITE);

        JLabel subtext = new JLabel("Your journey to digital safety starts here.", SwingConstants.CENTER);
        subtext.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtext.setForeground(textMuted);
        textWrapper.add(welcomeTitle);
        textWrapper.add(subtext);
        content.add(imageLabel, BorderLayout.CENTER);
        content.add(textWrapper, BorderLayout.SOUTH);
        welcomeCard.add(content, BorderLayout.CENTER);

        JPanel actions = new JPanel(new GridBagLayout());
        actions.setOpaque(false);
        actions.setBorder(new EmptyBorder(10, 60, 40, 60));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;

        JButton startBtn = new JButton("GET STARTED");
        startBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        startBtn.setBackground(accentViolet);
        startBtn.setForeground(Color.WHITE);
        startBtn.setPreferredSize(new Dimension(0, 55));
        startBtn.setFocusPainted(false);
        startBtn.setBorder(null);
        startBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startBtn.addActionListener(e -> {
            new Registerationpage().setVisible(true);
            dispose();
        });
        actions.add(startBtn, gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 0, 0);
        JLabel loginLink = new JLabel("Existing Account? Login here", SwingConstants.CENTER);
        loginLink.setFont(new Font("Segoe UI", Font.BOLD, 13));
        loginLink.setForeground(textMuted);
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new sbLogin().setVisible(true);
                dispose();
            }
            @Override
            public void mouseEntered(MouseEvent e) { loginLink.setForeground(Color.WHITE); }
            @Override
            public void mouseExited(MouseEvent e) { loginLink.setForeground(textMuted); }
        });
        actions.add(loginLink, gbc);
        welcomeCard.add(actions, BorderLayout.SOUTH);
        mainPanel.add(welcomeCard);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new sbWelcome().setVisible(true));
    }
}
