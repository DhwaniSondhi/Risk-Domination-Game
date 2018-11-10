
### List of refactorings made

* Use single HashMap instead of two for fortification
* remove unnecessary check for owner in reinforcement (owner is never null)
* break down into small functions for modularity / reuse
* remove redundant id extraction (using country list itself for ids instead of using separate list with only IDs)
* other minor refactorings suggested by IDE lint

### Methods used to determine points
* team discussion
* peer programing
* IDE lint suggestions

### how we chose the targets

* for every phase of the game, we checked if the code could be optimized
* We checked if refactor targets were backed by test cases 
* Refactorings were thought in order to simplify the coding process required for the design patterns we needed for future builds.
 