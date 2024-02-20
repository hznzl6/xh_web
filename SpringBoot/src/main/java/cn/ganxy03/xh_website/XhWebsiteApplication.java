package cn.ganxy03.xh_website;

import cn.ganxy03.xh_website.service.ShetuanIntroService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration.class)
@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class XhWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(XhWebsiteApplication.class, args);

//        ApplicationContext context = SpringApplication.run(XhWebsiteApplication.class, args);
//
//        ShetuanIntroService shetuanIntroService = context.getBean(ShetuanIntroService.class);
//
//        shetuanIntroService.SetZero();
    }

}
