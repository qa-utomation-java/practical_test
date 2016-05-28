package com.practical.framework;

import ru.qatools.properties.Property;
import ru.qatools.properties.Resource;


/**
 * Created by sergey on 5/27/16.
 */
@Resource.Classpath("config.properties")
public interface Configuration {
    @Property("baseUrl")
    String getBaseUrl();
}
