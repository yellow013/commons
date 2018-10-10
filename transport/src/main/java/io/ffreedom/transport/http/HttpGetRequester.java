package io.ffreedom.transport.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.transport.base.role.Requester;

public class HttpGetRequester implements Requester<String> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

	private final CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();

	String url;

	public HttpGetRequester(String url) {
		this.url = url;
	}

	public String sendGetRequest(HttpUri uri) {
		try {
			HttpGet httpGet = new HttpGet(url + uri);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			try {
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode > 307) {
					throw new RuntimeException(
							"Exception -> Request URI: [" + httpGet.getURI() + "] return status code " + statusCode);
				}
				return EntityUtils.toString(response.getEntity(), Charsets.UTF8);
			} finally {
				response.close();
			}
		} catch (Exception e) {
			throw new RuntimeException("Exception -> " + e.getMessage());
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String request() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean destroy() {
		// TODO Auto-generated method stub
		return false;
	}

}
