package com.example.multipleentitymanagers;

import com.example.multipleentitymanagers.models.h2.Order;
import com.example.multipleentitymanagers.models.mysql.Store;
import com.example.multipleentitymanagers.repositories.h2.OrderRepository;
import com.example.multipleentitymanagers.repositories.mysql.StoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MultiDatabasesTest {

	@Autowired
	private OrderRepository userRepository;

	@Autowired
	private StoreRepository storeRepository;

	@AfterEach
	void tearDown() {
		userRepository.deleteAll();
		storeRepository.deleteAll();
	}

	@Test
	void should_save_a_new_user() {
		Order order = new Order("John");
		userRepository.save(order);
		Assertions.assertNotNull(order.getId());
	}

	@Test
	void should_save_a_new_store() {
		Store store = new Store("Shoes Store");
		storeRepository.save(store);
		Assertions.assertNotNull(store.getId());
	}
}
