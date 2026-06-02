import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class WeatherApp extends JFrame {

    private JTextField cityField;

    private JLabel weatherIconLabel;
    private JLabel cityLabel;
    private JLabel tempLabel;
    private JLabel humidityLabel;
    private JLabel windLabel;
    private JLabel conditionLabel;
    private JLabel updatedLabel;

    private JTextArea forecastArea;
    private JTextArea historyArea;

    private JComboBox<String> unitSelector;

    private WeatherService weatherService;
    private SearchHistoryManager historyManager;

    public WeatherApp() {

        weatherService = new WeatherService();
        historyManager = new SearchHistoryManager();

        setTitle("SkyCast Pro");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        applyDynamicBackground();

        setLayout(new BorderLayout());

        buildTopPanel();
        buildCenterPanel();
        buildRightPanel();

        setVisible(true);
    }

    private void buildTopPanel() {

        JPanel topPanel = new JPanel();

        JLabel title =
                new JLabel("SkyCast Pro");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24));

        cityField =
                new JTextField(20);

        JButton searchButton =
                new JButton("Search");

        unitSelector =
                new JComboBox<>(
                        new String[]{
                                "Celsius",
                                "Fahrenheit"
                        });

        topPanel.add(title);
        topPanel.add(
                new JLabel(" City: "));
        topPanel.add(cityField);
        topPanel.add(searchButton);
        topPanel.add(unitSelector);

        add(topPanel,
                BorderLayout.NORTH);

        searchButton.addActionListener(
                e -> searchWeather());
    }

    private void buildCenterPanel() {

        JPanel centerPanel =
                new JPanel();

        centerPanel.setLayout(
                new GridLayout(8,1));

        weatherIconLabel =
                new JLabel();

        weatherIconLabel.setHorizontalAlignment(
                SwingConstants.CENTER);

        cityLabel =
                new JLabel(
                        "City");

        tempLabel =
                new JLabel(
                        "Temperature");

        humidityLabel =
                new JLabel(
                        "Humidity");

        windLabel =
                new JLabel(
                        "Wind Speed");

        conditionLabel =
                new JLabel(
                        "Condition");

        updatedLabel =
                new JLabel(
                        "Last Updated");

        forecastArea =
                new JTextArea(8,20);

        forecastArea.setEditable(false);

        centerPanel.add(weatherIconLabel);
        centerPanel.add(cityLabel);
        centerPanel.add(tempLabel);
        centerPanel.add(humidityLabel);
        centerPanel.add(windLabel);
        centerPanel.add(conditionLabel);
        centerPanel.add(updatedLabel);

        centerPanel.add(
                new JScrollPane(
                        forecastArea));

        add(centerPanel,
                BorderLayout.CENTER);
    }

    private void buildRightPanel() {

        historyArea =
                new JTextArea();

        historyArea.setEditable(false);

        JScrollPane historyPane =
                new JScrollPane(
                        historyArea);

        historyPane.setPreferredSize(
                new Dimension(
                        300,
                        600));

        add(historyPane,
                BorderLayout.EAST);
    }

    private void searchWeather() {

        String city =
                cityField.getText().trim();

        if(city.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a city name");

            return;
        }

        try {

            WeatherData weather =
                    weatherService.getWeather(
                            city);

            double temp =
                    weather.getTemperature();

            String unit =
                    unitSelector.getSelectedItem()
                            .toString();

            if(unit.equals(
                    "Fahrenheit")) {

                temp =
                        (temp * 9 / 5) + 32;
            }

            cityLabel.setText(
                    "City: "
                            + weather.getCity());

            tempLabel.setText(
                    "Temperature: "
                            + String.format(
                            "%.1f",
                            temp)
                            + (unit.equals(
                            "Fahrenheit")
                            ? " °F"
                            : " °C"));

            humidityLabel.setText(
                    "Humidity: "
                            + weather.getHumidity()
                            + "%");

            windLabel.setText(
                    "Wind Speed: "
                            + weather.getWindSpeed()
                            + " m/s");

            conditionLabel.setText(
                    "Condition: "
                            + weather.getCondition());

            updatedLabel.setText(
                    "Last Updated: "
                            + LocalDateTime.now()
                            .format(
                                    DateTimeFormatter.ofPattern(
                                            "yyyy-MM-dd HH:mm:ss")));

            updateWeatherIcon(
                    weather.getCondition());

            updateForecast(city);

            historyManager.addSearch(
                    city);

            updateHistory();

        }
        catch(Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "City not found or API error");
        }
    }

    private void updateForecast(
            String city)
            throws Exception {

        forecastArea.setText(
                "5-Day Forecast\n\n");

        ArrayList<ForecastData>
                forecasts =
                weatherService.getForecast(
                        city);

        for(ForecastData forecast
                : forecasts) {

            forecastArea.append(
                    forecast.getDate()
                            + " | "
                            + String.format(
                            "%.1f",
                            forecast.getTemperature())
                            + "°C | "
                            + forecast.getCondition()
                            + "\n");
        }
    }

    private void updateHistory() {

        historyArea.setText("");

        for(String item :
                historyManager.getHistory()) {

            historyArea.append(
                    item + "\n");
        }
    }

    private void updateWeatherIcon(
            String condition) {

        String path;

        switch(condition.toLowerCase()) {

            case "clear":
                path =
                        "resources/sun.png";
                break;

            case "clouds":
                path =
                        "resources/cloud.png";
                break;

            case "rain":
                path =
                        "resources/rain.png";
                break;

            case "thunderstorm":
                path =
                        "resources/storm.png";
                break;

            case "snow":
                path =
                        "resources/snow.png";
                break;

            default:
                path =
                        "resources/mist.png";
        }

        ImageIcon icon =
                new ImageIcon(path);

        Image scaled =
                icon.getImage()
                        .getScaledInstance(
                                120,
                                120,
                                Image.SCALE_SMOOTH);

        weatherIconLabel.setIcon(
                new ImageIcon(
                        scaled));
    }

    private void applyDynamicBackground() {

        int hour =
                LocalTime.now()
                        .getHour();

        Color color;

        if(hour < 12) {

            color =
                    new Color(
                            135,
                            206,
                            235);

        }
        else if(hour < 18) {

            color =
                    new Color(
                            255,
                            223,
                            186);

        }
        else {

            color =
                    new Color(
                            25,
                            25,
                            112);
        }

        getContentPane()
                .setBackground(
                        color);
    }

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                WeatherApp::new);
    }
}