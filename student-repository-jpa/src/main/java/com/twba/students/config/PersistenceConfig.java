package com.twba.students.config;

import com.twba.students.StudentRepository;
import com.twba.students.StudentRepositoryJpa;
import com.twba.students.db.StudentRepositoryJpaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
        "com.twba.students"
})
@EntityScan(basePackages = {
        "com.twba.students"
})
@EnableJpaRepositories(basePackages = {
        "com.twba.students"
})
public class PersistenceConfig {

    @Bean
    public StudentRepository studentRepository(@Autowired StudentRepositoryJpaHelper studentRepositoryJpaHelper) {
        return new StudentRepositoryJpa(studentRepositoryJpaHelper);
    }

}
