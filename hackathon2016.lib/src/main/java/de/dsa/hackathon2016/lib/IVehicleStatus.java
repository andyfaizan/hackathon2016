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
 * Interface that represents a vehicle status snapshot.
 */
public interface IVehicleStatus {
    /**
     * Gets the ID of the vehicle this status belongs to.
     * @return The vehicle ID.
     */
    String getVehicleId();

    /**
     * Gets the Timestamp when this status was recorded in the vehicle.
     * @return The timestamp with the format "yyyyMMddHHmmss".
     */
    String getTimestamp();

    /**
     * Gets the GPS latitude of the vehicle location.
     * @return The GPS latitude.
     */
    float getGpsLatitude();

    /**
     * Gets the GPS longitude of the vehicle location.
     * @return The GPS longitude.
     */
    float getGpsLongitude();

    /**
     * Gets the current vehicle speed.
     * @return The vehicle speed in km/h.
     */
    float getSpeed();

    /**
     * Gets the engine RPMs.
     * @return The RPMs of the engine.
     */
    int getEngineRpm();

    /**
     * Gets the current fuel level.
     * @return The fuel level from 0 (empty) to 1 (full).
     */
    float getFuelLevel();

    /**
     * Gets the coolant temperature of the vehicle.
     * @return The coolant temperature in Celsius.
     */
    float getCoolantTemp();

    /**
     * Gets the total fuel used by the engine.
     * @return The total fuel used by the engine in liters.
     */
    int getTotalFuelUsed();

    /**
     * Gets the remaining distance for the next service.
     * @return The remaining distance for the next service in km.
     */
    int getServiceDistance();

    /**
     * Gets the weight in the axle.
     * @return The axle weight in kg.
     */
    float getAxleWeight();

    /**
     * Gets the odometer value.
     * @return The odometer value in km.
     */
    float getOdometer();

    /**
     * Gets the battery voltage.
     * @return The battery voltage.
     */
    float getBatteryVoltage();
}
