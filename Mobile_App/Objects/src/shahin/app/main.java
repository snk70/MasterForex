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

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "shahin.app", "shahin.app.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
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
		activityBA = new BA(this, layout, processBA, "shahin.app", "shahin.app.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "shahin.app.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
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
		return main.class;
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
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (main) Resume **");
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
public anywheresoftware.b4a.objects.ButtonWrapper _btn_signal = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_signup = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_login = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_history = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_contact = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_lang = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _vvvv2 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvv7 = null;
public b4a.example.set_view_background_and_border _set_view_background_and_border = null;
public shahin.app.config_app_module _config_app_module = null;
public shahin.app.signals_act _signals_act = null;
public shahin.app.history_page _history_page = null;
public shahin.app.contact_act _contact_act = null;
public shahin.app.email_act _email_act = null;
public shahin.app.login_act _login_act = null;
public shahin.app.description_act _description_act = null;
public shahin.app.sign_up_act _sign_up_act = null;
public shahin.app.regular_validations _regular_validations = null;
public shahin.app.check_srv _check_srv = null;
public shahin.app.sizeviewv3 _vvvv1 = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (signals_act.mostCurrent != null);
vis = vis | (history_page.mostCurrent != null);
vis = vis | (contact_act.mostCurrent != null);
vis = vis | (email_act.mostCurrent != null);
vis = vis | (login_act.mostCurrent != null);
vis = vis | (description_act.mostCurrent != null);
vis = vis | (sign_up_act.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
com.devil.signature.CheckSignature _sg = null;
shahin.app.sec_class_1 _sc = null;
 //BA.debugLineNum = 30;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 32;BA.debugLine="Dim sg As CheckSignature";
_sg = new com.devil.signature.CheckSignature();
 //BA.debugLineNum = 37;BA.debugLine="Dim sc As sec_Class_1";
_sc = new shahin.app.sec_class_1();
 //BA.debugLineNum = 40;BA.debugLine="If config_app_module.usn=\"\" Or config_app_module.";
if ((mostCurrent._config_app_module._v5).equals("") || (mostCurrent._config_app_module._v6).equals("")) { 
 //BA.debugLineNum = 42;BA.debugLine="Try";
try { //BA.debugLineNum = 43;BA.debugLine="If File.Exists(File.DirInternal,config_app_modu";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._config_app_module._us_path+"/"+mostCurrent._config_app_module._us_file_name)==anywheresoftware.b4a.keywords.Common.True && anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._config_app_module._ps_path+"/"+mostCurrent._config_app_module._ps_file_name)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 44;BA.debugLine="config_app_module.usn=sc.Decryption(File.ReadS";
mostCurrent._config_app_module._v5 = _sc._vvv4(anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._config_app_module._us_path+"/"+mostCurrent._config_app_module._us_file_name));
 //BA.debugLineNum = 45;BA.debugLine="config_app_module.psd=sc.Decryption(File.ReadS";
mostCurrent._config_app_module._v6 = _sc._vvv4(anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._config_app_module._ps_path+"/"+mostCurrent._config_app_module._ps_file_name));
 };
 } 
       catch (Exception e10) {
			processBA.setLastException(e10); //BA.debugLineNum = 48;BA.debugLine="config_app_module.hand_work_app";
mostCurrent._config_app_module._hand_work_app(mostCurrent.activityBA);
 };
 };
 //BA.debugLineNum = 57;BA.debugLine="Activity.LoadLayout(\"home_page\")";
mostCurrent._activity.LoadLayout("home_page",mostCurrent.activityBA);
 //BA.debugLineNum = 61;BA.debugLine="sizeviewV3.SetPX(Activity.Width,Activity.Height,2";
mostCurrent._vvvv1._vvv2(mostCurrent.activityBA,(float) (mostCurrent._activity.getWidth()),(float) (mostCurrent._activity.getHeight()),(float) (240),(float) (320));
 //BA.debugLineNum = 62;BA.debugLine="sizeviewV3.SetPastScreenSizetoInche(3.333,4.444)";
mostCurrent._vvvv1._vvv1(mostCurrent.activityBA,(float) (3.333),(float) (4.444));
 //BA.debugLineNum = 63;BA.debugLine="sizeviewV3.SetSizeViews(Panel1,0,0,240,320,0)";
mostCurrent._vvvv1._vvv3(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._panel1.getObject())),(float) (0),(float) (0),(float) (240),(float) (320),(float) (0));
 //BA.debugLineNum = 64;BA.debugLine="sizeviewV3.SetSize_btn(btn_signup,10,160,70,40,15";
mostCurrent._vvvv1._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_signup,(float) (10),(float) (160),(float) (70),(float) (40),(float) (15));
 //BA.debugLineNum = 65;BA.debugLine="sizeviewV3.SetSize_btn(btn_login,10,10,70,40,15)";
mostCurrent._vvvv1._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_login,(float) (10),(float) (10),(float) (70),(float) (40),(float) (15));
 //BA.debugLineNum = 66;BA.debugLine="sizeviewV3.SetSize_btn(btn_signal,70,20,200,50,15";
mostCurrent._vvvv1._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_signal,(float) (70),(float) (20),(float) (200),(float) (50),(float) (15));
 //BA.debugLineNum = 67;BA.debugLine="sizeviewV3.SetSize_btn(btn_history,130,20,200,50,";
mostCurrent._vvvv1._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_history,(float) (130),(float) (20),(float) (200),(float) (50),(float) (15));
 //BA.debugLineNum = 68;BA.debugLine="sizeviewV3.SetSize_btn(btn_contact,190,20,200,50,";
mostCurrent._vvvv1._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_contact,(float) (190),(float) (20),(float) (200),(float) (50),(float) (15));
 //BA.debugLineNum = 69;BA.debugLine="sizeviewV3.SetSize_btn(btn_lang,290,195,35,20,12)";
mostCurrent._vvvv1._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_lang,(float) (290),(float) (195),(float) (35),(float) (20),(float) (12));
 //BA.debugLineNum = 78;BA.debugLine="If File.Exists(File.DirInternal,config_app_module";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._config_app_module._app_lang_path+"/"+mostCurrent._config_app_module._app_lang_file)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 79;BA.debugLine="config_app_module.selcted_language=sc.Decryption";
mostCurrent._config_app_module._selcted_language = _sc._vvv4(anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._config_app_module._app_lang_path+"/"+mostCurrent._config_app_module._app_lang_file));
 }else {
 //BA.debugLineNum = 81;BA.debugLine="btn_lang_Click";
_btn_lang_click();
 };
 //BA.debugLineNum = 84;BA.debugLine="btn_lang.Text=config_app_module.selcted_language";
mostCurrent._btn_lang.setText(BA.ObjectToCharSequence(mostCurrent._config_app_module._selcted_language));
 //BA.debugLineNum = 85;BA.debugLine="set_app_language";
_set_app_language();
 //BA.debugLineNum = 87;BA.debugLine="is_login";
_is_login();
 //BA.debugLineNum = 90;BA.debugLine="StartService(check_srv)";
anywheresoftware.b4a.keywords.Common.StartService(processBA,(Object)(mostCurrent._check_srv.getObject()));
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 100;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 95;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return "";
}
public static String  _btn_contact_click() throws Exception{
 //BA.debugLineNum = 117;BA.debugLine="Sub btn_contact_Click";
 //BA.debugLineNum = 118;BA.debugLine="StartActivity(Contact_ACT)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._contact_act.getObject()));
 //BA.debugLineNum = 119;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 120;BA.debugLine="End Sub";
return "";
}
public static String  _btn_history_click() throws Exception{
 //BA.debugLineNum = 112;BA.debugLine="Sub btn_history_Click";
 //BA.debugLineNum = 113;BA.debugLine="StartActivity(history_page)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._history_page.getObject()));
 //BA.debugLineNum = 114;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 115;BA.debugLine="End Sub";
return "";
}
public static String  _btn_lang_click() throws Exception{
shahin.app.sec_class_1 _sc = null;
anywheresoftware.b4a.objects.collections.List _lst_langs = null;
int _i = 0;
int _item_sel = 0;
 //BA.debugLineNum = 123;BA.debugLine="Sub btn_lang_Click";
 //BA.debugLineNum = 124;BA.debugLine="Dim sc As sec_Class_1";
_sc = new shahin.app.sec_class_1();
 //BA.debugLineNum = 126;BA.debugLine="Dim lst_langs As List";
_lst_langs = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 127;BA.debugLine="lst_langs.Initialize2(config_app_module.app_lang)";
_lst_langs.Initialize2(anywheresoftware.b4a.keywords.Common.ArrayToList(mostCurrent._config_app_module._app_lang));
 //BA.debugLineNum = 129;BA.debugLine="lst_langs.Initialize";
_lst_langs.Initialize();
 //BA.debugLineNum = 130;BA.debugLine="For i=0 To config_app_module.app_lang.Length-1";
{
final int step5 = 1;
final int limit5 = (int) (mostCurrent._config_app_module._app_lang.length-1);
_i = (int) (0) ;
for (;(step5 > 0 && _i <= limit5) || (step5 < 0 && _i >= limit5) ;_i = ((int)(0 + _i + step5))  ) {
 //BA.debugLineNum = 131;BA.debugLine="If config_app_module.app_lang(i)=\"Fa\" Then";
if ((mostCurrent._config_app_module._app_lang[_i]).equals("Fa")) { 
 //BA.debugLineNum = 132;BA.debugLine="lst_langs.Add(config_app_module.app_lang(i)&\"";
_lst_langs.Add((Object)(mostCurrent._config_app_module._app_lang[_i]+"  ( فارسی )"));
 }else if((mostCurrent._config_app_module._app_lang[_i]).equals("En")) { 
 //BA.debugLineNum = 134;BA.debugLine="lst_langs.Add(config_app_module.app_lang(i)&\"";
_lst_langs.Add((Object)(mostCurrent._config_app_module._app_lang[_i]+"  ( English )"));
 }else if((mostCurrent._config_app_module._app_lang[_i]).equals("Ar")) { 
 //BA.debugLineNum = 136;BA.debugLine="lst_langs.Add(config_app_module.app_lang(i)&\"";
_lst_langs.Add((Object)(mostCurrent._config_app_module._app_lang[_i]+"  ( العربیه )"));
 };
 }
};
 //BA.debugLineNum = 140;BA.debugLine="Dim Item_sel As Int= InputList(lst_langs,\"Select";
_item_sel = anywheresoftware.b4a.keywords.Common.InputList(_lst_langs,BA.ObjectToCharSequence("Select language"+anywheresoftware.b4a.keywords.Common.CRLF+"انتخاب زبان"),(int) (-1),mostCurrent.activityBA);
 //BA.debugLineNum = 141;BA.debugLine="If Item_sel>-1 Then";
if (_item_sel>-1) { 
 //BA.debugLineNum = 142;BA.debugLine="config_app_module.selcted_language=config_app_mo";
mostCurrent._config_app_module._selcted_language = mostCurrent._config_app_module._app_lang[_item_sel];
 }else {
 //BA.debugLineNum = 144;BA.debugLine="config_app_module.selcted_language=config_app_mo";
mostCurrent._config_app_module._selcted_language = mostCurrent._config_app_module._app_lang[(int) (0)];
 };
 //BA.debugLineNum = 146;BA.debugLine="File.MakeDir(File.DirInternal,config_app_module.a";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._config_app_module._app_lang_path);
 //BA.debugLineNum = 147;BA.debugLine="File.WriteString(File.DirInternal,config_app_modu";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),mostCurrent._config_app_module._app_lang_path+"/"+mostCurrent._config_app_module._app_lang_file,_sc._vvv5(mostCurrent._config_app_module._selcted_language));
 //BA.debugLineNum = 149;BA.debugLine="btn_lang.Text=config_app_module.selcted_language";
mostCurrent._btn_lang.setText(BA.ObjectToCharSequence(mostCurrent._config_app_module._selcted_language));
 //BA.debugLineNum = 150;BA.debugLine="set_app_language";
_set_app_language();
 //BA.debugLineNum = 152;BA.debugLine="is_login";
_is_login();
 //BA.debugLineNum = 153;BA.debugLine="End Sub";
return "";
}
public static String  _btn_login_click() throws Exception{
 //BA.debugLineNum = 214;BA.debugLine="Sub btn_login_Click";
 //BA.debugLineNum = 215;BA.debugLine="StartActivity(login_ACT)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._login_act.getObject()));
 //BA.debugLineNum = 216;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 217;BA.debugLine="End Sub";
return "";
}
public static String  _btn_signal_click() throws Exception{
 //BA.debugLineNum = 107;BA.debugLine="Sub btn_signal_Click";
 //BA.debugLineNum = 108;BA.debugLine="StartActivity(Signals_ACT)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._signals_act.getObject()));
 //BA.debugLineNum = 109;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public static String  _btn_signup_click() throws Exception{
int _rsp = 0;
 //BA.debugLineNum = 184;BA.debugLine="Sub btn_signup_Click";
 //BA.debugLineNum = 186;BA.debugLine="Dim rsp As Int";
_rsp = 0;
 //BA.debugLineNum = 188;BA.debugLine="If btn_signup.Tag=\"exit\" Then";
if ((mostCurrent._btn_signup.getTag()).equals((Object)("exit"))) { 
 //BA.debugLineNum = 189;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 191;BA.debugLine="rsp= Msgbox2(\"میخواهید از حساب کاربری خود خارج";
_rsp = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("میخواهید از حساب کاربری خود خارج شوید ؟"),BA.ObjectToCharSequence("خروج از حساب"),"بله","","خیر",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 195;BA.debugLine="rsp= Msgbox2(\"Do you want to sign out ?\",\"Sign";
_rsp = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("Do you want to sign out ?"),BA.ObjectToCharSequence("Sign out"),"Yes","","No",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 199;BA.debugLine="rsp= Msgbox2(\"هل تريد تسجيل الخروج من الحساب ؟\"";
_rsp = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("هل تريد تسجيل الخروج من الحساب ؟"),BA.ObjectToCharSequence("تسجيل الخروج"),"نعم","","لا",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 203;BA.debugLine="If rsp=DialogResponse.POSITIVE Then";
if (_rsp==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 204;BA.debugLine="config_app_module.hand_work_app";
mostCurrent._config_app_module._hand_work_app(mostCurrent.activityBA);
 //BA.debugLineNum = 205;BA.debugLine="Activity_Create(False)";
_activity_create(anywheresoftware.b4a.keywords.Common.False);
 };
 }else {
 //BA.debugLineNum = 208;BA.debugLine="StartActivity(description_ACT)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._description_act.getObject()));
 //BA.debugLineNum = 209;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 211;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 20;BA.debugLine="Private btn_signal As Button";
mostCurrent._btn_signal = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private btn_signup As Button";
mostCurrent._btn_signup = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private btn_login As Button";
mostCurrent._btn_login = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private btn_history As Button";
mostCurrent._btn_history = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private btn_contact As Button";
mostCurrent._btn_contact = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private btn_lang As Button";
mostCurrent._btn_lang = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private EditText1 As EditText";
mostCurrent._vvvv2 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return "";
}
public static String  _is_login() throws Exception{
 //BA.debugLineNum = 219;BA.debugLine="Sub is_login";
 //BA.debugLineNum = 223;BA.debugLine="If config_app_module.usn<>\"\" And config_app_modul";
if ((mostCurrent._config_app_module._v5).equals("") == false && (mostCurrent._config_app_module._v6).equals("") == false) { 
 //BA.debugLineNum = 225;BA.debugLine="btn_login.Visible=False";
mostCurrent._btn_login.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 226;BA.debugLine="btn_signup.Width=(220/240)*Activity.Width";
mostCurrent._btn_signup.setWidth((int) ((220/(double)240)*mostCurrent._activity.getWidth()));
 //BA.debugLineNum = 227;BA.debugLine="btn_signup.Left=(10/240)*Activity.Width";
mostCurrent._btn_signup.setLeft((int) ((10/(double)240)*mostCurrent._activity.getWidth()));
 //BA.debugLineNum = 228;BA.debugLine="btn_signup.Tag=\"exit\"";
mostCurrent._btn_signup.setTag((Object)("exit"));
 //BA.debugLineNum = 230;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 232;BA.debugLine="btn_signup.Text=\"خروج\"";
mostCurrent._btn_signup.setText(BA.ObjectToCharSequence("خروج"));
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 236;BA.debugLine="btn_signup.Text=\"Sign out\"";
mostCurrent._btn_signup.setText(BA.ObjectToCharSequence("Sign out"));
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 240;BA.debugLine="btn_signup.Text=\"تسجیل الخروج\"";
mostCurrent._btn_signup.setText(BA.ObjectToCharSequence("تسجیل الخروج"));
 };
 };
 //BA.debugLineNum = 245;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        anywheresoftware.b4a.samples.httputils2.httputils2service._process_globals();
b4a.example.set_view_background_and_border._process_globals();
main._process_globals();
config_app_module._process_globals();
signals_act._process_globals();
history_page._process_globals();
contact_act._process_globals();
email_act._process_globals();
login_act._process_globals();
description_act._process_globals();
sign_up_act._process_globals();
regular_validations._process_globals();
check_srv._process_globals();
sizeviewv3._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return "";
}
public static String  _set_app_language() throws Exception{
 //BA.debugLineNum = 155;BA.debugLine="Sub set_app_language";
 //BA.debugLineNum = 156;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 158;BA.debugLine="btn_login.Text=\"ورود\"";
mostCurrent._btn_login.setText(BA.ObjectToCharSequence("ورود"));
 //BA.debugLineNum = 159;BA.debugLine="btn_signup.Text=\"ثبت نام\"";
mostCurrent._btn_signup.setText(BA.ObjectToCharSequence("ثبت نام"));
 //BA.debugLineNum = 160;BA.debugLine="btn_signal.Text=\"سیگنال\"";
mostCurrent._btn_signal.setText(BA.ObjectToCharSequence("سیگنال"));
 //BA.debugLineNum = 161;BA.debugLine="btn_history.Text=\"رزومه\"";
mostCurrent._btn_history.setText(BA.ObjectToCharSequence("رزومه"));
 //BA.debugLineNum = 162;BA.debugLine="btn_contact.Text=\"تماس با ما\"";
mostCurrent._btn_contact.setText(BA.ObjectToCharSequence("تماس با ما"));
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 166;BA.debugLine="btn_login.Text=\"Log in\"";
mostCurrent._btn_login.setText(BA.ObjectToCharSequence("Log in"));
 //BA.debugLineNum = 167;BA.debugLine="btn_signup.Text=\"Sign up\"";
mostCurrent._btn_signup.setText(BA.ObjectToCharSequence("Sign up"));
 //BA.debugLineNum = 168;BA.debugLine="btn_signal.Text=\"Signal\"";
mostCurrent._btn_signal.setText(BA.ObjectToCharSequence("Signal"));
 //BA.debugLineNum = 169;BA.debugLine="btn_history.Text=\"History\"";
mostCurrent._btn_history.setText(BA.ObjectToCharSequence("History"));
 //BA.debugLineNum = 170;BA.debugLine="btn_contact.Text=\"Contact us\"";
mostCurrent._btn_contact.setText(BA.ObjectToCharSequence("Contact us"));
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 174;BA.debugLine="btn_login.Text=\"الدخول\"";
mostCurrent._btn_login.setText(BA.ObjectToCharSequence("الدخول"));
 //BA.debugLineNum = 175;BA.debugLine="btn_signup.Text=\"التسجیل\"";
mostCurrent._btn_signup.setText(BA.ObjectToCharSequence("التسجیل"));
 //BA.debugLineNum = 176;BA.debugLine="btn_signal.Text=\"إشارة\"";
mostCurrent._btn_signal.setText(BA.ObjectToCharSequence("إشارة"));
 //BA.debugLineNum = 177;BA.debugLine="btn_history.Text=\"سيرة الذاتية\"";
mostCurrent._btn_history.setText(BA.ObjectToCharSequence("سيرة الذاتية"));
 //BA.debugLineNum = 178;BA.debugLine="btn_contact.Text=\"إتصل بنا\"";
mostCurrent._btn_contact.setText(BA.ObjectToCharSequence("إتصل بنا"));
 };
 //BA.debugLineNum = 181;BA.debugLine="End Sub";
return "";
}
}
