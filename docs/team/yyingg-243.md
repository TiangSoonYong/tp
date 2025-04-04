# Lee Ying Ying - Project Portfolio Page

# Overview
- RollaDie is a Dungeon & Dragons (DnD) text-based RPG,
optimized to play using Command Line Interface (CLI) and
has a simple text-ui display that reminisces games of the 1960s.        
- This program is meant for CS2113 students as a stress reliever
and it aims to provide a fun and replayable experience!

# Summary of Contributions
## Code contributed
Code contributed: [Code contributed](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=yyingg-243&breakdown=true)

## Enhancements implemented
1. Roll dice 
  - Implemented the RollDice class using Java's random library to simulate dice roll.
  - Dice outcome is important in determining bonus points used during attack and defend action.
  - Rolling dice is crucial to the turn-based mechanics of the game by introducing randomness into the game.

2. Damage calculation logic
  - Refactored the attack logic to separate damage calculation into its own method.
  - This helps in improving OOP design and improve readability and testability of the code.

3. Defend logic
  - Integrated roll dice logic for defend action
  - Correctly sets bonus points and defending status base on dice outcome and player commands.

4. Improved Code reliability and security
  - Added at least two Junit test cases for classes such as Character and RollDice to ensure correctness of logic.
  - Integrated assertions to validate game or character state to prevent invalid actions.


## Contributions to UG
- Wrote the base template for user guide.
- Complete components listed below:
  - Table content (add links to each section of user guide)
  - Introduction (Introduce Rolladie to the user)
  - Quick start (include explanation on running jar file)
  - Command summary (listed out possible commands in RollaDie)
- Draft out feature component
- Wrote explanation and include example usage for features. (Attacking an enemy, defending against incoming attacks)

## Contributions to DG
- Wrote the base template for developer guide.
- Authored overview section, implementation details section and diagram section for both attack and defend feature.
- Completed acknowledgement, setting up and getting started, product scope (target user, value proposition)
and user stories component.
- Draft out component details and wrote details for components such as main, storage, exception.
- Created UML diagrams as listed below:
    - Class Diagram (Exception class)
    - Sequence Diagram (attack, defend feature)

## Contributions to team-based tasks
* Maintain issue tracker
* Engaged in group discussion
* Set up milestones and linked it to relevant issues
* Reviewed PRs submitted by team members