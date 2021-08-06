package top.luoqiz.common.web.time;

import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 配置 @RequestParam 和 @PathVariable 注解的 LocalDateTime 转换器
 * 将 String 转为 LocalDateTime
 *
 * @author luoqiz
 */
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {


    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String timeStampFormat = "^\\d+$";

    @Override
    public LocalDateTime convert(String value) {

        if (value == null || value.trim().equals("") || value.equalsIgnoreCase("null")) {
            return null;
        }

        value = value.trim();

        try {
            if (value.contains("-")) {
                return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(dateFormat));
            } else if (value.matches(timeStampFormat)) {
                Long timestamp = Long.valueOf(value);
                return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.systemDefault()).toLocalDateTime();
            }
            return LocalDateTime.parse(value);
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to LocalDateTime fail", value));
        }
    }
}