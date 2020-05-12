package ID318783479_ID316334473;

public class SickCandidate extends Candidate {
	// Constants

	// Fields

	// Properties (Getters and Setters)

	// Constructors
	public SickCandidate(int ID, String fullName, int yearOfBirth, int daysOfSickness,
			Ballot<? extends Citizen> associatedBallot, boolean isIsolated, boolean isWearingSuit) {
		super(ID, fullName, yearOfBirth, daysOfSickness, associatedBallot, isIsolated, isWearingSuit);
	}

	// Methods
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		int candidateIndex = sb.indexOf("Candidate");

		sb.replace(candidateIndex, candidateIndex + 9, "Sick Candidate");

		return sb.toString();
	}
}
