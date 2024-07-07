using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace bilete.model
{
    [Serializable]
    public class DateOnly
    {
        private DateTime dateTime;

        public int Year { get => dateTime.Year; }
        public int Month { get => dateTime.Month; }
        public int Day { get => dateTime.Day; }

        public DateTime ToDateTime(TimeOnly time)
        {
            dateTime = new DateTime(Year, Month, Day, time.Hour, time.Minute, time.Second);
            return dateTime;
        }

        public static DateOnly FromDateTime(DateTime dateTime) => new DateOnly() { dateTime = dateTime };

        public override string ToString() => dateTime.ToString();
        public string ToString(String format) => dateTime.ToString(format);
    }

    [Serializable]
    public class TimeOnly
    {
        private DateTime dateTime;

        public int Hour { get => dateTime.Hour; }
        public int Minute { get => dateTime.Minute; }
        public int Second { get => dateTime.Second; }

        public TimeOnly()
        {
            dateTime = DateTime.Now;
            dateTime = dateTime.Date;
        }

        public static TimeOnly FromDateTime(DateTime dateTime) => new TimeOnly() { dateTime = dateTime };

        public override string ToString() => dateTime.ToString();
        public string ToString(String format) => dateTime.ToString(format);
    }
}
