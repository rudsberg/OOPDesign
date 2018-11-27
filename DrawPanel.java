import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    // Just a single image, TODO: Generalize
    private ArrayList<Tuple<BufferedImage, String>> vehicleImagesAndModelNames = new ArrayList<>();

    ArrayList<Tuple<Positioner, BufferedImage>> positionsAndImages = new ArrayList<>();


    // TODO: Make this general for all cars
    void moveit(Positioner position, String modelName){
        if (!isVehicleInList(position))
            addCarToList(position, modelName);
    }

    private void addCarToList(Positioner position, String modelName) {
        BufferedImage vehicleImage = getVehicleImage(modelName);
        Tuple<Positioner, BufferedImage> positionAndImage = new Tuple(position, vehicleImage);
        positionsAndImages.add(positionAndImage);
    }

    private BufferedImage getVehicleImage(String modelName) {
        for (Tuple<BufferedImage, String> imgAndModelName : vehicleImagesAndModelNames)
            if (imgAndModelName.getSnd().equals(modelName))
                return imgAndModelName.getFst();

        throw new Error("Could not find image matching model: " + modelName);
    }

    private boolean isVehicleInList(Positioner position) {
        for (Tuple<Positioner, BufferedImage> positionAndImage : positionsAndImages)
            if (positionAndImage.equals(position))
                return true;

        return false;
    }


/*    private void initPositionsAndImages() {

    }*/

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "src\\pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            // Linux users need to modify \ to / in path string
            vehicleImagesAndModelNames.add(new Tuple(ImageIO.read(new File("src\\pics\\Volvo240.jpg")), "Volvo240"));
            vehicleImagesAndModelNames.add(new Tuple(ImageIO.read(new File("src\\pics\\Saab95.jpg")), "Saab95"));
            vehicleImagesAndModelNames.add(new Tuple(ImageIO.read(new File("src\\pics\\Scania.jpg")), "Scania"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    private void drawVehicles(Graphics g) {
        for (Tuple<Positioner, BufferedImage> positionAndImage : positionsAndImages) {
            int xPosition = (int) positionAndImage.getFst().getX();
            int yPosition = (int) positionAndImage.getFst().getY();
            BufferedImage image = positionAndImage.getSnd();

            g.drawImage(image, xPosition, yPosition, null);
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawVehicles(g);
    }
}