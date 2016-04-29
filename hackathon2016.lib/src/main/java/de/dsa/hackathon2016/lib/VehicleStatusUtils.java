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
 * Utils class that implements the required method to obtain the vehicle status from the server.
 */
public final class VehicleStatusUtils {
    /** Private constructor. */
    private VehicleStatusUtils() {
    }

    /**
     * Method that requests the last vehicle status of the vehicle with the given ID.
     * @param vehicleId The ID of the vehicle to get the status from.
     * @param vehicleStatusReceiver The {@link VehicleStatusReceiver} that will obtain the response
     *                              with the vehicle status.
     */
    public static void getLastVehicleStatus(String vehicleId, VehicleStatusReceiver vehicleStatusReceiver) {
        (new GetVehicleStatusAsyncTask(vehicleId, vehicleStatusReceiver)).execute();
    }
}
