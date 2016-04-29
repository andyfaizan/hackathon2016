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
 * Interface to be implemented to manage the vehicle status when received from the server.
 */
public interface VehicleStatusReceiver {
    /**
     * Callback method called when receiving the vehicle status from the server.
     * @param vehicleStatus The {@link IVehicleStatus} instance containing the vehicle status
     *                      information.
     */
    void onStatusReceived(IVehicleStatus vehicleStatus);
}
