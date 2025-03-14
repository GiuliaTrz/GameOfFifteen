/*
 * Copyright (c) 2025 Francesco Valentini
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package it.GTFV.GameOfFifteen.GUIApp;

import it.GTFV.GameOfFifteen.Game.GameBoard;
import it.GTFV.GameOfFifteen.Models.Position;
import it.GTFV.GameOfFifteen.Models.SlidingDirection;
import it.GTFV.GameOfFifteen.Models.Tile;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * This class is the controller for the GameView scene
 *
 * @author Giulia Trozzi
 * @author Francesco Valentini
 */
public class GameViewController {
    private GameBoard grid;
    @FXML private GridPane boardGrid; //the board that contains the tiles
    @FXML private TextField shuffleMovesField;//the text field where you put the number of moves for the shuffle
    @FXML private Label statusLabel;//it shows you when you win

    /**
     * This method initializes the window and sets its dimensions
     * @author Giulia Trozzi
     * @author Francesco Valentini
     */
    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            Stage stage = (Stage) boardGrid.getScene().getWindow();
            stage.setWidth(500);
            stage.setHeight(600);
            stage.setMinWidth(500);
            stage.setMinHeight(600);

            boardGrid.prefWidthProperty().bind(stage.widthProperty().multiply(0.8));
            boardGrid.prefHeightProperty().bind(stage.heightProperty().multiply(0.6));
        });
    }

    /**
     *
     * This method is executed when the reset button is clicked
     *
     * @author Giulia Trozzi
     * @author Francesco Valentini
     */
    @FXML
    private void resetGame() {
        int shuffleMoves = Integer.parseInt(shuffleMovesField.getText());
        grid = new GameBoard(shuffleMoves);
        updateBoard();
        statusLabel.setText("");
    }

    /**
     * This method is used to update the boardGrid
     *
     * @author Giulia Trozzi
     * @author Francesco Valentini
     */
    private void updateBoard(){
        boardGrid.getChildren().clear(); // Clear the boardGrid
        Tile[][] tiles = grid.getBoard();

        for (int row = 0; row < grid.getGridSize(); row++) {
            for (int col = 0; col < grid.getGridSize(); col++) {
                Button tile = buildTile(tiles[row][col]);
                boardGrid.add(tile,col,row);
            }
        }
    }

    /**
     * This method return a button that represent a tile
     *
     * @param t the tile
     * @return a button representing the tile
     * @author Giulia Trozzi
     * @author Francesco Valentini
     */
    private Button buildTile(Tile t){
        double tileSize = Math.min(boardGrid.getPrefWidth() / grid.getGridSize(), boardGrid.getPrefHeight() / grid.getGridSize());

        Button tileButton = new Button(t.getValue() == 0 ? "" : String.valueOf(t.getValue()));

        tileButton.setMinSize(tileSize, tileSize);
        tileButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        if (t.getValue() == 0) {
            tileButton.getStyleClass().add("empty-tile");

        } else {
            tileButton.getStyleClass().add("tile");
            tileButton.setOnAction(this::handleTileButtonClick);
        }


        return tileButton;
    }

    /**
     * This method is executed when the empty tile is clicked
     *
     * @param actionEvent
     * @author Giulia Trozzi
     * @author Francesco Valentini
     */
    private void handleTileButtonClick(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource(); //identifies the clicked button
        int tileValue = Integer.parseInt(clickedButton.getText());//tileValue is what I read, the text of the button

        Position clickedPosition = grid.get(tileValue).getPosition();//gets the position of the clicked tile
        Position emptyPosition = grid.getEmptyPosition();//gets the position of the empty tile

        if(clickedPosition.isAdjacent(emptyPosition)){
            SlidingDirection direction = clickedPosition.getDirection(emptyPosition);//determines the wanted direction of the empty tile
            if (direction != null && grid.move(direction)){
                updateBoard();
            }
        }
    }
}

