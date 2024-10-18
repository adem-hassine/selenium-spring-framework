package com.upwork.selenium.config;


import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WebDriverScope implements Scope {
    public static final String NAME = "webDriver";
    private static final String WEB_DRIVER_CLASS = "org.openqa.selenium.WebDriver";
    private static final String[] BEAN_CLASSES = new String[]{"org.openqa.selenium.WebDriver", "org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder"};
    private final Map<String, Object> instances = new HashMap<>();

    public WebDriverScope() {
    }

    public Object get(String name, ObjectFactory<?> objectFactory) {
        synchronized(this.instances) {
            Object instance = this.instances.get(name);
            if (instance == null) {
                instance = objectFactory.getObject();
                this.instances.put(name, instance);
            }

            return instance;
        }
    }

    public Object remove(String name) {
        synchronized(this.instances) {
            return this.instances.remove(name);
        }
    }

    public void registerDestructionCallback(String name, Runnable callback) {
    }

    public Object resolveContextualObject(String key) {
        return null;
    }

    public String getConversationId() {
        return null;
    }

    boolean reset() {
        boolean reset = false;
        synchronized(this.instances) {
            Iterator var3 = this.instances.values().iterator();

            while(var3.hasNext()) {
                Object instance = var3.next();
                reset = true;
                if (instance instanceof WebDriver webDriver) {
                    webDriver.quit();
                }
            }

            this.instances.clear();
            return reset;
        }
    }

    static void registerWith(ConfigurableApplicationContext context) {
        if (ClassUtils.isPresent("org.openqa.selenium.WebDriver", (ClassLoader)null)) {
            ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
            if (beanFactory.getRegisteredScope("webDriver") == null) {
                beanFactory.registerScope("webDriver", new WebDriverScope());
            }

            context.addBeanFactoryPostProcessor(WebDriverScope::postProcessBeanFactory);
        }
    }

    private static void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        String[] var1 = BEAN_CLASSES;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String beanClass = var1[var3];
            String[] var5 = beanFactory.getBeanNamesForType(ClassUtils.resolveClassName(beanClass, (ClassLoader)null));
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String beanName = var5[var7];
                BeanDefinition definition = beanFactory.getBeanDefinition(beanName);
                if (!StringUtils.hasLength(definition.getScope())) {
                    definition.setScope("webDriver");
                }
            }
        }

    }

    static WebDriverScope getFrom(ApplicationContext context) {
        if (context instanceof ConfigurableApplicationContext configurableContext) {
            Scope scope = configurableContext.getBeanFactory().getRegisteredScope("webDriver");
            WebDriverScope var10000;
            if (scope instanceof WebDriverScope webDriverScope) {
                var10000 = webDriverScope;
            } else {
                var10000 = null;
            }

            return var10000;
        } else {
            return null;
        }
    }
}
