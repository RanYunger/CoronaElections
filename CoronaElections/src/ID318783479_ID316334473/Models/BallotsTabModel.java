package ID318783479_ID316334473.Models;

import java.time.LocalDate;
import java.util.ArrayList;

import ID318783479_ID316334473.TBN;
import ID318783479_ID316334473.UIHandler;
import javafx.scene.Group;

public class BallotsTabModel {

	// Constants

	// Fields
	private LocalDate electionsDate;
	private ArrayList<BallotModel<CitizenModel>> citizenBallots;
	private ArrayList<BallotModel<SoldierModel>> soldierBallots;
	private ArrayList<BallotModel<SickCitizenModel>> sickCitizenBallots;
	private ArrayList<BallotModel<SickCandidateModel>> sickCandidateBallots;
	private ArrayList<BallotModel<SickSoldierModel>> sickSoldierBallots;

	// Properties (Getters and Setters)
	public LocalDate getElectionsDate() {
		return electionsDate;
	}

	public void setElectionsDate(LocalDate electionsDate) {
		this.electionsDate = electionsDate;
	}

	public ArrayList<BallotModel<? extends CitizenModel>> getAllBallots() {
		ArrayList<BallotModel<? extends CitizenModel>> allBallots = new ArrayList<BallotModel<? extends CitizenModel>>();

		allBallots.addAll(citizenBallots);
		allBallots.addAll(soldierBallots);
		allBallots.addAll(sickCitizenBallots);
		allBallots.addAll(sickCandidateBallots);
		allBallots.addAll(sickSoldierBallots);

		return allBallots;
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
	public BallotsTabModel(LocalDate electionsDate) {
		setElectionsDate(electionsDate);
		setCitizenBallots(new ArrayList<BallotModel<CitizenModel>>());
		setSoldierBallots(new ArrayList<BallotModel<SoldierModel>>());
		setSickCitizenBallots(new ArrayList<BallotModel<SickCitizenModel>>());
		setSickCandidateBallots(new ArrayList<BallotModel<SickCandidateModel>>());
		setSickSoldierBallots(new ArrayList<BallotModel<SickSoldierModel>>());
	}

	// Methods
	public void show(Group root) {
		// TODO Auto-generated method stub
	}

	public void init(LocalDate electionsDate) {
		// Initiates 4 ballots
		citizenBallots.add(new BallotModel<CitizenModel>("CitizenModel", "21st Road Street, Town City", electionsDate));
		soldierBallots.add(new BallotModel<SoldierModel>("SoldierModel", "Area 51, Nevada", electionsDate));
		sickCitizenBallots.add(new BallotModel<SickCitizenModel>("Sick CitizenModel", electionsDate));
		sickCandidateBallots.add(new BallotModel<SickCandidateModel>("Sick CandidateModel", electionsDate));
	}

	public boolean addBallot() {
		// TODO: COMPLETE

		try {
		} catch (Exception ex) {
			UIHandler.showError("An unexpected error occured", ex.getMessage());
		}
		return false;
	}

	public boolean removeBallot() {
		// TODO: COMPLETE

		try {
		} catch (Exception ex) {
			UIHandler.showError("An unexpected error occured", ex.getMessage());
		}
		return false;
	}

	public BallotModel<? extends CitizenModel> getBallotByID(int ballotID) {
		return TBN.binarySearch(getAllBallots(), ballotID);
	}
}
