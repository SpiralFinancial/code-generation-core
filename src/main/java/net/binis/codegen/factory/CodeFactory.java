package net.binis.codegen.factory;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class CodeFactory {

    private static final Map<Class<?>, RegistryEntry> registry = new HashMap<>();

    private CodeFactory() {
        //Do nothing
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(Class<T> cls) {
        var entry = registry.get(cls);
        if (entry != null) {
            return (T) entry.getImplClass().create();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <M, T, P> M modify(P parent, T value) {
        var entry = registry.get(value.getClass());
        if (entry != null) {
            return (M) entry.getModifierClass().create(parent, value);
        }
        return null;

    }

    public static void registerType(Class<?> intf, ObjectFactory impl, EmbeddedObjectFactory modifier) {
        registry.put(intf, RegistryEntry.builder().implClass(impl).modifierClass(modifier).build());
    }

    @Data
    @Builder
    private static class RegistryEntry {
        private ObjectFactory implClass;
        private EmbeddedObjectFactory modifierClass;
    }
}
