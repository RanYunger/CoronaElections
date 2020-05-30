package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.Models.MainModel;
import ID318783479_ID316334473.Views.MainView;

public class MainController {
	// Constants
	
	// Fields
	private MainModel mainModel;
	private MainView mainView;
	
	// Properties (Getters and Setters)
	public MainModel getMainModel() {
		return mainModel;
	}
	
	private void setMainModel(MainModel mainModel) {
		this.mainModel = mainModel;
	}
	
	public MainView getMainView() {
		return mainView;
	}
	
	private void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
	
	// Constructors
	public MainController(MainModel model, MainView view) {
		setMainModel(model);
		setMainView(view);
		
		view.refresh(model);
	}
	
	// Methods
}
