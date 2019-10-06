import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

import javafx.geometry.Insets;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;

import java.nio.file.Paths;

import java.io.*; // pretty sure we used all of them because ide auto completed to .*

/**
 * This is the driver of the program. We generate the game here, and as the driver, handle
 * any events of the GUI here as well.
 *
 * @author Mohammad Salamat
 */
public class Main extends Application {

    private Game game1 = new Game();
    private static int fileVersion = 1;

    private Stage stage;

    public void start(Stage stage) throws Exception {

        this.stage = stage; // so we can have a reference to the stage

        Group root = new Group();
        HBox menu = new HBox();
        VBox screen = new VBox();
        Button b1 = new Button();
        Button b2 = new Button();

        b1.setId("b1");
        b2.setId("b2");

        b1.setText("Save");

        // adding the SVG as a button
        Region icon = new Region();
        b2.setGraphic(icon);

        // some spacing for the eyes
        menu.setPadding(new Insets(15, 12, 15, 12 ));
        menu.setSpacing(10);

        menu.getChildren().addAll(b1, b2);

        game1.generateBoard();

        screen.getChildren().addAll(game1.gp, menu);

        // one b1 click = one saved state
        b1.setOnMouseClicked(event -> {
            FileOutputStream fileOut;

            try {
                // probably means that it takes the . to be the root of the project
                fileOut = new FileOutputStream("./loads/load" + fileVersion + ".ser" );
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(game1);
                out.close();
                fileOut.close();
                System.out.println("Saved load" + fileVersion + ".ser" + " in /loads directory");
                fileVersion++;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Oh shet!");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Oh oh oh shet!");
            }
        });

        b2.setOnMouseClicked(event -> {
            Game loaded = deserialize(); // changed game1

            if (loaded != null) { // guarding against the user choosing against loading a game
                screen.getChildren().remove(game1.gp);
                screen.getChildren().remove(menu);

                game1 = loaded;
                loadJavaFX(game1);

                screen.getChildren().addAll(game1.gp, menu);
            }
        });

        // at this point, screen = menu + gp, and we add it to the Group root
        root.getChildren().add(screen);

        Scene scene = new Scene(root);

        // TODO IF I CHANGE THIS TO game1.gp.setOnMouseClicked IT DOESN'T WORK WHY NOT?!
        scene.setOnMouseClicked(event -> {
            if (game1 != null)
                game1.nextState();
        });

        loadCSS(scene);

        stage.setScene(scene);
        stage.show();
    }

    private void loadJavaFX(Game game) {
        game.generateBoard();
    }

    /**
     * We take the scene and have the ability to decorate it with css
     *
     * @param scene the scene we want to decorate
     */
    private void loadCSS(Scene scene) {
        String style = getClass().getResource("./style.css").toExternalForm();
        // apply stylesheet to a node
        game1.gp.getStylesheets().addAll(style);
        // apply stylesheet to the scene graph
        scene.getStylesheets().addAll(style);
    }

    /**
     * Deserializes our .ser file!
     *
     * @return the de-serialized Game, otherwise returns null IF FOR WHAT REASON!?
     */
    private Game deserialize() {

        FileChooser fc = new FileChooser();

        // make it so that when loading the game, user is in the /load directory
        String currentPath = Paths.get("./loads").toAbsolutePath().normalize().toString();
        fc.setInitialDirectory(new File(currentPath));

        // make it so that only .ser files are clickable (and visible)
        FileChooser.ExtensionFilter serializedFileFilters = new FileChooser.ExtensionFilter("Serialized Files", "*.ser");
        fc.getExtensionFilters().add(serializedFileFilters);
        File selectedFile = fc.showOpenDialog(this.stage);

        Game loadGame = null;

        if (selectedFile == null) { // TODO forgot what this was guarding against
            return null;
        }

        try {
            FileInputStream fileIn = new FileInputStream(selectedFile.getAbsolutePath());
            //FileInputStream fileIn = new FileInputStream("./loads.ser"); // doesn't work for some reason
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadGame = (Game) in.readObject();
            System.out.println("Successfully read the serialized data from " + selectedFile.getAbsolutePath());
            System.out.println("Back to turn: " + loadGame.turn); // TODO what happens if loadGame was null here? guard against it?
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadGame;
    }

    public static void main(String[] args) {

        File loadsFolder = new File("./loads");

        if( !loadsFolder.exists() ) {
            boolean wasSuccessful = loadsFolder.mkdir();

            if (!wasSuccessful) {
                System.out.println("Looks like the loads folder didn't exist, and the program tried to create one" +
                        "for you, but it didn't seem to pan out. Try to remove all the code in the main method apart from" +
                        "launch(args) and create your own /loads directory in the root of the project.");
            } else {
                System.out.println("You didn't have a /loads folder so the program made one for you.");
            }

        } else {
            System.out.println("You already have a /loads folder so the program did not make one for you.");
        }

        System.out.println("Let's begin the game.\n\n");

        // the curtains slowly open..
        launch(args);
    }
}
