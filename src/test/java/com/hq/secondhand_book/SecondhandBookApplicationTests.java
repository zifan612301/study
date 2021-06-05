package com.hq.secondhand_book;

import com.hq.secondhand_book.service.BookCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecondhandBookApplicationTests {

    @Autowired
    BookCategoryService bookCategoryService;
    @Test
    public void contextLoads() {
        System.out.println(bookCategoryService.findAll());
    }

}
