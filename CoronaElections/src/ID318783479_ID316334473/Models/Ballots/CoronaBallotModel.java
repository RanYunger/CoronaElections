package ID318783479_ID316334473.Models.Ballots;

import java.time.LocalDate;

import ID318783479_ID316334473.Models.Citizens.CitizenModel;

public class CoronaBallotModel extends BallotModel implements Sanitizable {

	// Properties (Getters and Setters)
	@Override
	protected String getTextualType() {
		return "Corona Ballot";
	}

	// Constructors
	public CoronaBallotModel(String address, LocalDate electionsDate) {
		super(address, electionsDate);
	}

	// Methods
	@Override
	public boolean addVoter(CitizenModel voter) {
		if (checkIsolationStatus(voter))
			return super.addVoter(voter);
		return false;
	}
}
