package top.luoqiz.file.event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EventListener;

/**
 * @author luoqiz
 */
@FunctionalInterface
public interface ApplicationListener<T extends ApplicationEvent> extends EventListener {

    /**
     * 监听器响应事件
     *
     * @param event
     */
    void onApplicationEvent(T event);

    /**
     * 是否支持此类型
     *
     * @param event
     * @return
     */
    default boolean support(T event) {
//        如果是继承基类而来的泛型，就用 getGenericSuperclass() , 转型为 ParameterizedType 来获得实际类型
//        如果是实现接口而来的泛型，就用 getGenericInterfaces() , 针对其中的元素转型为 ParameterizedType 来获得实际类型
//        我们所说的 Java 泛型在字节码中会被擦除，并不总是擦除为 Object 类型，而是擦除到上限类型
//        能否获得想要的类型可以在 IDE 中，或用 javap -v <your_class>  来查看泛型签名来找到线索
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericInterfaces()[0];
        Type type = parameterizedType.getActualTypeArguments()[0];
        if (type.getTypeName().equals(event.getClass().getTypeName())) {
            return true;
        }
        return false;
    }

}
