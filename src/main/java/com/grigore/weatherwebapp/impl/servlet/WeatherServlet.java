package com.grigore.weatherwebapp.impl.servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;

/**
 * Serving weather data based on the city parameter's value
 * 
 * @author agrigore
 *
 */
@SuppressWarnings("serial")
public class WeatherServlet extends HttpServlet {
	
	private static final String OWM_API_KEY = "owmApiKey";
	private static final String PARAM_CITY= "city";
	
	private String owmApiKey = null;
	
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init();
		owmApiKey = servletConfig.getInitParameter(OWM_API_KEY);
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding(StandardCharsets.UTF_8.toString());
		
		OpenWeatherMap owm = new OpenWeatherMap(owmApiKey);
		CurrentWeather cw = owm.currentWeatherByCityName(req.getParameter(PARAM_CITY));
		
		if (cw != null) {
			resp.getWriter().append(getCurrentDate() + "\n");
			resp.getWriter().append(cw.getCityName() + "\n");
			resp.getWriter().append(cw.getWeatherInstance(0).getWeatherDescription() + "\n");
			float minTemp = cw.getMainInstance().getMinTemperature();
			float maxTemp = cw.getMainInstance().getMaxTemperature();
			
			resp.getWriter().append(minTemp + "\n");
			resp.getWriter().append(maxTemp + "\n");
			
			resp.getWriter().append(fahrenheitToCelsius(minTemp) + "\n");
			resp.getWriter().append(fahrenheitToCelsius(maxTemp) + "\n");
		} else {
			
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
	
	private String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy, hh:mm aaa");
		return sdf.format(new GregorianCalendar().getTime());
	}
	
	private float fahrenheitToCelsius(float fahrenheit) {
		return ((fahrenheit - 32)*5)/9;
	}
	
}
