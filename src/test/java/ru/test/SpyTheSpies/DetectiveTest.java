package ru.test.SpyTheSpies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Consumer;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DetectiveTest {
    private Detective detective;
    private Class<? extends Detective> detectiveClass;

    @BeforeAll
    void setUp() {
        detective = new Detective(
                new String[]{"Amanat", "Jelly"},
                new String[]{"Amanat 3 smart tall strong", "Petsy 3 stupid short weak", "Jelly 1 smart", "Igor 2 stupid strong"});
        detectiveClass = detective.getClass();
    }

    @Test
    void fillAttributes() {
            Set attributes = (Set) getFieldValue("attributes", Assertions::fail, detective);
            Assertions.assertArrayEquals(
                    new String[]{"smart", "tall", "strong", "stupid", "short", "weak"},
                    attributes != null ? attributes.toArray() : new Object[0]);
    }

    @Test
    void getInnocentsCount() {
        int invokeRes = (int) invokeMethod("getInnocentsCount", Assertions::fail, detective);
        Assertions.assertEquals(2, invokeRes);
    }

    @Test
    void allSpiesHasAttribute() {
            boolean invokeRes = (boolean) invokeMethod("allSpiesHasAttribute", Assertions::fail, detective, "smart");
            Assertions.assertTrue(invokeRes);
    }

    @Test
    void allInnocentsHasNotAttribute() {
        boolean invokeRes = (boolean) invokeMethod("allInnocentsHasNotAttribute", Assertions::fail, detective, "smart");
        Assertions.assertTrue(invokeRes);
    }

    @Test
    void notAllInnocentsHasNotAttribute() {
        boolean invokeRes = (boolean) invokeMethod("allInnocentsHasNotAttribute", Assertions::fail, detective, "weak");
        Assertions.assertFalse(invokeRes);
    }

    @Test
    void getSpiesDescriptions() {
        Object[] invokeRes = (Object[]) invokeMethod("getSpiesDescriptions", Assertions::fail, detective);
        Assertions.assertArrayEquals(new String[]{"Amanat 3 smart tall strong", "Jelly 1 smart"}, invokeRes);
    }

    @Test
    void getSpiesCount() {
        int invokeRes = (int) invokeMethod("getSpiesCount", Assertions::fail, detective);
        Assertions.assertEquals(2, invokeRes);
    }

    @Test
    void getInnocentsDescriptions() {
        Object[] invokeRes = (Object[]) invokeMethod("getInnocentsDescriptions", Assertions::fail, detective);
        Assertions.assertArrayEquals(new String[]{"Petsy 3 stupid short weak", "Igor 2 stupid strong"}, invokeRes);
    }

    @Test
    void getInnocentsNames() {
        Object[] invokeRes = (Object[]) invokeMethod("getInnocentsNames", Assertions::fail, detective);
        Assertions.assertArrayEquals(new String[]{"Petsy", "Igor"}, invokeRes);
    }

    @Test
    void getSuspectsNames() {
        Object[] invokeRes = (Object[]) invokeMethod("getSuspectsNames", Assertions::fail, detective);
        Assertions.assertArrayEquals(new String[]{"Amanat", "Petsy", "Jelly", "Igor"}, invokeRes);
    }

    @Test
    void getSuspectsCount() {
        Object[] fieldValue = (Object[]) getFieldValue("suspectsDescriptions", Assertions::fail, detective);
        Assertions.assertEquals(4, fieldValue != null ? fieldValue.length : 0);
    }

    void getSpiesAttributes() {
        Object[] invokeRes = (Object[]) invokeMethod("getSpiesAttributes", Assertions::fail, detective);
        Assertions.assertArrayEquals(new String[]{"smart", "tall", "strong"}, invokeRes);
    }

    private Object getFieldValue(String fieldName, Consumer<Exception> exceptionCatcher, Object obj) {
        try {
            Field field = detectiveClass.getDeclaredField(fieldName);
            field.setAccessible(true);

            return field.get(detective);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Object invokeMethod(String name, Consumer<Exception> exceptionCatcher, Object obj, Object... args) {
        Class[] argsTypes = new Class[args.length];

        for (int i = 0; i < args.length; i++)
            argsTypes[i] = args[i++].getClass();

        try {
            Method method = detectiveClass.getDeclaredMethod(name, argsTypes);
            method.setAccessible(true);

            return method.invoke(obj, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            exceptionCatcher.accept(e);
        }

        return null;
    }
}