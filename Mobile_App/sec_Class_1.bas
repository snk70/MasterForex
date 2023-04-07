B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=6.8
@EndOfDesignText@
Sub Class_Globals
	Dim lng As Int
End Sub

'Initializes the object. You can add parameters to this method if needed.
Public Sub Initialize

End Sub

Sub Encryption(Val As String) As String
	If Val="" Then
		Return ""
	Else	
		Dim bs As Base64
		Val=bs.EncodeStoS(Val,"UTF-8")
		Val=data_Encryption(Val)
		Val=Val.SubString2(3,Val.Length)&Val.SubString2(0,3)
		Val=Val.SubString2(3,Val.Length)&Val.SubString2(0,3)
		Return Val
	End If
End Sub

Sub Decryption(Val As String) As String
	If Val="" Then
		Return ""
	Else
		Dim bs As Base64
		Val=Val.SubString2(Val.Length-3,Val.Length)&Val.SubString2(0,Val.Length-3)
		Val=Val.SubString2(Val.Length-3,Val.Length)&Val.SubString2(0,Val.Length-3)
		Val=data_Decryption(Val)
		Val=bs.DecodeStoS(Val,"UTF-8")
	End If
	Return Val
End Sub


Public Sub data_Encryption(t As String) As String

	Dim txt As String

	Dim tx1 As String = ""
	tx1 = ""
	Dim cec(t.Length) As String
	txt = t

	For i = 0 To t.Length - 1
		Dim x As String
		x = txt.Substring(txt.Length - 1)
		cec(i) = sina_ENC(x)
		txt = txt.SubString2(0, txt.Length - 1)
	Next
	tx1 = ""
	For j = 0 To cec.Length - 1
		tx1 = cec(j) & tx1

	Next

	'''''''''''''''''''''''''''''''''''''''''''''''''''''''
	Return tx1
End Sub



Public Sub sina_ENC(v As String) As String

	If v="0" Then
		Return "mbW"
	Else If v="1" Then
		Return "ndn"
	Else If v="2" Then
		Return "tDr"
	Else If v="3" Then
		Return "KWM"
	Else If v="4" Then
		Return "E1E"
	Else If v="5" Then
		Return "JVA"
	Else If v="7" Then
		Return "HIS"
	Else If v="8" Then
		Return "JUb"
	Else If v="9" Then
		Return "at1"
	Else If v="a" Then
		Return "noe"
	Else If v="b" Then
		Return "fkH"
	Else If v="c" Then
		Return "S7g"
	Else If v="d" Then
		Return "06r"
	Else If v="e" Then
		Return "buW"
	Else If v="f" Then
		Return "zYE"
	Else If v="g" Then
		Return "jyd"
	Else If v="h" Then
		Return "rH4"
	Else If v="i" Then
		Return "TNu"
	Else If v="j" Then
		Return "MI7"
	Else If v="k" Then
		Return "er4"
	Else If v="l" Then
		Return "sGM"
	Else If v="m" Then
		Return "sgx"
	Else If v="n" Then
		Return "vBo"
	Else If v="o" Then
		Return "IAB"
	Else If v="p" Then
		Return "78G"
	Else If v="q" Then
		Return "0Yh"
	Else If v="r" Then
		Return "ub8"
	Else If v="s" Then
		Return "mxb"
	Else If v="t" Then
		Return "T3b"
	Else If v="u" Then
		Return "Kt4"
	Else If v="v" Then
		Return "apJ"
	Else If v="w" Then
		Return "p7w"
	Else If v="x" Then
		Return "5ph"
	Else If v="y" Then
		Return "KTy"
	Else If v="z" Then
		Return "pze"
	Else If v="A" Then
		Return "73i"
	Else If v="B" Then
		Return "qht"
	Else If v="C" Then
		Return "o6V"
	Else If v="D" Then
		Return "2vY"
	Else If v="E" Then
		Return "e6M"
	Else If v="F" Then
		Return "DUl"
	Else If v="G" Then
		Return "g5m"
	Else If v="H" Then
		Return "uHc"
	Else If v="I" Then
		Return "eM2"
	Else If v="J" Then
		Return "7LQ"
	Else If v="K" Then
		Return "CRM"
	Else If v="L" Then
		Return "tD1"
	Else If v="M" Then
		Return "TXe"
	Else If v="N" Then
		Return "MQD"
	Else If v="O" Then
		Return "IlG"
	Else If v="P" Then
		Return "Tu9"
	Else If v="Q" Then
		Return "NKn"
	Else If v="R" Then
		Return "rTs"
	Else If v="S" Then
		Return "8EK"
	Else If v="T" Then
		Return "7fs"
	Else If v="U" Then
		Return "273"
	Else If v="V" Then
		Return "sLn"
	Else If v="W" Then
		Return "9Qm"
	Else If v="X" Then
		Return "3wd"
	Else If v="Y" Then
		Return "I5i"
	Else If v="Z" Then
		Return "gMM"
	Else If v="~" Then
		Return "uLg"
	Else If v="`" Then
		Return "5R3"
	Else If v="!" Then
		Return "R4f"
	Else If v="#" Then
		Return "pCd"
	Else If v="$" Then
		Return "n64"
	Else If v="%" Then
		Return "wGm"
	Else If v="^" Then
		Return "rC1"
	Else If v="&" Then
		Return "WKl"
	Else If v="*" Then
		Return "j20"
	Else If v="(" Then
		Return "vgg"
	Else If v=")" Then
		Return "MTS"
	Else If v="_" Then
		Return "gpG"
	Else If v="-" Then
		Return "Rgj"
	Else If v="=" Then
		Return "Gdt"
	Else If v="+" Then
		Return "pEE"
	Else If v="/" Then
		Return "P2u"
	Else If v="?" Then
		Return "JYt"
	Else If v="<" Then
		Return "Bwu"
	Else If v=">" Then
		Return "IOM"
	Else If v="." Then
		Return "Mnk"
	Else If v="{" Then
		Return "UV8"
	Else If v="}" Then
		Return "q4z"
	Else If v="[" Then
		Return "zcg"
	Else If v="]" Then
		Return "AMp"
	Else If v="," Then
		Return "oLf"
	Else If v="ا" Then
		Return "Ukt"
	Else If v="آ" Then
		Return "Kcw"
	Else If v="ب" Then
		Return "pP0"
	Else If v="پ" Then
		Return "jCw"
	Else If v="ت" Then
		Return "TOG"
	Else If v="س" Then
		Return "onL"
	Else If v="ج" Then
		Return "gDw"
	Else If v="چ" Then
		Return "o2e"
	Else If v="ح" Then
		Return "725"
	Else If v="خ" Then
		Return "rMT"
	Else If v="د" Then
		Return "yHF"
	Else If v="ذ" Then
		Return "aew"
	Else If v="ر" Then
		Return "gvN"
	Else If v="ز" Then
		Return "3BR"
	Else If v="ژ" Then
		Return "y33"
	Else If v="س" Then
		Return "FAr"
	Else If v="ش" Then
		Return "A6T"
	Else If v="ص" Then
		Return "vVr"
	Else If v="ض" Then
		Return "Ts3"
	Else If v="ط" Then
		Return "G0e"
	Else If v="ظ" Then
		Return "7yE"
	Else If v="ع" Then
		Return "fEV"
	Else If v="غ" Then
		Return "ay0"
	Else If v="ف" Then
		Return "HMx"
	Else If v="ق" Then
		Return "fk8"
	Else If v="ک" Then
		Return "kgq"
	Else If v="گ" Then
		Return "2GE"
	Else If v="ل" Then
		Return "wbO"
	Else If v="م" Then
		Return "P16"
	Else If v="ن" Then
		Return "Uy5"
	Else If v="و" Then
		Return "2n4"
	Else If v="ه" Then
		Return "T2p"
	Else If v="ی" Then
		Return "T5q"
	Else If v="ء" Then
		Return "R2G"
	Else If v="ئ" Then
		Return "8lO"
	Else
		Return v & v & v
	End If
End Sub

'''''''''''''
' Dim lng As Int
'''''''''''''



Public Sub data_Decryption( t As String) As String
	lng =3


	Dim txt As String

	Dim tx2 As String
	Dim cdc(t.Length) As String

	txt = t

	For n = 0 To t.Length / lng - 1
		Dim x As String
		x = txt.Substring(txt.Length - lng)
		cdc(n) = sina_DEC(x)
		txt = txt.SubString2(0, txt.Length - lng)
	Next

	For b = 0 To cdc.Length - 1
		tx2 = cdc(b) & tx2
	Next
	''''''''''''''''''''
	Return tx2
End Sub


Public Sub sina_DEC(v As String) As String
	If v="mbW" Then
		Return "0"
	Else If v="ndn" Then
		Return "1"
	Else If v="tDr" Then
		Return "2"
	Else If v="KWM" Then
		Return "3"
	Else If v="E1E" Then
		Return "4"
	Else If v="JVA" Then
		Return "5"
	Else If v="HIS" Then
		Return "7"
	Else If v="JUb" Then
		Return "8"
	Else If v="at1" Then
		Return "9"
	Else If v="noe" Then
		Return "a"
	Else If v="fkH" Then
		Return "b"
	Else If v="S7g" Then
		Return "c"
	Else If v="06r" Then
		Return "d"
	Else If v="buW" Then
		Return "e"
	Else If v="zYE" Then
		Return "f"
	Else If v="jyd" Then
		Return "g"
	Else If v="rH4" Then
		Return "h"
	Else If v="TNu" Then
		Return "i"
	Else If v="MI7" Then
		Return "j"
	Else If v="er4" Then
		Return "k"
	Else If v="sGM" Then
		Return "l"
	Else If v="sgx" Then
		Return "m"
	Else If v="vBo" Then
		Return "n"
	Else If v="IAB" Then
		Return "o"
	Else If v="78G" Then
		Return "p"
	Else If v="0Yh" Then
		Return "q"
	Else If v="ub8" Then
		Return "r"
	Else If v="mxb" Then
		Return "s"
	Else If v="T3b" Then
		Return "t"
	Else If v="Kt4" Then
		Return "u"
	Else If v="apJ" Then
		Return "v"
	Else If v="p7w" Then
		Return "w"
	Else If v="5ph" Then
		Return "x"
	Else If v="KTy" Then
		Return "y"
	Else If v="pze" Then
		Return "z"
	Else If v="73i" Then
		Return "A"
	Else If v="qht" Then
		Return "B"
	Else If v="o6V" Then
		Return "C"
	Else If v="2vY" Then
		Return "D"
	Else If v="e6M" Then
		Return "E"
	Else If v="DUl" Then
		Return "F"
	Else If v="g5m" Then
		Return "G"
	Else If v="uHc" Then
		Return "H"
	Else If v="eM2" Then
		Return "I"
	Else If v="7LQ" Then
		Return "J"
	Else If v="CRM" Then
		Return "K"
	Else If v="tD1" Then
		Return "L"
	Else If v="TXe" Then
		Return "M"
	Else If v="MQD" Then
		Return "N"
	Else If v="IlG" Then
		Return "O"
	Else If v="Tu9" Then
		Return "P"
	Else If v="NKn" Then
		Return "Q"
	Else If v="rTs" Then
		Return "R"
	Else If v="8EK" Then
		Return "S"
	Else If v="7fs" Then
		Return "T"
	Else If v="273" Then
		Return "U"
	Else If v="sLn" Then
		Return "V"
	Else If v="9Qm" Then
		Return "W"
	Else If v="3wd" Then
		Return "X"
	Else If v="I5i" Then
		Return "Y"
	Else If v="gMM" Then
		Return "Z"
	Else If v="uLg" Then
		Return "~"
	Else If v="5R3" Then
		Return "`"
	Else If v="R4f" Then
		Return "!"
	Else If v="pCd" Then
		Return "#"
	Else If v="n64" Then
		Return "$"
	Else If v="wGm" Then
		Return "%"
	Else If v="rC1" Then
		Return "^"
	Else If v="WKl" Then
		Return "&"
	Else If v="j20" Then
		Return "*"
	Else If v="vgg" Then
		Return "("
	Else If v="MTS" Then
		Return ")"
	Else If v="gpG" Then
		Return "_"
	Else If v="Rgj" Then
		Return "-"
	Else If v="Gdt" Then
		Return "="
	Else If v="pEE" Then
		Return "+"
	Else If v="P2u" Then
		Return "/"
	Else If v="JYt" Then
		Return "?"
	Else If v="Bwu" Then
		Return "<"
	Else If v="IOM" Then
		Return ">"
	Else If v="Mnk" Then
		Return "."
	Else If v="UV8" Then
		Return "{"
	Else If v="q4z" Then
		Return "}"
	Else If v="zcg" Then
		Return "["
	Else If v="AMp" Then
		Return "]"
	Else If v="oLf" Then
		Return ","
	Else If v="Ukt" Then
		Return "ا"
	Else If v="Kcw" Then
		Return "آ"
	Else If v="pP0" Then
		Return "ب"
	Else If v="jCw" Then
		Return "پ"
	Else If v="TOG" Then
		Return "ت"
	Else If v="onL" Then
		Return "س"
	Else If v="gDw" Then
		Return "ج"
	Else If v="o2e" Then
		Return "چ"
	Else If v="725" Then
		Return "ح"
	Else If v="rMT" Then
		Return "خ"
	Else If v="yHF" Then
		Return "د"
	Else If v="aew" Then
		Return "ذ"
	Else If v="gvN" Then
		Return "ر"
	Else If v="3BR" Then
		Return "ز"
	Else If v="y33" Then
		Return "ژ"
	Else If v="FAr" Then
		Return "س"
	Else If v="A6T" Then
		Return "ش"
	Else If v="vVr" Then
		Return "ص"
	Else If v="Ts3" Then
		Return "ض"
	Else If v="G0e" Then
		Return "ط"
	Else If v="7yE" Then
		Return "ظ"
	Else If v="fEV" Then
		Return "ع"
	Else If v="ay0" Then
		Return "غ"
	Else If v="HMx" Then
		Return "ف"
	Else If v="fk8" Then
		Return "ق"
	Else If v="kgq" Then
		Return "ک"
	Else If v="2GE" Then
		Return "گ"
	Else If v="wbO" Then
		Return "ل"
	Else If v="P16" Then
		Return "م"
	Else If v="Uy5" Then
		Return "ن"
	Else If v="2n4" Then
		Return "و"
	Else If v="T2p" Then
		Return "ه"
	Else If v="T5q" Then
		Return "ی"
	Else If v="R2G" Then
		Return "ء"
	Else If v="8lO" Then
		Return "ئ"
	Else
		Return v.Substring(v.Length - 1)
	End If

End Sub