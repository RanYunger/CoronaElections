package ID318783479_ID316334473.Models;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import javafx.scene.Group;

public class PartiesTabModel {
	// Constants

	// Fields
	private YearMonth electionsDate;
	private ArrayList<PartyModel> parties;

	// Properties (Getters and Setters)
	public YearMonth getElectionsDate() {
		return electionsDate;
	}

	public void setElectionsDate(YearMonth electionsDate) {
		this.electionsDate = electionsDate;
	}

	public ArrayList<PartyModel> getParties() {
		return parties;
	}

	public void setParties(ArrayList<PartyModel> parties) {
		this.parties = parties;
	}

	// Constructors
	public PartiesTabModel(YearMonth electionsDate) {
		setElectionsDate(electionsDate);
		setParties(new ArrayList<PartyModel>());
		
		init();
	}

	// Methods
	public void show(Group root) {
		// TODO Auto-generated method stub
	}

	private void init() {
		// Initiates 4 parties
		parties.add(new PartyModel("Halikud", PartyModel.PartyAssociation.Right, LocalDate.of(1973, 9, 13)));
		parties.add(new PartyModel("Blue and White", PartyModel.PartyAssociation.Center, LocalDate.of(2019, 2, 21)));
		parties.add(new PartyModel("Israel is Our Home", PartyModel.PartyAssociation.Center, LocalDate.of(1999, 3, 29)));
		parties.add(new PartyModel("Israeli Labor PartyModel", PartyModel.PartyAssociation.Left, LocalDate.of(1968, 1, 21)));
	}
}
