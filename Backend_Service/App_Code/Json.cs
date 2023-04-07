/*

راهنما :

برای کد گشایی :
استفاده از حالت By Number :

در این حالت شماره مپ را وارد کرده و سپس تمام کلید ها به همراه مقدار های آنان در v , k ریخته میشود

با استفاده از حالت معمولی :
در این حالت پس از دادن مقدار کد ، کلید ها و مقدار هایشان در k , v به ترتیب ذخیره شده و میتوان از آن استفاده کرد . همچنین اندیس آن ها در متغیر Json_Number ذخیره میشود


برای کد کردن :
 لیست های مپ را با تابع Add_List اضافه کرده و سپس تابع Json_Encode را فراخوانی و از خروجی آن استفاده میکنیم .


توابع اصلی برای کد گشایی :

- Decomposition_Json که با دادن مقدار کد شده json ، کلید ها و مقدار های دسته بندی شده ( مپ ) را بر میگرداند

- Decomposition_Maps که با وارد کردن مپ ، کلید و مقدار را در متغیر های لیست v , k میریزد .
 



*/


using System;
using System.Collections.Generic;

namespace Json_Creater
{
    public class Json
    {



        List<string> value = new List<string>();
        List<string> key = new List<string>();
        public List<string> k = new List<string>();
        public List<string> v = new List<string>();
        List<string> Encode_Lists = new List<string>();
        public Int32 Json_Number;


        public void Add_Lists(List<string> json_key, List<string> Json_Value)
        {
            //key.Clear();
            //value.Clear();
            //////////////////////
            key = json_key;
            value = Json_Value;
            Encode_Lists.Add(Encode());


        }

        public string Json_Encode()
        {

            string str = "[";

            for (int i = 0; i < Encode_Lists.Count; i++)
            {

                if (i != Encode_Lists.Count - 1)
                {
                    str += Encode_Lists[i] + ",";
                }
                else
                {
                    str += Encode_Lists[i];
                }


            }

            return str + "]";

        }


        public void Decomposition_Maps(string Json_Encoded)
        {
            v.Clear();
            k.Clear();

            //////////////////////////////////////////////////////////ریختن کلید به همراه مقدار در متغیر Map_String

            List<string> Map_String = new List<string>();
            Int32 cama_Pos = 0;
            Int32 kot_Pos = 0;
            Json_Encoded = Json_Encoded.Substring(0, Json_Encoded.Length - 1) + ",\"" + "}";

            //Clipboard.SetText(Json_Encoded);

            string s = "";

            while (Json_Encoded.IndexOf("\",\"", cama_Pos + 1) != -1)
            {
                kot_Pos = Json_Encoded.IndexOf("\"", cama_Pos + 1);
                cama_Pos = Json_Encoded.IndexOf("\",\"", cama_Pos + 1);
                Map_String.Add(Json_Encoded.Substring(kot_Pos + 1, cama_Pos - (kot_Pos + 1)));
            }

            for (int i = 0; i < Map_String.Count; i++)
            {
                s += Map_String[i] + "\n";

            }



            ///////////////////////////////////////////////////////////////تجزیه کلید و مقدار و ریختن آن ها در k و v

            for (int j = 0; j < Map_String.Count; j++)
            {
                k.Add(Map_String[j].Substring(0, Map_String[j].IndexOf("\":\"")));
                v.Add(Map_String[j].Substring(Map_String[j].IndexOf("\":\"") + 3, Map_String[j].Length - (Map_String[j].IndexOf("\":\"") + 3)));
                Json_Number = j;
            }

        }




        public void Json_Decode(string Encode_text)
        {
            v.Clear();
            k.Clear();

            Encode_text = Encode_text.Substring(0, Encode_text.Length - 1) + ",{" + "]";
            Int32 Akolad_Pos = 0, Cama_Pos = 0;

            while (Encode_text.IndexOf("{\"", Akolad_Pos + 1) != -1)
            {

                Akolad_Pos = Encode_text.IndexOf("{\"", Akolad_Pos + 1);
                Cama_Pos = Encode_text.IndexOf(",{", Akolad_Pos + 1);

                String str = Encode_text.Substring(Akolad_Pos, Cama_Pos - Akolad_Pos);

                Decode(str);
            }

        }

        public List<string> Decomposition_Json(string Encode_text)
        {
            List<string> lst_Maps = new List<string>();
            Encode_text = Encode_text.Substring(0, Encode_text.Length - 1) + ",{" + "]";
            Int32 Akolad_Pos = 0, Cama_Pos = 0;

            while (Encode_text.IndexOf("{\"", Akolad_Pos + 1) != -1)
            {

                Akolad_Pos = Encode_text.IndexOf("{\"", Akolad_Pos + 1);
                Cama_Pos = Encode_text.IndexOf(",{", Akolad_Pos + 1);

                String str = Encode_text.Substring(Akolad_Pos, Cama_Pos - Akolad_Pos);

                lst_Maps.Add(str);
            }

            return lst_Maps;
        }



        public void Json_Decode_ByNumber(string Encode_text, int position)
        {
            int p = -1;
            v.Clear();
            k.Clear();
            //position--;

            Encode_text = Encode_text.Substring(0, Encode_text.Length - 1) + ",{" + "]";
            Int32 Akolad_Pos = 0, Cama_Pos = 0;

            while (Encode_text.IndexOf("{\"", Akolad_Pos + 1) != -1)
            {

                Akolad_Pos = Encode_text.IndexOf("{\"", Akolad_Pos + 1);
                Cama_Pos = Encode_text.IndexOf(",{", Akolad_Pos + 1);
                p++;
                k.Clear();
                v.Clear();
                if (p == position)
                {

                    String str = Encode_text.Substring(Akolad_Pos, Cama_Pos - Akolad_Pos);
                    Decode(str);

                }

            }

        }

        private string Encode()
        {

            string str = "";
            str = "{";
            for (int i = 0; i < key.Count; i++)
            {
                if (i - 1 >= 0)
                {
                    str += ",";
                }

                str += "\"" + key[i] + "\":\"" + value[i] + "\"";

            }
            str += "}";


            return str;
        }


        private void Decode(string Json_Encoded)
        {
            //////////////////////////////////////////////////////////ریختن کلید به همراه مقدار در متغیر Map_String

            List<string> Map_String = new List<string>();
            Int32 cama_Pos = 0;
            Int32 kot_Pos = 0;
            Json_Encoded = Json_Encoded.Substring(0, Json_Encoded.Length - 1) + ",\"" + "}";

            //Clipboard.SetText(Json_Encoded);

            string s = "";

            while (Json_Encoded.IndexOf("\",\"", cama_Pos + 1) != -1)
            {
                kot_Pos = Json_Encoded.IndexOf("\"", cama_Pos + 1);
                cama_Pos = Json_Encoded.IndexOf("\",\"", cama_Pos + 1);
                Map_String.Add(Json_Encoded.Substring(kot_Pos + 1, cama_Pos - (kot_Pos + 1)));
            }

            for (int i = 0; i < Map_String.Count; i++)
            {
                s += Map_String[i] + "\n";

            }



            ///////////////////////////////////////////////////////////////تجزیه کلید و مقدار و ریختن آن ها در k و v

            for (int j = 0; j < Map_String.Count; j++)
            {
                k.Add(Map_String[j].Substring(0, Map_String[j].IndexOf("\":\"")));
                v.Add(Map_String[j].Substring(Map_String[j].IndexOf("\":\"") + 3, Map_String[j].Length - (Map_String[j].IndexOf("\":\"") + 3)));
                Json_Number = j;
            }

        }


    }

   



}


