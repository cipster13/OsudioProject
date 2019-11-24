import com.osudio.service.BusinessHourCalculator;
import com.osudio.service.implementation.BusinessHourCalculatorImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class UnitTest {

    BusinessHourCalculator businessHourCalculator;

    @Before
    public void init() throws Exception {
        businessHourCalculator = new BusinessHourCalculatorImpl("09:00", "15:00");
        businessHourCalculator.setOpeningHours(DayOfWeek.FRIDAY, "10:00", "17:00");
        businessHourCalculator.setOpeningHours("2010-12-24", "8:00", "13:00");
        List<DayOfWeek> l = new ArrayList<>();
        l.add(DayOfWeek.SUNDAY);
        l.add(DayOfWeek.WEDNESDAY);
        businessHourCalculator.setClosed(l);
        String [] s = new String[1];
        s[0] = "2010-12-25";
        businessHourCalculator.setClosed(s);
    }

    @Test
    public void testDates(){

        Date date = businessHourCalculator.calculateDeadline(2 * 60 * 60, LocalDate.parse("2010-06-07"), LocalTime.parse("09:10"));

        Assert.assertNotNull(date);
        Assert.assertEquals(date.getTime(), new GregorianCalendar(2010, 5, 7, 11, 10).getTime().getTime());

        Date date2 = businessHourCalculator.calculateDeadline(15 * 60, LocalDate.parse("2010-06-08"), LocalTime.parse("14:48"));

        Assert.assertNotNull(date2);
        Assert.assertEquals(date2.getTime(), new GregorianCalendar(2010, 5, 10, 9, 03).getTime().getTime());

        Date date3 = businessHourCalculator.calculateDeadline(7 * 60 * 60, LocalDate.parse("2010-12-24"), LocalTime.parse("06:45"));

        Assert.assertNotNull(date3);
        Assert.assertEquals(date3.getTime(), new GregorianCalendar(2010, 11, 27, 11, 00).getTime().getTime());


    }
}
