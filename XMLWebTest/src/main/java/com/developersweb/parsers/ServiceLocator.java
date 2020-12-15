package com.developersweb.parsers;

import java.util.HashMap;

public class ServiceLocator {
    private static ServiceLocator _instance;

    public static ServiceLocator getInstance()
    {
        if (_instance == null)
        {
            _instance = new ServiceLocator();
        }

        return _instance;
    }

    private final HashMap<String, Object> _instanceMap = new HashMap<String, Object>();
    public <T> ServiceLocator register(Class<T> cls, T instance)
    {
        _instanceMap.put(cls.getTypeName(), instance);

        return this;
    }

    public <T> T resolve(Class<T> cls) throws Exception {
        if (!_instanceMap.containsKey(cls.getTypeName()))
        {
            throw new Exception("not registered");
        }
        return (T)_instanceMap.get(cls.getTypeName());
    }
}
