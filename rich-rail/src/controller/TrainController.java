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
	
	public HashSet<Train> getTrains(){
		return trains;
	}
	
	public HashSet<Wagon> getWagons(){
		return wagons;
	}

	public Train getTrain(String id) {
		for (Train train : trains) {
			if (train.getId().equals(id)) {
				return train;
			}
		}
		return null;
	}

	public Wagon getWagon(String id) {
		for (Wagon wagon : wagons) {
			if (wagon.getId().equals(id)) {
				return wagon;
			}
		}

		for (Train train : trains) {
			if (train.getWagon(id) != null) {
				return train.getWagon(id);
			}
		}
		return null;
	}





	public String parseCommand(String cmd) throws Exception {
		if (cmd.trim().equals("")) {
			throw new Exception("No command is given!");
		} else {		
			CommandResult result;
			
			// Opsplitsen van de commando string
			String[] cmdArray = cmd.split(" ");			
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
				throw new Exception("Command cannot be resolved!");
			}

			/*
			 * De code komt niet hier als er een fout/exception is gethrowed vanuit de commandinterface. We kunnen dus gewoon doorgaan zonder iets te controleren.
			 */
			if(result.getObject() instanceof Train){
				trains.add((Train) result.getObject());
			}else{
				wagons.add((Wagon) result.getObject());
			}
			
			setChanged();
			notifyObservers(this);
			
			return result.getMessage();			
		}
	}
}
