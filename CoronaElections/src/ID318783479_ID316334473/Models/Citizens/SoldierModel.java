package ID318783479_ID316334473.Models.Citizens;

import ID318783479_ID316334473.Models.Ballots.BallotModel;

public class SoldierModel extends CitizenModel {
	
	// Fields
	protected boolean isCarryingWeapon;

	// Properties (Getters and Setters)
	public boolean isCarryingWeapon() {
		return isCarryingWeapon;
	}

	public void setIsCarryingWeapon(boolean isCarryingWeapon) {
		this.isCarryingWeapon = isCarryingWeapon;
	}

	// Constructors
	public SoldierModel(int ID, String fullName, int yearOfBirth, int daysOfSickness, BallotModel associatedBallot,
			boolean isIsolated, boolean isWearingSuit, boolean isCarryingWeapon) {
		super(ID, fullName, yearOfBirth, daysOfSickness, associatedBallot, isIsolated, isWearingSuit);
		
		setIsCarryingWeapon(isCarryingWeapon);
	}
}
