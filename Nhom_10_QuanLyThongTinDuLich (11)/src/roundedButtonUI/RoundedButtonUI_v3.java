package roundedButtonUI;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class RoundedButtonUI_v3 extends javax.swing.plaf.basic.BasicButtonUI {
	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		AbstractButton button = (AbstractButton) c;
		button.setOpaque(false);
		button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		AbstractButton b = (AbstractButton) c;
		paintBackground(g, b, b.getModel().isPressed() ? 5 : 0);
		super.paint(g, c);
	}

	private void paintBackground(Graphics g, JComponent c, int yOffset) {
		int width = c.getWidth();
		int height = c.getHeight() - yOffset;

//		Color brandColor = new Color(0, 139, 139);
//		Color brandColor1 = new Color(0, 214, 23);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		GradientPaint gp = new GradientPaint(3, 3, Color.RED, 3, height, Color.BLACK);
		g2.setPaint(gp);
		g2.fillRoundRect(0, yOffset, width, height, height, height);
	}
}