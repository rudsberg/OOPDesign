import java.awt.*;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.
public class DrawPanel extends JPanel {
    private ImagesPositions imagesPositions = new ImagesPositions();

    void moveit(Positioner position, String modelName) {
        if (!imagesPositions.isVehicleHere(position))
            imagesPositions.addVehicleToList(position, modelName);
    }

    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    private void drawVehicles(Graphics g) {
        for (ImagePosition imagePosition : imagesPositions.getImagesPositionsList())
            if (imagePosition.isValid())
                drawVehicle(g, imagePosition);
    }

    private void drawVehicle(Graphics g, ImagePosition imagePosition) {
        g.drawImage(imagePosition.getImage(), (int) imagePosition.getPosition().getX(), (int) imagePosition.getPosition().getY(), null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawVehicles(g);
    }
}