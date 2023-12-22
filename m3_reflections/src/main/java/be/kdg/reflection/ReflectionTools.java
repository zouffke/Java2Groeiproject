package be.kdg.reflection;

import java.lang.reflect.*;

/**
 * The ReflectionTools class provides static methods for analyzing a class using reflection.
 * It can print various information about a class, such as its name, superclass, package, interfaces, constructors, attributes, and methods.
 * It can also run methods that are annotated with a specific annotation.
 */
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

    /**
     * Runs methods of a class that are annotated with the CanRun annotation.
     * The methods must have a single parameter of type String.
     * The value of the CanRun annotation is passed as the argument to the method.
     *
     * @param aClass The class whose methods are to be run.
     * @return An instance of the class.
     * @throws NoSuchMethodException if a matching method is not found.
     * @throws SecurityException if a security violation occurred.
     * @throws InstantiationException if the class that declares the underlying constructor represents an abstract class.
     * @throws IllegalAccessException if this Constructor object is enforcing Java language access control and the underlying constructor is inaccessible.
     * @throws IllegalArgumentException if the number of actual and formal parameters differ; if an unwrapping conversion for primitive arguments fails; or if, after possible unwrapping, a parameter value cannot be converted to the corresponding formal parameter type by a method invocation conversion.
     * @throws InvocationTargetException if the underlying constructor throws an exception.
     */
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
            Type[] params = m.getGenericParameterTypes();
            if (params.length == 1 && params[0].getTypeName().equals("java.lang.String") && c != null){
                m.invoke(o, c.value());
            }
        }
        return o;
    }
}
