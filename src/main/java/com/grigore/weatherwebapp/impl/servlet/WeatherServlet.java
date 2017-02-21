package com.grigore.weatherwebapp.impl.servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;

@SuppressWarnings("serial")
public class WeatherServlet extends HttpServlet {
	
	private String owmApiKey = null;
	
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init();
		owmApiKey = servletConfig.getInitParameter("owmApiKey");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding(StandardCharsets.UTF_8.toString());
		
		OpenWeatherMap owm = new OpenWeatherMap(owmApiKey);
		CurrentWeather cw = owm.currentWeatherByCityName(req.getParameter("city"));
		
		if (cw != null) {
			resp.getWriter().append(getCurrentDate() + "\n");
			resp.getWriter().append(cw.getCityName() + "\n");
		} else {
			
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
	
	private String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy, hh:mm aaa");
		return sdf.format(new GregorianCalendar().getTime());
	}
	
}
