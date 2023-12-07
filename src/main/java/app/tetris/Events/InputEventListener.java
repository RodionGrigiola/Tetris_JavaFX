package app.tetris.Events;

import app.tetris.Components.DownData;
import app.tetris.Components.ViewData;

public interface InputEventListener {

    DownData onDownEvent(MoveEvent event);
    ViewData onLeftEvent();
    ViewData onRightEvent();
    ViewData onRotateEvent();
}
