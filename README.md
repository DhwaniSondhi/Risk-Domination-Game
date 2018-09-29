#README

##Coding Conventions
1. Indentation
    1. Should be properly indented with 4 Spaces / 1 tab
2. Naming 
    1. Should follow camelCase
    2. Variable names and method names should start with small letter
    3. Constants should be AllCaps
    3. class names should start with capital letter
3. Declarations
    1. All variables need to be declared on top of the class before any function definitions
    2. function declarations should come after variable definitions
    3. group together private and public variables / functions
4. Comments
    1. Add comments before all the code blocks (class, function, variables)
    2. should follow javadoc format
    3. No unwanted commented code should be present 
5. Folder Structure
    1. Only 2 folders 
    2. Entity : Core Component Classes only
    3. GUI : GUI classes and Classes containing the Logic
##Git Conventions
1. Branch Name
    1. {name_initials}_{feature_title} eg: jm_feature1 / ns_feature2
2. Merge Request
    1. All merge requests should be made for develop branch
    2. should pass the build and test phases in CI