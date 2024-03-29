package ID318783479_ID316334473;

import ID318783479_ID316334473.Controllers.ElectionsDateController;
import ID318783479_ID316334473.Views.ElectionsDateView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {

	// Methods
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	@SuppressWarnings("unused")
	public void start(Stage primaryStage) throws Exception {
		ElectionsDateView view = new ElectionsDateView(primaryStage);
		ElectionsDateController controller = new ElectionsDateController(view);
	}
}
