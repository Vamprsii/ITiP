import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    private int displaySize;
    private JImageDisplay displayImage;
    private FractalGenerator fractalGenerator;
    private Rectangle2D.Double complexPlaneRange;
    private JComboBox<FractalGenerator> comboBox;
    private int rowsRemaining = 0;
    private JButton buttonReset;
    private JButton buttonSave;

    public static void main(String[] args) {
        FractalExplorer fractalExplorer = new FractalExplorer(700);
        fractalExplorer.createAndShowGUI();
        fractalExplorer.drawFractal();
    }


    private FractalExplorer(int sizeDisplay) { //конструктор, сохраняющий размер отображения, инициализирующий объекты диапазона и фрактального генератора
        this.displaySize = sizeDisplay;
        this.fractalGenerator = new Mandelbrot();
        this.complexPlaneRange = new Rectangle2D.Double(0, 0, 0, 0);
        fractalGenerator.getInitialRange(this.complexPlaneRange);
    }


    public void createAndShowGUI() {  //метод, инициализирующий графический интерфейс Swing
        //настройки отображения
        displayImage = new JImageDisplay(displaySize, displaySize);
        displayImage.addMouseListener(new MouseListener());

        //кнопка для сброса изображения
        buttonReset = new JButton("Reset");
        buttonReset.addActionListener(new ResetActionListener());
        //кнопка для сохранения изображения
        buttonSave = new JButton("Save image");
        buttonSave.addActionListener(new SaveActionListener());

        //настройки для выбора фрактала
        JLabel label = new JLabel("Fractal:");
        comboBox = new JComboBox<>();
        comboBox.addItem(new Mandelbrot());
        comboBox.addItem(new Tricorn());
        comboBox.addItem(new BurningShip());
        comboBox.addActionListener(new ComboActionListener());

        //размещение панелей
        JPanel jPanelFractal = new JPanel();
        JPanel jPanelButtons = new JPanel();
        jPanelFractal.add(label, BorderLayout.CENTER);
        jPanelFractal.add(comboBox, BorderLayout.CENTER);
        jPanelButtons.add(buttonReset, BorderLayout.CENTER);
        jPanelButtons.add(buttonSave, BorderLayout.CENTER);

        //размещение содержимого окна
        JFrame frame = new JFrame("Fractal generation");
        frame.setLayout(new BorderLayout());
        frame.add(displayImage, BorderLayout.CENTER);
        frame.add(jPanelFractal, BorderLayout.NORTH);
        frame.add(jPanelButtons, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }


    private void drawFractal() {  //метод для вывода на экран фрактала
        enableUI(false); //отключение GUI во время рисования
        rowsRemaining = displaySize;
        for (int i = 0; i < displaySize; i++) {
            FractalWorker curRow = new FractalWorker(i);
            curRow.execute();
        }
    }


    public void enableUI(boolean val) { //функция для включения или отключения пользовательского интерфейса
        buttonSave.setEnabled(val);
        buttonReset.setEnabled(val);
        comboBox.setEnabled(val);
    }


    private class ResetActionListener implements ActionListener {//восстановление масштаба, перерисовка фрактала (кнопка Reset)
        @Override
        public void actionPerformed(ActionEvent e) {
            displayImage.clearImage();
            fractalGenerator.getInitialRange(complexPlaneRange);
            drawFractal();
        }
    }

    //сохранение изображения (кнопка Save Image)
    private class SaveActionListener implements ActionListener { //сохранение изображения (кнопка Save Image)
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("PNG Images", "png");
            fileChooser.setFileFilter(fileFilter);
            fileChooser.setAcceptAllFileFilterUsed(false);
            int t = fileChooser.showSaveDialog(displayImage);
            if (t == JFileChooser.APPROVE_OPTION) {
                try {
                    ImageIO.write(displayImage.image, "png", fileChooser.getSelectedFile());
                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(displayImage, ee.getMessage(), "Cannot Save Image", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }


    private class ComboActionListener implements ActionListener { //восстановление масштаба, перерисовка фрактала (combobox)
        @Override
        public void actionPerformed(ActionEvent e) {
            fractalGenerator = (FractalGenerator) comboBox.getSelectedItem();
            fractalGenerator.getInitialRange(complexPlaneRange);
            drawFractal();
        }
    }


    private class MouseListener extends MouseAdapter { //обработка события о щелчке мышью (нажимая на какое-либо место на фрактальном отображении, мы увеличиваем его)
        @Override
        public void mouseClicked(MouseEvent e) {
            if (rowsRemaining > 0) {
                return;
            }
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


    private class FractalWorker extends SwingWorker<Object, Object> { //вычисление значений цвета для одной строки фрактала
        //поля
        private int yCoord; //координата y вычисляемой строки
        private int[] colors; //вычисленные значения RGB для каждого пикселя в этой строке

        public FractalWorker(int yCoord) {
            this.yCoord = yCoord;
        }


        @Override
        public Object doInBackground() throws Exception { //выполнение длительной задачи
            colors = new int[displaySize];
            //цикл сохраняет каждое значение RGB в соответствующем элементе массива
            for (int curX = 0; curX < displaySize; curX++) {
                //вычисление количества итераций
                int count = fractalGenerator.numIterations(
                        FractalGenerator.getCoord(
                                complexPlaneRange.x,
                                complexPlaneRange.x + complexPlaneRange.width,
                                displaySize,
                                curX
                        ),
                        FractalGenerator.getCoord(
                                complexPlaneRange.y,
                                complexPlaneRange.y + complexPlaneRange.width,
                                displaySize,
                                yCoord
                        )
                );
                int rgbColor;
                if (count == -1) {
                    rgbColor = 0; //точка не выходит за границы: цвет - чёрный
                }
                else { //иначе, значение цвета = кол-ву итераций
                    float hue = 0.7f + (float) count / 200f;
                    rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                }
                colors[curX] = rgbColor;
            }
            return null;
        }


        @Override
        public void done() { //вызывается при завершении фоновой задачи
            //изменение текущей строки
            for (int curX = 0; curX < displaySize; curX++) {
                displayImage.drawPixel(curX, yCoord, colors[curX]);
            }
            //перерисовка указанной области
            displayImage.repaint(0,0,yCoord,displaySize,1);
            rowsRemaining --;
            if (rowsRemaining == 0) {
                enableUI(true);
            }
        }
    }
}