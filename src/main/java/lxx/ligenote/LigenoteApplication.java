package lxx.ligenote;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("lxx.ligenote.mapper")
public class LigenoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(LigenoteApplication.class, args);
    }

}
