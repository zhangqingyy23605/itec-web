package edu.hust.itec.util;

import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by xsh on 2015/5/16.
 */
public class NullAwareBeanUtils extends BeanUtilsBean {

    public static NullAwareBeanUtils INSTANCE = new NullAwareBeanUtils();

    @Override
    public void copyProperties(Object dest, Object orig) {
        try {
            super.copyProperties(dest, orig);
        } catch(Exception e) {

        }
    }
}