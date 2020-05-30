package ID318783479_ID316334473.Models;

import java.time.LocalDate;
import java.util.ArrayList;

public class PartyModel implements Comparable<PartyModel> {
	// Constants
	public enum PartyAssociation {
		Left, Center, Right
	}

	// Fields
	private String name;
	private PartyAssociation wing;
	private LocalDate foundationDate;
	private ArrayList<CandidateModel> candidates;
	private int capacity;

	// Properties (Getters and Setters)
	public String getName() {
		return name;
	}

	private void setName(String name) throws Exception {
		if (name.trim().length() == 0)
			throw new Exception("PartyModel's name must contain at least 1 letter.");
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
			throw new Exception("Time paradox prevented - creation time can't be in the future.");
		this.foundationDate = foundationDate;
	}

	public ArrayList<CandidateModel> getCandidates() {
		return candidates;
	}

	private void setCandidates(ArrayList<CandidateModel> candidates) {
		this.candidates = candidates;
	}

	public int getCandidateCount() {
		return candidates.size();
	}

	// Constructors
	public PartyModel() {
		this("<UNKNOWN>", PartyAssociation.Center, LocalDate.now());
	}

	public PartyModel(String name, PartyAssociation wing, LocalDate foundationDate) {
		try {
			setName(name);
			setWing(wing);
			setFoundationDate(foundationDate);
			setCandidates(new ArrayList<CandidateModel>());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	// Methods
	public CandidateModel getCandidateByID(int candidateID) {
		try {
			return candidates.get(getCandidateOffsetByID(candidateID));
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public int getCandidateOffsetByID(int candidateID) {
		return getCandidateOffsetByID(candidateID, 0, candidates.size() - 1);
	}

	public int getCandidateOffsetByID(int candidateID, int start, int end) {
		if (end >= start) {
			int mid = (start + end) / 2;
			int ID = candidates.get(mid).getID();
			if (ID == candidateID)
				return mid;

			if (ID > candidateID)
				return getCandidateOffsetByID(candidateID, start, mid - 1);

			return getCandidateOffsetByID(candidateID, mid + 1, end);
		}
		return -1;
	}

	public boolean addCandidate(CandidateModel candidate) {
		return addCandidate(candidate, candidates.size());
	}

	public boolean addCandidate(CandidateModel candidate, int rank) {
		try {
			// Validations
			int lastRank = candidates.size() - 1;

			if (getCandidateByID(candidate.getID()) != null)
				throw new Exception("This candidate is already in this party.");
			if (rank > lastRank || rank == -1)
				candidates.add(candidate);
			else
				candidates.add(rank, candidate);

			candidate.joinParty(this);
			ensureCapacity();

			return true;
		} catch (Exception e) {
			// TODO: fix this, or ask about using it
			if (!e.getStackTrace()[1].toString().split("[.(]")[1].equals("CandidateModel")) {
				System.err.println(e.getMessage());
				return false;
			}
			return true;
		}

	}

	private void ensureCapacity() {
		if (candidates.size() == capacity) {
			capacity *= 2;
			candidates.ensureCapacity(capacity);
		}
	}

	private static String ordinal(int rank) {
		switch (rank % 10) {
		case 1:
			if (rank % 100 != 11)
				return (rank + "st");
			break;
		case 2:
			if (rank % 100 != 12)
				return (rank + "nd");
			break;
		case 3:
			if (rank % 100 != 13)
				return (rank + "rd");
			break;
		}
		return (rank + "th");
	}

	@Override
	public int compareTo(PartyModel other) {
		return name.compareToIgnoreCase(other.name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PartyModel))
			return false;
		PartyModel other = (PartyModel) obj;
		return name.equalsIgnoreCase(other.name); // Two parties can't have the same name
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("PartyModel [Name: %s | Association: %s | Foundation: %s]\n", name, wing.toString(),
				foundationDate.toString()));
		sb.append("\tCandidates:");
		if (candidates.size() == 0)
			sb.append("\n\t\tNothing to see here...");
		else {
			sb.append(String.format("\n\t\tParty leader: %s", candidates.get(0).toString()));
			for (int i = 1; i < candidates.size(); i++)
				sb.append(String.format("\n\t\t%s runner: %s", ordinal(i), candidates.get(i).toString()));
		}
		return sb.toString();
	}
}
