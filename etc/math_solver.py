1 + 2 + 3
3 + 3
6

1 + 2 * 3
1 + 6
7

assert 1 + 2 * 3 / 4 - 5 == 1 + 2 * (3 / 4) - 5 == 1 + (2 * (3 / 4)) - 5 == 1 + ((2 * (3 / 4)) - 5)
assert 1 + 2 * 3 / 6 - 5 == 1 + 2 * 0.5 - 5     == 1 + 1 - 5             == 2 - 5                   == -3


def calc(formula):
	a = formula.split()
	numbers = [int(x) for x in a[::2]]
	operators = a[1::2]
	OP_PRECEDENCE = ["*", "+"]

	for precedence in OP_PRECEDENCE:
		temp_numbers = [numbers[0]]
		temp_operators = []

		for index, operator in enumerate(operators):
			if operator == precedence:
				if operator == "+":
					temp_numbers[-1] += numbers[index+1]
				elif operator == "*":
					temp_numbers[-1] *= numbers[index+1]
				else: assert 0
			else:
				temp_operators.append(operators[index])
				temp_numbers.append(numbers[index+1])
		
		numbers = temp_numbers
		operators = temp_operators

		assert len(numbers) - 1 == len(operators), "Wrong numbers and operators length: numbers=%s, operators=%s" % (numbers, operators)

		if not operators:
			break

	assert numbers[0] == eval(formula)
	return numbers[0]

print(calc("10 * 10 + 1 + 2 + 3 * 4 + 5"))
