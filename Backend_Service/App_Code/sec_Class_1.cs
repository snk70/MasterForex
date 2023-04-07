//using System;
//using System.Text;

   public class sec_Class_1
    {

        public string Encryption(string Val)
        {
            Val=Base64Encode(Val);
            Val=data_Encryption(Val);
            Val = Val.Substring(3, Val.Length - 3)+Val.Substring(0,3);
            Val = Val.Substring(3, Val.Length - 3) + Val.Substring(0, 3);
            return Val;
        }

        public string Decryption(string Val)
        {
            Val = Val.Substring(Val.Length - 3, 3)+Val.Substring(0,Val.Length-3);
            Val = Val.Substring(Val.Length - 3, 3) + Val.Substring(0, Val.Length - 3);
            Val = data_Decryption(Val);
            Val = Base64Decode(Val);
            return Val;
        }
        public static string Base64Encode(string plainText)
        {
            var plainTextBytes = System.Text.Encoding.UTF8.GetBytes(plainText);
            return System.Convert.ToBase64String(plainTextBytes);
        }


        public static string Base64Decode(string base64EncodedData)
        {
            var base64EncodedBytes = System.Convert.FromBase64String(base64EncodedData);
            return System.Text.Encoding.UTF8.GetString(base64EncodedBytes);
        }
        public string data_Encryption(string inp)
        {
            string txt = "", tx2, tx1 = inp;
            for (int i = 0; i < inp.Length; i++)
            {
                tx2 = tx1.Remove(1, tx1.Length - 1);
                tx1 = tx1.Remove(0, 1);
                txt += sina_ENC(tx2);
            }
            return txt;
        }


        public string sina_ENC(string v)
        {
            if (v == "0")
            {
                return "mbW";
            }
            else if (v == "1")
            {
                return "ndn";
            }
            else if (v == "2")
            {
                return "tDr";
            }
            else if (v == "3")
            {
                return "KWM";
            }
            else if (v == "4")
            {
                return "E1E";
            }
            else if (v == "5")
            {
                return "JVA";
            }
            else if (v == "7")
            {
                return "HIS";
            }
            else if (v == "8")
            {
                return "JUb";
            }
            else if (v == "9")
            {
                return "at1";
            }
            else if (v == "a")
            {
                return "noe";
            }
            else if (v == "b")
            {
                return "fkH";
            }
            else if (v == "c")
            {
                return "S7g";
            }
            else if (v == "d")
            {
                return "06r";
            }
            else if (v == "e")
            {
                return "buW";
            }
            else if (v == "f")
            {
                return "zYE";
            }
            else if (v == "g")
            {
                return "jyd";
            }
            else if (v == "h")
            {
                return "rH4";
            }
            else if (v == "i")
            {
                return "TNu";
            }
            else if (v == "j")
            {
                return "MI7";
            }
            else if (v == "k")
            {
                return "er4";
            }
            else if (v == "l")
            {
                return "sGM";
            }
            else if (v == "m")
            {
                return "sgx";
            }
            else if (v == "n")
            {
                return "vBo";
            }
            else if (v == "o")
            {
                return "IAB";
            }
            else if (v == "p")
            {
                return "78G";
            }
            else if (v == "q")
            {
                return "0Yh";
            }
            else if (v == "r")
            {
                return "ub8";
            }
            else if (v == "s")
            {
                return "mxb";
            }
            else if (v == "t")
            {
                return "T3b";
            }
            else if (v == "u")
            {
                return "Kt4";
            }
            else if (v == "v")
            {
                return "apJ";
            }
            else if (v == "w")
            {
                return "p7w";
            }
            else if (v == "x")
            {
                return "5ph";
            }
            else if (v == "y")
            {
                return "KTy";
            }
            else if (v == "z")
            {
                return "pze";
            }
            else if (v == "A")
            {
                return "73i";
            }
            else if (v == "B")
            {
                return "qht";
            }
            else if (v == "C")
            {
                return "o6V";
            }
            else if (v == "D")
            {
                return "2vY";
            }
            else if (v == "E")
            {
                return "e6M";
            }
            else if (v == "F")
            {
                return "DUl";
            }
            else if (v == "G")
            {
                return "g5m";
            }
            else if (v == "H")
            {
                return "uHc";
            }
            else if (v == "I")
            {
                return "eM2";
            }
            else if (v == "J")
            {
                return "7LQ";
            }
            else if (v == "K")
            {
                return "CRM";
            }
            else if (v == "L")
            {
                return "tD1";
            }
            else if (v == "M")
            {
                return "TXe";
            }
            else if (v == "N")
            {
                return "MQD";
            }
            else if (v == "O")
            {
                return "IlG";
            }
            else if (v == "P")
            {
                return "Tu9";
            }
            else if (v == "Q")
            {
                return "NKn";
            }
            else if (v == "R")
            {
                return "rTs";
            }
            else if (v == "S")
            {
                return "8EK";
            }
            else if (v == "T")
            {
                return "7fs";
            }
            else if (v == "U")
            {
                return "273";
            }
            else if (v == "V")
            {
                return "sLn";
            }
            else if (v == "W")
            {
                return "9Qm";
            }
            else if (v == "X")
            {
                return "3wd";
            }
            else if (v == "Y")
            {
                return "I5i";
            }
            else if (v == "Z")
            {
                return "gMM";
            }
            else if (v == "~")
            {
                return "uLg";
            }
            else if (v == "`")
            {
                return "5R3";
            }
            else if (v == "!")
            {
                return "R4f";
            }
            else if (v == "#")
            {
                return "pCd";
            }
            else if (v == "$")
            {
                return "n64";
            }
            else if (v == "%")
            {
                return "wGm";
            }
            else if (v == "^")
            {
                return "rC1";
            }
            else if (v == "&")
            {
                return "WKl";
            }
            else if (v == "*")
            {
                return "j20";
            }
            else if (v == "(")
            {
                return "vgg";
            }
            else if (v == ")")
            {
                return "MTS";
            }
            else if (v == "_")
            {
                return "gpG";
            }
            else if (v == "-")
            {
                return "Rgj";
            }
            else if (v == "=")
            {
                return "Gdt";
            }
            else if (v == "+")
            {
                return "pEE";
            }
            else if (v == "/")
            {
                return "P2u";
            }
            else if (v == "?")
            {
                return "JYt";
            }
            else if (v == "<")
            {
                return "Bwu";
            }
            else if (v == ">")
            {
                return "IOM";
            }
            else if (v == ".")
            {
                return "Mnk";
            }
            else if (v == "{")
            {
                return "UV8";
            }
            else if (v == "}")
            {
                return "q4z";
            }
            else if (v == "[")
            {
                return "zcg";
            }
            else if (v == "]")
            {
                return "AMp";
            }
            else if (v == ",")
            {
                return "oLf";
            }
            else if (v == "ا")
            {
                return "Ukt";
            }
            else if (v == "آ")
            {
                return "Kcw";
            }
            else if (v == "ب")
            {
                return "pP0";
            }
            else if (v == "پ")
            {
                return "jCw";
            }
            else if (v == "ت")
            {
                return "TOG";
            }
            else if (v == "س")
            {
                return "onL";
            }
            else if (v == "ج")
            {
                return "gDw";
            }
            else if (v == "چ")
            {
                return "o2e";
            }
            else if (v == "ح")
            {
                return "725";
            }
            else if (v == "خ")
            {
                return "rMT";
            }
            else if (v == "د")
            {
                return "yHF";
            }
            else if (v == "ذ")
            {
                return "aew";
            }
            else if (v == "ر")
            {
                return "gvN";
            }
            else if (v == "ز")
            {
                return "3BR";
            }
            else if (v == "ژ")
            {
                return "y33";
            }
            else if (v == "س")
            {
                return "FAr";
            }
            else if (v == "ش")
            {
                return "A6T";
            }
            else if (v == "ص")
            {
                return "vVr";
            }
            else if (v == "ض")
            {
                return "Ts3";
            }
            else if (v == "ط")
            {
                return "G0e";
            }
            else if (v == "ظ")
            {
                return "7yE";
            }
            else if (v == "ع")
            {
                return "fEV";
            }
            else if (v == "غ")
            {
                return "ay0";
            }
            else if (v == "ف")
            {
                return "HMx";
            }
            else if (v == "ق")
            {
                return "fk8";
            }
            else if (v == "ک")
            {
                return "kgq";
            }
            else if (v == "گ")
            {
                return "2GE";
            }
            else if (v == "ل")
            {
                return "wbO";
            }
            else if (v == "م")
            {
                return "P16";
            }
            else if (v == "ن")
            {
                return "Uy5";
            }
            else if (v == "و")
            {
                return "2n4";
            }
            else if (v == "ه")
            {
                return "T2p";
            }
            else if (v == "ی")
            {
                return "T5q";
            }
            else if (v == "ء")
            {
                return "R2G";
            }
            else if (v == "ئ")
            {
                return "8lO";
            }
            else
            {
                return v + v + v;
            }
        }

        int lng = 3;



        public string data_Decryption(string inp)
        {
            string txt = "", tx2, tx1 = inp;
            for (int i = 0; i < inp.Length / lng; i++)
            {
                tx2 = tx1.Remove(lng, tx1.Length - lng);
                tx1 = tx1.Remove(0, lng);
                txt += sina_DEC(tx2);
            }
            return txt;
        }


        public string sina_DEC(string v)
        {
            if (v == "mbW")
            {
                return "0";
            }
            else if (v == "ndn")
            {
                return "1";
            }
            else if (v == "tDr")
            {
                return "2";
            }
            else if (v == "KWM")
            {
                return "3";
            }
            else if (v == "E1E")
            {
                return "4";
            }
            else if (v == "JVA")
            {
                return "5";
            }
            else if (v == "HIS")
            {
                return "7";
            }
            else if (v == "JUb")
            {
                return "8";
            }
            else if (v == "at1")
            {
                return "9";
            }
            else if (v == "noe")
            {
                return "a";
            }
            else if (v == "fkH")
            {
                return "b";
            }
            else if (v == "S7g")
            {
                return "c";
            }
            else if (v == "06r")
            {
                return "d";
            }
            else if (v == "buW")
            {
                return "e";
            }
            else if (v == "zYE")
            {
                return "f";
            }
            else if (v == "jyd")
            {
                return "g";
            }
            else if (v == "rH4")
            {
                return "h";
            }
            else if (v == "TNu")
            {
                return "i";
            }
            else if (v == "MI7")
            {
                return "j";
            }
            else if (v == "er4")
            {
                return "k";
            }
            else if (v == "sGM")
            {
                return "l";
            }
            else if (v == "sgx")
            {
                return "m";
            }
            else if (v == "vBo")
            {
                return "n";
            }
            else if (v == "IAB")
            {
                return "o";
            }
            else if (v == "78G")
            {
                return "p";
            }
            else if (v == "0Yh")
            {
                return "q";
            }
            else if (v == "ub8")
            {
                return "r";
            }
            else if (v == "mxb")
            {
                return "s";
            }
            else if (v == "T3b")
            {
                return "t";
            }
            else if (v == "Kt4")
            {
                return "u";
            }
            else if (v == "apJ")
            {
                return "v";
            }
            else if (v == "p7w")
            {
                return "w";
            }
            else if (v == "5ph")
            {
                return "x";
            }
            else if (v == "KTy")
            {
                return "y";
            }
            else if (v == "pze")
            {
                return "z";
            }
            else if (v == "73i")
            {
                return "A";
            }
            else if (v == "qht")
            {
                return "B";
            }
            else if (v == "o6V")
            {
                return "C";
            }
            else if (v == "2vY")
            {
                return "D";
            }
            else if (v == "e6M")
            {
                return "E";
            }
            else if (v == "DUl")
            {
                return "F";
            }
            else if (v == "g5m")
            {
                return "G";
            }
            else if (v == "uHc")
            {
                return "H";
            }
            else if (v == "eM2")
            {
                return "I";
            }
            else if (v == "7LQ")
            {
                return "J";
            }
            else if (v == "CRM")
            {
                return "K";
            }
            else if (v == "tD1")
            {
                return "L";
            }
            else if (v == "TXe")
            {
                return "M";
            }
            else if (v == "MQD")
            {
                return "N";
            }
            else if (v == "IlG")
            {
                return "O";
            }
            else if (v == "Tu9")
            {
                return "P";
            }
            else if (v == "NKn")
            {
                return "Q";
            }
            else if (v == "rTs")
            {
                return "R";
            }
            else if (v == "8EK")
            {
                return "S";
            }
            else if (v == "7fs")
            {
                return "T";
            }
            else if (v == "273")
            {
                return "U";
            }
            else if (v == "sLn")
            {
                return "V";
            }
            else if (v == "9Qm")
            {
                return "W";
            }
            else if (v == "3wd")
            {
                return "X";
            }
            else if (v == "I5i")
            {
                return "Y";
            }
            else if (v == "gMM")
            {
                return "Z";
            }
            else if (v == "uLg")
            {
                return "~";
            }
            else if (v == "5R3")
            {
                return "`";
            }
            else if (v == "R4f")
            {
                return "!";
            }
            else if (v == "pCd")
            {
                return "#";
            }
            else if (v == "n64")
            {
                return "$";
            }
            else if (v == "wGm")
            {
                return "%";
            }
            else if (v == "rC1")
            {
                return "^";
            }
            else if (v == "WKl")
            {
                return "&";
            }
            else if (v == "j20")
            {
                return "*";
            }
            else if (v == "vgg")
            {
                return "(";
            }
            else if (v == "MTS")
            {
                return ")";
            }
            else if (v == "gpG")
            {
                return "_";
            }
            else if (v == "Rgj")
            {
                return "-";
            }
            else if (v == "Gdt")
            {
                return "=";
            }
            else if (v == "pEE")
            {
                return "+";
            }
            else if (v == "P2u")
            {
                return "/";
            }
            else if (v == "JYt")
            {
                return "?";
            }
            else if (v == "Bwu")
            {
                return "<";
            }
            else if (v == "IOM")
            {
                return ">";
            }
            else if (v == "Mnk")
            {
                return ".";
            }
            else if (v == "UV8")
            {
                return "{";
            }
            else if (v == "q4z")
            {
                return "}";
            }
            else if (v == "zcg")
            {
                return "[";
            }
            else if (v == "AMp")
            {
                return "]";
            }
            else if (v == "oLf")
            {
                return ",";
            }
            else if (v == "Ukt")
            {
                return "ا";
            }
            else if (v == "Kcw")
            {
                return "آ";
            }
            else if (v == "pP0")
            {
                return "ب";
            }
            else if (v == "jCw")
            {
                return "پ";
            }
            else if (v == "TOG")
            {
                return "ت";
            }
            else if (v == "onL")
            {
                return "س";
            }
            else if (v == "gDw")
            {
                return "ج";
            }
            else if (v == "o2e")
            {
                return "چ";
            }
            else if (v == "725")
            {
                return "ح";
            }
            else if (v == "rMT")
            {
                return "خ";
            }
            else if (v == "yHF")
            {
                return "د";
            }
            else if (v == "aew")
            {
                return "ذ";
            }
            else if (v == "gvN")
            {
                return "ر";
            }
            else if (v == "3BR")
            {
                return "ز";
            }
            else if (v == "y33")
            {
                return "ژ";
            }
            else if (v == "FAr")
            {
                return "س";
            }
            else if (v == "A6T")
            {
                return "ش";
            }
            else if (v == "vVr")
            {
                return "ص";
            }
            else if (v == "Ts3")
            {
                return "ض";
            }
            else if (v == "G0e")
            {
                return "ط";
            }
            else if (v == "7yE")
            {
                return "ظ";
            }
            else if (v == "fEV")
            {
                return "ع";
            }
            else if (v == "ay0")
            {
                return "غ";
            }
            else if (v == "HMx")
            {
                return "ف";
            }
            else if (v == "fk8")
            {
                return "ق";
            }
            else if (v == "kgq")
            {
                return "ک";
            }
            else if (v == "2GE")
            {
                return "گ";
            }
            else if (v == "wbO")
            {
                return "ل";
            }
            else if (v == "P16")
            {
                return "م";
            }
            else if (v == "Uy5")
            {
                return "ن";
            }
            else if (v == "2n4")
            {
                return "و";
            }
            else if (v == "T2p")
            {
                return "ه";
            }
            else if (v == "T5q")
            {
                return "ی";
            }
            else if (v == "R2G")
            {
                return "ء";
            }
            else if (v == "8lO")
            {
                return "ئ";
            }
            else
            {
                return v.Remove(1);
            }
        }
    }
