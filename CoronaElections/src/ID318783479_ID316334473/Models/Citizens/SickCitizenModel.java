package ID318783479_ID316334473.Models.Citizens;

import ID318783479_ID316334473.Models.Ballots.BallotModel;

public class SickCitizenModel extends CitizenModel {

	public SickCitizenModel(int ID, String fullName, int yearOfBirth, int daysOfSickness, BallotModel associatedBallot,
			boolean isWearingSuit) {
		super(ID, fullName, yearOfBirth, associatedBallot, true, isWearingSuit, daysOfSickness, false, false);
	}
}
