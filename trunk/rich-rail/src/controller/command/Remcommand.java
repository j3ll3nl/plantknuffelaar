package controller.command;

import model.Depot;


public class Remcommand implements CommandInterface {

	private Depot dm;
	public Remcommand(Depot dm) {
		this.dm = dm;
	}
	@Override
	public CommandResult execute(String[] cmd) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public void removeWagon(String id) {

	}




	

}
