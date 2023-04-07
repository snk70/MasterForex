

public class get_seconds_time
{
    public static double get_seconds_YourTimes()
    {
        double date_num = 0;
        date_num = (System.DateTime.Now.Year * 365) + System.DateTime.Now.DayOfYear;//Days
        date_num += (date_num * 24) + System.DateTime.Now.Hour;//Hours
        date_num += (date_num * 60) + System.DateTime.Now.Minute;//Minutes
        date_num += (date_num * 60) + System.DateTime.Now.Second;//Seconds

        return date_num;
    }
}