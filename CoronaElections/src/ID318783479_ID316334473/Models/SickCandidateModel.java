package ID318783479_ID316334473.Models;

public class SickCandidateModel extends CandidateModel {
	// Constants

	// Fields

	// Properties (Getters and Setters)

	// Constructors
	public SickCandidateModel(int ID, String fullName, int yearOfBirth, int daysOfSickness,
			BallotModel<? extends CitizenModel> associatedBallot, boolean isIsolated, boolean isWearingSuit) {
		super(ID, fullName, yearOfBirth, daysOfSickness, associatedBallot, isIsolated, isWearingSuit);
	}

	// Methods
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		int candidateIndex = sb.indexOf("CandidateModel");

		sb.replace(candidateIndex, candidateIndex + 9, "Sick CandidateModel");

		return sb.toString();
	}
}
