package controller;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import model.Train;
import model.Wagon;

import command.RichRailCommands;

public class TrainController extends Observable {
	private HashSet<Train> trains = new HashSet<Train>();
	private HashSet<Wagon> wagons = new HashSet<Wagon>();
	private RichRailCommands rrc = null;

	public TrainController() {
		rrc = new RichRailCommands(this);
	}

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
		rrc.execute(this, cmd);
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
