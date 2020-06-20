package ID318783479_ID316334473.Models.Citizens;

import java.time.LocalDate;

import ID318783479_ID316334473.UIHandler;
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
	protected SimpleIntegerProperty yearOfBirth;
	protected SimpleIntegerProperty daysOfSickness;
	protected SimpleObjectProperty<BallotModel> associatedBallot;
	protected SimpleBooleanProperty isIsolated;
	protected SimpleBooleanProperty isWearingSuit;
	protected SimpleBooleanProperty isSoldier;
	protected SimpleBooleanProperty isCarryingWeapon;

	// Properties (Getters and Setters)
	public SimpleIntegerProperty getObservableID() {
		return ID;
	}

	public int getNumericID() {
		return ID.get();
	}

	private void setID(int ID) {
		if (String.valueOf(ID).length() != 9)
			UIHandler.showError("CitizenModel's ID must contain exactly 9 digits.");
		this.ID = new SimpleIntegerProperty(ID);
	}

	public SimpleStringProperty getObservableFullName() {
		return fullName;
	}

	public String getTextualFullName() {
		return fullName.get();
	}

	private void setFullName(String fullName) {
		if (fullName.isBlank())
			UIHandler.showError("CitizenModel's name must contain at least 1 letter.");
		this.fullName = new SimpleStringProperty(fullName);
	}

	public SimpleIntegerProperty getObservableYearOfBirth() {
		return yearOfBirth;
	}

	public int getNumericYearOfBirth() {
		return yearOfBirth.get();
	}

	private void setYearOfBirth(int yearOfBirth) {
		if (yearOfBirth > LocalDate.now().getYear())
			UIHandler.showError("Time paradox prevented - I mean, come on");
		this.yearOfBirth = new SimpleIntegerProperty(yearOfBirth);
	}

	public SimpleIntegerProperty getObservableDaysOfSickness() {
		return daysOfSickness;
	}

	public int getNumericDaysOfSickness() {
		return daysOfSickness.get();
	}

	protected void setDaysOfSickness(int daysOfSickness) {
		if (daysOfSickness < 0)
			UIHandler.showError("Citizen can only have non-negative amount of sickness days.");
		if ((isIsolated()) && (daysOfSickness < 1))
			UIHandler.showError("An Isolated citizen must've been sick for at least 1 day.");
		this.daysOfSickness = new SimpleIntegerProperty(daysOfSickness);
	}

	public SimpleObjectProperty<BallotModel> getObservableAssociatedBallot() {
		return associatedBallot;
	}

	public BallotModel getActualAssociatedBallot() {
		return associatedBallot.get();
	}

	public void setAssociatedBallot(BallotModel associatedBallot) {
		this.associatedBallot = new SimpleObjectProperty<BallotModel>(associatedBallot);
		getActualAssociatedBallot().addVoter(this);
	}

	public SimpleBooleanProperty observableIsolationStatus() {
		return isIsolated;
	}

	public boolean isIsolated() {
		return isIsolated.get();
	}

	protected void setIsIsolated(boolean isIsolated) {
		this.isIsolated = new SimpleBooleanProperty(isIsolated);
	}

	public SimpleBooleanProperty observableWearingSuitStatus() {
		return isWearingSuit;
	}

	public boolean isWearingSuit() {
		return isWearingSuit.get();
	}

	protected void setIswearingSuit(boolean iswearingSuit) {
		this.isWearingSuit = new SimpleBooleanProperty(iswearingSuit);
	}

	public SimpleBooleanProperty observableSoldierStatus() {
		return isSoldier;
	}

	public boolean isSoldier() {
		return isSoldier.get();
	}

	protected void setIsSoldier(boolean isSoldier) {
		this.isSoldier = new SimpleBooleanProperty(isSoldier);
	}

	public SimpleBooleanProperty observableCarryingWeaponStatus() {
		return isCarryingWeapon;
	}

	public boolean isCarryingWeapon() {
		return isCarryingWeapon.get();
	}

	protected void setIsCarryingWeapon(boolean isCarryingWeapon) {
		this.isCarryingWeapon = new SimpleBooleanProperty(isCarryingWeapon);
	}

	// Constructors
	protected CitizenModel(int ID, String fullName, int yearOfBirth, BallotModel associatedBallot, boolean isIsolated,
			boolean isWearingSuit, int daysOfSickness, boolean isSoldier, boolean isCarryingWeapon) {
		try {
			setID(ID);
			setFullName(fullName);
			setYearOfBirth(yearOfBirth);
			setIsIsolated(isIsolated);
			setIswearingSuit(isWearingSuit);
			setIsSoldier(isSoldier);
			setIsCarryingWeapon(isCarryingWeapon);
			setAssociatedBallot(associatedBallot);
			setDaysOfSickness(daysOfSickness);
		} catch (Exception ex) {
			UIHandler.showError("An unexpected error occoured.", ex.getStackTrace());
		}
	}

	public CitizenModel(int ID, String fullName, int yearOfBirth, BallotModel associatedBallot) {
		this(ID, fullName, yearOfBirth, associatedBallot, false, false, 0, false, false);
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