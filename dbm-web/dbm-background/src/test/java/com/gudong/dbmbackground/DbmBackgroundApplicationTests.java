package com.gudong.dbmbackground;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
class DbmBackgroundApplicationTests {

	@Autowired
	FastFileStorageClient fastFileStorageClient;


	//测试删除图片
	@Test
	void delete() {
		fastFileStorageClient.deleteFile("/M00/00/00/wKgFDGCxBnCAV2xTAABdrZgsqUU690_big.jpg");
		System.out.println("deleted successfully!!!");
	}

	//测试上传图片
	@Test
	void contextLoads() throws FileNotFoundException {
		File file = new File("D:\\Downloads\\008.png");
		String name = file.getName();
		StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(new FileInputStream(file),
				file.length(), name.substring(name.lastIndexOf(".")+1), null);
		System.out.println(storePath.getGroup());
		System.out.println(storePath.getPath());
		System.out.println(storePath.getFullPath());
		/*
		 * 测试成功返回数据：
		 * gudong
		 * M00/00/00/wKgFDGCw8xaAbNr7AABLh3eTJkg803.png
		 * gudong/M00/00/00/wKgFDGCw8xaAbNr7AABLh3eTJkg803.png
		 */
	}

}
