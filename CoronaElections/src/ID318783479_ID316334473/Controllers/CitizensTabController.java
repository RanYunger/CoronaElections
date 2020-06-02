package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.Models.CitizensTabModel;
import ID318783479_ID316334473.Views.CitizensTabView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CitizensTabController {
	// Constants
	
	// Fields
	private CitizensTabModel citizensTabModel;
	private CitizensTabView citizensTabView;

	// Properties (Getters and Setters)
	public CitizensTabModel getCitizensTabModel() {
		return citizensTabModel;
	}

	public void setCitizensTabModel(CitizensTabModel citizensTabModel) {
		this.citizensTabModel = citizensTabModel;
	}

	public CitizensTabView getCitizensTabView() {
		return citizensTabView;
	}

	public void setCitizensTabView(CitizensTabView citizensTabView) {
		this.citizensTabView = citizensTabView;
	}

	// Constructors
	public CitizensTabController(CitizensTabModel model, CitizensTabView view) {
		setCitizensTabModel(model);
		setCitizensTabView(view);
		
		view.refresh(model);
		
		EventHandler<ActionEvent> addCitizenButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: COMPLETE
			}
		};
		EventHandler<ActionEvent> removeCitizenButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: COMPLETE
			}
		};
		view.addEventHandlerToButton("addCitizenButton" ,addCitizenButtonEventHandler);
		view.addEventHandlerToButton("removeCitizenButton" ,removeCitizenButtonEventHandler);
	}
	
	// Methods
}
