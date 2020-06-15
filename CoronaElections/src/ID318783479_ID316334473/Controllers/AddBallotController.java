package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.TBN;
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
				String address = ballotAddressTextField.getText();
				if (!address.matches(TBN.VALID_BALLOT_ADDRESS_PATTERN)) {
					UIHandler.showError("Invalid address!", ballotAddressTextField.getTooltip().getText());
				} else {
					String ballotType = addView.getBallotTypeComboBox().getValue();
					if (ballotType == null)
						UIHandler.showError("Choose a ballot type");
					else {
						tabView.addBallot(TBN.createBallotByType(ballotType, address, UIHandler.getElectionsDate()));
						addView.close();
					}
				}
			}
		};

		addView.getSubmitButton().setOnAction(submitButtonEventHandler);
	}
}
