package controller.output;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextArea;

import model.Train;
import model.Wagon;

import controller.TrainController;

public class TextLog extends JTextArea implements Observer {

	private static final long serialVersionUID = 7251401618317921382L;
	private HashSet<Train> trains;
	private HashSet<Wagon> wagons;

	public TextLog() {
		super();
		setEditable(false);
		setFont(new java.awt.Font("Tahoma", 0, 11));
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("TextLog.update(" + o + ", " + arg + ")");

		if (arg instanceof TrainController) {
			trains = ((TrainController) arg).getTrains();
			wagons = ((TrainController) arg).getWagons();

			StringBuffer sb = new StringBuffer("");

			if (wagons != null && !wagons.isEmpty()) {
				sb.append("wagons\n");
				for (Wagon wagon : wagons) {
					sb.append("(" + wagon.getId() + ":" + wagon.getNumSeats() + ") ");
				}
				sb.append("\n");
			}

			if (trains != null && !trains.isEmpty()) {
				sb.append("trains\n");
				for (Train train : trains) {
					sb.append("(" + train.getId() + ")");
					HashSet<Wagon> wagonsfromtrain = train.getWagons();
					for (Wagon wagon : wagonsfromtrain) {
						sb.append("-(" + wagon.getId() + ")");
					}
					sb.append("\n");
				}
			}
			setText(sb.toString());
		}
	}
}
