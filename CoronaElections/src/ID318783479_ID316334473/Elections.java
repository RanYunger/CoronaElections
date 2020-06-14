package ID318783479_ID316334473;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import ID318783479_ID316334473.Models.PartyModel;
import ID318783479_ID316334473.Models.Ballots.BallotModel;
import ID318783479_ID316334473.Models.Ballots.CoronaBallotModel;
import ID318783479_ID316334473.Models.Ballots.CoronaMilitaryBallotModel;
import ID318783479_ID316334473.Models.Ballots.MilitaryBallotModel;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;

@SuppressWarnings("unchecked")
public class Elections {
	// Fields

	public static LocalDate votingDate = null;
	public static ArrayList<CitizenModel> voters = new ArrayList<CitizenModel>();
	public static ArrayList<PartyModel> parties = new ArrayList<PartyModel>();
	public static ArrayList<BallotModel> citizenBallots = new ArrayList<BallotModel>();
	public static ArrayList<MilitaryBallotModel> soldierBallots = new ArrayList<MilitaryBallotModel>();
	public static ArrayList<CoronaBallotModel> sickCitizenBallots = new ArrayList<CoronaBallotModel>();
	public static ArrayList<CoronaMilitaryBallotModel> sickSoldierBallots = new ArrayList<CoronaMilitaryBallotModel>();

	// Methods
	public static PartyModel getPartyByName(ArrayList<PartyModel> parties, String partyName) {
		ArrayList<PartyModel> sortedParties = (ArrayList<PartyModel>) parties.clone();
		Collections.sort(sortedParties);
		return TBN.binarySearch(sortedParties, partyName);
	}

	// Binary Search
	public static BallotModel getBallotByID(ArrayList<BallotModel> ballots, int ballotID) {
		return TBN.binarySearch(ballots, ballotID);
	}
}