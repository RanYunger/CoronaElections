package ID318783479_ID316334473;

import java.time.YearMonth;

public class MilitaryBallot extends Ballot {
	// Constants

	// Fields

	// Properties (Getters and Setters)

	// Constructors
	public MilitaryBallot(YearMonth votingDate) {
		this("<UNKNOWN>", votingDate);
	}

	public MilitaryBallot(String address, YearMonth votingDate) {
		super(address, votingDate);
	}
	// Methods

	@Override
	public boolean addVoter(Citizen citizen) {
		int citizenAge = voterRegistry.getVotingDate().getYear() - citizen.getYearOfBirth();

		if (citizenAge <= Citizen.SOLDIER_AGE)
			return super.addVoter(citizen);

		return false;
	}
}
