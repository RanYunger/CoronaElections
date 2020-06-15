package ID318783479_ID316334473.Controllers;

import ID318783479_ID316334473.UIHandler;
import ID318783479_ID316334473.Views.ComplaintView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
				UIHandler.showSuccess("Your complaint has processed in our servers.\nstay tuned for updates.");
//				if (isFormValid()) {
//					Thread.currentThread().sleep(3000);
//				}
			}
		};

		complaintView.getSubmitButton().setOnAction(submitButtonEventHandler);
	}

	// Methods
	public boolean isFormValid() {
		ComboBox<?> windowNameComboBox = complaintView.getWindowNameComboBox();
		TextField controlNameTextField = complaintView.getControlNameTextField();
		TextArea descriptionTextArea = complaintView.getDescriptionTextArea();

		try {
			// Validations
			if (windowNameComboBox.getSelectionModel().getSelectedIndex() == -1)
				throw new Exception();
			if ((controlNameTextField.getText().trim().length() == 0)
					|| (descriptionTextArea.getText().trim().length() == 0))
				throw new Exception();

			return true;
		} catch (Exception ex) {
			UIHandler.showError("Please make sure to fill all fields");
		}

		return false;
	}
}
