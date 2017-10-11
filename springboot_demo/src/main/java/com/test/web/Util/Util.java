package com.test.web.Util;

import com.test.web.comm.ReturnValue;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	private static Properties properties = null;//ReadProperties.getProperties(RedisUtils.class, "config.properties");
	public static final String dtLong   = "yyyyMMddHHmmss";

	/**
	 * 设置token
	 * @param userid
	 * @param role: 1：企业  2：个人
	 * @return
	 */
	public static String   initToken(int userid,int role){
		String token = UUID.randomUUID().toString();
		int time_out =Integer.parseInt(properties.getProperty("login_time_out"));//6379;
		RedisUtils.setStrWithExpire(token,userid+"-"+role,time_out);
		return  token;
	}


	public static  void refreshToken(String token,String value){
		int time_out =Integer.parseInt(properties.getProperty("login_time_out"));//6379;
		RedisUtils.setStrWithExpire(token,value,time_out);

	}

	/**
	 * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
	 * @return
	 *      以yyyyMMddHHmmss为格式的当前系统时间
	 */
	public  static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}


	public static String getDate(String str){
		String str_return = "";
		if(str!=null&&str.length()==8){
			String yy = str.substring(0,4)+"年";
			String mm = str.substring(4,6)+"月";
			String dd = str.substring(6)+"日";
			str_return = yy+mm+dd;
		}
		return str_return;
	}

	/**
	 * 判断给定字符串是否空白串。
	 * 空白串是指由空格、制表符、回车符、换行符组成的字符串
	 * 若输入字符串为null或空字符串，返回true
	 * @param input
	 * @return boolean
	 */
	public static boolean isStrEmpty( String input ) 
	{
		if ( input == null || "".equals( input ) || "null".equals(input) || "[]".equals(input))
			return true;
		
		for ( int i = 0; i < input.length(); i++ ) 
		{
			char c = input.charAt( i );
			if ( c != ' ' && c != '\t' && c != '\r' && c != '\n' )
			{
				return false;
			}
		}
		return true;
	}
	
	public static String getDatebyYYMMDD(String str){
		String str_return = "";
		if(str!=null&&str.length()>=8){
			String yy = str.substring(0,4)+"年";
			String mm = str.substring(4,6)+"月";
			String dd = str.substring(6,8)+"日";
			str_return = yy+mm+dd;
		}
		return str_return;
	}
	
	public static String getDateyymm(String str){
		String str_return = "";
		if(str!=null&&str.length()==6){
			String yy = str.substring(0,4)+"年";
			String mm = str.substring(4,6)+"月";
			str_return = yy+mm;
		}
		return str_return;
	}
	
	public static String getDateyymm2(String str){
		String str_return = "";
		if(str!=null&&str.length()==6){
			String yy = str.substring(2,4)+"年";
			String mm = str.substring(4,6)+"月";
			str_return = yy+mm;
		}
		return str_return;
	}
	
	public static String getDateyymm1(String str){
		String str_return = "";
		if(str!=null&&str.length()>=6){
			String yy = str.substring(0,4)+"年";
			String mm = Integer.parseInt(str.substring(4,6))+"月";
			str_return = yy+mm;
		}
		return str_return;
	}
	
	public static String getDatemm1(String str){
		String str_return = "";
		if(str!=null&&str.length()>=6){
			String mm = Integer.parseInt(str.substring(4,6))+"月";
			str_return = mm;
		}
		return str_return;
	}
	
	public static String getDateyy(String str){
		String str_return = "";
		if(str!=null&&str.length()==4){
			String yy = str.substring(0,4)+"年";
			str_return = yy;
		}
		return str_return;
	}
	
	public static String getDate1(String str){
		String str_return = "";
		if(str!=null&&str.length()==8){
			String yy = str.substring(0,4)+"-";
			String mm = str.substring(4,6)+"-";
			String dd = str.substring(6);
			str_return = yy+mm+dd;
		}
		return str_return;
	}
	

	public static String getTime3(String str){
		String str_return = "";
		if(str!=null && !str.equals("00:00")){
			str_return=str;
		}
		
		
		/*if(str!=null&&str.length()==8){
			String yy = str.substring(0,4)+"-";
			String mm = str.substring(4,6)+"-";
			String dd = str.substring(6);
			str_return = yy+mm+dd;
		}*/
		return str_return;
	}
	public static String getTime2(String str){
		String result = "";
		if(str!=null&&str.length()==6){
			result = str.substring(0, 2) + ":";
			result += str.substring(2, 4) + ":";
			result += str.substring(4) ;
		}
		return result;
	}
	public static String getTime1(String str){
		String str_return = "";
		if(str!=null&&str.length()==4){
			String hh = str.substring(0,2)+"-";
			String mm = str.substring(2);
			str_return = hh+mm;
		}
		else
			str_return = str;
		return str_return;
	}

	public static String getTime4(String str){
		String str_return = "";
		if(str!=null&&str.length()>=4){
			String hh = str.substring(0,2)+":";
			String mm = str.substring(2,4);
			str_return = hh+mm;
		}
		else
			str_return = str;
		return str_return;
	}
	
	public static String getTime5(String str){
		String str_return = "";
		if(str!=null&&str.length()>=4){
			String hh = str.substring(0,2)+"点";
			String mm = str.substring(2,4)+"分";
			if(mm.equals("00")){
				str_return = hh;
			}else{
				str_return = hh+mm;
			}
		}
		else
			str_return = str;
		return str_return;
	}
	
	public static boolean isEmpty(final String str) {
		if (str == null) {
			return true;
		}
		if (str.trim().length() == 0) {
			return true;
		}
		return false;
	}
	
	public static String getToday(){
		Date today1=new Date();
		DateFormat fmt=new SimpleDateFormat("yyyyMMdd");
		String mydate=fmt.format(today1);
		return mydate;
	}
	

	public static String getMyBrandno(String brandno){
		 char[] aa = brandno.toCharArray();
         StringBuffer sb = new StringBuffer("");
         for(char c : aa ) {
             if(c>='0' && c<='9' || c>='A'&&c<='Z' 
            		 || c>='a' && c<='z' || c=='/' || c=='+' || c=='(' || c==')') {
                 sb.append(c);
             }
         }
		return sb.toString();
	}
	
	public static String getHourMinute(){
		Date today1=new Date();
		DateFormat fmt=new SimpleDateFormat("HHmm");
		String mydate=fmt.format(today1);
		return mydate;
	}
	public static String getDateDB(String strdate){
		DateFormat fmt=new SimpleDateFormat("yyyyMMdd");
		DateFormat fmt2=new SimpleDateFormat("yyyy-MM-dd");
		String mydate = "";
		if(strdate!=null&&!strdate.equals("")&&strdate.indexOf("-")>=0){
			try {
				mydate = fmt.format(fmt2.parse((strdate)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return mydate;
	}

	public static String getTimeOfString(String time){
		String mytime = "";
		if(time!=null){
			mytime = time.replaceAll(":", "");
			if(mytime.length()==4)
				mytime += "00";
		}
		return mytime;
	}
	public static Integer getAge(String birthday){
		Integer age = 0;
		if(birthday!=null&&birthday.length()==8){
			Date today=new Date();
			Date birth = Util.toDateFromYYYYMMDD2(birthday);
			if(birth == null){
				return 0;
			}
			long day = (today.getTime()-birth.getTime())/(24*60*60*1000)+1;
			age = (int)(day/365f);
		}
		return age;
	}
	
	
	public static int getRemindMiles(String remark){
		int remindmiles=0;
		remark=remark.trim();  
		String str2="";  
		if(remark != null && !"".equals(remark)){  
			for(int i=0;i<remark.length();i++){  
				if(remark.charAt(i)>=48 && remark.charAt(i)<=57){  
					str2+=remark.charAt(i);
				}
			}
		}  
		remindmiles = parseInt(str2);
		return remindmiles;
	}
	
	//---------根据  HcPurchaseItem 状态 返回 字符串---------
	public static String getHcPurchaseItemState(Integer flag){
		String strState = "";
		if(flag!=null){
			if(flag==0)
				strState = "<font color='#FF0000'>未入库</font>";
			else if(flag==1)
				strState = "<font color='#00FF00'>已入库</font>";
		}
		
		return strState;
	}
	


	public static String getPercent(double data) {
		NumberFormat nf = NumberFormat.getPercentInstance();
		return nf.format(data);
	}

	public static Date getDateByMonths(int months, Date baseDate) {
		Calendar l_cal = Calendar.getInstance();
		l_cal.setTime(new Date());
		l_cal.add(Calendar.MONTH, months);
		Date l_date = l_cal.getTime();
		return l_date;
	}

	public static String toStrDateYYYYMMDD(final Date date) {
		SimpleDateFormat l_format = (SimpleDateFormat) DateFormat.getInstance();
		l_format.applyPattern("yyyyMMdd");
		return toStrDate(date, l_format);
	}

	public static String toStrDateYYYYMMDD2(final Date date) {
		SimpleDateFormat l_format = (SimpleDateFormat) DateFormat.getInstance();
		l_format.applyPattern("yyyy-MM-dd");
		return toStrDate(date, l_format);
	}
	
	public static String toStrDateYYYYMMDD3(final Date date) {
		SimpleDateFormat l_format = (SimpleDateFormat) DateFormat.getInstance();
		l_format.applyPattern("MM-dd");
		return toStrDate(date, l_format);
	}

	public static String toStrDateYYYYMM(final Date date) {
		SimpleDateFormat l_format = (SimpleDateFormat) DateFormat.getInstance();
		l_format.applyPattern("yyyyMM");
		return toStrDate(date, l_format);
	}
	public static String toStrDateYYYYMM1(final Date date) {
		SimpleDateFormat l_format = (SimpleDateFormat) DateFormat.getInstance();
		l_format.applyPattern("yyyy年MM月");
		return toStrDate(date, l_format);
	}
	
	public static String toStrDate(final Date date,
			final SimpleDateFormat format) {
		if (date == null) {
			return null;
		}
		return format.format(date);
	}

	public static Date getCurDate() {
		Date l_date = new Date();
		return l_date;
	}
	 public static int parseInt(String intStr){
	        int result=0;
	       try {
	             result = Integer.parseInt(intStr);
	       } catch (Exception e){
	             return result;
	       }
	        return result;
	    }
	public static int getTodayMiles(String recordtm,int recordmiles,int miles1,int miles2){

	       int result=0;
	       
	      /* if(recordtm.equals(getToday())){
	            return recordmiles;
	        }
	        if(eoFactoryDt!=null && eoFactoryDt.equals(recordtm))
	            return recordmiles;*/

	       
	        long day =(getOffsetDay2(recordtm,getToday()));
	        int day1 = (int) day;
	        if(miles2>0){


	           result = recordmiles+day1*miles2/30;

	        }else if(miles1>0){
	           result = recordmiles+day1*miles1/30;


	        }
	        System.out.println("result==============>"+result+"day=======>"+day1);

			return result;
		}

	public static String toDateDb(final String dateInput, final String type) {
		String l_dateDb = "";
		if (!isEmpty(dateInput)) {
			String[] l_date = dateInput.split(type);
			l_dateDb = l_date[0];
			if (!isEmpty(l_date[1]) && l_date[1].length() == 1) {
				l_dateDb += "0";
			}
			l_dateDb += l_date[1];
			if (!isEmpty(l_date[2]) && l_date[2].length() == 1) {
				l_dateDb += "0";
			}
			l_dateDb += l_date[2];
		}
		return l_dateDb;
	}

    public static String toDateDb2(final String dateInput, final String type) {
        String l_dateDb = "";
        if (!isEmpty(dateInput)) {
            String[] l_date = dateInput.split(type);
            l_dateDb = l_date[2];
            if (!isEmpty(l_date[1]) && l_date[1].length() == 1) {
                l_dateDb += "0";
            }
            l_dateDb += l_date[1];
            if (!isEmpty(l_date[0]) && l_date[0].length() == 1) {
                l_dateDb += "0";
            }
            l_dateDb += l_date[2];
        }
        return l_dateDb;
    }

	public static String toStrDateYYYYMMDD3(final String dateInput, final String type) {
		String l_date = "";
		if (!isEmpty(dateInput) && dateInput.length()==8) {
			l_date = dateInput.substring(0,4) + type + dateInput.substring(4,6) + type + dateInput.substring(6);
		}
		return l_date;
	}

    public static String toStrDateYYYYMMDD4(final String dateInput, final String type) {
        String l_date = "";
        if (!isEmpty(dateInput) && dateInput.length()==8) {
            l_date = dateInput.substring(6) + type + dateInput.substring(4,6) + type + dateInput.substring(0,4);
        }
        return l_date;
    }

	public static String toStrDateHHMMSS(final String dateInput, final String type) {
		String l_date = "";
		if (!isEmpty(dateInput) && dateInput.length()==6) {
			l_date = dateInput.substring(0,2) + type + dateInput.substring(2,4) + type + dateInput.substring(4);
		}
		return l_date;
	}

    public static String toStrDateHHMM(final String dateInput, final String type) {
        String l_date = "";
        if (!isEmpty(dateInput) && dateInput.length()==4) {
            l_date = dateInput.substring(0,2) + type + dateInput.substring(2,4);
        }
        return l_date;
    }

    public static String toStrDateHHMMNew(final String dateInput, final String type) {
        String l_date = "";
        if (!isEmpty(dateInput) && dateInput.length()==6) {
            l_date = dateInput.substring(0,2) + type + dateInput.substring(2,4);
        }
        return l_date;
    }

	public static long getDays(final String dateFrom, final String dateTo) {
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			return new Long((format.parse(dateFrom).getTime() - format.parse(
					dateTo).getTime())
					/ (1000 * 60 * 60 * 24) + 1).intValue();
		} catch (ParseException e) {
			return 0;
		}
	}

	public static String toStrDateHHMMSS(final Date date) {
		SimpleDateFormat l_format = (SimpleDateFormat) DateFormat.getInstance();
		l_format.applyPattern("HHmmss");
		return toStrDate(date, l_format);
	}

	public static String toStrDateHHMMSS2(final Date date) {
		SimpleDateFormat l_format = (SimpleDateFormat) DateFormat.getInstance();
		l_format.applyPattern("HH:mm:ss");
		return toStrDate(date, l_format);
	}

    public static Date toDateFromYYYYMMDD(String strDate) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = format.parse(strDate); // Thu Jan 18 00:00:00 CST 2007
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date toDateFromYYYYMMDD2(String strDate) {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        try {
            date = format.parse(strDate); // Thu Jan 18 00:00:00 CST 2007
        } catch (ParseException e) {
        
//            e.printStackTrace();
            return null;
        }
        return date;
    }
    public static String getNowYYYYMMDDHHMMSS() {
    	String result = "";
    	SimpleDateFormat l_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    	result = l_sdf.format(new Date());
    	return result;
    }

	public static String getNowYYYYMMDDHHMM() {
		String result = "";
		SimpleDateFormat l_sdf = new SimpleDateFormat("yyyyMMddHHmm");
		result = l_sdf.format(new Date());
		return result;
	}
    
    public static String getNowYYYYMMDD() {
    	String result = "";
    	SimpleDateFormat l_sdf = new SimpleDateFormat("yyyyMMdd");
    	result = l_sdf.format(new Date());
    	return result;
    }
    
	public static String getNowTm() {
		SimpleDateFormat l_sdf = new SimpleDateFormat("HHmmss");
		return l_sdf.format(new Date());
	}

    public static String getStrDateForYYYYMMDDHHMMSS(Date date) {
    	String result = "";
    	if(date == null){
    		return result;
    	}
    	SimpleDateFormat l_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    	result = l_sdf.format(date);
    	return result;
    }
    
    public static String getStrDateForYYYYMMDDHHMMSSSSS(Date date) {
    	String result = "";
    	if(date == null){
    		return result;
    	}
    	SimpleDateFormat l_sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    	result = l_sdf.format(date);
    	return result;
    }
    
    
    public static String getStartTimeByMonth(Date date){
    	Calendar calender = Calendar.getInstance();
    	calender.setTime(date);
    	calender.set(Calendar.DAY_OF_MONTH, 1);
    	calender.set(Calendar.HOUR_OF_DAY, 0);
    	calender.set(Calendar.MINUTE, 0);
    	calender.set(Calendar.SECOND, 0);
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    	return format.format(calender.getTime());
    }
    
    public static String getEndTimeByMonth(Date date){
    	Calendar calender = Calendar.getInstance();
    	calender.setTime(date);
    	calender.add(Calendar.MONTH, 1);
    	calender.set(Calendar.DAY_OF_MONTH, 1);
    	calender.add(Calendar.DAY_OF_MONTH, -1);
    	calender.set(Calendar.HOUR_OF_DAY, 23);
    	calender.set(Calendar.MINUTE, 59);
    	calender.set(Calendar.SECOND, 59);
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    	return format.format(calender.getTime());
    }
    
    public static String getStartDateByMonth(Date date){
    	Calendar calender = Calendar.getInstance();
    	calender.setTime(date);
    	calender.set(Calendar.DAY_OF_MONTH, 1);
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	return format.format(calender.getTime());
    }
    
    public static String getEndDateByMonth(Date date){
    	Calendar calender = Calendar.getInstance();
    	calender.setTime(date);
    	calender.add(Calendar.MONTH, 1);
    	calender.set(Calendar.DAY_OF_MONTH, 1);
    	calender.add(Calendar.DAY_OF_MONTH, -1);
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	return format.format(calender.getTime());
    }
    
    public static String getStartDateByMonth1(Date date){
    	Calendar calender = Calendar.getInstance();
    	calender.setTime(date);
    	calender.set(Calendar.DAY_OF_MONTH, 1);
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    	return format.format(calender.getTime());
    }
    
    public static String getEndDateByMonth1(Date date){
    	Calendar calender = Calendar.getInstance();
    	calender.setTime(date);
    	calender.add(Calendar.MONTH, 1);
    	calender.set(Calendar.DAY_OF_MONTH, 1);
    	calender.add(Calendar.DAY_OF_MONTH, -1);
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    	return format.format(calender.getTime());
    }
    
    public static String getEndDateByYear(Date date){
    	Calendar calender = Calendar.getInstance();
    	calender.setTime(date);
    	calender.set(Calendar.MONTH, 11);
    	calender.set(Calendar.DAY_OF_MONTH, 31);
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    	return format.format(calender.getTime());
    }
    
	public static String appendZeroLeft(String strObj, int len){
		if(strObj == null){
			return "";
		}
		if(strObj.length() >= len){
			return strObj;
		}
		return (StringUtils.repeat("0", len - strObj.length()) + strObj);
	}
	
	public static Integer convertToInt(String str){
		try{
			return Integer.parseInt(str);
		}catch(Exception e){
			return 0;
		}
	}
	
	public static Float convertToPercentFloat(String str){
		try{
			float coefficient=1f;
			coefficient = Float.parseFloat(str);
			if(coefficient==0){
				coefficient = 100f;
			}
			return coefficient/100;
		}catch(Exception e){
			return 1f;
		}
	}
	
	public static Float convertToFloat(String str){
		try{
			return Float.parseFloat(str);
		}catch(Exception e){
			return 0f;
		}
	}
	
	public static Float convertToFloat(BigDecimal str){
		try{
			return str.floatValue();
		}catch(Exception e){
			return 0f;
		}
	}
	
	
	public static Float convertToFloat(Double str){
		try{
			return str.floatValue();
		}catch(Exception e){
			return 0f;
		}
	}
	
	public static Double convertToDouble(String str){
		try{
			return Double.parseDouble(str);
		}catch(Exception e){
			return 0d;
		}
	}
	
	public static Long getOffsetDay(String startDate, String endDate){
		Date start = toDateFromYYYYMMDD(startDate);
		Date end = toDateFromYYYYMMDD(endDate);
		long offset = end.getTime() - start.getTime();
		return (offset/(24*60*60*1000));
	}
	
	public static Long getOffsetDay2(String startDate, String endDate){
		Date start = toDateFromYYYYMMDD2(startDate);
		Date end = toDateFromYYYYMMDD2(endDate);
		long offset = end.getTime() - start.getTime();
		return (offset/(24*60*60*1000));
	}

    //以秒返回
	public static Long getOffsetSeconds(String startDate, String endDate){
		Date start = toDateFromYYYYMMDDHHMMSS(startDate);
		Date end = toDateFromYYYYMMDDHHMMSS(endDate);
		long offset = end.getTime() - start.getTime();
		return offset/1000;
	}

	public static String toStrDateYYYYMMDD5(final String date, String format) {
		if(Util.isEmpty(date)){
			return "";
		}
		SimpleDateFormat l_format = new SimpleDateFormat(format);
        DateFormat l_dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
			return l_dateFormat.format(l_format.parse(date));
		} catch (ParseException e) {
			return "";
		}
	}

	public static String toStrDateYYYYMMDD8(final String date, String format) {
		if(Util.isEmpty(date)){
			return "";
		}
		SimpleDateFormat l_format = new SimpleDateFormat(format);
		DateFormat l_dateFormat = new SimpleDateFormat("HH:mm");
		try {
			return l_dateFormat.format(l_format.parse(date));
		} catch (ParseException e) {
			return "";
		}
	}
	
    public static Date toDateFromYYYYMMDDHHMMSS(String strDate) {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        try {
            date = format.parse(strDate); // Thu Jan 18 00:00:00 CST 2007
        } catch (ParseException e) {
        	date = null;
        }
        return date;
    }
    public static Date toDateFromYYYYMMDD1(String strDate) {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        try {
            date = format.parse(strDate); // Thu Jan 18 00:00:00 CST 2007
        } catch (ParseException e) {
        	date = null;
        }
        return date;
    }
    public static Long offsetMinite(String strDate){
    	Date objDate = toDateFromYYYYMMDDHHMMSS(strDate);
    	if(objDate == null){
    		return 0L;
    	} else {
    		Date cruDate = new Date();
    		Long offset = (cruDate.getTime() - objDate.getTime())/(60*1000);
    		return offset;
    	}
    }
	public static String getDateDisp(String strdate){
		DateFormat fmt=new SimpleDateFormat("yyyyMMdd");
		DateFormat fmt2=new SimpleDateFormat("yyyy-MM-dd");
		String mydate = "";
		if(strdate!=null&&!strdate.equals("")){
			try {
				mydate = fmt2.format(fmt.parse((strdate)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return mydate;
	}

	public static String dateFormatChange(String strdate){
		DateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
		DateFormat fmt2=new SimpleDateFormat("yyyyMMdd");
		String mydate = "";
		if(strdate!=null&&!strdate.equals("")){
			try {
				mydate = fmt2.format(fmt.parse((strdate)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return mydate;
	}


	public static String getDateYYYYMMDDHHMMSS(String strdate){
		DateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		DateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mydate = "";
		if(strdate!=null&&!strdate.equals("")&&!"null".equals(strdate)){
			try {
				mydate = fmt2.format(fmt.parse((strdate)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return mydate;
	}
	
	public static String getDateYYYYMMDDHHMMSS2(String strdate){
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat fmt2 = new SimpleDateFormat("yyyyMMddHHmmss");
		String mydate = "";
		if(strdate!=null&&!strdate.equals("")){
			try {
				mydate = fmt2.format(fmt.parse((strdate)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return mydate;
	}
	public static String getDateYYYYMMDDHHMM(String strdate){
		DateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		DateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String mydate = "";
		if(strdate!=null&&!strdate.equals("")){
			try {
				mydate = fmt2.format(fmt.parse((strdate)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return mydate;
	}

	public static String getDateYYYYMMDDHHMM2(String strdate){
		DateFormat fmt = new SimpleDateFormat("yyyyMMddHHmm");
		DateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String mydate = "";
		if(strdate!=null&&!strdate.equals("")){
			try {
				mydate = fmt2.format(fmt.parse((strdate)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return mydate;
	}

	// 到期日期
	public static String getPaymentDate(Integer paymentDays){
		if(paymentDays == null){
			return "";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, paymentDays);
		return toStrDateYYYYMMDD2(calendar.getTime());
	}
	
	//---------返回当月首日-----------
	public static String getFirstDayOfMonth(){
		Calendar calendar  =   new  GregorianCalendar();
		calendar.set( Calendar.DATE,  1 );
		DateFormat simpleFormate  =   new  SimpleDateFormat( "yyyyMMdd" );
		String sd = simpleFormate.format(calendar.getTime());
		return sd;
	}
	//---------返回当月尾日-----------
	public static String getLastDayOfMonth(){
		Calendar calendar  =   new  GregorianCalendar();
		DateFormat simpleFormate  =   new  SimpleDateFormat( "yyyyMMdd" );
		calendar.set( Calendar.DATE,  1 );
		calendar.roll(Calendar.DATE, - 1 );
		String ed = simpleFormate.format(calendar.getTime());
		return ed;
	}
	
	//---------返回当月首日-----------
	public static String getFirstDayOfMonth(int year, int month){
		Calendar calendar = new  GregorianCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set( Calendar.DATE,  1 );
		DateFormat simpleFormate  =   new  SimpleDateFormat( "yyyyMMdd" );
		String sd = simpleFormate.format(calendar.getTime());
		return sd;
	}
	//---------返回当月尾日-----------
	public static String getLastDayOfMonth(int year, int month){
		Calendar calendar = new  GregorianCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set( Calendar.DATE,  1 );
		calendar.roll(Calendar.DATE, - 1 );
		DateFormat simpleFormate  =   new  SimpleDateFormat( "yyyyMMdd" );
		String ed = simpleFormate.format(calendar.getTime());
		return ed;
	}
	
	/**
	 * ----获取当前  年月日等
	 * @return
	 */
	public static int getNowYear(){
		Calendar cal=Calendar.getInstance(); 
	    cal.setTime(new Date());
	    int   year=cal.get(Calendar.YEAR); 
	    int   month=cal.get(Calendar.MONTH)+1; 
	    int   date=cal.get(Calendar.DATE); 
	    int   hour=cal.get(Calendar.HOUR); 
	    int   minute=cal.get(Calendar.MINUTE); 
	    int   second=cal.get(Calendar.SECOND); 
	    int   milliSecond=cal.get(Calendar.MILLISECOND);

		return year;
	}
	public static int getNowMonth(){
		Calendar cal=Calendar.getInstance(); 
	    cal.setTime(new Date());
	    int   year=cal.get(Calendar.YEAR); 
	    int   month=cal.get(Calendar.MONTH)+1; 
	    int   date=cal.get(Calendar.DATE); 
	    int   hour=cal.get(Calendar.HOUR); 
	    int   minute=cal.get(Calendar.MINUTE); 
	    int   second=cal.get(Calendar.SECOND); 
	    int   milliSecond=cal.get(Calendar.MILLISECOND);

		return month;
	}
	
	public static int getMonth(Calendar cal){
	    int   month=cal.get(Calendar.MONTH)+1; 
		return month;
	}
	
	public static int getyear1(Calendar cal){
		return cal.get(Calendar.YEAR);
	}
	
	public static String getyear(Calendar cal){
	    String  year=String.valueOf(cal.get(Calendar.YEAR)); 
	    return year.substring(2,4)+ "年";
	}
	
	public static String getStandardFloat(Float f){
		if(f == null ) {
			return "0.00";
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(f);
	}
	public static int maxMonthDays(){
		Calendar cal=Calendar.getInstance(); 
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	public static int maxMonthDays(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	public static String getnextPaymentdate(Date prePaymentdate){
		
		Calendar cal=Calendar.getInstance(); 
		cal.setTime(prePaymentdate);
		
		if(cal.get(Calendar.DAY_OF_MONTH) > maxMonthDays()){
			return getLastDayOfMonth();
		} else {
			cal.add(Calendar.MONTH, 1);
			return toStrDateYYYYMMDD(cal.getTime());
		}
	}
	public static Integer validateInt(String str){
		try{
			return Integer.parseInt(str);
		}catch(Exception e){
			return null;
		}
	}
	
	public static String numFormart(Integer num){
		if(num == null){
			return "0";
		}
		DecimalFormat df1 = new DecimalFormat("##,##0");
		return(df1.format(num));
	}
	public static String numFormart3(Number dddd){
		if(dddd == null){
			return "0";
		}
		String priceStr = dddd.toString();
		BigDecimal decimalPrice = new BigDecimal(priceStr);
		DecimalFormat df1 = new DecimalFormat("#0");
		return(df1.format(decimalPrice));
	}
	public static String numFormart1(Number dddd){
		if(dddd == null){
			return "0";
		}
		String priceStr = dddd.toString();
		BigDecimal decimalPrice = new BigDecimal(priceStr);
		DecimalFormat df1 = new DecimalFormat("#0.##");
		return(df1.format(decimalPrice));
	}
	public static String numFormart2(Number dddd){
		if(dddd == null){
			return "0";
		}
		String priceStr = dddd.toString();
		BigDecimal decimalPrice = new BigDecimal(priceStr);
		DecimalFormat df1 = new DecimalFormat("##,##0.##");
		return(df1.format(decimalPrice));
	}
	public static String priceFormart(Float price){
		if(price == null){
			return "0.00";
		}
		String priceStr = price.toString();
		BigDecimal decimalPrice = new BigDecimal(priceStr);
		DecimalFormat df1 = new DecimalFormat("##,##0.00");
		return(df1.format(decimalPrice));
	}
	
	public static Float priceFormart2(Float price){
		if(price == null){
			return 0f;
		}
		BigDecimal decimalPrice = new BigDecimal(price);
//		decimalPrice.setScale(1, BigDecimal.ROUND_DOWN);
		
		System.out.println(priceFormart4(decimalPrice.floatValue()));
		return Util.convertToFloat(priceFormart4(decimalPrice.floatValue()));
	}
	
	public static String priceFormart(BigDecimal price){
		if(price == null){
			return "0.00";
		}
		DecimalFormat df1 = new DecimalFormat("##,##0.00");
		return(df1.format(price));
	}
	public static String priceFormart1(Float price){
		if(price == null){
			return "0.00";
		}
		String priceStr = price.toString();
		BigDecimal decimalPrice = new BigDecimal(price);
		DecimalFormat df1 = new DecimalFormat("##0.00");
		
		return(df1.format(decimalPrice));
	}
	public static String priceFormart3(Float price){
		if(price == null){
			return "0.00";
		}
		String priceStr = price.toString();
		BigDecimal decimalPrice = new BigDecimal(price);
		DecimalFormat df1 = new DecimalFormat("##0.00");
		df1.setRoundingMode(RoundingMode.DOWN);
		return(df1.format(decimalPrice));
	}
	public static String priceFormart4(Float price){
		if(price == null){
			return "0.00";
		}
		String priceStr = price.toString();
		BigDecimal decimalPrice = new BigDecimal(price);
		DecimalFormat df1 = new DecimalFormat("##0.0");
		df1.setRoundingMode(RoundingMode.DOWN);
		return(df1.format(decimalPrice));
	}
	public static BigDecimal convertdecimal(String price){
		if(Util.isEmpty(price)){
			return BigDecimal.ZERO;
		}
		BigDecimal decimalPrice = new BigDecimal(price);
		decimalPrice = decimalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
		return decimalPrice;
	}


	public static BigDecimal convertdecimalMult100(String price){
		if(Util.isEmpty(price)){
			return BigDecimal.ZERO;
		}
		BigDecimal decimalPrice = new BigDecimal(price);
		decimalPrice = decimalPrice.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
		return decimalPrice;
	}











	public static String getDateDisp1(String strdate){
		DateFormat fmt=new SimpleDateFormat("yyyyMMdd");
		DateFormat fmt2=new SimpleDateFormat("MM-dd");
		String mydate = "";
		if(strdate!=null&&!strdate.equals("")){
			try {
				mydate = fmt2.format(fmt.parse((strdate)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return mydate;
	}
	public static String getWeekDisp(Calendar cal){
		int week = cal.get(Calendar.DAY_OF_WEEK);
		String weekDisp = "";
		switch(week){
			case 1:
				weekDisp = "(周日)";
				break;
			case 2 :
				weekDisp = "(周一)";
				break;
			case 3 :
				weekDisp = "(周二)";
				break;
			case 4 :
				weekDisp = "(周三)";
				break;
			case 5 :
				weekDisp = "(周四)";
				break;
			case 6 :
				weekDisp = "(周五)";
				break;
			case 7 :
				weekDisp = "(周六)";
				break;
			default:
				break;
		}
		return weekDisp;
	}
	public static String getWeekDisp1(Calendar cal){
		int week = cal.get(Calendar.DAY_OF_WEEK);
		String weekDisp = "";
		switch(week){
			case 1:
				weekDisp = "周日";
				break;
			case 2 :
				weekDisp = "周一";
				break;
			case 3 :
				weekDisp = "周二";
				break;
			case 4 :
				weekDisp = "周三";
				break;
			case 5 :
				weekDisp = "周四";
				break;
			case 6 :
				weekDisp = "周五";
				break;
			case 7 :
				weekDisp = "周六";
				break;
			default:
				break;
		}
		return weekDisp;
	}
	public static String getWeekDisp2(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		String weekDisp = "";
		switch(week){
			case 1:
				weekDisp = "周日";
				break;
			case 2 :
				weekDisp = "周一";
				break;
			case 3 :
				weekDisp = "周二";
				break;
			case 4 :
				weekDisp = "周三";
				break;
			case 5 :
				weekDisp = "周四";
				break;
			case 6 :
				weekDisp = "周五";
				break;
			case 7 :
				weekDisp = "周六";
				break;
			default:
				break;
		}
		return weekDisp;
	}
	public static BigDecimal getRate(double d1, double d2){
		   if(d1 == 0 || d2 == 0){
			   return BigDecimal.ZERO;
		   }
	       BigDecimal decimal1 = new BigDecimal(d1);
	       BigDecimal decimal2 = new BigDecimal(d2);
	       BigDecimal rate = decimal1.divide(
	    		   decimal2,2,BigDecimal.ROUND_HALF_UP);
	       return rate;
	}
	public static BigDecimal getRate(int d1, int d2){
		   if(d1 == 0 || d2 == 0){
			   return BigDecimal.ZERO;
		   }
	       BigDecimal decimal1 = new BigDecimal(d1);
	       BigDecimal decimal2 = new BigDecimal(d2);
	       BigDecimal rate = decimal1.divide(
	    		   decimal2,2,BigDecimal.ROUND_HALF_UP);
	       return rate;
	}
	public static Float getRate1(double d1, double d2){
		   if(d1 == 0 || d2 == 0){
			   return 0f;
		   }
	       BigDecimal decimal1 = new BigDecimal(d1);
	       BigDecimal decimal2 = new BigDecimal(d2);
	       BigDecimal rate = decimal1.divide(
	    		   decimal2,2,BigDecimal.ROUND_HALF_UP);
	       rate = rate.multiply(BigDecimal.TEN.multiply(BigDecimal.TEN));
	       return rate.floatValue();
	}
	public static Float getRatebyfloat(float d1, float d2){
		   if(d2 == 0){
			   return Util.convertToFloat("100");
		   }
	       BigDecimal decimal1 = new BigDecimal(d1);
	       BigDecimal decimal2 = new BigDecimal(d2);
	       BigDecimal rate = decimal1.divide(
	    		   decimal2,4,BigDecimal.ROUND_DOWN);
	       rate = rate.multiply(BigDecimal.TEN.multiply(BigDecimal.TEN));
	       
	       return Util.convertToFloat(rate);
	}
	public static Float getRatebyfloat1(float d1, float d2){
		   if(d2 == 0){
			   return Util.convertToFloat("0");
		   }
	       BigDecimal decimal1 = new BigDecimal(d1);
	       BigDecimal decimal2 = new BigDecimal(d2);
	       BigDecimal rate = decimal1.divide(
	    		   decimal2,4,BigDecimal.ROUND_DOWN);
	       rate = rate.multiply(BigDecimal.TEN.multiply(BigDecimal.TEN));
	       
	       return Util.convertToFloat(rate);
	}
	public static String toPercentage(BigDecimal rate){
		if(rate == null){
			return "0%";
		}
		BigDecimal percentage = rate.multiply(BigDecimal.TEN).multiply(BigDecimal.TEN);
		percentage = percentage.setScale(0);
       return (percentage.toPlainString()) + "%";
	}
	
	public static Float getPriceforrate(Float price,Float rate){
		if(rate == null){
			return price;
		}
		if(price == null){
			return 0f;
		}
		BigDecimal bigdecimalprice = new BigDecimal(price);
		BigDecimal bigdecimalrate = new BigDecimal(rate);
		bigdecimalprice = bigdecimalprice.multiply(bigdecimalrate);
		bigdecimalprice = bigdecimalprice.setScale(2,BigDecimal.ROUND_HALF_UP);
       return bigdecimalprice.floatValue();
	}
	
	public static String getDateYYYYMMDDHHMMSS1(String strdate){
		DateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		DateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String mydate = "";
		if(strdate!=null&&!strdate.equals("")){
			try {
				mydate = fmt2.format(fmt.parse((strdate)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return mydate;
	}
	
	public static String delNull(String str){
		if(str==null||str.equals("null"))
			str = "";
		return str;
	}

	public static String getQuarterStart(){
		Calendar cal=Calendar.getInstance(); 
	    int month=cal.get(Calendar.MONTH)+1;
	    int   year=cal.get(Calendar.YEAR); 
	    String startdate = "";
	    if(1 <= month && month <= 3){
	    	startdate = String.valueOf(year) + "0101";
	    }
	    if(4 <= month && month <= 6){
	    	startdate = String.valueOf(year) + "0401";
	    }
	    if(7 <= month && month <= 9){
	    	startdate = String.valueOf(year) + "0701";
	    }
	    if(10 <= month && month <= 12){
	    	startdate = String.valueOf(year) + "1001";
	    }
		return startdate;
	}
	
	public static String getHalfYearStart(){
		Calendar cal=Calendar.getInstance(); 
	    int month=cal.get(Calendar.MONTH)+1;
	    int   year=cal.get(Calendar.YEAR); 
	    String startdate = "";
	    if(1 <= month && month <= 6){
		    startdate = String.valueOf(year) + "0101";
	    } else {
	    	startdate = String.valueOf(year) + "0701";
	    }
		return startdate;
	}
	
	public static String getQuarterEnd(){
		Calendar cal=Calendar.getInstance(); 
	    int month=cal.get(Calendar.MONTH)+1;
	    int   year=cal.get(Calendar.YEAR); 
	    String startdate = "";
	    if(1 <= month && month <= 3){
	    	startdate = String.valueOf(year) + "0331";
	    }
	    if(4 <= month && month <= 6){
	    	startdate = String.valueOf(year) + "0630";
	    }
	    if(7 <= month && month <= 9){
	    	startdate = String.valueOf(year) + "0930";
	    }
	    if(10 <= month && month <= 12){
	    	startdate = String.valueOf(year) + "1231";
	    }
		return startdate;
	}
	
	public static String getHalfYearEnd(){
		Calendar cal=Calendar.getInstance(); 
	    int month=cal.get(Calendar.MONTH)+1;
	    int   year=cal.get(Calendar.YEAR); 
	    String startdate = "";
	    if(1 <= month && month <= 6){
	    	startdate = String.valueOf(year) + "0630";
	    }
	    if(7 <= month && month <= 12){
	    	startdate = String.valueOf(year) + "1231";
	    }
		return startdate;
	}
	
	public static String getYearStart(){
		Calendar cal=Calendar.getInstance();
	    int   year=cal.get(Calendar.YEAR); 
	    return (String.valueOf(year) + "0101");
	}
	public static String getYearEnd(){
		Calendar cal=Calendar.getInstance();
	    int   year=cal.get(Calendar.YEAR); 
	    return (String.valueOf(year) + "1231");
	}
	public static String getSmname(Integer smid){
		if(smid == null){
			return "";
		}
		if(smid.equals(0)){
			return "会员卡";
		}
		if(smid.equals(1)){
			return "现付";
		}
		if(smid.equals(2)){
			return "月结";
		}
		if(smid.equals(3)){
			return "银行卡";
		}
		if(smid.equals(7)){
			return "微信";
		}
		return "";
	}
	
	public static String toStrDateYYYYMMDD6(final String date, final String time) {
		if(Util.isEmpty(date)){
			return "";
		}
		if(Util.isEmpty(time)){
			SimpleDateFormat l_format = new SimpleDateFormat("yyyyMMdd");
	        DateFormat l_dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	        try {
				return l_dateFormat.format(l_format.parse(date));
			} catch (ParseException e) {
				return "";
			}
		
		} else {
			SimpleDateFormat l_format = new SimpleDateFormat("yyyyMMddHHmmss");
	        DateFormat l_dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	        try {
				return l_dateFormat.format(l_format.parse(date + time));
			} catch (ParseException e) {
				return "";
			}
		}

	}


	public static String toStrDateYYYYMMDDaddHHmm(final String date) {
		if(Util.isEmpty(date)){
			return "";
		}
		SimpleDateFormat l_format = new SimpleDateFormat("yyyyMMddHHmmss");
		DateFormat l_dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		try {
			return l_dateFormat.format(l_format.parse(date));
		} catch (ParseException e) {
			return "";
		}
	}

	public static String toStrDateYYYYMMDDHHmm(final Date date) {
		if(date==null){
			return "";
		}
		DateFormat l_dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		try {
			return l_dateFormat.format(date);
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String toStrDateYYYYMMDD7(final String date, final String time) {
		if(Util.isEmpty(date)){
			return "";
		}
		if(Util.isEmpty(time)){
			SimpleDateFormat l_format = new SimpleDateFormat("yyyyMMdd");
	        DateFormat l_dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	        try {
				return l_format.format(l_dateFormat.parse(date));
			} catch (ParseException e) {
				return "";
			}
		
		} else {
			SimpleDateFormat l_format = new SimpleDateFormat("yyyyMMddHHmmss");
	        DateFormat l_dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	        try {
				return l_format.format(l_dateFormat.parse(date + time));
			} catch (ParseException e) {
				return "";
			}
		}

	}
	
	
	public static String getStrItemStatus(int itemstatus){
		String str = "";
		switch(itemstatus){
			case 1:
				str = "待估价";
				break;
			case 2 :
				str = "已估价";
				break;
			case 3 :
				str = "已发布、未派工";
				break;
			case 4 :
				str = "待施工";
				break;
			case 5 :
				str = "施工中";
				break;
			case 6 :
				str = "待复核";
				break;
			case 7 :
				str = "复核中";
				break;
			case 8 :
				str = "不合格";
				break;
				
			case 9 :
				str = "完工";
				break;
		}
		
		return str;
	}
	
	public static String getDateforCn(String str){
		String str_return = "";
		if(str!=null && str.length()==14){
			String yy = str.substring(0,4)+"年";
			String mm = str.substring(4,6)+"月";
			String dd = str.substring(6,8)+"日";
			String hh = str.substring(8,10) + "时";
			String mi = str.substring(10,12) + "分";
			str_return = yy+mm+dd+hh+mi;
		}
		return str_return;
	}
	
	public static String getDateforCn1(String str){
		String str_return = "";
		if(str!=null && str.length()==14){
			String yy = str.substring(0,4)+"年";
			String mm = str.substring(4,6)+"月";
			String dd = str.substring(6,8)+"日";
			String hh = str.substring(8,10) + "时";
			String mi = str.substring(10,12) + "分";
			String mm1 = str.substring(12,14) + "秒";
			str_return = yy+mm+dd+hh+mi + mm1;
		}
		return str_return;
	}
	
	public static String getDateforalert(String str){
		if(Util.isEmpty(str)){
			return "";
		}
		
		String str_return = "";

		String yy = "";
		String mm = "";
		String dd = "";
		String hh = "";
		String mi = "";
		String ss = "";
		
		if(str.length() >= 4){
			yy = str.substring(0,4);
		} 
		if(str.length() > 4){
			yy += "/";
		}
		if(str.length() >= 6){
			mm = str.substring(4,6);
		} 
		if(str.length() > 6){
			mm += "/";
		}
		if(str.length() >= 8){
			dd = str.substring(6,8)+"";
		} 
		if(str.length() > 8){
			dd += " ";
		}
		if(str.length() >= 10){
			hh = str.substring(8,10);
		}
        if(str.length() > 10){
			hh += ":";
		}
		if(str.length() >= 12){
			mi = str.substring(10,12);
		} 
        if(str.length() > 12){
			mi += ":";
		}
		if(str.length() >= 14){
			ss = str.substring(12,14);
		}
		str_return = yy+mm+dd+hh+mi+ss;
		return str_return;
	}
	
	public static String getDateforalert1(String str){
		if(Util.isEmpty(str)){
			return "";
		}
		
		String str_return = "";

		String yy = "";
		String mm = "";
		String dd = "";
		
		if(str.length() >= 4){
			yy = str.substring(0,4);
		}
		if(str.length() >= 6){
			mm = str.substring(4,6);
		} 
		if(str.length() >= 8){
			dd = str.substring(6,8)+"";
		} 
		str_return = yy+mm+dd;
		return str_return;
	}
	
	public static String getDateForOffset(String date,int fieldtype , int offset){
		Date objdate = toDateFromYYYYMMDD1(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(objdate);
		cal.add(fieldtype, offset);
		String formarteddate = toStrDateYYYYMMDD(cal.getTime());
		return formarteddate;
	}
	
	public static String getDateForOffset(Date date,int fieldtype , int offset){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(fieldtype, offset);
		String formarteddate = toStrDateYYYYMMDD(cal.getTime());
		return formarteddate;
	}
	
	public static boolean isEndmonthdate(String date){
		Date objdate = toDateFromYYYYMMDD1(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(objdate);
		int orgmonth = cal.get(Calendar.MONTH);
		int orgyear = cal.get(Calendar.YEAR);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		if(orgmonth == month && orgyear == year){
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean isStartmonthdate(String date){
		Date objdate = toDateFromYYYYMMDD1(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(objdate);
		int orgday = cal.get(Calendar.DAY_OF_MONTH);
		if(orgday == 1){
			return true;
		} else {
			return false;
		}
	}
	
	public static String formartdispdate(String date){
		if(Util.isEmpty(date)){
			return "";
		} else {
			if(date.length() == 14){
				return Util.getDateYYYYMMDDHHMMSS(date);
			} else if(date.length() == 12){
				return Util.getDateYYYYMMDDHHMMSS(date+"00");
			} else if(date.length() == 8){
				return Util.getDateDisp(date);
			} else{
				return "";
			}
			
		}
	}
    
    public static String getAlertDate(String targetDate, int beforehandDays){
        Date objdate = toDateFromYYYYMMDD1(targetDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(objdate);
        cal.add(Calendar.DAY_OF_MONTH, beforehandDays);
        Date alertDate = cal.getTime();
        Date now = new Date();
        if (now.compareTo(alertDate) >= 0){
            return toStrDateYYYYMMDD(now);
        } else {
            return toStrDateYYYYMMDD(alertDate);
        }
    }
    
    
    public static String getDateAfterDays(String date,int days){
    	System.out.println(date+","+days);
    	Date d = toDateFromYYYYMMDDHHMMSS(date+"120000");
    	Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_MONTH, days);
        d = cal.getTime();
    	
        return toStrDateYYYYMMDD(d);
    }
    
    public static boolean isNumeric(String str){
	   for(int i=str.length();--i>=0;){
	      int chr=str.charAt(i);
	      if((chr>=48 && chr<=57)|| chr == 47)
	         return true;
	   }
	   return false;
    }
    
    public static boolean isallNumeric(String str){
    	if(Util.isEmpty(str)){
    		return false;
    	}
    	boolean isallnumberic = true;
    	char[] chrarray = str.toCharArray();
    	for(char chr : chrarray){
  	      if((chr>=48 && chr<=57)){
  	    	
  	      } else {
  	    	isallnumberic = false;
  	      }
    	}
    	return isallnumberic;
    }
    /**
     * 判断字符串是否全是中文
     *
     * @param str
     * @return
     */
    public static boolean isAllChinese(String str) {
    	if(Util.isEmpty(str)){
    		return false;
    	}
        for (int i = 0; i < str.length(); i++) {
            char ss = str.charAt(i);
            boolean s = String.valueOf(ss).matches("[\u4e00-\u9fa5]");
            if (!s) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 判断字符串是否包含一个中文
     *
     * @param str
     * @return
     */
    public static boolean isOneChinese(String str) {
        	if(str!=null){
        		 char ss = str.charAt(0);
                 boolean s = String.valueOf(ss).matches("[\u4e00-\u9fa5]");
                 if (s) {
                     return true;
                 }else{
                	 return false;
                 }
        	}else{
        		return false;
        	}
    }
    
	public static String getTodayYYYYMMDD(Calendar curdate){
		Date today = curdate.getTime();
		DateFormat fmt= new SimpleDateFormat("yyyyMMdd");
		String mydate=fmt.format(today);
		return mydate;
	}
	public static String getTodayHHmmss(Calendar curdate){
		Date today = curdate.getTime();
		DateFormat fmt = new SimpleDateFormat("HHmmss");
		String mytime=fmt.format(today);
		return mytime;
	}
	
    public static int getMaxdayofMonth(Date date){
    	Calendar calender = Calendar.getInstance();
    	calender.setTime(date);
    	calender.add(Calendar.MONTH, 1);
    	calender.set(Calendar.DAY_OF_MONTH, 1);
    	calender.add(Calendar.DAY_OF_MONTH, -1);
    	return calender.get(Calendar.DAY_OF_MONTH);
    }
    
    public static String getCurrentDate(Date objdate,Integer year){
    	Calendar ca = Calendar.getInstance();
    	ca.setTime(objdate);
//    	ca.add(Calendar.YEAR, 1);
    	do{
    		int objyear = ca.get(Calendar.YEAR);
    		if(year > objyear){
    			ca.add(Calendar.YEAR, 1);
    		} else {
    			break;
    		}
    	}while(true);
    	return Util.toStrDateYYYYMMDD(ca.getTime());
    }
    
	public static String getinspection_expiresdate(String membercarRegistrationDt,int day){
		int year = 0;
		int offset = 2;
		String today = Util.getToday();
		
		today = Util.getDateForOffset(today, Calendar.DAY_OF_MONTH, day);
		String membercarRegistrationDt1 = membercarRegistrationDt;
		System.out.println(membercarRegistrationDt1);
		Date membercarRegistrationDt2 = Util.toDateFromYYYYMMDD2(membercarRegistrationDt1);
//		System.out.println(membercarRegistrationDt2);
		if(membercarRegistrationDt2 == null){
			return null;
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(membercarRegistrationDt2);
		
		String aft6yeardate = Util.getDateForOffset(membercarRegistrationDt1, Calendar.YEAR, 6);
		
		if(aft6yeardate.compareTo(Util.getToday()) > 0){
			return aft6yeardate;
		}
		while(today.compareTo(membercarRegistrationDt1) >= 0){
			System.out.println(today + ">>>>>>>>>>>>>>>" + membercarRegistrationDt1);
			if(today.compareTo(membercarRegistrationDt1)>=0 & Util.getToday().compareTo(membercarRegistrationDt1)<=0){

				return membercarRegistrationDt1;
			}
//			if(year < 6){
//				offset = 2;
//			} else {
				offset = 1;
//			}
			ca.add(Calendar.YEAR, offset);
			year += offset;
			membercarRegistrationDt1 = Util.toStrDateYYYYMMDD(ca.getTime());
//			System.out.println(year);
//			System.out.println(membercarRegistrationDt1);

		}
/*		if(membercarRegistrationDt1.compareTo(today) <=0 && Util.getToday().compareTo(membercarRegistrationDt1) <= 0){
			return membercarRegistrationDt1;
		} else{
			return null;
		}*/
		return membercarRegistrationDt1;
	}

	/**
	 * @author zy
	 * @CreateTime 2014年4月22日 下午4:55:26   
	 * @remark 获取单号
	 * @param orderid 委托单号
	 * @param eoserialorderid	流水单号
	 * @return
	 */
	public static String getEoorderId(int orderid,String eoserialorderid){
		if(!Util.isEmpty(eoserialorderid)){
			return eoserialorderid;
		}else{
			return orderid+"";
		}
	}

	
	//  本周开始日
	public static String getStartweek(){

    	Calendar calender = Calendar.getInstance();
    	
    	int week = calender.get(Calendar.DAY_OF_WEEK);

    	int offset = 0;
    	if(week == 1){
    		offset = -6;
    	} else if(week == 2){
    		offset = 0;
    	} else {
    		offset = 2-week;
    	}
    	
    	calender.add(Calendar.DAY_OF_MONTH, offset);
    	
    	return Util.toStrDateYYYYMMDD(calender.getTime());
	}
	
	
	//本周终了日
	public static String getEndweek(){

    	Calendar calender = Calendar.getInstance();
    	
    	int week = calender.get(Calendar.DAY_OF_WEEK);

    	int offset = 0;
    	if(week == 1){
    		offset = 0;
    	} else if(week == 2){
    		offset = 6;
    	} else {
    		offset = 8-week;
    	}
    	
    	calender.add(Calendar.DAY_OF_MONTH, offset);
    	
    	return Util.toStrDateYYYYMMDD(calender.getTime());
	}
	
	public static List<Date> getweekdays(String date){

    	Date currentdate = Util.toDateFromYYYYMMDD1(date);
		//周初
    	Calendar calenderstart = Calendar.getInstance();
    	calenderstart.setTime(currentdate);
    	
    	int weekstart = calenderstart.get(Calendar.DAY_OF_WEEK);

    	int offsetstart = 0;
    	if(weekstart == 1){
    		offsetstart = -6;
    	} else if(weekstart == 2){
    		offsetstart = 0;
    	} else {
    		offsetstart = 2-weekstart;
    	}
    	
    	calenderstart.add(Calendar.DAY_OF_MONTH, offsetstart);
    	
		//周末
    	Calendar calenderend = Calendar.getInstance();
    	calenderend.setTime(currentdate);
    	int weekend = calenderend.get(Calendar.DAY_OF_WEEK);

    	int offsetend = 0;
    	if(weekend == 1){
    		offsetend = 0;
    	} else if(weekend == 2){
    		offsetend = 6;
    	} else {
    		offsetend = 8-weekend;
    	}
    	calenderend.add(Calendar.DAY_OF_MONTH, offsetend);
    	
    	Calendar calender = Calendar.getInstance();
    	calender.setTime(calenderstart.getTime());
    	List<Date> weekdatelist = new ArrayList<>();
    	do{
    		weekdatelist.add(calender.getTime());
    		calender.add(Calendar.DAY_OF_MONTH, 1);
    	}while(Util.toStrDateYYYYMMDD(calender.getTime()).compareTo(Util.toStrDateYYYYMMDD(calenderend.getTime()))<=0);
    	
    	return weekdatelist;
	}
	
	public static List<String> getQuarterlist(int year,int quarter){
		List<String> monthlist = new ArrayList<>();
	    if(1 <= quarter && quarter <= 3){
	    	System.out.println(quarter);
	    	monthlist.add(String.valueOf(year) + "01");
	    	monthlist.add(String.valueOf(year) + "02");
	    	monthlist.add(String.valueOf(year) + "03");
	    }
	    if(4 <= quarter && quarter <= 6){
	    	System.out.println(quarter);
	    	monthlist.add(String.valueOf(year) + "04");
	    	monthlist.add(String.valueOf(year) + "05");
	    	monthlist.add(String.valueOf(year) + "06");
	    }
	    if(7 <= quarter && quarter <= 9){
	    	System.out.println(quarter);
	    	monthlist.add(String.valueOf(year) + "07");
	    	monthlist.add(String.valueOf(year) + "08");
	    	monthlist.add(String.valueOf(year) + "09");
	    }
	    if(10 <= quarter && quarter <= 12){
	    	System.out.println(quarter);
	    	monthlist.add(String.valueOf(year) + "10");
	    	monthlist.add(String.valueOf(year) + "11");
	    	monthlist.add(String.valueOf(year) + "12");
	    }
		return monthlist;
	}
	
	public static List<String> getYearmonthlist(int year){
		List<String> monthlist = new ArrayList<>();
		monthlist.add(String.valueOf(year) + "01");
		monthlist.add(String.valueOf(year) + "02");
		monthlist.add(String.valueOf(year) + "03");
		monthlist.add(String.valueOf(year) + "04");
		monthlist.add(String.valueOf(year) + "05");
		monthlist.add(String.valueOf(year) + "06");
		monthlist.add(String.valueOf(year) + "07");
		monthlist.add(String.valueOf(year) + "08");
		monthlist.add(String.valueOf(year) + "09");
		monthlist.add(String.valueOf(year) + "10");
		monthlist.add(String.valueOf(year) + "11");
		monthlist.add(String.valueOf(year) + "12");
		return monthlist;
	}
	public static int getIntegerValue(Integer value){
		if(value == null){
			return 0;
		} else {
			return value.intValue();
		}
	}
	public static String getNullStringvalue(String value){
		if(value == null){
			return "";
		} else {
			return value;
		}
	}
	
	public static List<Date> getdatelist(String startdate,String enddate){
		List<Date> dates = new ArrayList<>();
    	Date start = Util.toDateFromYYYYMMDD1(startdate);
    	Calendar calenderstart = Calendar.getInstance();
    	calenderstart.setTime(start);
    	
    	Date end = Util.toDateFromYYYYMMDD1(enddate);
    	Calendar calenderend = Calendar.getInstance();
    	calenderend.setTime(end);
    	if(end.before(start)){
    		return dates;
    	}
    	do{
    		dates.add(calenderstart.getTime());
    		calenderstart.add(Calendar.DAY_OF_MONTH, 1);
    	} while(!calenderstart.after(calenderend));
    	
    	return dates;
	}
	public static Float getcarprice(String carprice){
		if(Util.isEmpty(carprice)){
			return 0f;
		}
		char[] chrarray = carprice.toCharArray();
		StringBuffer carpricee = new StringBuffer();
		for(int index = chrarray.length -1;index>=0;index--){
			char carsub = chrarray[index];
			if((carsub >= 48 & carsub <=  57) || carsub == '.'){
				carpricee.append(carsub);
			}else {
				break;
			}
		}
		carpricee = carpricee.reverse();
		System.out.println(carpricee);
		return Util.convertToFloat(carpricee.toString());
	}
	public static List<String> gettireinfo(String tireinfo){
		if(Util.isEmpty(tireinfo)){
			return null;
		}
		String tirewidth = "";
		String flatratio = "";
		String size = "";
		String[] tire1array = tireinfo.split("\\/");
		List<String> rtnlist = new ArrayList<>();
		
		if(tire1array.length < 2){
			return rtnlist;
		}
		
		if(tire1array.length >= 1){
			tirewidth = tire1array[0];
		}
		if(tire1array.length >= 2){

			tire1array = tire1array[1].trim().split("R");
			
			if(tire1array.length < 2){
				return rtnlist;
			}
			
			if(tire1array.length >= 1){
				flatratio = tire1array[0].trim();
			}
			if(tire1array.length >= 2){
				String digitstr = getstartstring(tire1array[1]);
				if(!Util.isEmpty(digitstr)){
					size = digitstr;
				}
			}
		}
		
		if(!Util.isEmpty(tirewidth)){
			rtnlist.add(tirewidth);
		}
		if(!Util.isEmpty(flatratio)){
			rtnlist.add(flatratio);
		}
		if(!Util.isEmpty(size)){
			rtnlist.add(size);
		}
		if(rtnlist.size() < 3){
			return null;
		}
		return rtnlist;
	}
	
	public static String getstartstring(String objstring){
		if(Util.isEmpty(objstring)){
			return null;
		}
		String rtn = "";
		char[] chrs = objstring.toCharArray();
		for(char chr : chrs){
			if(chr >= 48 & chr <=  57){
				rtn += chr;
			} else {
				break;
			}
		}
		
		if(Util.isEmpty(rtn)){
			return null;
		}
		return rtn;
	}
	
    public static Object deepCopy(Object obj) {
    	try{
    	       ByteArrayOutputStream bos = new ByteArrayOutputStream();  
    	       
    	        ObjectOutputStream oos = new ObjectOutputStream(bos);  
    	  
    	        oos.writeObject(obj);  
    	  
    	        //将流序列化成对象  
    	        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());  
    	  
    	        ObjectInputStream ois = new ObjectInputStream(bis);  
    	  
    	        return ois.readObject();  
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    	return null;
    }
    
	public static boolean isprice(String str){
		try{
			 Float.parseFloat(str);
			 return true;
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * 判断日期是否是三月前的日期
	 * @param date 
	 * @return 三月前的日期为:true
	 */
	public static boolean decideDate(String date){
		boolean flag=false;
		if (date!=null&&date.length()==8) {
			Date currentDate=new Date();
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(currentDate);
			calendar.add(calendar.MONTH, -3);
			Date beforethreedate=	calendar.getTime();
			SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
			String threedate=format.format(beforethreedate);
			
			int three=Integer.parseInt(threedate);
			int nowDate=	Integer.parseInt(date);
			
			if (nowDate<three) {
				flag=true;
			}
		}
		return flag;
	}
	
	
    public static String replaceBlank1(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        dest = dest.replaceAll("-", "");
        return dest;
    }
    
    /**
     * 左补 formatString
     * ("1030",6, "0")
     * 
     */
    public static String lpad(String input, int n, String formatString) {
    	if(input == null){
    		input = "";
    	}
		if (input.length() < n) {
			while (input.length() < n) {
				input = formatString + input;
			}
		}
		return input;
	}
    
    /**
     * 右补 formatString
     * ("1030",6, "0")
     * 
     */
    public static String rpad(String input, int n, String formatString) {
    	if(input == null){
    		input = "";
    	}
		if (input.length() < n) {
			while (input.length() < n) {
				input = input+formatString;
			}
		}
		return input;
	}
    
    public static String formatchar(String input,String formatString){
//    	String input = "dsaf";
//		String formatString = "%";
    	if(Util.isEmpty(input) || Util.isEmpty(formatString)){
    		return "";
    	}
		StringBuffer newstr = new StringBuffer();
		for (int i=0;i<input.length();i++) {
			String substr = input.substring(i,i+1);
			newstr.append(formatString);
			newstr.append(substr);
		}
    	return newstr.substring(1).toString();
    }
    
    /**
	 * 
	 * @param sidx
	 * @param sord
	 * @param start
	 * @param rows
	 * @param flg 0:排序加分页，1:只排序，2:只分页，3+无
	 * @return
	 */
	public static String getOrderLimit(String sidx,String sord,Integer start,Integer rows,int flg){
		String ordrelimit = "";
		if(flg==0 || flg==1){
			if(Util.isEmpty(sidx)){
				return "";
			}
		}
		if(Util.isEmpty(sord)){
			sord = "desc";
		}
		if(start==null){
			start = 0;
		}
		if(rows==null){
			rows = 0;
		}
		if(flg==0){
			ordrelimit=" order by "+sidx+" "+sord+" limit "+start+","+rows+" ";
		}else if(flg==1){
			ordrelimit=" order by "+sidx+" "+sord+" ";
		}else if(flg==2){
			ordrelimit=" limit "+start+","+rows+" ";
		}else{
			
		}
		return ordrelimit;
	}

	
	public static BigDecimal positivedecimal(BigDecimal value){
		BigDecimal decimalPrice = BigDecimal.ZERO;
		if(value!=null){
			decimalPrice = value.compareTo(BigDecimal.ZERO)>0?value:BigDecimal.ZERO;
		}
		return decimalPrice;
	}
	
	public static BigDecimal positivedecimal2(BigDecimal value){
		BigDecimal decimalPrice = BigDecimal.ZERO;
		if(value!=null){
			decimalPrice = value;
		}
		return decimalPrice;
	}
	
	public static BigDecimal positivedecimal3(BigDecimal value){
		DecimalFormat df = new DecimalFormat("0.00");
		BigDecimal decimalPrice = BigDecimal.ZERO;
		if(value!=null){
			 decimalPrice = new BigDecimal(df.format(value));
		}
		return decimalPrice;
	}
	
	//获取整型list转成string供sql使用
	public static String getIdsStr(List<Integer> ids){
		String idStr = "";
		if(ids!=null&&ids.size()>0){
			idStr += "(";
			for(Integer i:ids){
				idStr += i + ",";
			}
			if(idStr.length()>1){
				idStr=idStr.substring(0, idStr.length()-1);
			}
			idStr += ")";
		}
		return idStr;
	}
	
	public static String getimgurl(int picid){

        DecimalFormat df1 = new DecimalFormat("0000");
        int a = picid/1000;
        String c = df1.format(a);
        int b = picid - a * 1000;
        String url = "http://cdn-image.handlecar-oms.com/image/" + c + "/"+b+".jpg";
		return url;
	}
	
	/**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException{    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }  
    
    /** 
    *字符串的日期格式的计算 
    */  
    public static int minutesBetween(String smdate,String bdate){  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");  
        Calendar cal = Calendar.getInstance();    
        try {
			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();                 
	        cal.setTime(sdf.parse(bdate));    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*60);  
	        return Integer.parseInt(String.valueOf(between_days));     
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
       return 0;     
    }  

    
	public static String getDate2(String str){
		String str_return = "";
		if(str!=null&&str.length()==8){
			String yy = str.substring(0,4)+".";
			String mm = str.substring(4,6)+".";
			String dd = str.substring(6);
			str_return = yy+mm+dd;
		}
		return str_return;
	}

	public static <T> String converOjbtoJSONDataCenter(List<T> targetlist) throws Exception{
		JSONArray json = JSONArray.fromObject(targetlist);
		String data = json.toString();
		return data;
	}

	public static <T> String converOjbtoJSONDataCenterNoArray(Object targetobj) throws Exception{
		JSONObject json = JSONObject.fromObject(targetobj);
		String data = json.toString();
		return data;
	}

	public static String returnResult(String jsoncallback, Object obj){
		String returnjson = null;
		try {
			returnjson = Util.converOjbtoJSONDataCenterNoArray(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!Util.isEmpty(jsoncallback)){
			returnjson = jsoncallback + "(" + returnjson + ")";
		}
		return returnjson;
	}

	public static String returnResult(String jsoncallback, List<?> list){
		String returnjson = null;
		try {
			returnjson = Util.converOjbtoJSONDataCenter(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!Util.isEmpty(jsoncallback)){
			returnjson = jsoncallback + "(" + returnjson + ")";
		}
		return returnjson;
	}

	public static <T> String converOjbtoJSONData2(Object obj) throws Exception{
		JSONObject json = JSONObject.fromObject(obj);
		String data = json.toString();
		return data;
	}

	public static Map<String, Object> parseJsonToMap(String jsonStr){
		Map<String, Object> map = new HashMap<String, Object>();
		//最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for(Object k : json.keySet()){
			Object v = json.get(k);
			map.put(k.toString(), v);
		}
		return map;
	}
	public static String getmp3url(int voiceid){
		if(voiceid==0){
			return "";
		}
		DecimalFormat df1 = new DecimalFormat("0000");
		int a = voiceid/1000;
		String c = df1.format(a);
		int b = voiceid - a * 1000;
		String url = "http://cdn-image.handlecar-oms.com/sound/" + c + "/"+b+".mp3";
		return url;
	}

	public static int getWeekforcn(Calendar cal){
		int week = cal.get(Calendar.DAY_OF_WEEK);
		if(week == 1){
			week = 7;
		} else {
			week = week -1;
		}
		return week;
	}

	public static String formartworkhourno(String workhourno){
		workhourno = workhourno.trim();

		int index = 1;
		int endindex = 2;
		String workhourno1 = "";
		if(workhourno.length() >=index ){
			if(workhourno.length() < endindex){
				endindex = workhourno.length();
			}
			workhourno1 = workhourno.substring(index-1,endindex);
			workhourno = workhourno.substring(endindex);
			workhourno = workhourno.trim();
		}

		index = 1;
		endindex = 2;
		String workhourno2 = "";
		if(workhourno.length() >= index ){
			if(workhourno.length() < endindex){
				endindex = workhourno.length();
			}
			workhourno2 = workhourno.substring(index-1,endindex);
			workhourno = workhourno.substring(endindex);
			workhourno = workhourno.trim();
		}

		index = 1;
		endindex = 2;
		String workhourno3 = "";
		if(workhourno.length() >= index ){
			if(workhourno.length() < endindex){
				endindex = workhourno.length();
			}
			workhourno3 = workhourno.substring(index-1,endindex);
			workhourno = workhourno.substring(endindex);
			workhourno = workhourno.trim();
		}

		index = 1;
		endindex = 2;
		String workhourno4 = "";
		if(workhourno.length() >= index ){
			if(workhourno.length() < endindex){
				endindex = workhourno.length();
			}
			workhourno4 = workhourno.substring(index-1,endindex);
			workhourno = workhourno.substring(endindex);
			workhourno = workhourno.trim();
		}

		String workhournoall = "";
		workhournoall = workhourno1;
		if(!Util.isEmpty(workhourno2)){
			workhournoall += " " + workhourno2;
		}
		if(!Util.isEmpty(workhourno3)){
			workhournoall += " " + workhourno3;
		}
		if(!Util.isEmpty(workhourno4)){
			workhournoall += " " + workhourno4;
		}
		return workhournoall;
	}

	public static String formartpartno(String partno){

		partno = partno.trim();
		int index = 1;
		int endindex = 3;
		String partno1 = "";
		if(partno.startsWith("N")){
			endindex = 1;
		}
		if(partno.length() >=index ){
			if(partno.length() < endindex){
				endindex = partno.length();
			}
			partno1 = partno.substring(index-1,endindex);
			partno = partno.substring(endindex);
			partno = partno.trim();
		}

		index = 1;
		endindex = 3;
		String partno2 = "";
		if(partno.length() >=index ){
			if(partno.length() < endindex){
				endindex = partno.length();
			}
			partno2 = partno.substring(index-1,endindex);
			partno = partno.substring(endindex);
			partno = partno.trim();
		}

		index = 1;
		endindex = 3;
		String partno3 = "";
		if(partno.length() >=index ){
			if(partno.length() < endindex){
				endindex = partno.length();
			}
			partno3 = partno.substring(index-1,endindex);
			partno = partno.substring(endindex);
			partno = partno.trim();
		}


		index = 1;
		endindex = 2;
		String partno4 = "";
		if(partno.length() >=index ){
			if(partno.length() < endindex){
				endindex = partno.length();
			}
			partno4 = partno.substring(index-1,endindex);
			partno = partno.substring(endindex);
			partno = partno.trim();
		}

		index = 1;
		endindex = 10;
		String partno5 = "";
		if(partno.length() >=index ){
			if(partno.length() < endindex){
				endindex = partno.length();
			}
			partno5 = partno.substring(index-1,endindex);
			partno = partno.substring(endindex);
			partno = partno.trim();
		}
		String partnoall = "";
		partnoall = partno1;
		if(!Util.isEmpty(partno2)){
			partnoall += " " + partno2;
		}
		if(!Util.isEmpty(partno3)){
			partnoall += " " + partno3;
		}
		if(!Util.isEmpty(partno4)){
			partnoall += " " + partno4;
		}
		if(!Util.isEmpty(partno5)){
			partnoall += " " + partno5;
		}

		return partnoall;
	}

	//格式化备件图号
	public static String formartimgno(String partno){

		partno = partno.trim();
		int index = 1;
		int endindex = 3;
		String partno1 = "";
		if(partno.length() >=index ){
			if(partno.length() < endindex){
				endindex = partno.length();
			}
			partno1 = partno.substring(index-1,endindex);
			partno = partno.substring(endindex);
			partno = partno.trim();
		}

		index = 1;
		endindex = 3;
		String partno2 = "";
		if(partno.length() >=index ){
			if(partno.length() < endindex){
				endindex = partno.length();
			}
			partno2 = partno.substring(index-1,endindex);
			partno = partno.substring(endindex);
			partno = partno.trim();
		}

		index = 1;
		endindex = 3;
		String partno3 = "";
		if(partno.length() >=index ){
			if(partno.length() < endindex){
				endindex = partno.length();
			}
			partno3 = partno.substring(index-1,endindex);
			partno = partno.substring(endindex);
			partno = partno.trim();
		}


		index = 1;
		endindex = 1;
		String partno4 = "";
		if(partno.length() >=index ){
			if(partno.length() < endindex){
				endindex = partno.length();
			}
			partno4 = partno.substring(index-1,endindex);
			partno = partno.substring(endindex);
			partno = partno.trim();
		}

		index = 1;
		endindex = 10;
		String partno5 = "";
		if(partno.length() >=index ){
			if(partno.length() < endindex){
				endindex = partno.length();
			}
			partno5 = partno.substring(index-1,endindex);
			partno = partno.substring(endindex);
			partno = partno.trim();
		}
		String partnoall = "";
		partnoall = partno1;
		if(!Util.isEmpty(partno2)){
			partnoall += " " + partno2;
		}
		if(!Util.isEmpty(partno3)){
			partnoall += " " + partno3;
		}
		if(!Util.isEmpty(partno4)){
			partnoall += " " + partno4;
		}
		if(!Util.isEmpty(partno5)){
			partnoall += " " + partno5;
		}

		return partnoall;
	}

	public static  String getstartdateforyyyymmddhhmm(String date){

		if(date.length()>=8){
			date = date.substring(0,8);
		}

		return date +"000000";

	}

	public static String getenddateforyyyymmddhhmm(String date){

		if(date.length()>=8){
			date = date.substring(0,8);
		}
		return date + "2400000";
	}

	//税后价格取得
	public static BigDecimal gettaxaftprice(BigDecimal price){
		if(price == null){
			return BigDecimal.ZERO;
		}
		BigDecimal taxaftprice = price.multiply(new BigDecimal(1.17));
		return taxaftprice;
	}

	//税前价格取得
	public static BigDecimal gettaxbefprice(BigDecimal price){
		if(price == null){
			return BigDecimal.ZERO;
		}
		BigDecimal taxbefprice = price.divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_UP);
		return taxbefprice;
	}

	//获取当前月
	public static String getNowYYYYMM() {
		String result = "";
		SimpleDateFormat l_sdf = new SimpleDateFormat("yyyyMM");
		result = l_sdf.format(new Date());
		return result;
	}


	/**
	 * 千分位
	 * @param num
	 * @return
	 */
	public static String formatNumber(int num){
		NumberFormat numberFormat1 = NumberFormat.getNumberInstance();
		return numberFormat1.format(num); //结果是11,122.33
	}
	/**
	 * 千分位
	 * @param num
	 * @return
	 */
	public static String formatFloat(float num){
		NumberFormat numberFormat1 = NumberFormat.getNumberInstance();
		return numberFormat1.format(num); //结果是11,122.33
	}




	public static  String getOrderdate(String date){

		if(isEmpty(date))
			return "";

		String[] datearray = date.split("/");
		String year = "";
		String month = "";
		String day = "";

		if(datearray.length <3){
			datearray = date.split("-");
		}

		if(datearray.length <3){
			datearray = date.split("\\.");
		}

		if(datearray.length > 0){
			year = datearray[0];
		}
		if(datearray.length > 1){
			month = datearray[1];
		}
		if(datearray.length > 2){
			day = datearray[2];
		}

		if(year.length() == 2){
			year = "20" + year;
		}
		if(month.length() == 1){
			month = "0" + month;
		}

		if(day.length() == 1){
			day = "0" + day;
		}

		return year + month+day;


	}



	/**
	 * 验证码相关
	 *
	 * @return
	 */

	/*public static ReturnValue checkVerify(String phoneno, String verify) {

		if ("111111".equals(verify))
			return new ReturnValue(ReturnValue.SUCCESS, "验证通过");
		Record social = Db.findFirst("select verfitycode,createtime from "
				+ ITableName.TBL_USER_VERIFYCODE + " where phone =? ", phoneno);
		if (social == null)
			return new ReturnValue(ReturnValue.VERIFYNULL, "验证码不存在");
		String verifyDb = social.getStr("verfitycode");
		if (!verifyDb.equals(verify))
			return new ReturnValue(ReturnValue.VERIFYERR, "验证码错误");
		int verifytime = social.getInt("createtime");
		if (System.currentTimeMillis() / 1000 - verifytime > 1800000)
			return new ReturnValue(ReturnValue.VERIFYVALID, "验证码过期");
		return new ReturnValue(ReturnValue.SUCCESS, "验证通过");
	}

	public static String getVercode(String phoneno) {
		Record social = Db.findFirst("select verfitycode,createtime from "
				+ ITableName.TBL_USER_VERIFYCODE + " where phone =? ", phoneno);
		String rt_vercode = "";
		int verifytime = 0;
		if (social == null) {
			rt_vercode = produceVerify(rt_vercode, verifytime);
			Db.update("insert into " + ITableName.TBL_USER_VERIFYCODE
							+ " (verfitycode,createtime,phone) values(?,?,?)",
					rt_vercode, System.currentTimeMillis() / 1000, phoneno);
		} else {
			if (social.get("verfitycode") != null)
				rt_vercode = social.getStr("verfitycode");
			if (social.get("createtime") != null)
				verifytime = social.getInt("createtime");
			rt_vercode = produceVerify(rt_vercode, verifytime);
			Db.update("update " + ITableName.TBL_USER_VERIFYCODE
							+ " set verfitycode=? ,createtime=? where phone =?",
					rt_vercode, System.currentTimeMillis() / 1000, phoneno);
		}
		try {

			System.out.println("发送验证码："+phoneno+" vercode"+rt_vercode);
			//SmsAlidayuUtil.getInstance().send(phoneno, rt_vercode);
			SmsAliyunUtil.send(phoneno,rt_vercode);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rt_vercode;

	}*/

	private static String produceVerify(String vercode, long verfy) {
		// 生成6位数字验证码

		String rt_vercode = "";
		if (verfy == 0 || (System.currentTimeMillis() - verfy > 1800000)) {
			for (int i = 0; i < 4; i++) {
				String rand = String.valueOf(Rand.RandNum(10));
				rt_vercode += rand;
			}
		} else {
			rt_vercode = vercode;
		}

		return rt_vercode;
	}

	/**
	 * 两个日期时间差
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(String smdate,String bdate) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);

		return Integer.parseInt(String.valueOf(between_days));
	}



}