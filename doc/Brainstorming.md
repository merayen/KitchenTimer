# Brainstorming

## What we want
- Calculate time it takes to cook a recipe
- Show the steps necessary to make the recipe
    - Down to details, like "peel potato" and "bring water to boil"
- Show alternative recipe routes to the user (e.g make sauce of tomatoes manually vs having a box of tomato sauce
available)
- Track actual time the chef spends on the different tasks
- Track every equipment in the kitchen and the chef
- Very fluid interface

## How user uses it
1. Sets up a new kitchen
    - Adds the equipment the kitchen has
        - If the kitchen has e.g 4 potato peelers they need to be added 4 times(?)
2. Creates a recipe
    - Adds the tasks, e.g "Peel p"
        - Add ingredients needed by the task, e.g `Item[Name=Potato]`
        - Sets which tool type that are required for the task (induction zone, potato peeler etc)
        - Sets how much gram this task will make, if it does that

