package com.gudong.dbmsearchservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.gudong.dbm.entity.Product;
import com.gudong.dbm.mapper.ProductMapper;
import com.gudong.dbm.pojo.PageResultBean;
import com.gudong.dbm.pojo.ResultBean;
import com.gudong.dbm.service.ISearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author gudong
 * @description
 * @date 2021-06-01 11:06
 */
@Component
@Service
public class SearchServiceImpl implements ISearchService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    SolrClient solrClient;

    @Override
    public ResultBean initAllData() {
        List<Product> list = productMapper.list();
        for (Product pro : list) {
            if (pushSolr(pro)) {
                return new ResultBean(500, "同步数据失败！");
            }
        }
        try {
            solrClient.commit();
        } catch (SolrServerException  | IOException e) {
            e.printStackTrace();
            return new ResultBean(500,"同步数据失败！");
        }
        return new ResultBean();
    }

    @Override
    public ResultBean<List<Product>> searchByKeywords(String keyword) {
        SolrQuery queryCondition = new SolrQuery();
        queryCondition.setQuery("product_keywords:"+keyword);
        //实现高亮
        queryCondition.setHighlight(true);
        queryCondition.addHighlightField("product_name");
        queryCondition.setHighlightSimplePre("<font color='red'>");
        queryCondition.setHighlightSimplePost("</font>");
        QueryResponse response;
        List<Product> list = null;
        try {
            response = solrClient.query(queryCondition);
            SolrDocumentList results = response.getResults();
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            list = new ArrayList<>(results.size());
            for (SolrDocument document : results) {
                Product product = new Product();
                product.setId(Long.parseLong(document.get("id").toString()));
             //   product.setName(document.get("product_name").toString());
                product.setImage(document.get("product_images").toString());
                product.setSalePoint(document.get("product_sale_point").toString());
                product.setPrice(Long.parseLong(document.get("product_price").toString()));

                //设置高亮信息
                Map<String, List<String>> idMap = highlighting.get(document.get("id").toString());
                List<String> nameList = idMap.get("product_name");
                if(!nameList.isEmpty()){
                    product.setName(nameList.get(0));
                }else {
                    product.setName(document.get("product_name").toString());
                }
                list.add(product);
            }
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        return new ResultBean<List<Product>>().ok(list);
    }

    @Override
    public PageResultBean<Product> searchByKeywords(String keyword, Integer index, Integer size) {
        SolrQuery queryCondition = new SolrQuery();
        queryCondition.setQuery("product_keywords:"+keyword);
        //实现高亮
        queryCondition.setHighlight(true);
        queryCondition.addHighlightField("product_name");
        queryCondition.setHighlightSimplePre("<font color='red'>");
        queryCondition.setHighlightSimplePost("</font>");
        QueryResponse response;
        List<Product> list = null;
        try {
            response = solrClient.query(queryCondition);
            SolrDocumentList results = response.getResults();
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            list = new ArrayList<>(results.size());
            for (SolrDocument document : results) {
                Product product = new Product();
                product.setId(Long.parseLong(document.get("id").toString()));
                //   product.setName(document.get("product_name").toString());
                product.setImage(document.get("product_images").toString());
                product.setSalePoint(document.get("product_sale_point").toString());
                product.setPrice(Long.parseLong(document.get("product_price").toString()));

                //设置高亮信息
                Map<String, List<String>> idMap = highlighting.get(document.get("id").toString());
                List<String> nameList = idMap.get("product_name");
                if(!nameList.isEmpty()){
                    product.setName(nameList.get(0));
                }else {
                    product.setName(document.get("product_name").toString());
                }
                list.add(product);
            }
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        PageResultBean<Product> page = new PageResultBean<>();
        page.setList(list);
        page.setPageNum(index);
        page.setPageSize(size);
        page.setTotal(list.size());
        page.setPages((int) (page.getTotal()%size==0?page.getTotal()/size:(page.getTotal()/size)+1));
        return page;
    }

    @Override
    public ResultBean updateById(Long id) {
        Product pro = productMapper.selectByPrimaryKey(id);
        if (pushSolr(pro)) {
            System.out.println("同步数据失败");
            return new ResultBean(500, "同步数据失败！");
        }
        try {
            solrClient.commit();
        } catch (SolrServerException  | IOException e) {
            e.printStackTrace();
            return new ResultBean(500,"同步数据失败！");
        }
        return new ResultBean();
    }

    private boolean pushSolr(Product pro) {
        SolrInputDocument document = new SolrInputDocument();
        //需要有唯一标识 添加或修改
        document.setField("id", pro.getId());
        document.setField("product_name", pro.getName());
        document.setField("product_price", pro.getPrice());
        document.setField("product_sale_point", pro.getSalePoint());
        document.setField("product_images", pro.getImage());
        //提交
        try {
            solrClient.add(document);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }


}
