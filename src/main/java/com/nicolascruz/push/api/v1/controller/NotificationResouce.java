package com.nicolascruz.push.api.v1.controller;

import com.nicolascruz.push.api.v1.model.records.PushSubscriptionRecord;
import com.nicolascruz.push.domain.services.impl.PushNotificationServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin
@Log4j2
public class NotificationResouce {

    PushNotificationServiceImpl pushNotificationService;

    public NotificationResouce(PushNotificationServiceImpl pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }

    @PostMapping
    public ResponseEntity<?> postSubscription(@RequestBody @Valid PushSubscriptionRecord subscription) {
        log.info(subscription.toString());
        pushNotificationService.sendPushNotification(subscription);
        return ResponseEntity.ok().build();
    }

}
