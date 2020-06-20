package ID318783479_ID316334473.Models.Ballots;

import java.time.LocalDate;

import ID318783479_ID316334473.Models.Citizens.CitizenModel;

public class CoronaMilitaryBallotModel extends BallotModel implements Militarizable, Sanitizable {

	// Properties (Getters and Setters)
	@Override
	protected String getTextualType() {
		return "CoronaÎMilitary Ballot";
	}

	// Constructors
	public CoronaMilitaryBallotModel(String address, LocalDate votingDate) {
		super(address, votingDate);
	}

	// Methods
	@Override
	public boolean addVoter(CitizenModel voter) {
		if (checkMilitaryStatus(voter))
			return super.addVoter(voter);
		return false;
	}
}
