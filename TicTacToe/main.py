# Nathan Whitehead
# Tic Tac Toe
# 27 Jan 2024


# Global Variables
gamerunning = True
counter = 1

# Main class
class game:

    # Initializer
    def __init__(self):

        # Sets up initial board state
        self.board = [["-", "-", "-"], 
                      ["-", "-", "-"], 
                      ["-", "-", "-"]]

    def __str__(self):

        # Displays board state to the screen
        b = self.getBoard()
        return f"{b[0][0]}|{b[0][1]}|{b[0][2]}\n{b[1][0]}|{b[1][1]}|{b[1][2]}\n{b[2][0]}|{b[2][1]}|{b[2][2]}"
    
    def isGameCompleted(self):

        # Checks if game has been completed, and what player won.
        b = self.getBoard()


        for row in range(len(b)):
            cols = []
            frontdiag = []
            backdiag = []

            # Adds to the list of column numbers
            for col in range(len(b)):
                cols.append(b[col][row])

                # Adds to the list of \ diagnol numbers
                backdiag.append(b[col][col])

                # Adds to the list of / diagnol numbers
                frontdiag.append(b[col][2-col])

            # Checks row, columns, / diagnol, and \ diagnol numbers for completion respectively.
            if len(set(b[row])) == 1 and "-" not in b[row]:
                return (True, b[row][0])
            elif len(set(cols)) == 1 and "-" not in cols:
                return (True, cols[0])
            elif len(set(frontdiag)) == 1 and "-" not in frontdiag:
                return (True, frontdiag[0])
            elif len(set(backdiag)) == 1 and "-" not in backdiag:
                print(backdiag)
                return (True, backdiag[0])
        
        # Checks if the board is full but there is no winner
        if not any("-" in sl for sl in b):
            return (True, "Tie")

        # Continues the game if the board is not full and there is no winner
        else:
            return (False, None)
    
    def getBoard(self):
        return self.board
    
    def setBoard(self, row, col, value):

        # Sets "x" or "o" in the desired board slot if it is not already taken
        if self.board[row][col] == "-":
            self.board[row][col] = value
            state, winner = self.isGameCompleted()
            if state:
                global gamerunning
                gamerunning = False
                print(self)
                if winner != "Tie":
                    print(f"{winner} is the winner!")
                else:
                    print(f"The game has ended in a tie.")
        else:
            print(f"That board spot its already been filled.")

            # Counter is used to switch turns but since we want the turn to stay the same if the player chooses a filled slot, 
            # we iterate the counter a second time.
            global counter
            counter *= -1



if __name__ == "__main__":
    x = game()
    print("Welcome to Tic-Tac-Toe")
    
    # Runs until game has been completed
    while gamerunning:
        # Displays the board at the start of every turn
        print(x)
        player = "x" if counter == 1 else "o"
        print(f"It is {player}'s turn\n" + "-"*50)

        # Queries the player for their move selection
        row = int(input("What row would you like to select? (1) (2) (3): ")) - 1
        col = int(input("What column would you like to select? (1) (2) (3): ")) - 1
        x.setBoard(row, col, player)
        counter *= -1
