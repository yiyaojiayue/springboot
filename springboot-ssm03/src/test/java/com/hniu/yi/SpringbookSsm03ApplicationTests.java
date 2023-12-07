package com.hniu.yi;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hniu.yi.dao.BookDao;
import com.hniu.yi.domain.Book;
import com.hniu.yi.service.IBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbookSsm03ApplicationTests {


	@Autowired
	private BookDao bookDao;

	@Autowired
	private IBookService iBookService;


	@Test
	void contextLoads() {

		System.out.println(bookDao.selectById(1));

	}
	@Test
	void Page(){
		String name = "西游记";
        // 实现分页功能
		Page page = new Page(1, 5);
		// 增加条件
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.like("name", name);
		// 方式二
		LambdaQueryWrapper<Book> bookLambdaQueryWrapper = new LambdaQueryWrapper<>();
		bookLambdaQueryWrapper.like(Book::getName, name);
		bookDao.selectPage(page,queryWrapper);
		page.getRecords().forEach(System.out::println);
	}

	@Test
	void anyone(){

		List<Book> list = iBookService.list();
		list.forEach(System.out::println);

	}

}
