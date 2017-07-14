package cn.db.psql;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class Client {

	private static final Logger logger = LoggerFactory.getLogger(Client.class);
	// 发令枪
	CountDownLatch cdl = new CountDownLatch(10);
	// 锁
	ReentrantLock rtl = new ReentrantLock();
	// 加载配置
	private static ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private static PersonRepository personRepository = (PersonRepository) ctx
			.getBean("personRepository");
	private static PersonJpaSpecificationExecutor personJpaSpecificationExecutor = (PersonJpaSpecificationExecutor) ctx
			.getBean("personJpaSpecificationExecutor");

	public static void main(String[] args) throws Exception {
		try {
			// save();
			// SaveInBatch();
			// delete();
			// deleteInBatch();
			// find();
			// update();
			// findOne();
			// findAll();
			// findByCondition();
			// findByUserName();
			// findByPage();
			findByPageAndCondition();

		} catch (Exception e) {
			logger.error(e.toString());
		} finally {
			ctx.close();
		}
	}

	// 新增
	public static void save() {
		Person person = new Person();
		Random random = new Random();
		person.setCity_id(random.nextInt(1000));
		person.setStype(String.valueOf(random.nextInt(1000)));
		person.setUsername(String.valueOf(random.nextInt(1000)));
		Person save = personRepository.save(person);

	}

	// 批量新增
	private static List<Person> SaveInBatch() {

		List<Person> personList = new ArrayList<Person>();
		for (int i = 0; i < 3; i++) {
			Person person = new Person();
			Random random = new Random();
			person.setCity_id(random.nextInt(1000));
			person.setStype(String.valueOf(random.nextInt(1000)));
			person.setUsername(String.valueOf(random.nextInt(1000)));
			personList.add(person);
		}

		List<Person> resList = personRepository.save(personList);
		return resList;
	}

	// 删除
	private static void delete() {
		personRepository.delete(1);
	}

	// 批量删除
	private static void deleteInBatch() {
		List<Person> personList = new ArrayList<Person>();
		for (int i = 0; i < 10; i++) {
			Person person = new Person();
			Random random = new Random();
			person.setUserid(random.nextInt(1000));
			personList.add(person);
		}
		personRepository.deleteInBatch(personList);
	}

	// 修改
	private static void update() {
		Person person = new Person();
		Random random = new Random();
		person.setUserid(12);
		person.setCity_id(random.nextInt(1000));
		person.setStype(String.valueOf(random.nextInt(1000)));
		person.setUsername(String.valueOf(random.nextInt(1000)));
		boolean exists = personRepository.exists(12);
		if (exists) {
			personRepository.saveAndFlush(person);
		}

	}

	// 查询 全部
	public static void findAll() {
		List<Person> findAll = personRepository.findAll();
		for (Person person : findAll) {
			System.out.println(person.toString());

		}
	}

	// 根据uid 查询
	public static void findOne() {
		Person person = personRepository.findOne(12);
		System.out.println(person.toString());
	}

	// 条件查询
	public static void findByCondition() {
		List<Person> personList = personRepository.findByCondition(12, "1");
		if (null != personList && personList.size() > 0) {
			for (Person person : personList) {
				System.out.println("-------------" + person.toString()
						+ "-------------");
			}
		} else {
			System.out.println("-------------------没有查询到结果------------------");
		}
	}

	public static void findByUserName() {
		List<Person> personList = personRepository.findByUserName("1");
		if (null != personList && personList.size() > 0) {
			for (Person person : personList) {
				System.out.println("-------------" + person.toString()
						+ "-------------");
			}
		} else {
			System.out.println("-------------------没有查询到结果------------------");
		}
	}

	// 分页查询
	public static void findByPage() {
		Integer page = 0;
		Integer size = 5;
		Sort sort = new Sort(Direction.ASC, "userid");
		Pageable pageable = new PageRequest(page, size, sort);
		Page<Person> personPage = personRepository.findAll(pageable);
		List<Person> content = personPage.getContent();
		for (Person person : content) {
			System.out.println(person.toString());
		}
	}

	// 分页条件查询
	public static void findByPageAndCondition() {
		Integer page = 0;
		Integer size = 5;
		Sort sort = new Sort(Direction.ASC, "userid");
		Pageable pageable = new PageRequest(page, size, sort);
		PersonSpecification personSpecification = new PersonSpecification("1",
				"1", 515);
		Page<Person> personPage = personJpaSpecificationExecutor.findAll(
				personSpecification, pageable);
		List<Person> content = personPage.getContent();
		for (Person person : content) {
			System.out.println(person.toString());
		}
	}

}
