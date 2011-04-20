package model;

import java.util.HashSet;

public class Depot {
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
	 * Add a train to the depot.
	 * 
	 * @param train The train.
	 */
	public void addTrain(Train train) {
		trains.add(train);
	}

	/**
	 * Add a wagon to the depot.
	 * 
	 * @param wagon The wagon
	 */
	public void addWagon(Wagon wagon) {
		wagons.add(wagon);
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
}
