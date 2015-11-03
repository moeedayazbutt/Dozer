
package bamcorp.dozer;


import android.content.Context;
import android.content.SharedPreferences.Editor;


public class ManagerPreferences
{
    public final static String NOT_SET = "NOT_SET";
    public final static String DOZER_STATUS = "_DOZER_STATUS_";


    private static void apply(Editor editor)
    {
        editor.commit();
    }


    public static void remove(Context context, String key)
    {
        apply(context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE).edit().remove(key));
    }

    public static void saveBoolean(Context context, String key, boolean value)
    {
        apply(context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE).edit().putBoolean(key, value));
    }

    public static boolean loadBoolean(Context context, String key, boolean defaultValue)
    {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE).getBoolean(key, defaultValue);
    }

    public static void saveString(Context context, String key, String value)
    {
        apply(context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE).edit().putString(key, value));
    }

    public static String loadString(Context context, String key)
    {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE).getString(key, NOT_SET);
    }
}
