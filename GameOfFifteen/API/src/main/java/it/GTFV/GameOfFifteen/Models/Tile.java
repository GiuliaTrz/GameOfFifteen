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

package it.GTFV.GameOfFifteen.Models;

/**
 * This class represent a tile in the grid
 * @author Giulia Trozzi
 * @author Francesco Valentini
 */
public class Tile {
    private Position position;
    private int value;

    /**
     * Tile constructor
     * @param position The position of the tile in the grid
     * @param value the value associated with this tile
     */
    public Tile(Position position, int value) {
        if(position == null) throw new IllegalArgumentException("Position can't be null");
        if(value < 0 || value > 15) throw new IllegalArgumentException("Tile value must be in the 0-15 range");

        this.position = position;
        this.value = value;
    }

    /**
     * Returns true if the tile is in the right/expected position
     *
     * @return true if the tile is in the right/expected position
     */
    public boolean isCorrect(){
        return correctPosition().equals(this.position) ? true : false;
    }

    /**
     *
     * Returns the correct/expected position given the tile value
     *
     * @return the correct/expected position given the tile value
     */
    public Position correctPosition(){
        int tileValue = this.value;

        // empty cell case
        if(tileValue == 0) return new Position(this.position.size()-1, this.position.size()-1, this.position.size());

        int x = (tileValue - 1) % this.position.size();
        int y = (tileValue - 1) / this.position.size();
        return new Position(y,x,this.position.size());
    }

    @Override
    public String toString() {
        return "Tile{" +
                "position=" + this.position +
                ", value=" + this.value +
                ", Expected="+this.correctPosition()+
                ", Valid="+ this.isCorrect()+
                '}';
    }
}
