package ru.test.SpyTheSpies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

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
        try {
            Field attributesField = detectiveClass.getDeclaredField("attributes");
            attributesField.setAccessible(true);

            Set attributes = (Set) attributesField.get(detective);
            Assertions.assertArrayEquals(
                    new String[]{"smart", "tall", "strong", "stupid", "short", "weak"}, attributes.toArray());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            Assertions.fail(e);
        }


    }

    @Test
    void allSpiesHasAttribute() {
        try {
            Method allSpiesHasAttribute = detectiveClass.getDeclaredMethod("allSpiesHasAttribute", String.class);
            allSpiesHasAttribute.setAccessible(true);

            Boolean invokeRes = (Boolean) allSpiesHasAttribute.invoke(detective, "smart");
            Assertions.assertTrue(invokeRes);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void getSpiesAttributes() {
        try {
            Method getSpiesAttributes = detectiveClass.getDeclaredMethod("getSpiesAttributes");
            getSpiesAttributes.setAccessible(true);

            Object[] invokeRes = (Object[]) getSpiesAttributes.invoke(detective);
            Assertions.assertArrayEquals(new String[]{"smart", "tall", "strong"}, invokeRes);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            Assertions.fail(e);
        }
    }
}