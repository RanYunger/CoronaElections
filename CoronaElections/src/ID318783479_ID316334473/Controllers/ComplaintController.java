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
				try {
					ComboBox<?> windowNameComboBox = complaintView.getWindowNameComboBox();
					TextArea descriptionTextArea = complaintView.getDescriptionTextArea();
					if (windowNameComboBox.getSelectionModel().getSelectedIndex() == -1)
						throw new Exception("Choose a window to complain about");
					if (descriptionTextArea.getText().isBlank())
						throw new Exception("No trolling around - this is our job, not yours.");

					Thread.sleep(3000);
					UIHandler.showSuccess("Your complaint has processed in our servers.\nstay tuned for updates.");
				} catch (Exception ex) {
					UIHandler.showError(ex.getMessage());
				}
			}
		};

		complaintView.getSubmitButton().setOnAction(submitButtonEventHandler);
	}
}
