package ru.sertok.game.puzzle.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static ru.sertok.game.puzzle.utils.Constants.SIZE;

public class Game extends JFrame {
    private ArrayList<Point> solution;
    private JPanel panel;
    private Image source;
    private Image resized;
    private int width, height;

    public Game() {
        init();
    }

    private void init() {
        solution = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < 4; j++) {
                solution.add(new Point(i, j));
            }
        }
    }
}
