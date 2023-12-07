package com.hniu.yi;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hniu.yi.dao.BookDao;
import com.hniu.yi.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootMybatisplusApplicationTests {

	@Autowired
	private BookDao bookDao;

	@Test
	void contextLoads() {

		bookDao.selectList(null).forEach(System.out::println);

	}
}
