package com.test.web.Util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

public class HttpClientUtil {


	public static boolean doUploadClientforfile(byte[] file, File existfile) throws Exception{

		FileOutputStream fos = null;
		BufferedInputStream bis = null;
//		existfile.renameTo(new File(fileNewName));
		try {
			bis = new BufferedInputStream(new ByteArrayInputStream(file));
			fos = new FileOutputStream(existfile);
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage()+","+e.getStackTrace());
			return false;
		}finally{
			fos.close();
			bis.close();
		}


		System.out.println("上传图片成功======================");
		return true;
	}


	//post 参数 json ， Map 
	public static String post(String url,String json,Map<String,String> paramters)  {
		RequestConfig config = null;
		CloseableHttpClient client = null;
		HttpPost hp = null;
		try {
			config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
			client = HttpClients.custom().setDefaultRequestConfig(config).build();

			hp = new HttpPost(url);
			if(paramters != null && !paramters.isEmpty()){
				List<Namevaluepairforhttp> formparams = new ArrayList<Namevaluepairforhttp>();
				for(Entry<String,String> entry : paramters.entrySet()){
					Namevaluepairforhttp formparam = new Namevaluepairforhttp();
					formparam.setName(entry.getKey());
					formparam.setValue(entry.getValue());
					formparams.add(formparam);
				}
				UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
				hp.setEntity(uefEntity);
			}
			if(json != null && json.trim().length() > 0){
				List<Namevaluepairforhttp> formparams = new ArrayList<Namevaluepairforhttp>();
				Namevaluepairforhttp formparam = new Namevaluepairforhttp();
				formparam.setName("data");
				formparam.setValue(json);
				formparams.add(formparam);
				UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
				hp.setEntity(uefEntity);
			}
			HttpResponse response = client.execute(hp);
			String resultjson = HttpClientUtil.paseResponse(response);
			return resultjson;
		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if(null != hp){
				try {
					hp.releaseConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(client != null){
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	//post 参数 json ， Map
	public static byte[] post_1(String url,String json,Map<String,String> paramters){

		org.apache.commons.httpclient.HttpClient hc = null;
		PostMethod mPost = null;
		byte[] responseBody = new byte[0];
		try {
			org.apache.commons.httpclient.params.HttpClientParams params = new org.apache.commons.httpclient.params.HttpClientParams();
			params.setSoTimeout(50000);//设置读数据超时时间
			params.setConnectionManagerTimeout(50000);//设置连接超时时间
			hc = new org.apache.commons.httpclient.HttpClient(params,new SimpleHttpConnectionManager(true));
			mPost = new PostMethod(url);
			if(!Util.isEmpty(json)){
				org.apache.commons.httpclient.methods.RequestEntity requestEntity = new StringRequestEntity(json,"text/xml","UTF-8");
				mPost.setRequestEntity(requestEntity);
			}
			if(paramters != null && paramters.size() > 0){
				for(Entry<String, String> entry : paramters.entrySet()){
					mPost.addParameter(entry.getKey(), entry.getValue());
				}
			}
			mPost.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			int status = hc.executeMethod(mPost);
			System.out.println(url + "[" + status +"]");
			if(status == HttpStatus.SC_OK) {
				responseBody = mPost.getResponseBody();
			} else {
				responseBody = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			responseBody = null;
		} finally{
			if(mPost != null){
				mPost.releaseConnection();
			}
		}

		return responseBody;
	}

	public static String postfile(String url,byte[] filedata)  {
		RequestConfig config = null;
		CloseableHttpClient client = null;
		try {
			config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
			client = HttpClients.custom().setDefaultRequestConfig(config).build();

			HttpPost hp = new HttpPost(url);
			hp.setEntity(new ByteArrayEntity(filedata));
			HttpResponse response = client.execute(hp);
			String resultjson = HttpClientUtil.paseResponse(response);
			return resultjson;
		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if(client != null){
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}


	public static String get(String url) {
		CloseableHttpClient client = null;
		RequestConfig config = null;
		HttpGet hp = null;
		try {
			config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
			client = HttpClients.custom().setDefaultRequestConfig(config).build();
			hp = new HttpGet(url);
			HttpResponse response = client.execute(hp);
			String resultjson = HttpClientUtil.paseResponse(response);
			return resultjson;
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			if(null != hp){
				try {
					hp.releaseConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public static String paseResponse(HttpResponse response) {
		//log.info("get response from http server..");
		HttpEntity entity = response.getEntity();

//        log.info("response status: " + response.getStatusLine());
		String charset = EntityUtils.getContentCharSet(entity);
//        log.info(charset);

		String body = null;
		try {
			body = EntityUtils.toString(entity);
//            log.info(body);
		}catch (IOException e) {
			e.printStackTrace();
		}

		return body;
	}

	public static <T> Object getRequestdate(byte[] responseBody,Class<T> clazz,Map<String,Class<T>> classMap){

		if(responseBody == null || responseBody.length == 0){
			return null;
		}

		JSONObject jo = JSONObject.fromObject(responseBody);

		Object ccb = JSONObject.toBean(jo, clazz, classMap);
		return ccb;
	}

	public static <T> Object getRequestdate(byte[] responseBody,TypeReference<T> typeref) throws Exception{

		String s3 = new String(responseBody,"utf-8");

		return getRequestdate(s3, typeref);
	}

	public static <T> List<T> getRequestdateList(String s3,
												 TypeReference<List<T>> typeref) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		// 去掉默认的时间戳格式
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		// 设置为中国北京时区
		objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		// 空值不序列化
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		// 反序列化时，属性不存在的兼容处理
		objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 单引号处理
		objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		return objectMapper.readValue(s3,typeref);
	}
	public static <T> Object getRequestdate(String s3,
											TypeReference<T> typeref) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		// 去掉默认的时间戳格式
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		// 设置为中国北京时区
		objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		// 空值不序列化
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		// 反序列化时，属性不存在的兼容处理
		objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 单引号处理
		objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		return objectMapper.readValue(s3,typeref);
	}

	public static <T> List<T> getRequestdateList(byte[] responseBody,
												 TypeReference<List<T>> typeref) throws Exception{
		String s3 = "";
		if(responseBody != null){
			s3 = new String(responseBody,"utf-8");
			return getRequestdateList(s3, typeref);
		} else {
			return new ArrayList<>();
		}
	}

}
