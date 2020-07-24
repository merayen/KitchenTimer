# Database

Proposal on how the database may look like, respecting the features in [Features](Features.md)

## Recipes

### Example formula
- Item 1
    - `type=pot`
    - `volume=2 liters`
    - `diameter=0.20 meter`
    - `material=stainless_steel`
- Item 2
    - `type=pan`
    - `volume=8 liters`
    - `diameter=0.32 meter`
    - `material=carbon_steel`
- Item 3
    - `type=potato`
    - `unit=weight`
- Item 4
    - `type=water`
    - `unit=volume`
- Task
    - `name=Boil potatoes`
    - `requires=pot.volume { ( [water] * x * 0.2 liter + [potato] * x * 100 gram ) }`
- TaskInstance
    - `name=Boil the potatoes`
    - `requires=Boil potatoes`
    - `amount=1.5`

If the amount of boiled potatoes to be made is more than the largest pot, the app will create another TaskInstance for
a secondary pot too.


### Formula representation in database
The math formula needs to be represented in the database.

- TaskFormulaItem
    - `type="add"`
    - `a=TaskFormulaItem.id`
    - `b=TaskFormulaItem.id`
- TaskFormulaItem
    - `type="sub"`
    - `a=TaskFormulaItem.id`
    - `b=TaskFormulaItem.id`
- TaskFormulaItem
    - `type="mul"`
    - `a=TaskFormulaItem.id`
    - `b=TaskFormulaItem.id`
- TaskFormulaItem
    - `type="div"`
    - `a=TaskFormulaItem.id`
    - `b=TaskFormulaItem.id`
- TaskFormulaItem
    - `type="max"`
    - `a=TaskFormulaItem.id`
    - `b=TaskFormulaItem.id`
- TaskFormulaItem
    - `type="min"`
    - `a=TaskFormulaItem.id`
    - `b=TaskFormulaItem.id`
- TaskFormulaItem
    - `type="pow"`
    - `a=TaskFormulaItem.id`
    - `b=TaskFormulaItem.id`
- TaskFormulaItem
    - `type="sqr"`
    - `a=TaskFormulaItem.id`
    - `b=TaskFormulaItem.id`

### Complete database example
- Item
    - `type=potato`
- Item
    - `type=water`
- Task
    - `name=Boil potatoes`
    - `requires="[water.volume] = 0.2 * [x]; [potato.weight] = 0.1 * [x]; [pot.volume] = [water.volume] + [potato.weight] * 1.1`
