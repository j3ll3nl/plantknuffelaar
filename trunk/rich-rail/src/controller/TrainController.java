package controller;

import java.util.HashSet;
import java.util.Observable;

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
	private HashSet<Train> trains = new HashSet<Train>();
	private HashSet<Wagon> wagons = new HashSet<Wagon>();

	/**
	 * Get all created trains
	 * 
	 * @return HashSet<Train> with trains
	 */
	public HashSet<Train> getTrains() {
		return trains;
	}

	/**
	 * Get all created wagons
	 * 
	 * @return HashSet<Wagon> with wagons
	 */
	public HashSet<Wagon> getWagons() {
		return wagons;
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
			String[] cmdArray = cmd.split(" ");
			// Test if the command starts with any of the available commands. Throws an exception if the command cannot be executed.
			if (cmdArray[0].equals("new")) {
				System.out.println("TrainController.parseCommand(" + cmd + ") : new");
				CommandInterface ci = new Newcommand();
				result = ci.execute(cmdArray);
			} else if (cmdArray[0].equals("add")) {
				System.out.println("TrainController.parseCommand(" + cmd + ") : add");
				CommandInterface ci = new Addcommand();
				result = ci.execute(cmdArray);
			} else if (cmdArray[0].startsWith("get")) {
				System.out.println("TrainController.parseCommand(" + cmd + ") : get");
				CommandInterface ci = new Getcommand();
				result = ci.execute(cmdArray);
			} else if (cmdArray[0].equals("delete")) {
				System.out.println("TrainController.parseCommand(" + cmd + ") : delete");
				CommandInterface ci = new Delcommand();
				result = ci.execute(cmdArray);
			} else if (cmdArray[0].equals("remove")) {
				System.out.println("TrainController.parseCommand(" + cmd + ") : remove");
				CommandInterface ci = new Remcommand();
				result = ci.execute(cmdArray);
			} else {
				// The exception that the command cannot be resolved
				throw new Exception("Command cannot be resolved!");
			}

			// We continue if there was not an exception

			// Test if the result object is a train
			if (result.getObject() instanceof Train) {
				trains.add((Train) result.getObject());
			} else {
				// Test if the result object is a wagon
				wagons.add((Wagon) result.getObject());
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
		notifyObservers(this);
	}
}
