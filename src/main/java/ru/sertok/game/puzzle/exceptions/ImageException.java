package ru.sertok.game.puzzle.exceptions;

public class ImageException extends RuntimeException {
    public ImageException(Throwable cause) {
        super("Неудалось загрузить картинку! : ", cause);
    }
}
