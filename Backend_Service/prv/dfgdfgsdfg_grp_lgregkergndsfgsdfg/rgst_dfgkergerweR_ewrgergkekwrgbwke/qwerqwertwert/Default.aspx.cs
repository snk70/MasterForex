using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
using System.Net.Mail;
using System.Net;
using System.Net.Sockets;




//Web-Page : 3

public partial class prv_dfgdfgsdfg_grp_lgregkergndsfgsdfg_rgst_dfgkergerweR_ewrgergkekwrgbwke_qwerqwertwert_Default : System.Web.UI.Page
{
    string us, ps;

    protected void Page_Load(object sender, EventArgs e)
    {
        sec_Class_1 sc = new sec_Class_1();

        try
        {
            if (Request.Form["potgw52894fo_dkgnndfg_gtkrtkjtrldrt"].ToString() != "dtpognerregjwerg" || Request.Form["sina_rigergbwlergwer"].ToString() != "wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe" || Request.Form["ya_heidar"].ToString() != "sdgsergwer_313_asdfkakefkqwelfqwef34875234589" || Request.Form["ma"].ToString() == "" || Request.Form["ps"].ToString() == "")
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

        //////us = "asdasf@afas.com";
        //////ps = "1234";
        //////////////////////////////////////////////////////////////


        us = sc.Decryption(Request.Form["ma"].ToString());
        //us = "sinakordestani@gmail.com";

        SqlConnection con1 = new SqlConnection(config_web_class.connection_String);
        con1.Open();

        SqlCommand com2 = new SqlCommand("Select r From [tbl_users] Where ([email]='" + us + "')", con1);
        SqlDataReader dr1 = com2.ExecuteReader();
        dr1.Read();

        if (dr1.HasRows)
        {
            Response.Write(sc.Encryption("repeat_mail"));
            return;
        }
        else
        {
            com2.Dispose();
            dr1.Dispose();
            dr1.Close();
            com2 = new SqlCommand("Insert Into [tbl_users] ([email],[password],[license_money],[phone_number],[expire_number],[status]) Values ('" + sc.Decryption(Request.Form["ma"]) + "','" + Request.Form["ps"] + "','" + sc.Decryption(Request.Form["lcs"].ToString()) + "','" + sc.Decryption(Request.Form["phn"].ToString()) + "','" + sc.Encryption("0") + "','0')", con1);
            if (com2.ExecuteNonQuery() == 1)
            {
                #region Send Verify Email to user


                MailMessage mg = new MailMessage();
                SmtpClient sm = new SmtpClient(config_web_class.mail_base);

                mg.Subject = "Verify your E-mail";
                mg.From = new MailAddress(config_web_class.mail_address);


                mg.To.Add(us);

                mg.IsBodyHtml = false;
                mg.Body = config_web_class.Web_URL + "/" + "confirm_e-mail/Default.aspx?v=" + sc.Encryption(us);

                sm.Port = 25;
                sm.Credentials = new NetworkCredential(config_web_class.mail_address, config_web_class.mail_pass);
                sm.EnableSsl = false;
                sm.Send(mg);



                Response.Write(sc.Encryption("ok_send_mail"));
                #endregion


            }


            else
            {
                Response.Write(sc.Encryption("error"));
            }


        }


        con1.Close();
    }
}