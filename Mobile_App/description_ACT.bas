B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=6.8
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	Private wb_description As WebView
	Private btn_countinue As Button
	Private lbl_ava As Label
	Dim lbl_tm As Timer
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("description_page")
	'	wb_description.LoadUrl("file:///android_asset/persion_description.html")

	#region ست کردن سایز ویو ها	
	sizeviewV3.SetPX(Activity.Width,Activity.Height,240,320)
	sizeviewV3.SetPastScreenSizetoInche(3.333,4.444)
	sizeviewV3.SetSizeViews(wb_description,0,0,240,220,0)
	sizeviewV3.SetSize_lbl_Views(lbl_ava,220,5,230,40,15)
	sizeviewV3.SetSize_btn(btn_countinue,260,10,100,40,15)
	#end region

	If config_app_module.selcted_language="Fa" Then
		lbl_ava.Text=""" جهت انجام معاملات از این لینک ، وارد شوید """
		wb_description.LoadUrl("file:///android_asset/persion_description.html")
		btn_countinue.Text="ادامه"
		btn_countinue.Left=Activity.Width-btn_countinue.Width-btn_countinue.Left
	Else If config_app_module.selcted_language="En" Then
		lbl_ava.Text="""Sign in to order transactions from this link"""
		wb_description.LoadUrl("file:///android_asset/english_description.html")
		btn_countinue.Text="Continue"
	Else If config_app_module.selcted_language="Ar" Then
		lbl_ava.Text="""تسجيل الدخول لطلب المعاملات من هذا الرابط"""
		wb_description.LoadUrl("file:///android_asset/arabic_description.html")
		btn_countinue.Text="استمر"
		btn_countinue.Left=Activity.Width-btn_countinue.Width-btn_countinue.Left
	End If



	
	'	lbl_ava.TextColor=Colors.Red
	'	lbl_ava.Color=Colors.Blue

End Sub

Sub Activity_Resume
	lbl_tm.Initialize("tmr",700)
	lbl_tm.Enabled=True
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	lbl_tm.Enabled=False
End Sub


Sub btn_countinue_Click
	StartActivity(sign_up_ACT)
	Activity.Finish
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode=KeyCodes.KEYCODE_BACK Then
		StartActivity(Main)
		Activity.Finish
	End If
	Return True
End Sub


Sub lbl_ava_Click
	Dim ava_url As Intent
	
	If config_app_module.selcted_language="Fa" Then
		ava_url.Initialize(ava_url.ACTION_VIEW,"http://cabin.fxpcm.net/fa/ref/NTQzMjExODczODc4Ng==")
		StartActivity(ava_url)
	Else If config_app_module.selcted_language="En" Then
		ava_url.Initialize(ava_url.ACTION_VIEW,"http://cabin.fxpcm.net/en/ref/NTQzMjExODczODc4Ng==")
		StartActivity(ava_url)
	Else If config_app_module.selcted_language="Ar" Then
		ava_url.Initialize(ava_url.ACTION_VIEW,"http://cabin.fxpcm.net/ar/ref/NTQzMjExODczODc4Ng==")
		StartActivity(ava_url)
	End If
End Sub


Sub tmr_Tick
	If lbl_ava.Visible=True Then
		lbl_ava.Visible=False
	Else
		lbl_ava.Visible=True
	End If
End Sub