package ID318783479_ID316334473;

import java.time.LocalDate;
import java.util.Arrays;
//import java.util.ArrayList;

public class Ballot /*implements iAssociateVoters*/ {
	// Constants

	// Fields
	private static int IDGenerator = 1;

	private int ID;
	private String address;
	private Citizen[] voterRegister; // private ArrayList<Citizen> voterRegister;
	private int voters;
	private int[] results;

	// Properties (Getters and Setters)
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Citizen[] getVoterRegister() {
		return voterRegister;
	}
	public void setVoterRegister(Citizen[] voterRegister) {
		this.voterRegister = voterRegister;

		setVoters();
	}
//	public ArrayList<Citizen> getVoterRegister() {
//		return voterRegister;
//	}
//	public void setVoterRegister(ArrayList<Citizen> voterRegister) {
//		this.voterRegister = voterRegister;  
//		setVoters(); 
//	}	 
	public int getVoters() {
		return voters;
	}
	public void setVoters() {
		int currentYear = LocalDate.now().getYear();

		for (int i = 0; i < voterRegister.length; i++)
			this.voters += (currentYear - voterRegister[i].getDateOfBirth().getYear()) >= Citizen.VOTING_AGE ? 1 : 0;
//		for (int i = 0; i < voterRegister.size(); i++)
//			this.voters += (currentYear - voterRegister.get(i).getDateOfBirth().getYear()) >= Citizen.VOTING_AGE ? 1 : 0;
	}
	public int[] getResults() {
		return results;
	}
	public void setResults(int[] results) {
		this.results = results;
	}

	// Constructors
	public Ballot() {
		this("<UNKNOWN>", new Citizen[Program.MAX_ARRAY_SIZE], new int[Elections.getParties().length]);
	}
	public Ballot(String address) {
		this(address, new Citizen[Program.MAX_ARRAY_SIZE], new int[Elections.getParties().length]);
	}
	public Ballot(String address, Citizen[] voterRegister, int[] results) {
		setID(IDGenerator++);
		setAddress(address);
		setVoterRegister(voterRegister);
		setResults(results);
	}
//	public Ballot() {
//		//this("<UNKNOWN>", new ArrayList<Citizen>(), new int[Elections.getParties().size()]);
//	}
//	public Ballot(String address) {
//		this(address, new new ArrayList<Citizen>(), new int[Elections.getParties().length]);
//	}
//	public Ballot(String address, ArrayList<Citizen> voterRegister, int[] results) {
//		setID(IDGenerator++);
//		setAddress(address);
//		setVoterRegister(voterRegister);
//		setResults(results);
//	}

	// Methods
	private void associateVoters() {
		Citizen[] voterRegister = Elections.getVoterRegister();
		
		for (int i = 0; i < voterRegister.length; i++)
//			if(voterRegister.get(i).getAssociatedBallotID() == this.ID)
			if(voterRegister[i].getAssociatedBallotID() == this.ID)
				addVoter(voterRegister[i]);
	}	
	public Citizen getVoterByID(int voterID) {
		for (int i = 0; i < voterRegister.length; i++)
//			if(voterRegister.get(i).getAssociatedBallotID() == this.ID)
			if(voterRegister[i].getID() == voterID)
				return voterRegister[i];
		
		return null;
	}
	public int getVoterOffsetByID(int voterID) {
		for (int i = 0; i < voterRegister.length; i++)
//			if(voterRegister.get(i).getAssociatedBallotID() == this.ID)
			if(voterRegister[i].getID() == voterID)
				return i;
		
		return -1;
	}
	public boolean addVoter(Citizen addedVoter) {
		if((voterRegister.length + 1) > Program.MAX_ARRAY_SIZE)
//		if((voterRegister.size() + 1) > Program.MAX_ARRAY_SIZE)
			return false;

//		voterRegister.add(addedVoter);
		voterRegister = Arrays.copyOf(voterRegister, voterRegister.length + 1);
		voterRegister[voterRegister.length - 1] = addedVoter;
		
		return true;
	}
	public boolean removeVoter(int voterID) {
		int voterOffset = getVoterOffsetByID(voterID);
		
		if(voterOffset == -1)
			return false;
		
//		voterRegister.removeAt(voterOffset);
		voterRegister[voterOffset] = null;
		voterRegister = Arrays.copyOf(voterRegister, voterRegister.length - 1);
		
		return true;
	}	
	public void vote(int voterID, int partyOffset) {
		results[partyOffset]++;
		getVoterByID(voterID).setCanVote(false);
	}
	
	@Override
	public boolean equals(Object obj) {
		Ballot other = null;

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		other = (Ballot) obj;
		if (ID != other.ID)
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (!Arrays.equals(results, other.results))
			return false;
		if (voterRegister == null) {
			if (other.voterRegister != null)
				return false;
		} else if (!voterRegister.equals(other.voterRegister))
			return false;
		if (voters != other.voters)
			return false;

		return true;
	}

	@Override
	public String toString() {
		Party[] parties = Elections.getParties();
		// ArrayList<Party> parties = Elections.getParties();
		String voterRegisterStr = "", resultsStr = "";

//		for (int i = 0; i < voterRegister.size(); i++)
//			voterRegisterStr += "\n" + voterRegister.get(i).toString();
		for (int i = 0; i < voterRegister.length; i++)
			voterRegisterStr += "\n" + voterRegister[i].toString();
		for (int i = 0; i < results.length; i++)
			resultsStr += String.format("\n[%s | %s]", parties[i].getName(), results[i]);
		// resultsStr += String.format("\n[%s | %s]", parties.get(i).getName(), results[i]);

		return String.format("Ballot [ID: %d | Address: %s | Voters: %d]\\nVoter Register:%s\nResults:%s", ID, address,
				voters, voterRegisterStr, resultsStr);
	}

}
