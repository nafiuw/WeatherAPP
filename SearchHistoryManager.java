import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SearchHistoryManager {

    private ArrayList<String> history =
            new ArrayList<>();

    public void addSearch(String city) {

        String time =
                LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(
                        "yyyy-MM-dd HH:mm:ss"));

        history.add(city + " | " + time);
    }

    public ArrayList<String> getHistory() {
        return history;
    }
}