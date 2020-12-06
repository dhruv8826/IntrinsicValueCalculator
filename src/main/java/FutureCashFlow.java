import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FutureCashFlow {
    private static Scanner sc;
    private static Double discountingRate;
    private static Double terminalValueRate;
    private static Double averageCashFlow;

    public FutureCashFlow(final Scanner sc, final Double discountingRate, final Double terminalValueRate, Double averageCashFlow) {
        this.sc = sc;
        this.discountingRate = discountingRate;
        this.terminalValueRate = terminalValueRate;
        this.averageCashFlow = averageCashFlow;
    }


    public static double finalFutureCashFlow() {
        System.out.println("Enter both future cash flow percentages (Usually somewhere between 5 to 20% based on companies future growing power)-");
        final Double futureCashFlowPercentage1 = sc.nextDouble();
        final Double futureCashFlowPercentage2 = sc.nextDouble();
        final List<Double> futureCashFlow = getFutureCashFlow(futureCashFlowPercentage1, futureCashFlowPercentage2);
        final Double totalPresentValue = getTotalPresentValue(futureCashFlow);
        System.out.println("Total Present Value before reducing net debt = " + String.format("%.2f", totalPresentValue));
        final double netDebt = getNetDebt();
        return totalPresentValue - netDebt;
    }

    private static Double getTotalPresentValue(final List<Double> futureCashFlow) {
        final List<Double> presentValueList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            presentValueList.add(i, calculatePresentValue(futureCashFlow.get(i), i + 1));
        }
        presentValueList.add(10, calculatePresentValue(futureCashFlow.get(10), 10));
        double totalPresentValue = 0.0;
        for (Double presentVal : presentValueList) {
            totalPresentValue = totalPresentValue + presentVal;
        }
        return totalPresentValue;
    }

    private static Double calculatePresentValue(final Double value, final int pow) {
        final Double val1 = (100 + discountingRate) / 100;
        Double val2 = 1.0;
        for (int i = 1; i <= pow; i++) {
            val2 = val2 * val1;
        }
        return value / val2;
    }

    private static List<Double> getFutureCashFlow(final Double futureCashFlowPercentage1, final Double futureCashFlowPercentage2) {
        final List<Double> futureCashFlow = new ArrayList<>();
        futureCashFlow.add(0, calculateFreeCashFlow(averageCashFlow, futureCashFlowPercentage1));
        for (int i = 1; i < 5; i++) {
            futureCashFlow.add(i, calculateFreeCashFlow(futureCashFlow.get(i - 1), futureCashFlowPercentage1));
        }
        for (int i = 5; i < 10; i++) {
            futureCashFlow.add(i, calculateFreeCashFlow(futureCashFlow.get(i - 1), futureCashFlowPercentage2));
        }
        final Double terminalValue = calculateTerminalValue(futureCashFlow.get(9));
        futureCashFlow.add(10, terminalValue);
        return futureCashFlow;
    }

    private static Double calculateTerminalValue(final Double value) {
        final Double val1 = 100 + terminalValueRate;
        final double val2 = discountingRate - terminalValueRate;
        return value * val1 / val2;
    }

    private static Double calculateFreeCashFlow(final Double cashFlow, final Double futureCashFlowPercentage1) {
        final double percentageValue = futureCashFlowPercentage1 / 100;
        return cashFlow * (1 + percentageValue);
    }

    private static double getNetDebt() {
        System.out.println("Enter total debt");
        final Double netDebt = sc.nextDouble();
        System.out.println("Enter the cash and cash equivalent");
        final Double cashEquivalent = sc.nextDouble();
        return netDebt - cashEquivalent;
    }
}
