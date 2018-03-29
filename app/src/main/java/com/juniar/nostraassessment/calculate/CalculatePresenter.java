package com.juniar.nostraassessment.calculate;

public class CalculatePresenter {
    CalculateView callback;

    public CalculatePresenter(CalculateView callback) {
        this.callback = callback;
    }

    void calculate(String number) {
        String result = String.valueOf(Math.pow(2.0, Double.parseDouble(number)));
        StringBuilder stringBuilder = new StringBuilder();
        int resultCalculation = result.substring(0, result.length() - 2).length();
        int total = 0;
        for (int i = 0; i < resultCalculation; i++) {
            total += Integer.parseInt(String.valueOf(result.charAt(i)));
            if (i != resultCalculation - 1) {
                stringBuilder.append(result.charAt(i) + "+");
            } else {
                stringBuilder.append(result.charAt(i));
            }
        }
        callback.onCalculate(result, stringBuilder.toString(), total);
    }
}
