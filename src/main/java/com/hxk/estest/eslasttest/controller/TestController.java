package com.hxk.estest.eslasttest.controller;/*
 * @Author huangxk
 * @Description：
 * @Date
 **/

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hxk.estest.eslasttest.vo.ResponseBean;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@RestController
public class TestController {
    @Autowired
    private RestHighLevelClient rhlClient;

    //@Autowired
    Client client;

    private BoolQueryBuilder boolQueryBuilder = null;

    @RequestMapping("search")
    public ResponseBean searchInfoByCursor1(){
        SearchRequest searchRequest = new SearchRequest("shuji");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        System.out.println("heello ");
        //如果用name直接查询，其实是匹配name分词过后的索引查到的记录(倒排索引)；如果用name.keyword查询则是不分词的查询，正常查询到的记录
//        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("birthday").from("1991-01-01").to("2010-10-10").format("yyyy-MM-dd");//范围查询
////        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name.keyword", name);//精准查询
//        PrefixQueryBuilder prefixQueryBuilder = QueryBuilders.prefixQuery("name.keyword", "张");//前缀查询
////        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("name.keyword", "*三");//通配符查询
////        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", "三");//模糊查询
//        FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort("age");//按照年龄排序
//        fieldSortBuilder.sortMode(SortMode.MIN);//从小到大排序
//
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder.must(rangeQueryBuilder).should(prefixQueryBuilder);//and or  查询
//
//        sourceBuilder.query(boolQueryBuilder).sort(fieldSortBuilder);//多条件查询
//        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
//        searchRequest.source(sourceBuilder);
        sourceBuilder.query();

        try {
            SearchResponse response = rhlClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            JSONArray jsonArray = new JSONArray();
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                JSONObject jsonObject = JSON.parseObject(sourceAsString);
                jsonArray.add(jsonObject);
                System.out.println("输出"+jsonObject.toString());
            }
            return new ResponseBean(200,"查询成功",jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBean(10001, "查询失败", null);

        }

    }

}
