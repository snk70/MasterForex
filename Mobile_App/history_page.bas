B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=6.8
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	Private btn_mql5 As Button
	Private btn_insta As Button
	Private btn_master_forex_shahin As Button
	Private btn_teleg As Button
	Private Panel1 As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("history_page")
	
#region Set Sizes
	sizeviewV3.SetPX(Activity.Width,Activity.Height,240,320)
	sizeviewV3.SetPastScreenSizetoInche(3.333,4.444)
	sizeviewV3.SetSizeViews(Panel1,0,0,240,320,0)
	sizeviewV3.SetSize_btn(btn_mql5,40,20,200,50,15)
	sizeviewV3.SetSize_btn(btn_insta,100,20,200,50,15)
	sizeviewV3.SetSize_btn(btn_master_forex_shahin,160,20,200,50,15)
#end region
	
	
	If config_app_module.selcted_language="Fa" Then
		Activity.Title="رزومه"
	Else If config_app_module.selcted_language="En" Then
		Activity.Title="Contact us"
	Else If config_app_module.selcted_language="Ar" Then
		Activity.Title="سيرة الذاتية"
	End If
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub btn_mql5_Click
	Dim mql_url As Intent
	mql_url.Initialize(mql_url.ACTION_VIEW,"https://www.mql5.com/en/users/sh.big")
	StartActivity(mql_url)
End Sub

Sub btn_insta_Click
	Dim instagram_url As Intent
	instagram_url.Initialize(instagram_url.ACTION_VIEW,"https://www.instagram.com/master_forex_shahin/")
	StartActivity(instagram_url)
End Sub

Sub btn_master_forex_shahin_Click
	Dim mql_url As Intent
	mql_url.Initialize(mql_url.ACTION_VIEW,"https://www.mql5.com/en/users/sh.big")
	StartActivity(mql_url)
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode=KeyCodes.KEYCODE_BACK Then
		StartActivity(Main)
		Activity.Finish
	End If
	Return True
End Sub