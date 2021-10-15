package com.task.restAPIproj;

import com.task.restAPIproj.entity.OrderEntity;
import com.task.restAPIproj.entity.PassportEntity;
import com.task.restAPIproj.entity.ProjectEntity;
import com.task.restAPIproj.entity.UserEntity;
import com.task.restAPIproj.model.Passport;
import com.task.restAPIproj.repository.OrderRepository;
import com.task.restAPIproj.repository.PassportRepository;
import com.task.restAPIproj.repository.ProjectRepository;
import com.task.restAPIproj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.stream.LongStream;

@SpringBootApplication
@EnableSwagger2
public class RestApIprojApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApIprojApplication.class, args);
    }

   @Bean
   public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
   }

    @Bean
    CommandLineRunner run(PassportRepository passportRepository) {
        return args -> LongStream.rangeClosed(1, 500).forEach(i -> {
            PassportEntity passport = new PassportEntity();
            passport.setNumber("BE" + i);
            passportRepository.save(passport);
        });
    }

    @Bean
    CommandLineRunner run1(ProjectRepository projectRepository) {
        return args -> LongStream.rangeClosed(1, 500).forEach(i -> {
            ProjectEntity projectEntity = new ProjectEntity();
            projectEntity.setTitle("simple" + i);
            projectRepository.save(projectEntity);
        });
    }

    @Bean
    CommandLineRunner run2(UserRepository userRepository, PassportRepository passportRepository) {
        return args -> LongStream.rangeClosed(1, 500).forEach(i -> {
            UserEntity userEntity = new UserEntity();
            userEntity.setName("Bob" + i);
            userEntity.setPassword("psw");
            userEntity.setSalary(i + 0.5);
            userRepository.save(userEntity);
        });
    }

    @Bean
    CommandLineRunner run3(OrderRepository orderRepository, UserRepository userRepository) {
        return args -> LongStream.rangeClosed(1, 500).forEach(i -> {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setAddress("Minsk" + i);
            orderEntity.setDescription("your desc" + i);
            orderEntity.setUser(userRepository.getById(i));
            orderRepository.save(orderEntity);
        });
    }

}

