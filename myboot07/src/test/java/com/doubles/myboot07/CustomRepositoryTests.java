package com.doubles.myboot07;

import com.doubles.myboot07.persistence.CustomCrudRepository;
import com.doubles.myboot07.persistence.CustomCrudRepositoryImpl;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class CustomRepositoryTests {

    @Autowired
    CustomCrudRepository repo;


    @Test
    public void test1() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");

        String type = "t";
        String keyword = "10";

        Page<Object[]> result = repo.getCustomPage(type, keyword, pageable);

        log.info("" + result);

        log.info("TOTAL PAGES : " + result.getTotalPages());

        log.info("TOTAL SIZE : " + result.getTotalElements());

        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });
    }

    @Test
    public void testWriter() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
        String type = "w";
        String keyword = "user01";
        Page<Object[]> result = repo.getCustomPage(type, keyword, pageable);

        log.info("" + result);
        log.info("TOTAL PAGE : " + result.getTotalPages());
        log.info("TOTAL SIZE : " + result.getTotalElements());
        result.getContent().forEach(arr-> {
            log.info(Arrays.toString(arr));
        });
    }

}
