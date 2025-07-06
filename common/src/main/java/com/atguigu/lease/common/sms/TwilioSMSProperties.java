package com.atguigu.lease.common.sms;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
@Data
public class TwilioSMSProperties {

    @Value("ACCOUNT_SID")
    public static String ACCOUNT_SID ;

    @Value("AUTH_TOKEN")
    public static String AUTH_TOKEN;

    @Value("TWILIO_PHONE_NUMBER")
    public static String TWILIO_PONHE_NUMBER ;

    @Value("SEND_TO_PHONE_NUMBER")
    public static String SEND_TO_PHONE_NUMBER;
}
