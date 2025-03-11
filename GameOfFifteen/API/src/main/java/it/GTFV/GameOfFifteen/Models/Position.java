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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * This record class represents the position
 * of a cell inside the grid
 *
 * @param row
 * @param column
 * @param size the size of the grid
 * @author Giulia Trozzi
 * @author Francesco Valentini
 */
public record Position(int row, int column, int size) {


    /**
     *
     * Evaluates the position of a cell given their SlidingDirection (UP, DOWN, LEFT, RIGHT)
     *
     * @param direction
     * @return the position of a cell given their SlidingDirection (UP, DOWN, LEFT, RIGHT)
     * @throws IllegalArgumentException if direction is null
     */
    public Position next(SlidingDirection direction){
        if(direction == null) throw new IllegalArgumentException("Direction can't be null!");
        switch (direction){
            case UP: if(this.row < this.size-1) return new Position(row+1,column,size);
            case DOWN: if(this.row > 0) return new Position(row-1,column,size);
            case RIGHT: if(this.column < size-1) return new Position(row,column+1,size);
            case LEFT: if(this.column > 0) return new Position(row,column-1,size);
        }
        return null;
    }

    /**
     * This method returns the allowed SlidingDirections of this position
     *
     * @return the allowed SlidingDirections of this position
     */
    public SlidingDirection[] enabledMoves(){
        List<SlidingDirection> moves = new ArrayList<SlidingDirection>();
        if(this.row < this.size-1) moves.add(SlidingDirection.UP);
        if(this.row > 0) moves.add(SlidingDirection.DOWN);
        if(this.column < size-1) moves.add(SlidingDirection.RIGHT);
        if(this.column > 0) moves.add(SlidingDirection.LEFT);
        return moves.toArray(new SlidingDirection[moves.size()]);
    }

    /**
     * Determines the sliding direction from the current position to the target position.
     * The method checks if the target position is in the same row or column as the current position.
     * If the target position is in the same row, it returns either LEFT or RIGHT based on the column difference.
     * If the target position is in the same column, it returns either UP or DOWN based on the row difference.
     * If the target position is not in the same row or column, it returns null.
     *
     * @param to The target position to determine the direction to.
     * @return The sliding direction (LEFT, RIGHT, UP, DOWN) if the target is in the same row or column; otherwise, null.
     * @throws IllegalArgumentException if the target position is null
     */
    public SlidingDirection getDirection(Position to) {
        if(to == null) throw new IllegalArgumentException("Position can't be null!");
        if (this.row() == to.row()) {
            if (this.column() < to.column()) {
                return SlidingDirection.LEFT;
            } else {
                return SlidingDirection.RIGHT;
            }
        } else if (this.column() == to.column()) {
            if (this.row() < to.row()) {
                return SlidingDirection.DOWN;
            } else {
                return SlidingDirection.UP;
            }
        }
        return null;
    }

    /**
     * Checks if the current position is adjacent to the specified position.
     * Two positions are considered adjacent if they are either in the same row and their columns differ by 1,
     * or in the same column and their rows differ by 1.
     *
     * @param pos2 The position to check adjacency with.
     * @return true if the positions are adjacent; otherwise, false.
     * @throws IllegalArgumentException if the specified position is null
     */
    public boolean isAdjacent(Position pos2) {
        if(pos2 == null) throw new IllegalArgumentException("Position can't be null!");
        return (Math.abs(this.row() - pos2.row()) == 1 && this.column() == pos2.column()) ||
                (Math.abs(this.column() - pos2.column()) == 1 && this.row() == pos2.row());
    }

    @Override
    public String toString() {
        return "Position{" +
                " X=" + row +
                ", Y=" + column +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position position)) return false;
        return row() == position.row() && size() == position.size() && column() == position.column();
    }

    @Override
    public int hashCode() {
        return Objects.hash(row(), column(), size());
    }
}
