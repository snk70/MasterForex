using System;
using System.Data.SqlClient;
using System.Collections.Generic;


//Web-Page : 1

public partial class prv_dfgdfgsdfg_grp_lgregkergndsfgsdfg_sdfgsdfgsdfg_grp_asfsaf_pujkdthm_sel_grp_dgkewbbger_fgsdfgsdfgsdfgsdfgsdfg_SG_sdfgsdfg : System.Web.UI.Page
{
    string us, ps;

    protected void Page_Load(object sender, EventArgs e)
    {
        sec_Class_1 sc = new sec_Class_1();

        try
        {
            if (Request.Form["potgw52894fo_dkgnndfg_gtkrtkjtrldrt"].ToString() != "dtpognerregjwerg" || Request.Form["sina_rigergbwlergwer"].ToString() != "wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe" || Request.Form["ya_heidar"].ToString() != "sdgsergwer_313_asdfkakefkqwelfqwef34875234589" || Request.Form["usn"].ToString() == "" || Request.Form["psd"].ToString() == "")
            {
                Response.Write(sc.Encryption("hnd"));
                return;
            }
        }
        catch
        {
            Response.Write(sc.Encryption("hnd"));
            return;
        }

        //////////us = "kordestani_sina@yahoo.com";
        //////////ps = sc.Encryption("1234");

        us = sc.Decryption(Request.Form["usn"].ToString());
        ps = Request.Form["psd"].ToString();


        SqlConnection con1 = new SqlConnection(config_web_class.connection_String);
        con1.Open();


        #region بررسی صحت اطلاعات کاربر
        SqlCommand com = new SqlCommand("Select r,status,expire_number From [tbl_users] Where ([email]='" + us + "' and [password]='" + ps + "')", con1);
        SqlDataReader dr = com.ExecuteReader();
        dr.Read();

        if (dr.HasRows == false)
        {
            Response.Write(sc.Encryption("wrong_info"));
            return;
        }
        else
        {
            if (dr["status"].ToString() == "1" && (Convert.ToDouble(sc.Decryption(dr["expire_number"].ToString())) - get_days_time.get_days_YourTimes()) <= 0)
            {
                //اعتبار شارژ اکانت تمام شده
                Response.Write(sc.Encryption("expire_time"));
                return;
            }
            else if (dr["status"].ToString() == "0")
            {
                //تأیید ایمیل نشده
                Response.Write(sc.Encryption("no_verify"));
                return;
            }
            else if (dr["status"].ToString() == "2")
            {
                //تعلیق حساب
                Response.Write(sc.Encryption("wrong_info"));
                return;
            }
            else if (dr["status"].ToString() == "1" && (Convert.ToDouble(sc.Decryption(dr["expire_number"].ToString())) - get_days_time.get_days_YourTimes()) > 0)
            {
                //درسته
                //Response.Write("ok");
                //return;
            }
            else
            {
                Response.Write(sc.Encryption("wrong_info"));
                return;
            }
        }

        com.Dispose();
        dr.Dispose();
        dr.Close();
        #endregion

        SqlCommand com2 = new SqlCommand("Select * From [tbl_signals] ORDER BY [sort_num] DESC", con1);
        SqlDataReader dr1 = com2.ExecuteReader();
        Json_Creater.Json js1 = new Json_Creater.Json();
        List<string> js_keys = new List<string>();
        List<string> js_vals = new List<string>();


        while (dr1.Read())
        {
            js_vals.Clear();
            js_keys.Clear();

            js_keys.Add("type");
            js_vals.Add(dr1["type"].ToString());

            js_keys.Add("buy_sel");
            js_vals.Add(dr1["buy_sel"].ToString());

            js_keys.Add("tp");
            js_vals.Add(dr1["tp"].ToString());

            js_keys.Add("sl");
            js_vals.Add(dr1["sl"].ToString());

            js_keys.Add("time");
            js_vals.Add(dr1["time"].ToString());

            js_keys.Add("buy");
            js_vals.Add(dr1["buy"].ToString());

            js_keys.Add("date");
            js_vals.Add(dr1["date"].ToString());

            js_keys.Add("is_signal");
            js_vals.Add(dr1["is_signal"].ToString());

            js1.Add_Lists(js_keys, js_vals);

        }



        Response.Write(sc.Encryption(js1.Json_Encode()));
        //Response.Write(js1.Json_Encode());

    }
}