package eu.epitech.area.config;

/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.github.connect.GitHubConnectionFactory;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

/**
 * Spring Social Configuration.
 *
 * @author Craig Walls
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {
    @Inject
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Environment environment;

    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();

        registry.addConnectionFactory(new GitHubConnectionFactory(
                environment.getProperty("spring.social.github.appId"),
                environment.getProperty("spring.social.github.appSecret")));

        registry.addConnectionFactory(new FacebookConnectionFactory(
                environment.getProperty("spring.social.facebook.appId"),
                environment.getProperty("spring.social.facebook.appSecret")));

        registry.addConnectionFactory(new TwitterConnectionFactory(
                environment.getProperty("spring.social.twitter.appId"),
                environment.getProperty("spring.social.twitter.appSecret")));

        return registry;
    }

    @Bean
    public TextEncryptor textEncryptor() {
        return Encryptors.noOpText();
    }

    @Bean
    public UsersConnectionRepository usersConnectionRepository() {
        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(), textEncryptor());
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public ConnectionRepository connectionRepository() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
        }
        return usersConnectionRepository().createConnectionRepository(authentication.getName());
    }
}