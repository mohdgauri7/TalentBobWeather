package com.mohdgauri.talentbobweather.DataModels.PlaceWeather;

public class ResponsePlaceWeather{
	private Current current;
	private Location location;

	public Current getCurrent(){
		return current;
	}

	public Location getLocation(){
		return location;
	}
}
