package controller;

import java.util.HashSet;

import model.Trein;
import model.Wagon;

public class TrainController {
	private HashSet<Trein> trains = new HashSet<Trein>();
	private HashSet<Wagon> wagons = new HashSet<Wagon>();

	public void newTrain(String newId) {
		System.out.println("TrainController.newTrain(" + newId + ")");
		Trein newTrain = new Trein(newId);
		trains.add(newTrain);
	}

	public Trein getTrain(String id) {
		for (Trein train : trains) {
			if (train.getId().equals(id)) {
				return train;
			}
		}
		return null;
	}

	public HashSet<Trein> getTrains() {
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

		for (Trein train : trains) {
			if (train.getWagon(id) != null) {
				return train.getWagon(id);
			}
		}
		return null;
	}
}
