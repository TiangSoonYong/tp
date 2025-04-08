# Irwin Teo - Project Portfolio Page

## Overview

Rolladie is a tiny text-based adventure game aimed at maximising replayability and strategic depth for immersive gameplay through each and every turn. It pulls you into delving head-first into the secrets of its world and to emerge victorious in the fight against the horde of enemies charging your way!

### Summary of Contributions

#### Code contributed: 
My contributions can be found in the following [link](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=zavsky&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=zavsky&tabRepo=AY2425S2-CS2113-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

Enhancements implemented:
- Improved the replayability of the game through increasing dynamism. Implemented Power stats to prevent players from abusing/spamming strong skills. At the same time, the Power stat will also increase total damage output when stacked. This presents players the dilemma of choice and adds strategic depth to the game.
- Implemented special effects for each Ability to the Abilities class (player skills), to increase the number of ways a battle outcome can be reached. Each ability can introduce extra rules to the damage calculation, to change the outcome dynamically.
- Simplified the way player characters are created in order to vary the enemies that the players can face
- All terminal animations like the ones in TypewriterEffect, DiceBattleAnimation, HpBar packages, to increase the appeal of the terminal-based application for players

Contributions to User Guide:
Written the parts including Starting a new game, Loading from a save, Making your character, Choosing your battle ability, Battle sequence, command summary and FAQ

Contributions to Developer Guide:
Helped draw the overall architecture diagram, edited the Main component, Functions component, and Game component. Created the sequence diagram for Load and save segment
