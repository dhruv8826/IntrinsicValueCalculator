import java.io.IOException;
import java.util.Scanner;

public class IntrinsicValueCalculator {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Share Name-");
        final String shareName = sc.nextLine();
        new FreeCashFlow(sc, shareName);
        final Double averageCashFlow = FreeCashFlow.averageFreeCashFlow();
        System.out.println("Average Free Cash Flow over last 4 years =" + String.format("%.2f", averageCashFlow) + "\n");
        if (averageCashFlow > 0) {
            System.out.println("Enter Discounting rate (Usually 9%)");
            final Double discountingRate = sc.nextDouble();
            System.out.println("Enter Terminal Value rate (Usually 3.5%)");
            final Double terminalValueRate = sc.nextDouble();
            new FutureCashFlow(sc, discountingRate, terminalValueRate, averageCashFlow);
            final double futureCashFlowValue = FutureCashFlow.finalFutureCashFlow() * 10000000;
            System.out.println("Total future cash flow's current value->" + String.format("%.2f", futureCashFlowValue) + "\n");
            System.out.println("Enter the total number of shares outstanding for " + shareName);
            final double totalShares = sc.nextDouble();
            final double intrinsicValue = futureCashFlowValue / totalShares;
            System.out.println("\nIntrinsic value of " + shareName + " with a 10% band is-" + String.format("%.2f", intrinsicValue) + "\nRange-");
            final double lowerIntrinsicValue = (intrinsicValue * 1.1);
            final double upperIntrinsicValue = (intrinsicValue * 0.9);
            System.out.println(String.format("%.2f", lowerIntrinsicValue) + " to " + String.format("%.2f", upperIntrinsicValue));
        }
        else
            System.out.println("Average Cash flow is negative, so this would require manual calculation. This might even be a RED FLAG for the stock.");
    }
}
