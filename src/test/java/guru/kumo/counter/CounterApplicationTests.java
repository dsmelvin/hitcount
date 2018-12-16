package guru.kumo.counter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CounterApplicationTests {
    @Autowired
    private Counter counter;

    String sample = "1407564300|www.cnn.com\n" +
            "1407564300|www.nba.com\n" +
            "1407564301|www.nba.com\n" +
            "1407478021|www.facebook.com\n" +
            "1407478022|www.facebook.com\n" +
            "1407481200|news.ycombinator.com\n" +
            "1407478028|www.google.com\n" +
            "1407564301|sports.yahoo.com\n" +
            "1407564300|www.nba.com\n" +
            "1407564301|sports.yahoo.com\n" +
            "1407478022|www.google.com\n" +
            "1407478022|www.google.com \n" +
            "140aaaaaaa|www.google.com\n" +
            "140aaaaaaa|www.google.com||\n" +
            "1407478022|www.google.com|  \n" +
            "1407478022|\n" +
            "1407478022\n" +
            "1407648022|www.twitter.com";

    @Test
    public void contextLoads() {
        for(String s: sample.split("\n")){
            counter.add(s);
            counter.add(s);
            counter.add(s);
        }
        System.out.println(counter);
    }
}

