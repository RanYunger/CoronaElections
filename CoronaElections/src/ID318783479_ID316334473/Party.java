package ID318783479_ID316334473;

import java.time.LocalDate;
import java.util.ArrayList;

public class Party {
	// Constants
	public enum PartyAssociation {
		Left, Center, Right
	}

	// Fields
	private String name;
	private PartyAssociation wing;
	private LocalDate foundationDate;
	private ArrayList<Candidate> candidates;

	// Properties (Getters and Setters)
	public String getName() {
		return name;
	}

	private void setName(String name) throws Exception {
		if (name.trim().length() == 0)
			throw new Exception("Party's name must contain at least 1 letter.");
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

	private void setFoundationDate(LocalDate foundationDate) throws Exception {
		if (foundationDate.compareTo(LocalDate.now()) > 0)
			throw new Exception("Time paradox prevented.");
		this.foundationDate = foundationDate;
	}

	public ArrayList<Candidate> getCandidates() {
		return candidates;
	}

	private void setCandidates(ArrayList<Candidate> candidates) {
		this.candidates = candidates;
	}

	// Constructors
	public Party() {
		this("<UNKNOWN>", PartyAssociation.Center, LocalDate.now());
	}

	public Party(String name, PartyAssociation wing, LocalDate foundationDate) {
		try {
			setName(name);
			setWing(wing);
			setFoundationDate(foundationDate);
			setCandidates(new ArrayList<Candidate>());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	// Methods
	public Candidate getCandidateByID(int candidateID) {
		for (int i = 0; i < candidates.size(); i++)
			if ((candidates.get(i).getID() == candidateID))
				return candidates.get(i);

		return null;
	}

	public int getCandidateOffsetByID(int candidateID) {
		for (int i = 0; i < candidates.size(); i++)
			if ((candidates.get(i).getID() == candidateID))
				return i;

		return -1;
	}

	public boolean addCandidate(Candidate candidate) {
		try {
			// Validations
			if (candidates.size() == UIHandler.MAX_ARRAY_SIZE)
				throw new Exception("Cannot add more candidates to this party.");	
			if (getCandidateByID(candidate.getID()) != null)
				throw new Exception("This candidate is already in this party.");	
			
			candidates.add(candidate);
			candidate.setAssociatedParty(this);
			candidate.setRank(candidates.size());

			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return false;
	}

	public boolean addCandidate(Candidate candidate, int rank) {
		try {
			// Validations
			if (candidates.size() == UIHandler.MAX_ARRAY_SIZE)
				throw new Exception("Cannot add more candidates to this party.");	
			if (getCandidateByID(candidate.getID()) != null)
				throw new Exception("This candidate is already in this party.");	
			
			for (int i = candidates.size(); i > rank - 1; i++) {
				candidates.set(i, candidates.get(i - 1));
				candidates.get(i).setRank(i + 1);
			}
			candidates.set(rank - 1, candidate);
			candidate.setRank(rank);

			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return false;
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
		if (candidates.size() == 0)
			sb.append("\n\t\tNothing to see here...");
		else {
			for (int i = 0; i < candidates.size(); i++)
				sb.append("\n\t\t" + candidates.get(i).toString());
		}

		return sb.toString();
	}
}
