package com.gudong.dbmitemservice;

import com.gudong.dbm.service.IItemService;
import com.gudong.dbmitemservice.service.ItemServiceImpl;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DbmItemServiceApplicationTests {

	@Autowired
	IItemService itemService;


	@Test
	void createHTMLTest() throws IOException, TemplateException {
		List<Long> ids = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			ids.add((long) i);
		}

		itemService.BatchCreateHtmlById(ids);
		System.out.println("生成静态页面成功！");
	}
}
