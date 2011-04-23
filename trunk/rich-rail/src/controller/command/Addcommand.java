package controller.command;

import model.Depot;
import model.Train;
import model.Wagon;

public class Addcommand implements CommandInterface {

	private CommandResult cresult = new CommandResult();
	private Depot dm;

	public Addcommand(Depot dm) {
		this.dm = dm;
	}

	@Override
	public CommandResult execute(String[] cmd) throws Exception {
		addIdtoId(cmd);
		return cresult;
	}

	private void addIdtoId(String[] cmd) throws Exception {
		System.out.println("Addcommand.addIdtoId(" + cmd + ")");

		if (cmd[1].trim().equals("")) {
			throw new Exception("No wagon ID given!");
		} else if (!cmd[2].trim().equals("to")) {
			throw new Exception("Invallid command!");
		} else if (cmd[3].trim().equals("")) {
			throw new Exception("No train ID given!");
		} else {
			Wagon w = dm.getWagon(cmd[1]);
			Train t = dm.getTrain(cmd[3]);

			if (w == null || t == null) {
				throw new Exception("Train or Wagon ID does not exist or is already connected!");
			} else {
				t.addWagon(w);
				dm.removeWagonFromDepot(w);
				
				cresult.setMessage("wagon " + cmd[1] + " added to train " + cmd[3]);
				cresult.setObject(null);
			}
		}
	}

}
