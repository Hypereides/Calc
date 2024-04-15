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

        BiFunction<Double, Double, Double> operation = operations.get(operator);
        if (operation != null) {
            double result = operation.apply(n1, n2);
            stack.push(String.valueOf(result));
        } else {
            throw new IllegalArgumentException("Error: Invalid operator");
        }
    }
}