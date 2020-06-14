package ID318783479_ID316334473.Models.Citizens;

import ID318783479_ID316334473.Models.Ballots.BallotModel;

public class SickCandidateModel extends CandidateModel {

	public SickCandidateModel(int ID, String fullName, int yearOfBirth, int daysOfSickness, BallotModel associatedBallot,
			boolean isIsolated, boolean isWearingSuit) {
		super(ID, fullName, yearOfBirth, daysOfSickness, associatedBallot, isIsolated, isWearingSuit);
	}
}
