package ID318783479_ID316334473;

import java.time.LocalDate;
//import java.util.ArrayList;

public class Elections {
	// Constants

	// Fields
	private LocalDate date;
	private Citizen[] voterRegister; // private ArrayList<Citizen> voterRegister;
	private static Party[] parties; // private static ArrayList<Party> parties;
	private Ballot[] ballots; // private ArrayList<Ballot> ballots;

	// Properties (Getters and Setters)
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Citizen[] getVoterRegister() {
		return voterRegister;
	}

	public void setVoterRegister(Citizen[] voterRegister) {
		this.voterRegister = voterRegister;
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

	public static void setParties(Party[] parties) {
		Elections.parties = parties;
	}

//	public static ArrayList<Party> getParties() {
//		return parties;
//	}
//	public void setParties(ArrayList<Party> parties) {
//		this.parties = parties;
//	}
	public Ballot[] getBallots() {
		return ballots;
	}

	public void setBallots(Ballot[] ballots) {
		this.ballots = ballots;
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

	// TODO: "addCitizenToRegistry" method
	// TODO: "getVoterFromRegistry" method
	// TODO: "getPartyByName" method
	// TODO: "addBallot" method
	// TODO: "addParty" method
	// TODO: "addCandidate" method?
	// TODO: "setCitizenAsCandidate" method

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
		if (ballots == null) {
			if (other.ballots != null)
				return false;
		} else if (!ballots.equals(other.ballots))
			return false;
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
		if (voterRegister == null) {
			if (other.voterRegister != null)
				return false;
		} else if (!voterRegister.equals(other.voterRegister))
			return false;

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
