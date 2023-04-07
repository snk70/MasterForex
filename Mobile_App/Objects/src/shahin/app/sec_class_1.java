package shahin.app;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class sec_class_1 extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "shahin.app.sec_class_1");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", shahin.app.sec_class_1.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public int _vvv6 = 0;
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
public shahin.app.check_srv _check_srv = null;
public shahin.app.sizeviewv3 _vvvv1 = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 2;BA.debugLine="Dim lng As Int";
_vvv6 = 0;
 //BA.debugLineNum = 3;BA.debugLine="End Sub";
return "";
}
public String  _data_decryption(String _t) throws Exception{
String _txt = "";
String _tx2 = "";
String[] _cdc = null;
int _n = 0;
String _x = "";
int _b = 0;
 //BA.debugLineNum = 319;BA.debugLine="Public Sub data_Decryption( t As String) As String";
 //BA.debugLineNum = 320;BA.debugLine="lng =3";
_vvv6 = (int) (3);
 //BA.debugLineNum = 323;BA.debugLine="Dim txt As String";
_txt = "";
 //BA.debugLineNum = 325;BA.debugLine="Dim tx2 As String";
_tx2 = "";
 //BA.debugLineNum = 326;BA.debugLine="Dim cdc(t.Length) As String";
_cdc = new String[_t.length()];
java.util.Arrays.fill(_cdc,"");
 //BA.debugLineNum = 328;BA.debugLine="txt = t";
_txt = _t;
 //BA.debugLineNum = 330;BA.debugLine="For n = 0 To t.Length / lng - 1";
{
final int step6 = 1;
final int limit6 = (int) (_t.length()/(double)_vvv6-1);
_n = (int) (0) ;
for (;(step6 > 0 && _n <= limit6) || (step6 < 0 && _n >= limit6) ;_n = ((int)(0 + _n + step6))  ) {
 //BA.debugLineNum = 331;BA.debugLine="Dim x As String";
_x = "";
 //BA.debugLineNum = 332;BA.debugLine="x = txt.Substring(txt.Length - lng)";
_x = _txt.substring((int) (_txt.length()-_vvv6));
 //BA.debugLineNum = 333;BA.debugLine="cdc(n) = sina_DEC(x)";
_cdc[_n] = _sina_dec(_x);
 //BA.debugLineNum = 334;BA.debugLine="txt = txt.SubString2(0, txt.Length - lng)";
_txt = _txt.substring((int) (0),(int) (_txt.length()-_vvv6));
 }
};
 //BA.debugLineNum = 337;BA.debugLine="For b = 0 To cdc.Length - 1";
{
final int step12 = 1;
final int limit12 = (int) (_cdc.length-1);
_b = (int) (0) ;
for (;(step12 > 0 && _b <= limit12) || (step12 < 0 && _b >= limit12) ;_b = ((int)(0 + _b + step12))  ) {
 //BA.debugLineNum = 338;BA.debugLine="tx2 = cdc(b) & tx2";
_tx2 = _cdc[_b]+_tx2;
 }
};
 //BA.debugLineNum = 341;BA.debugLine="Return tx2";
if (true) return _tx2;
 //BA.debugLineNum = 342;BA.debugLine="End Sub";
return "";
}
public String  _data_encryption(String _t) throws Exception{
String _txt = "";
String _tx1 = "";
String[] _cec = null;
int _i = 0;
String _x = "";
int _j = 0;
 //BA.debugLineNum = 37;BA.debugLine="Public Sub data_Encryption(t As String) As String";
 //BA.debugLineNum = 39;BA.debugLine="Dim txt As String";
_txt = "";
 //BA.debugLineNum = 41;BA.debugLine="Dim tx1 As String = \"\"";
_tx1 = "";
 //BA.debugLineNum = 42;BA.debugLine="tx1 = \"\"";
_tx1 = "";
 //BA.debugLineNum = 43;BA.debugLine="Dim cec(t.Length) As String";
_cec = new String[_t.length()];
java.util.Arrays.fill(_cec,"");
 //BA.debugLineNum = 44;BA.debugLine="txt = t";
_txt = _t;
 //BA.debugLineNum = 46;BA.debugLine="For i = 0 To t.Length - 1";
{
final int step6 = 1;
final int limit6 = (int) (_t.length()-1);
_i = (int) (0) ;
for (;(step6 > 0 && _i <= limit6) || (step6 < 0 && _i >= limit6) ;_i = ((int)(0 + _i + step6))  ) {
 //BA.debugLineNum = 47;BA.debugLine="Dim x As String";
_x = "";
 //BA.debugLineNum = 48;BA.debugLine="x = txt.Substring(txt.Length - 1)";
_x = _txt.substring((int) (_txt.length()-1));
 //BA.debugLineNum = 49;BA.debugLine="cec(i) = sina_ENC(x)";
_cec[_i] = _sina_enc(_x);
 //BA.debugLineNum = 50;BA.debugLine="txt = txt.SubString2(0, txt.Length - 1)";
_txt = _txt.substring((int) (0),(int) (_txt.length()-1));
 }
};
 //BA.debugLineNum = 52;BA.debugLine="tx1 = \"\"";
_tx1 = "";
 //BA.debugLineNum = 53;BA.debugLine="For j = 0 To cec.Length - 1";
{
final int step13 = 1;
final int limit13 = (int) (_cec.length-1);
_j = (int) (0) ;
for (;(step13 > 0 && _j <= limit13) || (step13 < 0 && _j >= limit13) ;_j = ((int)(0 + _j + step13))  ) {
 //BA.debugLineNum = 54;BA.debugLine="tx1 = cec(j) & tx1";
_tx1 = _cec[_j]+_tx1;
 }
};
 //BA.debugLineNum = 59;BA.debugLine="Return tx1";
if (true) return _tx1;
 //BA.debugLineNum = 60;BA.debugLine="End Sub";
return "";
}
public String  _vvv4(String _val) throws Exception{
anywheresoftware.b4a.agraham.encryption.Base64 _bs = null;
 //BA.debugLineNum = 23;BA.debugLine="Sub Decryption(Val As String) As String";
 //BA.debugLineNum = 24;BA.debugLine="If Val=\"\" Then";
if ((_val).equals("")) { 
 //BA.debugLineNum = 25;BA.debugLine="Return \"\"";
if (true) return "";
 }else {
 //BA.debugLineNum = 27;BA.debugLine="Dim bs As Base64";
_bs = new anywheresoftware.b4a.agraham.encryption.Base64();
 //BA.debugLineNum = 28;BA.debugLine="Val=Val.SubString2(Val.Length-3,Val.Length)&Val.";
_val = _val.substring((int) (_val.length()-3),_val.length())+_val.substring((int) (0),(int) (_val.length()-3));
 //BA.debugLineNum = 29;BA.debugLine="Val=Val.SubString2(Val.Length-3,Val.Length)&Val.";
_val = _val.substring((int) (_val.length()-3),_val.length())+_val.substring((int) (0),(int) (_val.length()-3));
 //BA.debugLineNum = 30;BA.debugLine="Val=data_Decryption(Val)";
_val = _data_decryption(_val);
 //BA.debugLineNum = 31;BA.debugLine="Val=bs.DecodeStoS(Val,\"UTF-8\")";
_val = _bs.DecodeStoS(_val,"UTF-8");
 };
 //BA.debugLineNum = 33;BA.debugLine="Return Val";
if (true) return _val;
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return "";
}
public String  _vvv5(String _val) throws Exception{
anywheresoftware.b4a.agraham.encryption.Base64 _bs = null;
 //BA.debugLineNum = 10;BA.debugLine="Sub Encryption(Val As String) As String";
 //BA.debugLineNum = 11;BA.debugLine="If Val=\"\" Then";
if ((_val).equals("")) { 
 //BA.debugLineNum = 12;BA.debugLine="Return \"\"";
if (true) return "";
 }else {
 //BA.debugLineNum = 14;BA.debugLine="Dim bs As Base64";
_bs = new anywheresoftware.b4a.agraham.encryption.Base64();
 //BA.debugLineNum = 15;BA.debugLine="Val=bs.EncodeStoS(Val,\"UTF-8\")";
_val = _bs.EncodeStoS(_val,"UTF-8");
 //BA.debugLineNum = 16;BA.debugLine="Val=data_Encryption(Val)";
_val = _data_encryption(_val);
 //BA.debugLineNum = 17;BA.debugLine="Val=Val.SubString2(3,Val.Length)&Val.SubString2(";
_val = _val.substring((int) (3),_val.length())+_val.substring((int) (0),(int) (3));
 //BA.debugLineNum = 18;BA.debugLine="Val=Val.SubString2(3,Val.Length)&Val.SubString2(";
_val = _val.substring((int) (3),_val.length())+_val.substring((int) (0),(int) (3));
 //BA.debugLineNum = 19;BA.debugLine="Return Val";
if (true) return _val;
 };
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 6;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public String  _sina_dec(String _v) throws Exception{
 //BA.debugLineNum = 345;BA.debugLine="Public Sub sina_DEC(v As String) As String";
 //BA.debugLineNum = 346;BA.debugLine="If v=\"mbW\" Then";
if ((_v).equals("mbW")) { 
 //BA.debugLineNum = 347;BA.debugLine="Return \"0\"";
if (true) return "0";
 }else if((_v).equals("ndn")) { 
 //BA.debugLineNum = 349;BA.debugLine="Return \"1\"";
if (true) return "1";
 }else if((_v).equals("tDr")) { 
 //BA.debugLineNum = 351;BA.debugLine="Return \"2\"";
if (true) return "2";
 }else if((_v).equals("KWM")) { 
 //BA.debugLineNum = 353;BA.debugLine="Return \"3\"";
if (true) return "3";
 }else if((_v).equals("E1E")) { 
 //BA.debugLineNum = 355;BA.debugLine="Return \"4\"";
if (true) return "4";
 }else if((_v).equals("JVA")) { 
 //BA.debugLineNum = 357;BA.debugLine="Return \"5\"";
if (true) return "5";
 }else if((_v).equals("HIS")) { 
 //BA.debugLineNum = 359;BA.debugLine="Return \"7\"";
if (true) return "7";
 }else if((_v).equals("JUb")) { 
 //BA.debugLineNum = 361;BA.debugLine="Return \"8\"";
if (true) return "8";
 }else if((_v).equals("at1")) { 
 //BA.debugLineNum = 363;BA.debugLine="Return \"9\"";
if (true) return "9";
 }else if((_v).equals("noe")) { 
 //BA.debugLineNum = 365;BA.debugLine="Return \"a\"";
if (true) return "a";
 }else if((_v).equals("fkH")) { 
 //BA.debugLineNum = 367;BA.debugLine="Return \"b\"";
if (true) return "b";
 }else if((_v).equals("S7g")) { 
 //BA.debugLineNum = 369;BA.debugLine="Return \"c\"";
if (true) return "c";
 }else if((_v).equals("06r")) { 
 //BA.debugLineNum = 371;BA.debugLine="Return \"d\"";
if (true) return "d";
 }else if((_v).equals("buW")) { 
 //BA.debugLineNum = 373;BA.debugLine="Return \"e\"";
if (true) return "e";
 }else if((_v).equals("zYE")) { 
 //BA.debugLineNum = 375;BA.debugLine="Return \"f\"";
if (true) return "f";
 }else if((_v).equals("jyd")) { 
 //BA.debugLineNum = 377;BA.debugLine="Return \"g\"";
if (true) return "g";
 }else if((_v).equals("rH4")) { 
 //BA.debugLineNum = 379;BA.debugLine="Return \"h\"";
if (true) return "h";
 }else if((_v).equals("TNu")) { 
 //BA.debugLineNum = 381;BA.debugLine="Return \"i\"";
if (true) return "i";
 }else if((_v).equals("MI7")) { 
 //BA.debugLineNum = 383;BA.debugLine="Return \"j\"";
if (true) return "j";
 }else if((_v).equals("er4")) { 
 //BA.debugLineNum = 385;BA.debugLine="Return \"k\"";
if (true) return "k";
 }else if((_v).equals("sGM")) { 
 //BA.debugLineNum = 387;BA.debugLine="Return \"l\"";
if (true) return "l";
 }else if((_v).equals("sgx")) { 
 //BA.debugLineNum = 389;BA.debugLine="Return \"m\"";
if (true) return "m";
 }else if((_v).equals("vBo")) { 
 //BA.debugLineNum = 391;BA.debugLine="Return \"n\"";
if (true) return "n";
 }else if((_v).equals("IAB")) { 
 //BA.debugLineNum = 393;BA.debugLine="Return \"o\"";
if (true) return "o";
 }else if((_v).equals("78G")) { 
 //BA.debugLineNum = 395;BA.debugLine="Return \"p\"";
if (true) return "p";
 }else if((_v).equals("0Yh")) { 
 //BA.debugLineNum = 397;BA.debugLine="Return \"q\"";
if (true) return "q";
 }else if((_v).equals("ub8")) { 
 //BA.debugLineNum = 399;BA.debugLine="Return \"r\"";
if (true) return "r";
 }else if((_v).equals("mxb")) { 
 //BA.debugLineNum = 401;BA.debugLine="Return \"s\"";
if (true) return "s";
 }else if((_v).equals("T3b")) { 
 //BA.debugLineNum = 403;BA.debugLine="Return \"t\"";
if (true) return "t";
 }else if((_v).equals("Kt4")) { 
 //BA.debugLineNum = 405;BA.debugLine="Return \"u\"";
if (true) return "u";
 }else if((_v).equals("apJ")) { 
 //BA.debugLineNum = 407;BA.debugLine="Return \"v\"";
if (true) return "v";
 }else if((_v).equals("p7w")) { 
 //BA.debugLineNum = 409;BA.debugLine="Return \"w\"";
if (true) return "w";
 }else if((_v).equals("5ph")) { 
 //BA.debugLineNum = 411;BA.debugLine="Return \"x\"";
if (true) return "x";
 }else if((_v).equals("KTy")) { 
 //BA.debugLineNum = 413;BA.debugLine="Return \"y\"";
if (true) return "y";
 }else if((_v).equals("pze")) { 
 //BA.debugLineNum = 415;BA.debugLine="Return \"z\"";
if (true) return "z";
 }else if((_v).equals("73i")) { 
 //BA.debugLineNum = 417;BA.debugLine="Return \"A\"";
if (true) return "A";
 }else if((_v).equals("qht")) { 
 //BA.debugLineNum = 419;BA.debugLine="Return \"B\"";
if (true) return "B";
 }else if((_v).equals("o6V")) { 
 //BA.debugLineNum = 421;BA.debugLine="Return \"C\"";
if (true) return "C";
 }else if((_v).equals("2vY")) { 
 //BA.debugLineNum = 423;BA.debugLine="Return \"D\"";
if (true) return "D";
 }else if((_v).equals("e6M")) { 
 //BA.debugLineNum = 425;BA.debugLine="Return \"E\"";
if (true) return "E";
 }else if((_v).equals("DUl")) { 
 //BA.debugLineNum = 427;BA.debugLine="Return \"F\"";
if (true) return "F";
 }else if((_v).equals("g5m")) { 
 //BA.debugLineNum = 429;BA.debugLine="Return \"G\"";
if (true) return "G";
 }else if((_v).equals("uHc")) { 
 //BA.debugLineNum = 431;BA.debugLine="Return \"H\"";
if (true) return "H";
 }else if((_v).equals("eM2")) { 
 //BA.debugLineNum = 433;BA.debugLine="Return \"I\"";
if (true) return "I";
 }else if((_v).equals("7LQ")) { 
 //BA.debugLineNum = 435;BA.debugLine="Return \"J\"";
if (true) return "J";
 }else if((_v).equals("CRM")) { 
 //BA.debugLineNum = 437;BA.debugLine="Return \"K\"";
if (true) return "K";
 }else if((_v).equals("tD1")) { 
 //BA.debugLineNum = 439;BA.debugLine="Return \"L\"";
if (true) return "L";
 }else if((_v).equals("TXe")) { 
 //BA.debugLineNum = 441;BA.debugLine="Return \"M\"";
if (true) return "M";
 }else if((_v).equals("MQD")) { 
 //BA.debugLineNum = 443;BA.debugLine="Return \"N\"";
if (true) return "N";
 }else if((_v).equals("IlG")) { 
 //BA.debugLineNum = 445;BA.debugLine="Return \"O\"";
if (true) return "O";
 }else if((_v).equals("Tu9")) { 
 //BA.debugLineNum = 447;BA.debugLine="Return \"P\"";
if (true) return "P";
 }else if((_v).equals("NKn")) { 
 //BA.debugLineNum = 449;BA.debugLine="Return \"Q\"";
if (true) return "Q";
 }else if((_v).equals("rTs")) { 
 //BA.debugLineNum = 451;BA.debugLine="Return \"R\"";
if (true) return "R";
 }else if((_v).equals("8EK")) { 
 //BA.debugLineNum = 453;BA.debugLine="Return \"S\"";
if (true) return "S";
 }else if((_v).equals("7fs")) { 
 //BA.debugLineNum = 455;BA.debugLine="Return \"T\"";
if (true) return "T";
 }else if((_v).equals("273")) { 
 //BA.debugLineNum = 457;BA.debugLine="Return \"U\"";
if (true) return "U";
 }else if((_v).equals("sLn")) { 
 //BA.debugLineNum = 459;BA.debugLine="Return \"V\"";
if (true) return "V";
 }else if((_v).equals("9Qm")) { 
 //BA.debugLineNum = 461;BA.debugLine="Return \"W\"";
if (true) return "W";
 }else if((_v).equals("3wd")) { 
 //BA.debugLineNum = 463;BA.debugLine="Return \"X\"";
if (true) return "X";
 }else if((_v).equals("I5i")) { 
 //BA.debugLineNum = 465;BA.debugLine="Return \"Y\"";
if (true) return "Y";
 }else if((_v).equals("gMM")) { 
 //BA.debugLineNum = 467;BA.debugLine="Return \"Z\"";
if (true) return "Z";
 }else if((_v).equals("uLg")) { 
 //BA.debugLineNum = 469;BA.debugLine="Return \"~\"";
if (true) return "~";
 }else if((_v).equals("5R3")) { 
 //BA.debugLineNum = 471;BA.debugLine="Return \"`\"";
if (true) return "`";
 }else if((_v).equals("R4f")) { 
 //BA.debugLineNum = 473;BA.debugLine="Return \"!\"";
if (true) return "!";
 }else if((_v).equals("pCd")) { 
 //BA.debugLineNum = 475;BA.debugLine="Return \"#\"";
if (true) return "#";
 }else if((_v).equals("n64")) { 
 //BA.debugLineNum = 477;BA.debugLine="Return \"$\"";
if (true) return "$";
 }else if((_v).equals("wGm")) { 
 //BA.debugLineNum = 479;BA.debugLine="Return \"%\"";
if (true) return "%";
 }else if((_v).equals("rC1")) { 
 //BA.debugLineNum = 481;BA.debugLine="Return \"^\"";
if (true) return "^";
 }else if((_v).equals("WKl")) { 
 //BA.debugLineNum = 483;BA.debugLine="Return \"&\"";
if (true) return "&";
 }else if((_v).equals("j20")) { 
 //BA.debugLineNum = 485;BA.debugLine="Return \"*\"";
if (true) return "*";
 }else if((_v).equals("vgg")) { 
 //BA.debugLineNum = 487;BA.debugLine="Return \"(\"";
if (true) return "(";
 }else if((_v).equals("MTS")) { 
 //BA.debugLineNum = 489;BA.debugLine="Return \")\"";
if (true) return ")";
 }else if((_v).equals("gpG")) { 
 //BA.debugLineNum = 491;BA.debugLine="Return \"_\"";
if (true) return "_";
 }else if((_v).equals("Rgj")) { 
 //BA.debugLineNum = 493;BA.debugLine="Return \"-\"";
if (true) return "-";
 }else if((_v).equals("Gdt")) { 
 //BA.debugLineNum = 495;BA.debugLine="Return \"=\"";
if (true) return "=";
 }else if((_v).equals("pEE")) { 
 //BA.debugLineNum = 497;BA.debugLine="Return \"+\"";
if (true) return "+";
 }else if((_v).equals("P2u")) { 
 //BA.debugLineNum = 499;BA.debugLine="Return \"/\"";
if (true) return "/";
 }else if((_v).equals("JYt")) { 
 //BA.debugLineNum = 501;BA.debugLine="Return \"?\"";
if (true) return "?";
 }else if((_v).equals("Bwu")) { 
 //BA.debugLineNum = 503;BA.debugLine="Return \"<\"";
if (true) return "<";
 }else if((_v).equals("IOM")) { 
 //BA.debugLineNum = 505;BA.debugLine="Return \">\"";
if (true) return ">";
 }else if((_v).equals("Mnk")) { 
 //BA.debugLineNum = 507;BA.debugLine="Return \".\"";
if (true) return ".";
 }else if((_v).equals("UV8")) { 
 //BA.debugLineNum = 509;BA.debugLine="Return \"{\"";
if (true) return "{";
 }else if((_v).equals("q4z")) { 
 //BA.debugLineNum = 511;BA.debugLine="Return \"}\"";
if (true) return "}";
 }else if((_v).equals("zcg")) { 
 //BA.debugLineNum = 513;BA.debugLine="Return \"[\"";
if (true) return "[";
 }else if((_v).equals("AMp")) { 
 //BA.debugLineNum = 515;BA.debugLine="Return \"]\"";
if (true) return "]";
 }else if((_v).equals("oLf")) { 
 //BA.debugLineNum = 517;BA.debugLine="Return \",\"";
if (true) return ",";
 }else if((_v).equals("Ukt")) { 
 //BA.debugLineNum = 519;BA.debugLine="Return \"ا\"";
if (true) return "ا";
 }else if((_v).equals("Kcw")) { 
 //BA.debugLineNum = 521;BA.debugLine="Return \"آ\"";
if (true) return "آ";
 }else if((_v).equals("pP0")) { 
 //BA.debugLineNum = 523;BA.debugLine="Return \"ب\"";
if (true) return "ب";
 }else if((_v).equals("jCw")) { 
 //BA.debugLineNum = 525;BA.debugLine="Return \"پ\"";
if (true) return "پ";
 }else if((_v).equals("TOG")) { 
 //BA.debugLineNum = 527;BA.debugLine="Return \"ت\"";
if (true) return "ت";
 }else if((_v).equals("onL")) { 
 //BA.debugLineNum = 529;BA.debugLine="Return \"س\"";
if (true) return "س";
 }else if((_v).equals("gDw")) { 
 //BA.debugLineNum = 531;BA.debugLine="Return \"ج\"";
if (true) return "ج";
 }else if((_v).equals("o2e")) { 
 //BA.debugLineNum = 533;BA.debugLine="Return \"چ\"";
if (true) return "چ";
 }else if((_v).equals("725")) { 
 //BA.debugLineNum = 535;BA.debugLine="Return \"ح\"";
if (true) return "ح";
 }else if((_v).equals("rMT")) { 
 //BA.debugLineNum = 537;BA.debugLine="Return \"خ\"";
if (true) return "خ";
 }else if((_v).equals("yHF")) { 
 //BA.debugLineNum = 539;BA.debugLine="Return \"د\"";
if (true) return "د";
 }else if((_v).equals("aew")) { 
 //BA.debugLineNum = 541;BA.debugLine="Return \"ذ\"";
if (true) return "ذ";
 }else if((_v).equals("gvN")) { 
 //BA.debugLineNum = 543;BA.debugLine="Return \"ر\"";
if (true) return "ر";
 }else if((_v).equals("3BR")) { 
 //BA.debugLineNum = 545;BA.debugLine="Return \"ز\"";
if (true) return "ز";
 }else if((_v).equals("y33")) { 
 //BA.debugLineNum = 547;BA.debugLine="Return \"ژ\"";
if (true) return "ژ";
 }else if((_v).equals("FAr")) { 
 //BA.debugLineNum = 549;BA.debugLine="Return \"س\"";
if (true) return "س";
 }else if((_v).equals("A6T")) { 
 //BA.debugLineNum = 551;BA.debugLine="Return \"ش\"";
if (true) return "ش";
 }else if((_v).equals("vVr")) { 
 //BA.debugLineNum = 553;BA.debugLine="Return \"ص\"";
if (true) return "ص";
 }else if((_v).equals("Ts3")) { 
 //BA.debugLineNum = 555;BA.debugLine="Return \"ض\"";
if (true) return "ض";
 }else if((_v).equals("G0e")) { 
 //BA.debugLineNum = 557;BA.debugLine="Return \"ط\"";
if (true) return "ط";
 }else if((_v).equals("7yE")) { 
 //BA.debugLineNum = 559;BA.debugLine="Return \"ظ\"";
if (true) return "ظ";
 }else if((_v).equals("fEV")) { 
 //BA.debugLineNum = 561;BA.debugLine="Return \"ع\"";
if (true) return "ع";
 }else if((_v).equals("ay0")) { 
 //BA.debugLineNum = 563;BA.debugLine="Return \"غ\"";
if (true) return "غ";
 }else if((_v).equals("HMx")) { 
 //BA.debugLineNum = 565;BA.debugLine="Return \"ف\"";
if (true) return "ف";
 }else if((_v).equals("fk8")) { 
 //BA.debugLineNum = 567;BA.debugLine="Return \"ق\"";
if (true) return "ق";
 }else if((_v).equals("kgq")) { 
 //BA.debugLineNum = 569;BA.debugLine="Return \"ک\"";
if (true) return "ک";
 }else if((_v).equals("2GE")) { 
 //BA.debugLineNum = 571;BA.debugLine="Return \"گ\"";
if (true) return "گ";
 }else if((_v).equals("wbO")) { 
 //BA.debugLineNum = 573;BA.debugLine="Return \"ل\"";
if (true) return "ل";
 }else if((_v).equals("P16")) { 
 //BA.debugLineNum = 575;BA.debugLine="Return \"م\"";
if (true) return "م";
 }else if((_v).equals("Uy5")) { 
 //BA.debugLineNum = 577;BA.debugLine="Return \"ن\"";
if (true) return "ن";
 }else if((_v).equals("2n4")) { 
 //BA.debugLineNum = 579;BA.debugLine="Return \"و\"";
if (true) return "و";
 }else if((_v).equals("T2p")) { 
 //BA.debugLineNum = 581;BA.debugLine="Return \"ه\"";
if (true) return "ه";
 }else if((_v).equals("T5q")) { 
 //BA.debugLineNum = 583;BA.debugLine="Return \"ی\"";
if (true) return "ی";
 }else if((_v).equals("R2G")) { 
 //BA.debugLineNum = 585;BA.debugLine="Return \"ء\"";
if (true) return "ء";
 }else if((_v).equals("8lO")) { 
 //BA.debugLineNum = 587;BA.debugLine="Return \"ئ\"";
if (true) return "ئ";
 }else {
 //BA.debugLineNum = 589;BA.debugLine="Return v.Substring(v.Length - 1)";
if (true) return _v.substring((int) (_v.length()-1));
 };
 //BA.debugLineNum = 592;BA.debugLine="End Sub";
return "";
}
public String  _sina_enc(String _v) throws Exception{
 //BA.debugLineNum = 64;BA.debugLine="Public Sub sina_ENC(v As String) As String";
 //BA.debugLineNum = 66;BA.debugLine="If v=\"0\" Then";
if ((_v).equals("0")) { 
 //BA.debugLineNum = 67;BA.debugLine="Return \"mbW\"";
if (true) return "mbW";
 }else if((_v).equals("1")) { 
 //BA.debugLineNum = 69;BA.debugLine="Return \"ndn\"";
if (true) return "ndn";
 }else if((_v).equals("2")) { 
 //BA.debugLineNum = 71;BA.debugLine="Return \"tDr\"";
if (true) return "tDr";
 }else if((_v).equals("3")) { 
 //BA.debugLineNum = 73;BA.debugLine="Return \"KWM\"";
if (true) return "KWM";
 }else if((_v).equals("4")) { 
 //BA.debugLineNum = 75;BA.debugLine="Return \"E1E\"";
if (true) return "E1E";
 }else if((_v).equals("5")) { 
 //BA.debugLineNum = 77;BA.debugLine="Return \"JVA\"";
if (true) return "JVA";
 }else if((_v).equals("7")) { 
 //BA.debugLineNum = 79;BA.debugLine="Return \"HIS\"";
if (true) return "HIS";
 }else if((_v).equals("8")) { 
 //BA.debugLineNum = 81;BA.debugLine="Return \"JUb\"";
if (true) return "JUb";
 }else if((_v).equals("9")) { 
 //BA.debugLineNum = 83;BA.debugLine="Return \"at1\"";
if (true) return "at1";
 }else if((_v).equals("a")) { 
 //BA.debugLineNum = 85;BA.debugLine="Return \"noe\"";
if (true) return "noe";
 }else if((_v).equals("b")) { 
 //BA.debugLineNum = 87;BA.debugLine="Return \"fkH\"";
if (true) return "fkH";
 }else if((_v).equals("c")) { 
 //BA.debugLineNum = 89;BA.debugLine="Return \"S7g\"";
if (true) return "S7g";
 }else if((_v).equals("d")) { 
 //BA.debugLineNum = 91;BA.debugLine="Return \"06r\"";
if (true) return "06r";
 }else if((_v).equals("e")) { 
 //BA.debugLineNum = 93;BA.debugLine="Return \"buW\"";
if (true) return "buW";
 }else if((_v).equals("f")) { 
 //BA.debugLineNum = 95;BA.debugLine="Return \"zYE\"";
if (true) return "zYE";
 }else if((_v).equals("g")) { 
 //BA.debugLineNum = 97;BA.debugLine="Return \"jyd\"";
if (true) return "jyd";
 }else if((_v).equals("h")) { 
 //BA.debugLineNum = 99;BA.debugLine="Return \"rH4\"";
if (true) return "rH4";
 }else if((_v).equals("i")) { 
 //BA.debugLineNum = 101;BA.debugLine="Return \"TNu\"";
if (true) return "TNu";
 }else if((_v).equals("j")) { 
 //BA.debugLineNum = 103;BA.debugLine="Return \"MI7\"";
if (true) return "MI7";
 }else if((_v).equals("k")) { 
 //BA.debugLineNum = 105;BA.debugLine="Return \"er4\"";
if (true) return "er4";
 }else if((_v).equals("l")) { 
 //BA.debugLineNum = 107;BA.debugLine="Return \"sGM\"";
if (true) return "sGM";
 }else if((_v).equals("m")) { 
 //BA.debugLineNum = 109;BA.debugLine="Return \"sgx\"";
if (true) return "sgx";
 }else if((_v).equals("n")) { 
 //BA.debugLineNum = 111;BA.debugLine="Return \"vBo\"";
if (true) return "vBo";
 }else if((_v).equals("o")) { 
 //BA.debugLineNum = 113;BA.debugLine="Return \"IAB\"";
if (true) return "IAB";
 }else if((_v).equals("p")) { 
 //BA.debugLineNum = 115;BA.debugLine="Return \"78G\"";
if (true) return "78G";
 }else if((_v).equals("q")) { 
 //BA.debugLineNum = 117;BA.debugLine="Return \"0Yh\"";
if (true) return "0Yh";
 }else if((_v).equals("r")) { 
 //BA.debugLineNum = 119;BA.debugLine="Return \"ub8\"";
if (true) return "ub8";
 }else if((_v).equals("s")) { 
 //BA.debugLineNum = 121;BA.debugLine="Return \"mxb\"";
if (true) return "mxb";
 }else if((_v).equals("t")) { 
 //BA.debugLineNum = 123;BA.debugLine="Return \"T3b\"";
if (true) return "T3b";
 }else if((_v).equals("u")) { 
 //BA.debugLineNum = 125;BA.debugLine="Return \"Kt4\"";
if (true) return "Kt4";
 }else if((_v).equals("v")) { 
 //BA.debugLineNum = 127;BA.debugLine="Return \"apJ\"";
if (true) return "apJ";
 }else if((_v).equals("w")) { 
 //BA.debugLineNum = 129;BA.debugLine="Return \"p7w\"";
if (true) return "p7w";
 }else if((_v).equals("x")) { 
 //BA.debugLineNum = 131;BA.debugLine="Return \"5ph\"";
if (true) return "5ph";
 }else if((_v).equals("y")) { 
 //BA.debugLineNum = 133;BA.debugLine="Return \"KTy\"";
if (true) return "KTy";
 }else if((_v).equals("z")) { 
 //BA.debugLineNum = 135;BA.debugLine="Return \"pze\"";
if (true) return "pze";
 }else if((_v).equals("A")) { 
 //BA.debugLineNum = 137;BA.debugLine="Return \"73i\"";
if (true) return "73i";
 }else if((_v).equals("B")) { 
 //BA.debugLineNum = 139;BA.debugLine="Return \"qht\"";
if (true) return "qht";
 }else if((_v).equals("C")) { 
 //BA.debugLineNum = 141;BA.debugLine="Return \"o6V\"";
if (true) return "o6V";
 }else if((_v).equals("D")) { 
 //BA.debugLineNum = 143;BA.debugLine="Return \"2vY\"";
if (true) return "2vY";
 }else if((_v).equals("E")) { 
 //BA.debugLineNum = 145;BA.debugLine="Return \"e6M\"";
if (true) return "e6M";
 }else if((_v).equals("F")) { 
 //BA.debugLineNum = 147;BA.debugLine="Return \"DUl\"";
if (true) return "DUl";
 }else if((_v).equals("G")) { 
 //BA.debugLineNum = 149;BA.debugLine="Return \"g5m\"";
if (true) return "g5m";
 }else if((_v).equals("H")) { 
 //BA.debugLineNum = 151;BA.debugLine="Return \"uHc\"";
if (true) return "uHc";
 }else if((_v).equals("I")) { 
 //BA.debugLineNum = 153;BA.debugLine="Return \"eM2\"";
if (true) return "eM2";
 }else if((_v).equals("J")) { 
 //BA.debugLineNum = 155;BA.debugLine="Return \"7LQ\"";
if (true) return "7LQ";
 }else if((_v).equals("K")) { 
 //BA.debugLineNum = 157;BA.debugLine="Return \"CRM\"";
if (true) return "CRM";
 }else if((_v).equals("L")) { 
 //BA.debugLineNum = 159;BA.debugLine="Return \"tD1\"";
if (true) return "tD1";
 }else if((_v).equals("M")) { 
 //BA.debugLineNum = 161;BA.debugLine="Return \"TXe\"";
if (true) return "TXe";
 }else if((_v).equals("N")) { 
 //BA.debugLineNum = 163;BA.debugLine="Return \"MQD\"";
if (true) return "MQD";
 }else if((_v).equals("O")) { 
 //BA.debugLineNum = 165;BA.debugLine="Return \"IlG\"";
if (true) return "IlG";
 }else if((_v).equals("P")) { 
 //BA.debugLineNum = 167;BA.debugLine="Return \"Tu9\"";
if (true) return "Tu9";
 }else if((_v).equals("Q")) { 
 //BA.debugLineNum = 169;BA.debugLine="Return \"NKn\"";
if (true) return "NKn";
 }else if((_v).equals("R")) { 
 //BA.debugLineNum = 171;BA.debugLine="Return \"rTs\"";
if (true) return "rTs";
 }else if((_v).equals("S")) { 
 //BA.debugLineNum = 173;BA.debugLine="Return \"8EK\"";
if (true) return "8EK";
 }else if((_v).equals("T")) { 
 //BA.debugLineNum = 175;BA.debugLine="Return \"7fs\"";
if (true) return "7fs";
 }else if((_v).equals("U")) { 
 //BA.debugLineNum = 177;BA.debugLine="Return \"273\"";
if (true) return "273";
 }else if((_v).equals("V")) { 
 //BA.debugLineNum = 179;BA.debugLine="Return \"sLn\"";
if (true) return "sLn";
 }else if((_v).equals("W")) { 
 //BA.debugLineNum = 181;BA.debugLine="Return \"9Qm\"";
if (true) return "9Qm";
 }else if((_v).equals("X")) { 
 //BA.debugLineNum = 183;BA.debugLine="Return \"3wd\"";
if (true) return "3wd";
 }else if((_v).equals("Y")) { 
 //BA.debugLineNum = 185;BA.debugLine="Return \"I5i\"";
if (true) return "I5i";
 }else if((_v).equals("Z")) { 
 //BA.debugLineNum = 187;BA.debugLine="Return \"gMM\"";
if (true) return "gMM";
 }else if((_v).equals("~")) { 
 //BA.debugLineNum = 189;BA.debugLine="Return \"uLg\"";
if (true) return "uLg";
 }else if((_v).equals("`")) { 
 //BA.debugLineNum = 191;BA.debugLine="Return \"5R3\"";
if (true) return "5R3";
 }else if((_v).equals("!")) { 
 //BA.debugLineNum = 193;BA.debugLine="Return \"R4f\"";
if (true) return "R4f";
 }else if((_v).equals("#")) { 
 //BA.debugLineNum = 195;BA.debugLine="Return \"pCd\"";
if (true) return "pCd";
 }else if((_v).equals("$")) { 
 //BA.debugLineNum = 197;BA.debugLine="Return \"n64\"";
if (true) return "n64";
 }else if((_v).equals("%")) { 
 //BA.debugLineNum = 199;BA.debugLine="Return \"wGm\"";
if (true) return "wGm";
 }else if((_v).equals("^")) { 
 //BA.debugLineNum = 201;BA.debugLine="Return \"rC1\"";
if (true) return "rC1";
 }else if((_v).equals("&")) { 
 //BA.debugLineNum = 203;BA.debugLine="Return \"WKl\"";
if (true) return "WKl";
 }else if((_v).equals("*")) { 
 //BA.debugLineNum = 205;BA.debugLine="Return \"j20\"";
if (true) return "j20";
 }else if((_v).equals("(")) { 
 //BA.debugLineNum = 207;BA.debugLine="Return \"vgg\"";
if (true) return "vgg";
 }else if((_v).equals(")")) { 
 //BA.debugLineNum = 209;BA.debugLine="Return \"MTS\"";
if (true) return "MTS";
 }else if((_v).equals("_")) { 
 //BA.debugLineNum = 211;BA.debugLine="Return \"gpG\"";
if (true) return "gpG";
 }else if((_v).equals("-")) { 
 //BA.debugLineNum = 213;BA.debugLine="Return \"Rgj\"";
if (true) return "Rgj";
 }else if((_v).equals("=")) { 
 //BA.debugLineNum = 215;BA.debugLine="Return \"Gdt\"";
if (true) return "Gdt";
 }else if((_v).equals("+")) { 
 //BA.debugLineNum = 217;BA.debugLine="Return \"pEE\"";
if (true) return "pEE";
 }else if((_v).equals("/")) { 
 //BA.debugLineNum = 219;BA.debugLine="Return \"P2u\"";
if (true) return "P2u";
 }else if((_v).equals("?")) { 
 //BA.debugLineNum = 221;BA.debugLine="Return \"JYt\"";
if (true) return "JYt";
 }else if((_v).equals("<")) { 
 //BA.debugLineNum = 223;BA.debugLine="Return \"Bwu\"";
if (true) return "Bwu";
 }else if((_v).equals(">")) { 
 //BA.debugLineNum = 225;BA.debugLine="Return \"IOM\"";
if (true) return "IOM";
 }else if((_v).equals(".")) { 
 //BA.debugLineNum = 227;BA.debugLine="Return \"Mnk\"";
if (true) return "Mnk";
 }else if((_v).equals("{")) { 
 //BA.debugLineNum = 229;BA.debugLine="Return \"UV8\"";
if (true) return "UV8";
 }else if((_v).equals("}")) { 
 //BA.debugLineNum = 231;BA.debugLine="Return \"q4z\"";
if (true) return "q4z";
 }else if((_v).equals("[")) { 
 //BA.debugLineNum = 233;BA.debugLine="Return \"zcg\"";
if (true) return "zcg";
 }else if((_v).equals("]")) { 
 //BA.debugLineNum = 235;BA.debugLine="Return \"AMp\"";
if (true) return "AMp";
 }else if((_v).equals(",")) { 
 //BA.debugLineNum = 237;BA.debugLine="Return \"oLf\"";
if (true) return "oLf";
 }else if((_v).equals("ا")) { 
 //BA.debugLineNum = 239;BA.debugLine="Return \"Ukt\"";
if (true) return "Ukt";
 }else if((_v).equals("آ")) { 
 //BA.debugLineNum = 241;BA.debugLine="Return \"Kcw\"";
if (true) return "Kcw";
 }else if((_v).equals("ب")) { 
 //BA.debugLineNum = 243;BA.debugLine="Return \"pP0\"";
if (true) return "pP0";
 }else if((_v).equals("پ")) { 
 //BA.debugLineNum = 245;BA.debugLine="Return \"jCw\"";
if (true) return "jCw";
 }else if((_v).equals("ت")) { 
 //BA.debugLineNum = 247;BA.debugLine="Return \"TOG\"";
if (true) return "TOG";
 }else if((_v).equals("س")) { 
 //BA.debugLineNum = 249;BA.debugLine="Return \"onL\"";
if (true) return "onL";
 }else if((_v).equals("ج")) { 
 //BA.debugLineNum = 251;BA.debugLine="Return \"gDw\"";
if (true) return "gDw";
 }else if((_v).equals("چ")) { 
 //BA.debugLineNum = 253;BA.debugLine="Return \"o2e\"";
if (true) return "o2e";
 }else if((_v).equals("ح")) { 
 //BA.debugLineNum = 255;BA.debugLine="Return \"725\"";
if (true) return "725";
 }else if((_v).equals("خ")) { 
 //BA.debugLineNum = 257;BA.debugLine="Return \"rMT\"";
if (true) return "rMT";
 }else if((_v).equals("د")) { 
 //BA.debugLineNum = 259;BA.debugLine="Return \"yHF\"";
if (true) return "yHF";
 }else if((_v).equals("ذ")) { 
 //BA.debugLineNum = 261;BA.debugLine="Return \"aew\"";
if (true) return "aew";
 }else if((_v).equals("ر")) { 
 //BA.debugLineNum = 263;BA.debugLine="Return \"gvN\"";
if (true) return "gvN";
 }else if((_v).equals("ز")) { 
 //BA.debugLineNum = 265;BA.debugLine="Return \"3BR\"";
if (true) return "3BR";
 }else if((_v).equals("ژ")) { 
 //BA.debugLineNum = 267;BA.debugLine="Return \"y33\"";
if (true) return "y33";
 }else if((_v).equals("س")) { 
 //BA.debugLineNum = 269;BA.debugLine="Return \"FAr\"";
if (true) return "FAr";
 }else if((_v).equals("ش")) { 
 //BA.debugLineNum = 271;BA.debugLine="Return \"A6T\"";
if (true) return "A6T";
 }else if((_v).equals("ص")) { 
 //BA.debugLineNum = 273;BA.debugLine="Return \"vVr\"";
if (true) return "vVr";
 }else if((_v).equals("ض")) { 
 //BA.debugLineNum = 275;BA.debugLine="Return \"Ts3\"";
if (true) return "Ts3";
 }else if((_v).equals("ط")) { 
 //BA.debugLineNum = 277;BA.debugLine="Return \"G0e\"";
if (true) return "G0e";
 }else if((_v).equals("ظ")) { 
 //BA.debugLineNum = 279;BA.debugLine="Return \"7yE\"";
if (true) return "7yE";
 }else if((_v).equals("ع")) { 
 //BA.debugLineNum = 281;BA.debugLine="Return \"fEV\"";
if (true) return "fEV";
 }else if((_v).equals("غ")) { 
 //BA.debugLineNum = 283;BA.debugLine="Return \"ay0\"";
if (true) return "ay0";
 }else if((_v).equals("ف")) { 
 //BA.debugLineNum = 285;BA.debugLine="Return \"HMx\"";
if (true) return "HMx";
 }else if((_v).equals("ق")) { 
 //BA.debugLineNum = 287;BA.debugLine="Return \"fk8\"";
if (true) return "fk8";
 }else if((_v).equals("ک")) { 
 //BA.debugLineNum = 289;BA.debugLine="Return \"kgq\"";
if (true) return "kgq";
 }else if((_v).equals("گ")) { 
 //BA.debugLineNum = 291;BA.debugLine="Return \"2GE\"";
if (true) return "2GE";
 }else if((_v).equals("ل")) { 
 //BA.debugLineNum = 293;BA.debugLine="Return \"wbO\"";
if (true) return "wbO";
 }else if((_v).equals("م")) { 
 //BA.debugLineNum = 295;BA.debugLine="Return \"P16\"";
if (true) return "P16";
 }else if((_v).equals("ن")) { 
 //BA.debugLineNum = 297;BA.debugLine="Return \"Uy5\"";
if (true) return "Uy5";
 }else if((_v).equals("و")) { 
 //BA.debugLineNum = 299;BA.debugLine="Return \"2n4\"";
if (true) return "2n4";
 }else if((_v).equals("ه")) { 
 //BA.debugLineNum = 301;BA.debugLine="Return \"T2p\"";
if (true) return "T2p";
 }else if((_v).equals("ی")) { 
 //BA.debugLineNum = 303;BA.debugLine="Return \"T5q\"";
if (true) return "T5q";
 }else if((_v).equals("ء")) { 
 //BA.debugLineNum = 305;BA.debugLine="Return \"R2G\"";
if (true) return "R2G";
 }else if((_v).equals("ئ")) { 
 //BA.debugLineNum = 307;BA.debugLine="Return \"8lO\"";
if (true) return "8lO";
 }else {
 //BA.debugLineNum = 309;BA.debugLine="Return v & v & v";
if (true) return _v+_v+_v;
 };
 //BA.debugLineNum = 311;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
