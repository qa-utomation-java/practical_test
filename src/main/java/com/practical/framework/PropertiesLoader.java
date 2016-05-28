package com.practical.framework;

import ru.qatools.properties.PropertyLoader;

/**
 * Created by sergey on 5/28/16.
 */
public class PropertiesLoader {

    private PropertiesLoader() {
    }

    private static final Configuration config = init();

    public static Configuration getConfig() {
        if (config != null) {
            return config;
        }
        return init();
    }

    private static Configuration init() {
        return PropertyLoader.newInstance()
                .populate(Configuration.class);
    }
}
