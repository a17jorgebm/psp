package practicarExamen.ejer10FundRaising.tcp;

import java.util.concurrent.atomic.AtomicInteger;

public class Fund {
    private final AtomicInteger totalAmount;

    public Fund() {
        totalAmount = new AtomicInteger(0);
    }

    public int getTotalAmount() {
        return totalAmount.get();
    }

    public int addAndGetTotalAmount(int add){
        return totalAmount.addAndGet(add);
    }
}
