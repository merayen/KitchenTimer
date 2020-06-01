# Time calculation
This app tries to find the fastest route to make food.

## Task time multiplied with the tool used
For example, the time it takes to boil potatoes depends on type of cook top being used. An induction zone at 3500 watt
will bring the water to the boiling point much faster than an old iron based cook top.

The tool defines how much it can handle at once, and its wattage. E.g:

### Potato example
- Tool: Kitchen at home
    - All other tools below linked to this tool, the kitchen
- Tool: Induction zone
    - Defines `type=boil` and `value=0.12`
    - Means that the induction zone uses 0.12 second on each gram of water to bring from 8C to 100C. that is 2 minutes
    on each litre of water
- Tool: Potato peeler
    - Defines `type=potato` and `value=1`. Value is always 1 as it gets multiplied with seconds the chef uses 
    on the task
- Tool: The chef
    - Defines that he/she uses `20s` on each task `Peel potatoes`
- Item: Potato
    - Amount: 200 gram
- Item: Saucepan
    - Defines `capacity=2L`

Now the tasks:
- Task: Peel potatoes
    - Amount: 200 gram
    - The chef uses 20s for each potato multiplied with 4: 1m 20s
- Task: Bring the water with potatoes to boil
    - Water amount: 1000 gram
    - User sets amount of potatoes in the recipe (defined by the amount of the recipe)
    - App figures out the total time needed to bring it to boil
- Task: Boil the potatoes
    - Defines that it will take 23 minutes
    - Not multiplied by anything