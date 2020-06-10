package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.AddCitizenModel;
import ID318783479_ID316334473.Models.CitizensTabModel;
import ID318783479_ID316334473.Views.AddCitizenView;
import ID318783479_ID316334473.Views.CitizensTabView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

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

		citizensTabView.refresh(model);
		
		EventHandler<ActionEvent> addCitizenButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					AddCitizenModel model = new AddCitizenModel();
					AddCitizenView view = new AddCitizenView(new Stage(), citizensTabModel.getElectionsDate());
					AddCitizenController controller = new AddCitizenController(model, view);
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured", ex.getMessage());
				}
			}
		};
		EventHandler<ActionEvent> removeCitizenButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TableView<String> citizensTableView = (TableView<String>) citizensTabView.getNodeByName("citizensTableView");
				int selectedIndex = citizensTableView.getSelectionModel().getSelectedIndex();
				
				try {
					// Validations
					if (selectedIndex == -1)
						throw new IllegalStateException("Choose a citizen to remove.");
					
				} catch (IllegalStateException ex) {
					UIHandler.showError(ex.getMessage());
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured.", ex.getMessage());
				}
			}
		};
		
		view.addEventHandlerToButton("addCitizenButton" ,addCitizenButtonEventHandler);
		view.addEventHandlerToButton("removeCitizenButton" ,removeCitizenButtonEventHandler);
	}
	
	// Methods
}
