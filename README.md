# **XXL** **Chess**

## **Gameplay**

XXL Chess is a Java-based 2-player chess game that utilizes the
Processing library for graphics and Gradle for dependency management. It
aims to provide a classic chess experience with an expanded board and
unique piece movements, challenging players to strategize and checkmate
their opponent's king.


## **Requirements**
- Java 8 
- Gradle

## Installation

1. **Clone the repository**:

   ```bash
   git clone <github-link>
   ```

2. **Navigate to the project directory**:

   ```bash
   cd app
   ```

3. **Build the project using Gradle**:

   ```bash
   gradle build
   ```

Requires **Java 8** and **Gradle** installed.

## Usage

1. **Run the game**:

   ```bash
   gradle run
   ```

2. **How to play**:
   * Click on a piece to select it.
   * Valid moves are highlighted in **blue**; capture moves in **light red**.
   * Click on a highlighted square to move the selected piece.
   * Click on another of your own pieces to select it instead.
   * If you click on an invalid square, the piece is deselected.

3. **Special rules**:
   * Pawns can move 2 squares on their first move.
   * Pawns promote to Queens upon reaching the 8th rank.
   * Only Knights and Camels can jump over pieces.
   * The game ends when a King is checkmated or a player runs out of time.

4. **Enjoy XXL Chess!**

## Controls

* **Select / Move Pieces**: Mouse Clicks
* **Highlights**:
  * **Blue** – valid moves
  * **Light Red** – capture moves
  * **Green** – selected piece
  * **Yellow** – last moved piece
  * **Dark Red** – king in check / checkmate


## **Board**

The game window is sized at 792x672 pixels, featuring a 14x14 grid of
tiles. In addition to the board, a sidebar displays game information
such as the current player's turn, stalemate conditions, or game over
status.

<p align="center">
  <img src="./resources/43ltynkd.png" width="300" />
</p>

The board follows a traditional checkerboard pattern with alternating
black and white tiles. Various tile highlights aid in gameplay clarity:

- Blue highlights indicate valid moves for a selected piece.
- Light red highlights show squares where a selected piece can move and capture another piece. 
- Green highlights mark the player's currently selected piece.
- Yellow highlights denote the last moved piece.
- Dark red indicates squares where a king is under threat or where checkmate has occurred.

The initial arrangement of pieces is determined by a layout file
specified in the "layout" attribute of the project's JSON configuration
file.

## **Configuration**

The game's configuration is stored in `config.json` in the project's root
directory. This file specifies the name of another file containing the
initial layout of chess pieces. Each character in the layout file
represents a specific chess piece:

| Sprite | Char | Piece        | Movement Description |
|--------|------|--------------|----------------------|
| <img src="./resources/xxhekgnt.png" style="width:0.3125in;height:0.3125in" /><img src="./resources/42tzcv5a.png" style="width:0.3125in;height:0.3125in" />| P p | Pawn | Moves forward one square; captures diagonally forward.|
| <img src="./resources/i2n452f3.png" style="width:0.3125in;height:0.3125in" /><img src="./resources/xnkznnqy.png" style="width:0.3125in;height:0.3125in" /> | R r | Rook | Moves horizontally or vertically any number of squares. |
| <img src="./resources/45kntjxg.png" style="width:0.3125in;height:0.3125in" /><img src="./resources/r4pi5ojx.png" style="width:0.3125in;height:0.3125in" /> | N n | Knight | 2 squares in one direction, then 1 square perpendicular. |
| <img src="./resources/quokj54i.png" style="width:0.3125in;height:0.3125in" /><img src="./resources/jcl5xk4l.png" style="width:0.3125in;height:0.3125in" /> | B b | Bishop | Moves diagonally any number of squares. |
| <img src="./resources/wsvgspwg.png" style="width:0.3125in;height:0.3125in" /><img src="./resources/o4pifnqw.png" style="width:0.3125in;height:0.3125in" /> | H h | Archbishop | Moves like a Knight or a Bishop. |
| <img src="./resources/3buxzlro.png" style="width:0.3125in;height:0.3125in" /><img src="./resources/yffbl443.png" style="width:0.3125in;height:0.3125in" /> | C c | Camel | 3 squares in one direction, then 1 square perpendicular. |
| <img src="./resources/fctl52nq.png" style="width:0.3125in;height:0.3125in" /><img src="./resources/eihwtcgs.png" style="width:0.3125in;height:0.3125in" /> | G g | KnightKing | Moves like a Knight or a King. |
| <img src="./resources/zxt0g00f.png" style="width:0.3125in;height:0.3125in" /><img src="./resources/2g0lalyb.png" style="width:0.3125in;height:0.3125in" /> | A a | Amazon | Moves like a Knight, Bishop, or Rook. |
| <img src="./resources/jpqdjjsd.png" style="width:0.3125in;height:0.3125in" /><img src="./resources/prafqbda.png" style="width:0.3125in;height:0.3125in" /> | K k | King | Moves one square in any direction; must avoid check. |
| <img src="./resources/3rlar2ua.png" style="width:0.3125in;height:0.3125in" /><img src="./resources/mh4p05h4.png" style="width:0.3125in;height:0.3125in" /> | E e | Chancellor | Moves like a Knight or a Rook. |
| <img src="./resources/hmw4qx3x.png" style="width:0.3125in;height:0.3125in" /><img src="./resources/5coofxva.png" style="width:0.3125in;height:0.3125in" /> | Q q | Queen | Moves like a Bishop or a Rook. |




## **Movement**

The `piece_movement_speed` property in `config.json` determines the speed in
pixels per frame at which pieces move. This speed is capped by
`max_movement_time`, a limit in seconds that ensures moves are not too
slow.

To execute a move, players first select a piece by clicking on its
current position. Subsequently, they click on the destination tile where
the piece should move. If a player clicks on one of their own pieces
after selecting a piece, the new piece becomes selected instead.
Clicking on an invalid move deselects the piece.

**Special Moves:**

- **Pawn Initial Move:** A pawn can move two squares forward from its starting position if it is located on the 2nd row from the top or bottom of the board and has not moved before.
- **Pawn** **Promotion:** When a pawn reaches the 8th rank (crossing the halfway point on the 14x14 board), it is promoted to a queen. This promotion happens immediately upon reaching the 8th rank, allowing the pawn to function as a queen for all subsequent moves.

Only a camel or knight moves may jump over pieces, and a player may not
move a piece onto a cell already containing one of their own pieces.

If a move causes the piece to enter a tile containing one of the
opponent's pieces, the opponent's piece is "captured" and removed from
the board. All pieces capture on the same tiles as their regular
movement, with the only exception being pawns which capture diagonally
forwards instead, if there is a piece there. This is the only time they
are allowed to move diagonally. If there is a piece directly in front of
a pawn, it is blocked and cannot move to the cell occupied by that
piece.

<table align="center">
  <tr>
    <td align="center">
      <img src="./resources/3gjyudbe.png" width="200" /><br><em>Amazon</em>
    </td>
    <td align="center">
      <img src="./resources/2rpihtli.png" width="200" /><br><em>Archbishop</em>
    </td>
    <td align="center">
      <img src="./resources/q4jlwsyg.png" width="200" /><br><em>Bishop</em>
    </td>
  </tr>

  <tr>
    <td align="center">
      <img src="./resources/zbnp5tcz.png" width="200" /><br><em>Camel</em>
    </td>
    <td align="center">
      <img src="./resources/klsr1gmj.png" width="200" /><br><em>Chancellor</em>
    </td>
    <td align="center">
      <img src="./resources/hu345l22.png" width="200" /><br><em>King</em>
    </td>
  </tr>

  <tr>
    <td align="center">
      <img src="./resources/yjgnhyl1.png" width="200" /><br><em>Knight</em>
    </td>
    <td align="center">
      <img src="./resources/mnrsznsc.png" width="200" /><br><em>Knight King</em>
    </td>
    <td align="center">
      <img src="./resources/z02djwz2.png" width="200" /><br><em>Pawn</em>
    </td>
  </tr>

  <tr>
    <td align="center">
      <img src="./resources/wyb44j3e.png" width="200" /><br><em>Queen</em>
    </td>
    <td align="center">
      <img src="./resources/l3wzgn4p.png" width="200" /><br><em>Rook</em>
    </td>
    <td align="center">
    </td>
  </tr>

</table>



## **Check and Checkmate:**

If after a move, a king is under attack, the king is said to be in
'check'. The player whose king is in check must do one of the following
(all must already be legal moves):

- Move their king to a safe square. 
- Move a piece to block the attack.
- Capture the attacking piece.

<p align="center">
  <img src="./resources/oyxxksmo.png" width="300" />
</p>

If none of these possibilities are available, then the player has been
checkmated - there is no move available to them that would save their
king, and they have lost. When check occurs, the king’s square is
highlighted in dark red.

A player cannot make a move that would result in their king coming under
attack. This could be any of either:

- Moving the king to a square which is under attack by the opponent.
- Moving a piece that is blocking an attack on their king by the opponent.

## **Win & Lose Conditions:**

The game ends when either one player runs out of time, or their king is
checkmated. The other player wins.

When the game ends, the board remains intact and frozen so that the
player cannot make any moves (but may restart the game with the key
press 'r').

If there are no legal moves for a player, then the game is considered a
draw and enters the end state. Display the message “Stalemate - draw”.

<p align="center">
  <img src="./resources/3gulaekl.png" width="300" />
</p>

## **Credits**

- This project was created as part of an assignment for The University of Sydney.
- All resources and assets used in this project were provided by The University of Sydney.

## **Author**
This chess game project was created by Fahim Faisal Osman.
