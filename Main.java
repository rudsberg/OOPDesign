import java.awt.*;

public class Main {
    public static void main(String args[]) {

        Saab95 saab95 = new Saab95(200, 0, Color.red, 5, 0, 0, Direction.DOWN, 2000);
        Volvo240 volvo240 = new Volvo240(200, 0, Color.red, 5, 15, 15, Direction.DOWN, 2000);
        volvo240.setsPositionToSameAs(saab95);
        System.out.println(saab95.getX() + " and " + saab95.getY());


        VolvoSemiTruck volvoSemiTruck =  new VolvoSemiTruck(200,0,Color.red,3,20,20,Direction.DOWN,2000);
        volvoSemiTruck.openTruckBed();
        volvoSemiTruck.loadItem(volvo240);
        volvoSemiTruck.closeTruckBed();
        volvoSemiTruck.startEngine();
        volvoSemiTruck.gas(1);
        System.out.println(volvoSemiTruck.getAmountItemLoaded());
        System.out.println((volvo240.getX() + volvo240.getY())==(volvoSemiTruck.getX()+volvoSemiTruck.getY()));
    }
}
