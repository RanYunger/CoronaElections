package ID318783479_ID316334473;

import java.time.YearMonth;

public class CoronaMilitaryBallot extends MilitaryBallot implements CoronaChecker {
	// Constructors
	public CoronaMilitaryBallot(YearMonth votingDate) {
		this("<UNKNOWN>", votingDate);
	}

	public CoronaMilitaryBallot(String address, YearMonth votingDate) {
		super(address, votingDate);
	}

	@Override
	public boolean addVoter(Citizen citizen) {
		if (checkIsolation(citizen))
			return super.addVoter(citizen);
		return false;
	}
	

	


	// Methods
}
