package telran.interviews;
import java.util.*;
import java.util.stream.Collectors;
public class Multicounters {
	HashMap<Object, Integer> items = new HashMap<>();
	TreeMap<Integer, Set<Object>> counters = new TreeMap<>();
public int addCounter(Object item) {
	//O[logN]
	int res = items.merge(item, 1, Integer::sum);
	if (res > 1) {
		removeItemFromCounters(item, res - 1);
	}
	counters.computeIfAbsent(res, k -> new HashSet<>()).add(item);
	
	return res;
}
public void removeItem(Object item) {
	//O[logN]
	int counter = getCounter(item);
	items.remove(item);
	Set<Object> itemsWithCounter = counters.get(counter);
	itemsWithCounter.remove(item);
	counters.remove(counter);
	
}
public int getCounter(Object item) {
	//O[1]
	int counter = items.getOrDefault(item, 0);
	if(counter == 0) {
		throw new NoSuchElementException();
	}
	return counter;
}
public Set<Object> getItemsByCounter(int counterFrom, int counterTo) {
	//O[logN]
	return counters.subMap(counterFrom, true, counterTo, true)
			.values().stream().flatMap(Set::stream).collect(Collectors.toSet());
}
public Set<Object> getItemsMaxCounter() {
	//O[logN]
	return counters.lastEntry().getValue();
}
private void removeItemFromCounters(Object item, int counter) {
	Set<Object> itemsWithCounter = counters.get(counter);
	itemsWithCounter.remove(item);
	counters.remove(counter);
}
}
