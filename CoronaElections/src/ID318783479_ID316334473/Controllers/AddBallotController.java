package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.SearchHandler;
import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.ValidPatterns;
import ID318783479_ID316334473.Views.AddBallotView;
import ID318783479_ID316334473.Views.BallotsTabView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class AddBallotController {
	public AddBallotController(BallotsTabView ballotTabView, AddBallotView addView) {
		EventHandler<ActionEvent> submitButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TextField ballotAddressTextField = addView.getBallotAddressTextField();
				String address = ballotAddressTextField.getText(),
						ballotType = addView.getBallotTypeComboBox().getValue();

				// Validations
				if (!address.matches(ValidPatterns.BALLOT_ADDRESS.getPattern())) {
					UIHandler.showError("Invalid address!", ballotAddressTextField.getTooltip().getText());
					return;
				}

				ballotType = (ballotType == null) ? addView.getBallotTypeComboBox().getItems().get(0) : ballotType;

				UIHandler.getMainView().getBallotsTabView()
						.addBallot(SearchHandler.createBallotByType(ballotType, address, UIHandler.getElectionsDate()));
				addView.close();
				UIHandler.showSuccess("A new ballot was added successfully!");
			}
		};

		addView.getSubmitButton().setOnAction(submitButtonEventHandler);
	}
}
