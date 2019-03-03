package ru.sertok.game.puzzle.game;

import ru.sertok.game.puzzle.button.PuzzleButton;
import ru.sertok.game.puzzle.exceptions.ImageException;
import ru.sertok.game.puzzle.utils.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static ru.sertok.game.puzzle.utils.Constants.SIZE;

public class Game extends JFrame {
    private ArrayList<Point> solution;
    private ArrayList<PuzzleButton> buttons;
    private JPanel panel;
    private Image source;
    private BufferedImage resized;
    private double width, height;
    private Image image;
    private PuzzleButton lastButton;

    public Game() {
        init();
    }

    private void init() {
        solution = new ArrayList<>();
        buttons = new ArrayList<>();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < 4; j++) {
                solution.add(new Point(i, j));
            }
        }

        panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.gray));
        panel.setLayout(new GridLayout(SIZE, SIZE));
        try {
            source = load();
            resized = resize(source,BufferedImage.TYPE_INT_ARGB);
        } catch (IOException e) {
            throw new ImageException(e);
        }

        width = resized.getWidth();
        height = resized.getHeight();
        add(panel, BorderLayout.CENTER);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < 4; j++) {
               image = createImage(new FilteredImageSource(resized.getSource(),new CropImageFilter((int)(j*width/4),(int)(i*height/4),(int)(width/4),(int)(height/4))));
                PuzzleButton puzzleButton = new PuzzleButton(image);
                puzzleButton.putClientProperty("position",new Point(i,j));
                if(i==3&&j==3){
                    lastButton = new PuzzleButton();
                    lastButton.setBorderPainted(false);
                    lastButton.setContentAreaFilled(false);
                    lastButton.setLastButton(true);
                }
                else {
                    buttons.add(puzzleButton);
                }

            }
        }
    }

    private Image load() throws IOException {
        return ImageIO.read(new File("image.jpg"));
    }
    private BufferedImage resize(Image image,int type) throws IOException {
        BufferedImage resizedImage = new BufferedImage(Constants.WIDTH, getNewHeight(width, height), type);
        Graphics2D graphics = resizedImage.createGraphics();
        graphics.drawImage(image,0,0,null);
        graphics.dispose();
        return resizedImage;
    }

    private int getNewHeight(double width, double height) {
        double ratio = WIDTH / width;
        return (int)(height*ratio);
    }
}
