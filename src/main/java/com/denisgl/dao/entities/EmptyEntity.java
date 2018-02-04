package com.denisgl.dao.entities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * I decided that my DAO will not return null
 * but will entity with null fields
 * My solution to struggle with NullPointerException
 */
public interface EmptyEntity {
    /**
     * Check if entity has null fields
     */
    default boolean areFieldsNull(){
        for (Field field : getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if(field.get(this)!=null) return false;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
