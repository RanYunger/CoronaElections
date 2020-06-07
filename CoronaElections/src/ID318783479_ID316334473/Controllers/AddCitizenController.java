package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.Models.AddCitizenModel;
import ID318783479_ID316334473.Views.AddCitizenView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class AddCitizenController {
	// Constants

	// Fields
	private AddCitizenModel addCitizenModel;
	private AddCitizenView addCitizenView;

	// Properties (Getters and Setters)
	public AddCitizenModel getAddCitizenModel() {
		return addCitizenModel;
	}

	public void setAddCitizenModel(AddCitizenModel addCitizenModel) {
		this.addCitizenModel = addCitizenModel;
	}

	public AddCitizenView getAddCitizenView() {
		return addCitizenView;
	}

	public void setAddCitizenView(AddCitizenView addCitizenView) {
		this.addCitizenView = addCitizenView;
	}

	// Constructors
	public AddCitizenController(AddCitizenModel model, AddCitizenView view) {
		setAddCitizenModel(model);
		setAddCitizenView(view);

		addCitizenView.refresh(addCitizenModel);

		EventHandler<ActionEvent> yearOfBirthComboBoxEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ComboBox<Integer> yearOfBirthComboBox = (ComboBox<Integer>) addCitizenView
						.getNodeByName("yearOfBirthComboBox");
				CheckBox soldierCheckBox = (CheckBox) addCitizenView.getNodeByName("soldierCheckBox"),
						carryingWeaponCheckBox = (CheckBox) addCitizenView.getNodeByName("carryingWeaponCheckBox");

				if (yearOfBirthComboBox.getSelectionModel().getSelectedIndex() <= 3) { // Citizen is soldier
					soldierCheckBox.setSelected(true);
					carryingWeaponCheckBox.setDisable(false);
				} else {
					soldierCheckBox.setSelected(false);
					carryingWeaponCheckBox.setDisable(true);
				}
			}
		};
		EventHandler<ActionEvent> isolatedCheckBoxEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CheckBox isolatedCheckBox = (CheckBox) addCitizenView.getNodeByName("isolatedCheckBox");
				CheckBox wearingSuitCheckBox = (CheckBox) addCitizenView.getNodeByName("wearingSuitCheckBox");
				CheckBox soldierCheckBox = (CheckBox) addCitizenView.getNodeByName("soldierCheckBox");
				ComboBox<Integer> daysOfSicknessComboBox = (ComboBox<Integer>) addCitizenView.getNodeByName("daysOfSicknessComboBox");

				if (isolatedCheckBox.isSelected()) {
					wearingSuitCheckBox.setDisable(false);
					daysOfSicknessComboBox.setDisable(false);
					view.refreshAssociatedBallotComboBox(true, soldierCheckBox.isSelected());
				}
				else {
					wearingSuitCheckBox.setDisable(true);
					daysOfSicknessComboBox.setDisable(true);
					daysOfSicknessComboBox.getSelectionModel().clearSelection();
				}
			}
		};
		EventHandler<ActionEvent> soldierCheckBoxEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CheckBox isolatedCheckBox = (CheckBox) addCitizenView.getNodeByName("isolatedCheckBox");
				CheckBox soldierCheckBox = (CheckBox) addCitizenView.getNodeByName("soldierCheckBox");

				if (soldierCheckBox.isSelected())
					view.refreshAssociatedBallotComboBox(isolatedCheckBox.isSelected(), true);
			}
		};
		EventHandler<ActionEvent> submitButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO: COMPLETE
			}
		};

		view.addEventHandlerToComboBox("yearOfBirthComboBox", yearOfBirthComboBoxEventHandler);
		view.addEventHandlerToCheckBox("isolatedCheckBox", isolatedCheckBoxEventHandler);
		view.addEventHandlerToCheckBox("soldierCheckBox", soldierCheckBoxEventHandler);
		view.addEventHandlerToButton("submitButton", submitButtonEventHandler);
	}

	// Methods
}
