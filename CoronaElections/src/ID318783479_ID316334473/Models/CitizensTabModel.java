package ID318783479_ID316334473.Models;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;

import ID318783479_ID316334473.TBN;
import ID318783479_ID316334473.UIHandler;
import javafx.scene.Group;

public class CitizensTabModel {
	// Constants

	// Fields
	private YearMonth electionsDate;
	public ArrayList<CitizenModel> citizens;

	// Properties (Getters and Setters)
	public YearMonth getElectionsDate() {
		return electionsDate;
	}

	public void setElectionsDate(YearMonth electionsDate) {
		this.electionsDate = electionsDate;
	}

	public ArrayList<CitizenModel> getCitizens() {
		return citizens;
	}

	public void setCitizens(ArrayList<CitizenModel> citizens) {
		this.citizens = citizens;
	}

	// Constructors
	public CitizensTabModel(YearMonth electionsDate) {
		setElectionsDate(electionsDate);
		setCitizens(new ArrayList<CitizenModel>());

//		init();
	}

	// Methods
	public void show(Group root) {
		// TODO Auto-generated method stub
	}

	private void init() {
		BallotsTabModel ballotsTabModel = (BallotsTabModel) UIHandler.getModelByName("ballotsTabModel");
		ArrayList<BallotModel<CitizenModel>> citizenBallots = ballotsTabModel.getCitizenBallots();
		ArrayList<BallotModel<SoldierModel>> soldierBallots = ballotsTabModel.getSoldierBallots();
		ArrayList<BallotModel<SickCitizenModel>> sickCitizenBallots = ballotsTabModel.getSickCitizenBallots();
		ArrayList<BallotModel<SickCandidateModel>> sickCandidateBallots = ballotsTabModel.getSickCandidateBallots();

		// Initiates 5 citizen
		citizens.add(new CitizenModel(123456789, "Charles Foster Kane", 1941, 0, citizenBallots.get(0), false, false));
		citizens.add(new CitizenModel(234567890, "Donald John Trump", 1946, 1, citizenBallots.get(0), true, true));
		citizens.add(new CitizenModel(345678901, "Tonny Stark", 1970, 0, citizenBallots.get(0), false, false));
		citizens.add(new SickCitizenModel(456789012, "Steve Rogers", 1918, 1, sickCitizenBallots.get(0), true, true));
		citizens.add(
				new SoldierModel(567890123, "Childish Gambino", 2001, 1, soldierBallots.get(0), false, false, true));

		// Initiates 8 candidates (2 per party)
		citizens.add(new CandidateModel(678901234, "Benjamin Netanyahu", 1949, 50, citizenBallots.get(0), true, false));
		citizens.add(
				new SickCandidateModel(789012345, "Miri Regev", 1965, 38, sickCandidateBallots.get(0), true, false));
		citizens.add(
				new SickCandidateModel(890123456, "Benny Gantz", 1959, 40, sickCandidateBallots.get(0), true, true));
		citizens.add(new SickCandidateModel(901234567, "Yair Lapid", 1963, 1, sickCandidateBallots.get(0), true, true));
		citizens.add(new SickCandidateModel(901234568, "Avigdor Lieberman", 1958, 1, sickCandidateBallots.get(0), true,
				true));
		citizens.add(new CandidateModel(901234566, "Oded Forer", 1977, 1, citizenBallots.get(0), false, true));
		citizens.add(new CandidateModel(901234569, "Tamar Zandberg", 1976, 1, citizenBallots.get(0), false, false));
		citizens.add(new CandidateModel(901234565, "Nitzan Horowitz", 1965, 1, citizenBallots.get(0), false, false));
	}

	public boolean addCitizen() {
		// TODO: COMPLETE

		try {
		} catch (Exception ex) {
			UIHandler.showError("An unexpected error occured", ex.getMessage());
		}
		return false;
	}

	public boolean removeCitizen() {
		// TODO: COMPLETE

		try {
		} catch (Exception ex) {
			UIHandler.showError("An unexpected error occured", ex.getMessage());
		}
		return false;
	}

	public CitizenModel getCitizenByID(int citizenID) {
		ArrayList<CitizenModel> sortedVoters = (ArrayList<CitizenModel>) citizens.clone();
		Collections.sort(sortedVoters);
		return TBN.binarySearch(sortedVoters, citizenID);
	}

	public boolean morphCitizenToCandidate(CitizenModel citizen) {
		int index = citizens.indexOf(getCitizenByID(citizen.getID()));

		if (index != -1) {
			if (citizen.getClass() == CitizenModel.class) // "morphs" the CitizenModel into a CandidateModel
				citizens.set(index,
						citizen.isIsolated()
								? new SickCandidateModel(citizen.getID(), citizen.getFullName(),
										citizen.getYearOfBirth(), citizen.getDaysOfSickness(),
										citizen.getAssociatedBallot(), citizen.isIsolated(), citizen.iswearingSuit())
								: new CandidateModel(citizen.getID(), citizen.getFullName(), citizen.getYearOfBirth(),
										citizen.getDaysOfSickness(), citizen.getAssociatedBallot(),
										citizen.isIsolated(), citizen.iswearingSuit()));

			return true;
		}

		return false;
	}

	public boolean morphCandidateToCitizen(CandidateModel candidate) {
		int index = citizens.indexOf(getCitizenByID(candidate.getID()));

		if (index != -1) {
			if (candidate.getClass() == CandidateModel.class) // "morphs" the CandidateModel into a CitizenModel
				citizens.set(index, candidate.isIsolated()
						? new SickCitizenModel(candidate.getID(), candidate.getFullName(), candidate.getYearOfBirth(),
								candidate.getDaysOfSickness(), candidate.getAssociatedBallot(), candidate.isIsolated(),
								candidate.iswearingSuit())
						: new CitizenModel(candidate.getID(), candidate.getFullName(), candidate.getYearOfBirth(),
								candidate.getDaysOfSickness(), candidate.getAssociatedBallot(), candidate.isIsolated(),
								candidate.iswearingSuit()));

			return true;
		}

		return false;
	}
}
