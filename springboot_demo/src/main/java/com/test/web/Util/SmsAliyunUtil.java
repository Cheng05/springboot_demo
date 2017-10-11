package com.test.web.Util;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;
import com.aliyun.oss.ServiceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bamcdx on 2017/5/31.
 */
public class SmsAliyunUtil {


    private  static final String ENDPOINT ="";
    private static   final String topicname="";




/*
    public static void main(String[] args) {

        List<String> phones=new ArrayList<>();
        SmsAliyunUtil.send(phones,"25804");


        */
/**
         * Step 1. 获取主题引用
         *//*

        */
/*CloudAccount account = new CloudAccount(OssHelper.ACCESS_ID, OssHelper.ACCESS_KEY, ENDPOINT);
        MNSClient client = account.getMNSClient();
        CloudTopic topic = client.getTopicRef(topicname);
        *//*
*/
/**
         * Step 2. 设置SMS消息体（必须）
         *
         * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
         *//*
*/
/*
        RawTopicMessage msg = new RawTopicMessage();
        msg.setMessageBody("sms-message");
        *//*
*/
/**
         * Step 3. 生成SMS消息属性
         *//*
*/
/*
        MessageAttributes messageAttributes = new MessageAttributes();
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
        // 3.1 设置发送短信的签名（SMSSignName）
        batchSmsAttributes.setFreeSignName("绿色直通车");
        // 3.2 设置发送短信使用的模板（SMSTempateCode）
        batchSmsAttributes.setTemplateCode("SMS_70375233");
        // 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
        smsReceiverParams.setParam("code", "21354");
        //smsReceiverParams.setParam("product", "上海绿色新能源");
        // 3.4 增加接收短信的号码
        batchSmsAttributes.addSmsReceiver("", smsReceiverParams);

        messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
        try {
            *//*
*/
/**
             * Step 4. 发布SMS消息
             *//*
*/
/*
            TopicMessage ret = topic.publishMessage(msg, messageAttributes);
            System.out.println("MessageId: " + ret.getMessageId());
            System.out.println("MessageMD5: " + ret.getMessageBodyMD5());

        } catch (ServiceException se) {
            System.out.println(se.getErrorCode() + se.getRequestId());
            System.out.println(se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();*//*

    }
*/


    //单个手机发送验证码
    public static void send(String phone,String code){
        List<String> phones=new ArrayList<>();
        phones.add(phone);
        SmsAliyunUtil.send(phones,code);
    }

    //多个手机发送
    public static void send(List<String> phones,String code){
        /**
         * Step 1. 获取主题引用
         */
        CloudAccount account = new CloudAccount(OssHelper.ACCESS_ID, OssHelper.ACCESS_KEY, ENDPOINT);
        MNSClient client = account.getMNSClient();
        CloudTopic topic = client.getTopicRef(topicname);
        /**
         * Step 2. 设置SMS消息体（必须）
         *
         * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
         */
        RawTopicMessage msg = new RawTopicMessage();
        msg.setMessageBody("sms-message");
        /**
         * Step 3. 生成SMS消息属性
         */
        MessageAttributes messageAttributes = new MessageAttributes();
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
        // 3.1 设置发送短信的签名（SMSSignName）
        batchSmsAttributes.setFreeSignName("绿色直通车");
        // 3.2 设置发送短信使用的模板（SMSTempateCode）
        batchSmsAttributes.setTemplateCode("SMS_70375233");
        // 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
        smsReceiverParams.setParam("code", code);
        smsReceiverParams.setParam("product", "上海绿色新能源");
        // 3.4 增加接收短信的号码
        if(phones!=null && phones.size()>0){
            for(String phone:phones){
                batchSmsAttributes.addSmsReceiver(phone, smsReceiverParams);
                messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
                try {
                    /**
                     * Step 4. 发布SMS消息
                     */
                    TopicMessage ret = topic.publishMessage(msg, messageAttributes);
                    System.out.println("MessageId: " + ret.getMessageId());
                    System.out.println("MessageMD5: " + ret.getMessageBodyMD5());

                } catch (ServiceException se) {
                    System.out.println(se.getErrorCode() + se.getRequestId());
                    System.out.println(se.getMessage());
                    se.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        client.close();
    }


}
