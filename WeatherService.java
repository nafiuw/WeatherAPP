import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherService {

    private static final String API_KEY =
            "d366fe7193cfd51f658e8fea5d730c6b";

    public WeatherData getWeather(String city)
            throws Exception {

        String urlString =
                "https://api.openweathermap.org/data/2.5/weather?q="
                        + city
                        + "&appid="
                        + API_KEY
                        + "&units=metric";

        URL url = new URL(urlString);

        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()));

        StringBuilder response =
                new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        JSONObject json =
                new JSONObject(response.toString());

        String cityName =
                json.getString("name");

        JSONObject main =
                json.getJSONObject("main");

        JSONObject wind =
                json.getJSONObject("wind");

        JSONArray weatherArray =
                json.getJSONArray("weather");

        JSONObject weather =
                weatherArray.getJSONObject(0);

        return new WeatherData(
                cityName,
                main.getDouble("temp"),
                main.getInt("humidity"),
                wind.getDouble("speed"),
                weather.getString("main"),
                weather.getString("icon")
        );
    }

    public ArrayList<ForecastData> getForecast(
            String city)
            throws Exception {

        ArrayList<ForecastData> forecastList =
                new ArrayList<>();

        String urlString =
                "https://api.openweathermap.org/data/2.5/forecast?q="
                        + city
                        + "&appid="
                        + API_KEY
                        + "&units=metric";

        URL url = new URL(urlString);

        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()));

        StringBuilder response =
                new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        JSONObject json =
                new JSONObject(response.toString());

        JSONArray list =
                json.getJSONArray("list");

        for (int i = 0;
             i < list.length();
             i += 8) {

            JSONObject item =
                    list.getJSONObject(i);

            String date =
                    item.getString("dt_txt");

            double temp =
                    item.getJSONObject("main")
                            .getDouble("temp");

            String condition =
                    item.getJSONArray("weather")
                            .getJSONObject(0)
                            .getString("main");

            forecastList.add(
                    new ForecastData(
                            date,
                            temp,
                            condition
                    )
            );
        }

        return forecastList;
    }
}