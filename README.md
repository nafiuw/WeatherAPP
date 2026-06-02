# SkyCast Pro - Weather Information App

## Overview

SkyCast Pro is a Java Swing-based Weather Information Application that provides real-time weather information using the OpenWeatherMap API. The application allows users to search for weather conditions in any city worldwide and view current weather details, forecast information, weather icons, search history, and dynamic user interface themes.

This project was developed for the CS 1103 Weather Information App programming assignment.

---

## Features

### Real-Time Weather Information

* Current temperature
* Humidity level
* Wind speed
* Weather conditions
* City information

### Forecast Display

* 5-Day weather forecast
* Daily temperature overview
* Forecast conditions

### Weather Icons

* Sunny
* Cloudy
* Rainy
* Thunderstorm
* Snow
* Mist

### Unit Conversion

* Celsius
* Fahrenheit

### Search History

* Stores recent searches
* Includes timestamps
* Displays search history panel

### Dynamic Background Themes

* Morning theme
* Afternoon theme
* Evening/Night theme

### Error Handling

* Invalid city detection
* API connection error handling
* Empty input validation

---

## Technologies Used

* Java Swing
* OpenWeatherMap API
* JSON-Java Library (org.json)
* Java Collections Framework
* Java Date and Time API

---

## Project Structure

WeatherApp/

├── src/

│ ├── WeatherApp.java

│ ├── WeatherService.java

│ ├── WeatherData.java

│ ├── ForecastData.java

│ └── SearchHistoryManager.java

├── resources/

│ ├── sun.png

│ ├── cloud.png

│ ├── rain.png

│ ├── storm.png

│ ├── snow.png

│ └── mist.png

├── lib/

│ └── json.jar

└── README.md

---

## API

Weather data is retrieved using the OpenWeatherMap API.

API Provider:
https://openweathermap.org

---

## How To Compile

Linux:

javac -cp ".:lib/json.jar" src/*.java

---

## How To Run

Linux:

java -cp ".:lib/json.jar:src" WeatherApp

---

## Usage Instructions

1. Launch the application.
2. Enter a city name.
3. Click the Search button.
4. View current weather information.
5. View the forecast section.
6. Switch temperature units using the dropdown menu.
7. Review search history from the history panel.

---

## Future Improvements

* GPS-based location detection
* Interactive weather maps
* Weather alerts
* Dark mode toggle
* Weekly weather analytics


