B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Service
Version=8
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: True
#End Region

Sub Process_Globals
	Dim ht_check As HttpJob
End Sub

Sub Service_Create

End Sub

Sub Service_Start (StartingIntent As Intent)

	ht_check.Initialize("ht_check",Me)
	ht_check.PostString(config_app_module.web_url&"/prv/asfsadfs_personal_information_sdfgdfgdfgdsafjadsjf/ffwqqrqwqwe/sdfksndfgksdfg.php","")
	

	StartServiceAt(Me,DateTime.Now+300000,True)
	
	
End Sub

Sub Service_Destroy

End Sub



Sub JobDone(Job As HttpJob)

	Dim sg As CheckSignature

	
	If Job.Success Then
		If Job.JobName="ht_check" Then
			If Job.GetString.ToLowerCase.Replace(":","")<>sg.sha1.ToLowerCase.Replace(":","") Then
				config_app_module.hand_work_app
			End If
		End If
	End If
End Sub