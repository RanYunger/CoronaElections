package ID318783479_ID316334473;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Ballot {
	// Constants
	
	// Fields
	private int IDGenerator = 1;
	
	private int ID;
	private String address;
	private Citizen[] voterRegister;	//private ArrayList<Citizen> voterRegister;
	private int voters;
	private int[] results;
	
	// Properties (Getters and Setters)
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
	/*public ArrayList<Citizen> getVoterRegister() {
		return voterRegister;
	}
	public void setVoterRegister(ArrayList<Citizen> voterRegister) {
		this.voterRegister = voterRegister;
		
		setVoters();
	}*/
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
	public Ballot(String address, Citizen[] voterRegister, int[] results) {
		setID(IDGenerator++);
		setAddress(address);
		setVoterRegister(voterRegister);
		setResults(results);
	}
//	public Ballot() {
//		//this("<UNKNOWN>", new ArrayList<Citizen>(), new int[Elections.getParties().size()]);
//	}
//	public Ballot(String address, ArrayList<Citizen> voterRegister, int[] results) {
//		setID(IDGenerator++);
//		setAddress(address);
//		setVoterRegister(voterRegister);
//		setResults(results);
//	}
	
	// Methods
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
		if (IDGenerator != other.IDGenerator)
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
		//ArrayList<Party> parties = Elections.getParties();
		String voterRegisterStr = "", resultsStr = "";

//		for (int i = 0; i < voterRegister.size(); i++)
//			voterRegisterStr += "\n" + voterRegister.get(i).toString();
		for (int i = 0; i < voterRegister.length; i++)
			voterRegisterStr += "\n" + voterRegister[i].toString();
		for (int i = 0; i < results.length; i++)
			resultsStr += String.format("\n[%s | %s]", parties[i].getName(), results[i]);
			//resultsStr += String.format("\n[%s | %s]", parties.get(i).getName(), results[i]);
		
		return String.format("Ballot [ID: %d | Address: %s | Voters: %d]\\nVoter Register:%s\nResults:%s",
				ID, address, voters, voterRegisterStr, resultsStr);
	}
	
}
