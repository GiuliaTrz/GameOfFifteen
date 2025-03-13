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

package it.GTFV.GameOfFifteen.CLIApp;

import it.GTFV.GameOfFifteen.Game.GameBoard;
import it.GTFV.GameOfFifteen.Models.Tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    private static GameBoard gameboard;
    public static void main(String[] args) throws IOException {
       int shuffleLevel = Integer.parseInt(readString("Enter shuffle level: "));
       gameboard = new GameBoard(shuffleLevel);
        System.out.println(printBoard());
    }

    /**
     * This method reads a string
     * @param message the prompt
     * @return the input value
     * @throws IOException in case of input errors
     * @author Giulia Trozzi
     * @author Francesco Valentini
     */
    private static String readString(String message) throws IOException {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(message);
        return keyboard.readLine();
    }

    /**
     *  The method printBoard returns a string (result) that represents the game board
     * @return a string (result) that represents the game board
     *  @author Giulia Trozzi
     *  @author Francesco Valentini
     */
    private static String printBoard() {
        String result = "";
        Tile tile;
        Tile[][] tiles = gameboard.getBoard();
        for (int i = 0; i < gameboard.getGridSize(); i++) {
            for (int j = 0; j < gameboard.getGridSize(); j++) {
                tile = tiles[i][j];
                if (tile.getValue() == 0) {
                    result += "   ";
                } else {
                    result += String.format("%02d ", tile.getValue());
                }
            }
            result += "\n";
        }
        return result;
    }
}
