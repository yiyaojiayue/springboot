package com.hniu.yi;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hniu.yi.dao.BookDao;
import com.hniu.yi.dao.UserDao;
import com.hniu.yi.domain.Book;
import com.hniu.yi.domain.User;
import com.hniu.yi.service.IBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class SpringbookSsm03ApplicationTests {


	@Autowired
	private BookDao bookDao;

	@Autowired
	private IBookService iBookService;

	@Autowired
	private UserDao userDao;


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

	@Test
	void UserText01(){
		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(User::getUserName,"张三");
		User user = userDao.selectOne(wrapper);
		System.out.println(user);
	}

	@Test
	void PasswordTest01(){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encode = passwordEncoder.encode("123123");
		System.out.println(encode);
		System.out.println(passwordEncoder.matches("123123",encode));
	}

}
