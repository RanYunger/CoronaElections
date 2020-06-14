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
	public CoronaBallotModel(LocalDate votingDate) {
		super(votingDate);
	}

	public CoronaBallotModel(String address, LocalDate votingDate) {
		super(address, votingDate);
	}

	// Methods
	@Override
	public boolean addVoter(CitizenModel voter) {
		if (checkSuitStatus(voter))
			return super.addVoter(voter);
		return false;
	}
}
