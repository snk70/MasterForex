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
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private btn_Email As Button
	Private btn_teleg As Button
	Private btn_ava As Button
	Private btn_whatsapp As Button
	Private Panel1 As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("contact_us")
	
		#region ست کردن سایز ویو ها	
	sizeviewV3.SetPX(Activity.Width,Activity.Height,240,320)
	sizeviewV3.SetPastScreenSizetoInche(3.333,4.444)
	sizeviewV3.SetSizeViews(Panel1,0,0,240,320,0)
	sizeviewV3.SetSize_btn(btn_Email,40,20,200,50,15)
	sizeviewV3.SetSize_btn(btn_teleg,100,20,200,50,15)
	sizeviewV3.SetSize_btn(btn_whatsapp,160,20,200,50,15)
	sizeviewV3.SetSize_btn(btn_ava,220,20,200,50,15)
		#end region
		
	If config_app_module.selcted_language="Fa" Then
		Activity.Title="تماس با ما"
	Else If config_app_module.selcted_language="En" Then
		Activity.Title="Contact us"
	Else If config_app_module.selcted_language="Ar" Then
		Activity.Title="إتصل بنا"
	End If
		
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub



Sub btn_Email_Click
	StartActivity(email_ACT)
	Activity.Finish	
End Sub

Sub btn_teleg_Click
	Dim tel_url As Intent
	tel_url.Initialize(tel_url.ACTION_VIEW,"https://t.me/Masterfxshahin")
	StartActivity(tel_url)
End Sub

Sub btn_ava_Click
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
'	ava_url.Initialize(ava_url.ACTION_VIEW,"http://cabin.pcmfa.net/fa/ref/NTQzMjExODczODc4Ng==")
'	StartActivity(ava_url)
End Sub

Sub btn_whatsapp_Click
	Dim what_url As Intent
	what_url.Initialize(what_url.ACTION_VIEW,"https://chat.whatsapp.com/1cEkQqFuOg0H7tTmthZllk")
	StartActivity(what_url)
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode=KeyCodes.KEYCODE_BACK Then
		StartActivity(Main)
		Activity.Finish
	End If
	Return True
End Sub