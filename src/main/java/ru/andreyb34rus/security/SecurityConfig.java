package ru.andreyb34rus.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.andreyb34rus.security.handler.LoginSuccessHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService; // сервис, с помощью которого тащим пользователя
    private final LoginSuccessHandler loginSuccessHandler; // класс, в котором описана логика перенаправления пользователей по ролям

    @Autowired
    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                          LoginSuccessHandler loginSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*// Назначение паролей и ролей прямо в коде
        auth.inMemoryAuthentication().withUser("Bob").password("12345").roles("ROLE_ADMIN");
        auth.inMemoryAuthentication().withUser("Rob").password("12345").roles("ROLE_USER");*/
        auth.authenticationProvider(daoAuthenticationProvider());
    }
//    // Этот метод пропускает ошибку логина обратно на форму и направляет по ссылке экшена
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // необходимо для работы css и прочих ресурсов
//        web.ignoring().antMatchers("/resources/**").anyRequest();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() //customizing login form
                .loginPage("/showLoginPage")    // указываем страницу с формой логина
                .successHandler(loginSuccessHandler)  //указываем логику обработки при логине
                .loginProcessingUrl(
                        "/showLoginPage") // указываем action (URL) который примет POST запрос от формы. Можно указать любое имя
                // спринг реализует обработку самостоятельно.
                .usernameParameter("j_username")   // Указываем параметры логина и пароля с формы логина
                .passwordParameter("j_password")
                //.failureUrl("//showLoginPage?error=true")
                .permitAll();  // даем доступ к форме логина всем. No need to be logged in.

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                //выключаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable();

        // делаем страницу регистрации недоступной для авторизированных пользователей
        http.authorizeRequests()  // restrict access based on HttpServletRequest
                .antMatchers("/showLoginPage").anonymous()  //страницы аутентификации доступна всем
                .antMatchers("/resources/**").permitAll() // для работы css
                // защищенные URL
                .antMatchers("/hello")
                .access("hasAnyRole('ADMIN')")
                .anyRequest().authenticated();// any request to app must be authenticated
    }

    // Необходимо для шифрования паролей
    // В данном примере не используется, отключен
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
