package pl.wizyg.payroll.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class PayrollSecurityConfig extends WebSecurityConfigurerAdapter {

    final
    DataSource dataSource;

    public PayrollSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


//        auth.inMemoryAuthentication().withUser("wizyg").password("{noop}wizyg").roles("ADMIN");

        auth.jdbcAuthentication().dataSource(dataSource);


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/userAuth")
                .permitAll()
                .and()
                .logout().permitAll();

    }
}
