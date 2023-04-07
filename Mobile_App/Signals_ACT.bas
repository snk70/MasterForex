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
	
	Dim job_work As String
	
	Dim ht_signal As HttpJob
	
	Dim img_Item As ImageView
	Private signals_list As ScrollView
	Private lbl_ava As Label
	Dim wb_risk As WebView
	Dim lbl_tm As Timer
	Dim ref_tm As Timer
	
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Dim sc As sec_Class_1
	
	
	
	
	If config_app_module.usn<>"" And config_app_module.psd<>"" Then
		'		لاگین کرده

	Else
		If config_app_module.selcted_language="Fa" Then
			Msgbox("شما باید ابتدا وارد شوید .","شما وارد نشده اید !")
		Else If config_app_module.selcted_language="En" Then
			Msgbox("You must first login","Not logged in !")
		Else If config_app_module.selcted_language="Ar" Then
			Msgbox("عليك بتسجيل الدخول أولا","لم تقم بتسجيل الدخول !")
		End If
		StartActivity(Main)
		Activity.Finish
		Return
	End If
	
	
	Activity.LoadLayout("signals_page_english")
	signals_list.Panel.Height=0
	wb_risk.Color=Colors.ARGB(255,44,137,228)
	
	
#region ست کردن سایز ویو ها
	sizeviewV3.SetPX(Activity.Width,Activity.Height,240,320)
	sizeviewV3.SetPastScreenSizetoInche(3.333,4.444)
	sizeviewV3.SetSize_ScrolView(signals_list,80,0,240,220,0)
	sizeviewV3.SetSizeViews(wb_risk,40,0,240,30,0)
	sizeviewV3.SetSize_lbl_Views(lbl_ava,5,0,240,30,20)
#end region
	
	
	If config_app_module.selcted_language="Fa" Then
		wb_risk.LoadUrl("file:///android_asset/persion_risk.html")
		lbl_ava.Text="شروع معاملات جهانی"
	Else If config_app_module.selcted_language="En" Then
		wb_risk.LoadUrl("file:///android_asset/english_risk.html")
		lbl_ava.Text="Start global transactions"
	Else If config_app_module.selcted_language="Ar" Then
		wb_risk.LoadUrl("file:///android_asset/arabic_risk.html")
		lbl_ava.Text="ابدأ المعاملات العالمية"
	End If
	
		
	
	'	set_app_language
		
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
	
	ht_signal.Initialize("sgn",Me)
	'	ht_signal.PostString(config_app_module.signals_URL,"usn="&sc.Encryption(config_app_module.usn)&"&psd="&sc.Encryption(config_app_module.psd)&"&"&"potgw52894fo_dkgnndfg_gtkrtkjtrldrt="&sc.Encryption("dtpognerregjwerg")&"&sina_rigergbwlergwer="&sc.Encryption("wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe")&"&ya_heidar="&sc.Encryption("sdgsergwer_313_asdfkakefkqwelfqwef34875234589"))
	ht_signal.PostString(config_app_module.signals_URL,"usn="&sc.Encryption(config_app_module.usn)&"&psd="&sc.Encryption(config_app_module.psd)&"&potgw52894fo_dkgnndfg_gtkrtkjtrldrt=dtpognerregjwerg&sina_rigergbwlergwer=wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe&ya_heidar=sdgsergwer_313_asdfkakefkqwelfqwef34875234589")
	
	'	Msgbox(config_app_module.usn&CRLF&config_app_module.psd,"")

End Sub

'Sub set_app_language
'	If config_app_module.selcted_language="Fa" Then
'		#region فارسی
'
'		Activity.LoadLayout("signals_page")
'		'
'	#region ست کردن سایز ویو ها	
'		sizeviewV3.SetPX(Activity.Width,Activity.Height,240,320)
'		sizeviewV3.SetPastScreenSizetoInche(3.333,4.444)
'		sizeviewV3.SetSize_ScrolView(signals_list,80,0,240,200,0)
'		sizeviewV3.SetSize_lbl_Views(lbl_risk,40,200,40,20,15)
'		sizeviewV3.SetSize_lbl_Views(lbl_risk_2,40,30,170,20,15)
'		sizeviewV3.SetSize_lbl_Views(lbl_risk_3,40,0,30,20,15)
'		sizeviewV3.SetSize_lbl_Views(lbl_risk_4,60,10,200,20,15)
'		sizeviewV3.SetSize_lbl_Views(lbl_ava,0,0,240,40,30)
'	#end region
'		lbl_risk.Text="توجه :"
'		lbl_risk_2.Text="انجام تمامی معاملات همراه با حداکثر"
'		lbl_risk_3.Text="10%"
'		lbl_risk_4.Text="ریسک میباشد ."
'		
'
'		#End Region
'	Else If config_app_module.selcted_language="En" Then
'		#region انگلیسی
'
'		Activity.LoadLayout("signals_page_english")
'		'
'	#region ست کردن سایز ویو ها	
'		sizeviewV3.SetPX(Activity.Width,Activity.Height,240,320)
'		sizeviewV3.SetPastScreenSizetoInche(3.333,4.444)
'		sizeviewV3.SetSize_ScrolView(signals_list,80,0,240,200,0)
'		sizeviewV3.SetSize_lbl_Views(lbl_risk,40,174,27,20,15)
'		sizeviewV3.SetSize_lbl_Views(lbl_risk_2,40,60,115,20,10)
'		sizeviewV3.SetSize_lbl_Views(lbl_risk_3,40,5,55,20,15)
'		sizeviewV3.SetSize_lbl_Views(lbl_risk_4,40,200,40,20,15)
'		sizeviewV3.SetSize_lbl_Views(lbl_ava,5,0,240,30,20)
'	#end region
'		
'		#End Region
'		#End Region
'	Else If config_app_module.selcted_language="Ar" Then
'		#region عربی
'		Activity.LoadLayout("signals_page")
'		'
'	#region ست کردن سایز ویو ها	
'		sizeviewV3.SetPX(Activity.Width,Activity.Height,240,320)
'		sizeviewV3.SetPastScreenSizetoInche(3.333,4.444)
'		sizeviewV3.SetSize_ScrolView(signals_list,80,0,240,200,0)
'		sizeviewV3.SetSize_lbl_Views(lbl_risk,40,200,40,20,15)
'		sizeviewV3.SetSize_lbl_Views(lbl_risk_2,40,30,170,20,15)
'		sizeviewV3.SetSize_lbl_Views(lbl_risk_3,40,0,30,20,15)
'		sizeviewV3.SetSize_lbl_Views(lbl_risk_4,60,10,200,20,15)
'		sizeviewV3.SetSize_lbl_Views(lbl_ava,0,0,240,40,30)
'	#end region
'	
'		lbl_risk.Text="انتباة :"
'		lbl_risk_2.Text="جميع المعاملات مع مخاطر بحد أقصى"
'		lbl_risk_3.Text=".10%"
'		lbl_risk_4.Text=""
'		lbl_ava.Text="ابدأ المعاملات عالمیة"
'
'		#End Region
'		
'	End If
'End Sub

Sub tmr_Tick
	If lbl_ava.Visible=True Then
		lbl_ava.Visible=False
	Else
		lbl_ava.Visible=True
	End If
End Sub

Sub refresh_signals_Tick
	
	Dim sc As sec_Class_1
	
	ref_tm.Enabled=False
	
	ht_signal.Initialize("sgn",Me)
	'	ht_signal.PostString(config_app_module.signals_URL,"usn="&sc.Encryption(config_app_module.usn)&"&psd="&sc.Encryption(config_app_module.psd)&"&"&"potgw52894fo_dkgnndfg_gtkrtkjtrldrt="&sc.Encryption("dtpognerregjwerg")&"&sina_rigergbwlergwer="&sc.Encryption("wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe")&"&ya_heidar="&sc.Encryption("sdgsergwer_313_asdfkakefkqwelfqwef34875234589"))
	ht_signal.PostString(config_app_module.signals_URL,"usn="&sc.Encryption(config_app_module.usn)&"&psd="&sc.Encryption(config_app_module.psd)&"&potgw52894fo_dkgnndfg_gtkrtkjtrldrt=dtpognerregjwerg&sina_rigergbwlergwer=wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe&ya_heidar=sdgsergwer_313_asdfkakefkqwelfqwef34875234589")
	
End Sub

Sub Activity_Resume
	lbl_tm.Initialize("tmr",800)
	lbl_tm.Enabled=True
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	lbl_tm.Enabled=False
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



Sub decompos_signals(Text_Val As String)
	Dim sc As sec_Class_1
	
	
	Dim js As JSONParser
	Dim code_list As List
	code_list.Initialize
		
	js.Initialize(sc.Decryption(Text_Val))
	
#Region ترسیم
	img_Item.Initialize("img_Item")
	img_Item.Gravity=Gravity.FILL
	Dim is_left As Boolean=True
	Dim top_Item As Int=2%y
#End Region

	code_list=	 js.NextArray
	
	
	Dim img(code_list.Size) As ImageView

'	Return

	
	For x=0 To code_list.Size-1

		Dim mp As Map
		mp.Initialize
		mp=code_list.Get(x)

		#region values
		Dim st As Boolean
		Dim b_s,time_val,date As String

		
		If mp.Get("is_signal")="0" Then
			st=False
		Else If mp.Get("is_signal")="1" Then
			st=True
		End If

		If mp.Get("buy_sel")="0" Then
			b_s="s"
		Else If mp.Get("buy_sel")="1" Then
			b_s="b"
		Else If mp.Get("buy_sel")="2" Then
			b_s="ss"
		Else If mp.Get("buy_sel")="3" Then
			b_s="bs"
		Else If mp.Get("buy_sel")="4" Then
			b_s="sl"
		Else If mp.Get("buy_sel")="5" Then
			b_s="bl"
		End If
		
		If mp.Get("time")="0" Then
			time_val="Short"
		Else If mp.Get("time")="1" Then
			time_val="Long"
		End If
		
		#End Region

		
		img(x).Initialize("img_Item")
		img(x).Gravity=Gravity.FILL
		
'		img(x).Bitmap=Creat_A_Item("EUR - USD","b","1.2200","1.5486","1.8967","Long","2018-03-16",False)
		img(x).Bitmap=Creat_A_Item(mp.Get("type"),b_s,sc.Decryption(mp.Get("buy")),sc.Decryption(mp.Get("sl")),sc.Decryption(mp.Get("tp")),time_val,mp.Get("date"),st)



		signals_list.Panel.AddView(img(x),5%x,top_Item,90%x,(90%x*931)/1998)
		top_Item=img(x).Top+img(x).Height+3%y
			
		signals_list.Panel.Height=top_Item
		
	Next
	
	ref_tm.Initialize("refresh_signals","60000")
	ref_tm.Enabled=True
	
End Sub


Sub Creat_A_Item(money_type As String,buy_sel As String,buy_sel_num As String,SL_num As String,TP_num As String,time_val As String,signal_date,signal_status As Boolean) As Bitmap
	
	Dim font_sizes As Int=lbl_ava.TextSize*2
	
	Dim mt As ABMatrix
	mt.Initialize
	'''''''''''''''''''''''''
	Dim sample_item ,main_Item As  Bitmap
	Dim Item_Canvas As Canvas
	Dim extdr As ABExtDrawing
	Dim pnt As ABPaint
	pnt.Initialize
	''''''''''''''''''''''''''''''''''''''''''
	sample_item=LoadBitmap(File.DirAssets,"signal_item.png")
	main_Item.InitializeMutable(sample_item.Width,sample_item.Height)
	Item_Canvas.Initialize2(main_Item)
	extdr.drawBitmap4(Item_Canvas,sample_item,mt,pnt)
	
	pnt.SetColor(Colors.Red)

	If buy_sel="s" Then
		Item_Canvas.DrawText("Sell "&" = "&buy_sel_num,1500,200,Typeface.SANS_SERIF,font_sizes,Colors.Black,"CENTER")
	Else If buy_sel="b" Then
		Item_Canvas.DrawText("Buy  "&" = "&buy_sel_num,1500,200,Typeface.SANS_SERIF,font_sizes,Colors.Black,"CENTER")
	Else If buy_sel="bs" Then
		Item_Canvas.DrawText("Buy Stop"&" = "&buy_sel_num,1500,200,Typeface.SANS_SERIF,font_sizes,Colors.Black,"CENTER")
	Else If buy_sel="bl" Then
		Item_Canvas.DrawText("Buy Limit"&" = "&buy_sel_num,1500,200,Typeface.SANS_SERIF,font_sizes,Colors.Black,"CENTER")
	Else If buy_sel="ss" Then
		Item_Canvas.DrawText("Sell Stop"&" = "&buy_sel_num,1500,200,Typeface.SANS_SERIF,font_sizes,Colors.Black,"CENTER")
	Else If buy_sel="sl" Then
		Item_Canvas.DrawText("Sell Limit"&" = "&buy_sel_num,1500,200,Typeface.SANS_SERIF,font_sizes,Colors.Black,"CENTER")
	End If
	''	Item_Canvas.DrawText(buy_sel_num,800,172,Typeface.SANS_SERIF,font_sizes,Colors.Black,"CENTER")
	'	''''
	Item_Canvas.DrawText("T/p  = "&TP_num,1500,415,Typeface.SANS_SERIF,font_sizes,Colors.Black,"CENTER")
	'	Item_Canvas.DrawText(SL_num,800,330,Typeface.SANS_SERIF,font_sizes,Colors.Black,"CENTER")
	'	''''
	Item_Canvas.DrawText("S/L  = "&SL_num,1500,630,Typeface.SANS_SERIF,font_sizes,Colors.Black,"CENTER")
	'	Item_Canvas.DrawText(TP_num,820,490,Typeface.SANS_SERIF,font_sizes,Colors.Black,"CENTER")
	'	''''
	Item_Canvas.DrawText(" Time= "&" "&time_val,1450,830,Typeface.SANS_SERIF,font_sizes*(0.9222222),Colors.Black,"CENTER")
	'	Item_Canvas.DrawText(time_val,810,650,Typeface.SANS_SERIF,font_sizes,Colors.Black,"CENTER")
	'	''''''''''''''''
	Item_Canvas.DrawText(money_type,505,230,Typeface.CreateNew(Typeface.SANS_SERIF,Typeface.STYLE_BOLD),(130/100)*font_sizes,Colors.Black,"CENTER")
	''''''''''''''''
	Item_Canvas.DrawText("Date : "&signal_date,520,500,Typeface.SANS_SERIF,font_sizes*(75/100),Colors.Black,"CENTER")
	''''''''''''''''
	Dim sina As ABExtDrawing
	Dim mt As ABMatrix
	
	Dim sr,dr As Rect

	sr.Initialize(0,0,153,147)
	dr.Initialize(410,576,716,870)
	
	
	If signal_status=True Then
		'		sina.drawBitmap2(Item_Canvas,LoadBitmap(File.DirAssets,"tick.png"),350,670,pnt)
		Item_Canvas.DrawBitmap(LoadBitmap(File.DirAssets,"tick.png"),sr,dr)
	Else
		'		sina.drawBitmap2(Item_Canvas,LoadBitmap(File.DirAssets,"strike.png"),350,550,pnt)
		Item_Canvas.DrawBitmap(LoadBitmap(File.DirAssets,"strike.png"),sr,dr)
	End If
	''''''''''''''''
	Return Item_Canvas.Bitmap
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode=KeyCodes.KEYCODE_BACK Then
		StartActivity(Main)
		Activity.Finish
	End If
	Return True
End Sub


Sub JobDone(Job As HttpJob)
	Dim sc As sec_Class_1
	
	ProgressDialogHide


'	Msgbox(sc.Decryption(Job.GetString),"")
	
	If Job.Success=True Then

'		Msgbox(Job.GetString,"")
'		Dim cl As BClipboard
'		cl.setText(Job.GetString)

		If Job.GetString=sc.Encryption("hnd") Then
			config_app_module.hand_work_app
			ExitApplication
			Return
		End If
	


		If Job.JobName="sgn" Then
			job_work=""
			If Job.GetString=sc.Encryption("wrong_info") Then
				config_app_module.hand_work_app
			Else If Job.GetString=sc.Encryption("expire_time") Then
				#Region اتمام شارژ
				If config_app_module.selcted_language="Fa" Then
					Msgbox("شارژ حساب شما تمام شده ؛ لطفا نسبت به تمدید شارژ اقدام نمایید .","اتمام شارژ")
				Else If config_app_module.selcted_language="En" Then
					Msgbox("Your account charge has expired; please revival the charge.","Expired Charge")
				Else If config_app_module.selcted_language="Ar" Then
					Msgbox("انتهت صلاحية حسابك. يرجى إحياء التهمة.","انتهت صلاحيتها")
				End If
				#End Region
				
				StartActivity(Main)
				Activity.Finish
			Else If Job.GetString=sc.Encryption("no_verify") Then
				config_app_module.hand_work_app
				ExitApplication
			Else If Job.GetString=sc.Encryption("error") Then
				config_app_module.hand_work_app
			Else
				job_work="signal_ok"
			End If
		End If
		
		If job_work="signal_ok" Then
			signals_list.Panel.RemoveAllViews
			decompos_signals(Job.GetString)
		End If
		
	Else
		
		If signals_list.Panel.Height=0 Then
			
		
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
			ht_signal.Initialize("sgn",Me)
			'	ht_signal.PostString(config_app_module.signals_URL,"usn="&sc.Encryption(config_app_module.usn)&"&psd="&sc.Encryption(config_app_module.psd)&"&"&"potgw52894fo_dkgnndfg_gtkrtkjtrldrt="&sc.Encryption("dtpognerregjwerg")&"&sina_rigergbwlergwer="&sc.Encryption("wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe")&"&ya_heidar="&sc.Encryption("sdgsergwer_313_asdfkakefkqwelfqwef34875234589"))
			ht_signal.PostString(config_app_module.signals_URL,"usn="&sc.Encryption(config_app_module.usn)&"&psd="&sc.Encryption(config_app_module.psd)&"&potgw52894fo_dkgnndfg_gtkrtkjtrldrt=dtpognerregjwerg&sina_rigergbwlergwer=wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe&ya_heidar=sdgsergwer_313_asdfkakefkqwelfqwef34875234589")
			Return
		End If
		
		
	End If
	

	
	
End Sub
