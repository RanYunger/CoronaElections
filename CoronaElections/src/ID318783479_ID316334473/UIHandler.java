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

/*
 * Helper class which contains all UI methods, plus extra helpful
 * methods.
 * Most of the methods here will be changed when switching to GUI, so there will
 * be no need to change anything in the main method.
 */
public class UIHandler {
	// Constants

	// Fields
	private static boolean isAudioOn = true;
	private static LocalDate electionsDate;
	private static MainController mainController;
	private static MainView mainView;
	private static MediaPlayer mediaPlayer;
	private static Media media;

	// Properties
	public static LocalDate getElectionsDate() {
		return electionsDate;
	}

	public static boolean isAudioOn() {
		return isAudioOn;
	}

	public static void toggleAudio() {
		isAudioOn = !isAudioOn;
		
		// Validations
		if ((!isAudioOn) && (mediaPlayer != null))
			mediaPlayer.stop();
		else if ((isAudioOn) && (mediaPlayer != null))
			mediaPlayer.play();
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

	// Methods
	// First Letter in controllerName must be capital!
	public static Object getControllerByName(String controllerName) {
		try {
			return mainController.getClass().getMethod(String.format("get%s", controllerName)).invoke(mainController);
		} catch (Exception ex) {
			UIHandler.showError("An unexpected error occured", ex.getMessage());
		}

		return null;
	}

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
		Alert alert = new Alert(AlertType.CONFIRMATION);
		ArrayList<String> partyNames = new ArrayList<String>();
		Optional<String> chosenParty;
		Stage stage = mainView.getStage();

		try {
			// Validations
			if (voter.getActualAssociatedBallot().isCoronaBallot() && voter.isIsolated() && !voter.isWearingSuit()) {
				showWarning(String.format("Greetings, %s. You can't vote without a suit, so we have to turn you back.",
						voter.getTextualFullName()));
				
				return "<UNKNOWN>";
			}

			for (PartyModel partyModel : allParties)
				partyNames.add(partyModel.getTextualName());

			alert.initOwner(stage);
			setGeneralFeatures(stage);
			alert.getDialogPane().setExpandableContent(null);
			alert.getDialogPane().setExpanded(false);
			alert.setHeaderText(String.format("Greetings, %s. Do you want to vote?", voter.getTextualFullName()));
			alert.getButtonTypes().clear();
			alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

			alert.showAndWait();
			
			if (alert.getResult() == ButtonType.YES) {
				ChoiceDialog<String> choiceDialog = new ChoiceDialog<String>("<UNKNOWN>", partyNames);

				choiceDialog.initOwner(stage);
				choiceDialog.setHeaderText("Who do you vote for");				
				chosenParty = choiceDialog.showAndWait();

				return chosenParty.isPresent() ? chosenParty.get() : "<UNKNOWN>";
			}
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
		alert.show();

		return alert.getResult();
	}

	public static void showSuccess(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

		setIcon(stage);
		alert.setTitle("Success");
		alert.setHeaderText(message);
		
		playAudio("Yayyy.mp3");

		alert.show();
	}

	public static void showWarning(String message) {
		Alert alert = new Alert(AlertType.WARNING);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

		setIcon(stage);
		alert.setTitle("Warning");
		alert.setHeaderText(message);
		
		playAudio("UhOh.mp3");

		alert.show();
	}

	public static void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

		setIcon(stage);
		alert.setTitle("Error");
		alert.setHeaderText(message);
		
		playAudio("Awww.mp3");

		alert.show();
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

		alert.show();
	}
	
	public static void setGeneralFeatures(Stage stage) {	
		setIcon(stage);
		stage.setTitle(String.format("Corona Elections [%s %d]", electionsDate.getMonth().toString(), electionsDate.getYear()));
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

//==================== STARTING HERE ARE THE CONSOLE METHODS (TO BE DELETED LATER) ===================
//
//	public static int showMenu(boolean electionsOccurred) {
//		int selection;
//
//		try {
//			System.out.println("========== MENU ==========");
//			if (!electionsOccurred) { // only add if the elections haven't taken place
//				System.out.println("1 = Add ballot");
//				System.out.println("2 = Add citizen");
//				System.out.println("3 = Add party");
//				System.out.println("4 = Add candidate to party");
//			}
//			System.out.println("5 = Show all ballots");
//			System.out.println("6 = Show all citizens");
//			System.out.println("7 = Show all parties");
//			if (!electionsOccurred) // the elections should run precisely once
//				System.out.println("8 = Start elections");
//			else // show results only if the elections have taken place
//				System.out.println("9 = Show elections results");
//			System.out.println("10 = Exit");
//			System.out.println("========== MENU ==========");
//
//			System.out.println("Enter the code for your desired option:");
//			selection = Elections.scanner.nextInt();
//			if (electionsOccurred) { // don't allow options 1, 2, 3, 4, or 8 if the elections have taken place
//				if ((1 <= selection) && (selection <= 4))
//					throw new Exception("Updating registries is unavailable once the election already occoured.");
//				else if (selection == 8)
//					throw new Exception("Election process already occoured.");
//			} else if (selection == 9)
//				throw new Exception("Seeing the elections's results will be available once the process is complete.");
//			return selection;
//		} catch (Exception e) {
//			showExceptionMessage(e);
//		}
//
//		return -1;
//	}
//
//	// When entering 1
//	public static boolean addNewBallot(LocalDate votingDate) {
//		String address;
//		boolean validInput = false;
//
//		try {
//			// Validations
//			System.out.println("Enter ballot's address: ");
//			address = Elections.scanner.nextLine();
//			if (address.trim().length() == 0)
//				throw new Exception("BallotModel's address must contain at least 1 letter.");
//
//			while (!validInput) {
//				validInput = true;
//				System.out.println("1 = Regular BallotModel (Citizens / Candidates)");
//				System.out.println("2 = Military BallotModel (Soldiers)");
//				System.out.println("3 = Corona BallotModel (Citizens / Candidates / Soldiers)");
//				switch (Elections.scanner.nextInt()) {
//				case 1: {
//
//					if (Elections.citizenBallots
//							.add(new BallotModel<CitizenModel>("CitizenModel", address, votingDate))) {
//						System.out.println("Regular BallotModel added to registry.");
//
//						return true;
//					}
//				}
//				case 2: {
//					if (Elections.soldierBallots
//							.add(new BallotModel<SoldierModel>("SoldierModel", address, votingDate))) {
//						System.out.println("Military BallotModel added to registry.");
//
//						return true;
//					}
//				}
//				case 3:
//					validInput = false;
//					while (!validInput) {
//						validInput = true;
//						System.out.println("1 = Corona-Citizens BallotModel");
//						System.out.println("2 = Corona-Candidates BallotModel");
//						System.out.println("3 = Corona-Soldiers BallotModel");
//						switch (Elections.scanner.nextInt()) {
//						case 1: {
//							if (Elections.sickCitizenBallots
//									.add(new BallotModel<SickCitizenModel>("Sick CitizenModel", address, votingDate))) {
//								System.out.println("Corona-Citizens BallotModel added to registry.");
//
//								return true;
//							}
//						}
//						case 2: {
//							if (Elections.sickCandidateBallots.add(
//									new BallotModel<SickCandidateModel>("Sick CandidateModel", address, votingDate))) {
//								System.out.println("Corona-Candidates BallotModel added to registry.");
//
//								return true;
//							}
//						}
//						case 3: {
//							if (Elections.sickSoldierBallots
//									.add(new BallotModel<SickSoldierModel>("Sick SoldierModel", address, votingDate))) {
//								System.out.println("Corona-Soldiers BallotModel added to registry.");
//
//								return true;
//							}
//						}
//						default:
//							validInput = false;
//							System.out.println("Invalid input!");
//							break;
//						}
//					}
//					break;
//				default:
//					validInput = false;
//					System.out.println("Invalid input!");
//				}
//			}
//		} catch (Exception e) {
//			showExceptionMessage(e);
//		}
//		return false;
//	}
//
//	// When entering 2
//	public static boolean addNewCitizen(ArrayList<CitizenModel> voterRegistry, LocalDate votingDate) {
//		CitizenModel citizen;
//		Ballot associatedBallot;
//		int citizenID, yearOfBirth, daysOfSickness = 0, associatedBallotID, voterAge;
//		boolean isCarryingWeapon = false, isIsolated, isWearingSuit = false;
//		String fullName, outputMessage = "";
//
//		try {
//			// Validations
//			System.out.println("Enter voter's ID:");
//			citizenID = Elections.scanner.nextInt();
//			Elections.scanner.nextLine();
//			if (String.valueOf(citizenID).length() != 9)
//				throw new Exception("CitizenModel's ID must contain exactly 9 digits.");
//			if (Elections.getCitizenByID(voterRegistry, citizenID) != null)
//				throw new Exception("This CitizenModel is already in the registry, try something else.\n");
//
//			System.out.println("Enter year of birth:");
//			yearOfBirth = Elections.scanner.nextInt();
//			Elections.scanner.nextLine();
//			if (yearOfBirth > votingDate.getYear())
//				throw new Exception("Time paradox prevented - I mean, come on");
//			voterAge = (votingDate.getYear() - yearOfBirth);
//			if (voterAge < CitizenModel.VOTER_AGE)
//				throw new Exception("Sorry, this citizen is too young to vote, try something else.\n");
//
//			System.out.println("Enter voter's name:");
//			fullName = Elections.scanner.nextLine();
//			if (fullName.trim().length() == 0)
//				throw new Exception("CitizenModel's name must contain at least 1 letter.");
//			System.out.println("Enter associated BallotModel ID (starting 0):");
//			associatedBallotID = Elections.scanner.nextInt();
//			associatedBallot = Elections.getBallotByID(Elections.getAllBallots(), associatedBallotID);
//			if (associatedBallot == null)
//				throw new Exception(String.format("BallotModel #%d not found.", associatedBallotID));
//
//			if (associatedBallot.isCoronaBallot()) {
//				isIsolated = true;
//				System.out.println("This is a Corona BallotModel:");
//				System.out.println("Is the voter wearing a protective suit (Y/N)?");
//				isWearingSuit = getValidYesNoAnswer();
//				Elections.scanner.nextLine();
//				System.out.println("Enter amount of days the voter has been sick:");
//				daysOfSickness = Elections.scanner.nextInt();
//				Elections.scanner.nextLine();
//				if (daysOfSickness < 1)
//					throw new Exception("An Isolated CitizenModel must've been sick for at least 1 day.");
//
//				if (voterAge < CitizenModel.SOLDIER_AGE) {
//					System.out.println("This is a Military BallotModel:");
//					System.out.println("Is the soldier carrying a weapon (Y/N)?");
//					isCarryingWeapon = getValidYesNoAnswer();
//					Elections.scanner.nextLine();
//					citizen = new SickSoldierModel(citizenID, fullName, yearOfBirth, daysOfSickness, associatedBallot,
//							isIsolated, isWearingSuit, isCarryingWeapon);
//					outputMessage = String.format("Sick SoldierModel named '%s' added to BallotModel #%d.", fullName,
//							associatedBallotID);
//				} else {
//					citizen = new SickCitizenModel(citizenID, fullName, yearOfBirth, daysOfSickness, associatedBallot,
//							isIsolated, isWearingSuit);
//					outputMessage = String.format("Sick CitizenModel named '%s' added to BallotModel #%d.", fullName,
//							associatedBallotID);
//				}
//			} else {
//				isIsolated = false;
//				citizen = new CitizenModel(citizenID, fullName, yearOfBirth, daysOfSickness, associatedBallot,
//						isIsolated, isWearingSuit);
//				outputMessage = String.format("CitizenModel named '%s' added to BallotModel #%d.", fullName,
//						associatedBallotID);
//			}
//
//			System.out.println(outputMessage);
//			associatedBallot.addVoter(citizen);
//			voterRegistry.add(citizen);
//
//			return true;
//		} catch (Exception e) {
//			showExceptionMessage(e);
//		}
//		return false;
//	}
//
//	// When entering 3
//	public static boolean addNewParty(ArrayList<PartyModel> parties) {
//		String partyName;
//		PartyModel.PartyAssociation wing;
//		LocalDate foundationDate;
//
//		try {
//			// Validations
//			System.out.println("Enter party's name:");
//			partyName = Elections.scanner.nextLine();
//			if (partyName.trim().length() == 0)
//				throw new Exception("PartyModel's name must contain at least 1 letter.");
//			if (Elections.getPartyByName(parties, partyName) != null) {
//				System.out.println("This party already exists.");
//
//				return true;
//			}
//			System.out.println("Enter party's association (Left, Center, or Right):");
//			wing = PartyModel.PartyAssociation.valueOf(Elections.scanner.next());
//			Elections.scanner.nextLine();
//			System.out.println("Enter year, month and day of the party's foundation, in that order:");
//			foundationDate = LocalDate.of(Elections.scanner.nextInt(), Elections.scanner.nextInt(),
//					Elections.scanner.nextInt());
//			Elections.scanner.nextLine();
//			if (foundationDate.compareTo(LocalDate.now()) > 0)
//				throw new Exception("Time paradox prevented - I mean, come on");
//
//			if (parties.add(new PartyModel(partyName, wing, foundationDate))) {
//				System.out.println(String.format("PartyModel '%s' added to registry.", partyName));
//
//				return true;
//			}
//		} catch (Exception e) {
//			showExceptionMessage(e);
//		}
//
//		return false;
//	}
//
//	// When entering 4
//	public static boolean addCandidateToAParty(ArrayList<CitizenModel> voterRegistry, ArrayList<PartyModel> parties) {
//		CitizenModel citizen;
//		CandidateModel candidate;
//		PartyModel party;
//		int addedCandidateID;
//		String partyName;
//
//		try {
//			// Validations
//			System.out.println("Enter candidate's ID");
//			addedCandidateID = Elections.scanner.nextInt();
//			Elections.scanner.nextLine();
//			if (String.valueOf(addedCandidateID).length() != 9)
//				throw new Exception("CitizenModel's ID must contain exactly 9 digits.");
//			citizen = Elections.getCitizenByID(voterRegistry, addedCandidateID);
//			if (citizen == null)
//				throw new Exception("CandidateModel not registered, so he can't be added.");
//			if (citizen instanceof SoldierModel)
//				throw new Exception("SoldierModel cannot be a candidate in party.");
//			System.out.println("Enter party's name");
//			partyName = Elections.scanner.nextLine().trim();
//			if (partyName.trim().length() == 0)
//				throw new Exception("PartyModel's name must contain at least 1 letter.");
//			party = Elections.getPartyByName(parties, partyName);
//			if (party == null)
//				throw new Exception(String.format("There's no registered party going by the name %s.\n", partyName));
//
//			Elections.updateCitizenToCandidate(voterRegistry,
//					Elections.getCitizenByID(voterRegistry, addedCandidateID));
//			candidate = (CandidateModel) Elections.getCitizenByID(voterRegistry, addedCandidateID);
//			if (!party.addCandidate(candidate))
//				throw new Exception(String.format("Sorry, candidate %s couldn't have been added to %s party.\n",
//						candidate.getFullName(), partyName));
//			System.out.println(
//					String.format("CandidateModel %s was added to '%s' party.", candidate.getFullName(), partyName));
//
//			return true;
//		} catch (Exception e) {
//			showExceptionMessage(e);
//			return false;
//		}
//	}
//
//	// When entering 5
//	public static String showBallotRegistry(ArrayList<BallotModel<?>> ballots) {
//		if (ballots.size() == 0)
//			return "Nothing to See here..";
//
//		StringBuilder sb = new StringBuilder();
//		for (BallotModel<? extends CitizenModel> ballot : ballots)
//			sb.append("\n" + ballot.toString());
//		sb.deleteCharAt(0); // removes first /n
//
//		return sb.toString();
//	}
//
//	// When entering 6
//	public static String showVoterRegistry(ArrayList<? extends CitizenModel> voterRegistry, LocalDate votingDate) {
//		if (voterRegistry.size() == 0)
//			return "Nothing to See here..";
//
//		StringBuilder sb = new StringBuilder("Date of voting: " + votingDate + "\nVoter list:");
//		for (int i = 0; i < voterRegistry.size(); i++) {
//			voterRegistry.get(i).calculateAge(votingDate);
//			sb.append("\n\t" + voterRegistry.get(i).toString());
//		}
//
//		return sb.toString();
//	}
//
//	// When entering 7
//	public static String showPartyRegistry(ArrayList<PartyModel> parties) {
//		if (parties.size() == 0)
//			return "Nothing to See here..";
//
//		StringBuilder sb = new StringBuilder();
//		for (PartyModel party : parties)
//			sb.append("\n" + party.toString());
//		sb.deleteCharAt(0); // removes first /n
//
//		return sb.toString();
//	}
//
//	// When entering 8
//	public static void runElections(ArrayList<ArrayList<Integer>> resultsByBallot, ArrayList<PartyModel> parties) {
//		runElections(Elections.getAllBallots(), resultsByBallot, parties);
//	}
//
//	public static void runElections(ArrayList<BallotModel<? extends CitizenModel>> ballots,
//			ArrayList<ArrayList<Integer>> resultsByBallot, ArrayList<PartyModel> parties) {
//		for (BallotModel<? extends CitizenModel> ballot : ballots)
//			resultsByBallot.add(ballot.vote(parties));
//	}
//
//	public static int vote(ArrayList<PartyModel> parties, CitizenModel citizen) {
//		int voterChoice;
//		String selection;
//
//		try {
//			// Validations
//			if ((citizen.isIsolated()) && (citizen.getAssociatedBallot().isCoronaBallot())
//					&& (!citizen.isWearingSuit()))
//				throw new Exception(
//						String.format("Greetings, %s. You can't vote without a suit, so we have to turn you back.",
//								citizen.getFullName()));
//
//			System.out.println(String.format("Greetings, %s. Do you want to vote? (Y/N)", citizen.getFullName()));
//			if (getValidYesNoAnswer()) {
//				for (int i = 0; i < parties.size(); i++)
//					System.out.println(String.format("%d = %s", i + 1, parties.get(i).getName()));
//				while (true) {
//					System.out.println("Enter the code for your chosen party:");
//					voterChoice = Elections.scanner.nextInt();
//					if ((1 <= voterChoice) && (voterChoice <= parties.size())) {
//						selection = Elections.scanner.nextLine();
//
//						return voterChoice;
//					}
//					System.out.println("Invalid choice.");
//				}
//			}
//		} catch (Exception e) {
//			showExceptionMessage(e);
//		}
//		return -1;
//	}
//
//	// When entering 9
//	public static void showElectionsResults(String finalResultsString, ArrayList<ArrayList<Integer>> resultsByBallot,
//			ArrayList<PartyModel> parties) {
//		if (finalResultsString.isEmpty()) {
//			StringBuilder sb = new StringBuilder();
//			ArrayList<BallotModel<? extends CitizenModel>> allBallots = Elections.getAllBallots();
//			int ballotCount = resultsByBallot.size();
//			int partyCount = resultsByBallot.get(0).size();
//			TreeMap<String, Integer> partyVotes = new TreeMap<>();
//
//			for (PartyModel party : parties) {
//				partyVotes.put(party.getName(), 0);
//			}
//
//			for (int ballot = 0; ballot < ballotCount; ballot++) {
//				sb.append(String.format("\nBallot #%d (%.2f %% voted):\n", ballot,
//						allBallots.get(ballot).getVotersPercentage()));
//				for (int party = 0; party < partyCount; party++) {
//					String partyName = parties.get(party).getName();
//					int resultInBallot = resultsByBallot.get(ballot).get(party);
//					sb.append(String.format("\t%s: %d\t", partyName, resultInBallot));
//					partyVotes.put(partyName, partyVotes.get(partyName) + resultInBallot);
//				}
//			}
//			sb.append("\nFinal Results:");
//			for (Map.Entry<String, Integer> entry : partyVotes.entrySet())
//				sb.append(String.format("\n\t%s : %d", entry.getKey(), entry.getValue()));
//			System.out.println(sb.toString());
//			finalResultsString = sb.toString();
//		}
//		System.out.println(finalResultsString);
//	}
//
//	// When entering 10
//	public static void showExitMessage() {
//		System.out.println("Thank you for your vote (or not). See you again in 3 months!");
//	}
//
//	private static void showExceptionMessage(Exception e) {
//		System.out.println(e.getMessage());
//	}
//
//	private static boolean getValidYesNoAnswer() {
//		while (true) {
//			String answer = Elections.scanner.nextLine();
//			if (answer.equalsIgnoreCase("Y"))
//				return true;
//
//			if (answer.equalsIgnoreCase("N"))
//				return false;
//
//			System.out.println("please enter a valid answer (Y/N)");
//		}
//	}
}