package com.ansimpleasy.ans;

import com.ansimpleasy.ans.common.CredentialsMatcher;
import com.ansimpleasy.ans.entity.User;
import com.ansimpleasy.ans.enums.Sex;
import com.ansimpleasy.ans.enums.Status;
import com.ansimpleasy.ans.service.impl.UserServiceImpl;
import org.nutz.lang.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AnsApplication {
    @Autowired
    private UserServiceImpl userService;

    public static void main(String[] args) {
        SpringApplication.run(AnsApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                // 创建超级管理员
                User account = userService.getByName("ANS");
                if (Lang.isEmpty(account)) {
                    account = new User();
                    account.setName("ANS");
                    account.setStatus(Status.VALID);
                    account.setSex(Sex.MALE);
                    account.setPassword(CredentialsMatcher.password(String.valueOf(account.getName()), "G00dl^ck"));
                    userService.save(account);
                }

            }
        };
    }

}
