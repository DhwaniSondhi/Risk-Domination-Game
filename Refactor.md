
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