package src.calendarfx;

public class Event
{
    private String NameEvent;
    private String Place;
    private String Description;
    private String Time;
    private String Year, Month, Day;


    public Event(String nameEvent, String year, String month, String day)
    {
        this.NameEvent = nameEvent;
        this.Year = year;
        this.Month = month;
        this.Day = day;
    }

    public Event(String nameEvent, String place, String time, String year, String month, String day)
    {
        this.NameEvent = nameEvent;
        this.Place = place;
        this.Time = time;
        this.Year = year;
        this.Month = month;
        this.Day = day;
    }

    public Event(String nameEvent, String year, String month, String day, String place, String description, String time)
    {
        this.NameEvent = nameEvent;
        this.Year = year;
        this.Month = month;
        this.Day = day;
        this.Place = place;
        this.Description = description;
        this.Time = time;
    }



    public String GetNameEvent()
    {
        return this.NameEvent;
    }

    public String GetPlace()
    {
        return this.Place;
    }

    public String GetDescription()
    {
        return this.Description;
    }

    public String GetTime()
    {
        return this.Time;
    }

    public String GetYear()
    {
        return this.Year;
    }

    public String GetMonth()
    {
        return this.Month;
    }

    public String GetDay()
    {
        return this.Day;
    }
}
