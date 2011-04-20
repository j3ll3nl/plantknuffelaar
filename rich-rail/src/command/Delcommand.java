package command;

import model.Depot;


public class Delcommand implements CommandInterface {

	private Depot dm;

	public Delcommand(Depot dm) {
		this.dm = dm;
	}

	@Override
	public CommandResult execute(String[] cmd) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteWagon(String id) {

	}

	

}
