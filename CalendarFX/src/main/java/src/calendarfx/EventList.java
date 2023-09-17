package src.calendarfx;

import java.util.ArrayList;
import java.util.List;

public class EventList
{
    private List<Event> EventActivities;

    public EventList()
    {
        EventActivities = new ArrayList<>();
    }

    public List<Event> GetEventActivities()
    {
        return this.EventActivities;
    }

    public void AddEvent(Event event)
    {
        EventActivities.add(event);
    }
}
