package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.SearchHandler;
import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Views.AddBallotView;
import ID318783479_ID316334473.Views.BallotsTabView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class AddBallotController {
	// Constants

	// Fields
	private BallotsTabView tabView;
	private AddBallotView addView;

	// Properties (Getters and Setters)
	public BallotsTabView getBallotTabView() {
		return tabView;
	}

	public void setBallotTabView(BallotsTabView tabview) {
		this.tabView = tabview;
	}

	public AddBallotView getAddBallotView() {
		return addView;
	}

	public void setAddBallotView(AddBallotView addView) {
		this.addView = addView;
	}

	// Constructors
	public AddBallotController(BallotsTabView tabView, AddBallotView addView) {
		setBallotTabView(tabView);
		setAddBallotView(addView);

		EventHandler<ActionEvent> submitButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TextField ballotAddressTextField = addView.getBallotAddressTextField();
				String address = ballotAddressTextField.getText(),
						ballotType = addView.getBallotTypeComboBox().getValue();

				// Validations
				if (!address.matches(SearchHandler.VALID_BALLOT_ADDRESS_PATTERN)) {
					UIHandler.showError("Invalid address!", ballotAddressTextField.getTooltip().getText());
					return;
				}

				ballotType = ballotType == null ? addView.getBallotTypeComboBox().getItems().get(0) : ballotType;

				tabView.addBallot(SearchHandler.createBallotByType(ballotType, address, UIHandler.getElectionsDate()));
				UIHandler.showSuccess("A new ballot was added successfully!");

				addView.close();
			}
		};

		addView.getSubmitButton().setOnAction(submitButtonEventHandler);
	}
}
