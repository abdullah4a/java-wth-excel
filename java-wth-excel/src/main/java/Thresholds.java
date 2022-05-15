public class Thresholds {
    private String destination;
    private Long dealPrice;
    private Long normalPrice;
    private double percentOff;

    public Thresholds(String destination, Long dealPrice, Long normalPrice, double percentOff) {
        this.destination = destination;
        this.dealPrice = dealPrice;
        this.normalPrice = normalPrice;
        this.percentOff = percentOff;
    }

    public Thresholds() {
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Long getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(Long dealPrice) {
        this.dealPrice = dealPrice;
    }

    public Long getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(Long normalPrice) {
        this.normalPrice = normalPrice;
    }

    public double getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(double percentOff) {
        this.percentOff = percentOff;
    }
}
