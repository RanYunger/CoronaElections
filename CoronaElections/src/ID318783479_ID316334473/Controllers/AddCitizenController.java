package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.TBN;
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
			@SuppressWarnings("unchecked")
			public void handle(ActionEvent event) {

				ComboBox<Integer> yearOfBirthComboBox = (ComboBox<Integer>) addView
						.getNodeByName("yearOfBirthComboBox");
				CheckBox soldierCheckBox = (CheckBox) addView.getNodeByName("soldierCheckBox"),
						isolatedCheckBox = (CheckBox) addView.getNodeByName("isolatedCheckBox"),
						carryingWeaponCheckBox = (CheckBox) addView.getNodeByName("carryingWeaponCheckBox");

				if (yearOfBirthComboBox.getSelectionModel().getSelectedIndex() <= 3) { // Citizen is soldier
					soldierCheckBox.setSelected(true);
					carryingWeaponCheckBox.setDisable(false);
				} else {
					soldierCheckBox.setSelected(false);
					carryingWeaponCheckBox.setSelected(false);
					carryingWeaponCheckBox.setDisable(true);
				}

				addView.refreshAssociatedBallotComboBox(isolatedCheckBox.isSelected(), soldierCheckBox.isSelected());
			}
		};
		EventHandler<ActionEvent> isolatedCheckBoxEventHandler = new EventHandler<ActionEvent>() {
			@Override
			@SuppressWarnings("unchecked")
			public void handle(ActionEvent event) {

				CheckBox isolatedCheckBox = (CheckBox) addView.getNodeByName("isolatedCheckBox");
				CheckBox wearingSuitCheckBox = (CheckBox) addView.getNodeByName("wearingSuitCheckBox");
				CheckBox soldierCheckBox = (CheckBox) addView.getNodeByName("soldierCheckBox");
				ComboBox<Integer> daysOfSicknessComboBox = (ComboBox<Integer>) addView
						.getNodeByName("daysOfSicknessComboBox");

				if (isolatedCheckBox.isSelected()) {
					wearingSuitCheckBox.setDisable(false);
					daysOfSicknessComboBox.setDisable(false);
					daysOfSicknessComboBox.getSelectionModel().selectFirst();
				} else {
					wearingSuitCheckBox.setSelected(false);
					wearingSuitCheckBox.setDisable(true);
					daysOfSicknessComboBox.getSelectionModel().clearSelection();
					daysOfSicknessComboBox.setDisable(true);
				}

				addView.refreshAssociatedBallotComboBox(isolatedCheckBox.isSelected(), soldierCheckBox.isSelected());
			}
		};
		EventHandler<ActionEvent> soldierCheckBoxEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CheckBox isolatedCheckBox = (CheckBox) addView.getNodeByName("isolatedCheckBox");
				CheckBox soldierCheckBox = (CheckBox) addView.getNodeByName("soldierCheckBox");

				addView.refreshAssociatedBallotComboBox(isolatedCheckBox.isSelected(), soldierCheckBox.isSelected());
			}
		};
		EventHandler<ActionEvent> submitButtonEventHandler = new EventHandler<ActionEvent>() {

			@Override
			@SuppressWarnings("unchecked")
			public void handle(ActionEvent event) {
				String IDText = ((TextField) addView.getNodeByName("IDTextField")).getText();
				if (!IDText.matches(TBN.VALID_CITIZEN_ID_PATTERN))
					UIHandler.showError("Invalid ID", "ID must be 9 digits long");
				else {
					int ID = Integer.parseInt(IDText);
					String fullName = ((TextField) addView.getNodeByName("nameTextField")).getText();
					if (!fullName.matches(TBN.VALID_CITIZEN_FULL_NAME_PATTERN))
						UIHandler.showError("Invalid citizen name",
								"Name must be comprised of two words, each begins with a capital letter (i.e. John Doe)");
					else {
						boolean isIsolated = ((CheckBox) addView.getNodeByName("isolatedCheckBox")).isSelected(),
								isWearingSuit = ((CheckBox) addView.getNodeByName("wearingSuitCheckBox")).isSelected(),
								isSoldier = ((CheckBox) addView.getNodeByName("soldierCheckBox")).isSelected(),
								isCarryingWeapon = ((CheckBox) addView.getNodeByName("carryingWeaponCheckBox"))
										.isSelected();
						Integer daysOfSickness = ((ComboBox<Integer>) addView.getNodeByName("daysOfSicknessComboBox"))
								.getValue(),
								yearOfBirth = ((ComboBox<Integer>) addView.getNodeByName("yearOfBirthComboBox"))
										.getValue(),
								associatedBallotID = ((ComboBox<Integer>) addView
										.getNodeByName("associatedBallotComboBox")).getValue();
						BallotModel BModel = TBN.getBallotByID(associatedBallotID);
						// TODO: fix this shit
						if (daysOfSickness == null)
							daysOfSickness = 0;
						CitizenModel citizen = TBN.createCitizen(ID, fullName, yearOfBirth, daysOfSickness, BModel,
								isIsolated, isWearingSuit, isSoldier, isCarryingWeapon);
						System.out.println(citizen);
						tabView.addCitizen(citizen);
						addView.close();
					}
				}
			}
		};

		addView.addEventHandlerToComboBox("yearOfBirthComboBox", yearOfBirthComboBoxEventHandler);
		addView.addEventHandlerToCheckBox("isolatedCheckBox", isolatedCheckBoxEventHandler);
		addView.addEventHandlerToCheckBox("soldierCheckBox", soldierCheckBoxEventHandler);
		addView.addEventHandlerToButton("submitButton", submitButtonEventHandler);
	}

	// Methods

}
