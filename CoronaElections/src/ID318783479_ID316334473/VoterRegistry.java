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
		voterCount = 0;
		for (int i = 0; (i < voterRegistry.length) && (voterRegistry[i] != null); i++, voterCount++);
	}

	public int getVoterCount() {
		return voterCount;
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
		setVoterRegistry(new Citizen[Program.MAX_ARRAY_SIZE]);
		voterCount = 0;
		setVotingDate(votingDate);
	}

	// Methods
	public Citizen getByID(int citizenID) {
		return (indexOf(citizenID) == -1) ? null : voterRegistry[indexOf(citizenID)];
	}

	public Citizen get(int index) {
		return (index < 0 || voterCount <= index) ? null : voterRegistry[index];
	}

	public int indexOf(int citizenID) {
		return indexOf(0, voterCount - 1, citizenID);
	}

	private int indexOf(int leftIndex, int rightIndex, int citizenID) {
		int mid;

		if (rightIndex >= leftIndex) {
			mid = leftIndex + (rightIndex - leftIndex) / 2;
			if (voterRegistry[mid].getID() == citizenID)
				return mid;
			if (voterRegistry[mid].getID() > citizenID)
				return indexOf(leftIndex, mid - 1, citizenID);

			return indexOf(mid + 1, rightIndex, citizenID);
		}

		return -1;
	}

	public boolean add(Citizen citizen) {
		int i;

		// Validations
		if (voterCount == Program.MAX_ARRAY_SIZE) {
			System.out.println(String.format("Cannot add more voters (%d voters max)", Program.MAX_ARRAY_SIZE));
			
			return false;
		}
		if (voterCount == 0) {
			voterRegistry[voterCount] = citizen;
			voterCount++;
			System.out.println("Voter successfully added to the voter registry.");

			return true;
		}
		if (getByID(citizen.getID()) != null) {
			System.out.println("Voter already exists in the voter registry.");
			
			return false;
		}
		if ((votingDate.getYear() - citizen.getYearOfBirth()) < Citizen.VOTER_AGE) {
			System.out.println(String.format("Voter cannot participate in elections (minimum %d years old).", Citizen.VOTER_AGE));
			
			return false;
		}

		i = voterCount;
		while (i > 0 && voterRegistry[i - 1].getID() > citizen.getID()) {
			voterRegistry[i] = voterRegistry[i - 1];
			i--;
		}
		voterRegistry[i] = citizen;
		voterCount++;
		System.out.println("Voter successfully added to the voter registry.");
		
		return true;
	}

	public boolean remove(int citizenID) {
		int citizenOffset = indexOf(citizenID), i;

		// Validations
		if (voterCount == 0) {
			System.out.println("Cannot remove any voter (non-existing).");
			
			return false;
		}
		if (citizenOffset == -1) {
			System.out.println("Voter already doesn't exist in the citizen registry.");
			
			return false;
		}

		voterRegistry[citizenOffset] = null;
		i = citizenOffset;
		while ((i > 0) && (voterRegistry[i - 1].getID() < voterRegistry[i + 1].getID())) {
			voterRegistry[i] = voterRegistry[i + 1];
			i++;
		}
		voterCount--;
		System.out.println("Voter successfully removed from the citizen registry.");
		
		return true;
	}

	public boolean updateCitizenToCandidate(Citizen citizen) {
		int index = indexOf(citizen.getID());

		if (index != -1) {
			if (citizen.getClass() == Citizen.class) // "morphs" the Citizen into a Candidate
				voterRegistry[index] = new Candidate(citizen.getID(), citizen.getFullName(), citizen.getYearOfBirth(),
						citizen.getAssociatedBallot(), citizen.isIsolated(), citizen.iswearingSuit(), null, -1);

			return true;
		}

		return false;
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

		return (voterCount == other.voterCount) && (Arrays.equals(voterRegistry, other.voterRegistry)) && (votingDate.equals(other.votingDate));
	}

	@Override
	public String toString() {
		StringBuilder sb;

		if (voterCount == 0)
			return "Nothing to See here..";

		sb = new StringBuilder("Date of voting: " + votingDate + "\nVoter list:\n");
		for (int i = 0; i < voterCount; i++)
			sb.append(voterRegistry[i].toString() + "\n");
		sb.deleteCharAt(sb.length() - 1); // Removes last \n

		return sb.toString();
	}
}