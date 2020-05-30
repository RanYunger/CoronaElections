package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.Models.MainModel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainView {
	// Constants

	// Fields
	private Group root;
	private TabPane tabPane;

	// Properties (Getters and Setters)

	// Constructors
	public MainView(Stage stage) {
		root = new Group();

		buildScene(stage);
	}

	// Methods
	public void refresh(MainModel model) {
		root.getChildren().clear(); // clean the previous view
		model.show(root);
	}

	private void buildScene(Stage stage) {
		String[] tabNames = new String[] {"Main", "Ballots", "Citizens", "Parties"};
		Node[] tabContents = buildTabContents();
		Tab currTab;
		
		tabPane = new TabPane();
		for (int i = 0; i < tabNames.length; i++) {
			currTab = new Tab(tabNames[i]);
			currTab.setContent(tabContents[i]);
			tabPane.getTabs().add(currTab);
		}
		
		stage.setTitle("Corona Elections");
		stage.setResizable(false);
		// TODO: Set icon
		stage.setScene(new Scene(tabPane, 1500, 900));
		stage.show();
	}

	private Node[] buildTabContents() {
		Node[] tabContents = new Node[4];
		
		// TODO: COMPLETE (design each tab)
		
		return tabContents;
	}
}
