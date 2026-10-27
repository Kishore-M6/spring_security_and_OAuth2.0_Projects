package com.formbasedsec.init;


import com.formbasedsec.config.FormBasedLoginSecurityConfig;
import com.formbasedsec.config.FormBasedLoginSecurityRootConfig;
import com.formbasedsec.config.FormBasedLoginSecurityWebMvcConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class BasicConfigSecurityAnnDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {

        return new Class[]{FormBasedLoginSecurityRootConfig.class, FormBasedLoginSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{FormBasedLoginSecurityWebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"*.htm"};
    }
}
