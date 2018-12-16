package guru.kumo.counter;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Counter {
    private static final long timeBucket = 60 * 60 * 24;
    private HashMap<Long, HashMap<String, Integer>> storage = new HashMap<>();

    /**
     * Big-O O(1)
     */
    public void add(String s) {
        String[] tokens = s.split("\\|");
        if (tokens.length == 2) {
            try {
                Long l = Long.parseLong(tokens[0]);
                long dayBucket = l / timeBucket;
                storage.compute(dayBucket, (dayBucketKey, dayBucketItem) -> {
                            if (dayBucketItem == null) {
                                HashMap<String, Integer> item = new HashMap<String, Integer>();
                                item.put(tokens[1].trim(), 1);
                                return item;
                            } else {
                                dayBucketItem.compute(tokens[1].trim(), (url, count) -> (count == null) ? 1 : count + 1);
                                return dayBucketItem;
                            }
                        }
                );
            } catch (NumberFormatException e) {
                // skip and log somewhere
            }
        }
    }

    /**
     * Big-O O(n log n)
     */
    public String toString() {
        StringBuffer sb = new StringBuffer("\n");
        storage.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).forEach(e -> {
            sb.append(showDate(e.getKey() * timeBucket * 1000)).append("\n");
            e.getValue().entrySet().stream().sorted((x, y) -> y.getValue().compareTo(x.getValue())).forEach(e2 -> {
                sb.append("\t").append(e2.getKey()).append(" ").append(e2.getValue()).append("\n");
            });
        });
        return sb.toString();
    }

    private String showDate(long timeInMillis) {
        StringBuffer sb = new StringBuffer("\n");
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        c.setTimeInMillis(timeInMillis);
        return sb.append(c.get(Calendar.MONTH) + 1).append("/").append(c.get(Calendar.DAY_OF_MONTH)).append("/").append(c.get(Calendar.YEAR)).append(" ")
                .append(c.getTimeZone().getID()).toString();
    }
}
