package usantatecla.tictactoe.views.console;

import usantatecla.tictactoe.controllers.PlayController;
import usantatecla.tictactoe.models.Coordinate;
import usantatecla.tictactoe.types.Error;
import usantatecla.tictactoe.views.Message;
import usantatecla.utils.Console;

class PlayView {

    private PlayController playController;

    PlayView(PlayController playController) {
        this.playController = playController;
    }

    void interact() {
        do {
            int option = 1;
            if (playController.isUser()) {
                option = readOption();
            }
            if (option == 1) {
                if (!this.playController.isBoardComplete()) {
                    this.put();
                } else {
                    this.move();
                }
            }
            new GameView(this.playController).write();
        } while (!this.playController.isTicTacToe());
        new TokenView(this.playController.getToken()).write();
        Message.PLAYER_WIN.writeln();
    }

    private int readOption() {
        Console console = Console.getInstance();
        int option;
        do {
            Message.CHOOSE_OPTION.writeln();
            Message.MOVEMENT_OPTION.writeln();
            option = console.readInt("");
        } while (option != 1);
        return option;
    }

    private void put() {
        boolean isUser = this.playController.isUser();
        Coordinate coordinate;
        Error error;
        do {
            if (isUser) {
                coordinate = new CoordinateView().read(Message.COORDINATE_TO_PUT.toString());
            } else {
                coordinate = createRandomCoordinate();
            }
            error = this.playController.put(coordinate);
            if (isUser) {
                new ErrorView(error).writeln();
            }
        } while (!error.isNull());
    }

    private void move() {
        boolean isUser = this.playController.isUser();
        Coordinate origin;
        Coordinate target;
        Error error;
        do {
            if (isUser) {
                origin = new CoordinateView().read(Message.COORDINATE_TO_REMOVE.toString());
                target = new CoordinateView().read(Message.COORDINATE_TO_MOVE.toString());
            } else {
                origin = createRandomCoordinate();
                target = createRandomCoordinate();
            }
            error = this.playController.move(origin, target);
            if (isUser) {
                new ErrorView(error).writeln();
            }
        } while (!error.isNull());
    }

    Coordinate createRandomCoordinate() {
        Coordinate coordinate = new Coordinate();
        coordinate.random();
        return coordinate;
    }

}
