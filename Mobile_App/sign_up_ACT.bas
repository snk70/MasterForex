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
Dim ht_sp As HttpJob
	Private edt_email As EditText
	Private edt_pass As EditText
	Private edt_retype_pass As EditText
	Private edt_webmony As EditText
	Private edt_Phone_Number As EditText
	Private btn_accept As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("sign_up_page")

		#region Set Sizes
	'Size :
	sizeviewV3.SetPX(Activity.Width,Activity.Height,240,320)
	sizeviewV3.SetPastScreenSizetoInche(3.333,4.444)
	sizeviewV3.SetSize_txt(edt_email,10,10,220,40,15)
	sizeviewV3.SetSize_txt(edt_pass,60,10,220,40,15)
	sizeviewV3.SetSize_txt(edt_retype_pass,110,10,220,40,15)
	sizeviewV3.SetSize_txt(edt_webmony,160,10,220,40,15)
	sizeviewV3.SetSize_txt(edt_Phone_Number,210,10,220,40,15)
	sizeviewV3.SetSize_btn(btn_accept,260,10,90,40,15)

	edt_email.InputType=524288

	'Design :

	Set_View_BackGround_and_Border.Set_Initialize(480,640,Activity.Width,Activity.Height)
	Set_View_BackGround_and_Border.Set_View_Border_BG(edt_email,Colors.White,Colors.Black,1.5,5)
	Set_View_BackGround_and_Border.Set_View_Border_BG(edt_pass,Colors.White,Colors.Black,1.5,5)
	Set_View_BackGround_and_Border.Set_View_Border_BG(edt_Phone_Number,Colors.White,Colors.Black,1,5)
	Set_View_BackGround_and_Border.Set_View_Border_BG(edt_retype_pass,Colors.White,Colors.Black,1,5)
	Set_View_BackGround_and_Border.Set_View_Border_BG(edt_webmony,Colors.White,Colors.Black,1.5,5)
	Set_View_BackGround_and_Border.Set_View_Border_BG(btn_accept,Colors.White,Colors.Black,1.5,5)

		#end region
		
	If config_app_module.selcted_language="Fa" Then
		#region فارسی
		edt_email.Gravity=21
		edt_pass.Gravity=21
		edt_retype_pass.Gravity=21
		edt_webmony.Gravity=21
		edt_Phone_Number.Gravity=21
		
		Activity.Title="ثبت نام"
		edt_email.Hint="نشانی ایمیل"
		edt_pass.Hint="کلمه عبور"
		edt_retype_pass.Hint="تکرار کلمه عبور"
		edt_webmony.Hint="شناسه وب مانی"
		edt_Phone_Number.Hint="تلفن همراه"
		btn_accept.Text="تأیید"
		
		btn_accept.Left=Activity.Width-btn_accept.Width-btn_accept.Left
		
		#End Region
	Else If config_app_module.selcted_language="En" Then
		#region انگلیسی
		
		#End Region
	Else If config_app_module.selcted_language="Ar" Then
		#region عربی
		edt_email.Gravity=21
		edt_pass.Gravity=21
		edt_retype_pass.Gravity=21
		edt_webmony.Gravity=21
		edt_Phone_Number.Gravity=21
		
		Activity.Title="سجل"
		edt_email.Hint="عنوان البريد الإلكتروني"
		edt_pass.Hint="كلمه السر"
		edt_retype_pass.Hint="أعد إدخال كلمة السر"
		edt_webmony.Hint="رقم الوب مانی"
		edt_Phone_Number.Hint="رقم الهاتف"
		btn_accept.Text="التأكيد"
		
		btn_accept.Left=Activity.Width-btn_accept.Width-btn_accept.Left
		
		#End Region
	End If
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode=KeyCodes.KEYCODE_BACK Then
		StartActivity(description_ACT)
		Activity.Finish
	End If
	Return True
End Sub



Sub btn_accept_Click
	Dim sc As sec_Class_1
	
	If edt_email.Text="" Or edt_pass.Text="" Or edt_Phone_Number.Text="" Or edt_retype_pass.Text="" Or edt_webmony.Text="" Then
		
		If config_app_module.selcted_language="Fa" Then
			Msgbox("لطفا موارد مورد نیاز را وارد کنید","تکمیل موارد")
		Else If config_app_module.selcted_language="En" Then
			Msgbox("Please enter the required items","Enter items")
		Else If config_app_module.selcted_language="Ar" Then
			Msgbox("يرجى إدخال العناصر المطلوبة","أدخل العناصر")
		End If

	Else
		
		If edt_pass.Text=edt_retype_pass.Text Then
			

			If Regular_Validations.Email_validation(edt_email.Text) Then
				
				
				If config_app_module.selcted_language="Fa" Then
					#region فارسی
					ProgressDialogShow2("لطفا کمی صبر کنید ...",False)
					#End Region
				Else If config_app_module.selcted_language="En" Then
					#region انگلیسی
					ProgressDialogShow2("Please wait ...",False)
					#End Region
				Else If config_app_module.selcted_language="Ar" Then
					#region عربی
					ProgressDialogShow2("أرجو الإنتظار ...",False)
					#End Region
				End If
	
	
				ht_sp.Initialize("sgnp",Me)
				'	ht_sp.PostString(config_app_module.sgn_URL,"ma="&sc.Encryption(edt_email.Text)&"&ps="&sc.Encryption(edt_pass.Text)&"&lcs="&sc.Encryption(edt_webmony.Text)&"&phn="&sc.Encryption(edt_Phone_Number.Text)&"&potgw52894fo_dkgnndfg_gtkrtkjtrldrt="&sc.Encryption("dtpognerregjwerg")&"&sina_rigergbwlergwer="&sc.Encryption("wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe")&"&ya_heidar="&sc.Encryption("sdgsergwer_313_asdfkakefkqwelfqwef34875234589"))
				ht_sp.PostString(config_app_module.sgn_URL,"ma="&sc.Encryption(edt_email.Text)&"&ps="&sc.Encryption(edt_pass.Text)&"&lcs="&sc.Encryption(edt_webmony.Text)&"&phn="&sc.Encryption(edt_Phone_Number.Text)&"&potgw52894fo_dkgnndfg_gtkrtkjtrldrt=dtpognerregjwerg&sina_rigergbwlergwer=wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe&ya_heidar=sdgsergwer_313_asdfkakefkqwelfqwef34875234589")

			Else
				If config_app_module.selcted_language="Fa" Then
					Msgbox("لطفا ایمل خود را به شکل صحیح وارد نمایید .","ایمیل اشتباه")
				Else If config_app_module.selcted_language="En" Then
					Msgbox("Please enter your correct email","Enter Correct email")
				Else If config_app_module.selcted_language="Ar" Then
					Msgbox("يرجى إدخال البريد الإلكتروني الصحيح","أدخل البريد الإلكتروني الصحيح")
				End If
			End If

		Else
			Set_View_BackGround_and_Border.Set_View_Border_BG(edt_pass,Colors.Red,Colors.Black,1,5)
			Set_View_BackGround_and_Border.Set_View_Border_BG(edt_retype_pass,Colors.Red,Colors.Black,1,5)
		End If
		
	End If
	
End Sub

Sub edt_pass_TextChanged (Old As String, New As String)
	If edt_pass.Text=edt_retype_pass.Text Then
		Set_View_BackGround_and_Border.Set_View_Border_BG(edt_pass,Colors.White,Colors.Black,1,5)
		Set_View_BackGround_and_Border.Set_View_Border_BG(edt_retype_pass,Colors.White,Colors.Black,1,5)
	Else If  (edt_pass.Text<>"" And edt_retype_pass.Text<>"") And edt_pass.Text<>edt_retype_pass.Text Then
		Set_View_BackGround_and_Border.Set_View_Border_BG(edt_pass,Colors.Red,Colors.Black,1,5)
		Set_View_BackGround_and_Border.Set_View_Border_BG(edt_retype_pass,Colors.Red,Colors.Black,1,5)
	End If
End Sub

Sub edt_retype_pass_TextChanged (Old As String, New As String)
	If edt_pass.Text=edt_retype_pass.Text Then
		Set_View_BackGround_and_Border.Set_View_Border_BG(edt_pass,Colors.White,Colors.Black,1,5)
		Set_View_BackGround_and_Border.Set_View_Border_BG(edt_retype_pass,Colors.White,Colors.Black,1,5)
	Else If  (edt_pass.Text<>"" And edt_retype_pass.Text<>"") And edt_pass.Text<>edt_retype_pass.Text Then
		Set_View_BackGround_and_Border.Set_View_Border_BG(edt_pass,Colors.Red,Colors.Black,1,5)
		Set_View_BackGround_and_Border.Set_View_Border_BG(edt_retype_pass,Colors.Red,Colors.Black,1,5)
	End If
End Sub


Sub JobDone(Job As HttpJob)
	Dim sc As sec_Class_1
	
	ProgressDialogHide
	
	
'	Msgbox(sc.Decryption(Job.GetString),Job.JobName)
	
	
	If Job.Success=True Then
		If Job.JobName="sgnp" Then
			If Job.GetString=sc.Encryption("repeat_mail") Then
				If config_app_module.selcted_language="Fa" Then
					#region فارسی
					Msgbox("ایمیل وارد شده تکراری میباشد","ایمیل تکراری")
					#End Region
				Else If config_app_module.selcted_language="En" Then
					#region انگلیسی
					Msgbox("Email is duplicate","Duplicate")
					#End Region
				Else If config_app_module.selcted_language="Ar" Then
					#region عربی
					Msgbox("البريد الإلكتروني مكرر","مكرر")
					#End Region
				End If
				edt_email.Text=""
								
			Else If Job.GetString=sc.Encryption("ok_send_mail") Then
				If config_app_module.selcted_language="Fa" Then
					#region فارسی
					Msgbox("ثبت نام شما با موفقیت انجام شد . جهت تأیید ایمیل خود بروی روی لینک ارسال شده به ایمیل کلیک کنید .","تأیید")
					#End Region
				Else If config_app_module.selcted_language="En" Then
					#region انگلیسی
					Msgbox("Your registration has been successfully completed. To confirm your email, click on the link sent to the email.","Successful")
					#End Region
				Else If config_app_module.selcted_language="Ar" Then
					#region عربی
					Msgbox("تم الانتهاء من تسجيلك بنجاح. لتأكيد بريدك الإلكتروني ، انقر فوق الرابط المرسل إلى البريد الإلكتروني.","ناجح")
					#End Region
				End If

				StartActivity(Main)
				Activity.Finish
			Else If Job.GetString=sc.Encryption("error") Then
				If config_app_module.selcted_language="Fa" Then
						#region فارسی
					Msgbox("خطایی رخ داد ، لطفا بعدا مجددا تلاش نمایید","خطا")
						#End Region
				Else If config_app_module.selcted_language="En" Then
						#region انگلیسی
					Msgbox("An error occurred. Please try again later","Error")
						#End Region
				Else If config_app_module.selcted_language="Ar" Then
						#region عربی
					Msgbox("حدث خطأ. الرجاء معاودة المحاولة في وقت لاحق","خطأ")
						#End Region
				End If
				StartActivity(Main)
				Activity.Finish
			Else
				config_app_module.hand_work_app
				ExitApplication
			End If
		End If
	Else
		If config_app_module.selcted_language="Fa" Then
		#region فارسی
			Msgbox("خطایی رخ داد ، لطفا بعدا مجددا تلاش نمایید","خطا")
		#End Region
		Else If config_app_module.selcted_language="En" Then
		#region انگلیسی
			Msgbox("An error occurred. Please try again later","Error")
		#End Region
		Else If config_app_module.selcted_language="Ar" Then
		#region عربی
			Msgbox("حدث خطأ. الرجاء معاودة المحاولة في وقت لاحق","خطأ")
		#End Region
		End If
		
		StartActivity(Main)
		Activity.Finish
		
	End If
	
End Sub