package controller.command;

import model.Depot;

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

	public void deleteTrain(String[] cmd) {
		System.out.println("Delcommand.deleteTrain(" + cmd + ")");

	}

	public void deleteWagon(String[] cmd) {
		System.out.println("Delcommand.deleteWagon(" + cmd + ")");
		
	}

}
