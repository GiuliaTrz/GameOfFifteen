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

package it.GTFV.GameOfFifteen.Game;

import it.GTFV.GameOfFifteen.Models.Position;
import it.GTFV.GameOfFifteen.Models.SlidingDirection;
import it.GTFV.GameOfFifteen.Models.Tile;

import java.util.Random;


/**
 * This class represents and implements the game logic
 */
public class GameBoard {

    private final int gridSize = 4;
    private Tile[][] tiles;
    private Position emptyPosition;
    private int nCorrectCells;

    /**
     * Constructor method
     * Initializes the game board
     *
     * @param shuffleMovements represents the number of movements that happen during the shuffle
     */
    public GameBoard(int shuffleMovements) {
        if (shuffleMovements < 0) throw new IllegalArgumentException("The number of shuffle movements must be greater or equal than 0!");

        this.tiles = new Tile[gridSize][gridSize];
        initializeGame();
        shuffle(shuffleMovements);
        countCorrectCells();
    }

    /**
     * Initializes the game, setting the grid with numbered tiles and the empty position.
     * The grid get filled with numbered tiles in crescent order, starting from 1 to
     * {@code (gridSize * gridSize) - 1}. We leave the last position (down right) empty.
     *
     */
    private void initializeGame(){
        this.emptyPosition = new Position(gridSize-1,gridSize-1,gridSize);

        int cnt = 1;
        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize; j++) {
                this.tiles[i][j] = new Tile(
                        new Position(i,j,this.gridSize),
                        (cnt++)%(this.gridSize*this.gridSize)
                ); // The last position gets represented with the value 0
            }
        }
    }

    /**
     * Moves the tile adjacent to the empty position in the specified direction, updating
     * the empty position and the grid status.
     *
     *
     * @param direction The direction where we move the tile is adjacent to the empty position.
     *                  It must be one of the values of the enum  {@code SlidingDirection} (UP, DOWN, LEFT, RIGHT).
     *
     * @return {@code true} if the movement had success
     *         {@code false} if the movement is not possible
     * @throws IllegalArgumentException if direction is null
     */
    public Boolean move(SlidingDirection direction){
        if (direction == null) throw new IllegalArgumentException("Direction can't be null!");
        Position movingTile = this.emptyPosition.next(direction);

        if(movingTile == null) return false;

        set(this.emptyPosition, get(movingTile));
        set(movingTile, new Tile(movingTile, 0)); // uses 0 to represent the empty position

        this.emptyPosition = movingTile;
        countCorrectCells();
        return true;
    }

    /**
     *
     * Shuffles the tiles randomly based on the available movements
     *
     * @param movements number of wanted movements
     */
    private void shuffle(int movements){
        Random prng = new Random();
        for (int i = 0; i < movements; i++) {
            SlidingDirection[] moves = enabledMoves();
            if(moves.length > 0) {
                move(moves[prng.nextInt(moves.length)]);
            }
        }
    }

    /**
     *
     * @return true if the game is solved
     */
    public boolean isSolved() {
        return (this.nCorrectCells==(this.gridSize*this.gridSize));
    }

    /**
     * Method which sets the available movements based on the position of the empty cell
     *
     * @return array with available movements
     */
    public SlidingDirection[] enabledMoves() {
        return emptyPosition.enabledMoves();
    }

    /**
     *
     * Method which determines the available movements based on the given tile value
     *
     * @return array with available movements
     */
    public SlidingDirection[] enabledMoves(int value) {
        return get(value).getPosition().enabledMoves();
    }

    /**
     * Inserts a tile in the wanted position
     *
     * @param position wanted position
     * @param tile tile to insert
     */
    private void set(Position position, Tile tile){
        this.tiles[position.row()][position.column()] = new Tile(position, tile.getValue());
    }

    /**
     * Returns the tile in the specified position
     * @param position wanted position
     * @return tile in the wanted position
     */
    private Tile get(Position position){
        return this.tiles[position.row()][position.column()];
    }

    /**
     * Returns the tile based on its numeric value
     * @param value the tile value
     * @return the tile based on its numeric value
     */
    public Tile get(int value){
        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize; j++) {
                if(tiles[i][j].getValue() == value) return tiles[i][j];
            }
        }
        return null;
    }

    /**
     * Calculates the number of cells in the correct position
     */
    private void countCorrectCells(){
        nCorrectCells = 0;
        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize; j++) {
                if(tiles[i][j].isCorrect()) nCorrectCells++;
            }
        }
    }

    /**
     * Returns the game grid as a two-dimensional array
     * @return the game grid as a two-dimensional array
     */
    public Tile[][] getBoard(){return this.tiles;}

    /**
     * Returns the empty position
     * @return the empty position
     */
    public Position getEmptyPosition(){return this.emptyPosition;}
}
