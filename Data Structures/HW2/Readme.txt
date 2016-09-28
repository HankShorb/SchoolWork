_____________________________________________________________________________
---------------------------------HW 2 README---------------------------------
_____________________________________________________________________________
Hank Shorb (Robert Shorb rhs2131)

----------- MAZESHELL -----------
_________________________________
This program runs as specified in the problem set prompt. Pressing the Draw 
Initial Grid button will run the initialize() method which creates the maze 
in the GUI and creates a matrix representation of the maze in the program,
storing the locations of both the start and end of the maze in MazePlace
objects.
The Calculate Distances button runs a breadth first search that finds the 
shortest distance of each reachable point in the maze of distance from start 
<= the of the end distance from start (though it does not necessarily do this 
for every place in the maze which has equal distance from start in comparison 
to the end).

       ***  SHOW PATH  ***
There are two show path functions that I have written which are easily 
exchangable by commenting out and removing comment setters in the code, which
I have marked.  The method that is currently running in the code highlights 
every square which is part of any of the shortest available paths to the end 
of the maze (taking into accoun that multiple paths may beof the same length).
The currently inactive method highlights just one specific path.


Takes command line arguments, i.e. if I would like to run this program on a 
maze stored in maze1.data stored in the same directory, I run

  java MainMethod maze1.data



----------- POLYTOOL -----------
________________________________
PolyTool is fairly straight forward. The PolyTool Class is essentially a 
calculator that returns SimpleLinkedLists which are polynomials.
PolyToolInterface is code that accepts inputs from the user and handles all 
errors that could occur.  It has been built to be offensive.

Instructions:
Main menu, simply enter the letter of the command you wish to perform

input() "i"
  Will as you for the index in which you want to place a polynomial and the
  number of terms that polynomial will have.  Overwrites any pre-existing 
  polynomial from that index. Takes parameters in the form:
  "0 3" 
        (ignoring the "") where 0 is the index in which a polynomial will be
        stored and 3 is the number of terms that polynomial will have

  A polynomial input with 0 terms will be stored as 0.0 x^0

add() "a"
  Adds polynomials from two indexes (Can be the same index), and stores them 
  in a third index (may also be the same index, and will overwrite original 
  polynomial). Takes parameters in the form:
  "0 1 2"
          Where 0 is the index of the first polynomial, 1 of the second and 3
          the index where the sum will be stored

subtract() "s"
  Works just like add. The first index being P1 and the second being P2 this 
  calculates P1-P2 and stores it in the third index

multiply() "m"
  Works like add and subtract.

divide() "d"
  Works similarly. The first index being P1 the second P2 this calculates P1/P2 
  and stores the quotient in the third index and the remainder in the fourth

evaluate() "e"
  Takes two inputs the first being an int the second being a double:
  "0 3.7"
          Evaluates and displays the value of the polynomial stored in index 0
          evaluated at x=3.7

print() "p"
  prints the polynomial stored in the specified index. Enter an integer.

upSize() "u"
  polyArray starts with only ten possible entries for storage. This command
  increments the size by ten to allow for more polynomials to be stored.

check() "c"
  Displays both the number of indices available for storage of polynomials and
  which of these indices currently contain polynomials.

quit() "q"
  Terminates the program.