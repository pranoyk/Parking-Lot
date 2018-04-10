package com.step.bootcamp;

import java.util.ArrayList;

public enum ParkingMethod {
    CAPACITY{
        @Override
        public ParkingLot getLot(ArrayList<ParkingLot> availableLots) {
            return availableLots.stream().reduce((pl,lot)->{
                if(pl.compareCapacity(lot)) {
                    return lot;
                }
                return pl;
            }).get();
        }
    },
    VACANT_SPACE{
        @Override
        public ParkingLot getLot(ArrayList<ParkingLot> availableLots) {
            return availableLots.stream().reduce((pl,lot)->{
                if(pl.compareVacancy(lot)) {
                    return lot;
                }
                return pl;
            }).get();
        }
    },
    AVAILABILITY;

    public ParkingLot getLot(ArrayList<ParkingLot> availableLots) {
        return availableLots.get(0);
    }
}
