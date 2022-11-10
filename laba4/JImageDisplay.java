import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent {
    private final BufferedImage image;


    public JImageDisplay(int width, int height) { //конструктор, который принимает параметры нового изображения и инициализирует его
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Dimension dimension = new Dimension(width, height);
        super.setPreferredSize(dimension);
    }


    @Override
    public void paintComponent(Graphics g) {  //метод отрисовки изображения
        super.paintComponent(g);
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }


    public void clearImage() { //метод, который устанавливает все пиксели изображения в черный цвет
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                drawPixel(i, j, 0);
            }
        }
    }


    public void drawPixel(int x, int y, int rgbColor) { //метод, который устанавливает пиксель в определенный цвет
        image.setRGB(x, y, rgbColor);
    }
}