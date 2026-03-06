package com.fluxpay.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.fluxpay.core.orm.**.mapper")
@ComponentScan(basePackages = {
        "com.fluxpay.core.orm",
        "com.fluxpay.admin",
        "com.fluxpay.common"
})
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
