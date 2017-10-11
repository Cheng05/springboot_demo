package com.test.web.Util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class OssHelper {

	private static OssHelper ossHelper=null;
	
	private static Object lock=new Object();
	
	private static OSSClient client=null;
	
	private static String bucketname;
	
	public  static String endpoint="";
	public  static String URL = "";
	public  static  String ACCESS_ID="";
	public  static  String ACCESS_KEY = "";
	
	//private static Properties properties = ReadProperties.getProperties(RedisUtils.class, "config.properties");
	/**
	 * OSS外网域名: newenergycar.oss-cn-shanghai.aliyuncs.com
	 * OSS内网域名:newenergycar.oss-cn-shanghai-internal.aliyuncs.com
	 */
	
	private OssHelper(){
		client=new OSSClient(endpoint,ACCESS_ID,ACCESS_KEY);
		bucketname="newenergycar";
	}
	public static OssHelper getInstance(){
		synchronized (lock) {
			if (ossHelper==null){
				ossHelper=new OssHelper();
			}else{
				return ossHelper;
			}
		}
		return ossHelper;
	}
	
	
	public String putFile(String objectNamePath,String filename, String filePath){
		  // 获取指定文件的输入流
		String tag="-1";
	    try {
	    	System.out.println("objectNamePath:"+objectNamePath+"| filename:"+filename+" | filePath:"+filePath );
			File file = new File(filePath);
			
			tag=putFile(objectNamePath, filename,  file);
			
			System.out.println("put over tag:"+tag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    return tag;
	}
	/**
	 * 上传图片到OSS
	 * @param objectNamePath:上级目录名称
	 * @param filename：文件名
	 * @param file：文件
	 * @return
	 */
	public String putFile(String objectNamePath,String filename, File file){
		String tag="-1";
	    try {
			InputStream content = new FileInputStream(file);
			 // 创建上传Object的Metadata
			ObjectMetadata meta = new ObjectMetadata();
			// 必须设置ContentLength
			meta.setContentLength(file.length());
			
			System.out.println("bucketname:"+bucketname+" path:"+(objectNamePath+filename)+" meta:"+meta);
			
			PutObjectResult retsult = client.putObject(bucketname,objectNamePath+filename, content, meta);
			
			tag=retsult.getETag();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    return tag;
	}
	public File[] getFiles(String objectNamePath,String[] keys, String[] filenames){
		
		File[] fs=new File[filenames.length];
		
		for(int i=0;i<fs.length;i++){
			fs[i]=getFile(objectNamePath,keys[i],filenames[i]);
		}
		
		return fs;
		
	}
	
	public File getFile(String objectNamePath,String key, String filename){
		
		File f=new File(filename);
		
		client.getObject(new GetObjectRequest(bucketname, objectNamePath+key),
                f);
		
		return f;
	}
}
