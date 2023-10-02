package be.kdg.reflection;

import java.lang.reflect.*;

public class ReflectionTools {
    /**
     * Prints the class name, superclass name, package name, used interfaces, constructors, private attributes and functions of a class.
     *
     * @param aClass The class to be analyzed.
     */
    public static void classAnalysis(Class<?> aClass) {
        StringBuilder sB = new StringBuilder("Analysis of class: ").append(aClass.getSimpleName()).append("\n===================================\n");

        //Fully qualified name
        sB.append("Fully qualified class name:\n\t").append(aClass.getName());
        //Name of superclass
        sB.append("\nName of Superclass:\n\t").append(aClass.getSuperclass().getName());
        //Name of package
        sB.append("\nPackage name:\n\t").append(aClass.getPackageName());
        //Used interfaces
        sB.append("\nUsed Interfaces:");
        if (aClass.getInterfaces().length > 0) {
            for (Class<?> i : aClass.getInterfaces()) {
                sB.append("\n\t* ").append(i.getName());
            }
        } else {
            sB.append("\n\tNo Interfaces used");
        }
        //Present Constructors
        sB.append("\nPresent constructors:");
        for (Constructor<?> c : aClass.getConstructors()) {
            sB.append("\n\t* ").append(c.toGenericString());
        }
        //Present attributes
        sB.append("\nPresent private attributes:");
        if (aClass.getDeclaredFields().length > 0) {
            for (Field f : aClass.getDeclaredFields()) {
                f.setAccessible(true);
                if (Modifier.toString(f.getModifiers()).contains("private")) {
                    sB.append(String.format("\n\t * %s (%s)", f.getName(), f.getType()));
                }
            }
        } else {
            sB.append("\n\tNo private fields declared");
        }
        //Getters, setters + other methods
        sB.append("\nPresent functions:");

        StringBuilder getter = new StringBuilder();
        StringBuilder setter = new StringBuilder();
        StringBuilder functions = new StringBuilder();

        for (Method m : aClass.getDeclaredMethods()) {
            if (m.getName().startsWith("get") ) {
                getter.append("\n\t\t* ").append(m.getName());
            } else if (m.getName().startsWith("set") ) {
                setter.append("\n\t\t*").append(m.getName());
            } else {
                functions.append("\n\t\t* ").append(m.getName());
            }
        }

        if (getter.isEmpty()) {
            getter.append("\n\t\tNo getter functions declared");
        }
        if (setter.isEmpty()) {
            setter.append("\n\t\tNo setter functions declared");
        }
        if (functions.isEmpty()) {
            functions.append("\n\t\tNo other functions declared");
        }

        sB.append("\n\tGetters:").append(getter);
        sB.append("\n\tSetters:").append(setter);
        sB.append("\n\tOther functions:").append(functions);

        System.out.println(sB);
    }

    public static Object runAnnotated(Class<?> aClass)
            throws NoSuchMethodException,
            SecurityException,
            InstantiationException,
            IllegalAccessException,
            IllegalArgumentException,
            InvocationTargetException {

        Object o = aClass.getDeclaredConstructor().newInstance();

        for (Method m : o.getClass().getDeclaredMethods()) {
            CanRun c = m.getDeclaredAnnotation(CanRun.class);
            if (m.getParameterCount() == 1 && m.getParameterTypes()[0].getSimpleName().equals("String") && c != null){
                m.invoke(o, c.value());
            }
        }
        return o;
    }
}
