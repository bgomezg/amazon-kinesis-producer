package com.amazonaws.services.kinesis.producer.sample;

import java.util.Date;

public class Record{

	private String channel;
	private String deviceId;
	private Date currentDate;

	public Record() {
	}

	public Record(String channel, String deviceId, Date currentDate) {
		super();
		this.channel = channel;
		this.deviceId = deviceId;
		this.currentDate = currentDate;
	}

	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

}