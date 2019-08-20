package com.ansimpleasy.agave.ans;

import com.ansimpleasy.agave.ans.common.CredentialsMatcher;
import com.ansimpleasy.agave.ans.entity.User;
import com.ansimpleasy.agave.ans.enums.Status;
import com.ansimpleasy.agave.ans.service.impl.UserServiceImpl;
import org.nutz.lang.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
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
                User account = userService.getByName("admin");
                if (Lang.isEmpty(account)) {
                    account = new User();
                    account.setName("admin");
                    account.setStatus(Status.VALID.name());
                    account.setPassword(CredentialsMatcher.password(String.valueOf(account.getName()), "G00dl^ck"));
                    userService.save(account);
                }

            }
        };
    }

}
