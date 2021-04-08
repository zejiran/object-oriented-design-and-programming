package co.edu.uniandes.graphics.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class PanelLienzo extends JPanel {

	public PanelLienzo() {
		setSize(700, 700);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		Rectangle2D.Double a = new Rectangle2D.Double(0, 0, 400, 400);
		Ellipse2D.Double b = new Ellipse2D.Double(350, 30, 100, 100);
		Rectangle2D.Double c = new Rectangle2D.Double(390, 129, 20, 41);
		RoundRectangle2D.Double d = new RoundRectangle2D.Double(320, 170, 25, 180, 20, 20);
		RoundRectangle2D.Double e = new RoundRectangle2D.Double(455, 170, 25, 180, 20, 20);
		Rectangle2D.Double f = new Rectangle2D.Double(335, 170, 130, 40);
		RoundRectangle2D.Double gb = new RoundRectangle2D.Double(345, 200, 15, 40, 20, 20);
		RoundRectangle2D.Double h = new RoundRectangle2D.Double(440, 200, 15, 40, 20, 20);
		Rectangle2D.Double i = new Rectangle2D.Double(360, 170, 80, 180);
		RoundRectangle2D.Double j = new RoundRectangle2D.Double(360, 330, 35, 180, 20, 20);
		RoundRectangle2D.Double k = new RoundRectangle2D.Double(405, 330, 35, 180, 20, 20);

		Polygon l = new Polygon(new int[] { 230, 250, 550, 570 }, new int[] { 550, 500, 500, 550 }, 4);

		g2d.setColor(Color.gray);
		g2d.fill(a);
		g2d.setColor(Color.black);
		g2d.fill(b);
		g2d.setColor(Color.black);
		g2d.fill(c);
		g2d.setColor(Color.black);
		g2d.fill(d);
		g2d.setColor(Color.black);
		g2d.fill(e);
		g2d.setColor(Color.black);
		g2d.fill(f);
		g2d.setColor(Color.gray);
		g2d.fill(gb);
		g2d.setColor(Color.white);
		g2d.fill(h);
		g2d.setColor(Color.black);
		g2d.fill(i);
		g2d.setColor(Color.black);
		g2d.fill(j);
		g2d.setColor(Color.black);
		g2d.fill(k);
		g2d.fill(l);

		// Extra accessory (Crown)
		g2d.setColor(Color.orange);
		g2d.fillRoundRect(350, 30, 100, 41, 20, 20);
		g2d.fillRect(350, 10, 20, 50);
		g2d.fillRect(390, 10, 20, 50);
		g2d.fillRect(430, 10, 20, 50);
		g2d.setColor(Color.yellow);
		g2d.drawLine(370, 63, 430, 63);
		// Jewelry
		g2d.setColor(Color.blue);
		g2d.fillPolygon(new int[] { 390, 400, 410 }, new int[] { 60, 40, 60 }, 3);
		g2d.setColor(Color.green);
		g2d.fillOval(360, 40, 20, 20);
		g2d.setColor(Color.red);
		g2d.fillOval(420, 40, 20, 20);

		// Beard
		g2d.setColor(Color.white);
		for (int n = 0; n < 80; n++) {
			g2d.drawLine(360 + n, 100, 360 + n, 150);
		}
		// Mouth
		g2d.setColor(Color.black);
		for (int n = 0; n < 30; n++) {
			g2d.drawLine(390 + n, 110, 385 + n, 120);
		}

		// Star
		g2d.setColor(Color.yellow);
		g2d.fillRect(100, 100, 100, 100);
		g2d.setColor(Color.gray);
		g2d.fillOval(50, 50, 100, 100);
		g2d.fillOval(50, 150, 100, 100);
		g2d.fillOval(150, 50, 100, 100);
		g2d.fillOval(150, 150, 100, 100);
	}

}
