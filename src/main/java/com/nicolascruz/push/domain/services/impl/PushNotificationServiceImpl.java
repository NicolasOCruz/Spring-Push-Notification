package com.nicolascruz.push.domain.services.impl;

import com.nicolascruz.push.api.v1.model.records.PushSubscriptionRecord;
import lombok.extern.log4j.Log4j2;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PushNotificationServiceImpl {

    @Value("${webpush.publicKey}")
    private String publicKey;

    @Value("${webpush.privateKey}")
    private String privateKey;

    public void sendPushNotification(PushSubscriptionRecord subscription) {
        PushService pushService = new PushService();

        try {
            pushService.setPublicKey(publicKey);
            pushService.setPrivateKey(privateKey);

            String payload = "{\n" +
                    "  \"notification\": {\n" +
                    "    \"title\": \"New Notification!\",\n" +
                    "    \"data\": {\n" +
                    "      \"onActionClick\": {\n" +
                    "        \"default\": {\"operation\": \"openWindow\", \"url\": \"foo\"}\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }\n" +
                    "}";

            Subscription.Keys keys = new Subscription.Keys(subscription.keys().get("p256dh").toString(),
                    subscription.keys().get("auth").toString());
            Subscription sub = new Subscription(subscription.endpoint(), keys);
            Notification notification = new Notification(sub, payload);

            pushService.send(notification);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
