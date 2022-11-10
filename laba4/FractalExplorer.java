import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    private int displaySize;
    private JImageDisplay displayImage;
    private FractalGenerator fractalGenerator;
    private Rectangle2D.Double complexPlaneRange;


    public static void main(String[] args) {
        FractalExplorer fractalExplorer = new FractalExplorer(800);
        fractalExplorer.createAndShowGUI();
        fractalExplorer.drawFractal();
    }

    private FractalExplorer(int sizeDisplay) { //конструктор, сохраняющий размер отображения, инициализирующий объекты диапазона и фрактального генератора
        this.displaySize = sizeDisplay;
        this.fractalGenerator = new Mandelbrot();
        this.complexPlaneRange = new Rectangle2D.Double(0, 0, 0, 0);
        fractalGenerator.getInitialRange(this.complexPlaneRange);
    }


    public void createAndShowGUI() { //метод, инициализирующий графический интерфейс Swing

        //настройки отображения
        displayImage = new JImageDisplay(displaySize, displaySize);
        displayImage.addMouseListener(new MouseListener());

        //кнопка для сброса отображения
        JButton button = new JButton("Reset");
        button.addActionListener(new ResetActionListener());

        //размещение содержимого окна
        JFrame frame = new JFrame("Fractal generator");
        frame.setLayout(new BorderLayout());
        frame.add(displayImage, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }


    private void drawFractal() {
        for (int x = 0; x < displaySize; x++) {
            for (int y = 0; y < displaySize; y++) { //вычисление количества итераций
                int counter = fractalGenerator.numIterations(
                        FractalGenerator.getCoord(
                                complexPlaneRange.x,
                                complexPlaneRange.x + complexPlaneRange.width,
                                displaySize,
                                x
                        ),
                        FractalGenerator.getCoord(
                                complexPlaneRange.y,
                                complexPlaneRange.y + complexPlaneRange.width,
                                displaySize,
                                y
                        )
                );
                int rgbColor;
                if (counter == -1) {
                    rgbColor = 0; //точка не выходит за границы (чёрный цвет)
                }
                else { //в другом случае - значение цвета = количеству итераций
                    float hue = 0.7f + (float) counter / 200f;
                    rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                }
                displayImage.drawPixel(x, y, rgbColor);
            }
        }
        displayImage.repaint(); //обновление JimageDisplay
    }


    private class ResetActionListener implements ActionListener { //сброс дапазона к начальному и перерисовка фрактала
        @Override
        public void actionPerformed(ActionEvent e) {
            displayImage.clearImage();
            fractalGenerator.getInitialRange(complexPlaneRange);
            drawFractal();
        }
    }


    private class MouseListener extends MouseAdapter {//обработка события о щелчке мышью (нажимая на какое-либо место на фрактальном отображении, мы увеличиваем его)
        @Override
        public void mouseClicked(MouseEvent e) {
            double x = FractalGenerator.getCoord(
                    complexPlaneRange.x,
                    complexPlaneRange.x + complexPlaneRange.width,
                    displaySize,
                    e.getX()
            );
            double y = FractalGenerator.getCoord(
                    complexPlaneRange.y,
                    complexPlaneRange.y + complexPlaneRange.width,
                    displaySize,
                    e.getY()
            );
            fractalGenerator.recenterAndZoomRange(complexPlaneRange, x, y, 0.5);
            drawFractal();
        }
    }

}

