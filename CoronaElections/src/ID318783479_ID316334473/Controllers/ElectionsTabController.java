package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.Models.ElectionsTabModel;
import ID318783479_ID316334473.Views.ElectionsTabView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
		
		EventHandler<ActionEvent> runElectionsButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: COMPLETE
			}
		};
		EventHandler<ActionEvent> showResultsButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: COMPLETE
			}
		};
		
		view.addEventHandlerToButton("runElectionsButton" ,runElectionsButtonEventHandler);
		view.addEventHandlerToButton("showResultsButton" ,showResultsButtonEventHandler);
	}
	
	// Methods
}
