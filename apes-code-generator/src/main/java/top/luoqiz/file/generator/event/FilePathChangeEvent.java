package top.luoqiz.file.generator.event;

import top.luoqiz.file.event.ApplicationEvent;

/**
 * @author luoqiz
 */
public class FilePathChangeEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public FilePathChangeEvent(Object source) {
        super(source);
    }
}
