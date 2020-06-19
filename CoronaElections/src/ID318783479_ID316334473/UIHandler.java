package ID318783479_ID316334473;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import ID318783479_ID316334473.Controllers.MainController;
import ID318783479_ID316334473.Models.PartyModel;
import ID318783479_ID316334473.Models.Citizens.CitizenModel;
import ID318783479_ID316334473.Models.Citizens.SoldierModel;
import ID318783479_ID316334473.Views.MainView;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// Helper class which contains all UI methods, plus extra helpful methods.
public class UIHandler {
	// Constants

	// Fields
	private static LocalDate electionsDate;
	private static MainController mainController;
	private static MainView mainView;

	private static boolean isAudioOn = true;
	private static MediaPlayer mediaPlayer;
	private static Media media;

	// Properties
	public static LocalDate getElectionsDate() {
		return electionsDate;
	}

	public static void setElectionsDate(LocalDate electionsDate) {
		UIHandler.electionsDate = electionsDate;
	}

	public static MainController getMainController() {
		return mainController;
	}

	public static void setMainController(MainController mainController) {
		UIHandler.mainController = mainController;
	}

	public static MainView getMainView() {
		return mainView;
	}

	public static void setMainView(MainView mainView) {
		UIHandler.mainView = mainView;
	}

	public static boolean isAudioOn() {
		return isAudioOn;
	}

	public static void toggleAudio() {
		mediaPlayer.setMute(isAudioOn);
		isAudioOn = !isAudioOn;
	}

	// Methods
	// First Letter in controllerName must be capital!
	public static Object getViewByName(String viewName) {
		try {
			return mainView.getClass().getDeclaredMethod(String.format("get%s", viewName)).invoke(mainView);
		} catch (Exception ex) {
			UIHandler.showError("An unexpected error occured", ex.getMessage());
		}

		return null;
	}

	public static String vote(CitizenModel voter, ObservableList<PartyModel> allParties) {
		ArrayList<String> partyNames = new ArrayList<String>();
		ChoiceDialog<String> choiceDialog;
		Optional<String> chosenParty;
		Stage stage = mainView.getStage();

		try {
			// Validations
			if ((voter.getActualAssociatedBallot().isCoronaBallot()) && (voter.isIsolated())
					&& (!voter.isWearingSuit())) {
				showWarning(String.format("Greetings, %s. You can't vote without a suit, so we have to turn you back.",
						voter.getTextualFullName()));

				return "<UNKNOWN>";
			}

			for (PartyModel partyModel : allParties)
				partyNames.add(partyModel.getTextualName());

			choiceDialog = new ChoiceDialog<String>("I don't want to vote", partyNames);
			choiceDialog.initOwner(stage);
			choiceDialog.setTitle("Vote for your chosen party");
			choiceDialog.setHeaderText(String.format("Greetings, %s. Who do you vote for", voter.getTextualFullName()));
			chosenParty = choiceDialog.showAndWait();

			return chosenParty.isPresent() ? chosenParty.get() : "<UNKNOWN>";

		} catch (IllegalStateException ex) {
			UIHandler.showError(ex.getMessage());
		} catch (Exception ex) {
			UIHandler.showError("An unexpected error occured.", ex.getMessage());
		}

		return "<UNKNOWN>";
	}

	public static void addCursorEffectsToNode(Scene scene, Node node) {
		EventHandler<MouseEvent> nodeMouseEnteredEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				scene.setCursor(Cursor.HAND);
			}
		};
		EventHandler<MouseEvent> nodeMouseExitedEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				scene.setCursor(Cursor.DEFAULT);
			}
		};

		node.setOnMouseEntered(nodeMouseEnteredEventHandler);
		node.setOnMouseExited(nodeMouseExitedEventHandler);
	}

	public static void addAudioToImageView(Scene scene, ImageView imageView, String audioFileName) {

		EventHandler<MouseEvent> imageViewMouseClickedEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				try {
					playAudio(audioFileName);
				} catch (Exception ex) {
					showError(ex.getMessage());
				}
			}
		};

		imageView.setOnMouseClicked(imageViewMouseClickedEventHandler);

		addCursorEffectsToNode(scene, imageView);
	}

	public static void playAudio(String audioFileName) {
		String path = String.format("%s\\bin\\%s", System.getProperty("user.dir"), audioFileName);

		// Validations
		if (mediaPlayer != null)
			mediaPlayer.stop();

		media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);

		if (isAudioOn)
			mediaPlayer.play();
	}

	public static ButtonType showConfirmation(String message) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

		setIcon(stage);
		alert.setTitle("Confirmation");
		alert.setHeaderText(message);
		alert.getButtonTypes().clear();
		alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
		alert.showAndWait();

		return alert.getResult();
	}

	public static void showSuccess(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

		setIcon(stage);
		alert.setTitle("Success");
		alert.setHeaderText(message);

		playAudio("Yayyy.mp3");

		alert.showAndWait();
	}

	public static void showWarning(String message) {
		Alert alert = new Alert(AlertType.WARNING);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

		setIcon(stage);
		alert.setTitle("Warning");
		alert.setHeaderText(message);

		playAudio("UhOh.mp3");

		alert.showAndWait();
	}

	public static void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

		setIcon(stage);
		alert.setTitle("Error");
		alert.setHeaderText(message);

		playAudio("Awww.mp3");

		alert.showAndWait();
	}

	public static void showError(String header, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		TextArea textArea = new TextArea(message);

		setIcon(stage);
		textArea.setEditable(false);
		alert.setTitle("Error");
		alert.setHeaderText(header);
		if (message.trim().length() != 0)
			alert.getDialogPane().setExpandableContent(new ScrollPane(textArea));

		playAudio("Awww.mp3");

		alert.showAndWait();
	}

	public static void setGeneralFeatures(Stage stage) {
		setIcon(stage);
		stage.setTitle(String.format("Corona Elections [%s %d]", electionsDate.getMonth().toString(),
				electionsDate.getYear()));
		stage.setResizable(false);
	}

	public static void setIcon(Stage stage) {
		stage.getIcons().add(UIHandler.buildImage("Elections.jpg", 0, 0).getImage());
	}

	@SuppressWarnings("unchecked")
	public static <S extends CitizenModel> TableColumn<S, ImageView> buildStatusTableColumn(double statusColumnWidth) {
		TableColumn<S, ImageView> statusTableColumn;
		TableColumn<S, ImageView> isolatedTableColumn, wearingSuitTableColumn, soldierTableColumn,
				carryingWeaponTableColumn;
		ObservableList<TableColumn<S, ?>> nestedStatusTableColumns;

		statusTableColumn = new TableColumn<S, ImageView>("Status");

		nestedStatusTableColumns = statusTableColumn.getColumns();
		isolatedTableColumn = new TableColumn<S, ImageView>("Isolated");
		isolatedTableColumn.setCellValueFactory(cell -> {
			ImageView vImageView = buildImage("V.png", 10, 10), xImageView = buildImage("X.png", 10, 10);

			return new SimpleObjectProperty<ImageView>(cell.getValue().isIsolated() ? vImageView : xImageView);
		});

		wearingSuitTableColumn = new TableColumn<S, ImageView>("Wearing Suit");
		wearingSuitTableColumn.setCellValueFactory(cell -> {
			ImageView vImageView = buildImage("V.png", 10, 10), xImageView = buildImage("X.png", 10, 10);

			return new SimpleObjectProperty<ImageView>(cell.getValue().isWearingSuit() ? vImageView : xImageView);
		});

		soldierTableColumn = new TableColumn<S, ImageView>("Soldier");
		soldierTableColumn.setCellValueFactory(cell -> {
			ImageView vImageView = buildImage("V.png", 10, 10), xImageView = buildImage("X.png", 10, 10);

			return new SimpleObjectProperty<ImageView>(cell.getValue().isSoldier() ? vImageView : xImageView);
		});

		carryingWeaponTableColumn = new TableColumn<S, ImageView>("Armed");
		carryingWeaponTableColumn.setCellValueFactory(cell -> {
			ImageView vImageView = buildImage("V.png", 10, 10), xImageView = buildImage("X.png", 10, 10);

			if (cell.getValue() instanceof SoldierModel)
				return new SimpleObjectProperty<ImageView>(
						((SoldierModel) cell.getValue()).isCarryingWeapon() ? vImageView : xImageView);

			return new SimpleObjectProperty<ImageView>(xImageView);
		});

		nestedStatusTableColumns.addAll(isolatedTableColumn, wearingSuitTableColumn, soldierTableColumn,
				carryingWeaponTableColumn);
		for (TableColumn<S, ?> tableColumn : nestedStatusTableColumns) {
			tableColumn.setEditable(false);
			tableColumn.setReorderable(false);
			tableColumn.setSortable(false);
			tableColumn.setResizable(false);
			tableColumn.setStyle("-fx-alignment: CENTER;");
			tableColumn.setMinWidth(statusColumnWidth / nestedStatusTableColumns.size());
		}

		return statusTableColumn;
	}

	public static StackPane buildBackground(Node node, double width, double height, double fontSize, boolean hasTabs) {
		ImageView backgroundImage = buildImage("IsraelFlag.PNG", width, height),
				audioImageView = buildImage("AudioOn.png", 30, 30);
		Label topLabel = new Label("מדינה אנונימית במזרח התיכון");
		Label bottomLabel = new Label("מערכת ניהול בחירות בתקופת קורונה");
		Label fileAComplaintLabel = new Label("מצאת תקלה?");
		Label audioLabel = new Label("Audio:");
		Button fileAComplaintButton = new Button("התלונן עלינו!");
		StackPane stackPane = new StackPane();

		topLabel.setFont(new Font(fontSize));
		topLabel.setTextFill(Color.WHITE);
		bottomLabel.setFont(new Font(fontSize));
		bottomLabel.setTextFill(Color.WHITE);
		fileAComplaintLabel.setFont(new Font(15));
		fileAComplaintLabel.setTextFill(Color.WHITE);
		audioLabel.setFont(new Font(15));
		audioLabel.setTextFill(Color.WHITE);
		fileAComplaintButton.setStyle("-fx-background-radius: 5em; -fx-background-color: Red;");
		fileAComplaintButton.toFront();

		stackPane.getChildren().addAll(backgroundImage, topLabel, bottomLabel, node);
		if (node instanceof TabPane) { // Visible only in Tabs
			stackPane.getChildren().addAll(fileAComplaintLabel, fileAComplaintButton, audioLabel, audioImageView);
			StackPane.setMargin(fileAComplaintLabel, new Insets(height, 10, height * 1.8, width * 0.92));
			StackPane.setMargin(fileAComplaintButton, new Insets(height, 10, height * 1.8, width * 0.8));
			StackPane.setMargin(audioLabel, new Insets(height, width * 0.95, height * 1.8, 10));
			StackPane.setMargin(audioImageView, new Insets(height, width * 0.88, height * 1.8, 10));
		}
		StackPane.setMargin(topLabel, new Insets(hasTabs ? height : height * 0.92, 0, height * 1.8, 0));
		StackPane.setMargin(bottomLabel, new Insets(hasTabs ? height * 0.95 : height * 0.92, 0, height * 0.08, 0));

		return stackPane;
	}

	public static ImageView buildImage(String imageName, double height, double width) {
		Image image = new Image(imageName, height, width, false, false);

		return new ImageView(image);
	}
}