package ID318783479_ID316334473.Models.Citizens;

import java.time.LocalDate;

import ID318783479_ID316334473.Models.Ballots.BallotModel;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class CitizenModel implements Comparable<CitizenModel> {
	// Constants
	public static final int VOTER_AGE = 18;
	public static final int SOLDIER_AGE = 21;

	// Fields
	protected SimpleIntegerProperty ID;
	protected SimpleStringProperty fullName;
	protected SimpleIntegerProperty yearOfBirth, age;
	protected SimpleIntegerProperty daysOfSickness;
	protected SimpleObjectProperty<BallotModel> associatedBallot;
	protected SimpleBooleanProperty isIsolated;
	protected SimpleBooleanProperty isWearingSuit;
	protected SimpleBooleanProperty isSoldier;

	// Properties (Getters and Setters)
	public SimpleIntegerProperty getObservableID() {
		return ID;
	}

	public int getNumericID() {
		return ID.get();
	}

	private void setID(int ID) throws Exception {
		if (String.valueOf(ID).length() != 9)
			throw new Exception("CitizenModel's ID must contain exactly 9 digits.");
		this.ID = new SimpleIntegerProperty(ID);
	}

	public SimpleStringProperty getObservableFullName() {
		return fullName;
	}

	public String getTextualFullName() {
		return fullName.get();
	}

	private void setFullName(String fullName) throws Exception {
		if (fullName.isBlank())
			throw new Exception("CitizenModel's name must contain at least 1 letter.");
		this.fullName = new SimpleStringProperty(fullName);
	}

	public SimpleIntegerProperty getObservableYearOfBirth() {
		return yearOfBirth;
	}

	public int getNumericYearOfBirth() {
		return yearOfBirth.get();
	}

	private void setYearOfBirth(int yearOfBirth) throws Exception {
		if (yearOfBirth > LocalDate.now().getYear())
			throw new Exception("Time paradox prevented - I mean, come on");
		this.yearOfBirth = new SimpleIntegerProperty(yearOfBirth);
	}

	public SimpleIntegerProperty getObservableAge() {
		return age;
	}

	public int getNumericAge() {
		return age.get();
	}

	protected void setAge(int age) throws Exception {
		if (age <= 0)
			throw new Exception("Time paradox prevented - I mean, come on");
		this.age = new SimpleIntegerProperty(age);
	}

	public SimpleIntegerProperty getObservableDaysOfSickness() {
		return daysOfSickness;
	}

	public int getNumericDaysOfSickness() {
		return daysOfSickness.get();
	}

	protected void setDaysOfSickness(int daysOfSickness) throws Exception {
		if (daysOfSickness < 0)
			throw new Exception("CitizenModel can only have non-negative amount of sickness days.");
		if ((isIsolated.getValue()) && (daysOfSickness < 1))
			throw new Exception("An Isolated CitizenModel must've been sick for at least 1 day.");
		this.daysOfSickness = new SimpleIntegerProperty(daysOfSickness);
	}

	public SimpleObjectProperty<BallotModel> getObservableAssociatedBallot() {
		return associatedBallot;
	}

	public BallotModel getActualAssociatedBallot() {
		return associatedBallot.get();
	}

	public void setAssociatedBallot(BallotModel associatedBallot) throws NullPointerException {
		this.associatedBallot = new SimpleObjectProperty<BallotModel>(associatedBallot);
		getActualAssociatedBallot().addVoter(this);
	}

	public SimpleBooleanProperty observableIsolationStatus() {
		return isIsolated;
	}

	public boolean isIsolated() {
		return isIsolated.get();
	}

	private void setIsIsolated(boolean isIsolated) {
		this.isIsolated = new SimpleBooleanProperty(isIsolated);
	}

	public SimpleBooleanProperty observableWearingSuitStatus() {
		return isWearingSuit;
	}

	public boolean isWearingSuit() {
		return isWearingSuit.get();
	}

	private void setIswearingSuit(boolean iswearingSuit) {
		this.isWearingSuit = new SimpleBooleanProperty(iswearingSuit);
	}

	public SimpleBooleanProperty observableSoldierStatus() {
		return isSoldier;
	}

	public boolean isSoldier() {
		return isSoldier.get();
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
			System.err.println(ex.getMessage());
		}
	}

	public void calculateAge(LocalDate votingDate) {
		try {
			setAge(votingDate.getYear() - yearOfBirth.get());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	@Override
	public int compareTo(CitizenModel other) {
		return Integer.compare(ID.get(), other.ID.get());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CitizenModel))
			return false;
		CitizenModel other = (CitizenModel) obj;
		return ID == other.ID; // Two citizens can't have the same ID
	}
}