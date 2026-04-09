package css.ch.smartfridgejavabackend;

import org.springframework.boot.SpringApplication;

public class TestSmartFridgeJavaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.from(SmartFridgeJavaBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
