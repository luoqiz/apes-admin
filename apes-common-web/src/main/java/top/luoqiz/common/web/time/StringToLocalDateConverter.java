package top.luoqiz.common.web.time;

import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 配置 @RequestParam 和 @PathVariable 注解的 LocalDate 转换器
 * 将 String 转为 LocalDate
 *
 * @author luoqiz
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate> {
    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String shortDateFormat = "yyyy-MM-dd";
    private static final String timeStampFormat = "^\\d+$";

    @Override
    public LocalDate convert(String value) {

        if (value == null || value.trim().equals("") || value.equalsIgnoreCase("null")) {
            return null;
        }

        value = value.trim();

        try {
            if (value.contains("-")) {
                DateTimeFormatter formatter;
                if (value.contains(":")) {
                    formatter = DateTimeFormatter.ofPattern(dateFormat);
                } else {
                    formatter = DateTimeFormatter.ofPattern(shortDateFormat);
                }
                return LocalDate.parse(value, formatter);
            } else if (value.matches(timeStampFormat)) {
                Long timestamp = Long.valueOf(value);
                return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.systemDefault()).toLocalDate();
            }
            return LocalDate.parse(value);
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to LocalDate fail", value));
        }
    }
}