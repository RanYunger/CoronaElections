package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Views.ElectionsTabView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ElectionsTabController {
	// Constants

	// Fields
	private ElectionsTabView tabView;

	// Properties (Getters and Setters)
	public ElectionsTabView getElectionsTabView() {
		return tabView;
	}

	public void setElectionsTabView(ElectionsTabView electionsTabView) {
		this.tabView = electionsTabView;
	}

	// Constructors
	public ElectionsTabController(ElectionsTabView view) {
		setElectionsTabView(view);

		EventHandler<ActionEvent> runElectionsButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					// TODO: COMPLETE
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured.", ex.getMessage());
				}
			}
		};
		EventHandler<ActionEvent> showResultsButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					// TODO: COMPLETE

				} catch (IllegalStateException ex) {
					UIHandler.showError(ex.getMessage());
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured.", ex.getMessage());
				}
			}
		};

		tabView.getRunElectionsButton().setOnAction(runElectionsButtonEventHandler);
		tabView.getShowResultsButton().setOnAction(showResultsButtonEventHandler);
	}

	// Methods
}
