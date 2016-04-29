/************************************************************************
 *                                                                      *
 *  DDDD     SSSS    AAA        Daten- und Systemtechnik Aachen GmbH    *
 *  D   D   SS      A   A       Pascalstrasse 28                        *
 *  D   D    SSS    AAAAA       52076 Aachen-Oberforstbach, Germany     *
 *  D   D      SS   A   A       Telefon: +49 (0)2408 / 9492-0           *
 *  DDDD    SSSS    A   A       Telefax: +49 (0)2408 / 9492-92          *
 *                                                                      *
 *                                                                      *
 *  (c) Copyright by DSA - all rights reserved                          *
 *                                                                      *
 ************************************************************************
 *
 * Initial Creation:
 *    Author      pb
 *    Created on  29/03/2016
 *
 ************************************************************************/
package de.dsa.hackathon2016.lib;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * The {@link AsyncTask} used to get the vehicle status from the server and provide the result
 * on the given receiver.
 */
class GetVehicleStatusAsyncTask extends AsyncTask<Void, Void, IVehicleStatus> {

    private static final String TAG = GetVehicleStatusAsyncTask.class.getName();
    private final VehicleStatusReceiver mVehicleStatusReceiver;
    private final String mVehicleId;

    /**
     * The constructor.
     * @param vehicleId The ID of the vehicle to retrieve the data from.
     * @param vehicleStatusReceiver The receiver that will get the data back.
     */
    GetVehicleStatusAsyncTask(String vehicleId, VehicleStatusReceiver vehicleStatusReceiver) {
        mVehicleStatusReceiver = vehicleStatusReceiver;
        mVehicleId = vehicleId;
    }

    @Override
    protected IVehicleStatus doInBackground(Void... params) {
        String urlString = "https://hackathon:dsaHackathon2016@www.vpext.dsa-ac.de/fleetportal/events/data.csv?a=demo&u=hackathon&p=dsa&d=" + mVehicleId + "&l=1&vg=0&at=1";
        URL url;
        try {
            url = new URL(urlString);
            Log.d(TAG, "URL: " + urlString);
        } catch (MalformedURLException e) {
            Log.e(TAG, "Malformed URL: " + urlString, e);
            return null;
        }
        InputStream is = null;
        Reader reader = null;
        StringWriter writer = null;
        try {
            URLConnection urlConnection = url.openConnection();
            String userInfo = url.getUserInfo();
            if (userInfo != null) {
                String encoded = Base64.encodeToString(userInfo.getBytes("UTF-8"), Base64.NO_WRAP);
                urlConnection.setRequestProperty("Authorization", "Basic " + encoded);
            }
            is = urlConnection.getInputStream();
            writer = new StringWriter();
            IOUtils.copy(is, writer, "UTF-8");
            String receivedString = writer.toString();
            Log.d(TAG, "Received CSV: " + receivedString);
            reader = new StringReader(receivedString);
            CSVParser csvParser = CSVFormat.EXCEL.withHeader().parse(reader);
            List<CSVRecord> records = csvParser.getRecords();
            if (records.size() == 0) {
                Log.d(TAG, "No CSV records found.");
                return null;
            }
            VehicleStatus vehicleStatus = new VehicleStatus();
            CSVRecord csvRecord = records.get(0);

            DateTimeFormatter fromDateFormat = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
            DateTimeFormatter toDateFormat = DateTimeFormat.forPattern("yyyyMMddHHmmss");
            DateTime dateTime = fromDateFormat.parseDateTime(csvRecord.get("Date") + " " + csvRecord.get("Time"));
            vehicleStatus.setTimestamp(dateTime.toString(toDateFormat));

            vehicleStatus.setVehicleId(csvRecord.get("DeviceID"));
            vehicleStatus.setGpsLatitude(Float.parseFloat(csvRecord.get("Latitude")));
            vehicleStatus.setGpsLongitude(Float.parseFloat(csvRecord.get("Longitude")));
            vehicleStatus.setSpeed(Float.parseFloat(csvRecord.get("Speed")));
            vehicleStatus.setEngineRpm(Integer.parseInt(csvRecord.get("engineRpm")));
            vehicleStatus.setFuelLevel(Float.parseFloat(csvRecord.get("fuelLevel")));
            vehicleStatus.setCoolantTemp(Float.parseFloat(csvRecord.get("coolantTemp")));
            vehicleStatus.setTotalFuelUsed(Integer.parseInt(csvRecord.get("engineTotalFuelUsed")));
            vehicleStatus.setServiceDistance(Integer.parseInt(csvRecord.get("serviceDistance")));
            vehicleStatus.setAxleWeight(Float.parseFloat(csvRecord.get("axleWeight")));
            vehicleStatus.setOdometer(Float.parseFloat(csvRecord.get("odometerKM")));
            vehicleStatus.setBatteryVoltage(Float.parseFloat(csvRecord.get("batteryExtVolts")));
            Log.d(TAG, "Received vehicle status: " + vehicleStatus.toString());
            return vehicleStatus;
        } catch (IOException e) {
            Log.e(TAG, "Error on obtaining vehicle status from server.", e);
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(writer);
        }
        return null;
    }

    @Override
    protected void onPostExecute(IVehicleStatus vehicleStatus) {
        mVehicleStatusReceiver.onStatusReceived(vehicleStatus);
    }
}
