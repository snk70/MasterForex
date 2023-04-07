package shahin.app;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class sign_up_act extends Activity implements B4AActivity{
	public static sign_up_act mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "shahin.app", "shahin.app.sign_up_act");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (sign_up_act).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, true))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "shahin.app", "shahin.app.sign_up_act");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "shahin.app.sign_up_act", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (sign_up_act) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (sign_up_act) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return sign_up_act.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (sign_up_act) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (sign_up_act) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.samples.httputils2.httpjob _ht_sp = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edt_email = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edt_pass = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edt_retype_pass = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edt_webmony = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edt_phone_number = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_accept = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvv7 = null;
public b4a.example.set_view_background_and_border _set_view_background_and_border = null;
public shahin.app.main _vvv0 = null;
public shahin.app.config_app_module _config_app_module = null;
public shahin.app.signals_act _signals_act = null;
public shahin.app.history_page _history_page = null;
public shahin.app.contact_act _contact_act = null;
public shahin.app.email_act _email_act = null;
public shahin.app.login_act _login_act = null;
public shahin.app.description_act _description_act = null;
public shahin.app.regular_validations _regular_validations = null;
public shahin.app.check_srv _check_srv = null;
public shahin.app.sizeviewv3 _vvvv1 = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 22;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 23;BA.debugLine="Activity.LoadLayout(\"sign_up_page\")";
mostCurrent._activity.LoadLayout("sign_up_page",mostCurrent.activityBA);
 //BA.debugLineNum = 27;BA.debugLine="sizeviewV3.SetPX(Activity.Width,Activity.Height,2";
mostCurrent._vvvv1._vvv2(mostCurrent.activityBA,(float) (mostCurrent._activity.getWidth()),(float) (mostCurrent._activity.getHeight()),(float) (240),(float) (320));
 //BA.debugLineNum = 28;BA.debugLine="sizeviewV3.SetPastScreenSizetoInche(3.333,4.444)";
mostCurrent._vvvv1._vvv1(mostCurrent.activityBA,(float) (3.333),(float) (4.444));
 //BA.debugLineNum = 29;BA.debugLine="sizeviewV3.SetSize_txt(edt_email,10,10,220,40,15)";
mostCurrent._vvvv1._setsize_txt(mostCurrent.activityBA,mostCurrent._edt_email,(float) (10),(float) (10),(float) (220),(float) (40),(float) (15));
 //BA.debugLineNum = 30;BA.debugLine="sizeviewV3.SetSize_txt(edt_pass,60,10,220,40,15)";
mostCurrent._vvvv1._setsize_txt(mostCurrent.activityBA,mostCurrent._edt_pass,(float) (60),(float) (10),(float) (220),(float) (40),(float) (15));
 //BA.debugLineNum = 31;BA.debugLine="sizeviewV3.SetSize_txt(edt_retype_pass,110,10,220";
mostCurrent._vvvv1._setsize_txt(mostCurrent.activityBA,mostCurrent._edt_retype_pass,(float) (110),(float) (10),(float) (220),(float) (40),(float) (15));
 //BA.debugLineNum = 32;BA.debugLine="sizeviewV3.SetSize_txt(edt_webmony,160,10,220,40,";
mostCurrent._vvvv1._setsize_txt(mostCurrent.activityBA,mostCurrent._edt_webmony,(float) (160),(float) (10),(float) (220),(float) (40),(float) (15));
 //BA.debugLineNum = 33;BA.debugLine="sizeviewV3.SetSize_txt(edt_Phone_Number,210,10,22";
mostCurrent._vvvv1._setsize_txt(mostCurrent.activityBA,mostCurrent._edt_phone_number,(float) (210),(float) (10),(float) (220),(float) (40),(float) (15));
 //BA.debugLineNum = 34;BA.debugLine="sizeviewV3.SetSize_btn(btn_accept,260,10,90,40,15";
mostCurrent._vvvv1._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_accept,(float) (260),(float) (10),(float) (90),(float) (40),(float) (15));
 //BA.debugLineNum = 36;BA.debugLine="edt_email.InputType=524288";
mostCurrent._edt_email.setInputType((int) (524288));
 //BA.debugLineNum = 40;BA.debugLine="Set_View_BackGround_and_Border.Set_Initialize(480";
mostCurrent._set_view_background_and_border._set_initialize(mostCurrent.activityBA,(int) (480),(int) (640),mostCurrent._activity.getWidth(),mostCurrent._activity.getHeight());
 //BA.debugLineNum = 41;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_BG";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._edt_email.getObject())),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1.5),(int) (5));
 //BA.debugLineNum = 42;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_BG";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._edt_pass.getObject())),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1.5),(int) (5));
 //BA.debugLineNum = 43;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_BG";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._edt_phone_number.getObject())),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1),(int) (5));
 //BA.debugLineNum = 44;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_BG";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._edt_retype_pass.getObject())),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1),(int) (5));
 //BA.debugLineNum = 45;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_BG";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._edt_webmony.getObject())),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1.5),(int) (5));
 //BA.debugLineNum = 46;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_BG";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._btn_accept.getObject())),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1.5),(int) (5));
 //BA.debugLineNum = 50;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 52;BA.debugLine="edt_email.Gravity=21";
mostCurrent._edt_email.setGravity((int) (21));
 //BA.debugLineNum = 53;BA.debugLine="edt_pass.Gravity=21";
mostCurrent._edt_pass.setGravity((int) (21));
 //BA.debugLineNum = 54;BA.debugLine="edt_retype_pass.Gravity=21";
mostCurrent._edt_retype_pass.setGravity((int) (21));
 //BA.debugLineNum = 55;BA.debugLine="edt_webmony.Gravity=21";
mostCurrent._edt_webmony.setGravity((int) (21));
 //BA.debugLineNum = 56;BA.debugLine="edt_Phone_Number.Gravity=21";
mostCurrent._edt_phone_number.setGravity((int) (21));
 //BA.debugLineNum = 58;BA.debugLine="Activity.Title=\"ثبت نام\"";
mostCurrent._activity.setTitle(BA.ObjectToCharSequence("ثبت نام"));
 //BA.debugLineNum = 59;BA.debugLine="edt_email.Hint=\"نشانی ایمیل\"";
mostCurrent._edt_email.setHint("نشانی ایمیل");
 //BA.debugLineNum = 60;BA.debugLine="edt_pass.Hint=\"کلمه عبور\"";
mostCurrent._edt_pass.setHint("کلمه عبور");
 //BA.debugLineNum = 61;BA.debugLine="edt_retype_pass.Hint=\"تکرار کلمه عبور\"";
mostCurrent._edt_retype_pass.setHint("تکرار کلمه عبور");
 //BA.debugLineNum = 62;BA.debugLine="edt_webmony.Hint=\"شناسه وب مانی\"";
mostCurrent._edt_webmony.setHint("شناسه وب مانی");
 //BA.debugLineNum = 63;BA.debugLine="edt_Phone_Number.Hint=\"تلفن همراه\"";
mostCurrent._edt_phone_number.setHint("تلفن همراه");
 //BA.debugLineNum = 64;BA.debugLine="btn_accept.Text=\"تأیید\"";
mostCurrent._btn_accept.setText(BA.ObjectToCharSequence("تأیید"));
 //BA.debugLineNum = 66;BA.debugLine="btn_accept.Left=Activity.Width-btn_accept.Width-";
mostCurrent._btn_accept.setLeft((int) (mostCurrent._activity.getWidth()-mostCurrent._btn_accept.getWidth()-mostCurrent._btn_accept.getLeft()));
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 75;BA.debugLine="edt_email.Gravity=21";
mostCurrent._edt_email.setGravity((int) (21));
 //BA.debugLineNum = 76;BA.debugLine="edt_pass.Gravity=21";
mostCurrent._edt_pass.setGravity((int) (21));
 //BA.debugLineNum = 77;BA.debugLine="edt_retype_pass.Gravity=21";
mostCurrent._edt_retype_pass.setGravity((int) (21));
 //BA.debugLineNum = 78;BA.debugLine="edt_webmony.Gravity=21";
mostCurrent._edt_webmony.setGravity((int) (21));
 //BA.debugLineNum = 79;BA.debugLine="edt_Phone_Number.Gravity=21";
mostCurrent._edt_phone_number.setGravity((int) (21));
 //BA.debugLineNum = 81;BA.debugLine="Activity.Title=\"سجل\"";
mostCurrent._activity.setTitle(BA.ObjectToCharSequence("سجل"));
 //BA.debugLineNum = 82;BA.debugLine="edt_email.Hint=\"عنوان البريد الإلكتروني\"";
mostCurrent._edt_email.setHint("عنوان البريد الإلكتروني");
 //BA.debugLineNum = 83;BA.debugLine="edt_pass.Hint=\"كلمه السر\"";
mostCurrent._edt_pass.setHint("كلمه السر");
 //BA.debugLineNum = 84;BA.debugLine="edt_retype_pass.Hint=\"أعد إدخال كلمة السر\"";
mostCurrent._edt_retype_pass.setHint("أعد إدخال كلمة السر");
 //BA.debugLineNum = 85;BA.debugLine="edt_webmony.Hint=\"رقم الوب مانی\"";
mostCurrent._edt_webmony.setHint("رقم الوب مانی");
 //BA.debugLineNum = 86;BA.debugLine="edt_Phone_Number.Hint=\"رقم الهاتف\"";
mostCurrent._edt_phone_number.setHint("رقم الهاتف");
 //BA.debugLineNum = 87;BA.debugLine="btn_accept.Text=\"التأكيد\"";
mostCurrent._btn_accept.setText(BA.ObjectToCharSequence("التأكيد"));
 //BA.debugLineNum = 89;BA.debugLine="btn_accept.Left=Activity.Width-btn_accept.Width-";
mostCurrent._btn_accept.setLeft((int) (mostCurrent._activity.getWidth()-mostCurrent._btn_accept.getWidth()-mostCurrent._btn_accept.getLeft()));
 };
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 103;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 104;BA.debugLine="If KeyCode=KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 105;BA.debugLine="StartActivity(description_ACT)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._description_act.getObject()));
 //BA.debugLineNum = 106;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 108;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 109;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 99;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 101;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 95;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 97;BA.debugLine="End Sub";
return "";
}
public static String  _btn_accept_click() throws Exception{
shahin.app.sec_class_1 _sc = null;
 //BA.debugLineNum = 113;BA.debugLine="Sub btn_accept_Click";
 //BA.debugLineNum = 114;BA.debugLine="Dim sc As sec_Class_1";
_sc = new shahin.app.sec_class_1();
 //BA.debugLineNum = 116;BA.debugLine="If edt_email.Text=\"\" Or edt_pass.Text=\"\" Or edt_P";
if ((mostCurrent._edt_email.getText()).equals("") || (mostCurrent._edt_pass.getText()).equals("") || (mostCurrent._edt_phone_number.getText()).equals("") || (mostCurrent._edt_retype_pass.getText()).equals("") || (mostCurrent._edt_webmony.getText()).equals("")) { 
 //BA.debugLineNum = 118;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 119;BA.debugLine="Msgbox(\"لطفا موارد مورد نیاز را وارد کنید\",\"تکم";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("لطفا موارد مورد نیاز را وارد کنید"),BA.ObjectToCharSequence("تکمیل موارد"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 121;BA.debugLine="Msgbox(\"Please enter the required items\",\"Enter";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Please enter the required items"),BA.ObjectToCharSequence("Enter items"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 123;BA.debugLine="Msgbox(\"يرجى إدخال العناصر المطلوبة\",\"أدخل العن";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("يرجى إدخال العناصر المطلوبة"),BA.ObjectToCharSequence("أدخل العناصر"),mostCurrent.activityBA);
 };
 }else {
 //BA.debugLineNum = 128;BA.debugLine="If edt_pass.Text=edt_retype_pass.Text Then";
if ((mostCurrent._edt_pass.getText()).equals(mostCurrent._edt_retype_pass.getText())) { 
 //BA.debugLineNum = 131;BA.debugLine="If Regular_Validations.Email_validation(edt_ema";
if (mostCurrent._regular_validations._email_validation(mostCurrent.activityBA,mostCurrent._edt_email.getText())) { 
 //BA.debugLineNum = 134;BA.debugLine="If config_app_module.selcted_language=\"Fa\" The";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 136;BA.debugLine="ProgressDialogShow2(\"لطفا کمی صبر کنید ...\",F";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,BA.ObjectToCharSequence("لطفا کمی صبر کنید ..."),anywheresoftware.b4a.keywords.Common.False);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 140;BA.debugLine="ProgressDialogShow2(\"Please wait ...\",False)";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,BA.ObjectToCharSequence("Please wait ..."),anywheresoftware.b4a.keywords.Common.False);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 144;BA.debugLine="ProgressDialogShow2(\"أرجو الإنتظار ...\",False";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,BA.ObjectToCharSequence("أرجو الإنتظار ..."),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 149;BA.debugLine="ht_sp.Initialize(\"sgnp\",Me)";
mostCurrent._ht_sp._initialize(processBA,"sgnp",sign_up_act.getObject());
 //BA.debugLineNum = 151;BA.debugLine="ht_sp.PostString(config_app_module.sgn_URL,\"ma";
mostCurrent._ht_sp._poststring(mostCurrent._config_app_module._sgn_url,"ma="+_sc._vvv5(mostCurrent._edt_email.getText())+"&ps="+_sc._vvv5(mostCurrent._edt_pass.getText())+"&lcs="+_sc._vvv5(mostCurrent._edt_webmony.getText())+"&phn="+_sc._vvv5(mostCurrent._edt_phone_number.getText())+"&potgw52894fo_dkgnndfg_gtkrtkjtrldrt=dtpognerregjwerg&sina_rigergbwlergwer=wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe&ya_heidar=sdgsergwer_313_asdfkakefkqwelfqwef34875234589");
 }else {
 //BA.debugLineNum = 154;BA.debugLine="If config_app_module.selcted_language=\"Fa\" The";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 155;BA.debugLine="Msgbox(\"لطفا ایمل خود را به شکل صحیح وارد نما";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("لطفا ایمل خود را به شکل صحیح وارد نمایید ."),BA.ObjectToCharSequence("ایمیل اشتباه"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 157;BA.debugLine="Msgbox(\"Please enter your correct email\",\"Ent";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Please enter your correct email"),BA.ObjectToCharSequence("Enter Correct email"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 159;BA.debugLine="Msgbox(\"يرجى إدخال البريد الإلكتروني الصحيح\",";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("يرجى إدخال البريد الإلكتروني الصحيح"),BA.ObjectToCharSequence("أدخل البريد الإلكتروني الصحيح"),mostCurrent.activityBA);
 };
 };
 }else {
 //BA.debugLineNum = 164;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._edt_pass.getObject())),anywheresoftware.b4a.keywords.Common.Colors.Red,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1),(int) (5));
 //BA.debugLineNum = 165;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._edt_retype_pass.getObject())),anywheresoftware.b4a.keywords.Common.Colors.Red,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1),(int) (5));
 };
 };
 //BA.debugLineNum = 170;BA.debugLine="End Sub";
return "";
}
public static String  _edt_pass_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 172;BA.debugLine="Sub edt_pass_TextChanged (Old As String, New As St";
 //BA.debugLineNum = 173;BA.debugLine="If edt_pass.Text=edt_retype_pass.Text Then";
if ((mostCurrent._edt_pass.getText()).equals(mostCurrent._edt_retype_pass.getText())) { 
 //BA.debugLineNum = 174;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_B";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._edt_pass.getObject())),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1),(int) (5));
 //BA.debugLineNum = 175;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_B";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._edt_retype_pass.getObject())),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1),(int) (5));
 }else if(((mostCurrent._edt_pass.getText()).equals("") == false && (mostCurrent._edt_retype_pass.getText()).equals("") == false) && (mostCurrent._edt_pass.getText()).equals(mostCurrent._edt_retype_pass.getText()) == false) { 
 //BA.debugLineNum = 177;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_B";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._edt_pass.getObject())),anywheresoftware.b4a.keywords.Common.Colors.Red,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1),(int) (5));
 //BA.debugLineNum = 178;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_B";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._edt_retype_pass.getObject())),anywheresoftware.b4a.keywords.Common.Colors.Red,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1),(int) (5));
 };
 //BA.debugLineNum = 180;BA.debugLine="End Sub";
return "";
}
public static String  _edt_retype_pass_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 182;BA.debugLine="Sub edt_retype_pass_TextChanged (Old As String, Ne";
 //BA.debugLineNum = 183;BA.debugLine="If edt_pass.Text=edt_retype_pass.Text Then";
if ((mostCurrent._edt_pass.getText()).equals(mostCurrent._edt_retype_pass.getText())) { 
 //BA.debugLineNum = 184;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_B";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._edt_pass.getObject())),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1),(int) (5));
 //BA.debugLineNum = 185;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_B";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._edt_retype_pass.getObject())),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1),(int) (5));
 }else if(((mostCurrent._edt_pass.getText()).equals("") == false && (mostCurrent._edt_retype_pass.getText()).equals("") == false) && (mostCurrent._edt_pass.getText()).equals(mostCurrent._edt_retype_pass.getText()) == false) { 
 //BA.debugLineNum = 187;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_B";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._edt_pass.getObject())),anywheresoftware.b4a.keywords.Common.Colors.Red,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1),(int) (5));
 //BA.debugLineNum = 188;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_B";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._edt_retype_pass.getObject())),anywheresoftware.b4a.keywords.Common.Colors.Red,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1),(int) (5));
 };
 //BA.debugLineNum = 190;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 13;BA.debugLine="Dim ht_sp As HttpJob";
mostCurrent._ht_sp = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 14;BA.debugLine="Private edt_email As EditText";
mostCurrent._edt_email = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private edt_pass As EditText";
mostCurrent._edt_pass = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private edt_retype_pass As EditText";
mostCurrent._edt_retype_pass = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private edt_webmony As EditText";
mostCurrent._edt_webmony = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private edt_Phone_Number As EditText";
mostCurrent._edt_phone_number = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private btn_accept As Button";
mostCurrent._btn_accept = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
shahin.app.sec_class_1 _sc = null;
 //BA.debugLineNum = 193;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 194;BA.debugLine="Dim sc As sec_Class_1";
_sc = new shahin.app.sec_class_1();
 //BA.debugLineNum = 196;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 202;BA.debugLine="If Job.Success=True Then";
if (_job._success==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 203;BA.debugLine="If Job.JobName=\"sgnp\" Then";
if ((_job._jobname).equals("sgnp")) { 
 //BA.debugLineNum = 204;BA.debugLine="If Job.GetString=sc.Encryption(\"repeat_mail\") T";
if ((_job._getstring()).equals(_sc._vvv5("repeat_mail"))) { 
 //BA.debugLineNum = 205;BA.debugLine="If config_app_module.selcted_language=\"Fa\" The";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 207;BA.debugLine="Msgbox(\"ایمیل وارد شده تکراری میباشد\",\"ایمیل";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("ایمیل وارد شده تکراری میباشد"),BA.ObjectToCharSequence("ایمیل تکراری"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 211;BA.debugLine="Msgbox(\"Email is duplicate\",\"Duplicate\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Email is duplicate"),BA.ObjectToCharSequence("Duplicate"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 215;BA.debugLine="Msgbox(\"البريد الإلكتروني مكرر\",\"مكرر\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("البريد الإلكتروني مكرر"),BA.ObjectToCharSequence("مكرر"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 218;BA.debugLine="edt_email.Text=\"\"";
mostCurrent._edt_email.setText(BA.ObjectToCharSequence(""));
 }else if((_job._getstring()).equals(_sc._vvv5("ok_send_mail"))) { 
 //BA.debugLineNum = 221;BA.debugLine="If config_app_module.selcted_language=\"Fa\" The";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 223;BA.debugLine="Msgbox(\"ثبت نام شما با موفقیت انجام شد . جهت";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("ثبت نام شما با موفقیت انجام شد . جهت تأیید ایمیل خود بروی روی لینک ارسال شده به ایمیل کلیک کنید ."),BA.ObjectToCharSequence("تأیید"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 227;BA.debugLine="Msgbox(\"Your registration has been successful";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Your registration has been successfully completed. To confirm your email, click on the link sent to the email."),BA.ObjectToCharSequence("Successful"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 231;BA.debugLine="Msgbox(\"تم الانتهاء من تسجيلك بنجاح. لتأكيد ب";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("تم الانتهاء من تسجيلك بنجاح. لتأكيد بريدك الإلكتروني ، انقر فوق الرابط المرسل إلى البريد الإلكتروني."),BA.ObjectToCharSequence("ناجح"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 235;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._vvv0.getObject()));
 //BA.debugLineNum = 236;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else if((_job._getstring()).equals(_sc._vvv5("error"))) { 
 //BA.debugLineNum = 238;BA.debugLine="If config_app_module.selcted_language=\"Fa\" The";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 240;BA.debugLine="Msgbox(\"خطایی رخ داد ، لطفا بعدا مجددا تلاش ن";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("خطایی رخ داد ، لطفا بعدا مجددا تلاش نمایید"),BA.ObjectToCharSequence("خطا"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 244;BA.debugLine="Msgbox(\"An error occurred. Please try again l";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("An error occurred. Please try again later"),BA.ObjectToCharSequence("Error"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 248;BA.debugLine="Msgbox(\"حدث خطأ. الرجاء معاودة المحاولة في وق";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("حدث خطأ. الرجاء معاودة المحاولة في وقت لاحق"),BA.ObjectToCharSequence("خطأ"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 251;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._vvv0.getObject()));
 //BA.debugLineNum = 252;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else {
 //BA.debugLineNum = 254;BA.debugLine="config_app_module.hand_work_app";
mostCurrent._config_app_module._hand_work_app(mostCurrent.activityBA);
 //BA.debugLineNum = 255;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 };
 }else {
 //BA.debugLineNum = 259;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 261;BA.debugLine="Msgbox(\"خطایی رخ داد ، لطفا بعدا مجددا تلاش نما";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("خطایی رخ داد ، لطفا بعدا مجددا تلاش نمایید"),BA.ObjectToCharSequence("خطا"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 265;BA.debugLine="Msgbox(\"An error occurred. Please try again lat";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("An error occurred. Please try again later"),BA.ObjectToCharSequence("Error"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 269;BA.debugLine="Msgbox(\"حدث خطأ. الرجاء معاودة المحاولة في وقت";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("حدث خطأ. الرجاء معاودة المحاولة في وقت لاحق"),BA.ObjectToCharSequence("خطأ"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 273;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._vvv0.getObject()));
 //BA.debugLineNum = 274;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 278;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
}
