package com.formbasedsecindb.init;

import com.formbasedsecindb.config.FormBasedSecurityUsingDBRootConfig;
import com.formbasedsecindb.config.FormBasedSecurityUsingDBSecurityConfig;
import com.formbasedsecindb.config.FormBasedSecurityUsingDBWebMvcConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class BasicConfigSecurityAnnDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {

        return new Class[]{FormBasedSecurityUsingDBSecurityConfig.class, FormBasedSecurityUsingDBRootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{FormBasedSecurityUsingDBWebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"*.htm"};
    }
}
