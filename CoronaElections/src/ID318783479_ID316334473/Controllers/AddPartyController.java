package ID318783479_ID316334473.Controllers;

import java.time.LocalDate;

import ID318783479_ID316334473.SearchHandler;
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
			@Override
			public void handle(ActionEvent event) {
				TextField partyNameTextField = addView.getPartyNameTextField();
				ComboBox<String> partyWingComboBox = addView.getPartyWingComboBox();
				DatePicker partyFoundationDatePicker = addView.getPartyFoundationDatePicker();
				String partyName = partyNameTextField.getText(),
						partyWing = partyWingComboBox.getSelectionModel().selectedItemProperty().getValue();
				LocalDate foundationDate = partyFoundationDatePicker.getValue();

				// Validations
				if (!partyName.matches(SearchHandler.VALID_PARTY_NAME_PATTERN)) {
					UIHandler.showError("Invalid name!", partyNameTextField.getTooltip().getText());
					return;
				}
				if (SearchHandler.getPartyByName(partyName) != null) {
					UIHandler.showError("This name already taken. Try a different name.");
					return;
				}

				partyWing = partyWing == null ? partyWingComboBox.getItems().get(0) : partyWing;
				foundationDate = foundationDate == null ? UIHandler.getElectionsDate() : foundationDate;

				tabView.addParty(
						new PartyModel(partyName, PartyModel.PartyAssociation.valueOf(partyWing), foundationDate));
				UIHandler.showSuccess("A new party was added successfully!");

				addView.close();
			}
		};

		addPartyView.getSubmitButton().setOnAction(submitButtonEventHandler);
	}

// Methods
}
