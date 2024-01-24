/*
Dining-Philosophers Problem Simulator
Name: Ahthesham Ali Syed
Date: 12/10/2023
*/

package stars.dinningphilosophers;

import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;


public class DiningPhilosophers extends Application {

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;
    private static final int TABLE_RADIUS = 150;
    private static final int PHILOSOPHER_RADIUS = 15;
    private Label[] philosopherLabels;
    private Circle[] philosopherShapes;
    private static final int NUMBER_OF_PHILOSOPHERS = 5;
    private TextArea logArea;
    private Slider speedSlider;
    private boolean isPaused = false;

    @Override
    public void start(Stage primaryStage) {
        // Define a new height for the stage to accommodate controls and log area
        final int newHeight = WINDOW_HEIGHT + 250;

        Pane root = new Pane();
        root.setPrefSize(WINDOW_WIDTH, newHeight);

        Circle table = new Circle(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2, TABLE_RADIUS);
        table.setFill(Color.BEIGE);
        root.getChildren().add(table);

        philosopherLabels = new Label[NUMBER_OF_PHILOSOPHERS];
        philosopherShapes = new Circle[NUMBER_OF_PHILOSOPHERS];

        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
            double angle = Math.toRadians(360 / NUMBER_OF_PHILOSOPHERS * i);
            double x = WINDOW_WIDTH / 2 + TABLE_RADIUS * Math.cos(angle) - PHILOSOPHER_RADIUS;
            double y = WINDOW_HEIGHT / 2 + TABLE_RADIUS * Math.sin(angle) - PHILOSOPHER_RADIUS;

            philosopherLabels[i] = new Label("Philosopher " + (i + 1) + ": Thinking");
            philosopherLabels[i].setLayoutX(x);
            philosopherLabels[i].setLayoutY(y - PHILOSOPHER_RADIUS * 2);
            root.getChildren().add(philosopherLabels[i]);

            philosopherShapes[i] = new Circle(x, y, PHILOSOPHER_RADIUS, Color.BLUE);
            root.getChildren().add(philosopherShapes[i]);
        }

        initializePhilosophers();

        Button pauseResumeButton = new Button("Pause");
        pauseResumeButton.setOnAction(event -> {
            isPaused = !isPaused;
            pauseResumeButton.setText(isPaused ? "Resume" : "Pause");
            // Logic to pause/resume the philosophers' threads
            synchronized (DiningPhilosophers.class) {
                if (!isPaused) {
                    DiningPhilosophers.class.notifyAll();
                }
            }
        });

        speedSlider = new Slider(0.1, 2.0, 1.0);
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Logic to adjust the speed of the philosophers' actions
        });

        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefSize(WINDOW_WIDTH - 20, 200);
        logArea.setLayoutX(10); // Margin from the left
        logArea.setLayoutY(WINDOW_HEIGHT + 10); // Positioned just below the table

        HBox controls = new HBox(10, pauseResumeButton, speedSlider);
        controls.setLayoutX(10); // Align with logArea margin
        controls.setLayoutY(WINDOW_HEIGHT - 50); // Positioned just above logArea

        // Add controls above logArea
        root.getChildren().addAll(controls, logArea);

        Scene scene = new Scene(root, WINDOW_WIDTH, newHeight);
        primaryStage.setTitle("Dining Philosophers");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void initializePhilosophers() {
        Fork[] forks = new Fork[NUMBER_OF_PHILOSOPHERS];
        Philosopher[] philosophers = new Philosopher[NUMBER_OF_PHILOSOPHERS];

        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % NUMBER_OF_PHILOSOPHERS];
            philosophers[i] = new Philosopher("Philosopher " + (i + 1), leftFork, rightFork) {
                @Override
                public void run() {
                    try {
                        while (!Thread.interrupted()) {
                            synchronized (DiningPhilosophers.class) {
                                while (isPaused) {
                                    DiningPhilosophers.class.wait();
                                }
                            }
                            updateStatus(getName() + ": Thinking");
                            think();
                            synchronized (DiningPhilosophers.class) {
                                while (isPaused) {
                                    DiningPhilosophers.class.wait();
                                }
                            }
                            leftFork.pickUp();
                            rightFork.pickUp();
                            updateStatus(getName() + ": Eating");
                            eat();
                            rightFork.putDown();
                            leftFork.putDown();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            };
            philosophers[i].start();
        }
    }


    private void updateStatus(String status) {
        Platform.runLater(() -> {
            for (int i = 0; i < philosopherLabels.length; i++) {
                if (philosopherLabels[i].getText().startsWith(status.split(":")[0])) {
                    philosopherLabels[i].setText(status);
                    updateShapeColor(philosopherShapes[i], status.split(":")[1].trim(), status);
                    break;
                }
            }
            logArea.appendText(status + "\n"); // This is now in the correct place.
        });
    }
    private void updateShapeColor(Circle shape, String state, String status) {
        Color color;
        switch (state) {
            case "Thinking":
                color = Color.BLUE;
                break;
            case "Waiting":
                color = Color.YELLOW;
                break;
            case "Eating":
                color = Color.GREEN;
                break;
            default:
                color = Color.GRAY;
        }
        FillTransition ft = new FillTransition(Duration.millis(500), shape, (Color) shape.getFill(), color);
        ft.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
