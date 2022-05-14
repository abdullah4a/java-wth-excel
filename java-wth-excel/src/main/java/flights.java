package src.main.java;

import java.util.Date;

public class flights {
    private Long id;
    private Date date;
    private String origin;
    private String destination;
    private Long dealPrice;
    private Long normalPrice;
    private Date departureDate;
    private Date returnDate;
    private Long numberOfStops;
    private double percentOff;
    private boolean goodDeal;
    private String airline;
    private String departureAirport;
    private String returningAirport;
    private String sentToPremium;
    private String linkToFlights;
    private String sentToFree;
    private String remarks;

    public flights() {
    }

    public flights(Long id, Date date, String origin, String destination, Long dealPrice, Long normalPrice, Date departureDate, Date returnDate, Long numberOfStops, double percentOff, boolean goodDeal, String airline, String departureAirport, String returningAirport, String sentToPremium, String linkToFlights, String sentToFree, String remarks) {
        this.id = id;
        this.date = date;
        this.origin = origin;
        this.destination = destination;
        this.dealPrice = dealPrice;
        this.normalPrice = normalPrice;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.numberOfStops = numberOfStops;
        this.percentOff = percentOff;
        this.goodDeal = goodDeal;
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.returningAirport = returningAirport;
        this.sentToPremium = sentToPremium;
        this.linkToFlights = linkToFlights;
        this.sentToFree = sentToFree;
        this.remarks = remarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
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

    public boolean isGoodDeal() {
        return goodDeal;
    }

    public void setGoodDeal(boolean goodDeal) {
        this.goodDeal = goodDeal;
    }

    public void setNormalPrice(Long normalPrice) {
        this.normalPrice = normalPrice;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public String getLinkToFlights() {
        return linkToFlights;
    }

    public void setLinkToFlights(String linkToFlights) {
        this.linkToFlights = linkToFlights;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Long getNumberOfStops() {
        return numberOfStops;
    }

    public void setNumberOfStops(Long numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    public double getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(double percentOff) {
        this.percentOff = percentOff;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getReturningAirport() {
        return returningAirport;
    }

    public void setReturningAirport(String returningAirport) {
        this.returningAirport = returningAirport;
    }

    public String getSentToPremium() {
        return sentToPremium;
    }

    public void setSentToPremium(String sentToPremium) {
        this.sentToPremium = sentToPremium;
    }

    public String getSentToFree() {
        return sentToFree;
    }

    public void setSentToFree(String sentToFree) {
        this.sentToFree = sentToFree;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
