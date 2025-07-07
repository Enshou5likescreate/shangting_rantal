package com.atguigu.lease.web.app.service.impl;



import com.atguigu.lease.common.sms.TwilioSMSProperties;
import com.atguigu.lease.web.app.service.SmsService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {



        // Twilioの認証情報
    public SmsServiceImpl() {
            Twilio.init(TwilioSMSProperties.ACCOUNT_SID,TwilioSMSProperties.AUTH_TOKEN );
        }


        public  void sendCode(String phone, String code) {
            String messageBody = "あなたの認証コードは: " + code;

            Message.creator(
                    new PhoneNumber(phone),        // 受信者の電話番号（例：+819012345678）
                    new PhoneNumber(TwilioSMSProperties.TWILIO_PONHE_NUMBER), // Twilioから発行された電話番号
                    messageBody
            ).create();

            System.out.println("SMS送信完了: " + phone);
        }
    }

