package ID318783479_ID316334473;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Elections {
	// Constants
	
	// Fields
	private LocalDateTime date;
	private ArrayList<Citizen> voterRegister;
	private static ArrayList<Party> parties;	
	private ArrayList<Ballot> ballots;
	
	// Properties (Getters and Setters)
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public ArrayList<Citizen> getVoterRegister() {
		return voterRegister;
	}
	public void setVoterRegister(ArrayList<Citizen> voterRegister) {
		this.voterRegister = voterRegister;
	}
	public static ArrayList<Party> getParties() {
		return parties;
	}
	public void setParties(ArrayList<Party> parties) {
		this.parties = parties;
	}
	public ArrayList<Ballot> getBallots() {
		return ballots;
	}
	public void setBallots(ArrayList<Ballot> ballots) {
		this.ballots = ballots;
	}
	
	// Constructors
	public Elections() {
		this(LocalDateTime.now());
	}
	public Elections(LocalDateTime date) {
		this(date, new ArrayList<Citizen>(), new ArrayList<Party>(), new ArrayList<Ballot>());
	}
	public Elections(LocalDateTime date, ArrayList<Citizen> voterRegister, ArrayList<Party> parties, ArrayList<Ballot> ballots) {
		setDate(date);
		setVoterRegister(voterRegister);
		setParties(parties);
		setBallots(ballots);
	}
	
	// Methods
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
		if (parties == null) {
			if (other.parties != null)
				return false;
		} else if (!parties.equals(other.parties))
			return false;
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
		
		for (int i = 0; i < parties.size(); i++)
			partiesStr += "\n" + parties.get(i).toString();
		for (int i = 0; i < ballots.size(); i++)
			ballotsStr += "\n" + ballots.get(i).toString();
		
		return String.format("Elections [Date: %s]\nParties:%s\nBallots:%s", date, partiesStr, ballotsStr);
	}	
}
