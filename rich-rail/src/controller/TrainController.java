package controller;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import model.Train;
import model.Wagon;

import command.Addcommand;
import command.CommandInterface;
import command.Delcommand;
import command.Getcommand;
import command.Newcommand;
import command.Remcommand;

public class TrainController extends Observable {
	private HashSet<Train> trains = new HashSet<Train>();
	private HashSet<Wagon> wagons = new HashSet<Wagon>();

	public void newTrain(String newId) {
		System.out.println("TrainController.newTrain(" + newId + ")");
		Train newTrain = new Train(newId);
		trains.add(newTrain);
	}

	public Train getTrain(String id) {
		for (Train train : trains) {
			if (train.getId().equals(id)) {
				return train;
			}
		}
		return null;
	}

	public HashSet<Train> getAllTrains() {
		return trains;
	}

	public void newWagon(String newId) {
		newWagon(newId, 20);
	}

	public void newWagon(String newId, int newNumseats) {
		System.out.println("TrainController.newWagon(" + newId + ", " + newNumseats + ")");

		Wagon newWagon = new Wagon(newId, newNumseats);
		wagons.add(newWagon);
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

	public void removeWagon(String id) {

	}

	public void deleteWagon(String id) {

	}

	public void parseCommand(String cmd) throws Exception {
		if (cmd.equals("")) {
			throw new Exception("No command is given!");
		} else {
			String[] b = cmd.split(" ");
			if (b[0].equals("new")) { // New command
				CommandInterface ci = new Newcommand();
				ci.execute(cmd);
			} else if (b[0].equals("add")) { // Add command
				CommandInterface ci = new Addcommand();
				ci.execute(cmd);
			} else if (b[0].equals("get")) { // Get command
				CommandInterface ci = new Getcommand();
				ci.execute(cmd);
			} else if (b[0].equals("delete")) { // Delete command
				CommandInterface ci = new Delcommand();
				ci.execute(cmd);
			} else if (b[0].equals("remove")) { // Remove command
				CommandInterface ci = new Remcommand();
				ci.execute(cmd);
			} else {
				throw new Exception("Command not recognized!");
			}
		}
	}

	private void newUpdate() {
		setChanged();
		notifyObservers(getAllTrains());
	}

	@Override
	public void addObserver(Observer o) {
		super.addObserver(o);
		newUpdate();
	}
}
