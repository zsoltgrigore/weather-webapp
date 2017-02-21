package com.grigore.weatherwebapp.servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;

@SuppressWarnings("serial")
public class WeatherServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding(StandardCharsets.UTF_8.toString());
		
		OpenWeatherMap owm = new OpenWeatherMap("");
		CurrentWeather cw = owm.currentWeatherByCityName("London");
		
		if (cw != null) {
			resp.getWriter().append(cw.getCityName());
		} else {
			
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
	
}
