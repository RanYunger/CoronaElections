package ID318783479_ID316334473.Models;

import java.util.ArrayList;

import javafx.scene.Group;

public class VoteModel {
	// Constants

	// Fields
	private ArrayList<PartyModel> parties;

	// Properties (Getters and Setters)
	public ArrayList<PartyModel> getParties() {
		return parties;
	}

	public void setParties(ArrayList<PartyModel> parties) {
		this.parties = parties;
	}

	// Constructors
	public VoteModel(ArrayList<PartyModel> parties) {
		setParties(parties);
	}

	// Methods
	public void show(Group root) {
		// TODO Auto-generated method stub
	}
}
