package roundedButtonUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomInputDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private String input;

	public static String showInputDialog(String message, String initialValue) {
		CustomInputDialog dialog = new CustomInputDialog("                        Nhập dữ liệu", message, initialValue);
		dialog.setVisible(true);
		return dialog.input;
	}

	private CustomInputDialog(String title, String message, String initialValue) {

		setTitle("Quản lý thông tin du lịch");
		setResizable(false);
		setLocationRelativeTo(null);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		ImageIcon icon = new ImageIcon("demo\\logo.png");
		setIconImage(icon.getImage());

		JLabel messageLabel = new JLabel(message);
		messageLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

		JTextField inputField = new JTextField(initialValue);
		inputField.setPreferredSize(new Dimension(200, 30));

		JButton okButton = new RoundedGradientButton("OK");
		okButton.setPreferredSize(new Dimension(80, 30));
		okButton.addActionListener(e -> {
			input = inputField.getText();
			dispose();
		});

		JButton cancelButton = new RoundedGradientButton("Cancel");
		cancelButton.setPreferredSize(new Dimension(80, 30));
		cancelButton.addActionListener(e -> dispose());

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);

		JPanel contentPanel = new JPanel();

		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();

		contentPanel.add(b);

		b1.add(messageLabel);
		b1.add(inputField);
		b2.add(buttonPanel);

		b.add(b1);
		b.add(b2);

		JLabel titleLabel = new JLabel(title.toUpperCase());
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD | Font.ITALIC, 20));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setPreferredSize(new Dimension(400, 50));

		JPanel titlePanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				GradientPaint gp = new GradientPaint(0, 0, new Color(0x0055A4), 0, getHeight(), new Color(0x88CDE9));
				g2d.setPaint(gp);
				RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
				g2d.fill(roundedRect);
				g2d.dispose();
			}
		};
		titlePanel.setPreferredSize(new Dimension(400, 50));
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		titlePanel.add(titleLabel);

		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(400, 150));
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		mainPanel.add(titlePanel);
		mainPanel.add(contentPanel);

		setContentPane(mainPanel);
		pack();
		setLocationRelativeTo(null);
	}

	private static class RoundedGradientButton extends JButton {

		private static final long serialVersionUID = 1L;

		public RoundedGradientButton(String text) {
			super(text);
			setOpaque(false);
			setForeground(Color.WHITE);
			setBorderPainted(false);
			setContentAreaFilled(false);
			setFocusPainted(false);
			setPreferredSize(new Dimension(80, 30));
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			GradientPaint gp = new GradientPaint(0, 0, new Color(0x0055A4), 0, getHeight(), new Color(0x88CDE9));
			g2d.setPaint(gp);
			RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
			g2d.fill(roundedRect);
			g2d.dispose();

			super.paintComponent(g);
		}
	}
}