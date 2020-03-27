package ID318783479_ID316334473;

import java.time.YearMonth;
//import java.util.ArrayList;
import java.util.Arrays;

public class Party {
	// Constants
	enum PartyAssociation {Left, Center, Right}
	
	// Fields
	private int RANK_GENERATOR;
	
	private String name;
	private PartyAssociation wing;
	private YearMonth foundationDate;
	private Candidate[] candidates;
	
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
	public YearMonth getFoundationDate() {
		return foundationDate;
	}
	private void setFoundationDate(YearMonth foundationDate) {
		this.foundationDate = foundationDate;
	}
	public Candidate[] getCandidates() {
		return candidates;
	}
	private void setCandidates(Candidate[] candidates) {
		this.candidates = candidates;
		sortCandidates();
	}
	
	// Constructors
	public Party() {
		this("<UNKNOWN>", PartyAssociation.Center, YearMonth.now());
	}
	public Party(String name, PartyAssociation wing, YearMonth foundationDate) {
		setName(name);
		setWing(wing);
		setFoundationDate(foundationDate);
		setCandidates(new Candidate[Elections.MAX_ARRAY_SIZE]);	//setCandidates(new ArrayList<Candidate>());
		
		RANK_GENERATOR = 1;		
	}
	
	// Methods
	public Candidate getCandidateByID(int candidateID) {
		for (int i = 0; i < candidates.length; i++)
			if(candidates[i].getID() == candidateID)
				return candidates[i];
		
		return null;
	}
	public int getCandidateOffsetByID(int candidateID) {
		for (int i = 0; i < candidates.length; i++)
			if(candidates[i].getID() == candidateID)
				return i;
		
		return -1;
	}
	public boolean addCandidate(Candidate addedCandidate) {
		Candidate[] newCandidatesArray = new Candidate[candidates.length + 1];
		
		if(newCandidatesArray.length >= Elections.MAX_ARRAY_SIZE)
			return false;
		
		addedCandidate.setRank(RANK_GENERATOR++);
		candidates = Arrays.copyOf(candidates, candidates.length + 1);
		newCandidatesArray[newCandidatesArray.length - 1] = addedCandidate;
		sortCandidates();
		
		return true;
	}
	public boolean removeCandidate(int candidateID) {
		int candidateOffset = getCandidateOffsetByID(candidateID);
		
		if(candidateOffset == -1)
			return false;
		
		candidates[candidateOffset] = null;
		candidates = Arrays.copyOf(candidates, candidates.length - 1);
		sortCandidates();
		
		return true;
	}	
	public void sortCandidates() {
		// TODO: implement this method (implement quicksort method in TUI for Candidate's ranks ~Ran)
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
		String candidatesStr = "";
		
		for (int i = 0; i < candidates.length; i++)
			candidatesStr += "\n" + candidates[i].toString();
		
		return String.format("Party [Name: %s | Association: %s | Founded: %d]\nCandidates:%s",
				name, wing.name(), foundationDate.getYear(), candidatesStr);
	}
}
