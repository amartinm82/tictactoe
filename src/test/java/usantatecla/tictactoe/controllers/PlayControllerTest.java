package usantatecla.tictactoe.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import usantatecla.tictactoe.models.Game;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
public class PlayControllerTest {

    @Mock
    private Game game;

    @InjectMocks
    private PlayController playController;

    @BeforeEach
    public void before() {
        openMocks(this);
        this.playController = spy(this.playController);
    }

    @Test
    public void testGivenAPlayControllerWhenUndoThenUndo() {
        this.playController.undo();
        verify(this.game).undo();
    }
}
