package Testcodes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class MathQuestion {
    private int firstOperand;
    private int secondOperand;
    private String operator;
    private int result;
    private static Map<Integer, int[]> difficultyMaxMinMap = new HashMap<>();
    private int max;
    private int min;

    static {
        difficultyMaxMinMap.put(0, new int[]{1, 20});
        difficultyMaxMinMap.put(1, new int[] {11, 30});
        difficultyMaxMinMap.put(2, new int[] {11, 70});
        difficultyMaxMinMap.put(3, new int[] {11, 120});
    }

    public int getFirstOperand() {
        return firstOperand;
    }

    public void setFirstOperand(int firstOperand) {
        this.firstOperand = firstOperand;
    }

    public int getSecondOperand() {
        return secondOperand;
    }

    public void setSecondOperand(int secondOperand) {
        this.secondOperand = secondOperand;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public MathQuestion() {
        min = difficultyMaxMinMap.get(1)[0];
        max = difficultyMaxMinMap.get(1)[1];;
    }

    public MathQuestion(int difficulty) {
        min = difficultyMaxMinMap.get(difficulty)[0];
        max = difficultyMaxMinMap.get(difficulty)[1];
    }

    public MathQuestion(int firstOperand, int secondOperand, String operator, int result) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.operator = operator;
        this.result = result;
    }

    public MathQuestion getRandomMathQuestion() {
        String[] operators = new String[]{"+", "-", "*"};
        operator = operators[new Random().nextInt(operators.length)];
        switch (operator) {
            case "+":
                firstOperand = ThreadLocalRandom.current().nextInt(min, max + 1);
                secondOperand = ThreadLocalRandom.current().nextInt(min, max + 1);
                result = firstOperand + secondOperand;
                break;
            case "-":
                firstOperand = ThreadLocalRandom.current().nextInt(min, max + 1);
                secondOperand = ThreadLocalRandom.current().nextInt(min, max + 1);
                result = firstOperand - secondOperand;
                break;
            case "*":
                firstOperand = ThreadLocalRandom.current().nextInt(min, max + 1);
                secondOperand = ThreadLocalRandom.current().nextInt(min, min + 10);
                result = firstOperand * secondOperand;
                break;
            default:
                break;
        }
        return new MathQuestion(firstOperand, secondOperand, operator, result);
    }

    public String getQuestion() {
        return firstOperand + " " + operator + " " + secondOperand + " = ?";
    }
}
