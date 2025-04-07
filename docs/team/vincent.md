# Vincent - Project Portfolio Page

## Overview
- RollaDie is a Dungeon & Dragons (DnD) text-based RPG,
optimized to play using Command Line Interface (CLI) and
has a simple text-ui display that reminisces games of the 1960s.        
- This program is meant for CS2113 students as a stress reliever
and it aims to provide a fun and replayable experience!

### Summary of Contributions
## Code contributed
Code contributed: [Code contributed](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=vincesum&breakdown=true)

## Enhancements implemented
1. Loot Event
    - Implemented the Loot class which is a subclass of Event to grant player gold after defeating enemy.
    - Implemented testing logic for determining if player won the battle.
    - Only grants player loot if player wins the battle.
    - Junit Testing for Loot Event.
    
2. Equipment Database
    - Implemented the Equipments with the Armor, Boots, and Weapons as well as their associated databases.
    - Equipment is crucial for player to upgrade his stats to progress.
    - Equipments can be bought in Shop.

3. Shop Event
    - Junit Testing for Shop Event.

4. Balancing
    - As Rolladie is a game, managing the stats of the player and enemies as the waves progress is important.
    - Balanced the player and enemy such that the player is able to die if he is not careful while ensuring
    that the game is not too difficult.

## Contributions to UG
- Wrote about the Loot feature in the UG.
- Explained the battle sequence and the total number of rounds as well as the goal of the player.

## Contributions to DG
- Authored overview section, implementation details section and diagram section for attack, heal, loot and UI.
- Created UML diagrams as listed below:
    - Sequence Diagram (attack, heal, loot, UI)

## Contributions to team-based tasks
* Maintain issue tracker
* Set up group meetings
* Set up milestones and linked it to relevant issues
* Reviewed PRs submitted by team members