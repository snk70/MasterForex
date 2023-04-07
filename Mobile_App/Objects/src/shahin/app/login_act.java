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

public class login_act extends Activity implements B4AActivity{
	public static login_act mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "shahin.app", "shahin.app.login_act");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (login_act).");
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
		activityBA = new BA(this, layout, processBA, "shahin.app", "shahin.app.login_act");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "shahin.app.login_act", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (login_act) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (login_act) Resume **");
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
		return login_act.class;
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
        BA.LogInfo("** Activity (login_act) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (login_act) Resume **");
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
public anywheresoftware.b4a.samples.httputils2.httpjob _ht_lgn = null;
public anywheresoftware.b4a.samples.httputils2.httpjob _ht_frg = null;
public static String _jb_name = "";
public anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _check_remem = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edt_username = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edt_password = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_forget = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_ok = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvv7 = null;
public b4a.example.set_view_background_and_border _set_view_background_and_border = null;
public shahin.app.main _vvv0 = null;
public shahin.app.config_app_module _config_app_module = null;
public shahin.app.signals_act _signals_act = null;
public shahin.app.history_page _history_page = null;
public shahin.app.contact_act _contact_act = null;
public shahin.app.email_act _email_act = null;
public shahin.app.description_act _description_act = null;
public shahin.app.sign_up_act _sign_up_act = null;
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
 //BA.debugLineNum = 26;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 28;BA.debugLine="Activity.LoadLayout(\"login_page\")";
mostCurrent._activity.LoadLayout("login_page",mostCurrent.activityBA);
 //BA.debugLineNum = 31;BA.debugLine="sizeviewV3.SetPX(Activity.Width,Activity.Height,2";
mostCurrent._vvvv1._vvv2(mostCurrent.activityBA,(float) (mostCurrent._activity.getWidth()),(float) (mostCurrent._activity.getHeight()),(float) (240),(float) (320));
 //BA.debugLineNum = 32;BA.debugLine="sizeviewV3.SetPastScreenSizetoInche(3.333,4.444)";
mostCurrent._vvvv1._vvv1(mostCurrent.activityBA,(float) (3.333),(float) (4.444));
 //BA.debugLineNum = 33;BA.debugLine="sizeviewV3.SetSizeViews(Panel1,0,0,240,320,0)";
mostCurrent._vvvv1._vvv3(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._panel1.getObject())),(float) (0),(float) (0),(float) (240),(float) (320),(float) (0));
 //BA.debugLineNum = 34;BA.debugLine="sizeviewV3.SetSize_txt(edt_username,6,10,220,50,1";
mostCurrent._vvvv1._setsize_txt(mostCurrent.activityBA,mostCurrent._edt_username,(float) (6),(float) (10),(float) (220),(float) (50),(float) (15));
 //BA.debugLineNum = 35;BA.debugLine="sizeviewV3.SetSize_txt(edt_Password,76,10,220,50,";
mostCurrent._vvvv1._setsize_txt(mostCurrent.activityBA,mostCurrent._edt_password,(float) (76),(float) (10),(float) (220),(float) (50),(float) (15));
 //BA.debugLineNum = 36;BA.debugLine="sizeviewV3.SetSize_Checkbox(check_remem,141,10,22";
mostCurrent._vvvv1._setsize_checkbox(mostCurrent.activityBA,mostCurrent._check_remem,(float) (141),(float) (10),(float) (220),(float) (30),(float) (15));
 //BA.debugLineNum = 37;BA.debugLine="sizeviewV3.SetSize_lbl_Views(lbl_forget,255,10,22";
mostCurrent._vvvv1._setsize_lbl_views(mostCurrent.activityBA,mostCurrent._lbl_forget,(float) (255),(float) (10),(float) (220),(float) (40),(float) (15));
 //BA.debugLineNum = 38;BA.debugLine="sizeviewV3.SetSize_btn(btn_ok,195,95,50,40,20)";
mostCurrent._vvvv1._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_ok,(float) (195),(float) (95),(float) (50),(float) (40),(float) (20));
 //BA.debugLineNum = 42;BA.debugLine="edt_username.InputType=524288";
mostCurrent._edt_username.setInputType((int) (524288));
 //BA.debugLineNum = 43;BA.debugLine="check_remem.Checked=True";
mostCurrent._check_remem.setChecked(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 45;BA.debugLine="Set_View_BackGround_and_Border.Set_Initialize(480";
mostCurrent._set_view_background_and_border._set_initialize(mostCurrent.activityBA,(int) (480),(int) (640),mostCurrent._activity.getWidth(),mostCurrent._activity.getHeight());
 //BA.debugLineNum = 46;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_BG";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._edt_username.getObject())),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1.5),(int) (5));
 //BA.debugLineNum = 47;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_BG";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._edt_password.getObject())),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1.5),(int) (5));
 //BA.debugLineNum = 48;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_BG";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._btn_ok.getObject())),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (1.5),(int) (5));
 //BA.debugLineNum = 49;BA.debugLine="Set_View_BackGround_and_Border.Set_View_Border_BG";
mostCurrent._set_view_background_and_border._set_view_border_bg(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._check_remem.getObject())),anywheresoftware.b4a.keywords.Common.Colors.Gray,anywheresoftware.b4a.keywords.Common.Colors.Black,(int) (0),(int) (5));
 //BA.debugLineNum = 52;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 54;BA.debugLine="edt_username.Gravity=21";
mostCurrent._edt_username.setGravity((int) (21));
 //BA.debugLineNum = 55;BA.debugLine="edt_Password.Gravity=21";
mostCurrent._edt_password.setGravity((int) (21));
 //BA.debugLineNum = 56;BA.debugLine="check_remem.Gravity=21";
mostCurrent._check_remem.setGravity((int) (21));
 //BA.debugLineNum = 58;BA.debugLine="edt_Password.Hint=\"رمز عبور\"";
mostCurrent._edt_password.setHint("رمز عبور");
 //BA.debugLineNum = 59;BA.debugLine="edt_username.Hint=\"ایمیل یا نام کاربری\"";
mostCurrent._edt_username.setHint("ایمیل یا نام کاربری");
 //BA.debugLineNum = 60;BA.debugLine="check_remem.Text=\"به خاطر سپردن\"";
mostCurrent._check_remem.setText(BA.ObjectToCharSequence("به خاطر سپردن"));
 //BA.debugLineNum = 61;BA.debugLine="btn_ok.Text=\"تأیید\"";
mostCurrent._btn_ok.setText(BA.ObjectToCharSequence("تأیید"));
 //BA.debugLineNum = 62;BA.debugLine="lbl_forget.Text=\"رمز خود را فراموش کرده ایده ؟\"";
mostCurrent._lbl_forget.setText(BA.ObjectToCharSequence("رمز خود را فراموش کرده ایده ؟"));
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 70;BA.debugLine="edt_username.Gravity=21";
mostCurrent._edt_username.setGravity((int) (21));
 //BA.debugLineNum = 71;BA.debugLine="edt_Password.Gravity=21";
mostCurrent._edt_password.setGravity((int) (21));
 //BA.debugLineNum = 72;BA.debugLine="check_remem.Gravity=21";
mostCurrent._check_remem.setGravity((int) (21));
 //BA.debugLineNum = 74;BA.debugLine="edt_Password.Hint=\"كلمه السر\"";
mostCurrent._edt_password.setHint("كلمه السر");
 //BA.debugLineNum = 75;BA.debugLine="edt_username.Hint=\"اسم المستخدم\"";
mostCurrent._edt_username.setHint("اسم المستخدم");
 //BA.debugLineNum = 76;BA.debugLine="check_remem.Text=\"تذكر كلمة المرور\"";
mostCurrent._check_remem.setText(BA.ObjectToCharSequence("تذكر كلمة المرور"));
 //BA.debugLineNum = 77;BA.debugLine="btn_ok.Text=\"أدخل\"";
mostCurrent._btn_ok.setText(BA.ObjectToCharSequence("أدخل"));
 //BA.debugLineNum = 78;BA.debugLine="lbl_forget.Text=\"نسيت كلمة المرور ؟\"";
mostCurrent._lbl_forget.setText(BA.ObjectToCharSequence("نسيت كلمة المرور ؟"));
 };
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 94;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 95;BA.debugLine="If KeyCode=KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 96;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._vvv0.getObject()));
 //BA.debugLineNum = 97;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 99;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 100;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 89;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 91;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 85;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 87;BA.debugLine="End Sub";
return "";
}
public static String  _btn_ok_click() throws Exception{
shahin.app.sec_class_1 _sc = null;
 //BA.debugLineNum = 103;BA.debugLine="Sub btn_ok_Click";
 //BA.debugLineNum = 105;BA.debugLine="Dim sc As sec_Class_1";
_sc = new shahin.app.sec_class_1();
 //BA.debugLineNum = 107;BA.debugLine="If edt_Password.Text=\"\" Or edt_username.Text=\"\" T";
if ((mostCurrent._edt_password.getText()).equals("") || (mostCurrent._edt_username.getText()).equals("")) { 
 //BA.debugLineNum = 108;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 109;BA.debugLine="Msgbox(\"لطفا نام کاربری و کلمه عبور خود را وارد";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("لطفا نام کاربری و کلمه عبور خود را وارد نمایید"),BA.ObjectToCharSequence("ورود ایمیل و کلمه عبور"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 111;BA.debugLine="Msgbox(\"Please input username and password\",\"In";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Please input username and password"),BA.ObjectToCharSequence("Input email and password"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 113;BA.debugLine="Msgbox(\"يرجى إدخال اسم المستخدم وكلمة المرور\",\"";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("يرجى إدخال اسم المستخدم وكلمة المرور"),BA.ObjectToCharSequence("إدخال البريد الإلكتروني وكلمة المرور"),mostCurrent.activityBA);
 };
 }else if(mostCurrent._regular_validations._email_validation(mostCurrent.activityBA,mostCurrent._edt_username.getText())==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 117;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 118;BA.debugLine="Msgbox(\"لطفا آدرس ایمیل صحیح را وارد کنید\",\"آدر";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("لطفا آدرس ایمیل صحیح را وارد کنید"),BA.ObjectToCharSequence("آدرس ایمیل اشتباه !"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 120;BA.debugLine="Msgbox(\"Please enter the correct email address\"";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Please enter the correct email address"),BA.ObjectToCharSequence("Wrong E-mail address !"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 122;BA.debugLine="Msgbox(\"يرجى إدخال عنوان البريد الإلكتروني الصح";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("يرجى إدخال عنوان البريد الإلكتروني الصحيح"),BA.ObjectToCharSequence("عنوان البريد الإلكتروني الخاطئ!"),mostCurrent.activityBA);
 };
 }else {
 //BA.debugLineNum = 125;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 127;BA.debugLine="ProgressDialogShow2(\"لطفا کمی صبر کنید ...\",Fal";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,BA.ObjectToCharSequence("لطفا کمی صبر کنید ..."),anywheresoftware.b4a.keywords.Common.False);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 131;BA.debugLine="ProgressDialogShow2(\"Please wait ...\",False)";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,BA.ObjectToCharSequence("Please wait ..."),anywheresoftware.b4a.keywords.Common.False);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 135;BA.debugLine="ProgressDialogShow2(\"أرجو الإنتظار ...\",False)";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,BA.ObjectToCharSequence("أرجو الإنتظار ..."),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 139;BA.debugLine="ht_lgn.Initialize(\"login\",Me)";
mostCurrent._ht_lgn._initialize(processBA,"login",login_act.getObject());
 //BA.debugLineNum = 141;BA.debugLine="ht_lgn.PostString(config_app_module.info_URL,\"us";
mostCurrent._ht_lgn._poststring(mostCurrent._config_app_module._info_url,"usn="+_sc._vvv5(mostCurrent._edt_username.getText())+"&psd="+_sc._vvv5(mostCurrent._edt_password.getText())+"&potgw52894fo_dkgnndfg_gtkrtkjtrldrt=dtpognerregjwerg&sina_rigergbwlergwer=wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe&ya_heidar=sdgsergwer_313_asdfkakefkqwelfqwef34875234589");
 };
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 13;BA.debugLine="Dim ht_lgn As HttpJob";
mostCurrent._ht_lgn = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 14;BA.debugLine="Dim ht_frg As HttpJob";
mostCurrent._ht_frg = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 15;BA.debugLine="Dim JB_Name As String";
mostCurrent._jb_name = "";
 //BA.debugLineNum = 17;BA.debugLine="Private check_remem As CheckBox";
mostCurrent._check_remem = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private edt_username As EditText";
mostCurrent._edt_username = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private edt_Password As EditText";
mostCurrent._edt_password = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private lbl_forget As Label";
mostCurrent._lbl_forget = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private btn_ok As Button";
mostCurrent._btn_ok = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
shahin.app.sec_class_1 _sc = null;
 //BA.debugLineNum = 210;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 212;BA.debugLine="Dim sc As sec_Class_1";
_sc = new shahin.app.sec_class_1();
 //BA.debugLineNum = 213;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 216;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 220;BA.debugLine="If Job.JobName=\"login\" Then";
if ((_job._jobname).equals("login")) { 
 //BA.debugLineNum = 222;BA.debugLine="If Job.GetString=sc.Encryption(\"ok\") Then";
if ((_job._getstring()).equals(_sc._vvv5("ok"))) { 
 //BA.debugLineNum = 223;BA.debugLine="If config_app_module.selcted_language=\"Fa\" The";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 225;BA.debugLine="ToastMessageShow(\"شما با موفقیت وارد شدید\",Tr";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("شما با موفقیت وارد شدید"),anywheresoftware.b4a.keywords.Common.True);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 229;BA.debugLine="ToastMessageShow(\"You are logged in\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("You are logged in"),anywheresoftware.b4a.keywords.Common.True);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 233;BA.debugLine="ToastMessageShow(\"لقد سجلت الدخول\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("لقد سجلت الدخول"),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 237;BA.debugLine="config_app_module.usn=edt_username.Text";
mostCurrent._config_app_module._v5 = mostCurrent._edt_username.getText();
 //BA.debugLineNum = 238;BA.debugLine="config_app_module.psd=edt_Password.Text";
mostCurrent._config_app_module._v6 = mostCurrent._edt_password.getText();
 //BA.debugLineNum = 240;BA.debugLine="If check_remem.Checked=True Then";
if (mostCurrent._check_remem.getChecked()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 241;BA.debugLine="File.MakeDir(File.DirInternal,config_app_modu";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._config_app_module._us_path);
 //BA.debugLineNum = 242;BA.debugLine="File.MakeDir(File.DirInternal,config_app_modu";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._config_app_module._ps_path);
 //BA.debugLineNum = 244;BA.debugLine="File.WriteString(File.DirInternal,config_app_";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._config_app_module._us_path+"/"+mostCurrent._config_app_module._us_file_name,_sc._vvv5(mostCurrent._config_app_module._v5));
 //BA.debugLineNum = 245;BA.debugLine="File.WriteString(File.DirInternal,config_app_";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._config_app_module._ps_path+"/"+mostCurrent._config_app_module._ps_file_name,_sc._vvv5(mostCurrent._config_app_module._v6));
 }else {
 //BA.debugLineNum = 247;BA.debugLine="File.Delete(File.DirInternal,config_app_modul";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._config_app_module._us_path+"/"+mostCurrent._config_app_module._us_file_name);
 //BA.debugLineNum = 248;BA.debugLine="File.Delete(File.DirInternal,config_app_modul";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._config_app_module._ps_path+"/"+mostCurrent._config_app_module._ps_file_name);
 };
 //BA.debugLineNum = 251;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._vvv0.getObject()));
 //BA.debugLineNum = 252;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else if((_job._getstring()).equals(_sc._vvv5("no_verify"))) { 
 //BA.debugLineNum = 255;BA.debugLine="If config_app_module.selcted_language=\"Fa\" The";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 257;BA.debugLine="Msgbox(\"شما ایمیل خود را تأیید هویت نکرده اید";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("شما ایمیل خود را تأیید هویت نکرده اید . برای استفاده از این اپلیکیشن ، ابتدا به ایمیل خود رفته و بر روی لینک ارسال شده از سوی ما کلیک کنید تا ایمیل شما تأیید هویت گردد ."),BA.ObjectToCharSequence("ایمیل شما تأیید نشده است"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 261;BA.debugLine="Msgbox(\"You have not authenticated your email";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("You have not authenticated your email. To use this app, first go to your email and click on the link provided by us to verify your email."),BA.ObjectToCharSequence("Your email has not been verified"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 266;BA.debugLine="Msgbox(\"لم تصادق على بريدك الإلكتروني. لاستخد";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("لم تصادق على بريدك الإلكتروني. لاستخدام هذا التطبيق ، انتقل أولاً إلى بريدك الإلكتروني وانقر على الرابط الذي قدمته لنا للتحقق من بريدك الإلكتروني."),BA.ObjectToCharSequence("بريدك الالكتروني لم يتم تأكيده"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 270;BA.debugLine="edt_Password.Text=\"\"";
mostCurrent._edt_password.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 271;BA.debugLine="edt_username.Text=\"\"";
mostCurrent._edt_username.setText(BA.ObjectToCharSequence(""));
 }else if((_job._getstring()).equals(_sc._vvv5("wrong_info"))) { 
 //BA.debugLineNum = 274;BA.debugLine="If config_app_module.selcted_language=\"Fa\" The";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 276;BA.debugLine="Msgbox(\"نام کاربری و یا کلمه عبور شما اشتباه";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("نام کاربری و یا کلمه عبور شما اشتباه است"),BA.ObjectToCharSequence("خطا"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 280;BA.debugLine="Msgbox(\"Your username or password is incorrec";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Your username or password is incorrect"),BA.ObjectToCharSequence("Error"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 285;BA.debugLine="Msgbox(\"اسم المستخدم أو كلمة المرور غير صحيحة";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("اسم المستخدم أو كلمة المرور غير صحيحة"),BA.ObjectToCharSequence("خطأ"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 289;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._vvv0.getObject()));
 //BA.debugLineNum = 290;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 }else if((_job._jobname).equals("forget")) { 
 //BA.debugLineNum = 294;BA.debugLine="If Job.GetString=sc.Encryption(\"no_mail\") Then";
if ((_job._getstring()).equals(_sc._vvv5("no_mail"))) { 
 //BA.debugLineNum = 295;BA.debugLine="If config_app_module.selcted_language=\"Fa\" The";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 297;BA.debugLine="Msgbox(\"شما هنوز ثبت نام نکرده اید\",\"خطا\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("شما هنوز ثبت نام نکرده اید"),BA.ObjectToCharSequence("خطا"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 301;BA.debugLine="Msgbox(\"You have not registered yet\",\"Error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("You have not registered yet"),BA.ObjectToCharSequence("Error"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 306;BA.debugLine="Msgbox(\"لم تسجل بعد\",\"خطأ\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("لم تسجل بعد"),BA.ObjectToCharSequence("خطأ"),mostCurrent.activityBA);
 };
 }else if((_job._getstring()).equals(_sc._vvv5("ok"))) { 
 //BA.debugLineNum = 311;BA.debugLine="If config_app_module.selcted_language=\"Fa\" The";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 314;BA.debugLine="Msgbox(\"گذرواژه به ایمیل شما ارسال گردید ؛ لط";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("گذرواژه به ایمیل شما ارسال گردید ؛ لطفا صندوق اسپم ایمیل خود را چک نمایید ، ممکن است ایمیل ارسالی از طرف ما اشتباها در لیست ایمیل های اسپم رفته باشد ."),BA.ObjectToCharSequence("ارسال رمز عبور به ایمیل"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 319;BA.debugLine="Msgbox(\"The password was sent to your email;";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("The password was sent to your email; please check your email spam box, the mail sent by us may be incorrectly listed in the spam email list."),BA.ObjectToCharSequence("Send password to E-mail"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 325;BA.debugLine="Msgbox(\"تم إرسال كلمة المرور إلى بريدك الإلكت";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("تم إرسال كلمة المرور إلى بريدك الإلكتروني ؛ يرجى التحقق من صندوق البريد الإلكتروني غير المرغوب فيه ، وقد يتم إدراج البريد المرسل من جانبنا بشكل غير صحيح في قائمة البريد الإلكتروني العشوائي."),BA.ObjectToCharSequence("إرسال كلمة المرور إلى البريد الإلكتروني"),mostCurrent.activityBA);
 };
 };
 //BA.debugLineNum = 330;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._vvv0.getObject()));
 //BA.debugLineNum = 331;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 }else {
 //BA.debugLineNum = 335;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 337;BA.debugLine="Msgbox(\"خطایی رخ داد ، لطفا بعدا مجددا تلاش نما";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("خطایی رخ داد ، لطفا بعدا مجددا تلاش نمایید"),BA.ObjectToCharSequence("خطا"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 341;BA.debugLine="Msgbox(\"An error occurred. Please try again lat";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("An error occurred. Please try again later"),BA.ObjectToCharSequence("Error"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 345;BA.debugLine="Msgbox(\"حدث خطأ. الرجاء معاودة المحاولة في وقت";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("حدث خطأ. الرجاء معاودة المحاولة في وقت لاحق"),BA.ObjectToCharSequence("خطأ"),mostCurrent.activityBA);
 };
 };
 //BA.debugLineNum = 350;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._vvv0.getObject()));
 //BA.debugLineNum = 351;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 353;BA.debugLine="End Sub";
return "";
}
public static String  _lbl_forget_click() throws Exception{
shahin.app.sec_class_1 _sc = null;
 //BA.debugLineNum = 149;BA.debugLine="Sub lbl_forget_Click";
 //BA.debugLineNum = 150;BA.debugLine="Dim sc As sec_Class_1";
_sc = new shahin.app.sec_class_1();
 //BA.debugLineNum = 152;BA.debugLine="If edt_username.Text=\"\" Then";
if ((mostCurrent._edt_username.getText()).equals("")) { 
 //BA.debugLineNum = 153;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 155;BA.debugLine="Msgbox(\"لطفا ایمیل خود را وارد نمایید\",\"وارد کر";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("لطفا ایمیل خود را وارد نمایید"),BA.ObjectToCharSequence("وارد کردن ایمیل"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 159;BA.debugLine="Msgbox(\"Please input your email\",\"Inpute email\"";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Please input your email"),BA.ObjectToCharSequence("Inpute email"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 163;BA.debugLine="Msgbox(\"يرجى إدخال البريد الإلكتروني الخاص بك\",";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("يرجى إدخال البريد الإلكتروني الخاص بك"),BA.ObjectToCharSequence("البريد الإلكتروني المدخلات"),mostCurrent.activityBA);
 };
 }else if(mostCurrent._regular_validations._email_validation(mostCurrent.activityBA,mostCurrent._edt_username.getText())==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 167;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 168;BA.debugLine="Msgbox(\"لطفا آدرس ایمیل صحیح را وارد کنید\",\"آدر";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("لطفا آدرس ایمیل صحیح را وارد کنید"),BA.ObjectToCharSequence("آدرس ایمیل اشتباه !"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 170;BA.debugLine="Msgbox(\"Please enter the correct email address\"";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Please enter the correct email address"),BA.ObjectToCharSequence("Wrong E-mail address !"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 172;BA.debugLine="Msgbox(\"يرجى إدخال عنوان البريد الإلكتروني الصح";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("يرجى إدخال عنوان البريد الإلكتروني الصحيح"),BA.ObjectToCharSequence("عنوان البريد الإلكتروني الخاطئ!"),mostCurrent.activityBA);
 };
 }else {
 //BA.debugLineNum = 175;BA.debugLine="ht_frg.Initialize(\"forget\",Me)";
mostCurrent._ht_frg._initialize(processBA,"forget",login_act.getObject());
 //BA.debugLineNum = 176;BA.debugLine="ht_frg.PostString(config_app_module.rst_ps,\"usn=";
mostCurrent._ht_frg._poststring(mostCurrent._config_app_module._rst_ps,"usn="+_sc._vvv5(mostCurrent._edt_username.getText())+"&potgw52894fo_dkgnndfg_gtkrtkjtrldrt=dtpognerregjwerg&sina_rigergbwlergwer=wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe&ya_heidar=sdgsergwer_313_asdfkakefkqwelfqwef34875234589");
 //BA.debugLineNum = 178;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 180;BA.debugLine="ProgressDialogShow2(\"لطفا کمی صبر کنید ...\",Fal";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,BA.ObjectToCharSequence("لطفا کمی صبر کنید ..."),anywheresoftware.b4a.keywords.Common.False);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 184;BA.debugLine="ProgressDialogShow2(\"Please wait ...\",False)";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,BA.ObjectToCharSequence("Please wait ..."),anywheresoftware.b4a.keywords.Common.False);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 188;BA.debugLine="ProgressDialogShow2(\"أرجو الإنتظار ...\",False)";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,BA.ObjectToCharSequence("أرجو الإنتظار ..."),anywheresoftware.b4a.keywords.Common.False);
 };
 };
 //BA.debugLineNum = 207;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
}
