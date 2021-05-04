package com.alexnmat.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }

    //TODO: Fix database relationships, mostly MANY TO MANY. maybe ask pete?
    // https://stackoverflow.com/questions/39802264/jpa-how-to-persist-many-to-many-relation
    // https://stackoverflow.com/questions/19280121/spring-and-or-hibernate-saving-many-to-many-relations-from-one-side-after-form
    // https://thorben-janssen.com/hibernate-tips-map-bidirectional-many-many-association/
    //
}
