import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FreeCashFlowIT {

    @Mock
    FreeCashFlow freeCashFlow;

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public List<Double> getNetCashFromOperating() {
        final List<Double> netCashFromOperating = getNetCashFromOperating();
        assertNotNull(netCashFromOperating);
        return netCashFromOperating;
    }

    @Test
    public List<Double> getCapitalExpenditure() {
        final List<Double> capitalExpenditure = getCapitalExpenditure();
        assertNotNull(capitalExpenditure);
        return capitalExpenditure;
    }

    @Test
    public double averageFreeCashFlow() {
        List<Double> netCashFromOperating = new ArrayList<>();
        addTestNetCashFromOperating(netCashFromOperating);
        List<Double> capitalExpenditure = new ArrayList<>();
        addTestCapitalExpenditure(capitalExpenditure);
        FreeCashFlow mockFCF = mock(FreeCashFlow.class);

        when(mockFCF.getNetCashFromOperating()).thenReturn(netCashFromOperating);
        assertEquals(mockFCF.getNetCashFromOperating(), netCashFromOperating);

        when(mockFCF.getCapitalExpenditure()).thenReturn(capitalExpenditure);
        assertEquals(mockFCF.getCapitalExpenditure(), capitalExpenditure);

        final double avg = averageFreeCashFlow();
        assertEquals(avg, (double) 25560.25);
        return avg;
    }

    private void addTestCapitalExpenditure(List<Double> capitalExpenditure) {
        capitalExpenditure.add((double) 3057);
        capitalExpenditure.add((double) 2103);
        capitalExpenditure.add((double) 1862);
        capitalExpenditure.add((double) 1989);
    }

    private void addTestNetCashFromOperating(List<Double> netCashFromOperating) {
        netCashFromOperating.add((double) 32369);
        netCashFromOperating.add((double) 28593);
        netCashFromOperating.add((double) 25067);
        netCashFromOperating.add((double) 25223);
    }
}