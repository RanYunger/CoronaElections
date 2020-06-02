package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.UIHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AboutTabView {
	// Constants

	// Fields
	private Group root;
	private HBox hBox;
	private VBox vBox;
	private Label projectNameLabel, madeByLabel, sponseredByLabel, dateLabel;
	private ImageView dorAlonImageView, tnuvaImageView, ramiLeviImageView;

	// Properties (Getters and Setters)

	// Constructors
	public AboutTabView() {
		root = new Group();

		buildScene();
	}

	// Methods
//	public void refresh(PartiesTabModel model) {
//		root.getChildren().clear(); // clean the previous view
//		model.show(root);
//	}
	private void buildScene() {
		hBox = new HBox();
		vBox = new VBox();
		dorAlonImageView = UIHandler.buildImage("DorAlonLogo.png", 160, 160);
		tnuvaImageView = UIHandler.buildImage("TnuvaLogo.png", 160, 160);
		ramiLeviImageView = UIHandler.buildImage("RamiLeviLogo.jpg", 160, 160);

		projectNameLabel = new Label("Stupid Ass Project V-1");
		projectNameLabel.setFont(new Font(50));
		madeByLabel = new Label("Developed by: Ran Yunger, Shy Ohev Zion");
		madeByLabel.setFont(new Font(30));
		sponseredByLabel = new Label("Sponsered by: ");
		sponseredByLabel.setFont(new Font(30));
		dateLabel = new Label("June 2020");
		dateLabel.setFont(new Font(30));

		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(dorAlonImageView, tnuvaImageView, ramiLeviImageView);
		HBox.setMargin(dorAlonImageView, new Insets(0, 0, 0, 10));
		HBox.setMargin(tnuvaImageView, new Insets(0, 10, 0, 10));
		HBox.setMargin(ramiLeviImageView, new Insets(0, 0, 0, 10));
		
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(projectNameLabel, madeByLabel, sponseredByLabel, hBox, dateLabel);
		VBox.setMargin(projectNameLabel, new Insets(0, 10, 0, 0));
		VBox.setMargin(madeByLabel, new Insets(0, 10, 0, 10));
		VBox.setMargin(dateLabel, new Insets(0, 0, 0, 10));	
	}

	public Node asNode() {
		return (Node) vBox;
	}
}
