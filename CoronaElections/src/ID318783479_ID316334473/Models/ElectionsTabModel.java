package ID318783479_ID316334473.Models;

import java.time.YearMonth;

import javafx.scene.Group;

public class ElectionsTabModel {
	// Constants

	// Fields
	private YearMonth electionsDate;

	// Properties (Getters and Setters)
	public YearMonth getElectionsDate() {
		return electionsDate;
	}

	public void setElectionsDate(YearMonth electionsDate) {
		this.electionsDate = electionsDate;
	}

	// Constructors
	public ElectionsTabModel(YearMonth electionsDate) {
		setElectionsDate(electionsDate);
		
		init();
	}

	// Methods
	public void show(Group root) {
		// TODO Auto-generated method stub
	}

	private void init() {
	}
}
