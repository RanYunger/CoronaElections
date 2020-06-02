package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.Models.ElectionsTabModel;
import ID318783479_ID316334473.Views.ElectionsTabView;

public class ElectionsTabController {
	// Constants
	
	// Fields
	private ElectionsTabModel electionsTabModel;
	private ElectionsTabView electionsTabView;

	// Properties (Getters and Setters)
	public ElectionsTabModel getElectionsTabModel() {
		return electionsTabModel;
	}

	public void setElectionsTabModel(ElectionsTabModel electionsTabModel) {
		this.electionsTabModel = electionsTabModel;
	}

	public ElectionsTabView getElectionsTabView() {
		return electionsTabView;
	}

	public void setElectionsTabView(ElectionsTabView electionsTabView) {
		this.electionsTabView = electionsTabView;
	}

	// Constructors
	public ElectionsTabController(ElectionsTabModel model, ElectionsTabView view) {
		setElectionsTabModel(model);
		setElectionsTabView(view);
		
		view.refresh(model);
	}
	
	// Methods
}
