public class TimeOffRequest 
{
    private String startDate;
    private String endDate;
    private String reason;
    private boolean approved;

    public TimeOffRequest(String startDate, String endDate, String reason) 
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.approved = false;
    }

    public String getStartDate() 
    {
        return startDate;
    }

    public String getEndDate() 
    {
        return endDate;
    }

    public String getReason() 
    {
        return reason;
    }

    public boolean isApproved() 
    {
        return approved;
    }

    public void approve() 
    {
        this.approved = true;
    }
}
