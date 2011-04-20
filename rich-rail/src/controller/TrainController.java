package controller;

import java.util.Observable;

import model.Depot;
import model.Train;
import model.Wagon;

import command.Addcommand;
import command.CommandInterface;
import command.CommandResult;
import command.Delcommand;
import command.Getcommand;
import command.Newcommand;
import command.Remcommand;

public class TrainController extends Observable {

	private Depot dm;

	public TrainController() {
		dm = new Depot();
	}

	/**
	 * Method that will parse the command and send it to the right class that will handle the command.
	 * 
	 * @param cmd The command
	 * @return Result message
	 * @throws Exception Any errors that occure
	 */
	public String parseCommand(String cmd) throws Exception {
		if (cmd.trim().equals("")) {
			throw new Exception("No command is given!");
		} else {
			CommandResult result;

			// Split the command from whitespaces.
			String[] cmdSplit = cmd.trim().split(" ");
			String[] cmdArray = new String[20];
			for (int i = 0; i < cmdSplit.length; i++) {
				cmdArray[i] = cmdSplit[i];
			}
			for (int i = 0; i < cmdArray.length; i++) {
				if(cmdArray[i] == null){
					cmdArray[i] = "";
				}				
			}

			// Test if the command starts with any of the available commands. Throws an exception if the command cannot be executed.
			if (cmdArray[0].equals("new")) {
				System.out.println("TrainController.parseCommand(" + cmd + ") : new");
				CommandInterface ci = new Newcommand(dm);
				result = ci.execute(cmdArray);
			} else if (cmdArray[0].equals("add")) {
				System.out.println("TrainController.parseCommand(" + cmd + ") : add");
				CommandInterface ci = new Addcommand(dm);
				result = ci.execute(cmdArray);
			} else if (cmdArray[0].startsWith("get")) {
				System.out.println("TrainController.parseCommand(" + cmd + ") : get");
				CommandInterface ci = new Getcommand(dm);
				result = ci.execute(cmdArray);
			} else if (cmdArray[0].equals("delete")) {
				System.out.println("TrainController.parseCommand(" + cmd + ") : delete");
				CommandInterface ci = new Delcommand(dm);
				result = ci.execute(cmdArray);
			} else if (cmdArray[0].equals("remove")) {
				System.out.println("TrainController.parseCommand(" + cmd + ") : remove");
				CommandInterface ci = new Remcommand(dm);
				result = ci.execute(cmdArray);
			} else {
				// The exception that the command cannot be resolved
				throw new Exception("Command cannot be resolved!");
			}

			// We continue if there was not an exception

			// Test if the result object is a train
			if (result.getObject() instanceof Train) {
				dm.addTrain((Train) result.getObject());
			} else {
				dm.addWagon((Wagon) result.getObject());
			}
			// Notify observers that there is a change
			stateChanged();
			// Return the result message
			return result.getMessage();
		}
	}

	/**
	 * This method notifies the observers that there is an change.
	 */
	public void stateChanged() {
		setChanged();
		notifyObservers(dm);
	}
}
