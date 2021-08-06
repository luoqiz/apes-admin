package top.luoqiz.file.event;

import java.util.EventObject;

/**
 * @author luoqiz
 */
public class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
