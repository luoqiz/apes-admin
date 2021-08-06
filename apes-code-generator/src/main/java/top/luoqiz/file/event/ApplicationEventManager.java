package top.luoqiz.file.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 事件容器管理类
 */
public class ApplicationEventManager {
    static List<ApplicationListener> listenerList = new ArrayList<>();

    public static <T extends ApplicationEvent> void addListener(ApplicationListener<T> listener) {
        listenerList.add(listener);
    }

    public static <T extends ApplicationEvent> void addListener(T clazz, ApplicationListener<T> listener) {
        listenerList.add(listener);
        publishedEvent(clazz);
    }

    public static void removeListener(Class<? extends ApplicationListener> listener) {
        Iterator<ApplicationListener> iterator = listenerList.iterator();
        while (iterator.hasNext()) {
            ApplicationListener iter = iterator.next();
            if (iter.getClass().getTypeName().equals(listener.getTypeName())) {
                iterator.remove();
            }
        }
//        listenerList.remove(listener);
    }

    private static void notifyListeners(ApplicationEvent event) {
        for (ApplicationListener applicationListener : listenerList) {
            if (applicationListener.support(event)) {
                applicationListener.onApplicationEvent(event);
            }
        }
    }

    public static void publishedEvent(ApplicationEvent event) {
        notifyListeners(event);
    }

//    public static void addListener(TableEvent eeeeeeee, FilePathChangeEventHandler filePathChangeEventHandler) {
//    }
}
