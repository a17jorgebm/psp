package ejer10FundRaising_TCP_UDP.tcp;

public class Funds {
    private Double totalFundAmount;

    public Funds() {
        totalFundAmount = 0d;
    }

    public synchronized Double getTotalFundAmount(){
        return totalFundAmount;
    }

    public synchronized void addAmount(Double amount){
        totalFundAmount+=amount;
    }
}
