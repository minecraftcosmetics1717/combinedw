package com.stovetoptoad.jujutsuslop.client;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ShadowDiveSync {
    public static final Map<UUID, Boolean> MAP = new ConcurrentHashMap<>();
}