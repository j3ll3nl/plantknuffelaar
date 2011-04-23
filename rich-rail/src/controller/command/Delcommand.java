package controller.command;

import java.util.HashSet;

import model.Depot;
import model.Train;
import model.Wagon;

public class Delcommand implements CommandInterface {

	private CommandResult cresult = new CommandResult();
	private Depot dm;

	public Delcommand(Depot dm) {
		this.dm = dm;
	}

	@Override
	public CommandResult execute(String[] cmd) throws Exception {
		if (cmd[1].equals("train")) {
			deleteTrain(cmd);
		} else if (cmd[1].equals("wagon")) {
			deleteWagon(cmd);
		} else {
			throw new Exception("Unknown type given");
		}
		return cresult;
	}

	public void deleteTrain(String[] cmd) throws Exception {
		System.out.println("Delcommand.deleteTrain(" + cmd + ")");

		if (cmd[2].trim().equals("")) {
			throw new Exception("No train ID given!");
		} else {
			Train train = dm.getTrain(cmd[2]);
			if (train == null) {
				throw new Exception("Train does not exist");
			} else {
				dm.deleteTrainFromDepot(train);
				cresult.setMessage("train " + cmd[2] + " deleted");
			}
		}
	}

	public void deleteWagon(String[] cmd) throws Exception {
		System.out.println("Delcommand.deleteWagon(" + cmd + ")");

		if (cmd[2].trim().equals("")) {
			throw new Exception("No wagon ID given!");
		} else {
			boolean wagonFound = false;

			// First check in the Depot if the wagon is in here. This loop should be must smaller than the train loop
			HashSet<Wagon> wagons = dm.getWagons();
			if (wagons != null) {
				for (Wagon wagon : wagons) {
					if (wagon.getId().equals(cmd[2])) {
						dm.deleteWagonFromDepot(wagon);
						wagonFound = true;
					}
				}
			}

			if (!wagonFound) {
				HashSet<Train> trains = dm.getTrains();
				if (trains != null) {
					for (Train train : trains) {
						Wagon wagon = train.getWagon(cmd[2]);
						if (wagon != null) {
							train.deleteWagon(wagon);
							wagonFound = true;
						}
					}
				}
			}

			if (!wagonFound) {
				throw new Exception("Wagon does not exist");
			}
		}
	}

}
