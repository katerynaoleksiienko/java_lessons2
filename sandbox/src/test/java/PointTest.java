import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pdf.sandbox.MyFirstProgram;
import ru.stqa.pdf.sandbox.Point;

public class PointTest {

    @Test
    public void testPoint(){
        Point point1 = new Point(2, 9);
        Point point2 = new Point(8, 5);
        Assert.assertEquals(point1.distance(point2), 2, 9);
    }
    public void testMyFirst(){
        Point point1 = new Point(2, 9);
        Point point2 = new Point(8, 5);
        Assert.assertEquals(MyFirstProgram.distance(point1, point2), 2, 9);
    }
}
