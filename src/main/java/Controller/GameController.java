package Controller;

import javafx.scene.control.Alert;
import javafx.scene.layout.Background;
import Model.Logic;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import javafx.scene.input.MouseEvent;


public class GameController {
    @FXML
    private GridPane grid;
    private Logic logic;
    private int color;
    @FXML
    private void initialize() {
        restartGame();
        populateGrid();
    }

    private void restartGame() {
        logic = new Logic();
        populateGrid();
    }

    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);

        logic.coloring(row, col);

        populateGrid();
        handleGameOver();
    }

    private void populateGrid() {
        int[][] board = logic.getBoard();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                color = board[row][col];
                var square = createSquare();
                grid.add(square, col, row);
            }
        }
    }
    private StackPane createSquare() {
        var square = new StackPane();
        square.getStyleClass().add("square");
        square.setBackground(Background.fill(getColorForPlayer(color)));
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    private Color getColorForPlayer(int color) {
        return switch (color) {
            case 1 -> Color.BLUE;
            case 2 -> Color.RED;
            case 0, default -> Color.WHITE;
        };
    }
    private void handleGameOver() {
        if (logic.getGameOver()) {
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Game Over");
            if(logic.checkDraw())
                alert.setContentText("Draw");
            else if(logic.onlyReds)
                alert.setContentText("Red won");
            else if(logic.onlyBlues){
                alert.setContentText("Blue won");
            }
            alert.showAndWait();
            restartGame();
        }
    }


}