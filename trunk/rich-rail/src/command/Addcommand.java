package command;

import model.Depot;

public class Addcommand implements CommandInterface {

	private CommandResult cresult = new CommandResult();
	private Depot dm;

	public Addcommand(Depot dm) {
		this.dm = dm;
	}

	@Override
	public CommandResult execute(String[] cmd) throws Exception {
		if (cmd[2].equals("to")) {
			// Add wagon to train
			addIdtoId(cmd);
		}
		return cresult;
	}

	private void addIdtoId(String[] cmd) {

	}

}
