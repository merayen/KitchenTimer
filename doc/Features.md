# Features

Features of the KitchenTimer, written down using text as the communication method, in a format called Markdown.

## Kitchen timer
It is a kitchen timer, where the user mostly does not input any time, but uses the timers that are in the recipe.
User can then challenge himself by breaking his own records trying to be an effective chef, hopefully not sacrificing
the taste.

## Recipes
Contain a list of recipes that contains instructions (called "tasks") and the required amount of ingredients.

### Timing
The app plans how to cook the recipe, in what order instructions gets laid out, and the time all the stages will take.

When user starts a recipe, 

### Multiple recipes
Support for running multiple recipes, meaning the user can do multiple recipes at once. The app will automatically try
to plan so that the recipes gets finished almost at the same time.

## Ingredient storage
Track the amount of ingredients available and where it is located.

### Best before support
When user adds an amount of an ingredient to his kitchen (freezer, fridge etc), he registers it into the app
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

These formulas calculate when selecting a recipe, so that the user can see details on what needs to be done.

### Parameter operators
- `+`
- `-`
- `*`
- `/`
- `Max`
- `Min`

### Validation
Every item has a formula that validates if it can be used. E.g, when the app tries to figure out if a tool can be used
to boil 10 liters of water, the tool runs its (and the ingredients) formula against each other and creates a
list of all the possible tools that can be used. From there the app sorts the tools (and ingredients?) by how it
matches (the `requires`-formula in each Task, which is what the formulas output, telling how compatible the task is with
the available items (ingredients and tools).

