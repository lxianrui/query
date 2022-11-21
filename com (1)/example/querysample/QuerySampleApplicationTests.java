package com.example.querysample;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.apache.commons.compress.utils.Lists;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.sl.usermodel.Sheet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.misc.Unsafe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class QuerySampleApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    void contextLoads() {
        List<User> list = new ArrayList<>(50_0000);

        for (int i = 0; i < 50_0000; i++) {
            User user = new User();
            user.setUserName(UUID.randomUUID().toString());
            user.setProp1(UUID.randomUUID().toString());
            user.setProp2(UUID.randomUUID().toString());
            user.setProp3(UUID.randomUUID().toString());
            user.setProp4(UUID.randomUUID().toString());
            user.setProp5(UUID.randomUUID().toString());
            user.setProp6(UUID.randomUUID().toString());
            user.setProp7(UUID.randomUUID().toString());
            user.setProp8(UUID.randomUUID().toString());
            user.setProp9(UUID.randomUUID().toString());
            list.add(user);
        }
        userService.saveBatch(list,10000);
    }

    @Test
    void test1() throws IOException {
//        String fileName = "./simpleWrite" + System.currentTimeMillis() + ".xlsx";
//        EasyExcel.write(fileName, User.class) .sheet("模板");
//        Unsafe unsafe = Unsafe.getUnsafe();
//        long allocateMemory = unsafe.allocateMemory(1024 * 100);
//        unsafe.putByte(allocateMemory,new Byte[]{});
//        try (ExcelWriter excelWriter = EasyExcel.write(fileName, User.class).build()) {
//            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//            List<User> userList = new ArrayList<>();
//            userMapper.list((resultContext -> {
//                resultContext.isStopped()
//                userList.add(resultContext.getResultObject());
//            }));
//            excelWriter.write(userList, writeSheet);
//        }
        userMapper.list((resultContext -> {
            System.out.println(resultContext.getResultCount());
            System.out.println(resultContext.isStopped());

           // userList.add(resultContext.getResultObject());
        }));
        System.out.println("ok!");
    }


}
