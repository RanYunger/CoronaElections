package ID318783479_ID316334473.Models.Citizens;

import ID318783479_ID316334473.Models.Ballots.BallotModel;

public class SoldierModel extends CitizenModel {

	// Fields

	// Properties (Getters and Setters)

	// Constructors
	protected SoldierModel(int ID, String fullName, int yearOfBirth, BallotModel associatedBallot,
			boolean isWearingSuit, int daysOfSickness, boolean isCarryingWeapon) {
		super(ID, fullName, yearOfBirth, associatedBallot, true, isWearingSuit, daysOfSickness, true, isCarryingWeapon);
	}

	public SoldierModel(int ID, String fullName, int yearOfBirth, BallotModel associatedBallot,
			boolean isCarryingWeapon) {
		super(ID, fullName, yearOfBirth, associatedBallot, false, false, 0, true, isCarryingWeapon);
	}
}
