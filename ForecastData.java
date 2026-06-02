public class ForecastData {

    private String date;
    private double temperature;
    private String condition;

    public ForecastData(String date,
                        double temperature,
                        String condition) {

        this.date = date;
        this.temperature = temperature;
        this.condition = condition;
    }

    public String getDate() {
        return date;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getCondition() {
        return condition;
    }
}