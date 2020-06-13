package ID318783479_ID316334473.Views;

import ID318783479_ID316334473.UIHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AboutTabView {
	// Constants

	// Fields
	private HBox hBox;
	private VBox vBox;
	private Label madeByLabel, sponseredByLabel, dateLabel;
	private ImageView dorAlonImageView, tnuvaImageView, ramiLeviImageView;

	// Properties (Getters and Setters)

	// Constructors
	public AboutTabView() {
		buildScene();
	}

	// Methods
	private void buildScene() {
		hBox = new HBox();
		vBox = new VBox();
		dorAlonImageView = UIHandler.buildImage("DorAlonLogo.png", 160, 160);
		tnuvaImageView = UIHandler.buildImage("TnuvaLogo.png", 160, 160);
		ramiLeviImageView = UIHandler.buildImage("RamiLeviLogo.png", 160, 160);

		madeByLabel = new Label("Developed by: Ran Yunger, Shy Ohev Zion");
		sponseredByLabel = new Label("Sponsered by: ");
		dateLabel = new Label("June 2020");
		
		madeByLabel.setFont(new Font(30));
		sponseredByLabel.setFont(new Font(30));
		dateLabel.setFont(new Font(30));

		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(dorAlonImageView, tnuvaImageView, ramiLeviImageView);
		HBox.setMargin(dorAlonImageView, new Insets(0, 70, 0, 0));
		HBox.setMargin(tnuvaImageView, new Insets(0, 70, 0, 70));
		HBox.setMargin(ramiLeviImageView, new Insets(0, 0, 0, 70));

		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(madeByLabel, sponseredByLabel, hBox, dateLabel);
		VBox.setMargin(madeByLabel, new Insets(2, 0, 30, 0));
		VBox.setMargin(sponseredByLabel, new Insets(30, 0, 8, 0));
		VBox.setMargin(hBox, new Insets(0, 0, 50, 0));
		VBox.setMargin(dateLabel, new Insets(30, 0, 30, 0));
	}

	public Node asNode() {
		return (Node) vBox;
	}

	public void addEffects(Stage stage) {
		UIHandler.addAudioToImageView(stage.getScene(), dorAlonImageView, "DorAlonSlogan.mp3");
		UIHandler.addAudioToImageView(stage.getScene(), tnuvaImageView, "TnuvaSlogan.mp3");
		UIHandler.addAudioToImageView(stage.getScene(), ramiLeviImageView, "RamiLeviSlogan.mp3");
	}
}
