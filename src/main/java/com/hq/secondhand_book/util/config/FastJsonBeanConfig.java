package com.hq.secondhand_book.util.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FastJsonBeanConfig {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        SerializerFeature[] features = {
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue, // 输出空置字段
                SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
                SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
                SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
                SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
        };

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(features);
//        fastJsonConfig.setSerializeFilters((ValueFilter) (o, s, o1) -> {
//            if (o1 != null && o1 instanceof Double) {
//                return DecilmalUtil.prettyformat((Double) o1);
//            }
//            return o1;
//        });
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");

        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);

        fastConverter.setFastJsonConfig(fastJsonConfig);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        return new HttpMessageConverters((HttpMessageConverter<?>) fastConverter);
    }
}
