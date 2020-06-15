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
			@Override
			public void handle(ActionEvent event) {
				TextField partyNameTextField = addView.getPartyNameTextField();

				String name = partyNameTextField.getText();
				if (!name.matches(TBN.VALID_PARTY_NAME_PATTERN)) {
					UIHandler.showError("Invalid Party Name!", partyNameTextField.getTooltip().getText());
				} else {
					ComboBox<String> partyWingComboBox = addView.getPartyWingComboBox();
					DatePicker partyFoundationDatePicker = addView.getPartyFoundationDatePicker();
					String partyWing = partyWingComboBox.getSelectionModel().selectedItemProperty().getValue();
					LocalDate foundationDate = partyFoundationDatePicker.getValue();

					partyWing = partyWing == null ? partyWingComboBox.getItems().get(0) : partyWing;
					foundationDate = foundationDate == null ? LocalDate.now() : foundationDate;

					// TODO: set default value to wing combobox as to not mess with nulls
					tabView.addParty(
							new PartyModel(name, PartyModel.PartyAssociation.valueOf(partyWing), foundationDate));
					addView.close();
				}
			}
		};

		addPartyView.getSubmitButton().setOnAction(submitButtonEventHandler);
	}

	// Methods
}
