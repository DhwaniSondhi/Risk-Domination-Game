# Risk Domination Game
This game is built on Java and Swing using both pull and push models. We have used JUnit for automated testing.

## Description & Working
This game can be played by two or more players(both human and computerized players). The computerized players can have following behaviors using Strategy Pattern:
1.  **Aggressive computer player**: It focuses to attack other countries by reinforces its strongest countries, attacks until it can and fortifies for maximizing armies in one country.  
2.  **Benevolent computer player**: It focuses to protect its weak countries by avoiding attacking, reinforcing and fortifying weak countries.
3.  **Random computer player**: It plays randomly by reinforcing, attacking and fortifying countries randomly.
4.  **Cheater computer player**: It reinforces and fortifies by doubling the armies in all countries and conquering all the neighbors.

Maps can be loaded, edited or created. 

The game can be played in two modes:
1.  **Single Game Mode**: The game starts by selecting loading a map, selecting number and behaviors of players. The game ends when one of the player wins. 
2.  **Tournament Mode**: The game starts by choosing 1 to 5 maps, 2 to 4 computer players(with same or different behaviors). A report will be shown at the end of winners for each map.

## Coding Conventions
1. Indentation
    1. Should be properly indented with 4 Spaces / 1 tab
    2. The opening curly brace should be in the same line as function or class name.
2. Naming 
    1. Should follow camelCase
    2. Variable names and method names should start with small letter
    3. Constants should be AllCaps
    4. class names should start with capital letter
3. Declarations
    1. All variables need to be declared on top of the class before any function definitions
    2. function declarations should come after variable definitions
    3. group together private and public variables / functions
4. Comments
    1. Add comments before all the code blocks (class, function, variables) 
    2. No unwanted commented code should be present 
    3. Javadoc comments should include all the parameter and return type if there is any.
5. Folder Structure
    1. Only 4 folders
    2. Model : Core Component Classes and Business logic only.
    3. View : GUI classes only
    4. Controller : Controller Classes containing the flow of the application and user interactions.
    5. Utility : Helper files for handling maps
    
    
## Git Conventions
1. Branch Name
    1. {name_initials}_{feature_title} eg: jm_feature1 / ns_feature2
2. Commits
    1. Commit Description should be clear and concise in a sentence
3. Merge Request
    1. All merge requests should be made for develop branch
    2. should pass the build and test phases in CI
4. Merge Request needs to be approved by someone other than the creator
5. One Branch per feature
   
