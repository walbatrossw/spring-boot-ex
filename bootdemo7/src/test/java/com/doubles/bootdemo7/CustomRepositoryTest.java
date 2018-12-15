package com.doubles.bootdemo7;

import com.doubles.bootdemo7.article.persistence.CustomCrudRepository;
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
public class CustomRepositoryTest {

    @Autowired
    CustomCrudRepository customCrudRepository;

    @Test
    public void test() {

        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "articleNo");

        String type = "w";
        String keyword = "user09";

        Page<Object[]> result = customCrudRepository.getCustomPage(type, keyword, pageable);

        log.info("" + result);
        log.info("TOTAL PAGES : " + result.getTotalPages());
        log.info("TOTAL SIZE : " + result.getTotalElements());

        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });
    }

}
