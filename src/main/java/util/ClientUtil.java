package util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * 客户端工具
 * rest请求
 * @author rengl
 * 2016-02-26
 */
public class ClientUtil {
	
	/**
	 * get方式请求webService
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		String json = "";
		try {
			HttpResponse res = client.execute(get);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				HttpEntity entity = res.getEntity();
				json = EntityUtils.toString(entity, "UTF-8");
			}else{
				System.out.println("失败" + res.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接 ,释放资源
			client.getConnectionManager().shutdown();
		}
		return json;
	}	
	
	/**
	 * post方式请求webService
	 * @param url
	 * @param json
	 * @return
	 */
	public static String doPost(String url, String json) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		String result = "";
		try {
			StringEntity s = new StringEntity(json, "UTF-8");
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");// 发送jsn数据需要设置contentType
			post.setEntity(s);
			post.setHeader(HTTP.CONTENT_TYPE, "application/json");
			HttpResponse res = client.execute(post);
			System.out.println("==服务返回值==res.getStatusLine().getStatusCode()》》》》》》  "+res.getStatusLine().getStatusCode());
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result =EntityUtils.toString(res.getEntity(), "UTF-8");// 返回json格式：
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}
	
	/**
	 * post方式请求webService
	 * @param url
	 * @param json
	 * @return
	 */
	public static String doPostForm(String url, String json) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		String result = "";
		try {
			StringEntity s = new StringEntity(json, "UTF-8");
			s.setContentEncoding("UTF-8");
			s.setContentType("application/x-www-form-urlencoded");// 发送jsn数据需要设置contentType
			post.setEntity(s);
			post.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
			HttpResponse res = client.execute(post);
			System.out.println("==服务返回值==res.getStatusLine().getStatusCode()》》》》》》  "+res.getStatusLine().getStatusCode());
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result =EntityUtils.toString(res.getEntity(), "UTF-8");// 返回json格式：
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}	
}
