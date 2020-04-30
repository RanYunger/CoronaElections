package ID318783479_ID316334473;

import java.time.YearMonth;

public class CoronaBallot extends Ballot implements CoronaChecker {
	// Constructors
	public CoronaBallot(YearMonth votingDate) {
		this("<UNKNOWN>", votingDate);
	}

	public CoronaBallot(String address, YearMonth votingDate) {
		super(address, votingDate);
	}

	// Methods
	@Override
	public boolean addVoter(Citizen citizen) {
		if (checkIsolation(citizen))
			return super.addVoter(citizen);
		return false;
	}
}
