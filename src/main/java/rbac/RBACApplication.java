package rbac;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author MiracloW
 */
@SpringBootApplication
@MapperScan(basePackages = "rbac.mapper")
public class RBACApplication {

    public static void main(String[] args){
        SpringApplication.run(RBACApplication.class, args);
    }
}
