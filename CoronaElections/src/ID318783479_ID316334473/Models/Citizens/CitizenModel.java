package ID318783479_ID316334473.Models.Citizens;

import java.time.LocalDate;

import ID318783479_ID316334473.Models.Ballots.BallotModel;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class CitizenModel implements Comparable<CitizenModel> {
	// Constants
	public static final int VOTER_AGE = 18;
	public static final int SOLDIER_AGE = 21;

	// Fields
	protected ObservableValue<Number> ID;
	protected ObservableValue<String> fullName;
	protected ObservableValue<Number> yearOfBirth, age;
	protected ObservableValue<Number> daysOfSickness;
	protected ObservableValue<BallotModel> associatedBallot;
	protected ObservableValue<Boolean> isIsolated;
	protected ObservableValue<Boolean> isWearingSuit;
	protected ObservableValue<Boolean> isSoldier;

	// Properties (Getters and Setters)
	public ObservableValue<Number> getObservableID() {
		return ID;
	}

	public int getNumericID() {
		return ID.getValue().intValue();
	}

	private void setID(int ID) throws Exception {
		if (String.valueOf(ID).length() != 9)
			throw new Exception("CitizenModel's ID must contain exactly 9 digits.");
		this.ID = new SimpleIntegerProperty(ID);
	}

	public ObservableValue<String> getObservableFullName() {
		return fullName;
	}

	public String getTextualFullName() {
		return fullName.getValue();
	}

	private void setFullName(String fullName) throws Exception {
		if (fullName.isBlank())
			throw new Exception("CitizenModel's name must contain at least 1 letter.");
		this.fullName = new SimpleStringProperty(fullName);
	}

	public ObservableValue<Number> getObservableYearOfBirth() {
		return yearOfBirth;
	}

	public int getNumericYearOfBirth() {
		return yearOfBirth.getValue().intValue();
	}

	private void setYearOfBirth(int yearOfBirth) throws Exception {
		if (yearOfBirth > LocalDate.now().getYear())
			throw new Exception("Time paradox prevented - I mean, come on");
		this.yearOfBirth = new SimpleIntegerProperty(yearOfBirth);
	}

	public ObservableValue<Number> getObservableAge() {
		return age;
	}

	public int getNumericAge() {
		return age.getValue().intValue();
	}

	protected void setAge(int age) throws Exception {
		if (age <= 0)
			throw new Exception("Time paradox prevented - I mean, come on");
		this.age = new SimpleIntegerProperty(age);
	}

	public ObservableValue<Number> getObservableDaysOfSickness() {
		return daysOfSickness;
	}

	public int getNumericDaysOfSickness() {
		return daysOfSickness.getValue().intValue();
	}

	protected void setDaysOfSickness(int daysOfSickness) throws Exception {
		if (daysOfSickness < 0)
			throw new Exception("CitizenModel can only have non-negative amount of sickness days.");
		if ((isIsolated.getValue()) && (daysOfSickness < 1))
			throw new Exception("An Isolated CitizenModel must've been sick for at least 1 day.");
		this.daysOfSickness = new SimpleIntegerProperty(daysOfSickness);
	}

	public ObservableValue<BallotModel> getObservableAssociatedBallot() {
		return associatedBallot;
	}

	public BallotModel getActualAssociatedBallot() {
		return associatedBallot.getValue();
	}

	public void setAssociatedBallot(BallotModel associatedBallot) throws NullPointerException {
		this.associatedBallot = new SimpleObjectProperty<BallotModel>(associatedBallot);
		getActualAssociatedBallot().addVoter(this);
	}

	public ObservableValue<Boolean> observableIsolationStatus() {
		return isIsolated;
	}

	public boolean isIsolated() {
		return isIsolated.getValue();
	}

	private void setIsIsolated(boolean isIsolated) {
		this.isIsolated = new SimpleBooleanProperty(isIsolated);
	}

	public ObservableValue<Boolean> observableWearingSuitStatus() {
		return isWearingSuit;
	}

	public boolean isWearingSuit() {
		return isWearingSuit.getValue();
	}

	private void setIswearingSuit(boolean iswearingSuit) {
		this.isWearingSuit = new SimpleBooleanProperty(iswearingSuit);
	}

	public ObservableValue<Boolean> observableSoldierStatus() {
		return isSoldier;
	}

	public boolean isSoldier() {
		return isSoldier.getValue();
	}

	private void setIsSoldier(boolean isSoldier) {
		this.isSoldier = new SimpleBooleanProperty(isSoldier);
	}

	// Constructors
	public CitizenModel(int ID, String fullName, int yearOfBirth, int daysOfSickness, BallotModel associatedBallot,
			boolean isIsolated, boolean isWearingSuit) {
		try {
			setID(ID);
			setFullName(fullName);
			setYearOfBirth(yearOfBirth);
			setIsIsolated(isIsolated);
			setDaysOfSickness(daysOfSickness);
			setIsSoldier(this instanceof SoldierModel);
			setIswearingSuit(isWearingSuit);
			setAssociatedBallot(associatedBallot);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void calculateAge(LocalDate votingDate) {
		try {
			setAge(votingDate.getYear() - yearOfBirth.getValue().intValue());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public int compareTo(CitizenModel other) {
		return Integer.compare(ID.getValue().intValue(), other.ID.getValue().intValue());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CitizenModel))
			return false;
		CitizenModel other = (CitizenModel) obj;
		return ID == other.ID;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("Citizen [ID:%d | Full name: %s | Born: %s | Status: ", getNumericID(),
				getTextualFullName(), getNumericYearOfBirth()));
		sb.append(isIsolated() ? String.format("Isolated (%d Day(s) so far), ", getNumericDaysOfSickness())
				: "Not isolated, ");
		sb.append(isWearingSuit() ? "Wearing suit]" : "Not wearing suit]");

		return sb.toString();
	}
}