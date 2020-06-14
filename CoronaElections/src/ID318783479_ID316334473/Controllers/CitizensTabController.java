package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Views.AddCitizenView;
import ID318783479_ID316334473.Views.CitizensTabView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class CitizensTabController {
	// Constants

	// Fields
	private CitizensTabView citizensTabView;

	// Properties (Getters and Setters)

	public CitizensTabView getCitizensTabView() {
		return citizensTabView;
	}

	public void setCitizensTabView(CitizensTabView citizensTabView) {
		this.citizensTabView = citizensTabView;
	}

	// Constructors
	public CitizensTabController(CitizensTabView view) {
		setCitizensTabView(view);

		EventHandler<ActionEvent> addCitizenButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			@SuppressWarnings("unused")
			public void handle(ActionEvent event) {
				try {
					AddCitizenView addView = new AddCitizenView(new Stage(), UIHandler.getElectionsDate());
					AddCitizenController controller = new AddCitizenController(citizensTabView, addView);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		EventHandler<ActionEvent> removeCitizenButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			@SuppressWarnings("unchecked")
			public void handle(ActionEvent event) {
				TableView<String> citizensTableView = (TableView<String>) citizensTabView
						.getNodeByName("citizensTableView");
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

		citizensTabView.addEventHandlerToButton("addCitizenButton", addCitizenButtonEventHandler);
		citizensTabView.addEventHandlerToButton("removeCitizenButton", removeCitizenButtonEventHandler);
	}

	// Methods
}
