package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.UIHandler;
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

		electionsTabView.refresh(model);

		EventHandler<ActionEvent> runElectionsButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					electionsTabModel.runElections();
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured.", ex.getMessage());
				}
			}
		};
		EventHandler<ActionEvent> showResultsButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					// Validations
					if (!electionsTabModel.getElectionsOccurred())
						throw new IllegalStateException("The results will be visible once the process is complete.");
					
					electionsTabModel.showResults();

				} catch (IllegalStateException ex) {
					UIHandler.showError(ex.getMessage());
				} catch (Exception ex) {
					UIHandler.showError("An unexpected error occured.", ex.getMessage());
				}
			}
		};

		view.addEventHandlerToButton("runElectionsButton", runElectionsButtonEventHandler);
		view.addEventHandlerToButton("showResultsButton", showResultsButtonEventHandler);
	}

	// Methods
}
