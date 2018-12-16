package guru.kumo.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@SpringBootApplication
public class CounterApplication implements CommandLineRunner {
    @Autowired
    private Counter counter;

    public static void main(String[] args) {
        SpringApplication.run(CounterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (String s : args) {
            File f = new File(s);
            if (f.exists()) {
                BufferedReader in
                        = new BufferedReader(new FileReader(s));
                in.lines().forEach(line -> {
                    counter.add(line);
                });
                in.close();
            }
        }
        System.out.print(counter);
    }
}

