package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Views.ComplaintView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class ComplaintController {
	// Constants

	// Fields
	private ComplaintView complaintView;

	// Properties (Getters and Setters)

	public ComplaintView getComplaintView() {
		return complaintView;
	}

	public void setComplaintView(ComplaintView ComplaintView) {
		this.complaintView = ComplaintView;
	}

	// Constructors
	public ComplaintController(ComplaintView view) {
		setComplaintView(view);

		EventHandler<ActionEvent> submitButtonEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ComboBox<String> windowNameComboBox = complaintView.getWindowNameComboBox();
				TextArea descriptionTextArea = complaintView.getDescriptionTextArea();
				String selectedViewName;
				int selectedViewIndex;

				try {
					// Validations
					selectedViewIndex = windowNameComboBox.getSelectionModel().getSelectedIndex();
					if (selectedViewIndex == -1)
						throw new Exception("Select a view to complain about.");
					selectedViewName = windowNameComboBox.getSelectionModel().getSelectedItem();

					if (descriptionTextArea.getText().trim().length() == 0)
						throw new Exception("Tell us what you'd like us to fix.");

					Thread.sleep(3000);
					if (!UIHandler.getViewDamageStatuses().get(selectedViewName))
						UIHandler.getViewDamageStatuses().replace(selectedViewName, true);
					UIHandler.showSuccess("Your complaint has processed in our servers.\nstay tuned for updates.");
					view.close();
				} catch (Exception ex) {
					UIHandler.showError(ex.getMessage());
				}
			}
		};

		complaintView.getSubmitButton().setOnAction(submitButtonEventHandler);
	}

	// Methods
	public boolean isFormValid() {

		try {

			return true;
		} catch (Exception ex) {
			UIHandler.showError("Please make sure to fill all fields");
		}

		return false;
	}
}
