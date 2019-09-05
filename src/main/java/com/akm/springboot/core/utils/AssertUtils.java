package com.akm.springboot.core.utils;

import com.akm.springboot.core.exception.BusinessException;
import com.akm.springboot.core.exception.CodeMsg;

import java.util.Collection;
import java.util.Map;

/**
 * 在方法或者任何地方对参数的有效性做校验。
 * 当不满足断言条件时，会抛出BusinessException异常。.
 */
public class AssertUtils {

    /**
     * Don't let anyone instantiate this class
     */
    private AssertUtils() {
    }

    /**
     * 判断一个布尔表达式, 若表达式为{@code false}则抛出指定错误信息的{@code BusinessException}.
     *
     * @param expression 布尔表达式
     * @param message    断言失败时的错误信息
     * @throws BusinessException
     */
    public static void isTrue(boolean expression, String message) throws BusinessException {
        if (!expression) {
            throw new BusinessException(message);
        }
    }

    public static void isTrue(boolean expression, CodeMsg codeMsg) throws BusinessException {
        if (!expression) {
            throw new BusinessException(codeMsg);
        }
    }

    /**
     * 如果对象为{@code null}, 则抛出异常
     *
     * @param object 要判断的对象
     * @throws BusinessException
     */
    public static void notNull(Object object) throws BusinessException {
        notNull(object, "不能处理空对象");
    }

    /**
     * 如果对象为{@code null}, 则抛出异常
     *
     * @param object  要判断的对象
     * @param message 断言失败时的错误信息
     * @throws BusinessException
     */
    public static void notNull(Object object, String message) throws BusinessException {
        if (object == null) {
            throw new BusinessException(message);
        }
    }

    public static void notNull(Object object, CodeMsg codeMsg) throws BusinessException {
        if (object == null) {
            throw new BusinessException(codeMsg);
        }
    }

    /**
     * 如果字符串为{@code null}、空字符串或仅包含空白字符, 则抛出异常
     *
     * @param text    要进行检查的字符串
     * @param message 断言失败时的错误信息
     * @throws BusinessException
     */
    public static void notBlank(String text, String message) throws BusinessException {
        if (StringUtils.isBlank(text)) {
            throw new BusinessException(message);
        }
    }

    public static void notBlank(String text, CodeMsg codeMsg) throws BusinessException {
        if (StringUtils.isBlank(text)) {
            throw new BusinessException(codeMsg);
        }
    }

    /**
     * 如果数组为{@code null}或长度为0, 则抛出异常
     *
     * @param array   要进行检查的数组
     * @param message 断言失败时的错误信息
     * @param <T>     数组的数据类型
     * @throws BusinessException
     */
    public static <T> void notEmpty(T[] array, String message) throws BusinessException {
        if (array == null || array.length == 0) {
            throw new BusinessException(message);
        }
    }

    /**
     * 如果集合为{@code null},或者不包含任何元素,则抛出异常
     *
     * @param collection 要进行检查的集合
     * @param message    断言失败时的错误信息
     * @throws BusinessException
     */
    public static void notEmpty(Collection<?> collection, String message) throws BusinessException {
        if (collection == null || collection.isEmpty()) {
            throw new BusinessException(message);
        }
    }

    /**
     * 如果键值对为{@code null},或者不包含任何键值,则抛出异常
     *
     * @param map     要进行检查的键值对
     * @param message 断言失败时的错误信息
     * @throws BusinessException
     */
    public static void notEmpty(Map<?, ?> map, String message) throws BusinessException {
        if (map == null || map.isEmpty()) {
            throw new BusinessException(message);
        }
    }
}
