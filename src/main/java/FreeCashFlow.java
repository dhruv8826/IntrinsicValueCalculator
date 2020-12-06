import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FreeCashFlow {
    private static Scanner sc;
    private static String shareName;

    public FreeCashFlow(final Scanner sc, final String shareName) {
        this.sc = sc;
        this.shareName = shareName;
    }

    public static List<Double> getNetCashFromOperating() {
        final List<Double> netCashFromOperating = new ArrayList<>();
        System.out.println("Enter the net cash from operating activities for " + shareName + " for last 4 years on separate lines starting from the latest to the oldest(All values in Crs.)");
        for (int i = 0; i < 4; i++) {
            netCashFromOperating.add(sc.nextDouble());
        }
        return netCashFromOperating;
    }

    public static List<Double> getCapitalExpenditure() {
        final List<Double> capitalExpenditure = new ArrayList<>();
        System.out.println("Enter the capital expenditure for " + shareName + " for last 4 years on separate lines starting from the latest to the oldest(All values in Crs.)");
        for (int i = 0; i < 4; i++) {
            capitalExpenditure.add(sc.nextDouble());
        }
        return capitalExpenditure;
    }

    public static Double averageFreeCashFlow() {
        final List<Double> freeCashFlow = new ArrayList<>();
        final List<Double> netCashFromOperating = getNetCashFromOperating();
        final List<Double> capitalExpenditure = getCapitalExpenditure();
        for (int i = 0; i < 4; i++) {
            final Double freeCashFlowPerYear = netCashFromOperating.get(i) - capitalExpenditure.get(i);
            freeCashFlow.add(i, freeCashFlowPerYear);
        }
        printFreeCashFlow(freeCashFlow);
        printFreeCashFlowChange(freeCashFlow);
        return avg(freeCashFlow);
    }

    private static Double avg(final List<Double> freeCashFlow) {
        Double sum = 0.0;
        Double average = 0.0;
        for (Double cashFlow : freeCashFlow) {
            sum = sum + cashFlow;
        }
        average = sum / 4;
        return average;
    }

    private static void printFreeCashFlow(final List<Double> freeCashFlow) {
        System.out.println("Free Cash Flow ->");
        System.out.println("Year->\t  fy   \t fy-1  \t fy-2  \t fy-3  ");
        System.out.print("      \t");
        for (int i = 0; i < 4; i++) {
            System.out.print(String.format("%.2f", freeCashFlow.get(i)) + "\t\t");
        }
        System.out.println();
    }

    private static void printFreeCashFlowChange(final List<Double> freeCashFlow) {
        final List<Double> freeCashFlowChange = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            final Double cashFlowDiff = freeCashFlow.get(i) - freeCashFlow.get(i + 1);
            freeCashFlowChange.add(i, cashFlowDiff / freeCashFlow.get(i + 1));
        }
        System.out.println("Free Cash Flow Change ->");
        System.out.print("      \t");
        for (int i = 0; i < 3; i++) {
            System.out.print(String.format("%.2f", freeCashFlowChange.get(i)*100) + "\t\t");
        }
        System.out.println();
    }
}
