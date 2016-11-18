package com.virzt.search.utils;

/**
 * Static settings
 **/
public class SettingUtils {
    public static final boolean PROCEED = true;

    /**
     * Check whether the character sequence is numeric
     *
     * @param cs character sequence
     **/
    public static boolean isNumeric(CharSequence cs) {
        if (cs == null || cs.length() == 0) {
            return false;
        } else {
            int sz = cs.length();
            for (int i = 0; i < sz; ++i) {
                if (!Character.isDigit(cs.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }
}

