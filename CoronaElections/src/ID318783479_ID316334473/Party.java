package ID318783479_ID316334473;

import java.time.LocalDate;
import java.util.Arrays;

public class Party {
	// Constants
	public enum PartyAssociation {
		Left, Center, Right
	}

	// Fields
	private int RANK_GENERATOR;

	private String name;
	private PartyAssociation wing;
	private LocalDate foundationDate;
	private Candidate[] candidates;
	private int candidateCount;

	// Properties (Getters and Setters)
	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public PartyAssociation getWing() {
		return wing;
	}

	private void setWing(PartyAssociation wing) {
		this.wing = wing;
	}

	public LocalDate getFoundationDate() {
		return foundationDate;
	}

	private void setFoundationDate(LocalDate foundationDate) {
		this.foundationDate = foundationDate;
	}

	public Candidate[] getCandidates() {
		return candidates;
	}

	private void setCandidates(Candidate[] candidates) {
		this.candidates = candidates;
	}

	public int getCandidateCount() {
		return candidateCount;
	}

	private void setCandidateCount(int candidateCount) {
		this.candidateCount = candidateCount;
	}

	// Constructors
	public Party() {
		this("<UNKNOWN>", PartyAssociation.Center, LocalDate.now());
	}

	public Party(String name, PartyAssociation wing, LocalDate foundationDate) {
		setName(name);
		setWing(wing);
		setFoundationDate(foundationDate);
		setCandidates(new Candidate[Elections.MAX_ARRAY_SIZE]);
		setCandidateCount(0);

		RANK_GENERATOR = 1;
	}

	// Methods
	public Candidate getCandidateByID(int candidateID) {
		for (int i = 0; i < candidates.length; i++)
			if ((candidates[i] != null) && (candidates[i].getID() == candidateID))
				return candidates[i];

		return null;
	}

	public int getCandidateOffsetByID(int candidateID) {
		for (int i = 0; i < candidates.length; i++)
			if ((candidates[i] != null) && (candidates[i].getID() == candidateID))
				return i;

		return -1;
	}

	public boolean addCandidate(Candidate candidate) {
		// Validations
		if (candidateCount == Elections.MAX_ARRAY_SIZE)
			return false;
		if (candidateCount == 0) {
			candidates[candidateCount++] = candidate;
			candidate.setAssociatedParty(this);
			candidate.setRank(RANK_GENERATOR++);

			return true;
		}
		if (getCandidateByID(candidate.getID()) != null)
			return false;

		candidates[candidateCount++] = candidate;
		candidate.setAssociatedParty(this);
		candidate.setRank(RANK_GENERATOR++);

		return true;
	}

	public boolean removeCandidate(int candidateID) {
		int candidateOffset = getCandidateOffsetByID(candidateID), i;

		// Validations
		if (candidateCount == 0)
			return false;
		if (candidateOffset == -1)
			return false;

		candidates[candidateOffset] = null;
		i = candidateOffset;
		while (i < candidateCount && candidates[i].getRank() < candidates[i + 1].getRank()) {
			candidates[i] = candidates[i + 1];
			i++;
		}
		candidateCount--;

		// prevents inconsistencies, such as 2, 3, 4... or 1, 2, 4, 5...
		for (i = 0; i < candidateCount; i++)
			candidates[i].setRank(i + 1);

		return true;
	}

	@Override
	public boolean equals(Object obj) {
		Party other = null;

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		other = (Party) obj;
		if (!Arrays.equals(candidates, other.candidates))
			return false;
		if (foundationDate == null) {
			if (other.foundationDate != null)
				return false;
		} else if (!foundationDate.equals(other.foundationDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (wing != other.wing)
			return false;

		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("Party [Name: %s | Association: %s | Foundation: %s]\n", name, wing.toString(), foundationDate.toString()));
		sb.append("Candidates:\n");
		if(candidateCount == 0)
			sb.append("Nothing to see here...");
		else for (int i = 0; i < candidateCount; i++)
			sb.append(candidates[i].toString() + "\n");
		
		return sb.toString();
	}
}
