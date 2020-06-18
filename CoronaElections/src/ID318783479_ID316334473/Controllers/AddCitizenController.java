package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.SearchHandler;
import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Models.Ballots.BallotModel;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import ID318783479_ID316334473.Views.AddCitizenView;
import ID318783479_ID316334473.Views.CitizensTabView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddCitizenController {
	// Constants

	// Fields
	private CitizensTabView tabView;
	private AddCitizenView addView;
	// Properties (Getters and Setters)

	private void setAddCitizenView(AddCitizenView addView) {
		this.addView = addView;
	}

	private void setCitizensTabView(CitizensTabView tabView) {
		this.tabView = tabView;
	}

	// Constructors
	public AddCitizenController(CitizensTabView citizentabView, AddCitizenView addCitizenView) {
		setCitizensTabView(citizentabView);
		setAddCitizenView(addCitizenView);

		EventHandler<ActionEvent> yearOfBirthComboBoxEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				ComboBox<Number> citizenYearOfBirthComboBox = addView.getCitizenYearOfBirthComboBox();
				CheckBox citizenIsSoldierCheckBox = addView.getCitizenIsSoldierCheckBox(),
						citizenIsIsolatedCheckBox = addView.getCitizenIsIsolatedCheckBox(),
						citizenIsCarryingWeaponCheckBox = addView.getCitizenIsCarryingWeaponCheckBox();

				if (citizenYearOfBirthComboBox.getSelectionModel().getSelectedIndex() <= 3) { // Citizen is soldier
					citizenIsSoldierCheckBox.setSelected(true);
					citizenIsCarryingWeaponCheckBox.setDisable(false);
				} else {
					citizenIsSoldierCheckBox.setSelected(false);
					citizenIsCarryingWeaponCheckBox.setSelected(false);
					citizenIsCarryingWeaponCheckBox.setDisable(true);
				}

				addView.refreshAssociatedBallotComboBox(citizenIsIsolatedCheckBox.isSelected(),
						citizenIsSoldierCheckBox.isSelected());
			}
		};
		EventHandler<ActionEvent> isolatedCheckBoxEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				CheckBox citizenIsIsolatedCheckBox = addView.getCitizenIsIsolatedCheckBox(),
						citizenIsWearingSuitCheckBox = addView.getCitizenIsWearingSuitCheckBox(),
						citizenIsSoldierCheckBox = addView.getCitizenIsSoldierCheckBox();
				ComboBox<Number> citizenDaysOfSicknessComboBox = addView.getCitizenDaysOfSicknessComboBox();

				if (citizenIsIsolatedCheckBox.isSelected()) {
					citizenIsWearingSuitCheckBox.setDisable(false);
					citizenDaysOfSicknessComboBox.setDisable(false);
					citizenDaysOfSicknessComboBox.getSelectionModel().selectFirst();
				} else {
					citizenIsWearingSuitCheckBox.setSelected(false);
					citizenIsWearingSuitCheckBox.setDisable(true);
					citizenDaysOfSicknessComboBox.getSelectionModel().clearSelection();
					citizenDaysOfSicknessComboBox.setDisable(true);
				}

				addView.refreshAssociatedBallotComboBox(citizenIsIsolatedCheckBox.isSelected(),
						citizenIsSoldierCheckBox.isSelected());
			}
		};
		EventHandler<ActionEvent> soldierCheckBoxEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CheckBox citizenIsIsolatedCheckBox = addView.getCitizenIsIsolatedCheckBox(),
						citizenIsSoldierCheckBox = addView.getCitizenIsSoldierCheckBox();

				addView.refreshAssociatedBallotComboBox(citizenIsIsolatedCheckBox.isSelected(),
						citizenIsSoldierCheckBox.isSelected());
			}
		};
		EventHandler<ActionEvent> submitButtonEventHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				TextField citizenIDTextField = addView.getCitizenIDTextField();
				TextField citizenFullNameTextField = addView.getCitizenFullNameTextField();
				ComboBox<Number> citizenYearOfBirthComboBox = addView.getCitizenYearOfBirthComboBox(),
						citizenDaysOfSicknessComboBox = addView.getCitizenDaysOfSicknessComboBox(),
						citizenAssociatedBallotComboBox = addView.getCitizenAssociatedBallotComboBox();

				BallotModel associatedBallot;
				CitizenModel citizen;
				String IDText = citizenIDTextField.getText(), citizenName = citizenFullNameTextField.getText();
				Number yearOfBirth = citizenYearOfBirthComboBox.getSelectionModel().getSelectedItem(),
						daysOfSickness = citizenDaysOfSicknessComboBox.getSelectionModel().getSelectedItem(),
						associatedBallotID = citizenAssociatedBallotComboBox.getSelectionModel().getSelectedItem();
				boolean isIsolated = addView.getCitizenIsIsolatedCheckBox().isSelected(),
						isWearingSuit = addView.getCitizenIsWearingSuitCheckBox().isSelected(),
						isSoldier = addView.getCitizenIsSoldierCheckBox().isSelected(),
						isCarryingWeapon = addView.getCitizenIsCarryingWeaponCheckBox().isSelected();
				int citizenID;

				// Validations
				if (!IDText.matches(SearchHandler.VALID_CITIZEN_ID_PATTERN)) {
					UIHandler.showError("Invalid ID!", citizenIDTextField.getTooltip().getText());
					return;
				}
				citizenID = Integer.parseInt(IDText);
				if (SearchHandler.getCitizenByID(citizenID) != null) {
					UIHandler.showError("This citizen already exists. Try a different ID.");
					return;
				}
				if (!citizenName.matches(SearchHandler.VALID_CITIZEN_FULL_NAME_PATTERN)) {
					UIHandler.showError("Invalid name!", citizenFullNameTextField.getTooltip().getText());
					return;
				}
				if (yearOfBirth == null) {
					UIHandler.showError("Choose the citizen's year of birth.");
					return;
				}
				if (daysOfSickness == null)
					daysOfSickness = isIsolated ? citizenDaysOfSicknessComboBox.getItems().get(0) : 0;
				citizenDaysOfSicknessComboBox.getSelectionModel().select(daysOfSickness);	
					
				addView.refreshAssociatedBallotComboBox(isIsolated, isSoldier);
				if (citizenAssociatedBallotComboBox.getItems().isEmpty()) {
					UIHandler.showWarning("Make sure there's at least one ballot that matches this citizen's type!");
					return;
				}
				associatedBallotID = associatedBallotID == null ? citizenAssociatedBallotComboBox.getItems().get(0)
						: associatedBallotID;
				citizenAssociatedBallotComboBox.getSelectionModel().select(associatedBallotID);

				associatedBallot = SearchHandler.getBallotByID((int) associatedBallotID);
				citizen = SearchHandler.createCitizen((int) citizenID, citizenName, (int) yearOfBirth, (int) daysOfSickness,
						associatedBallot, isIsolated, isWearingSuit, isSoldier, isCarryingWeapon);

				tabView.addCitizen(citizen);
				UIHandler.showSuccess("A new citizen was added successfully!");

				addView.close();
			}
		};

		addView.getCitizenYearOfBirthComboBox().setOnAction(yearOfBirthComboBoxEventHandler);
		addView.getCitizenIsIsolatedCheckBox().setOnAction(isolatedCheckBoxEventHandler);
		addView.getCitizenIsSoldierCheckBox().setOnAction(soldierCheckBoxEventHandler);
		addView.getSubmitButton().setOnAction(submitButtonEventHandler);
	}

	// Methods
}
