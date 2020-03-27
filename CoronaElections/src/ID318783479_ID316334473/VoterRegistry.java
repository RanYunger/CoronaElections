package ID318783479_ID316334473;

import java.time.YearMonth;
import java.util.Arrays;

// Wrapper class for the Citizen[] voterRegistry array
public class VoterRegistry {
	// Constants

	// Fields
	private Citizen[] voterRegistry;
	private int voterCount;
	private YearMonth votingDate;

	// Properties (Getters and Setters)
	public int getVoterCount() {
		return voterCount;
	}
	public YearMonth getVotingDate() {
		return votingDate;
	}

	// Constructors
	public VoterRegistry(YearMonth votingDate) {
		voterRegistry = new Citizen[Elections.MAX_ARRAY_SIZE];
		voterCount = 0;
		this.votingDate = votingDate;
	}

	// Methods
	public Citizen getCitizenByID(int voterID) {
		return (voterCount > 0) ? getCitizenByID(0, voterCount - 1, voterID) : null;
	}
	private Citizen getCitizenByID(int leftIndex, int rightIndex, int voterID) {
		int mid;
		
		if (rightIndex >= leftIndex) {
			mid = leftIndex + (rightIndex - leftIndex) / 2;
			if (voterRegistry[mid].getID() == voterID)
				return voterRegistry[mid];
			if (voterRegistry[mid].getID() > voterID)
				return getCitizenByID(leftIndex, mid - 1, voterID);

			return getCitizenByID(mid + 1, rightIndex, voterID);
		}

		return null;
	}

	// extra case parameters will be considered in user classes (Ballot,
	// CoronaBallot, and MilitaryBallot)
	public boolean addCitizen(Citizen citizen) {
		int i;
		
		// Edge case: count = 100
		if (voterCount == Elections.MAX_ARRAY_SIZE)
			return false;
		// Edge case: count = 0
		if (voterCount == 0) {
			voterRegistry[voterCount++] = citizen;
			
			return true;
		}

		// Citizen already in registry
		if (getCitizenByID(citizen.getID()) != null)
			return false;

		// Voter cannot be younger than 18 years old
		if (citizen.calculateAge(votingDate) < Citizen.VOTING_AGE)
			return false;

		// Average case: 0 < count < 100
		i = voterCount - 1;
		while (i >= 0 && voterRegistry[i].getID() > citizen.getID()) {
			voterRegistry[i + 1] = voterRegistry[i];
			i = i - 1;
		}
		voterRegistry[i] = citizen;
		voterCount++;

		return true;
	}
	@Override
	public boolean equals(Object obj) {
		VoterRegistry other;
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		other = (VoterRegistry) obj;
		
		return voterCount == other.voterCount && Arrays.equals(voterRegistry, other.voterRegistry)
				&& votingDate.equals(other.votingDate);
	}
	@Override
	public String toString() {
		StringBuilder stringBuilder;
		
		if (voterCount == 0)
			return "Nothing to See here..";

		stringBuilder = new StringBuilder("Date of voting: " + votingDate + "\nVoter list:\n");
		for (int i = 0; i < voterCount; i++)
			stringBuilder.append(voterRegistry[i].toString());
		
		return stringBuilder.toString();
	}
}
