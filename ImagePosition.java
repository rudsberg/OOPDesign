import java.awt.image.BufferedImage;

public class ImagePosition {
    private BufferedImage image;
    private Positioner position;

    public ImagePosition(BufferedImage image, Positioner position) {
        this.image = image;
        this.position = position;
    }

    public boolean isValid() {
        return image != null && position != null;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Positioner getPosition() {
        return position;
    }
}
