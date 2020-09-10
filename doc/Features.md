# Features
Features of the feature-bloated KitchenTimer.

## Kitchen timer
Kitchen app to aid the chef(s) to cook and give instructions at the screen for the chef to follow.
Chef then checks off task-by-task when progressing. This helps the chef not forgetting any ingredient or steps
and keeps a record of available ingredients in the storage and utensils.

User can challenge him/herself by breaking own records trying to be an effective chef, hopefully not sacrificing
the taste or adding finger tips to the food.

## Recipes
Contains a list of recipes with instructions (called "tasks") and the required amount of ingredients.

## Menu
App has a "Menu mode" that are meant for guests. An Android tablet will be sent among the guests where they can order
dishes, read the ingredients and allergies, and maybe calorie count.

### Timing
The app plans how to cook the recipe, in what order instructions should be executed and the time all the stages will take.

### Multiple recipes
Support for running multiple recipes, meaning the user can do multiple recipes at once. The app will automatically try
to plan so that the recipes gets finished almost at the same time.

## Ingredient storage
Track the amount of ingredients available and where it is located.

When cooking recipes, app automatically 

### Best before support
When user adds an amount of an ingredient to his kitchen (freezer, fridge etc), user registers it into the app
with the date when it will go bad.

## Cooking tools
All the pans and tools in the kitchen can be put into the app and combined with tasks. Tasks then requires both
an ingredient, and a tool.

The tools can have parameters, like how quick they bring water to a boil (effective watt multiplied with amount of
water).

See "Formulas" below for more information on the calculation.

## Automatic reminders
App supports automatic reminders of when ingredients are about to go bad. The app will also highlight ingredients that
has only 2 days left(?) before they go bad, and recommend recipes to use those ingredients. This happens automatically,
using notifications when user does not use the app.

## Formulas
Tools and ingredients can have defined parameters that says which other parameter they interact with, and how they
interact, and their own value.

These formulas gets calculated when selecting a recipe, so that the chef can see details on what needs to be done
and that equipment is required/best suited for the different tasks (pans-size, induction wattage etc).

### Parameter operators
- `+`
- `-`
- `*`
- `/`
- `Max`
- `Min`
- `Pow`

### Validation
Every item has a formula that validates if it can be used. E.g, when the app tries to figure out if a tool can be used
to boil 10 liters of water, the tool runs its (and the ingredients) formula against each other and creates a
list of all the possible tools that can be used. From there the app sorts the tools (and ingredients?) by how it
matches (the `requires`-formula in each Task, which is what the formulas output, telling how compatible the task is with
the available items (ingredients and tools)).

