package ID318783479_ID316334473.Models.Citizens;

import ID318783479_ID316334473.Models.Ballots.BallotModel;

public class SickSoldierModel extends SoldierModel {

	public SickSoldierModel(int ID, String fullName, int yearOfBirth, int daysOfSickness, BallotModel associatedBallot,
			boolean isIsolated, boolean isWearingSuit, boolean isCarryingWeapon) {
		super(ID, fullName, yearOfBirth, daysOfSickness, associatedBallot, isIsolated, isWearingSuit, isCarryingWeapon);
	}

}
