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
  - Implemented the roll dice feature using Java's random library to simulate dice roll.
  - Dice outcome is important in determining bonus points used during attack and defend action.
  - Rolling dice is crucial to the turn-based mechanics of the game by introducing randomness into the game.

2. Damage calculation logic
  - Refactored the attack logic to separate damage calculation into its own method.
  - This helps in improving OOP design and improve readability and testability of the code.

3. Defend logic
  - Integrated roll dice logic for defend action
  - Correctly sets bonus points and defending status base on dice outcome and player commands.

4. Improved Code reliability and security
  - Added Junit test cases for classes such as Battle and Player to ensure correctness of logic.
  - Integrated assertions to validate game or character state to prevent invalid actions.
  - Solve check style errors.


## Contributions to UG
- Wrote the base template for user guide, draft out feature component,eplanation and include example usage for features.
- Complete component: table content (add links to each section of user guide), introduction
(Introduce Rolladie to the user),quick start (include explanation on running jar file),
command summary (listed out possible commands in RollaDie)

## Contributions to DG
- Wrote the base template for developer guide.
- Completed acknowledgement, overview, setting up and getting started, product scope (target user, value proposition)
and user stories component.
- Draft out component details and wrote implementation details for attack and defend feature. (revised by members)
- Created UML diagrams( revised by members): exception class diagram,
sequence diagram: attack, heal feature, architecture diagram

## Contributions to team-based tasks
* Maintain issue tracker
* Engaged in group discussion
* Set up milestones and linked it to relevant issues
* Reviewed PRs submitted by team members