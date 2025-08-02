package com.example.nerdguesser

class InvalidDayException: Exception("The game does not have a playable game for the entered day")

class GameDataMissingException: Exception("Missing game data")