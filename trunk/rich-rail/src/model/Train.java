package model;

import java.util.HashSet;

public class Train {
	private String id;
	private HashSet<Wagon> wagons = new HashSet<Wagon>();

	/**
	 * Constructor voor het aanmaken van een nieuwe trein.
	 * 
	 * @param id Trein ID.
	 */
	public Train(String newId) {
		setId(newId);
	}

	/**
	 * Geeft het aantal zitplaatsen terug in deze trein door alle zitplaatsen in de gekoppelde wagons te tellen.
	 * 
	 * @return Het aantal zitplaatsen.
	 */
	public int getNumSeats() {
		int seats = 0;
		for (Wagon wagon : wagons) {
			seats += wagon.getNumSeats();
		}
		return seats;
	}

	/**
	 * Voeg een wagon toe aan de trein.
	 * 
	 * @param newWagon De nieuwe wagon.
	 */
	public void addWagon(Wagon newWagon) {
		wagons.add(newWagon);
	}

	/**
	 * Geef de wagon uit deze trein met als ID het meegegeven ID.
	 * 
	 * @param id ID van de wagon.
	 * @return Null of de gevonden wagon.
	 */
	public Wagon getWagon(String id) {
		for (Wagon wagon : wagons) {
			if (wagon.getId().equals(id)) {
				return wagon;
			}
		}
		return null;
	}

	/**
	 * Geef de wagons van deze trein.
	 * 
	 * @return Alle wagons van deze trein.
	 */
	public HashSet<Wagon> getWagons() {
		return wagons;
	}

	/**
	 * Verwijder de wagon van de trein. De wagon wordt vernietigd en is niet meer herbruikbaar.
	 * 
	 * @param id ID van de wagon.
	 */
	public void deleteWagon(String id) {
		for (Wagon wagon : wagons) {
			if (wagon.getId().equals(id)) {
				wagons.remove(wagon);
			}
		}
	}

	/**
	 * Verwijder de wagon van de trein maar geef deze terug.
	 * 
	 * @param id ID van de wagon.
	 * @return Null of de gevonden wagon.
	 */
	public Wagon removeWagon(String id) {
		for (Wagon wagon : wagons) {
			if (wagon.getId().equals(id)) {
				wagons.remove(wagon);
				return wagon;
			}
		}
		return null;
	}

	/**
	 * Zet de nieuwe ID voor deze trein.
	 * 
	 * @param newId De ID van de trein.
	 */
	private void setId(String newId) {
		id = newId;
	}

	/**
	 * Geeft de ID van deze trein.
	 * 
	 * @return De ID van deze trein.
	 */
	public String getId() {
		return id;
	}

}
