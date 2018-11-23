package ru.sertok.game.puzzle.game;

import ru.sertok.game.puzzle.button.PuzzleButton;
import ru.sertok.game.puzzle.exceptions.ImageException;
import ru.sertok.game.puzzle.utils.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static ru.sertok.game.puzzle.utils.Constants.SIZE;

public class Game extends JFrame {
    private ArrayList<Point> solution;
    private ArrayList<PuzzleButton> buttons;
    private JPanel panel;
    private Image source;
    private Image resized;
    private double width, height;

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
    }

    private Image load() throws IOException {
        return ImageIO.read(new File("image.jpg"));
    }
    private Image resize(Image image,int type) throws IOException {
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
