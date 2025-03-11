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
 * SlidingDirection is an enum which contains the possible sliding positions.
 * @author Giulia Trozzi
 * @author Francesco Valentini
 */
public enum SlidingDirection {
    UP, DOWN, LEFT, RIGHT;

    /**
     * SlidingDirection is a method which returns the direction specified by the input character.
     * The possible directions are:
     * - 'U' -> UP;
     * - 'D' -> DOWN;
     * - 'L' -> LEFT;
     * - 'R' -> RIGHT;
     *
     * @param direction is a char that represents the direction
     * @return the direction specified by the input character
     */
    public static SlidingDirection fromChar(char direction) {
        direction = Character.toUpperCase(direction);
        return switch (direction) {
            case 'U' -> UP;
            case 'D' -> DOWN;
            case 'L' -> LEFT;
            case 'R' -> RIGHT;
            default -> throw new IllegalArgumentException("Invalid character");
        };
    }
}

