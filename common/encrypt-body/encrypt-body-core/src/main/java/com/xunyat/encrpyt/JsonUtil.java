package com.xunyat.encrpyt;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Json to bean 工具类
 * @author Li Xu
 * @version 1.0
 * @date 2021/3/8
 */
public final class JsonUtil {
    /** 默认日期时间格式 */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /** 默认日期格式 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /** 默认时间格式 */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T toBean(String jsonStr, Class<T> beanClass) {
        return toBean(jsonStr,beanClass,true);
    }

    public static <T> T toBean(String jsonStr, Class<T> beanClass,boolean state) {
        if (null == jsonStr || "".equals(jsonStr)) {
            return null;
        }
        try {

            mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN,true);
            mapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN,true);
            mapper.configure(JsonParser.Feature.ALLOW_MISSING_VALUES,true);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,state);

            // 设置json 反序列化时间格式
            mapper.setDateFormat(new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT));

            // java8日期处理
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
            javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
            javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
            javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
            javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));

            mapper.registerModule(javaTimeModule);
            return mapper.readValue(jsonStr, beanClass);
        }catch (Exception ex){
            ex.printStackTrace();
            //System.out.println(ex.getMessage());
            throw  new RuntimeException(ex.getMessage());
        }
    }

    public static JsonNode getNode(String content, String key) throws IOException {
        JsonNode jsonNode = mapper.readTree(content);
        return jsonNode.get(key);
    }

    public static String writeValueAsString(Object body) throws JsonProcessingException {
        return mapper.writeValueAsString(body);
    }
}
