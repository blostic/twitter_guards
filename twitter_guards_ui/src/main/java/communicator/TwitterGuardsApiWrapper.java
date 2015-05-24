package communicator;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;

import persistance.campaign.entity.Campaign;
import persistance.config.PropertiesManager;

public class TwitterGuardsApiWrapper {

	public void sendHttpRequest(Campaign campaign) throws ClientProtocolException, IOException {
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(PropertiesManager.getProperty("twitter_service_address") + "rest/twitterCampaigns/add");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(campaign);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(new BasicNameValuePair("campaign_json", json));
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {
				StringWriter writer = new StringWriter();
				IOUtils.copy(instream, writer, "utf-8");
				String result = writer.toString();
				System.out.println(result);
			} finally {
				instream.close();
			}
		}
		
	}
	
}