package com.itcast.manager.utils;


import com.itcast.manager.enums.CodeEnum;

/**
 * Created by dang
 * 2017-07-16 18:36
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
