package ID318783479_ID316334473.Models;

public class SickCitizenModel extends CitizenModel {
	// Constants

	// Fields

	// Properties (Getters and Setters)

	// Constructors
	public SickCitizenModel(int ID, String fullName, int yearOfBirth, int daysOfSickness,
			BallotModel<? extends CitizenModel> associatedBallot, boolean isIsolated, boolean isWearingSuit) {
		super(ID, fullName, yearOfBirth, daysOfSickness, associatedBallot, isIsolated, isWearingSuit);
	}

	// Methods
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		int citizenIndex = sb.indexOf("CitizenModel");

		sb.replace(citizenIndex, citizenIndex + 7, "Sick CitizenModel");

		return sb.toString();
	}
}
