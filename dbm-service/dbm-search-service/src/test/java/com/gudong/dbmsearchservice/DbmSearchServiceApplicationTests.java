package com.gudong.dbmsearchservice;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class DbmSearchServiceApplicationTests {

	@Autowired
	SolrClient solrClient;

	@Test
	void addOrUpdate() throws IOException, SolrServerException {
		//作用域数据的同步操作，源头数据库改变，索引库也发生改变
		//solr里面操作的记录，document
		SolrInputDocument document = new SolrInputDocument();
		//需要有唯一标识 添加或修改
		document.setField("id", "12");
		document.setField("product_name", "华为手机");
		document.setField("product_price", "1999");
		document.setField("product_sale_point", "华为666");
		document.setField("product_images", "暂无");
		//提交
		//solrClient.add(document);
		//solrClient.commit();
		//提交到collection2
		solrClient.add("collection2",document);
		solrClient.commit("collection2");
	}

	@Test
	void queryTest() throws IOException, SolrServerException {
		//组装查询条件
		SolrQuery queryCondition = new SolrQuery();
		//queryCondition.setQuery("*:*");
		//是分词之后再匹配的 结果可能有多个
		queryCondition.setQuery("product_name:小米");
		final QueryResponse query = solrClient.query(queryCondition);
		final SolrDocumentList results = query.getResults();
		for (SolrDocument document : results) {
			System.out.println(document.get("id"));
			System.out.println(document.get("product_name"));
			System.out.println(document.get("product_sale_point"));
			System.out.println(document.get("product_keywords"));
		}
	}

	@Test
	void delByQuery() throws IOException, SolrServerException {
		solrClient.deleteByQuery("product_name:小米");
		solrClient.commit();
	}

}
