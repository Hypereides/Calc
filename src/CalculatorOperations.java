/*
 * * The HashMap, named 'operations', has String keys and BiFunction values. 
 * Each String key is an operator (like "+", "-", "*", "/"), and each BiFunction takes two Double operands and returns a Double result.
 * The BiFunction is a functional interface in Java that represents a function that takes two arguments and produces a result.
 * This is used to abstract the operations and make the code more flexible and easier to maintain.
 * 
 * When an operation is to be performed, the operator is popped from the stack and used to retrieve the corresponding BiFunction from the HashMap.
 * The BiFunction is then applied to the two operands, which are also popped from the stack.
 * The result is then pushed back onto the stack.
 * 
 */

import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
import java.util.function.BiFunction;

public class CalculatorOperations {
    Stack<String> stack = new Stack<String>();

    public void performOperation() {
        if (stack.size() < 3) {
            return;
        }
        String operator = stack.pop();
        double n2 = Double.parseDouble(stack.pop());
        double n1 = Double.parseDouble(stack.pop());
        

        // Association of each operator with the operation respectively
        Map<String, BiFunction<Double, Double, Double>> operations = new HashMap<>();
        operations.put("+", (num1, num2) -> num1 + num2);
        operations.put("-", (num1, num2) -> num1 - num2);
        operations.put("*", (num1, num2) -> num1 * num2);
        operations.put("/", (num1, num2) -> {
            if (num2 != 0) {
                return num1 / num2;
            } else {
                throw new ArithmeticException("Cannot divide by zero");
            }
        });
        //Get the opertion corresponding to the operator 
        BiFunction<Double, Double, Double> operation = operations.get(operator);
        if (operation != null) {
            double result = operation.apply(n1, n2);
            stack.push(String.valueOf(result));
        } else {
            throw new IllegalArgumentException("Error: Invalid operator");
        }
    }
}