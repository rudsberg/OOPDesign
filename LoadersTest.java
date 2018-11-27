import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoadersTest {
    List<SemiTruck> loaders = new ArrayList<>();
    List<CarFerry> carFerries = new ArrayList<>();

    LoadersTest() {
        loaders.add(new VolvoSemiTruck(500, 60, Color.cyan, 2, 0, 0, Direction.DOWN, 15_000));
        carFerries.add(new CarFerry(4000, 5, Color.green, 50, 50, Direction.RIGHT, 50, 7000, 50_000, 40));
    }

    @Test
    void loadVehicle() {
        for (SemiTruck semi : loaders) {
            loadSpecificVehicle(semi);
        }

        for (CarFerry carFerry : carFerries) {
            loadSpecificVehicle(carFerry);
        }
    }

    private <T extends Vehicle & Loadables> void loadSpecificVehicle(T loader) {
        int amountLoaded = loader.getAmountItemLoaded();
        Vehicle car = createCar(loader.getX(), loader.getY(), 2000);
        Vehicle awayCar = createCar(loader.getX() + 2, loader.getY() + 3, 2000);
        Vehicle oversizedCar = createCar(loader.getX(), loader.getY(), loader.getMaxItemWeight() + 100);

        // Load two cars normal way
        getReadyToLoad(loader);
        loader.loadItem(car);
        assertEquals(amountLoaded + 1, loader.getAmountItemLoaded());
        loader.loadItem(car);
        assertEquals(amountLoaded + 2, loader.getAmountItemLoaded());

        // Try to load with closed truck bed
        amountLoaded = loader.getAmountItemLoaded();
        loader.closeTruckBed();
        loader.loadItem(car);
        assertEquals(amountLoaded, loader.getAmountItemLoaded());

        // Try to load car too far away
        getReadyToLoad(loader);
        amountLoaded = loader.getAmountItemLoaded();
        TestHelperFunctions.moveVehicleDistanceAway(car, loader.getMaxDistanceToLoad() + 10);
        loader.loadItem(car);
        assertEquals(amountLoaded, loader.getAmountItemLoaded());

        // Try to load too heavy car
        getReadyToLoad(loader);
        amountLoaded = loader.getAmountItemLoaded();
        loader.loadItem(oversizedCar);
        assertEquals(amountLoaded, loader.getAmountItemLoaded());

        // Try to load itself
        getReadyToLoad(loader);
        amountLoaded = loader.getAmountItemLoaded();
        loader.loadItem(loader);
        assertEquals(amountLoaded, loader.getAmountItemLoaded());

        // Check that loaded car has same position as loader
        getReadyToLoad(loader);
        getCapacityTo(loader, loader.getAmountItemLoaded() / 2);
        loader.loadItem(awayCar);
        assertEquals(awayCar.getX(), loader.getX());
    }

    @Test
    void unLoadVehicle() {
        for (SemiTruck truck : loaders) {
            unSpecificLoadVehicle(truck);
        }

        for (CarFerry carFerry : carFerries) {
            unSpecificLoadVehicle(carFerry);
        }
    }

    private <V extends Vehicle & Loadables> void unSpecificLoadVehicle(V truck) {
        int amountLoaded;
        Vehicle car = createCar(truck.getX(), truck.getY(), 2000);

        // With two cars as starting point, try to unload three times
        getReadyToLoad(truck);
        getCapacityTo(truck, 0);
        truck.loadItem(car);
        truck.loadItem(car);
        amountLoaded = truck.getAmountItemLoaded();
        truck.unLoadItem();
        assertEquals(amountLoaded - 1, truck.getAmountItemLoaded());
        truck.unLoadItem();
        assertEquals(amountLoaded - 2, truck.getAmountItemLoaded());
        truck.unLoadItem();
        assertEquals(amountLoaded - 2, truck.getAmountItemLoaded());

        // Try to load with truck bed closed
        getReadyToLoad(truck);
        truck.loadItem(car);
        amountLoaded = truck.getAmountItemLoaded();
        truck.closeTruckBed();
        truck.unLoadItem();
        assertEquals(amountLoaded, truck.getAmountItemLoaded());
    }

    @Test
    void isFull() {
        for (SemiTruck loader : loaders) {
            isSpecificFull(loader);
        }

        for (CarFerry carFerry : carFerries) {
            isSpecificFull(carFerry);
        }
    }

    private <V extends Vehicle & Loadables> void isSpecificFull(V loader) {
        int maxCapacaty = loader.getMaxLoadCapacity();
        loader.openTruckBed();

        getCapacityTo(loader, maxCapacaty);
        assertTrue(loader.isFull());

        getCapacityTo(loader, 0);
        assertFalse(loader.isFull());

        getCapacityTo(loader, maxCapacaty - 1);
        assertFalse(loader.isFull());

        getCapacityTo(loader, maxCapacaty / 2);
        assertFalse(loader.isFull());
    }

    @Test
    void isTruckBedOpen() {
        for (SemiTruck loader : loaders) {
            isTruckBedOpenSpecifik(loader);
        }

        for (CarFerry carFerry : carFerries) {
            isTruckBedOpenSpecifik(carFerry);
        }
    }

    private <V extends Vehicle & Loadables> void isTruckBedOpenSpecifik(V loader) {
        loader.openTruckBed();
        assertTrue(loader.isTruckBedOpen());

        loader.closeTruckBed();
        assertTrue(loader.isTruckBedClosed());
    }

    private <V extends Vehicle & Loadables> void getReadyToLoad(V loader) {
        loader.stopEngine();
        loader.openTruckBed();
    }

    private <V extends Vehicle & Loadables> void getCapacityTo(V loader, int num) {
        Vehicle car = createCar(loader.getX(), loader.getY(), 2000);
        do {
            if (loader.getAmountItemLoaded() <= num)
                loader.loadItem(car);
            else
                loader.unLoadItem();
        } while (loader.getAmountItemLoaded() != num);
    }

    private Vehicle createCar(double x, double y, int weight) {
        return new Volvo240(300, 30, Color.red, 4, x, y, Direction.DOWN, weight);
    }
}