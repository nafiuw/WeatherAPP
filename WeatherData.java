public class WeatherData {
    private String city;
    private double temperature;
    private int humidity;
    private double windSpeed;
    private String condition;
    private String iconCode;

    public WeatherData(String city, double temperature, int humidity,
                       double windSpeed, String condition, String iconCode) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.condition = condition;
        this.iconCode = iconCode;
    }

    public String getCity() { return city; }
    public double getTemperature() { return temperature; }
    public int getHumidity() { return humidity; }
    public double getWindSpeed() { return windSpeed; }
    public String getCondition() { return condition; }
    public String getIconCode() { return iconCode; }
}