package ID318783479_ID316334473;

import java.time.YearMonth;
import java.util.Scanner;

public class Ballot {
	// Constants

	// Fields
	protected static int IDGenerator = 0;
	
	protected int ID;
	protected String address;
	protected VoterRegistry voterRegistry;
	protected double votersPercentage;
	protected int[] results;

	// Properties (Getters and Setters)
	public int getID() {
		return ID;
	}
	private void setID(int ID) {
		this.ID = ID;
	}
	public String getAddress() {
		return address;
	}
	private void setAddress(String address) {
		this.address = address;
	}
	public VoterRegistry getVoterRegistry() {
		return voterRegistry;
	}
	private void setVoterRegistry(VoterRegistry voterRegistry) {
		this.voterRegistry = voterRegistry;
	}
	public double getVotersPercentage() {
		return votersPercentage;
	}
	private void setVotersPercentage(double votersPercentage) {
		this.votersPercentage = votersPercentage;
	}
	public int[] getResults() {
		return results;
	}
	private void setResults(int[] results) {
		this.results = results;
	}
	
	// Constructors
	public Ballot(YearMonth votingDate) {
		this("<UNKNOWN>", votingDate);
	}
	public Ballot(String address, YearMonth votingDate) {
		setID(IDGenerator++);
		setAddress(address);
		setVoterRegistry(new VoterRegistry(votingDate));
		setVotersPercentage(0);
		setResults(null);
	}

	// Methods
	public Citizen getCitizenByID(int citizenID) {
		return voterRegistry.getCitizenByID(citizenID);
	}
	public boolean addVoter(Citizen citizen) {
		if (citizen.getAssociatedBallot().getID() == ID)
			return true;

		if (citizen.getAssociatedBallot().getID() == -1)
			if (voterRegistry.addCitizen(citizen)) {
				citizen.setAssociatedBallot(this);
				setVotersPercentage((votersPercentage / voterRegistry.getVoterCount()) * 100);
				
				return true;
			}

		return false;
	}
	public boolean removeVoter(int citizenID) {
		Citizen citizen = getCitizenByID(citizenID);
		
		if(citizen == null)
			return false;		
		if(citizen.getAssociatedBallot().getID() == ID) {
			if (voterRegistry.removeCitizen(citizenID)) {
				citizen.setAssociatedBallot(null);
				setVotersPercentage((votersPercentage / voterRegistry.getVoterCount()) * 100);
				
				return true;
			}
		}

		return false;
	}
	public int[] vote(Scanner scan, Party[] candidateParties) {
		int voterCount = voterRegistry.getVoterCount(), currVoterChoice;
		Citizen[] votersArr = voterRegistry.getVoterRegistry();
		
		for (int i = 0; i < voterCount; i++) {
			currVoterChoice = votersArr[i].vote(scan, candidateParties);
			results[currVoterChoice]++;
		}
		
		return results;
	}
	@Override
	public String toString() {
		return String.format("Ballot [ID: %d | Address: %s | ]", ID, address);		
	}
}
