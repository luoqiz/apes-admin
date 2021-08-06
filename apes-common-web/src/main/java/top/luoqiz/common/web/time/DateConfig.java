package top.luoqiz.common.web.time;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 配置 @RequestBody 参数中的时间格式，需要指定前后端全部以时间戳交互
 *
 * @author luoqiz
 */
@Configuration
public class DateConfig {

    /**
     * 默认日期时间格式
     */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 默认时间格式
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    private static final String timeStampFormat = "^\\d+$";

    /**
     * Json序列化和反序列化转换器，用于转换Post请求体中的json以及将我们的对象序列化为返回响应的json
     */
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

        //LocalDateTime系列序列化和反序列化模块，继承自jsr310，我们在这里修改了日期格式
        JavaTimeModule javaTimeModule = new JavaTimeModule();

//        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
//        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
//        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
//        javaTimeModule.addSerializer(Date.class, new DateSerializer());

        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));

        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
        javaTimeModule.addSerializer(Date.class, new DateSerializer());

//        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
//        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
//        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
//        javaTimeModule.addDeserializer(Date.class, new DateDeserializer());
        objectMapper.registerModule(javaTimeModule);

        // 设置 long 型转字符串
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(long.class, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

    /**
     * 序列化实现 Date 转为时间戳
     */
    public static class DateSerializer extends JsonSerializer<Date> {
        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            if (value != null) {
                long timestamp = value.getTime();
                gen.writeNumber(timestamp);
            }
        }
    }

    /**
     * 反序列化实现 时间戳转为 Date
     */
    public static class DateDeserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser p, DeserializationContext deserializationContext)
                throws IOException {
            long timestamp = p.getValueAsLong();
            if (timestamp > 0) {
                return new Date(timestamp);// .ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
            } else {
                return null;
            }
        }
    }


    /**
     * 序列化实现 LocalDate 转为时间戳
     */
    public static class LocalDateSerializer extends JsonSerializer<LocalDate> {
        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            if (value != null) {
                long timestamp = value.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();// .toEpochDay();// .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                gen.writeNumber(timestamp);
            }
        }
    }

    /**
     * 反序列化实现 时间戳转为 LocalDate
     */
    public static class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
        private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
        private static final String shortDateFormat = "yyyy-MM-dd";
        private static final String timeStampFormat = "^\\d+$";

        @Override
        public LocalDate deserialize(JsonParser p, DeserializationContext deserializationContext)
                throws IOException {
            String value = p.getValueAsString();
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
            } catch (Exception e) {
                throw new RuntimeException(String.format("parser %s to LocalDate fail", value));
            }
            throw new RuntimeException(String.format("parser %s to LocalDate fail", value));
        }
    }

    /**
     * 序列化实现 LocalDateTime 转为时间戳
     */
    public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            if (value != null) {
                long timestamp = value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                gen.writeNumber(timestamp);
            }
        }
    }

    /**
     * 反序列化实现 时间戳转为 LocalDateTime
     */
    public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
        private static final String timeStampFormat = "^\\d+$";

        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext deserializationContext)
                throws IOException {

//            long timestamp = p.getValueAsLong();
//            if (timestamp > 0) {
//                return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
//            } else {
//                return null;
//            }
            String value = p.getValueAsString();
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
            } catch (Exception e) {
                throw new RuntimeException(String.format("parser %s to LocalDateTime fail", value));
            }
            throw new RuntimeException(String.format("parser %s to LocalDateTime fail", value));

        }
    }

}