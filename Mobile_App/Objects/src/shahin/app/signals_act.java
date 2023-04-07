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

public class signals_act extends Activity implements B4AActivity{
	public static signals_act mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "shahin.app", "shahin.app.signals_act");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (signals_act).");
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
		activityBA = new BA(this, layout, processBA, "shahin.app", "shahin.app.signals_act");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "shahin.app.signals_act", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (signals_act) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (signals_act) Resume **");
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
		return signals_act.class;
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
        BA.LogInfo("** Activity (signals_act) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (signals_act) Resume **");
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
public static String _job_work = "";
public anywheresoftware.b4a.samples.httputils2.httpjob _ht_signal = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_item = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _signals_list = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_ava = null;
public anywheresoftware.b4a.objects.WebViewWrapper _wb_risk = null;
public anywheresoftware.b4a.objects.Timer _lbl_tm = null;
public anywheresoftware.b4a.objects.Timer _ref_tm = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvv7 = null;
public b4a.example.set_view_background_and_border _set_view_background_and_border = null;
public shahin.app.main _vvv0 = null;
public shahin.app.config_app_module _config_app_module = null;
public shahin.app.history_page _history_page = null;
public shahin.app.contact_act _contact_act = null;
public shahin.app.email_act _email_act = null;
public shahin.app.login_act _login_act = null;
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
shahin.app.sec_class_1 _sc = null;
 //BA.debugLineNum = 28;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 30;BA.debugLine="Dim sc As sec_Class_1";
_sc = new shahin.app.sec_class_1();
 //BA.debugLineNum = 35;BA.debugLine="If config_app_module.usn<>\"\" And config_app_modul";
if ((mostCurrent._config_app_module._v5).equals("") == false && (mostCurrent._config_app_module._v6).equals("") == false) { 
 }else {
 //BA.debugLineNum = 39;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 40;BA.debugLine="Msgbox(\"شما باید ابتدا وارد شوید .\",\"شما وارد ن";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("شما باید ابتدا وارد شوید ."),BA.ObjectToCharSequence("شما وارد نشده اید !"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 42;BA.debugLine="Msgbox(\"You must first login\",\"Not logged in !\"";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("You must first login"),BA.ObjectToCharSequence("Not logged in !"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 44;BA.debugLine="Msgbox(\"عليك بتسجيل الدخول أولا\",\"لم تقم بتسجيل";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("عليك بتسجيل الدخول أولا"),BA.ObjectToCharSequence("لم تقم بتسجيل الدخول !"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 46;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._vvv0.getObject()));
 //BA.debugLineNum = 47;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 48;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 52;BA.debugLine="Activity.LoadLayout(\"signals_page_english\")";
mostCurrent._activity.LoadLayout("signals_page_english",mostCurrent.activityBA);
 //BA.debugLineNum = 53;BA.debugLine="signals_list.Panel.Height=0";
mostCurrent._signals_list.getPanel().setHeight((int) (0));
 //BA.debugLineNum = 54;BA.debugLine="wb_risk.Color=Colors.ARGB(255,44,137,228)";
mostCurrent._wb_risk.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (44),(int) (137),(int) (228)));
 //BA.debugLineNum = 58;BA.debugLine="sizeviewV3.SetPX(Activity.Width,Activity.Height,2";
mostCurrent._vvvv1._vvv2(mostCurrent.activityBA,(float) (mostCurrent._activity.getWidth()),(float) (mostCurrent._activity.getHeight()),(float) (240),(float) (320));
 //BA.debugLineNum = 59;BA.debugLine="sizeviewV3.SetPastScreenSizetoInche(3.333,4.444)";
mostCurrent._vvvv1._vvv1(mostCurrent.activityBA,(float) (3.333),(float) (4.444));
 //BA.debugLineNum = 60;BA.debugLine="sizeviewV3.SetSize_ScrolView(signals_list,80,0,24";
mostCurrent._vvvv1._setsize_scrolview(mostCurrent.activityBA,mostCurrent._signals_list,(float) (80),(float) (0),(float) (240),(float) (220),(float) (0));
 //BA.debugLineNum = 61;BA.debugLine="sizeviewV3.SetSizeViews(wb_risk,40,0,240,30,0)";
mostCurrent._vvvv1._vvv3(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._wb_risk.getObject())),(float) (40),(float) (0),(float) (240),(float) (30),(float) (0));
 //BA.debugLineNum = 62;BA.debugLine="sizeviewV3.SetSize_lbl_Views(lbl_ava,5,0,240,30,2";
mostCurrent._vvvv1._setsize_lbl_views(mostCurrent.activityBA,mostCurrent._lbl_ava,(float) (5),(float) (0),(float) (240),(float) (30),(float) (20));
 //BA.debugLineNum = 66;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 67;BA.debugLine="wb_risk.LoadUrl(\"file:///android_asset/persion_r";
mostCurrent._wb_risk.LoadUrl("file:///android_asset/persion_risk.html");
 //BA.debugLineNum = 68;BA.debugLine="lbl_ava.Text=\"شروع معاملات جهانی\"";
mostCurrent._lbl_ava.setText(BA.ObjectToCharSequence("شروع معاملات جهانی"));
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 70;BA.debugLine="wb_risk.LoadUrl(\"file:///android_asset/english_r";
mostCurrent._wb_risk.LoadUrl("file:///android_asset/english_risk.html");
 //BA.debugLineNum = 71;BA.debugLine="lbl_ava.Text=\"Start global transactions\"";
mostCurrent._lbl_ava.setText(BA.ObjectToCharSequence("Start global transactions"));
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 73;BA.debugLine="wb_risk.LoadUrl(\"file:///android_asset/arabic_ri";
mostCurrent._wb_risk.LoadUrl("file:///android_asset/arabic_risk.html");
 //BA.debugLineNum = 74;BA.debugLine="lbl_ava.Text=\"ابدأ المعاملات العالمية\"";
mostCurrent._lbl_ava.setText(BA.ObjectToCharSequence("ابدأ المعاملات العالمية"));
 };
 //BA.debugLineNum = 81;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 83;BA.debugLine="ProgressDialogShow2(\"لطفا کمی صبر کنید ...\",Fals";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,BA.ObjectToCharSequence("لطفا کمی صبر کنید ..."),anywheresoftware.b4a.keywords.Common.False);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 87;BA.debugLine="ProgressDialogShow2(\"Please wait ...\",False)";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,BA.ObjectToCharSequence("Please wait ..."),anywheresoftware.b4a.keywords.Common.False);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 91;BA.debugLine="ProgressDialogShow2(\"أرجو الإنتظار ...\",False)";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,BA.ObjectToCharSequence("أرجو الإنتظار ..."),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 95;BA.debugLine="ht_signal.Initialize(\"sgn\",Me)";
mostCurrent._ht_signal._initialize(processBA,"sgn",signals_act.getObject());
 //BA.debugLineNum = 97;BA.debugLine="ht_signal.PostString(config_app_module.signals_UR";
mostCurrent._ht_signal._poststring(mostCurrent._config_app_module._signals_url,"usn="+_sc._vvv5(mostCurrent._config_app_module._v5)+"&psd="+_sc._vvv5(mostCurrent._config_app_module._v6)+"&potgw52894fo_dkgnndfg_gtkrtkjtrldrt=dtpognerregjwerg&sina_rigergbwlergwer=wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe&ya_heidar=sdgsergwer_313_asdfkakefkqwelfqwef34875234589");
 //BA.debugLineNum = 101;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 370;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 371;BA.debugLine="If KeyCode=KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 372;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._vvv0.getObject()));
 //BA.debugLineNum = 373;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 375;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 376;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 195;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 196;BA.debugLine="lbl_tm.Enabled=False";
mostCurrent._lbl_tm.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 197;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 190;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 191;BA.debugLine="lbl_tm.Initialize(\"tmr\",800)";
mostCurrent._lbl_tm.Initialize(processBA,"tmr",(long) (800));
 //BA.debugLineNum = 192;BA.debugLine="lbl_tm.Enabled=True";
mostCurrent._lbl_tm.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 193;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _creat_a_item(String _money_type,String _buy_sel,String _buy_sel_num,String _sl_num,String _tp_num,String _time_val,String _signal_date,boolean _signal_status) throws Exception{
int _font_sizes = 0;
com.AB.ABExtDrawing.ABExtDrawing.ABMatrix _mt = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _sample_item = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _main_item = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _item_canvas = null;
com.AB.ABExtDrawing.ABExtDrawing _extdr = null;
com.AB.ABExtDrawing.ABExtDrawing.ABPaint _pnt = null;
com.AB.ABExtDrawing.ABExtDrawing _sina = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _sr = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _dr = null;
 //BA.debugLineNum = 302;BA.debugLine="Sub Creat_A_Item(money_type As String,buy_sel As S";
 //BA.debugLineNum = 304;BA.debugLine="Dim font_sizes As Int=lbl_ava.TextSize*2";
_font_sizes = (int) (mostCurrent._lbl_ava.getTextSize()*2);
 //BA.debugLineNum = 306;BA.debugLine="Dim mt As ABMatrix";
_mt = new com.AB.ABExtDrawing.ABExtDrawing.ABMatrix();
 //BA.debugLineNum = 307;BA.debugLine="mt.Initialize";
_mt.Initialize();
 //BA.debugLineNum = 309;BA.debugLine="Dim sample_item ,main_Item As  Bitmap";
_sample_item = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_main_item = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 310;BA.debugLine="Dim Item_Canvas As Canvas";
_item_canvas = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 311;BA.debugLine="Dim extdr As ABExtDrawing";
_extdr = new com.AB.ABExtDrawing.ABExtDrawing();
 //BA.debugLineNum = 312;BA.debugLine="Dim pnt As ABPaint";
_pnt = new com.AB.ABExtDrawing.ABExtDrawing.ABPaint();
 //BA.debugLineNum = 313;BA.debugLine="pnt.Initialize";
_pnt.Initialize();
 //BA.debugLineNum = 315;BA.debugLine="sample_item=LoadBitmap(File.DirAssets,\"signal_ite";
_sample_item = anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"signal_item.png");
 //BA.debugLineNum = 316;BA.debugLine="main_Item.InitializeMutable(sample_item.Width,sam";
_main_item.InitializeMutable(_sample_item.getWidth(),_sample_item.getHeight());
 //BA.debugLineNum = 317;BA.debugLine="Item_Canvas.Initialize2(main_Item)";
_item_canvas.Initialize2((android.graphics.Bitmap)(_main_item.getObject()));
 //BA.debugLineNum = 318;BA.debugLine="extdr.drawBitmap4(Item_Canvas,sample_item,mt,pnt)";
_extdr.drawBitmap4(_item_canvas,(android.graphics.Bitmap)(_sample_item.getObject()),_mt,_pnt);
 //BA.debugLineNum = 320;BA.debugLine="pnt.SetColor(Colors.Red)";
_pnt.SetColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 322;BA.debugLine="If buy_sel=\"s\" Then";
if ((_buy_sel).equals("s")) { 
 //BA.debugLineNum = 323;BA.debugLine="Item_Canvas.DrawText(\"Sell \"&\" = \"&buy_sel_num,1";
_item_canvas.DrawText(mostCurrent.activityBA,"Sell "+" = "+_buy_sel_num,(float) (1500),(float) (200),anywheresoftware.b4a.keywords.Common.Typeface.SANS_SERIF,(float) (_font_sizes),anywheresoftware.b4a.keywords.Common.Colors.Black,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 }else if((_buy_sel).equals("b")) { 
 //BA.debugLineNum = 325;BA.debugLine="Item_Canvas.DrawText(\"Buy  \"&\" = \"&buy_sel_num,1";
_item_canvas.DrawText(mostCurrent.activityBA,"Buy  "+" = "+_buy_sel_num,(float) (1500),(float) (200),anywheresoftware.b4a.keywords.Common.Typeface.SANS_SERIF,(float) (_font_sizes),anywheresoftware.b4a.keywords.Common.Colors.Black,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 }else if((_buy_sel).equals("bs")) { 
 //BA.debugLineNum = 327;BA.debugLine="Item_Canvas.DrawText(\"Buy Stop\"&\" = \"&buy_sel_nu";
_item_canvas.DrawText(mostCurrent.activityBA,"Buy Stop"+" = "+_buy_sel_num,(float) (1500),(float) (200),anywheresoftware.b4a.keywords.Common.Typeface.SANS_SERIF,(float) (_font_sizes),anywheresoftware.b4a.keywords.Common.Colors.Black,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 }else if((_buy_sel).equals("bl")) { 
 //BA.debugLineNum = 329;BA.debugLine="Item_Canvas.DrawText(\"Buy Limit\"&\" = \"&buy_sel_n";
_item_canvas.DrawText(mostCurrent.activityBA,"Buy Limit"+" = "+_buy_sel_num,(float) (1500),(float) (200),anywheresoftware.b4a.keywords.Common.Typeface.SANS_SERIF,(float) (_font_sizes),anywheresoftware.b4a.keywords.Common.Colors.Black,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 }else if((_buy_sel).equals("ss")) { 
 //BA.debugLineNum = 331;BA.debugLine="Item_Canvas.DrawText(\"Sell Stop\"&\" = \"&buy_sel_n";
_item_canvas.DrawText(mostCurrent.activityBA,"Sell Stop"+" = "+_buy_sel_num,(float) (1500),(float) (200),anywheresoftware.b4a.keywords.Common.Typeface.SANS_SERIF,(float) (_font_sizes),anywheresoftware.b4a.keywords.Common.Colors.Black,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 }else if((_buy_sel).equals("sl")) { 
 //BA.debugLineNum = 333;BA.debugLine="Item_Canvas.DrawText(\"Sell Limit\"&\" = \"&buy_sel_";
_item_canvas.DrawText(mostCurrent.activityBA,"Sell Limit"+" = "+_buy_sel_num,(float) (1500),(float) (200),anywheresoftware.b4a.keywords.Common.Typeface.SANS_SERIF,(float) (_font_sizes),anywheresoftware.b4a.keywords.Common.Colors.Black,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 };
 //BA.debugLineNum = 337;BA.debugLine="Item_Canvas.DrawText(\"T/p  = \"&TP_num,1500,415,Ty";
_item_canvas.DrawText(mostCurrent.activityBA,"T/p  = "+_tp_num,(float) (1500),(float) (415),anywheresoftware.b4a.keywords.Common.Typeface.SANS_SERIF,(float) (_font_sizes),anywheresoftware.b4a.keywords.Common.Colors.Black,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 //BA.debugLineNum = 340;BA.debugLine="Item_Canvas.DrawText(\"S/L  = \"&SL_num,1500,630,Ty";
_item_canvas.DrawText(mostCurrent.activityBA,"S/L  = "+_sl_num,(float) (1500),(float) (630),anywheresoftware.b4a.keywords.Common.Typeface.SANS_SERIF,(float) (_font_sizes),anywheresoftware.b4a.keywords.Common.Colors.Black,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 //BA.debugLineNum = 343;BA.debugLine="Item_Canvas.DrawText(\" Time= \"&\" \"&time_val,1450,";
_item_canvas.DrawText(mostCurrent.activityBA," Time= "+" "+_time_val,(float) (1450),(float) (830),anywheresoftware.b4a.keywords.Common.Typeface.SANS_SERIF,(float) (_font_sizes*(0.9222222)),anywheresoftware.b4a.keywords.Common.Colors.Black,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 //BA.debugLineNum = 346;BA.debugLine="Item_Canvas.DrawText(money_type,505,230,Typeface.";
_item_canvas.DrawText(mostCurrent.activityBA,_money_type,(float) (505),(float) (230),anywheresoftware.b4a.keywords.Common.Typeface.CreateNew(anywheresoftware.b4a.keywords.Common.Typeface.SANS_SERIF,anywheresoftware.b4a.keywords.Common.Typeface.STYLE_BOLD),(float) ((130/(double)100)*_font_sizes),anywheresoftware.b4a.keywords.Common.Colors.Black,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 //BA.debugLineNum = 348;BA.debugLine="Item_Canvas.DrawText(\"Date : \"&signal_date,520,50";
_item_canvas.DrawText(mostCurrent.activityBA,"Date : "+_signal_date,(float) (520),(float) (500),anywheresoftware.b4a.keywords.Common.Typeface.SANS_SERIF,(float) (_font_sizes*(75/(double)100)),anywheresoftware.b4a.keywords.Common.Colors.Black,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 //BA.debugLineNum = 350;BA.debugLine="Dim sina As ABExtDrawing";
_sina = new com.AB.ABExtDrawing.ABExtDrawing();
 //BA.debugLineNum = 351;BA.debugLine="Dim mt As ABMatrix";
_mt = new com.AB.ABExtDrawing.ABExtDrawing.ABMatrix();
 //BA.debugLineNum = 353;BA.debugLine="Dim sr,dr As Rect";
_sr = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
_dr = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 355;BA.debugLine="sr.Initialize(0,0,153,147)";
_sr.Initialize((int) (0),(int) (0),(int) (153),(int) (147));
 //BA.debugLineNum = 356;BA.debugLine="dr.Initialize(410,576,716,870)";
_dr.Initialize((int) (410),(int) (576),(int) (716),(int) (870));
 //BA.debugLineNum = 359;BA.debugLine="If signal_status=True Then";
if (_signal_status==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 361;BA.debugLine="Item_Canvas.DrawBitmap(LoadBitmap(File.DirAssets";
_item_canvas.DrawBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"tick.png").getObject()),(android.graphics.Rect)(_sr.getObject()),(android.graphics.Rect)(_dr.getObject()));
 }else {
 //BA.debugLineNum = 364;BA.debugLine="Item_Canvas.DrawBitmap(LoadBitmap(File.DirAssets";
_item_canvas.DrawBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"strike.png").getObject()),(android.graphics.Rect)(_sr.getObject()),(android.graphics.Rect)(_dr.getObject()));
 };
 //BA.debugLineNum = 367;BA.debugLine="Return Item_Canvas.Bitmap";
if (true) return _item_canvas.getBitmap();
 //BA.debugLineNum = 368;BA.debugLine="End Sub";
return null;
}
public static String  _decompos_signals(String _text_val) throws Exception{
shahin.app.sec_class_1 _sc = null;
anywheresoftware.b4a.objects.collections.JSONParser _js = null;
anywheresoftware.b4a.objects.collections.List _code_list = null;
boolean _is_left = false;
int _top_item = 0;
anywheresoftware.b4a.objects.ImageViewWrapper[] _img = null;
int _x = 0;
anywheresoftware.b4a.objects.collections.Map _mp = null;
boolean _st = false;
String _b_s = "";
String _time_val = "";
String _date = "";
 //BA.debugLineNum = 216;BA.debugLine="Sub decompos_signals(Text_Val As String)";
 //BA.debugLineNum = 217;BA.debugLine="Dim sc As sec_Class_1";
_sc = new shahin.app.sec_class_1();
 //BA.debugLineNum = 220;BA.debugLine="Dim js As JSONParser";
_js = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 221;BA.debugLine="Dim code_list As List";
_code_list = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 222;BA.debugLine="code_list.Initialize";
_code_list.Initialize();
 //BA.debugLineNum = 224;BA.debugLine="js.Initialize(sc.Decryption(Text_Val))";
_js.Initialize(_sc._vvv4(_text_val));
 //BA.debugLineNum = 227;BA.debugLine="img_Item.Initialize(\"img_Item\")";
mostCurrent._img_item.Initialize(mostCurrent.activityBA,"img_Item");
 //BA.debugLineNum = 228;BA.debugLine="img_Item.Gravity=Gravity.FILL";
mostCurrent._img_item.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 229;BA.debugLine="Dim is_left As Boolean=True";
_is_left = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 230;BA.debugLine="Dim top_Item As Int=2%y";
_top_item = anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (2),mostCurrent.activityBA);
 //BA.debugLineNum = 233;BA.debugLine="code_list=	 js.NextArray";
_code_list = _js.NextArray();
 //BA.debugLineNum = 236;BA.debugLine="Dim img(code_list.Size) As ImageView";
_img = new anywheresoftware.b4a.objects.ImageViewWrapper[_code_list.getSize()];
{
int d0 = _img.length;
for (int i0 = 0;i0 < d0;i0++) {
_img[i0] = new anywheresoftware.b4a.objects.ImageViewWrapper();
}
}
;
 //BA.debugLineNum = 241;BA.debugLine="For x=0 To code_list.Size-1";
{
final int step12 = 1;
final int limit12 = (int) (_code_list.getSize()-1);
_x = (int) (0) ;
for (;(step12 > 0 && _x <= limit12) || (step12 < 0 && _x >= limit12) ;_x = ((int)(0 + _x + step12))  ) {
 //BA.debugLineNum = 243;BA.debugLine="Dim mp As Map";
_mp = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 244;BA.debugLine="mp.Initialize";
_mp.Initialize();
 //BA.debugLineNum = 245;BA.debugLine="mp=code_list.Get(x)";
_mp.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_code_list.Get(_x)));
 //BA.debugLineNum = 248;BA.debugLine="Dim st As Boolean";
_st = false;
 //BA.debugLineNum = 249;BA.debugLine="Dim b_s,time_val,date As String";
_b_s = "";
_time_val = "";
_date = "";
 //BA.debugLineNum = 252;BA.debugLine="If mp.Get(\"is_signal\")=\"0\" Then";
if ((_mp.Get((Object)("is_signal"))).equals((Object)("0"))) { 
 //BA.debugLineNum = 253;BA.debugLine="st=False";
_st = anywheresoftware.b4a.keywords.Common.False;
 }else if((_mp.Get((Object)("is_signal"))).equals((Object)("1"))) { 
 //BA.debugLineNum = 255;BA.debugLine="st=True";
_st = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 258;BA.debugLine="If mp.Get(\"buy_sel\")=\"0\" Then";
if ((_mp.Get((Object)("buy_sel"))).equals((Object)("0"))) { 
 //BA.debugLineNum = 259;BA.debugLine="b_s=\"s\"";
_b_s = "s";
 }else if((_mp.Get((Object)("buy_sel"))).equals((Object)("1"))) { 
 //BA.debugLineNum = 261;BA.debugLine="b_s=\"b\"";
_b_s = "b";
 }else if((_mp.Get((Object)("buy_sel"))).equals((Object)("2"))) { 
 //BA.debugLineNum = 263;BA.debugLine="b_s=\"ss\"";
_b_s = "ss";
 }else if((_mp.Get((Object)("buy_sel"))).equals((Object)("3"))) { 
 //BA.debugLineNum = 265;BA.debugLine="b_s=\"bs\"";
_b_s = "bs";
 }else if((_mp.Get((Object)("buy_sel"))).equals((Object)("4"))) { 
 //BA.debugLineNum = 267;BA.debugLine="b_s=\"sl\"";
_b_s = "sl";
 }else if((_mp.Get((Object)("buy_sel"))).equals((Object)("5"))) { 
 //BA.debugLineNum = 269;BA.debugLine="b_s=\"bl\"";
_b_s = "bl";
 };
 //BA.debugLineNum = 272;BA.debugLine="If mp.Get(\"time\")=\"0\" Then";
if ((_mp.Get((Object)("time"))).equals((Object)("0"))) { 
 //BA.debugLineNum = 273;BA.debugLine="time_val=\"Short\"";
_time_val = "Short";
 }else if((_mp.Get((Object)("time"))).equals((Object)("1"))) { 
 //BA.debugLineNum = 275;BA.debugLine="time_val=\"Long\"";
_time_val = "Long";
 };
 //BA.debugLineNum = 281;BA.debugLine="img(x).Initialize(\"img_Item\")";
_img[_x].Initialize(mostCurrent.activityBA,"img_Item");
 //BA.debugLineNum = 282;BA.debugLine="img(x).Gravity=Gravity.FILL";
_img[_x].setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 285;BA.debugLine="img(x).Bitmap=Creat_A_Item(mp.Get(\"type\"),b_s,sc";
_img[_x].setBitmap((android.graphics.Bitmap)(_creat_a_item(BA.ObjectToString(_mp.Get((Object)("type"))),_b_s,_sc._vvv4(BA.ObjectToString(_mp.Get((Object)("buy")))),_sc._vvv4(BA.ObjectToString(_mp.Get((Object)("sl")))),_sc._vvv4(BA.ObjectToString(_mp.Get((Object)("tp")))),_time_val,BA.ObjectToString(_mp.Get((Object)("date"))),_st).getObject()));
 //BA.debugLineNum = 289;BA.debugLine="signals_list.Panel.AddView(img(x),5%x,top_Item,9";
mostCurrent._signals_list.getPanel().AddView((android.view.View)(_img[_x].getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),_top_item,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (90),mostCurrent.activityBA),(int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (90),mostCurrent.activityBA)*931)/(double)1998));
 //BA.debugLineNum = 290;BA.debugLine="top_Item=img(x).Top+img(x).Height+3%y";
_top_item = (int) (_img[_x].getTop()+_img[_x].getHeight()+anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (3),mostCurrent.activityBA));
 //BA.debugLineNum = 292;BA.debugLine="signals_list.Panel.Height=top_Item";
mostCurrent._signals_list.getPanel().setHeight(_top_item);
 }
};
 //BA.debugLineNum = 296;BA.debugLine="ref_tm.Initialize(\"refresh_signals\",\"60000\")";
mostCurrent._ref_tm.Initialize(processBA,"refresh_signals",(long)(Double.parseDouble("60000")));
 //BA.debugLineNum = 297;BA.debugLine="ref_tm.Enabled=True";
mostCurrent._ref_tm.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 299;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 14;BA.debugLine="Dim job_work As String";
mostCurrent._job_work = "";
 //BA.debugLineNum = 16;BA.debugLine="Dim ht_signal As HttpJob";
mostCurrent._ht_signal = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 18;BA.debugLine="Dim img_Item As ImageView";
mostCurrent._img_item = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private signals_list As ScrollView";
mostCurrent._signals_list = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private lbl_ava As Label";
mostCurrent._lbl_ava = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Dim wb_risk As WebView";
mostCurrent._wb_risk = new anywheresoftware.b4a.objects.WebViewWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Dim lbl_tm As Timer";
mostCurrent._lbl_tm = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 23;BA.debugLine="Dim ref_tm As Timer";
mostCurrent._ref_tm = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 26;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
shahin.app.sec_class_1 _sc = null;
 //BA.debugLineNum = 379;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 380;BA.debugLine="Dim sc As sec_Class_1";
_sc = new shahin.app.sec_class_1();
 //BA.debugLineNum = 382;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 387;BA.debugLine="If Job.Success=True Then";
if (_job._success==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 393;BA.debugLine="If Job.GetString=sc.Encryption(\"hnd\") Then";
if ((_job._getstring()).equals(_sc._vvv5("hnd"))) { 
 //BA.debugLineNum = 394;BA.debugLine="config_app_module.hand_work_app";
mostCurrent._config_app_module._hand_work_app(mostCurrent.activityBA);
 //BA.debugLineNum = 395;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 //BA.debugLineNum = 396;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 401;BA.debugLine="If Job.JobName=\"sgn\" Then";
if ((_job._jobname).equals("sgn")) { 
 //BA.debugLineNum = 402;BA.debugLine="job_work=\"\"";
mostCurrent._job_work = "";
 //BA.debugLineNum = 403;BA.debugLine="If Job.GetString=sc.Encryption(\"wrong_info\") Th";
if ((_job._getstring()).equals(_sc._vvv5("wrong_info"))) { 
 //BA.debugLineNum = 404;BA.debugLine="config_app_module.hand_work_app";
mostCurrent._config_app_module._hand_work_app(mostCurrent.activityBA);
 }else if((_job._getstring()).equals(_sc._vvv5("expire_time"))) { 
 //BA.debugLineNum = 407;BA.debugLine="If config_app_module.selcted_language=\"Fa\" The";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 408;BA.debugLine="Msgbox(\"شارژ حساب شما تمام شده ؛ لطفا نسبت به";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("شارژ حساب شما تمام شده ؛ لطفا نسبت به تمدید شارژ اقدام نمایید ."),BA.ObjectToCharSequence("اتمام شارژ"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 410;BA.debugLine="Msgbox(\"Your account charge has expired; plea";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Your account charge has expired; please revival the charge."),BA.ObjectToCharSequence("Expired Charge"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 412;BA.debugLine="Msgbox(\"انتهت صلاحية حسابك. يرجى إحياء التهمة";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("انتهت صلاحية حسابك. يرجى إحياء التهمة."),BA.ObjectToCharSequence("انتهت صلاحيتها"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 416;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._vvv0.getObject()));
 //BA.debugLineNum = 417;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else if((_job._getstring()).equals(_sc._vvv5("no_verify"))) { 
 //BA.debugLineNum = 419;BA.debugLine="config_app_module.hand_work_app";
mostCurrent._config_app_module._hand_work_app(mostCurrent.activityBA);
 //BA.debugLineNum = 420;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 }else if((_job._getstring()).equals(_sc._vvv5("error"))) { 
 //BA.debugLineNum = 422;BA.debugLine="config_app_module.hand_work_app";
mostCurrent._config_app_module._hand_work_app(mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 424;BA.debugLine="job_work=\"signal_ok\"";
mostCurrent._job_work = "signal_ok";
 };
 };
 //BA.debugLineNum = 428;BA.debugLine="If job_work=\"signal_ok\" Then";
if ((mostCurrent._job_work).equals("signal_ok")) { 
 //BA.debugLineNum = 429;BA.debugLine="signals_list.Panel.RemoveAllViews";
mostCurrent._signals_list.getPanel().RemoveAllViews();
 //BA.debugLineNum = 430;BA.debugLine="decompos_signals(Job.GetString)";
_decompos_signals(_job._getstring());
 };
 }else {
 //BA.debugLineNum = 435;BA.debugLine="If signals_list.Panel.Height=0 Then";
if (mostCurrent._signals_list.getPanel().getHeight()==0) { 
 //BA.debugLineNum = 438;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 440;BA.debugLine="Msgbox(\"خطایی رخ داد ، لطفا بعدا مجددا تلاش نم";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("خطایی رخ داد ، لطفا بعدا مجددا تلاش نمایید"),BA.ObjectToCharSequence("خطا"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 444;BA.debugLine="Msgbox(\"An error occurred. Please try again la";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("An error occurred. Please try again later"),BA.ObjectToCharSequence("Error"),mostCurrent.activityBA);
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 448;BA.debugLine="Msgbox(\"حدث خطأ. الرجاء معاودة المحاولة في وقت";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("حدث خطأ. الرجاء معاودة المحاولة في وقت لاحق"),BA.ObjectToCharSequence("خطأ"),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 452;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._vvv0.getObject()));
 //BA.debugLineNum = 453;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else {
 //BA.debugLineNum = 455;BA.debugLine="ht_signal.Initialize(\"sgn\",Me)";
mostCurrent._ht_signal._initialize(processBA,"sgn",signals_act.getObject());
 //BA.debugLineNum = 457;BA.debugLine="ht_signal.PostString(config_app_module.signals_";
mostCurrent._ht_signal._poststring(mostCurrent._config_app_module._signals_url,"usn="+_sc._vvv5(mostCurrent._config_app_module._v5)+"&psd="+_sc._vvv5(mostCurrent._config_app_module._v6)+"&potgw52894fo_dkgnndfg_gtkrtkjtrldrt=dtpognerregjwerg&sina_rigergbwlergwer=wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe&ya_heidar=sdgsergwer_313_asdfkakefkqwelfqwef34875234589");
 //BA.debugLineNum = 458;BA.debugLine="Return";
if (true) return "";
 };
 };
 //BA.debugLineNum = 467;BA.debugLine="End Sub";
return "";
}
public static String  _lbl_ava_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _ava_url = null;
 //BA.debugLineNum = 199;BA.debugLine="Sub lbl_ava_Click";
 //BA.debugLineNum = 200;BA.debugLine="Dim ava_url As Intent";
_ava_url = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 202;BA.debugLine="If config_app_module.selcted_language=\"Fa\" Then";
if ((mostCurrent._config_app_module._selcted_language).equals("Fa")) { 
 //BA.debugLineNum = 203;BA.debugLine="ava_url.Initialize(ava_url.ACTION_VIEW,\"http://c";
_ava_url.Initialize(_ava_url.ACTION_VIEW,"http://cabin.fxpcm.net/fa/ref/NTQzMjExODczODc4Ng==");
 //BA.debugLineNum = 204;BA.debugLine="StartActivity(ava_url)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_ava_url.getObject()));
 }else if((mostCurrent._config_app_module._selcted_language).equals("En")) { 
 //BA.debugLineNum = 206;BA.debugLine="ava_url.Initialize(ava_url.ACTION_VIEW,\"http://c";
_ava_url.Initialize(_ava_url.ACTION_VIEW,"http://cabin.fxpcm.net/en/ref/NTQzMjExODczODc4Ng==");
 //BA.debugLineNum = 207;BA.debugLine="StartActivity(ava_url)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_ava_url.getObject()));
 }else if((mostCurrent._config_app_module._selcted_language).equals("Ar")) { 
 //BA.debugLineNum = 209;BA.debugLine="ava_url.Initialize(ava_url.ACTION_VIEW,\"http://c";
_ava_url.Initialize(_ava_url.ACTION_VIEW,"http://cabin.fxpcm.net/ar/ref/NTQzMjExODczODc4Ng==");
 //BA.debugLineNum = 210;BA.debugLine="StartActivity(ava_url)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_ava_url.getObject()));
 };
 //BA.debugLineNum = 212;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _refresh_signals_tick() throws Exception{
shahin.app.sec_class_1 _sc = null;
 //BA.debugLineNum = 178;BA.debugLine="Sub refresh_signals_Tick";
 //BA.debugLineNum = 180;BA.debugLine="Dim sc As sec_Class_1";
_sc = new shahin.app.sec_class_1();
 //BA.debugLineNum = 182;BA.debugLine="ref_tm.Enabled=False";
mostCurrent._ref_tm.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 184;BA.debugLine="ht_signal.Initialize(\"sgn\",Me)";
mostCurrent._ht_signal._initialize(processBA,"sgn",signals_act.getObject());
 //BA.debugLineNum = 186;BA.debugLine="ht_signal.PostString(config_app_module.signals_UR";
mostCurrent._ht_signal._poststring(mostCurrent._config_app_module._signals_url,"usn="+_sc._vvv5(mostCurrent._config_app_module._v5)+"&psd="+_sc._vvv5(mostCurrent._config_app_module._v6)+"&potgw52894fo_dkgnndfg_gtkrtkjtrldrt=dtpognerregjwerg&sina_rigergbwlergwer=wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe&ya_heidar=sdgsergwer_313_asdfkakefkqwelfqwef34875234589");
 //BA.debugLineNum = 188;BA.debugLine="End Sub";
return "";
}
public static String  _tmr_tick() throws Exception{
 //BA.debugLineNum = 170;BA.debugLine="Sub tmr_Tick";
 //BA.debugLineNum = 171;BA.debugLine="If lbl_ava.Visible=True Then";
if (mostCurrent._lbl_ava.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 172;BA.debugLine="lbl_ava.Visible=False";
mostCurrent._lbl_ava.setVisible(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 174;BA.debugLine="lbl_ava.Visible=True";
mostCurrent._lbl_ava.setVisible(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 176;BA.debugLine="End Sub";
return "";
}
}
