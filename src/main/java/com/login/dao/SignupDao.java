package com.login.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.login.bean.SignupBean;


public class SignupDao {
	
	
	public void setSignup(SignupBean signupBean) throws IOException {
		
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
		IndexRequest indexRequest = new IndexRequest("test");
		
		indexRequest.id(signupBean.getUuid());
		indexRequest.source("email",signupBean.getEmail(),"password",signupBean.getPwd(),"salt",signupBean.getSalt());
		IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
		System.out.println("response id: "+indexResponse.getId());
		System.out.println("response name: "+indexResponse.getResult().name());
		
		
			 	     
	}
	
	public boolean checkEmail(String email) throws IOException
	{
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();
		Map<String,String> fields = new HashMap<>();
		fields.put("email", email);
		for (Map.Entry<String, String> entry : fields.entrySet()){
		    boolQuery.must(QueryBuilders.termQuery(entry.getKey()+".keyword", entry.getValue()));
		}
		sourceBuilder.query(boolQuery);
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("test");
		searchRequest.source(sourceBuilder);
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		System.out.println(searchResponse);
		
		return searchResponse.getHits().getHits().length==1;
	}
	

	
}
