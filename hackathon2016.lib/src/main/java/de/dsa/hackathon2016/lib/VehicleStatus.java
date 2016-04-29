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

/**
 * The object that implements the {@link IVehicleStatus}.
 */
class VehicleStatus implements IVehicleStatus {

    private String mTimestamp;
    private float mGpsLatitude;
    private float mGpsLongitude;
    private float mSpeed;
    private int mEngineRpm;
    private float mFuelLevel;
    private float mCoolantTemp;
    private String mVehicleId;
    private int mTotalFuelUsed;
    private int mServiceDistance;
    private float mAxleWeight;
    private float mOdometer;
    private float mBatteryVoltage;

    /**
     * Sets the vehicle ID.
     * @param vehicleId The vehicle ID.
     */
    void setVehicleId(String vehicleId) {
        mVehicleId = vehicleId;
    }

    /**
     * Sets the timestamp.
     * @param timestamp The timestamp has the format "yyyyMMddHHmmss".
     */
    void setTimestamp(String timestamp) {
        mTimestamp = timestamp;
    }

    /**
     * Sets the GPS latitude.
     * @param gpsLatitude The GPS latitude.
     */
    void setGpsLatitude(float gpsLatitude) {
        mGpsLatitude = gpsLatitude;
    }

    /**
     * Sets the GPS longitude.
     * @param gpsLongitude The GPS longitude.
     */
    void setGpsLongitude(float gpsLongitude) {
        mGpsLongitude = gpsLongitude;
    }

    /**
     * Sets the vehicle speed.
     * @param speed The vehicle speed in km/h. 
     */
    void setSpeed(float speed) {
        mSpeed = speed;
    }

    /**
     * Sets the engine RPMs.
     * @param engineRpm The engine RPMs.
     */
    void setEngineRpm(int engineRpm) {
        mEngineRpm = engineRpm;
    }

    /**
     * Sets the fuel level.
     * @param fuelLevel The fuel level from 0 (empty) to 1 (full).
     */
    void setFuelLevel(float fuelLevel) {
        mFuelLevel = fuelLevel;
    }

    /**
     * Sets the coolant temperature.
     * @param coolantTemp The coolant temperature in Celsius.
     */
    void setCoolantTemp(float coolantTemp) {
        mCoolantTemp = coolantTemp;
    }

    /**
     * Sets the total fuel used by the engine.
     * @param totalFuelUsed The total fuel used by the engine in liters.
     */
    void setTotalFuelUsed(int totalFuelUsed) {
        mTotalFuelUsed = totalFuelUsed;
    }

    /**
     * Sets the remaining distance for the next service.
     * @param serviceDistance  The remaining distance for the next service in km.
     */
    void setServiceDistance(int serviceDistance) {
        mServiceDistance = serviceDistance;
    }

    /**
     * Sets the weight in the axle.
     * @param axleWeight  The axle weight in kg.
     */
    void setAxleWeight(float axleWeight) {
        mAxleWeight = axleWeight;
    }

    /**
     * Sets the odometer value.
     * @param odometer The odometer value in km.
     */
    void setOdometer(float odometer) {
        mOdometer = odometer;
    }

    /**
     * Sets the battery voltage.
     * @param batteryVoltage The battery voltage.
     */
    void setBatteryVoltage(float batteryVoltage) {
        mBatteryVoltage = batteryVoltage;
    }

    @Override
    public String getVehicleId() {
        return mVehicleId;
    }

    @Override
    public String getTimestamp() {
        return mTimestamp;
    }

    @Override
    public float getGpsLatitude() {
        return mGpsLatitude;
    }

    @Override
    public float getGpsLongitude() {
        return mGpsLongitude;
    }

    @Override
    public float getSpeed() {
        return mSpeed;
    }

    @Override
    public int getEngineRpm() {
        return mEngineRpm;
    }

    @Override
    public float getFuelLevel() {
        return mFuelLevel;
    }

    @Override
    public float getCoolantTemp() {
        return mCoolantTemp;
    }

    @Override
    public int getTotalFuelUsed() {
        return mTotalFuelUsed;
    }

    @Override
    public int getServiceDistance() {
        return mServiceDistance;
    }

    @Override
    public float getAxleWeight() {
        return mAxleWeight;
    }

    @Override
    public float getOdometer() {
        return mOdometer;
    }

    @Override
    public float getBatteryVoltage() {
        return mBatteryVoltage;
    }

    @Override
    public String toString() {
        String string = super.toString();
        string += " vehicleId=" + getVehicleId();
        string += " timestamp=" + getTimestamp();
        string += " gpsLatitude=" + getGpsLatitude();
        string += " gpsLongitude=" + getGpsLongitude();
        string += " speed=" + getSpeed();
        string += " engineRpm=" + getEngineRpm();
        string += " fuelLevel=" + getFuelLevel();
        string += " coolantTemp=" + getCoolantTemp();
        string += " totalFuelUsed=" + getTotalFuelUsed();
        string += " serviceDistance=" + getServiceDistance();
        string += " axleWeight=" + getAxleWeight();
        string += " odometer=" + getOdometer();
        string += " batteryVoltage=" + getBatteryVoltage();
        return string;
    }
}
