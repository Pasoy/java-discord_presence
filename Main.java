import Profile;
import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import presentation.widgets.NumberTextField;
import presentation.widgets.PatternTextField;

public class Main extends Application implements EventHandler<ActionEvent> {

	public HBox top = new HBox();
	public VBox personRoot = new VBox();

	public MenuBar menuBar = new MenuBar();
	public Menu menuActions = new Menu("Action");
	public MenuItem menuItemUpdate = new MenuItem("Update");

	public HBox row1 = new HBox();
	public Label labelDetails = new Label("Details: ");
	public PatternTextField fieldDetails = new PatternTextField("[0-9.,/?!a-zA-Z������� ]+");
	public Label labelState = new Label("State: ");
	public PatternTextField fieldState = new PatternTextField("[0-9.,/?!a-zA-Z������� ]+");

	public HBox row2 = new HBox();
	public Label labelParty = new Label("Activated: ");
	public RadioButton radioPartyYes = new RadioButton("Yes");
	public RadioButton radioPartyNo = new RadioButton("No");
	public ToggleGroup group = new ToggleGroup();
	public Label labelPartySize = new Label("Party Size: ");
	public NumberTextField fieldPartySize = new NumberTextField();
	public Label labelPartyMax = new Label("Party Max: ");
	public NumberTextField fieldPartyMax = new NumberTextField();

	public HBox row3 = new HBox();
	public Label labelFree = new Label(" ");

	public HBox row4 = new HBox();
	public Label labelLargeImageText = new Label("Large Image Text: ");
	public PatternTextField fieldLargeImageText = new PatternTextField("[0-9.,/?!a-zA-Z������� ]+");
	public Label labelLargeImageKey = new Label("Large Image Key: ");
	public PatternTextField fieldLargeImageKey = new PatternTextField("[0-9.,/?!a-zA-Z������� ]+");

	public HBox row5 = new HBox();
	public Label labelSmallImageText = new Label("Small Image Text: ");
	public PatternTextField fieldSmallImageText = new PatternTextField("[0-9.,/?!a-zA-Z������� ]+");
	public Label labelSmallImageKey = new Label("Small Image Key: ");
	public PatternTextField fieldSmallImageKey = new PatternTextField("[0-9.,/?!a-zA-Z������� ]+");

	public HBox row6 = new HBox();

	private Profile profile = new Profile();

	private DiscordRPC lib = DiscordRPC.INSTANCE;
	private DiscordRichPresence presence = new DiscordRichPresence();
	private DiscordEventHandlers handlers = new DiscordEventHandlers();

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof MenuItem) {
			MenuItem m = (MenuItem) e.getSource();
			switch (m.getId()) {
			case "update":
				presence.details = fieldDetails.getText();
				presence.state = fieldState.getText();

				if (group.getSelectedToggle() == radioPartyYes) {
					try {
						presence.partySize = Integer.valueOf(fieldPartySize.getText());
					} catch (Exception ignored) {
					}
					try {
						presence.partyMax = Integer.valueOf(fieldPartyMax.getText());
					} catch (Exception ignored) {
					}
				}

				presence.largeImageText = fieldLargeImageText.getText();
				presence.largeImageKey = fieldLargeImageKey.getText();
				presence.smallImageText = fieldSmallImageText.getText();
				presence.smallImageKey = fieldSmallImageKey.getText();

				lib.Discord_UpdatePresence(presence);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void start(Stage primaryStage) {

		// LABELS
		labelDetails.setLabelFor(fieldDetails);
		labelState.setLabelFor(fieldState);

		labelParty.setLabelFor(radioPartyYes);
		labelPartySize.setLabelFor(fieldPartySize);
		labelPartyMax.setLabelFor(fieldPartyMax);

		labelLargeImageText.setLabelFor(fieldLargeImageText);
		labelLargeImageKey.setLabelFor(fieldLargeImageKey);

		labelSmallImageText.setLabelFor(fieldSmallImageText);
		labelSmallImageKey.setLabelFor(fieldSmallImageKey);

		menuItemUpdate.setId("update");
		menuItemUpdate.setOnAction(this);

		menuActions.getItems().addAll(menuItemUpdate);

		menuBar.getMenus().addAll(menuActions);

		// TOGGLE GROUP
		radioPartyYes.setToggleGroup(group);
		radioPartyYes.setUserData("Yes");
		radioPartyNo.setToggleGroup(group);
		radioPartyNo.setUserData("No");
		radioPartyNo.setSelected(true);
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (profile != null)
					profile.partyActivatedProperty().set((String) new_toggle.getUserData());
				if (ov == radioPartyNo) {

				}
			}
		});

		// LAYOUT
		row1.getChildren().addAll(labelDetails, fieldDetails, labelState, fieldState);
		row2.getChildren().addAll(labelParty, radioPartyYes, radioPartyNo, labelPartySize, fieldPartySize,
				labelPartyMax, fieldPartyMax);
		row3.getChildren().addAll(labelFree);
		row4.getChildren().addAll(labelLargeImageText, fieldLargeImageText, labelLargeImageKey, fieldLargeImageKey);
		row5.getChildren().addAll(labelSmallImageText, fieldSmallImageText, labelSmallImageKey, fieldSmallImageKey);
		personRoot.getChildren().addAll(menuBar, row1, row2, row3, row4, row5);

		// STYLE
		personRoot.getStyleClass().add("vbox");
		row1.getStyleClass().add("hbox");
		row2.getStyleClass().add("hbox");
		row3.getStyleClass().add("hbox");
		row4.getStyleClass().add("hbox");
		row5.getStyleClass().add("hbox");

		top.getChildren().addAll(personRoot);

		/*
		 * DISCORD API
		 */
		String applicationId = "id_here";
		String steamId = "id_here";

		handlers.ready = () -> System.out.println("Ready to work! Esskeetit!");

		lib.Discord_Initialize(applicationId, handlers, true, steamId);

		presence.details = profile.getDetails();
		presence.state = profile.getState();

		if (group.getSelectedToggle() == radioPartyYes) {
			presence.partySize = profile.getPartySize();
			presence.partyMax = profile.getPartyMax();
		}

		presence.largeImageText = profile.getLargeImageText();
		presence.largeImageKey = profile.getLargeImageKey();
		presence.smallImageText = profile.getSmallImageText();
		presence.smallImageKey = profile.getSmallImageKey();

		lib.Discord_UpdatePresence(presence);

		Thread t = new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				lib.Discord_RunCallbacks();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					lib.Discord_Shutdown();
					break;
				}
			}
		}, "RPC-Callback-Handler");
		t.start();

		Scene scene = new Scene(top);
		scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Discord Presence GUI");
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				t.interrupt();
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
