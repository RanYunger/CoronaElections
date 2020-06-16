package ID318783479_ID316334473.Models.Ballots;

import java.time.LocalDate;
import java.util.TreeMap;

import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BallotModel {
	// Constants

	// Fields
	private static int IDGenerator = 1;

	protected ObservableValue<Number> ID;
	protected ObservableValue<String> address;
	protected ObservableList<CitizenModel> voterRegistry;
	protected LocalDate electionsDate;
	protected ObservableValue<Number> votersPercentage;
	protected TreeMap<String, Integer> results;

	// Properties (Getters and Setters)
	public final ObservableValue<Number> getObservableID() {
		return ID;
	}

	public int getNumericID() {
		return ID.getValue().intValue();
	}

	private void setID(int id) {
		this.ID = new SimpleIntegerProperty(id);

	}

	public final ObservableValue<String> getObservableAddress() {
		return address;
	}

	public final String getTextualAddress() {
		return address.getValue();
	}

	private void setAddress(String address) throws Exception {
		if (address.isBlank())
			throw new Exception("BallotModel's address must contain at least 1 letter.");
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

	private void setVotingDate(LocalDate votingDate) throws Exception {
		if (votingDate.compareTo(LocalDate.now()) > 0)
			throw new Exception("Time paradox prevented.");
		this.electionsDate = votingDate;
	}

	public final ObservableValue<Number> getObservableVotersPercentage() {
		return votersPercentage;
	}

	public final double getNumericVotersPercentage() {
		return votersPercentage.getValue().doubleValue();
	}

	private void setVotersPercentage(int numOfVoters) {
		this.votersPercentage = new SimpleDoubleProperty(
				(voterRegistry.size() > 0) ? (100 * numOfVoters) / voterRegistry.size() : 0);
	}

	public TreeMap<String, Integer> getResults() {
		return results;
	}

	public void setResults(TreeMap<String, Integer> results) {
		this.results = results;
	}

	public ObservableValue<String> getObservableType() {
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
	public BallotModel(LocalDate votingDate) {
		this("<UNKNOWN>", votingDate);
	}

	public BallotModel(String address, LocalDate votingDate) {
		try {
			setID(IDGenerator++);
			setAddress(address);
			setVotingDate(votingDate);
			setVoterRegistry(FXCollections.observableArrayList());
			setVotersPercentage(0);
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof BallotModel))
			return false;
		BallotModel other = (BallotModel) obj;

		return ID.equals(other.ID);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(
				String.format("%s [ID: %d | Address: %s]\n", getTextualType(), getNumericID(), getTextualAddress()));

		if (voterRegistry.isEmpty())
			return sb.append("Nothing else to See here..").toString();

		sb.append("\tDate of voting: " + electionsDate + "\n\tVoter list:");
		for (CitizenModel c : voterRegistry) {
			c.calculateAge(electionsDate);
			sb.append("\n\t\t" + c.toString());
		}

		return sb.toString();
	}
}
