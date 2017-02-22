# WebApp showing the weather of London or Hong-Kong

## Configuration

The application uses OpenWeatherMap API in order to get weather information from the selected city.
Please register for an API KEY at <http://openweathermap.org/api>.

1. After getting the API KEY, navigate to &lt;project-home&gt;src/main/webapp/WEB-INF/web.xml
2. Place the API KEY under &lt;param-name&gt;owmApiKey&lt;/param-name&gt; between &lt;param-value&gt;&lt;/param-value&gt;

## Start
```bash
mvn jetty:run
```
Visit <http://localhost:8080/weather-webapp>

## TO-DO

* Use encrypted config for api key
* Create JUnit test
* Implement JS based form submission
* Use JSON for servlet response and process it client side
* Implement sunrise and sunset and reorganize servlet code
* Add meaningful error messages and configure logging