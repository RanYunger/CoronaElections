package ID318783479_ID316334473;

import java.time.YearMonth;
import java.util.Arrays;

public class VoterRegistry {
	// Constants

	// Fields
	private Citizen[] voterRegistry;
	private int voterCount;
	private YearMonth votingDate;

	// Properties (Getters and Setters)	
	public Citizen[] getVoterRegistry() {
		return voterRegistry;
	}	
	private void setVoterRegistry(Citizen[] voterRegistry) {
		this.voterRegistry = voterRegistry;
	}	
	public int getVoterCount() {
		return voterCount;
	}
	private void setVoterCount(int voterCount) {
		this.voterCount = voterCount;
	}	
	public YearMonth getVotingDate() {
		return votingDate;
	}
	private void setVotingDate(YearMonth votingDate) {
		this.votingDate = votingDate;
	}

	// Constructors
	public VoterRegistry() {
		this(YearMonth.now());
	}
	public VoterRegistry(YearMonth votingDate) {
		setVoterRegistry(new Citizen[Elections.MAX_ARRAY_SIZE]);
		setVoterCount(0);
		setVotingDate(votingDate);
	}

	// Methods
	public Citizen getCitizenByID(int citizenID) {
		return (voterCount > 0) ? getCitizenByID(0, voterCount - 1, citizenID) : null;
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
	public int getCitizenOffsetByID(int citizenID) {
		for (int i = 0; i < voterCount; i++)
			if(voterRegistry[i].getID() == citizenID)
				return i;
		
		return -1;
	}
	public boolean addCitizen(Citizen citizen) {
		int i;
		
		// Validations
		if (voterCount == Elections.MAX_ARRAY_SIZE)
			return false;
		if (voterCount == 0) {
			voterRegistry[voterCount++] = citizen;
			
			return true;
		}
		if (getCitizenByID(citizen.getID()) != null)
			return false;
		if ((citizen.getYearOfBirth() - votingDate.getYear()) < Citizen.VOTING_AGE)
			return false;

		i = voterCount - 1;
		while (i >= 0 && voterRegistry[i].getID() > citizen.getID()) {
			voterRegistry[i + 1] = voterRegistry[i];
			i--;
		}
		voterRegistry[i] = citizen;
		voterCount++;

		return true;
	}
	public boolean removeCitizen(int citizenID) {
		int citizenOffset = getCitizenOffsetByID(citizenID), i;
		
		// Validations
		if (voterCount == 0)
			return false;
		if (citizenOffset == -1)
			return false;

		voterRegistry[citizenOffset] = null;
		i = citizenOffset;
		while (i < voterCount && voterRegistry[i].getID() < voterRegistry[i].getID()) {
			voterRegistry[i] = voterRegistry[i + 1];
			i++;
		}
		voterCount--;

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
