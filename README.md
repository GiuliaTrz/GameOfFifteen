# Game Of Fifteen

The **Game Of Fifteen** is a classic numerical puzzle consisting of a 4x4 grid containing 15 numbered tiles from 1 to 15 and one empty square. The goal of the game is to arrange the tiles in ascending order by moving adjacent tiles into the empty space. The game is also known as the **15 Puzzle**.

## Game Rules

1. **Initial Setup**: The tiles are randomly arranged in the 4x4 grid, with one empty square.
   - By varying the number of moves made by the shuffle, it is possible to increase or decrease the complexity of the puzzle.
2. **Allowed Moves**: You can move a tile adjacent to the empty square (up, down, left, or right) into the empty position.
3. **Winning**: Rearrange the tiles in ascending order, with the empty square in the bottom-right corner.

## Screenshots
![mainScreen](https://github.com/user-attachments/assets/f901fcd8-cdf0-4619-b857-44b2106fb400)
![mainScreen-Victory](https://github.com/user-attachments/assets/75432907-b4d6-4d5b-8e67-aa66300cb960)

## How to play
1) Download the latest version from the [Releases](https://github.com/GiuliaTrz/GameOfFifteen/releases) and unzip it
2) Run the batch file (`GUIApp.bat` on Windows) or the bash file (`GUIApp` on Unix Systems) in the `bin/` folder

## Compile from sources
#### 1) Clone the repository and navigate to the project directory
```bash
git clone https://github.com/GiuliaTrz/GameOfFifteen.git && cd GameOfFifteen/GameOfFifteen
```

#### 2) Build and run the project
```bash
# Run the GUI version
./gradlew :GUIApp:run

# Run the CLI version
./gradlew :app:run
```

## Developers
- [Giulia Trozzi](https://github.com/GiuliaTrz)
- [Francesco Valentini](https://github.com/FrancescoValentini)

## Technologies
- Java 21
- JavaFX
- Gradle

