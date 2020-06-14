package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.TBN;
import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Views.AddBallotView;
import ID318783479_ID316334473.Views.BallotsTabView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddBallotController {
	// Constants

	// Fields
	private AddBallotView addView;
	private BallotsTabView tabView;

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
			@SuppressWarnings("unchecked")
			public void handle(ActionEvent event) {
				String address = ((TextField) addView.getNodeByName("addressTextField")).getText();
				if (address.isEmpty()) {
					UIHandler.showError("Empty address!", "ballot address cannot be an empty string!");
				} else {
					String type = ((ComboBox<String>) addView.getNodeByName("typeComboBox")).getSelectionModel()
							.selectedItemProperty().getValue();
					// TODO: make sure type cannot be null
					tabView.addBallot(TBN.createBallotByType(type, address, UIHandler.getElectionsDate()));
					addView.close();
				}
			}
		};
		addView.addEventHandlerToButton("submitButton", submitButtonEventHandler);
	}
}
