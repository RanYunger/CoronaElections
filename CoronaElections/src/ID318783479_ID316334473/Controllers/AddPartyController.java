package ID318783479_ID316334473.Controllers;

import java.time.LocalDate;

import ID318783479_ID316334473.TBN;
import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.PartyModel;
import ID318783479_ID316334473.Views.AddPartyView;
import ID318783479_ID316334473.Views.PartiesTabView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddPartyController {
	// Constants

	// Fields
	private AddPartyView addView;
	private PartiesTabView tabView;

	// Properties (Getters and Setters)
	public AddPartyView getAddPartyView() {
		return addView;
	}

	public void setAddPartyView(AddPartyView view) {
		this.addView = view;
	}

	public void setPartiesTabView(PartiesTabView view) {
		this.tabView = view;
	}

	// Constructors
	public AddPartyController(PartiesTabView partiesTabView, AddPartyView addPartyView) {
		setAddPartyView(addPartyView);
		setPartiesTabView(partiesTabView);
		EventHandler<ActionEvent> submitButtonEventHandler = new EventHandler<ActionEvent>() {
			@SuppressWarnings("unchecked")
			@Override
			public void handle(ActionEvent event) {
				String name = ((TextField) addView.getNodeByName("nameTextField")).getText();
				if (!name.matches(TBN.VALID_PARTY_NAME_PATTERN)) {
					UIHandler.showError("Invalid Party Name!",
							"Party name nust be at least one word in camel case (i.e. This Is An Example)");
				} else {
					String type = ((ComboBox<String>) addView.getNodeByName("wingComboBox")).getSelectionModel()
							.selectedItemProperty().getValue();

					// TODO: set default value to wing combobox as to not mess with nulls
					PartyModel.PartyAssociation wing = PartyModel.PartyAssociation.valueOf(type);
					LocalDate foundationDate = ((DatePicker) addView.getNodeByName("foundationDatePicker")).getValue();
					tabView.addParty(new PartyModel(name, wing, foundationDate));
					addView.close();
				}
			}
		};

		addPartyView.addEventHandlerToButton("submitButton", submitButtonEventHandler);
	}

	// Methods
}
