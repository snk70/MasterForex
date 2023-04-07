package shahin.app;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class config_app_module {
private static config_app_module mostCurrent = new config_app_module();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static String _web_url = "";
public static String _sgn_url = "";
public static String _info_url = "";
public static String _signals_url = "";
public static String _rst_ps = "";
public static String _us_path = "";
public static String _ps_path = "";
public static String _us_file_name = "";
public static String _ps_file_name = "";
public static String _v5 = "";
public static String _v6 = "";
public static String[] _app_lang = null;
public static String _app_lang_path = "";
public static String _app_lang_file = "";
public static String _selcted_language = "";
public anywheresoftware.b4a.samples.httputils2.httputils2service _vvv7 = null;
public b4a.example.set_view_background_and_border _set_view_background_and_border = null;
public shahin.app.main _vvv0 = null;
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
public static String  _hand_work_app(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 34;BA.debugLine="Sub hand_work_app";
 //BA.debugLineNum = 35;BA.debugLine="usn=\"\"";
_v5 = "";
 //BA.debugLineNum = 36;BA.debugLine="psd=\"\"";
_v6 = "";
 //BA.debugLineNum = 37;BA.debugLine="File.Delete(File.DirInternal,us_path&\"/\"&us_file_";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_us_path+"/"+_us_file_name);
 //BA.debugLineNum = 38;BA.debugLine="File.Delete(File.DirInternal,ps_path&\"/\"&ps_file_";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_ps_path+"/"+_ps_file_name);
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Dim web_url As String=\"http://getview.ir\"";
_web_url = BA.__b (new byte[] {112,110,15,-79,52,40,21,-73,112,110,29,-85,111,109,93,-82,59}, 220311);
 //BA.debugLineNum = 10;BA.debugLine="Dim sgn_URL As String=web_url&\"/prv/dfgdfgsdfg_gr";
_sgn_url = _web_url+BA.__b (new byte[] {55,106,37,-17,33,99,112,-17,113,124,32,-23,110,124,56,-64,46,122,56,-58,122,101,39,-1,101,119,125,-30,112,119,47,-14,98,101,46,-8,35,116,105,-13,115,114,45,-34,104,98,125,-8,126,112,34,-4,116,118,52,-43,20,110,51,-16,127,127,37,-2,101,98,125,-1,103,125,37,-19,97,127,112,-18,62,109,58,-24,97,103,39,-18,117,121,106,-28,56}, 141187);
 //BA.debugLineNum = 11;BA.debugLine="Dim info_URL As String=web_url&\"/prv/dfgdfgsdfg_g";
_info_url = _web_url+BA.__b (new byte[] {55,106,55,-15,33,99,98,-15,113,124,50,-9,110,124,42,-34,46,122,42,-40,122,101,53,-31,101,119,111,-4,112,119,61,-20,98,101,60,-26,35,116,123,-19,115,114,63,-64,104,98,111,-26,126,112,48,-30,116,118,38,-53,20,110,33,-18,127,127,55,-32,101,98,111,-31,103,125,55,-13,97,127,98,-27,47,100,61,-30,120,112,40,-29,117,121,120,-31,112,54,58,-19,103,118,16,-29,54,119,63,-2,103,103,56,-5,106,97,122,-46,127,113,49,-32,104,106,108}, 108776);
 //BA.debugLineNum = 12;BA.debugLine="Dim signals_URL As String=web_url&\"/prv/dfgdfgsdf";
_signals_url = _web_url+BA.__b (new byte[] {55,107,2,32,33,98,87,32,113,125,7,38,110,125,31,15,46,123,31,9,122,100,0,48,101,118,90,45,112,118,8,61,98,100,9,55,35,117,78,61,112,102,25,61,104,99,90,47,127,101,5,9,97,114,6,23,42,121,5,62,121,125,95,38,123,108,90,35,97,115,13,10,121,126,20,15,46,123,31,9,114,100,25,48,117,127,93,56,114,106,67}, 658578);
 //BA.debugLineNum = 13;BA.debugLine="Dim rst_ps As String=web_url&\"/prv/dfgdfgsdfg_grp";
_rst_ps = _web_url+BA.__b (new byte[] {55,106,36,104,33,99,113,104,113,124,33,110,110,124,57,71,46,122,57,65,122,101,38,120,101,119,124,101,112,119,46,117,98,101,47,127,35,116,104,117,112,103,63,117,104,98,124,103,127,100,35,65,97,115,32,95,42,120,35,118,121,124,121,109,97,110,100,106,103,116,52,50,110,124,57,114,34,100,22,105,116,101,11,123,102,123,119,124,35,54}, 138503);
 //BA.debugLineNum = 15;BA.debugLine="Dim us_path=\"sdfgjksdfgkserjlgwefrgpoergu_thbokrh";
_us_path = BA.__b (new byte[] {107,127,-70,19,100,109,-18,1,115,124,-89,4,111,105,-66,30,46,126,-90,18,100,100,-82,24,103,111,-12,8,72,108,-88,14,107,104,-92,25,47,102,-30,27,115,113,-96,9,107,96,-10,10,105,107,-85,28,108,101,-88,0,63,125,-87,10,97,113,-88,43,105,98,-5,7,116,122,-65,19,108,122,-79,20,102,110,-79,3,115,113,-127,32,71,79,-44,56,96,125,-90,59,86,68,-119,52,23,85,-102,41,70,71,-115,59,73,87,-42,41,94,81,-119,35,67,82,-99,15,57,109,-86,8,106,117,-13,6,124,96,-22,0,115,127,-72}, 838018);
 //BA.debugLineNum = 16;BA.debugLine="Dim ps_path=\"sdfgjksdfgksrgerdfsdjlgrgpoerguthbok";
_ps_path = BA.__b (new byte[] {107,126,90,-116,100,108,14,-98,115,125,71,-101,120,125,81,-97,45,110,80,-113,124,110,89,-102,101,108,28,-121,101,126,85,-121,108,96,89,-123,55,123,71,-121,59,118,85,-126,126,97,22,-124,124,118,92,-39,53,115,95,-110,127,120,71,-107,112,112,89,-103,100,115,4,-112,97,125,72,-73,108,120,85,-116,58,108,69,-118,115,100,17,-113,112,107,22,-112,72,78,101,-95,67,71,97,-117,55,116,127,-126,99,101,117,-84,73,86,54,-74,94,80,105,-76,81,68,104,-78,57,110,120,-75,74,93,107,-82,92,64,24,-120,114,127,75,-102,100,53,70,-97,47,90,100,-82,114,113,90,-100}, 93349);
 //BA.debugLineNum = 18;BA.debugLine="Dim us_file_name=\"42134.mgn\"";
_us_file_name = BA.__b (new byte[] {44,41,-32,-43,58,40,-3,-112,123}, 833410);
 //BA.debugLineNum = 19;BA.debugLine="Dim ps_file_name=\"wer876gE23ewRW_re.JPG\"";
_ps_file_name = BA.__b (new byte[] {111,127,8,119,57,49,92,27,39,41,15,59,88,77,45,59,44,38,47,31,81}, 217699);
 //BA.debugLineNum = 21;BA.debugLine="Dim usn,psd As String";
_v5 = "";
_v6 = "";
 //BA.debugLineNum = 25;BA.debugLine="Dim app_lang() As String=Array As String(\"En\",\"Fa";
_app_lang = new String[]{BA.__b (new byte[] {93,117}, 891320),BA.__b (new byte[] {94,122}, 653300),BA.__b (new byte[] {89,104}, 137354)};
 //BA.debugLineNum = 26;BA.debugLine="Dim app_lang_Path As String=\"dfgjkrerglkqerkgqwkl";
_app_lang_path = BA.__b (new byte[] {124,125,-77,78,101,116,-16,71,114,119,-81,86,111,105,-73,69,56,126,-96,72,115,111,-95,86,103,123,-76,68,120,106,-81,94,97,113,-73,84,41,117,-80,82,125,101,-68,78,123,98,-4,89,105,118,-88,76,97,119,-73,72,44,125,-94,16,106,124,-93,65,124,97,-30,80,103,113,-93,79,96,121,-71,84,43,126,-82,72,127,113,-68,81,107,108,-23,68,98,97,-67,77,112,122,-84,85,60,101,-79,69,118,107,-74,89,126,115,-86,26,44,53,-14,23,51,55,-32,9,100,63,-15,11,42,35,-29,23,58,96,-89,6,108,110,-74,64,104,110,-91,69,47,58,-83,18,99,55,-32,18,48,111,-83,30,35,127,-82,15,113,55,-72,23,34,37,-15,15,97,54,-80,11,106,51,-2,26,114,104,-11,16,52,102,-3,93,125,57,-15,13,44,125,-30,22,61,50,-28,66,96,114,-74,19,57,35,-82,21,122,61,-83,75,57,55,-27,65,59,37,-88}, 823122);
 //BA.debugLineNum = 27;BA.debugLine="Dim app_lang_File As String=\"logo.png\"";
_app_lang_file = BA.__b (new byte[] {116,116,-35,39,32,118,-107,62}, 791118);
 //BA.debugLineNum = 29;BA.debugLine="Dim selcted_language As String=\"fa\"";
_selcted_language = BA.__b (new byte[] {126,123}, 48297);
 //BA.debugLineNum = 31;BA.debugLine="End Sub";
return "";
}
}
