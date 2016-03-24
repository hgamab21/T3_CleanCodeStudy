TicTacToe v1.0

Created by Hiram Abraham

Built with:
-JDK 1.8
-JUnit 4.12
-Maven 3.3.9

Instructions:

-Install JDK 1.8, most recent distribution (1.8.0_66 as of the writing of this readme)
-Run the game with this command: java -cp target\tictactoe-1.0-SNAPSHOT.jar TicTacToeUI

Enjoy!

*BELOW FOR EDITORS/PROGRAMMERS ONLY*

*NOTE TO WINDOWS 8.1+ USERS*
-->For running source files from console, especially if intending to compile with console as well then make sure your evnironment variables are 
-->set. CLASSPATH does not exist so you will have to CREATE it. Create it in Start>Control Panel>System>Advanced>Environment Variables under
-->System Variables. Set it to where the class binary is going to compile to, as well as the junit/org.hamcrest directories [for testing].

/-For Testers-/
Included in the AITest is commented out code for the sake of exposing internals if you wish to thoroughly diagnose any potential bugs. However be advised that as of the current version said output process will take about ~10min on a 1.8 AMD Quad-Core processor.