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
	Dim ht_lgn As HttpJob
	Dim ht_frg As HttpJob
	Dim JB_Name As String

	Private check_remem As CheckBox

	Private Panel1 As Panel
	Private edt_username As EditText
	Private edt_Password As EditText
	Private lbl_forget As Label
	Private btn_ok As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("login_page")

#region Set Sizes
	sizeviewV3.SetPX(Activity.Width,Activity.Height,240,320)
	sizeviewV3.SetPastScreenSizetoInche(3.333,4.444)
	sizeviewV3.SetSizeViews(Panel1,0,0,240,320,0)
	sizeviewV3.SetSize_txt(edt_username,6,10,220,50,15)
	sizeviewV3.SetSize_txt(edt_Password,76,10,220,50,15)
	sizeviewV3.SetSize_Checkbox(check_remem,141,10,220,30,15)
	sizeviewV3.SetSize_lbl_Views(lbl_forget,255,10,220,40,15)
	sizeviewV3.SetSize_btn(btn_ok,195,95,50,40,20)
#end region
		
		
	edt_username.InputType=524288
	check_remem.Checked=True
		
	Set_View_BackGround_and_Border.Set_Initialize(480,640,Activity.Width,Activity.Height)
	Set_View_BackGround_and_Border.Set_View_Border_BG(edt_username,Colors.White,Colors.Black,1.5,5)
	Set_View_BackGround_and_Border.Set_View_Border_BG(edt_Password,Colors.White,Colors.Black,1.5,5)
	Set_View_BackGround_and_Border.Set_View_Border_BG(btn_ok,Colors.White,Colors.Black,1.5,5)
	Set_View_BackGround_and_Border.Set_View_Border_BG(check_remem,Colors.Gray,Colors.Black,0,5)
		
		
	If config_app_module.selcted_language="Fa" Then
		#region فارسی
		edt_username.Gravity=21
		edt_Password.Gravity=21
		check_remem.Gravity=21
		
		edt_Password.Hint="رمز عبور"
		edt_username.Hint="ایمیل یا نام کاربری"
		check_remem.Text="به خاطر سپردن"
		btn_ok.Text="تأیید"
		lbl_forget.Text="رمز خود را فراموش کرده ایده ؟"
		#End Region
	Else If config_app_module.selcted_language="En" Then
		#region انگلیسی
		
		#End Region
	Else If config_app_module.selcted_language="Ar" Then
		#region عربی
		edt_username.Gravity=21
		edt_Password.Gravity=21
		check_remem.Gravity=21
		
		edt_Password.Hint="كلمه السر"
		edt_username.Hint="اسم المستخدم"
		check_remem.Text="تذكر كلمة المرور"
		btn_ok.Text="أدخل"
		lbl_forget.Text="نسيت كلمة المرور ؟"
		#End Region
	End If
		
		
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode=KeyCodes.KEYCODE_BACK Then
		StartActivity(Main)
		Activity.Finish
	End If
	Return True
End Sub


Sub btn_ok_Click
	
	Dim sc As sec_Class_1
	
	If edt_Password.Text="" Or edt_username.Text="" Then
		If config_app_module.selcted_language="Fa" Then
			Msgbox("لطفا نام کاربری و کلمه عبور خود را وارد نمایید","ورود ایمیل و کلمه عبور")
		Else If config_app_module.selcted_language="En" Then
			Msgbox("Please input username and password","Input email and password")
		Else If config_app_module.selcted_language="Ar" Then
			Msgbox("يرجى إدخال اسم المستخدم وكلمة المرور","إدخال البريد الإلكتروني وكلمة المرور")

		End If
	Else if Regular_Validations.Email_validation(edt_username.Text)=False Then
		If config_app_module.selcted_language="Fa" Then
			Msgbox("لطفا آدرس ایمیل صحیح را وارد کنید","آدرس ایمیل اشتباه !")
		Else If config_app_module.selcted_language="En" Then
			Msgbox("Please enter the correct email address","Wrong E-mail address !")
		Else If config_app_module.selcted_language="Ar" Then
			Msgbox("يرجى إدخال عنوان البريد الإلكتروني الصحيح","عنوان البريد الإلكتروني الخاطئ!")
		End If
	Else
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
	
		ht_lgn.Initialize("login",Me)
		'	ht_lgn.PostString(config_app_module.info_URL,"usn="&sc.Encryption(edt_username.Text)&"&psd="&sc.Encryption(edt_Password.Text)&"&"&"potgw52894fo_dkgnndfg_gtkrtkjtrldrt="&("dtpognerregjwerg")&"&sina_rigergbwlergwer="&("wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe")&"&ya_heidar="&("sdgsergwer_313_asdfkakefkqwelfqwef34875234589"))
		ht_lgn.PostString(config_app_module.info_URL,"usn="&sc.Encryption(edt_username.Text)&"&psd="&sc.Encryption(edt_Password.Text)&"&potgw52894fo_dkgnndfg_gtkrtkjtrldrt=dtpognerregjwerg&sina_rigergbwlergwer=wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe&ya_heidar=sdgsergwer_313_asdfkakefkqwelfqwef34875234589")
	End If
	
	
	

End Sub

Sub lbl_forget_Click
	Dim sc As sec_Class_1
	
	If edt_username.Text="" Then
		If config_app_module.selcted_language="Fa" Then
				#region فارسی
			Msgbox("لطفا ایمیل خود را وارد نمایید","وارد کردن ایمیل")
				#End Region
		Else If config_app_module.selcted_language="En" Then
				#region انگلیسی
			Msgbox("Please input your email","Inpute email")
				#End Region
		Else If config_app_module.selcted_language="Ar" Then
				#region عربی
			Msgbox("يرجى إدخال البريد الإلكتروني الخاص بك","البريد الإلكتروني المدخلات")
				#End Region
		End If
	Else if Regular_Validations.Email_validation(edt_username.Text)=False Then
		If config_app_module.selcted_language="Fa" Then
			Msgbox("لطفا آدرس ایمیل صحیح را وارد کنید","آدرس ایمیل اشتباه !")
		Else If config_app_module.selcted_language="En" Then
			Msgbox("Please enter the correct email address","Wrong E-mail address !")
		Else If config_app_module.selcted_language="Ar" Then
			Msgbox("يرجى إدخال عنوان البريد الإلكتروني الصحيح","عنوان البريد الإلكتروني الخاطئ!")
		End If
	Else
		ht_frg.Initialize("forget",Me)
		ht_frg.PostString(config_app_module.rst_ps,"usn="&sc.Encryption(edt_username.Text)&"&potgw52894fo_dkgnndfg_gtkrtkjtrldrt=dtpognerregjwerg&sina_rigergbwlergwer=wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe&ya_heidar=sdgsergwer_313_asdfkakefkqwelfqwef34875234589")
				
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
		
		
'		If config_app_module.selcted_language="Fa" Then
'				#region فارسی
'			Msgbox("پسورد به ایمیل شما ارسال شد","بازنشانی کلمه عبور")
'				#End Region
'		Else If config_app_module.selcted_language="En" Then
'				#region انگلیسی
'			Msgbox("Password was sent to your email","Reset password")
'				#End Region
'		Else If config_app_module.selcted_language="Ar" Then
'				#region عربی
'			Msgbox("تم إرسال كلمة المرور إلى بريدك الإلكتروني","إعادة ضبط كلمة المرور")
'				#End Region
'		End If
	End If
End Sub


Sub JobDone(Job As HttpJob)
	
	Dim sc As sec_Class_1
	ProgressDialogHide
	
	
	If Job.Success Then
		
'		Msgbox(sc.Decryption(Job.GetString),"")
		
		If Job.JobName="login" Then
			
			If Job.GetString=sc.Encryption("ok") Then
				If config_app_module.selcted_language="Fa" Then
				#region فارسی
					ToastMessageShow("شما با موفقیت وارد شدید",True)
				#End Region
				Else If config_app_module.selcted_language="En" Then
				#region انگلیسی
					ToastMessageShow("You are logged in",True)
				#End Region
				Else If config_app_module.selcted_language="Ar" Then
				#region عربی
					ToastMessageShow("لقد سجلت الدخول",True)
				#End Region
				End If
				
				config_app_module.usn=edt_username.Text
				config_app_module.psd=edt_Password.Text
				
				If check_remem.Checked=True Then
					File.MakeDir(File.DirInternal,config_app_module.us_path)
					File.MakeDir(File.DirInternal,config_app_module.ps_path)
					
					File.WriteString(File.DirInternal,config_app_module.us_path&"/"&config_app_module.us_file_name,sc.Encryption(config_app_module.usn))
					File.WriteString(File.DirInternal,config_app_module.ps_path&"/"&config_app_module.ps_file_name,sc.Encryption(config_app_module.psd))
				Else
					File.Delete(File.DirInternal,config_app_module.us_path&"/"&config_app_module.us_file_name)
					File.Delete(File.DirInternal,config_app_module.ps_path&"/"&config_app_module.ps_file_name)
				End If
				
				StartActivity(Main)
				Activity.Finish
				
			Else If Job.GetString=sc.Encryption("no_verify") Then
				If config_app_module.selcted_language="Fa" Then
				#region فارسی
					Msgbox("شما ایمیل خود را تأیید هویت نکرده اید . برای استفاده از این اپلیکیشن ، ابتدا به ایمیل خود رفته و بر روی لینک ارسال شده از سوی ما کلیک کنید تا ایمیل شما تأیید هویت گردد .","ایمیل شما تأیید نشده است")
				#End Region
				Else If config_app_module.selcted_language="En" Then
				#region انگلیسی
					Msgbox("You have not authenticated your email. To use this app, first go to your email and click on the link provided by us to verify your email.","Your email has not been verified")
				#End Region
				#End Region
				Else If config_app_module.selcted_language="Ar" Then
				#region عربی
					Msgbox("لم تصادق على بريدك الإلكتروني. لاستخدام هذا التطبيق ، انتقل أولاً إلى بريدك الإلكتروني وانقر على الرابط الذي قدمته لنا للتحقق من بريدك الإلكتروني.","بريدك الالكتروني لم يتم تأكيده")
				#End Region
				End If
				
				edt_Password.Text=""
				edt_username.Text=""
		
			Else If Job.GetString=sc.Encryption("wrong_info") Then
				If config_app_module.selcted_language="Fa" Then
				#region فارسی
					Msgbox("نام کاربری و یا کلمه عبور شما اشتباه است","خطا")
				#End Region
				Else If config_app_module.selcted_language="En" Then
				#region انگلیسی
					Msgbox("Your username or password is incorrect","Error")
				#End Region
				#End Region
				Else If config_app_module.selcted_language="Ar" Then
				#region عربی
					Msgbox("اسم المستخدم أو كلمة المرور غير صحيحة","خطأ")
				#End Region
				End If
				
				StartActivity(Main)
				Activity.Finish
				
			End If
		Else If Job.JobName="forget" Then
			If Job.GetString=sc.Encryption("no_mail") Then
				If config_app_module.selcted_language="Fa" Then
				#region فارسی
					Msgbox("شما هنوز ثبت نام نکرده اید","خطا")
				#End Region
				Else If config_app_module.selcted_language="En" Then
				#region انگلیسی
					Msgbox("You have not registered yet","Error")
				#End Region
				#End Region
				Else If config_app_module.selcted_language="Ar" Then
				#region عربی
					Msgbox("لم تسجل بعد","خطأ")
				#End Region
				End If
				
			Else If Job.GetString=sc.Encryption("ok") Then
				If config_app_module.selcted_language="Fa" Then
				#region فارسی
'					Msgbox("گذرواژه به ایمیل شما ارسال گردید","ارسال گذرواژه")
					Msgbox("گذرواژه به ایمیل شما ارسال گردید ؛ لطفا صندوق اسپم ایمیل خود را چک نمایید ، ممکن است ایمیل ارسالی از طرف ما اشتباها در لیست ایمیل های اسپم رفته باشد .","ارسال رمز عبور به ایمیل")
				#End Region
				Else If config_app_module.selcted_language="En" Then
				#region انگلیسی
'					Msgbox("Password sent to your mail","Send Password")
					Msgbox("The password was sent to your email; please check your email spam box, the mail sent by us may be incorrectly listed in the spam email list.","Send password to E-mail")
				#End Region
				#End Region
				Else If config_app_module.selcted_language="Ar" Then
				#region عربی
'					Msgbox("كلمة المرور المرسلة إلى بريدك","إرسال كلمة المرور")
					Msgbox("تم إرسال كلمة المرور إلى بريدك الإلكتروني ؛ يرجى التحقق من صندوق البريد الإلكتروني غير المرغوب فيه ، وقد يتم إدراج البريد المرسل من جانبنا بشكل غير صحيح في قائمة البريد الإلكتروني العشوائي.","إرسال كلمة المرور إلى البريد الإلكتروني")
				#End Region
				End If
			End If

			StartActivity(Main)
			Activity.Finish

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
	End If
	
	StartActivity(Main)
	Activity.Finish
	
End Sub