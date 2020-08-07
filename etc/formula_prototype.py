recipe = {
	1: {
		"name": "Norsk kjøttkaketallerken",
		"info": "Kjempegod tallerken med kjøttkaker og tilbehør"
	}
}

tasks = {
	1: {
		"name": "Norsk kjøttkaketallerken",
		"uses": [2, 9]
	},

	2: {
		"name": "Boil vegetables",
		"uses": [3, 4, 5, 6],
		"ingredients": [1, 2, 3]
	},

	3: {
		"name": "Heat water",
	},

	4: {
		"name": "Peel potatoes",
	},

	5: {
		"name": "Peel carrots"
	},

	6: {
		"name": "Boil potatoes and carrots", 
	},

	9: {
		"name": "Brown sauce",
		"uses": [10, 11],
		"ingredients": []
	},

	10: {
		"name": "Mix flour and butter in pan"
	},

	11: {
		"name": "Heat up pan to 200C and stay"
	},

	12: {
		"name": "Lay tyttebær on the dish"
	}
}

items = {
	1: {
		"name": "Potatoes"
	},

	2: {
		"name": "Done meat balls"
	},

	3: {
		"name": "Carrots"
	},

	4: {
		"name": "Flour"
	},

	5: {
		"name": "Butter"
	}
}

storage = {
	1: {
		"item": 1,
		"location": 1
	}
}

location = {
	1: {
		"name": "Fridge",
		"parent": None
	},

	2: {
		"name": "Freezer",
		"parent": None
	},

	3: {
		"name": "Shelve 2",
		"parent": 1
	}
}


class Solver:
	def __init__(self, tasks, items):
		self.tasks = tasks
		self.items = items
	
	def begin(self, task_id):
		print(f"Supposed to make {self.tasks[task_id]['name']}")
		tasks = self.__collect_tasks(task_id)

		return {
			"tasks": tasks,
		}
	
	def use(self):
		pass

	def __collect_tasks(self, task_id):
		result = [task_id]

		for x in self.tasks[task_id].get("uses", []):
			result.extend(self.__collect_tasks(x))

		return result


solver = Solver(tasks, items)
solver.begin(1)
solver.use([])
