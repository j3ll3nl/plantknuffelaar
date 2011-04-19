package controller.output;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Train;

public class Display extends JPanel implements Observer {

	private static final long serialVersionUID = 1973301688261315362L;
	private int currentTrain;
	private int offset = 200;
	private HashSet<Train> trains;

	public Display() {
		super();
		setBackground(Color.WHITE);
		setPreferredSize(new java.awt.Dimension(784, 220));
		BorderLayout jPanel6Layout = new BorderLayout();
		setLayout(jPanel6Layout);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Display.update(" + o + ", " + arg + ")");

		if (arg instanceof HashSet) {
			trains = (HashSet<Train>) arg;
			repaint();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		currentTrain = 0;

		if (trains != null) {
			removeAll();
			for (Train train : trains) {
				// Draw the train
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(30, 80 + currentTrain * offset, 80, 40);
				g.fillRect(80, 60 + currentTrain * offset, 30, 30);
				g.drawRoundRect(85, 40 + currentTrain * offset, 20, 20, 20, 20);
				g.drawRoundRect(85, currentTrain * offset, 40, 40, 40, 40);
				g.setColor(Color.BLACK);
				g.fillRoundRect(35, 120 + currentTrain * offset, 20, 20, 20, 20);
				g.fillRoundRect(80, 120 + currentTrain * offset, 20, 20, 20, 20);
				g.drawString(train.getId(), 40, 105 + currentTrain * offset);

				currentTrain++;

			}
		}
	}
}
