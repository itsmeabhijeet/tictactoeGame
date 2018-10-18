# tictactoeGame
tictactoe 2.0 game Solution

Problem Statement :

TIC TAC TOE 2.0
We want to bring the pen-and-paper game Tic-tac-toe to the digital age, but
with a little twist: the size of the playfield should be configurable
between 3x3 and 10x10. And we also want the symbols (usually O and X) to be
configurable. Also it should be for 3 players instead of just 2. A player
can win the game by filling in a whole row, column or diagonal. If the
playfiled is 5x5 - then the player must fill all the 5 cells in a row,
column or diagonal to win.
General Rules: https://en.wikipedia.org/wiki/Tic-tac-toe
The three players play all together against each other. One of the players
is an AI. Who is starting is random. In and output should be on the
console. Input of the AI is automatic, no user action should be required.
After each move, the new state of the playfield is displayed and the player
can enter the next position of their character one after another. The next
position should be provided in a format like 3,2. Invalid inputs are
expected to be handled appropriately.
Requirements: - Do not use external frameworks and libraries, i.e. Spring, Django, React
JS, Vue JS, Angular JS, etc.
- You can use libraries only for testing or building purposes: e.g. JUnit,
Gradle, Rspec, Rake, GulpJS, etc.
- Software design is more important than a highly developed AI
- Use the programming language you feel most comfortable with.
- Configuration:
• Size of the playground - always a square. Valid values are between 3
and 10.
• Play character 1, 2 and 3: A single character for the first human
player, A single character for the second human player - A single
character for the computer.
• These configurations should come from a file
Rules:
- Please provide an explanation how to run your code
- Please explain your design decisions and assumptions
- Don't include executables* in your submission.
- Please write your solution in a way, that you would feel comfortable
handing this over to a colleague and deploy it into production.
- We especially look at design aspects (e.g. OOP) and if the code is well
tested and understandable.
* this includes: asp, bat, class, cmd, com, cpl, dll, exe, fon, hta, ini,
ins, iw, jar, jsp, js, jse, pif, scr, shs, sh, vb, vbe, vbs, ws, wsc, wsf,
wsh & msi
------------------------------------------------------------------------------------------------------------------------------------
Steps to run this project:

Pre-requisites:
1. Java version : 1.8.0_121
2. Gradle version: 4.10.2

3.Download this zip and unzip at a location and navigate to root path.
  Run with already defined installed Gradle
  
4.Just enter gradle run in your terminal from the project root.

5.Run with gradle shell or bat (preconfigured gradlewrapper)
Just enter gradlew run or gradlew.bat run in your terminal from the project root.

6. Also if using any IDE like Eclipse : Import the project as gradle project and then do gradle build first and then simply 
execute the main method as Run as Java application. 
--------------------------------------------------------------------------------------------------------------------------------------
Design Description


PlayMe: This is the main class responsible to start the Game (Entry Point). This class actually reads from the given property file and passes those config values to subsequent classes responsible to process the values. The resource file is present inside the resource folder which can be changed before one starts the game.

IBoardConsoleSystem : This interface is used for communication within the program. Creation of board structure or printing any info or
error message are the task defined within this interface. This is put into an interface because, for now the message is passed to console but lets say tomorrow one wants to print that in UI, a client can implement this interface and can implement in its own way.

TicTacToeConsoleSystem : This is the implementation class of IBoardConsoleSystem. Since the objective now is to print everything in console, it uses System.info, System.err and System.out to do the job.

IConfigurationReader : This interface is used to read configuration like size of board and number of players and their symbols.
This is created as interface as different class can implement it based on the way configuration is passed like property file
or gradle or yml file or may be REST call.
 
PropertyFileConfigReader :This class is the IConfigurationReader implementation using config.property file kept under resources folder.

Player  : This is an abstract class as certain things like the character associated with every player and the behavior or action 
like make move is common between a human player or computer player.


HumanPlayer : This class depicts a human player move. 
 
ComputerPlayer : This class depicts a compute class. For now there is not much intelligence given to this class, it simply returns a move based on the  empty positions of the board.

PlayerFactory  : Factory created for Different players like human or computer. Since the move made are random for every game, using PlayerConfiguration class factory is used. Usage: Factory  Design pattern. Other patterns are also used in the application


TicTacToeBoardField : This class is used to do a validation for the board size based on the configuration passed. Along with this it is used to register a valid move made and stores it.

TicTacToeConfiguration  : This created the current instance of the game holding the board size and players list.

TicTacToeMoveValidator : All the actions like to validate a current move , validate win again row, column or diagonal are performed by this class , hence named validator.

TicTacToeGameController  : This is the main container class which uses reference of the IBoardConsoleSystem and TicTacToeBoardField. It creates the game using the configuration and starts the play. It calls the relevant class for validation and winning moves. Sends proper message to console using IBoardConsoleSystem implementation.



