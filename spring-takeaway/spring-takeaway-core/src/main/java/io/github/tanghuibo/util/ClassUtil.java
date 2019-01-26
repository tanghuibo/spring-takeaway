package io.github.tanghuibo.util;

import java.lang.reflect.Field;

/**
 * @description: class工具类
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-01-27 02:48
 **/
public class ClassUtil {

    /**
     * 通过字段名获取属性
     *
     * @param obj       源
     * @param clazz     类
     * @param filedName 字段名
     * @return
     */
    public static Object getPropertyByFiledName(Object obj, Class clazz, String filedName) {
        try {
            Field field = clazz.getDeclaredField(filedName);
            field.setAccessible(true);
            return field.get(obj);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;

    }


}
