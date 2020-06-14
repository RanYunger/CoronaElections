package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Views.ElectionsTabView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ElectionsTabController {
	// Constants

	// Fields
	private ElectionsTabView electionsTabView;

	// Properties (Getters and Setters)
	public ElectionsTabView getElectionsTabView() {
		return electionsTabView;
	}

	public void setElectionsTabView(ElectionsTabView electionsTabView) {
		this.electionsTabView = electionsTabView;
	}

	// Constructors
	public ElectionsTabController(ElectionsTabView view) {
		setElectionsTabView(view);

		EventHandler<ActionEvent> runElectionsButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					// TODO: fix
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured.", ex.getMessage());
				}
			}
		};
		EventHandler<ActionEvent> showResultsButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					// TODO: fix

				} catch (IllegalStateException ex) {
					UIHandler.showError(ex.getMessage());
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured.", ex.getMessage());
				}
			}
		};

		electionsTabView.addEventHandlerToButton("runElectionsButton", runElectionsButtonEventHandler);
		electionsTabView.addEventHandlerToButton("showResultsButton", showResultsButtonEventHandler);
	}

	// Methods
}
