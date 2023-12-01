package com.hniu.yi;

import com.hniu.yi.dao.BookDao;
import oracle.jdbc.internal.OracleTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootTest
class SpringbookMybatis01ApplicationTests {

    @Autowired
    private BookDao bookDao;

    @Test
    void contextLoads() {
        System.out.println(bookDao.getById("李四"));
    }

    @Test
    void Test01() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //2.得到Connection连接
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "HLW", "030805");
        //3.得到预编译的Statement对象
        CallableStatement pstm = connection.prepareCall("{call 获取学生成绩(?, ?)}");
        //4.给参数赋值,第二个参数是一个输出类型的参数
        pstm.setObject(1, "李四");
        pstm.registerOutParameter(2, OracleTypes.VARCHAR);
        //执行数据库查询操作
        pstm.execute();
        //输出结果[第二个参数]
        System.out.println(pstm.getObject(2));
        //释放资源
        pstm.close();
        connection.close();

    }

}
