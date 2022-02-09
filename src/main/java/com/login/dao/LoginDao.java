package com.login.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.login.bean.LoginBean;

public class LoginDao {
	
	
	public String[] checkLogin(LoginBean loginBean) throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));


		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();
		Map<String,String> fields = new HashMap<>();
		fields.put("email", loginBean.getEmail());
		for (Map.Entry<String, String> entry : fields.entrySet()){
		    boolQuery.must(QueryBuilders.termQuery(entry.getKey()+".keyword", entry.getValue()));
		}
		sourceBuilder.query(boolQuery);
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("test");
		searchRequest.source(sourceBuilder);
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		System.out.println(searchResponse);
				
		SearchHit[] hits = searchResponse.getHits().getHits();
		String[] res = new String[3];
			
		
		res[0]= String.valueOf(hits.length==1);
		if(res[0].equals("true")) {
			res[1]= hits[0].getSourceAsMap().get("password").toString();
			res[2]= hits[0].getSourceAsMap().get("salt").toString();
		}
		
		return res;
		
		
	}
	
}
