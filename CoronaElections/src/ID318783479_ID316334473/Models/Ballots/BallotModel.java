package ID318783479_ID316334473.Models.Ballots;

import java.time.LocalDate;
import java.util.TreeMap;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BallotModel implements Comparable<BallotModel> {
	// Constants

	// Fields
	private static int IDGenerator = 1;

	protected SimpleIntegerProperty ID;
	protected int numOfVoters = 0;
	protected SimpleStringProperty address;
	protected ObservableList<CitizenModel> voterRegistry;
	protected LocalDate electionsDate;
	protected SimpleDoubleProperty votersPercentage;
	protected TreeMap<String, Integer> results;

	// Properties (Getters and Setters)
	public final SimpleIntegerProperty getObservableID() {
		return ID;
	}

	public int getNumericID() {
		return ID.get();
	}

	private void setID(int id) {
		this.ID = new SimpleIntegerProperty(id);

	}

	public final SimpleStringProperty getObservableAddress() {
		return address;
	}

	public final String getTextualAddress() {
		return address.getValue();
	}

	private void setAddress(String address) {
		if (address.isBlank())
			UIHandler.showError("Ballot's address must contain at least 1 letter.");
		this.address = new SimpleStringProperty(address);
	}

	public final ObservableList<CitizenModel> getVoterRegistry() {
		return voterRegistry;
	}

	private void setVoterRegistry(ObservableList<CitizenModel> observableArrayList) {
		this.voterRegistry = observableArrayList;
	}

	public final LocalDate getElectionsDate() {
		return electionsDate;
	}

	private void setElectionsDate(LocalDate electionsDate) {
		if (electionsDate.compareTo(LocalDate.now()) > 0)
			UIHandler.showError("Time paradox prevented.");
		this.electionsDate = electionsDate;
	}

	public final SimpleDoubleProperty getObservableVotersPercentage() {
		return votersPercentage;
	}

	public final double getNumericVotersPercentage() {
		return votersPercentage.get();
	}

	private void setVotersPercentage() {
		this.votersPercentage = new SimpleDoubleProperty(
				(voterRegistry.size() > 0) ? (double)(100 * numOfVoters) / voterRegistry.size() : 0);
	}

	public void voteConfirmed() {
		numOfVoters++;
		setVotersPercentage();
	}

	public TreeMap<String, Integer> getResults() {
		return results;
	}

	private void setResults(TreeMap<String, Integer> results) {
		this.results = results;
	}

	public SimpleStringProperty getObservableType() {
		return new SimpleStringProperty(getTextualType());
	}

	protected String getTextualType() {
		return "Regular Ballot";
	}

	public boolean isCoronaBallot() {
		return getTextualType().contains("Corona");
	}

	public boolean isMilitaryBallot() {
		return getTextualType().contains("Military");
	}

	public boolean isRegularBallot() {
		return !isCoronaBallot() && !isMilitaryBallot();
	}

	// Constructors
	public BallotModel(String address, LocalDate votingDate) {
		try {
			setID(IDGenerator++);
			setAddress(address);
			setElectionsDate(votingDate);
			setVoterRegistry(FXCollections.observableArrayList());
			setVotersPercentage();
			setResults(new TreeMap<String, Integer>());
		} catch (Exception ex) {
			System.err.println("error!\n" + ex.getMessage());
		}
	}

	// Methods
	public final CitizenModel getCitizenByID(int voterID) {
		for (CitizenModel citizen : voterRegistry) {
			if (citizen.getNumericID() == voterID)
				return citizen;
		}

		return null;
	}

	public boolean addVoter(CitizenModel voter) {
		return voterRegistry.add(voter);
	}

	@Override
	public int compareTo(BallotModel other) {
		return Integer.compare(getNumericID(), other.getNumericID());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof BallotModel))
			return false;
		BallotModel other = (BallotModel) obj;

		return ID.equals(other.ID); // Two ballots can't have the same ID
	}
}
