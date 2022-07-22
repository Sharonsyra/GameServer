## GameServer

Prerequisites:
- JDK 11
- sbt 1.4.x or higher

Once inside the project folder use the following command to run the code:
```
sbt run
```

## App Info
- User connects to the application and picks a game type between **SingleCardGame** and **DoubleCardGame**.
- The game add the user to the waiting list for their game of choice
- The game pairs the user if the waiting list has at least 2 players
- The user receives dealt cards
- The user **Plays** or **Folds**
- The Server scores the players and updates their balance
- In case of a draw, the server deals new cards


## Assumptions

- The project assumes the user knows what the game they want and whether they will play or fold.
  (I did not have much time to create a client to enable individual users to pick games and play in an interactive 
  fashion. I will continue working on it from this repository https://github.com/Sharonsyra/karata)
