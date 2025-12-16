package de.gfn.coffeesystemsmart.Classes;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageChange {
    private static Locale locale = Locale.GERMAN;

    public static void setLocale(Locale l) {
        locale = l;
    }

    public static ResourceBundle getBundle() {
        return ResourceBundle.getBundle("lang.msg", locale);
    }
}
