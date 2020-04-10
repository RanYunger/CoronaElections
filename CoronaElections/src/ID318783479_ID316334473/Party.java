package ID318783479_ID316334473;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Party {
	// Constants
	public enum PartyAssociation {
		Left, Center, Right
	}

	// Fields

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
		setCandidates(new Candidate[UIHandler.MAX_ARRAY_SIZE]);
		setCandidateCount(0);
	}

	// Methods
	public Candidate getCandidateByID(int candidateID) {
		for (int i = 0; i < candidateCount; i++)
			if ((candidates[i].getID() == candidateID))
				return candidates[i];

		return null;
	}

	public int getCandidateOffsetByID(int candidateID) {
		for (int i = 0; i < candidateCount; i++)
			if ((candidates[i].getID() == candidateID))
				return i;

		return -1;
	}

	public boolean addCandidate(Candidate candidate) {
		// Validations
		if (candidateCount == UIHandler.MAX_ARRAY_SIZE)
			return false;

		if (getCandidateByID(candidate.getID()) != null)
			return false;

		candidates[candidateCount++] = candidate;
		candidate.setAssociatedParty(this);
		candidate.setRank(candidateCount);

		return true;
	}

	public boolean addCandidate(Candidate candidate, int rank) {
		if (candidateCount == UIHandler.MAX_ARRAY_SIZE)
			return false;

		if (getCandidateByID(candidate.getID()) != null)
			return false;

		for (int i = candidateCount; i > rank - 1; i++) {
			candidates[i] = candidates[i - 1];
			candidates[i].setRank(i + 1);
		}

		candidates[rank - 1] = candidate;
		candidate.setRank(rank);

		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Party))
			return false;
		Party other = (Party) obj;
		return name.equalsIgnoreCase(other.name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("Party [Name: %s | Association: %s | Foundation: %s]\n", name, wing.toString(),
				foundationDate.toString()));
		sb.append("\tCandidates:");
		if (candidateCount == 0)
			sb.append("\n\t\tNothing to see here...");
		else {
			for (int i = 0; i < candidateCount; i++)
				sb.append("\n\t\t" + candidates[i].toString());
		}

		return sb.toString();
	}
}
