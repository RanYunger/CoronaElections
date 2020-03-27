package ID318783479_ID316334473;

import java.time.YearMonth;

public class Ballot {
	// Constants

	// Fields
	protected static int IDGenerator = 0;
	
	protected int ID;
	protected String address;
	protected VoterRegistry voterRegistry;
	protected int[] results;
	protected YearMonth votingDate;

	// Properties (Getters and Setters)
	public int getID() {
		return ID;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int[] getResults() {
		return results;
	}
	
	// Constructors
	public Ballot(YearMonth votingDate) {
		this("<UNKNOWN>", votingDate);
	}
	public Ballot(String address, YearMonth votingDate) {
		ID = IDGenerator++;
		setAddress(address);
		voterRegistry = new VoterRegistry(votingDate);
	}

	// Methods
	public Citizen getCitizenByID(int voterID) {
		return voterRegistry.getCitizenByID(voterID);
	}
	public boolean addVoter(Citizen citizen) {
		if (citizen.getAssociatedBallotID() == ID)
			return true;

		if (citizen.getAssociatedBallotID() == -1)
			if (voterRegistry.addCitizen(citizen)) {
				citizen.setAssociatedBallotID(this);
				return true;
			}

		return false;
	}
	public boolean vote(String partyName) {
		int partyOffset = Elections.getPartyOffsetByName(partyName);
		
		if(partyOffset == -1)
			return false;
		
		results[partyOffset]++;
		
		return true;
	}
	@Override
	public String toString() {
		return String.format("Ballot [ID: %d | Address: %s | ]", ID, address);		
	}
}
