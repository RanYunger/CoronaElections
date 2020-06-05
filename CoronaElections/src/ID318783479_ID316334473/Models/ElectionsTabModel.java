package ID318783479_ID316334473.Models;

import java.time.YearMonth;

import ID318783479_ID316334473.UIHandler;
import javafx.scene.Group;

public class ElectionsTabModel {
	// Constants

	// Fields
	private YearMonth electionsDate;
	private boolean electionsOccurred;

	// Properties (Getters and Setters)
	public YearMonth getElectionsDate() {
		return electionsDate;
	}

	public void setElectionsDate(YearMonth electionsDate) {
		this.electionsDate = electionsDate;
	}

	public boolean getElectionsOccurred() {
		return electionsOccurred;
	}

	public void setElectionsOccurred(boolean electionsOccurred) {
		this.electionsOccurred = electionsOccurred;
	}

	// Constructors
	public ElectionsTabModel(YearMonth electionsDate) {
		setElectionsDate(electionsDate);
		setElectionsOccurred(false);

		init();
	}

	// Methods
	public void show(Group root) {
		// TODO Auto-generated method stub
	}

	private void init() {
	}

	public boolean runElections() {
		// TODO: COMPLETE

		try {
		} catch (Exception ex) {
			UIHandler.showError("An unexpected error occured", ex.getMessage());
		}
		return false;
	}

	public boolean showResults() {
		// TODO: COMPLETE

		try {
		} catch (Exception ex) {
			UIHandler.showError("An unexpected error occured", ex.getMessage());
		}
		return false;
	}
}
