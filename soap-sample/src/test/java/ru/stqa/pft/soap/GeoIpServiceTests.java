package ru.stqa.pft.soap;
import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
public class GeoIpServiceTests {
    @Test
    public void testMyIp() {

        String location = new GeoIPService().getGeoIPServiceSoap().getLocation();
        System.out.println(location);
        assertEquals(location, "<GeoIP><Country>UA</Country><State>23</State></GeoIP>");
    }
}
