package com.nicolascruz.push.api.v1.model.records;

import java.util.Map;

public record PushSubscriptionRecord(String endpoint, String expirationTime, Map<String, Object> keys) {
}
