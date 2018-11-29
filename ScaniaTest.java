import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScaniaTest {
    private final List<Scania> scanias = new ArrayList<Scania>(
            Arrays.asList(
                    new Scania(400, Color.ORANGE, 2, 50, 50, Direction.LEFT, 50, 20000)
            ));

    @Test
    void setTruckBedAngle() {
        for (Scania s : scanias) {
            double maxAngle = s.getMaxTruckBedAngle();
            double minAngle = s.getMinTruckBedAngle();
            double middleAngle = maxAngle / 2;
            double tempAngle;

            s.stopEngine();
            s.setTruckBedAngle(maxAngle + 1);
            assertEquals(maxAngle, s.getTruckBedAngle());

            s.stopEngine();
            s.setTruckBedAngle(minAngle - 1);
            assertEquals(minAngle, s.getMinTruckBedAngle());

            s.stopEngine();
            s.setTruckBedAngle(maxAngle - 1);
            assertEquals(maxAngle - 1, s.getTruckBedAngle());

            s.stopEngine();
            s.setTruckBedAngle(minAngle + 1);
            assertEquals(minAngle + 1, s.getTruckBedAngle());

            s.setTruckBedAngle(minAngle);
            TestHelperFunctions.getSpeedTo(s, s.getEnginePower() / 2);
            s.setTruckBedAngle(middleAngle);
            assertEquals(s.getMinTruckBedAngle(), s.getTruckBedAngle());
        }

    }

    @Test
    void startEngine() {
        for (Scania s : scanias) {
            s.stopEngine();
            s.setTruckBedAngle(s.getMinTruckBedAngle());
            s.startEngine();
            assertEquals(0.1,s.getCurrentSpeed());
            s.stopEngine();
            s.setTruckBedAngle(s.getMaxTruckBedAngle());
            s.startEngine();
            assertEquals(0,s.getCurrentSpeed());

        }
    }
}