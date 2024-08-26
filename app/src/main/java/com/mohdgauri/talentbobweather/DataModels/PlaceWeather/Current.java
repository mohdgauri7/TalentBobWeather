package com.mohdgauri.talentbobweather.DataModels.PlaceWeather;

public class Current{
	private double feelslikeC;
	private double feelslikeF;
	private int windDegree;
	private double windchillF;
	private double windchillC;
	private int lastUpdatedEpoch;
	private double temp_c;
	private double tempF;
	private int cloud;
	private double windKph;
	private double windMph;
	private int humidity;
	private double dewpointF;
	private double uv;
	private String lastUpdated;
	private double heatindexF;
	private double dewpointC;
	private int isDay;
	private double precipIn;
	private double heatindexC;
	private String windDir;
	private double gustMph;
	private double pressureIn;
	private double gustKph;
	private double precipMm;
	private Condition condition;
	private double visKm;
	private double pressureMb;
	private double visMiles;

	public double getFeelslikeC(){
		return feelslikeC;
	}

	public double getFeelslikeF(){
		return feelslikeF;
	}

	public int getWindDegree(){
		return windDegree;
	}

	public double getWindchillF(){
		return windchillF;
	}

	public double getWindchillC(){
		return windchillC;
	}

	public int getLastUpdatedEpoch(){
		return lastUpdatedEpoch;
	}

	public double getTempC(){
		return temp_c;
	}

	public double getTempF(){
		return tempF;
	}

	public int getCloud(){
		return cloud;
	}

	public double getWindKph(){
		return windKph;
	}

	public double getWindMph(){
		return windMph;
	}

	public int getHumidity(){
		return humidity;
	}

	public double getDewpointF(){
		return dewpointF;
	}

	public double getUv(){
		return uv;
	}

	public String getLastUpdated(){
		return lastUpdated;
	}

	public double getHeatindexF(){
		return heatindexF;
	}

	public double getDewpointC(){
		return dewpointC;
	}

	public int getIsDay(){
		return isDay;
	}

	public double getPrecipIn(){
		return precipIn;
	}

	public double getHeatindexC(){
		return heatindexC;
	}

	public String getWindDir(){
		return windDir;
	}

	public double getGustMph(){
		return gustMph;
	}

	public double getPressureIn(){
		return pressureIn;
	}

	public double getGustKph(){
		return gustKph;
	}

	public double getPrecipMm(){
		return precipMm;
	}

	public Condition getCondition(){
		return condition;
	}

	public double getVisKm(){
		return visKm;
	}

	public double getPressureMb(){
		return pressureMb;
	}

	public double getVisMiles(){
		return visMiles;
	}
}
