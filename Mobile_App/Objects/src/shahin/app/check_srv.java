package shahin.app;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class check_srv extends  android.app.Service{
	public static class check_srv_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
            BA.LogInfo("** Receiver (check_srv) OnReceive **");
			android.content.Intent in = new android.content.Intent(context, check_srv.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
            ServiceHelper.StarterHelper.startServiceFromReceiver (context, in, false, BA.class);
		}

	}
    static check_srv mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return check_srv.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "shahin.app", "shahin.app.check_srv");
            if (BA.isShellModeRuntimeCheck(processBA)) {
                processBA.raiseEvent2(null, true, "SHELL", false);
		    }
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "shahin.app.check_srv", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!false && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, true) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("*** Service (check_srv) Create ***");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (false) {
			ServiceHelper.StarterHelper.runWaitForLayouts();
		}
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		onStartCommand(intent, 0, 0);
    }
    @Override
    public int onStartCommand(final android.content.Intent intent, int flags, int startId) {
    	if (ServiceHelper.StarterHelper.onStartCommand(processBA, new Runnable() {
            public void run() {
                handleStart(intent);
            }}))
			;
		else {
			ServiceHelper.StarterHelper.addWaitForLayout (new Runnable() {
				public void run() {
                    processBA.setActivityPaused(false);
                    BA.LogInfo("** Service (check_srv) Create **");
                    processBA.raiseEvent(null, "service_create");
					handleStart(intent);
                    ServiceHelper.StarterHelper.removeWaitForLayout();
				}
			});
		}
        processBA.runHook("onstartcommand", this, new Object[] {intent, flags, startId});
		return android.app.Service.START_NOT_STICKY;
    }
    public void onTaskRemoved(android.content.Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (false)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (check_srv) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = ServiceHelper.StarterHelper.handleStartIntent(intent, _service, processBA);
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }
	
	@Override
	public void onDestroy() {
        super.onDestroy();
        BA.LogInfo("** Service (check_srv) Destroy **");
		processBA.raiseEvent(null, "service_destroy");
        processBA.service = null;
		mostCurrent = null;
		processBA.setActivityPaused(true);
        processBA.runHook("ondestroy", this, null);
	}

@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.samples.httputils2.httpjob _ht_check = null;
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
public shahin.app.sign_up_act _sign_up_act = null;
public shahin.app.regular_validations _regular_validations = null;
public shahin.app.sizeviewv3 _vvvv1 = null;
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
com.devil.signature.CheckSignature _sg = null;
 //BA.debugLineNum = 30;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 32;BA.debugLine="Dim sg As CheckSignature";
_sg = new com.devil.signature.CheckSignature();
 //BA.debugLineNum = 35;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 36;BA.debugLine="If Job.JobName=\"ht_check\" Then";
if ((_job._jobname).equals("ht_check")) { 
 //BA.debugLineNum = 37;BA.debugLine="If Job.GetString.ToLowerCase.Replace(\":\",\"\")<>s";
if ((_job._getstring().toLowerCase().replace(":","")).equals(_sg.sha1(processBA).toLowerCase().replace(":","")) == false) { 
 //BA.debugLineNum = 38;BA.debugLine="config_app_module.hand_work_app";
mostCurrent._config_app_module._hand_work_app(processBA);
 };
 };
 };
 //BA.debugLineNum = 42;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Dim ht_check As HttpJob";
_ht_check = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 9;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 24;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 26;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 15;BA.debugLine="ht_check.Initialize(\"ht_check\",Me)";
_ht_check._initialize(processBA,"ht_check",check_srv.getObject());
 //BA.debugLineNum = 16;BA.debugLine="ht_check.PostString(config_app_module.web_url&\"/p";
_ht_check._poststring(mostCurrent._config_app_module._web_url+"/prv/asfsadfs_personal_information_sdfgdfgdfgdsafjadsjf/ffwqqrqwqwe/sdfksndfgksdfg.php","");
 //BA.debugLineNum = 19;BA.debugLine="StartServiceAt(Me,DateTime.Now+300000,True)";
anywheresoftware.b4a.keywords.Common.StartServiceAt(processBA,check_srv.getObject(),(long) (anywheresoftware.b4a.keywords.Common.DateTime.getNow()+300000),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return "";
}
}
