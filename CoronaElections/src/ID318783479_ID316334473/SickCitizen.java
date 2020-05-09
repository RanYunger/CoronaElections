package ID318783479_ID316334473;

public class SickCitizen extends Citizen {
	// Constants

	// Fields

	// Properties (Getters and Setters)

	// Constructors
	public SickCitizen(int ID, String fullName, int yearOfBirth, int daysOfSickness,
			Ballot<? extends Citizen> associatedBallot, boolean isIsolated, boolean isWearingSuit) {
		super(ID, fullName, yearOfBirth, daysOfSickness, associatedBallot, isIsolated, isWearingSuit);
	}

	// Methods
	@Override
	public String toString() {
		return super.toString();
	}
}
