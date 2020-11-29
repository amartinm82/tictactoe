package usantatecla.tictactoe.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
public class GameRegistryTest {

    @Mock
    private Game game;

    @Mock
    private List<GameMemento> mementos;

    @InjectMocks
    private GameRegistry gameRegistry;

    @Mock
    private Turn turn;

    @Mock
    private Board board;

    @BeforeEach
    public void before() {
        openMocks(this);
    }

    @Test
    public void testGivenAGameRegistryWhenRegisterThenRegisterAGameMemento() {
        GameMemento gameMemento = new GameMemento(this.turn, this.board);
        when(this.game.createMemento()).thenReturn(gameMemento);
        this.gameRegistry.register();
        verify(this.mementos).add(0, gameMemento);
    }

    @Test
    public void testGivenAGameRegistryWhenUndoButThereIsNotAGameMementoThenCallGameSetMethodWithNull() {
        this.gameRegistry.undo();
        verify(this.game).set(null);
    }

    @Test
    public void testGivenAGameRegistryWhenUndoAndThereIsAGameMementoThenCallGameSetMethodWithExistingGameMemento() {
        GameMemento gameMemento = new GameMemento(this.turn, this.board);
        when(this.mementos.get(1)).thenReturn(gameMemento);
        this.gameRegistry.undo();
        verify(this.game).set(gameMemento);
    }

    @Test
    public void testGivenAGameRegistryWhenIsUndoableAndNotIsUndoableThenReturnFalse() {
        assertFalse(this.gameRegistry.isUndoable());
    }

    @Test
    public void testGivenAGameRegistryWhenIsUndoableAndIsUndoableThenReturnTrue() {
        when(this.mementos.size()).thenReturn(2);
        assertTrue(this.gameRegistry.isUndoable());
    }

}
