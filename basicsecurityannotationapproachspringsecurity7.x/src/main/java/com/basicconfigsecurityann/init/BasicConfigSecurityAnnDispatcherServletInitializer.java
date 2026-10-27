package com.basicconfigsecurityann.init;

import com.basicconfigsecurityann.config.BasicConfigSecurityAnnRootConfig;
import com.basicconfigsecurityann.config.BasicConfigSecurityAnnSecurityConfig;
import com.basicconfigsecurityann.config.BasicConfigSecurityAnnWebMvcConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class BasicConfigSecurityAnnDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {

        return new Class[]{BasicConfigSecurityAnnRootConfig.class, BasicConfigSecurityAnnSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{BasicConfigSecurityAnnWebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"*.htm"};
    }
}
