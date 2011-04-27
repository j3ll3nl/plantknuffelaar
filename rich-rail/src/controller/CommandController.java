package controller;

import java.util.Observable;

import model.Depot;
import model.Train;
import model.Wagon;
import controller.command.Addcommand;
import controller.command.CommandInterface;
import controller.command.CommandResult;
import controller.command.Delcommand;
import controller.command.Displaycommand;
import controller.command.Getcommand;
import controller.command.Newcommand;
import controller.command.Remcommand;
import controller.output.GraphicDisplay;
import controller.output.TextLog;

public class CommandController extends Observable {

	private Depot dm;
	private CommandInterface ci;

	public CommandController() {
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
			// Split the command from whitespaces.
			String[] cmdSplit = cmd.trim().split(" ");

			String[] cmdArray = new String[20];
			for (int i = 0; i < cmdSplit.length; i++) {
				cmdArray[i] = cmdSplit[i];
			}
			for (int i = 0; i < cmdArray.length; i++) {
				if (cmdArray[i] == null) {
					cmdArray[i] = "";
				}
			}

			// Test if the command starts with any of the available commands. Throws an exception if the command cannot be executed.
			if (cmdArray[0].equals("new")) {
				System.out.println("CommandController.parseCommand(" + cmd + ") : new");
				ci = new Newcommand(dm);
			} else if (cmdArray[0].equals("add")) {
				System.out.println("CommandController.parseCommand(" + cmd + ") : add");
				ci = new Addcommand(dm);
			} else if (cmdArray[0].startsWith("get")) {
				System.out.println("CommandController.parseCommand(" + cmd + ") : get");
				ci = new Getcommand(dm);
			} else if (cmdArray[0].equals("delete")) {
				System.out.println("CommandController.parseCommand(" + cmd + ") : delete");
				ci = new Delcommand(dm);
			} else if (cmdArray[0].equals("remove")) {
				System.out.println("CommandController.parseCommand(" + cmd + ") : remove");
				ci = new Remcommand(dm);
			} else if (cmdArray[0].equals("display")) {
				System.out.println("CommandController.parseCommand(" + cmd + ") : display");
				
				System.out.println("GuiController.actionPerformed() - Duplicate display");
				
				ci = new Displaycommand();
			
			} else {
				// The exception that the command cannot be resolved
				throw new Exception("Command cannot be resolved!");
			}

			CommandResult result = ci.execute(cmdArray);

			// Get the instance of the result object from the command
			if (result.getObject() instanceof Train) {
				dm.addTrain((Train) result.getObject());
			} else if (result.getObject() instanceof Wagon) {
				dm.addWagon((Wagon) result.getObject());
			} else if(result.getObject() instanceof GraphicDisplay){
				// Register display at commandcontroller
				this.addObserver((GraphicDisplay) result.getObject());
			} else if(result.getObject() instanceof TextLog){
				// Register display at commandcontroller
				this.addObserver((TextLog) result.getObject());
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
