package ID318783479_ID316334473;

import java.time.LocalDate;
//import java.util.ArrayList;
import java.util.Arrays;

public class Elections {
	// Constants

	// Fields
	private LocalDate date;
	private static Citizen[] voterRegister; // private ArrayList<Citizen> voterRegister;
	private static Party[] parties; // private static ArrayList<Party> parties;
	private static Ballot[] ballots; // private ArrayList<Ballot> ballots;

	// Properties (Getters and Setters)
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public static Citizen[] getVoterRegister() {
		return voterRegister;
	}
	public void setVoterRegister(Citizen[] voterRegister) {
		Elections.voterRegister = voterRegister;
	}
//	public ArrayList<Citizen> getVoterRegister() {
//		return voterRegister;
//	}
//	public void setVoterRegister(ArrayList<Citizen> voterRegister) {
//		this.voterRegister = voterRegister;
//	}
	public static Party[] getParties() {
		return parties;
	}
	public void setParties(Party[] parties) {
		Elections.parties = parties;
	}
//	public static ArrayList<Party> getParties() {
//		return parties;
//	}
//	public void setParties(ArrayList<Party> parties) {
//		this.parties = parties;
//	}
	public static Ballot[] getBallots() {
		return ballots;
	}
	public void setBallots(Ballot[] ballots) {
		Elections.ballots = ballots;
	}
//	public ArrayList<Ballot> getBallots() {
//		return ballots;
//	}
//	public void setBallots(ArrayList<Ballot> ballots) {
//		this.ballots = ballots;
//	}

	// Constructors
	public Elections() {
		this(LocalDate.now());
	}
	public Elections(LocalDate date) {
		this(date, new Citizen[Program.MAX_ARRAY_SIZE], new Party[Program.MAX_ARRAY_SIZE],
				new Ballot[Program.MAX_ARRAY_SIZE]);
	}
	public Elections(LocalDate date, Citizen[] voterRegister, Party[] parties, Ballot[] ballots) {
		setDate(date);
		setVoterRegister(voterRegister);
		setParties(parties);
		setBallots(ballots);
	}
//	public Elections(LocalDate date) {
//		this(date, new ArrayList<Citizen>(), new ArrayList<Party>(), new ArrayList<Ballot>());
//	}
//	public Elections(LocalDate date, ArrayList<Citizen> voterRegister, ArrayList<Party> parties, ArrayList<Ballot> ballots) {
//		setDate(date);
//		setVoterRegister(voterRegister);
//		setParties(parties);
//		setBallots(ballots);
//	}

	// Methods
	public void startElections() {
		// TODO: COMPLETE
	}
	public Citizen getCitizenByID(int citizenID) {
		for (int i = 0; i < voterRegister.length; i++)
//			if(voterRegister.get(i).getID() == citizenID)
			if(voterRegister[i].getID() == citizenID)
				return voterRegister[i];
		
		return null;
	}
	public int getCitizenOffsetByID(int citizenID) {
		for (int i = 0; i < voterRegister.length; i++)
//			if(voterRegister.get(i).getID() == citizenID)
			if(voterRegister[i].getID() == citizenID)
				return i;
		
		return -1;
	}
	public boolean addCitizenToRegister(Citizen addedCitizen) {
//		if((voterRegister.size() + 1) > Program.MAX_ARRAY_SIZE)
		if((voterRegister.length + 1) > Program.MAX_ARRAY_SIZE)
			return false;

//		voterRegister.add(addedCitizen);
		voterRegister = Arrays.copyOf(voterRegister, voterRegister.length + 1);
		voterRegister[voterRegister.length - 1] = addedCitizen;
		
		return true;
	}
	public boolean removeCitizenFromRegister(int citizenID) {
		int citizenOffset = getCitizenOffsetByID(citizenID);
		
		if(citizenOffset == -1)
			return false;
		
//		voterRegister.removeAt(citizenOffset);
		voterRegister[citizenOffset] = null;
		voterRegister = Arrays.copyOf(voterRegister, voterRegister.length - 1);
		
		return true;
	}	
	public Ballot getBallotByID(int ballotID) {
		for (int i = 0; i < ballots.length; i++)
//			if(ballots.get(i).getID() == ballotID)
			if(ballots[i].getID() == ballotID)
				return ballots[i];
		
		return null;
	}
	public int getBallotOffsetByID(int ballotID) {
		for (int i = 0; i < ballots.length; i++)
//			if(ballots.get(i).getID() == ballotID)
			if(ballots[i].getID() == ballotID)
				return i;
		
		return -1;
	}
	public boolean addBallot(Ballot addedBallot) {
//		if((ballots.size() + 1) > Program.MAX_ARRAY_SIZE)
		if((ballots.length + 1) > Program.MAX_ARRAY_SIZE)
			return false;

//		ballots.add(addedBallot);
		ballots = Arrays.copyOf(ballots, ballots.length + 1);
		ballots[ballots.length - 1] = addedBallot;
		
		return true;
	}
	public boolean removeBallot(int ballotID) {
		int ballotOffset = getBallotOffsetByID(ballotID);
		
		if(ballotOffset == -1)
			return false;
		
//		ballots.removeAt(ballotOffset);
		ballots[ballotOffset] = null;
		ballots = Arrays.copyOf(ballots, ballots.length - 1);
		
		return true;
	}
	public Party getPartyByName(String partyName) {
		for (int i = 0; i < parties.length; i++)
//			if(parties.get(i).getName() == partyName)
			if(parties[i].getName() == partyName)
				return parties[i];
		
		return null;
	}
	public int getPartyOffsetByName(String partyName) {
		for (int i = 0; i < parties.length; i++)
//			if(parties.get(i).getName() == partyName)
			if(parties[i].getName() == partyName)
				return i;
		
		return -1;
	}
	public boolean addParty(Party addedParty) {
//		if((parties.size() + 1) > Program.MAX_ARRAY_SIZE)
		if((parties.length + 1) > Program.MAX_ARRAY_SIZE)
			return false;

//		parties.add(addedParty);
		parties = Arrays.copyOf(parties, parties.length + 1);
		parties[parties.length - 1] = addedParty;
		
		return true;
	}
	public boolean removeParty(String partyName) {
		int partyOffset = getPartyOffsetByName(partyName);
		
		if(partyOffset == -1)
			return false;
		
//		parties.removeAt(partyOffset);
		parties[partyOffset] = null;
		parties = Arrays.copyOf(parties, parties.length - 1);
		
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		Elections other = null;

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		other = (Elections) obj;
//		if (ballots == null) {
//			if (other.ballots != null)
//				return false;
//		} else if (!ballots.equals(other.ballots))
//			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
//		if (parties == null) {
//			if (other.parties != null)
//				return false;
//		} else if (!parties.equals(other.parties))
//			return false;
//		if (voterRegister == null) {
//			if (other.voterRegister != null)
//				return false;
//		} else if (!voterRegister.equals(other.voterRegister))
//			return false;

		return true;
	}

	@Override
	public String toString() {
		String partiesStr = "", ballotsStr = "";

		for (int i = 0; i < parties.length; i++)
			partiesStr += "\n" + parties[i].toString();
		for (int i = 0; i < ballots.length; i++)
			ballotsStr += "\n" + ballots[i].toString();
//		for (int i = 0; i < parties.size(); i++)
//			partiesStr += "\n" + parties.get(i).toString();
//		for (int i = 0; i < ballots.size(); i++)
//			ballotsStr += "\n" + ballots.get(i).toString();

		return String.format("Elections [Date: %s]\nParties:%s\nBallots:%s", date, partiesStr, ballotsStr);
	}
}
