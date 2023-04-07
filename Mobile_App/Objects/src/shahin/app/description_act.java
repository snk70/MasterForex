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

public class description_act extends Activity implements B4AActivity{
	public static description_act mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "shahin.app", "shahin.app.description_act");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (description_act).");
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
		activityBA = new BA(this, layout, processBA, "shahin.app", "shahin.app.description_act");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "shahin.app.description_act", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (description_act) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (description_act) Resume **");
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
		return description_act.class;
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
        BA.LogInfo("** Activity (description_act) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (description_act) Resume **");
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
public anywheresoftware.b4a.objects.WebViewWrapper _wb_description = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_countinue = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_ava = null;
public anywheresoftware.b4a.objects.Timer _lbl_tm = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvv7 = null;
public b4a.example.set_view_background_and_border _set_view_background_and_border = null;
public shahin.app.main _vvv0 = null;
public shahin.app.config_app_module _config_app_module = null;
public shahin.app.signals_act _signals_act = null;
public shahin.app.history_page _history_page = null;
public shahin.app.contact_act _contact_act = null;
public shahin.app.email_act _email_act = null;
public shahin.app.login_act _login_act = null;
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
 //BA.debugLineNum = 19;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 20;BA.debugLine="Activity.LoadLayout(\"description_page\")";
mostCurrent._activity.LoadLayout("description_page",mostCurrent.activityBA);
 //BA.debugLineNum = 24;BA.debugLine="sizeviewV3.SetPX(Activity.Width,Activity.Height,2";
mostCurrent._vvvv1._vvv2(mostCurrent.activityBA,(float) (mostCurrent._activity.getWidth()),(float) (mostCurrent._activity.getHeight()),(float) (240),(float) (320));
 //BA.debugLineNum = 25;BA.debugLine="sizeviewV3.SetPastScreenSizetoInche(3.333,4.444)";
mostCurrent._vvvv1._vvv1(mostCurrent.activityBA,(float) (3.333),(float) (4.444));
 //BA.debugLineNum = 26;BA.debugLine="sizeviewV3.SetSizeViews(wb_description,0,0,240,22";
mostCurrent._vvvv1._vvv3(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._wb_description.getObject())),(float) (0),(float) (0),(float) (240),(float) (220),(float) (0));
 //BA.debugLineNum = 27;BA.debugLine="sizeviewV3.SetSize_lbl_Views(lbl_ava,220,5,230,40";
mostCurrent._vvvv1._setsize_lbl_views(mostCurrent.activityBA,mostCurrent._lbl_ava,(float) (220),(float) (5),(float) (230),(float) (40),(float) (15));
 //BA.debugLineNum = 28;BA.debugLine="sizeviewV3.SetSize_btn(btn_countinue,260,10,100,4";
mostCurrent._vvvv1._setsize_btn(mostCurrent.activityBA,mostCurrent._btn_countinue,(float) (260),(float) (10),(float) (100),(float) (40),(float) (15));
 //BA.debugLineNum = 31;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 32;BA.debugLine="lbl_ava.Text=\"\"\" جهت انجام معاملات از این لینک ،";
mostCurrent._lbl_ava.setText(BA.ObjectToCharSequence("\" جهت انجام معاملات از این لینک ، وارد شوید \""));
 //BA.debugLineNum = 33;BA.debugLine="wb_description.LoadUrl(\"file:///android_asset/pe";
mostCurrent._wb_description.LoadUrl("file:///android_asset/persion_description.html");
 //BA.debugLineNum = 34;BA.debugLine="btn_countinue.Text=\"ادامه\"";
mostCurrent._btn_countinue.setText(BA.ObjectToCharSequence("ادامه"));
 //BA.debugLineNum = 35;BA.debugLine="btn_countinue.Left=Activity.Width-btn_countinue.";
mostCurrent._btn_countinue.setLeft((int) (mostCurrent._activity.getWidth()-mostCurrent._btn_countinue.getWidth()-mostCurrent._btn_countinue.getLeft()));
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 37;BA.debugLine="lbl_ava.Text=\"\"\"Sign in to order transactions fr";
mostCurrent._lbl_ava.setText(BA.ObjectToCharSequence("\"Sign in to order transactions from this link\""));
 //BA.debugLineNum = 38;BA.debugLine="wb_description.LoadUrl(\"file:///android_asset/en";
mostCurrent._wb_description.LoadUrl("file:///android_asset/english_description.html");
 //BA.debugLineNum = 39;BA.debugLine="btn_countinue.Text=\"Continue\"";
mostCurrent._btn_countinue.setText(BA.ObjectToCharSequence("Continue"));
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 41;BA.debugLine="lbl_ava.Text=\"\"\"تسجيل الدخول لطلب المعاملات من ه";
mostCurrent._lbl_ava.setText(BA.ObjectToCharSequence("\"تسجيل الدخول لطلب المعاملات من هذا الرابط\""));
 //BA.debugLineNum = 42;BA.debugLine="wb_description.LoadUrl(\"file:///android_asset/ar";
mostCurrent._wb_description.LoadUrl("file:///android_asset/arabic_description.html");
 //BA.debugLineNum = 43;BA.debugLine="btn_countinue.Text=\"استمر\"";
mostCurrent._btn_countinue.setText(BA.ObjectToCharSequence("استمر"));
 //BA.debugLineNum = 44;BA.debugLine="btn_countinue.Left=Activity.Width-btn_countinue.";
mostCurrent._btn_countinue.setLeft((int) (mostCurrent._activity.getWidth()-mostCurrent._btn_countinue.getWidth()-mostCurrent._btn_countinue.getLeft()));
 };
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 70;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 71;BA.debugLine="If KeyCode=KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 72;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._vvv0.getObject()));
 //BA.debugLineNum = 73;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 75;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 60;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 61;BA.debugLine="lbl_tm.Enabled=False";
mostCurrent._lbl_tm.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 55;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 56;BA.debugLine="lbl_tm.Initialize(\"tmr\",700)";
mostCurrent._lbl_tm.Initialize(processBA,"tmr",(long) (700));
 //BA.debugLineNum = 57;BA.debugLine="lbl_tm.Enabled=True";
mostCurrent._lbl_tm.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 58;BA.debugLine="End Sub";
return "";
}
public static String  _btn_countinue_click() throws Exception{
 //BA.debugLineNum = 65;BA.debugLine="Sub btn_countinue_Click";
 //BA.debugLineNum = 66;BA.debugLine="StartActivity(sign_up_ACT)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._sign_up_act.getObject()));
 //BA.debugLineNum = 67;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 68;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 13;BA.debugLine="Private wb_description As WebView";
mostCurrent._wb_description = new anywheresoftware.b4a.objects.WebViewWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private btn_countinue As Button";
mostCurrent._btn_countinue = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private lbl_ava As Label";
mostCurrent._lbl_ava = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Dim lbl_tm As Timer";
mostCurrent._lbl_tm = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return "";
}
public static String  _lbl_ava_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _ava_url = null;
 //BA.debugLineNum = 79;BA.debugLine="Sub lbl_ava_Click";
 //BA.debugLineNum = 80;BA.debugLine="Dim ava_url As Intent";
_ava_url = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 82;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 83;BA.debugLine="ava_url.Initialize(ava_url.ACTION_VIEW,\"http://c";
_ava_url.Initialize(_ava_url.ACTION_VIEW,"http://cabin.fxpcm.net/fa/ref/NTQzMjExODczODc4Ng==");
 //BA.debugLineNum = 84;BA.debugLine="StartActivity(ava_url)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_ava_url.getObject()));
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 86;BA.debugLine="ava_url.Initialize(ava_url.ACTION_VIEW,\"http://c";
_ava_url.Initialize(_ava_url.ACTION_VIEW,"http://cabin.fxpcm.net/en/ref/NTQzMjExODczODc4Ng==");
 //BA.debugLineNum = 87;BA.debugLine="StartActivity(ava_url)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_ava_url.getObject()));
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 89;BA.debugLine="ava_url.Initialize(ava_url.ACTION_VIEW,\"http://c";
_ava_url.Initialize(_ava_url.ACTION_VIEW,"http://cabin.fxpcm.net/ar/ref/NTQzMjExODczODc4Ng==");
 //BA.debugLineNum = 90;BA.debugLine="StartActivity(ava_url)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_ava_url.getObject()));
 };
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _tmr_tick() throws Exception{
 //BA.debugLineNum = 95;BA.debugLine="Sub tmr_Tick";
 //BA.debugLineNum = 96;BA.debugLine="If lbl_ava.Visible=True Then";
if (mostCurrent._lbl_ava.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 97;BA.debugLine="lbl_ava.Visible=False";
mostCurrent._lbl_ava.setVisible(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 99;BA.debugLine="lbl_ava.Visible=True";
mostCurrent._lbl_ava.setVisible(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 101;BA.debugLine="End Sub";
return "";
}
}
