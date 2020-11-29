package usantatecla.tictactoe.views.console;

import usantatecla.tictactoe.controllers.PlayController;
import usantatecla.tictactoe.models.Coordinate;
import usantatecla.tictactoe.types.Error;
import usantatecla.tictactoe.views.Message;

class PlayView {

    private PlayController playController;

    PlayView(PlayController playController) {
        this.playController = playController;
    }

    void interact() {
        do {
            if (playController.isUser()) {
                new PlayMenu(playController).execute();
            } else {
                this.randomPlay(playController);
            }
            new GameView(this.playController).write();
        } while (!this.playController.isTicTacToe());
        new TokenView(this.playController.getToken()).write();
        Message.PLAYER_WIN.writeln();
    }

    private void randomPlay(PlayController playController) {
        Error error;
        do {
            Coordinate target = createRandomCoordinate();
            if (!playController.isBoardComplete()) {
                error = playController.put(target);
            } else {
                Coordinate origin = createRandomCoordinate();
                error = playController.move(origin, target);
            }
        } while (!error.isNull());
    }

    Coordinate createRandomCoordinate() {
        Coordinate coordinate = new Coordinate();
        coordinate.random();
        return coordinate;
    }

}
