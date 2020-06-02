package ID318783479_ID316334473.Models;

import java.time.YearMonth;
import javafx.scene.Group;

public class MainModel {
	// Constants

	// Fields
	private YearMonth electionsDate;
	private ElectionsTabModel electionsTabModel;
	private BallotsTabModel ballotsTabModel;
	private CitizensTabModel citizensTabModel;
	private PartiesTabModel partiesTabModel;

	// Properties (Getters and Setters)
	public YearMonth getElectionsDate() {
		return electionsDate;
	}

	private void setElectionsDate(YearMonth electionsDate) {
		this.electionsDate = electionsDate;
	}

	public ElectionsTabModel getElectionsTabModel() {
		return electionsTabModel;
	}

	private void setElectionsTabModel(ElectionsTabModel electionsTabModel) {
		this.electionsTabModel = electionsTabModel;
	}

	public BallotsTabModel getBallotsTabModel() {
		return ballotsTabModel;
	}

	private void setBallotsTabModel(BallotsTabModel ballotsTabModel) {
		this.ballotsTabModel = ballotsTabModel;
	}

	public CitizensTabModel getCitizensTabModel() {
		return citizensTabModel;
	}

	private void setCitizensTabModel(CitizensTabModel citizensTabModel) {
		this.citizensTabModel = citizensTabModel;
	}

	public PartiesTabModel getPartiesTabModel() {
		return partiesTabModel;
	}

	private void setPartiesTabModel(PartiesTabModel partiesTabModel) {
		this.partiesTabModel = partiesTabModel;
	}

	// Constructors
	public MainModel(YearMonth electionsDate) {
		setElectionsDate(electionsDate);
		setElectionsTabModel(new ElectionsTabModel());
		setBallotsTabModel(new BallotsTabModel());
		setCitizensTabModel(new CitizensTabModel());
		setPartiesTabModel(new PartiesTabModel());
	}

	public void show(Group root) {
		// TODO: COMPLETE
	}

	// Methods
}
