package ru.sertok.game.puzzle.button;

import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Data
public class PuzzleButton extends JButton {
    private boolean isLastButton;

    public PuzzleButton() {
    }

    public PuzzleButton(Image image) {
        super(new ImageIcon(image));
    }

    private void initUI() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.yellow));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.gray));
            }
        });
    }
}
