package com.cos.blog.service;


import java.util.MissingResourceException;
import java.util.ResourceBundle;


public class KEY
{
    private static final String BUNDLE_NAME = "key";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);


    private KEY() {
    }


    public static String getString(String key)
    {
        try {
            return RESOURCE_BUNDLE.getString(key);
        }
        catch(MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
