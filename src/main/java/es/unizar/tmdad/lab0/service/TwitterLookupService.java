package es.unizar.tmdad.lab0.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.social.twitter.api.Stream;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import es.unizar.tmdad.lab0.controller.SimpleStreamListener;

@Service
public class TwitterLookupService {
	
	private static final int MAX_STREAMS = 10;
	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	private static Map<String, Stream> queries = new HashMap<String, Stream>();
	
	@Value("${twitter.consumerKey}")
	private String consumerKey;
	
	@Value("${twitter.consumerSecret}")
	private String consumerSecret;
	
	@Value("${twitter.accessToken}")
	private String accessToken;
	
	@Value("${twitter.accessTokenSecret}")
	private String accessTokenSecret;
	
	public void search(String query) {
		
		if(!queries.containsValue(query)){
			if(queries.size() >= MAX_STREAMS){
			String clave = queries.keySet().iterator().next();
			queries.get(clave).close();
			queries.remove(clave);
			}
		}
			
		Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
		List<StreamListener> list = new ArrayList<StreamListener>();
		list.add(new SimpleStreamListener(messagingTemplate, query));
		Stream s = twitter.streamingOperations().filter(query, list);
		queries.put(query, s);
	}
}
