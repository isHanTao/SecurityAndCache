package com.hant.words;

import com.hant.words.bean.Book;
import com.hant.words.bean.Word;
import com.hant.words.bean.domain.User;
import com.hant.words.dao.UserDao;
import com.hant.words.dao.WordDao;

import com.hant.words.service.WordElasticSearchService;
import com.hant.words.util.SpringBeanUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordsApplicationTests {

    @Autowired
    WordDao dao;
//    @Autowired
//    WordElasticSearchService wordService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate<String,Object> objectRedisTemplate;
    @Test
    public void test01(){
        stringRedisTemplate.opsForValue().set("hant","hant");
        System.out.println(stringRedisTemplate.opsForValue().get("hant"));
    }

    @Test
    public void test02(){
        User user = new User();
        user.setId(1);
        user.setUsername("hant");
        user.setPassword("111111");
        objectRedisTemplate.opsForValue().set("hant",user);
        System.out.println(objectRedisTemplate.opsForValue().get("hant"));
    }
    @Test
    public void test(){
        System.out.println(passwordEncoder.encode("hant"));
    }
    @Test
    public void userDaoTest(){
        System.out.println(userDao.findUserByName("hant"));
    }

//    @TestController
//    public void contextLoads() {
//        Book book = new Book();
//        book.setName("西游记");
//        book.setPrice(123f);
//        book.setId(1);
//        service.save(book);
//    }
//    @TestController
//    public void search(){
//        Iterable<Book> all = service.findAll();
//        for (Book book :
//                all) {
//            System.out.println(book);
//        }
//    }
//    @Test
//    public void wordUp(){
//        List<Word> all = dao.findAll();
//        int i = 0;
//        for (Word word :
//                all) {
//            System.out.println(i++);
//            wordService.save(word);
//        }
//    }

//    @TestController
//    public void searchWord(){
//        Iterable<Word> all = wordService.findAll();
//        for (Word word:all
//        ) {
//            System.out.println(word);
//        }
//    }
    @Test
    public void daoTest(){
        Word word = dao.findById(10000);

        System.out.println(word);
    }
//    @Test
//    public void delete(){
//        wordService.deleteAll();
//        System.out.println("删除成功");
//        System.out.println(Thread.currentThread().getThreadGroup().activeCount());
//    }
//    @Test
//    public void wordUp2(){
//            List<Word> byLimit = dao.findByLimit(77 * 10000);
//            wordService.saveAll(() -> byLimit.iterator());
//    }


//    @Test
//    public void wordUpMany(){
//        for (int i = 10; i < 20; i++) {
//            MyThread myThread = new MyThread();
//            myThread.setStart(i*10000);
//            myThread.start();
//        }
//        try {
//            Thread.currentThread().join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }


//    @Service
//    class MyThread extends Thread{
//        private int start;
//        private List<Word> byLimit;
////        private WordDao dao;
////        @Autowired
////        private WordElasticSearchService wordService;
//
//
//        public void setStart(int start) {
//            this.start = start;
//
//        }
//
//        @Override
//        public void run() {
//            byLimit = dao.findByLimit(start);
//            System.out.println("线程" + this.getName() +"从"+start+"个开始工作 ，一共有" + byLimit.size());
//            int i = 0;
//            for (Word w :
//                    byLimit) {
//                try{
//                    wordService.save(w);
//                    i++;
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//            System.out.println("线程" + this.getName() +"做了" + i);
//        }
//    }
}
