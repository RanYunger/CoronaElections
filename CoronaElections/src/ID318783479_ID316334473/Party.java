package ID318783479_ID316334473;

import java.time.LocalDate;
//import java.util.ArrayList;

public class Party {
	// Constants
	enum PartyAssociation {Left, Center, Right}
	
	// Fields
	private String name;
	private PartyAssociation association;
	private LocalDate foundationDate;
	private Candidate[] candidates;			//private ArrayList<Candidate> candidates; // Sorted by Candidate rank
	
	// Properties (Getters and Setters)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PartyAssociation getAssociation() {
		return association;
	}
	public void setAssociation(PartyAssociation association) {
		this.association = association;
	}
	public LocalDate getFoundationDate() {
		return foundationDate;
	}
	public void setFoundationDate(LocalDate foundationDate) {
		this.foundationDate = foundationDate;
	}
	public Candidate[] getCandidates() {
		return candidates;
	}
	public void setCandidates(Candidate[] candidates) {
		this.candidates = candidates;
		SortCandidates();
	}
//	public ArrayList<Candidate> getCandidates() {
//		return candidates;
//	}
//	public void setCandidates(ArrayList<Candidate> candidates) {
//		this.candidates = candidates;
//		// SortCandidates();
//	}
	
	// Constructors
	public Party() {
		this("<UNKNOWN>", PartyAssociation.Center, LocalDate.now());
	}
	public Party(String name, PartyAssociation association, LocalDate foundationDate) {
		setName(name);
		setAssociation(association);
		setFoundationDate(foundationDate);
		setCandidates(new Candidate[Program.MAX_ARRAY_SIZE]);	//setCandidates(new ArrayList<Candidate>());
	}

	// Methods
	// TODO: "addCandidate" method
	
	
	public void SortCandidates() {
		// TODO: COMPLETE (Sorting candidates by rank (after sorting, substract (first - 1) from all ranks (RANK_GENERATOR doesn't reset))
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
		if (association != other.association)
			return false;
		if (candidates == null) {
			if (other.candidates != null)
				return false;
		} else if (!candidates.equals(other.candidates))
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
		
		return true;
	}
	@Override
	public String toString() {
		String candidatesStr = "";
		
		for (int i = 0; i < candidates.length; i++)
			candidatesStr += "\n" + candidates[i].toString();
//		for (int i = 0; i < candidates.size(); i++)
//			candidatesStr += "\n" + candidates.get(i).toString();
		
		return String.format("Party [Name: %s | Association: %s | Founded: %d]\nCandidates:%s",
				name, association, foundationDate.getYear(), candidatesStr);
	}
}
