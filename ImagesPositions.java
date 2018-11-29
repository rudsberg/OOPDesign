import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImagesPositions {
    private final List<ImagePosition> imagesPositionsList = new ArrayList<>();

    ImagesPositions() {
        initImagesOfAllVehicles();
    }

    List<ImagePosition> getImagesPositionsList() {
        return imagesPositionsList;
    }

    private void initImagesOfAllVehicles() {
        for (VehicleModel model : VehicleModel.values()) {
            addOnlyImageFor(model.name());
        }
    }

    boolean isVehicleHere(Positioner position) {
        for (ImagePosition imagePosition : imagesPositionsList) {
            Positioner pos = imagePosition.getPosition();
            if (position.equals(pos))
                return true;
        }

        return false;
    }

    void addVehicleToList(Positioner position, String modelName) {
        addPositionAndImageFor(modelName, position);
    }

    private void addPositionAndImageFor(String model, Positioner position) {
        try {
            imagesPositionsList.add(new ImagePosition(ImageIO.read(new File("src\\pics\\" + model + ".jpg")), position));
        } catch (IOException e) {
            throw new Error("Could not find image matching model: " + model);
        }
    }

    private void addOnlyImageFor(String model) {
        try {
            imagesPositionsList.add(new ImagePosition(ImageIO.read(new File("src\\pics\\" + model + ".jpg")), null));
        } catch (IOException e) {
        }
    }
}
