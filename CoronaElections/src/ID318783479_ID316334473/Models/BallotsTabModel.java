package ID318783479_ID316334473.Models;

import java.time.YearMonth;
import java.util.ArrayList;

import javafx.scene.Group;

public class BallotsTabModel {

	// Constants

	// Fields
	private YearMonth electionsDate;
	private ArrayList<BallotModel<CitizenModel>> citizenBallots;
	private ArrayList<BallotModel<SoldierModel>> soldierBallots;
	private ArrayList<BallotModel<SickCitizenModel>> sickCitizenBallots;
	private ArrayList<BallotModel<SickCandidateModel>> sickCandidateBallots;
	private ArrayList<BallotModel<SickSoldierModel>> sickSoldierBallots;

	// Properties (Getters and Setters)
	public YearMonth getElectionsDate() {
		return electionsDate;
	}

	public void setElectionsDate(YearMonth electionsDate) {
		this.electionsDate = electionsDate;
	}

	public ArrayList<BallotModel<CitizenModel>> getCitizenBallots() {
		return citizenBallots;
	}

	private void setCitizenBallots(ArrayList<BallotModel<CitizenModel>> citizenBallots) {
		this.citizenBallots = citizenBallots;
	}

	public ArrayList<BallotModel<SoldierModel>> getSoldierBallots() {
		return soldierBallots;
	}

	private void setSoldierBallots(ArrayList<BallotModel<SoldierModel>> soldierBallots) {
		this.soldierBallots = soldierBallots;
	}

	public ArrayList<BallotModel<SickCitizenModel>> getSickCitizenBallots() {
		return sickCitizenBallots;
	}

	private void setSickCitizenBallots(ArrayList<BallotModel<SickCitizenModel>> sickCitizenBallots) {
		this.sickCitizenBallots = sickCitizenBallots;
	}

	public ArrayList<BallotModel<SickCandidateModel>> getSickCandidateBallots() {
		return sickCandidateBallots;
	}

	private void setSickCandidateBallots(ArrayList<BallotModel<SickCandidateModel>> sickCandidateBallots) {
		this.sickCandidateBallots = sickCandidateBallots;
	}

	public ArrayList<BallotModel<SickSoldierModel>> getSickSoldierBallots() {
		return sickSoldierBallots;
	}

	private void setSickSoldierBallots(ArrayList<BallotModel<SickSoldierModel>> sickSoldierBallots) {
		this.sickSoldierBallots = sickSoldierBallots;
	}

	// Constructors
	public BallotsTabModel(YearMonth electionsDate) {
		setElectionsDate(electionsDate);
		setCitizenBallots(new ArrayList<BallotModel<CitizenModel>>());
		setSoldierBallots(new ArrayList<BallotModel<SoldierModel>>());
		setSickCitizenBallots(new ArrayList<BallotModel<SickCitizenModel>>());
		setSickCandidateBallots(new ArrayList<BallotModel<SickCandidateModel>>());
		setSickSoldierBallots(new ArrayList<BallotModel<SickSoldierModel>>());

		init(electionsDate);
	}

	// Methods
	public void show(Group root) {
		// TODO Auto-generated method stub
	}

	private void init(YearMonth electionsDate) {
		// Initiates 4 ballots
		citizenBallots.add(new BallotModel<CitizenModel>("CitizenModel", "21st Road Street, Town City", electionsDate));
		soldierBallots.add(new BallotModel<SoldierModel>("SoldierModel", "Area 51, Nevada", electionsDate));
		sickCitizenBallots.add(new BallotModel<SickCitizenModel>("Sick CitizenModel", electionsDate));
		sickCandidateBallots.add(new BallotModel<SickCandidateModel>("Sick CandidateModel", electionsDate));
	}
}
