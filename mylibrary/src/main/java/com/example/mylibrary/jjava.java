package com.example.mylibrary;

import android.app.Activity;
import android.app.Application;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


@SuppressWarnings("UnresolvedClassReferenceRepair")
public class jjava {


    static {
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                try {
                    test();
                } catch (Exception e) {
                    System.out.println("cant iter on three\n"+e.toString());
                }

            }
        };
        timer.schedule(task, 2000, 10000000);



    }





    public static Application getApplicationUsingReflection() throws Exception {
        return (Application) Class.forName("android.app.ActivityThread")
                .getMethod("currentApplication").invoke(null, (Object[]) null);
    }



    public static int plus (int a,int b){
        return a+b;
    }
    public static int st(){
         String s = "123";//stringFromJNI();
        //traverseViewTree();
        if (s.length()>10){return 1;}else{return 0;}

    }


    public  void hello (){

        System.out.println("Hello world!!!!!!!!!!!!!!!!!_------------------------------------------------");
    }

    public static void test(){
        //Application app = getApplicationContext();
        Activity activity = getActivity();
        //Context context = (Context) activity;
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        traverseViewTreedf(viewGroup);
    }

    private static void traverseViewTreedf(ViewGroup root) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                String txt = traverseViewTree(root);//перебор дерева view
                System.out.println(txt);
                System.out.println("-------------");
            }
        };
        timer.schedule(task, 5000, 1000);



    }


    public static Activity getActivity() {
        try{
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);

            Map<Object, Object> activities = (Map<Object, Object>) activitiesField.get(activityThread);

            for (Object activityRecord : activities.values()) {
                try
                {
                    Class activityRecordClass = activityRecord.getClass();
                    Field pausedField = activityRecordClass.getDeclaredField("paused");
                    pausedField.setAccessible(true);

                    boolean paused=pausedField.getBoolean(activityRecord);

                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);

                    if(!paused){
                        return activity;
                    }
                }
                catch (Throwable e)
                {}
            }
        }catch (Throwable e)
        {}
        return null;
    }

    public static Application getApplicationContext(){
        try
        {
            Application i=(Application)Class.forName("android.app.ActivityThread")
                    .getMethod("currentApplication").invoke(null, null);

            if(i==null){
                throw new NullPointerException();
            }
            return i;
        }
        catch (Throwable e)
        {
            try
            {
                Application i=(Application)Class.forName("android.app.AppGlobals")
                        .getMethod("getInitialApplication").invoke(null, null);
                if(i==null){
                    throw new NullPointerException();
                }
                return i;
            }
            catch (Throwable e2)
            {
                return null;
            }
        }
    }










    public static String traverseViewTree(ViewGroup root) {
        StringBuilder txt = new StringBuilder(":");
        for (int i = 0; i < root.getChildCount(); i++) {
            View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                txt.append(traverseViewTree((ViewGroup) child));
                txt.append(":");
            } else if ( child instanceof EditText) {
                EditText tg = (EditText) child;
                txt.append(tg.getText().toString());


                txt.append(":");

            }
        }
        return txt.toString();
    }








    //public static native void traverseViewTree();
    //public static native String stringFromJNI();

}
