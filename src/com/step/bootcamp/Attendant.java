package com.step.bootcamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Attendant {
  private final ArrayList<ParkingLot> parkingLots;
  private final ArrayList<ParkingLot> availableLots;
  private ParkingMethod typeOfParking;

  public Attendant(ParkingMethod typeOfParking) {
    this.typeOfParking = typeOfParking;
    parkingLots = new ArrayList<>();
    availableLots = new ArrayList<>();
  }

  public void add(ParkingLot parkingLot) {
    parkingLots.add(parkingLot);
    availableLots.add(parkingLot);
  }

  public Object park(Vehicle vehicle) throws CannotParkException {
    if(availableLots.isEmpty()) {
      throw new CannotParkException("Parking is full");
    }
    ParkingLot availableLot = typeOfParking.getLot(availableLots);
    Object token = availableLot.park(vehicle);
    if(availableLot.isFull()){
      availableLots.remove(availableLot);
    }
    return token;
  }

  public Vehicle checkoutFor(Object token) throws VehicleNotFoundException {
    for (ParkingLot parkingLot : parkingLots) {
      try {
        Vehicle car = parkingLot.checkoutFor(token);
        if(!availableLots.contains(parkingLot)) {
          availableLots.add(parkingLot);
          return car;
        }
      } catch (VehicleNotFoundException ignored){}
    }
    throw new VehicleNotFoundException();
  }
}
