package ro.shiftleft.apiTestingDemo.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class CollectionUtils {

    private static final Random RANDOM = new Random();

    // Selects a single random item from a collection
    public static <T> T getRandomItem(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("Collection must not be null or empty");
        }
        int randomIndex = RANDOM.nextInt(collection.size());
        return new ArrayList<>(collection).get(randomIndex);
    }

    // Selects a specified number of random items from a collection
    public static <T> List<T> getRandomItems(Collection<T> collection, int count) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("Collection must not be null or empty");
        }
        if (count < 0 || count > collection.size()) {
            throw new IllegalArgumentException("Count must be between 0 and the size of the collection");
        }
        List<T> list = new ArrayList<>(collection);
        List<T> randomItems = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int randomIndex = RANDOM.nextInt(list.size());
            randomItems.add(list.remove(randomIndex));
        }
        return randomItems;
    }
}
