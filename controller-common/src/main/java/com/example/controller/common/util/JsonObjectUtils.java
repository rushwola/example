package com.example.controller.common.util;

import com.example.common.context.ContextReader;
import com.example.controller.common.enumer.MsgCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/2.
 */
@Component
public class JsonObjectUtils {

    protected static final Logger LOG = LoggerFactory.getLogger(JsonObjectUtils.class);

    private static ContextReader contextReader;

    @Autowired(required = true)
    public JsonObjectUtils(@Qualifier("contextReaderImpl") ContextReader contextReader) {
        JsonObjectUtils.contextReader = contextReader;
    }

    public static JsonObjectBase buildFieldError(Map<String, String> errors, MsgCodeEnum statusCode) {

        JsonObjectError json = new JsonObjectError();
        json.setStatus(statusCode.getCode());

        for (String str : errors.keySet()) {
            json.addFieldError(str, contextReader.getMessage(errors.get(str)));
        }

        LOG.info(json.toString());

        return json;
    }

    public static JsonObjectBase buildFieldError(Map<String, String> errors, Map<String, Object[]> argsMap,
                                                 MsgCodeEnum statusCode) {
        JsonObjectError json = new JsonObjectError();
        json.setStatus(statusCode.getCode());
        for (String str : errors.keySet()) {
            try {
                json.addFieldError(str, contextReader.getMessage(errors.get(str), argsMap.get(str)));
            } catch (NoSuchMessageException e) {
                json.addFieldError(str, errors.get(str));
            }
        }

        LOG.info(json.toString());

        return json;
    }

}
