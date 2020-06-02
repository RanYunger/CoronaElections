package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.Models.BallotsTabModel;
import ID318783479_ID316334473.Models.MainModel;
import ID318783479_ID316334473.Models.SetModel;
import ID318783479_ID316334473.Views.BallotsTabView;
import ID318783479_ID316334473.Views.MainView;

public class BallotsTabController {
	// Constants

	// Fields
	private BallotsTabModel ballotsTabModel;
	private BallotsTabView ballotsTabView;

	// Properties (Getters and Setters)
	public BallotsTabModel getBallotsTabModel() {
		return ballotsTabModel;
	}

	public void setBallotsTabModel(BallotsTabModel ballotsTabModel) {
		this.ballotsTabModel = ballotsTabModel;
	}

	public BallotsTabView getBallotsTabView() {
		return ballotsTabView;
	}

	public void setBallotsTabView(BallotsTabView ballotsTabView) {
		this.ballotsTabView = ballotsTabView;
	}

	// Constructors
	public BallotsTabController(BallotsTabModel model, BallotsTabView view) {
		setBallotsTabModel(model);
		setBallotsTabView(view);
		
		view.refresh(model);
	}

	// Methods
}
