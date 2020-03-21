package pl.wizyg.payroll.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class PayrollServletDispatcher extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {PayrollConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }
}
