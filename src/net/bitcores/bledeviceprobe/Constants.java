package net.bitcores.bledeviceprobe;

import java.util.HashMap;

public class Constants {
	
	public final static Integer STATE_DISCONNECTED = 0;
	public final static Integer STATE_SCANNING = 1;
	public final static Integer STATE_CONNECTING = 2;
	public final static Integer STATE_CONNECTED = 3;
	
	public static final HashMap<String, String> serviceMap = new HashMap<String, String>();
	public static final HashMap<String, String> characteristicMap = new HashMap<String, String>();
	
	static {
		//	information on the gatt services/characteristics is found at
		//	https://developer.bluetooth.org/gatt/services/Pages/ServicesHome.aspx
		
		//	SERVICES
		serviceMap.put("0x1800", "Generic Access");
		serviceMap.put("0x1801", "Generic Attribute");
		serviceMap.put("0x1802", "Immediate Alert");
		serviceMap.put("0x1803", "Link Loss");
		serviceMap.put("0x1805", "Current Time Service");
		serviceMap.put("0x1806", "Reference Time Update Service");
		serviceMap.put("0x1807", "Next DST Change Service");
		serviceMap.put("0x1808", "Glucose");
		serviceMap.put("0x1809", "Health Thermometer");
		serviceMap.put("0x180A", "Device Information");
		serviceMap.put("0x180D", "Heart Rate");
		serviceMap.put("0x180E", "Phone Alert Status Service");
		serviceMap.put("0x180F", "Battery Service");
		serviceMap.put("0x1810", "Blood Pressure");
		serviceMap.put("0x1811", "Alert Notification Service");
		serviceMap.put("0x1812", "Human Interface Device");
		serviceMap.put("0x1813", "Scan Parameters");
		serviceMap.put("0x1814", "Running Speed and Cadence");
		serviceMap.put("0x1816", "Cycling Speed and Cadence");
		serviceMap.put("0x1818", "Cycling Power");
		serviceMap.put("0x1819", "Location and Navigation");
		serviceMap.put("0x181A", "Environmental Sensing");
		serviceMap.put("0x181B", "Body Composition");
		serviceMap.put("0x181C", "User Data");
		serviceMap.put("0x181D", "Weight Scale");
		serviceMap.put("0x181E", "Bond Management");
		serviceMap.put("0x181F", "Continuous Glucose Monitoring");
		serviceMap.put("0x1820", "Internet Protocol Support");
		
		
		// CHARACTERISTICS
		characteristicMap.put("0x2A00", "Device Name");
		characteristicMap.put("0x2A01", "Appearance");
		characteristicMap.put("0x2A02", "Peripheral Privacy Flag");
		characteristicMap.put("0x2A03", "Reconnection Address");
		characteristicMap.put("0x2A04", "Peripheral Preferred Connection Parameters");
		
		characteristicMap.put("0x2A05", "Service Changed");
		
		characteristicMap.put("0x2A06", "Alert Level");
		
		characteristicMap.put("0x2A07", "Tx Power Level");
		
		characteristicMap.put("0x2A2B", "Current Time");
		characteristicMap.put("0x2A0F", "Local Time Information");
		characteristicMap.put("0x2A14", "Reference Time Information");
		
		characteristicMap.put("0x2A16", "Time Update Control Point");
		characteristicMap.put("0x2A17", "Update State");
		
		characteristicMap.put("0x2A11", "Time with DST");
		
		characteristicMap.put("0x2A18", "Glucose Measurement");
		characteristicMap.put("0x2A34", "Glucose Measurement Context");
		characteristicMap.put("0x2A51", "Glucose Feature");
		characteristicMap.put("0x2A52", "Record Access Control Point");
		
		characteristicMap.put("0x2A1C", "Temperature Measurement");
		characteristicMap.put("0x2A1D", "Temperature Type");
		characteristicMap.put("0x2A1E", "Intermediate Temperature");
		characteristicMap.put("0x2A21", "Measurement Interval");
		
		characteristicMap.put("0x2A29", "Manufacturer Name String");
		characteristicMap.put("0x2A24", "Model Number String");
		characteristicMap.put("0x2A25", "Serial Number String");
		characteristicMap.put("0x2A27", "Hardware Revision String");
		characteristicMap.put("0x2A26", "Firmware Revision String");
		characteristicMap.put("0x2A28", "Software Revision String");
		characteristicMap.put("0x2A23", "System ID");
		characteristicMap.put("0x2A2A", "IEEE 11073-20601 Regulatory Certification Data List");
		characteristicMap.put("0x2A50", "PnP ID");
		
		characteristicMap.put("0x2A37", "Heart Rate Measurement");
		characteristicMap.put("0x2A38", "Body Sensor Location");
		characteristicMap.put("0x2A39", "Heart Rate Control Point");
		
		characteristicMap.put("0x2A3F", "Alert Status");
		characteristicMap.put("0x2A41", "Ringer Setting");
		characteristicMap.put("0x2A40", "Ringer Control Point");
		
		characteristicMap.put("0x2A19", "Battery Level");
		
		characteristicMap.put("0x2A35", "Blood Pressure Measurement");
		characteristicMap.put("0x2A36", "Intermediate Cuff Pressure");
		characteristicMap.put("0x2A49", "Blood Pressure Feature");
		
		characteristicMap.put("0x2A47", "Supported New Alert Category");
		characteristicMap.put("0x2A46", "New Alert");
		characteristicMap.put("0x2A48", "Supported Unread Alert Category");
		characteristicMap.put("0x2A45", "Unread Alert Status");
		characteristicMap.put("0x2A44", "Alert Notification Control Point");
		
		characteristicMap.put("0x2A4E", "Protocol Mode");
		characteristicMap.put("0x2A4D", "Report");
		characteristicMap.put("0x2A4B", "Report Map");
		characteristicMap.put("0x2A22", "Boot Keyboard Input Report");
		characteristicMap.put("0x2A32", "Boot Keyboard Output Report");
		characteristicMap.put("0x2A33", "Boot Mouse Input Report");
		characteristicMap.put("0x2A4A", "HID Information");
		characteristicMap.put("0x2A4C", "HID Control Point");
		
		characteristicMap.put("0x2A4F", "Scan Interval Window");
		characteristicMap.put("0x2A31", "Scan Refresh");
		
		characteristicMap.put("0x2A53", "RSC Measurement");
		characteristicMap.put("0x2A54", "RSC Feature");
		characteristicMap.put("0x2A5D", "Sensor Location");
		characteristicMap.put("0x2A55", "SC Control Point");
		
		characteristicMap.put("0x2A5B", "CSC Measurement");
		characteristicMap.put("0x2A5C", "CSC Feature");
		
		characteristicMap.put("0x2A63", "Cycling Power Measurement");	
		characteristicMap.put("0x2A65", "Cycling Power Feature");
		characteristicMap.put("0x2A64", "Cycling Power Vector");
		characteristicMap.put("0x2A66", "Cycling Power Control Point");
		
		characteristicMap.put("0x2A6A", "LN Feature");
		characteristicMap.put("0x2A67", "Location and Speed");
		characteristicMap.put("0x2A69", "Position Quality");
		characteristicMap.put("0x2A6B", "LN Control Point");
		characteristicMap.put("0x2A68", "Navigation");
		
		characteristicMap.put("0x2A7D", "Descriptor Value Changed");
		characteristicMap.put("0x2A73", "Apparent Wind Direction");
		characteristicMap.put("0x2A72", "Apparent Wind Speed");
		characteristicMap.put("0x2A7B", "Dew Point");
		characteristicMap.put("0x2A6C", "Elevation");
		characteristicMap.put("0x2A74", "Gust Factor");
		characteristicMap.put("0x2A7A", "Heat Index");
		characteristicMap.put("0x2A6F", "Humidity");
		characteristicMap.put("0x2A77", "Irradiance");
		characteristicMap.put("0x2A75", "Pollen Concentration");
		characteristicMap.put("0x2A78", "Rainfall");
		characteristicMap.put("0x2A6D", "Pressure");
		characteristicMap.put("0x2A6E", "Temperature");
		characteristicMap.put("0x2A71", "True Wind Direction");
		characteristicMap.put("0x2A70", "True Wind Speed");
		characteristicMap.put("0x2A76", "UV Index");
		characteristicMap.put("0x2A79", "Wind Chill");
		characteristicMap.put("0x2AA3", "Barometric Pressure Trend");
		characteristicMap.put("0x2A2C", "Magnetic Declination");
		characteristicMap.put("0x2AA0", "Magnetic Flux Density - 2D");
		characteristicMap.put("0x2AA1", "Magnetic Flux Density - 3D");
		
		characteristicMap.put("0x2A9B", "Body Composition Feature");
		characteristicMap.put("0x2A9C", "Body Composition Measurement");
		
		characteristicMap.put("0x2A8A", "First Name");
		characteristicMap.put("0x2A90", "Last Name");
		characteristicMap.put("0x2A87", "Email Address");
		characteristicMap.put("0x2A80", "Age");
		characteristicMap.put("0x2A85", "Date of Birth");
		characteristicMap.put("0x2A8C", "Gender");
		characteristicMap.put("0x2A98", "Weight");
		characteristicMap.put("0x2A8E", "Height");
		characteristicMap.put("0x2A96", "VO2 Max");
		characteristicMap.put("0x2A8D", "Heart Rate Max");
		characteristicMap.put("0x2A92", "Resting Heart Rate");
		characteristicMap.put("0x2A91", "Maximum Recommended Heart Rate");
		characteristicMap.put("0x2A7F", "Aerobic Threshold");
		characteristicMap.put("0x2A83", "Anaerobic Threshold");
		characteristicMap.put("0x2A93", "Sport Type for Aerobic and Anaerobic Thresholds");
		characteristicMap.put("0x2A86", "Date of Threshold Assessment");
		characteristicMap.put("0x2A97", "Waist Circumference");
		characteristicMap.put("0x2A8F", "Hip Circumference");
		characteristicMap.put("0x2A88", "Fat Burn Heart Rate Lower Limit");
		characteristicMap.put("0x2A89", "Fat Burn Heart Rate Upper Limit");
		characteristicMap.put("0x2A7E", "Aerobic Heart Rate Lower Limit");
		characteristicMap.put("0x2A84", "Aerobic Heart Rate Upper Limit");
		characteristicMap.put("0x2A81", "Anaerobic Heart Rate Lower Limit");
		characteristicMap.put("0x2A82", "Anaerobic Heart Rate Upper Limit");
		characteristicMap.put("0x2A8B", "Five Zone Heart Rate Limits");
		characteristicMap.put("0x2A94", "Three Zone Heart Rate Limits");
		characteristicMap.put("0x2A95", "Two Zone Heart Rate Limit");
		characteristicMap.put("0x2A99", "Database Change Increment");
		characteristicMap.put("0x2A9A", "User Index");
		characteristicMap.put("0x2A9F", "User Control Point");
		characteristicMap.put("0x2AA2", "Language");
		
		characteristicMap.put("0x2A9E", "Weight Scale Feature");
		characteristicMap.put("0x2A9D", "Weight Measurement");
		
		characteristicMap.put("0x2AA4", "Bond Management Control Point");
		characteristicMap.put("0x2AA5", "Bond Management Feature");
		
		characteristicMap.put("0x2AA7", "CGM Measurement");
		characteristicMap.put("0x2AA8", "CGM Feature");
		characteristicMap.put("0x2AA9", "CGM Status");
		characteristicMap.put("0x2AAA", "CGM Session Start Time");
		characteristicMap.put("0x2AAB", "CGM Session Run Time");
		characteristicMap.put("0x2A52", "Record Access Control Point");
		characteristicMap.put("0x2AAC", "CGM Specific Ops Control Point");

	}
}
