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

public partial class prv_dfgdfgsdfg_grp_lgregkergndsfgsdfg_sdfgsdfgsdfg_grp_asfsaf_soisernr_dfgjkl_wbg_fdgnk4_Default : System.Web.UI.Page
{
    //Web-Page : 4

    string us, ps;

    protected void Page_Load(object sender, EventArgs e)
    {

        sec_Class_1 sc = new sec_Class_1();

        try
        {
            if (Request.Form["potgw52894fo_dkgnndfg_gtkrtkjtrldrt"].ToString() != "dtpognerregjwerg" || Request.Form["sina_rigergbwlergwer"].ToString() != "wewenofewni_348712fdger56gwer6gwe6gasdcasdqerqwe" || Request.Form["ya_heidar"].ToString() != "sdgsergwer_313_asdfkakefkqwelfqwef34875234589" || Request.Form["usn"].ToString() == "")
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

        us = sc.Decryption(Request.Form["usn"].ToString());

        //////us = "kordestani_sina@yahoo.com";
        //////////////////////////////////////////////////////////////



        SqlConnection con1 = new SqlConnection(config_web_class.connection_String);
        con1.Open();

        SqlCommand com2 = new SqlCommand("Select r,status,password From [tbl_users] Where ([email]='" + us + "')", con1);
        SqlDataReader dr1 = com2.ExecuteReader();
        dr1.Read();

        if (dr1.HasRows && dr1["status"].ToString() != "2")
        {

            #region Send mail

            MailMessage mg = new MailMessage();
            SmtpClient sm = new SmtpClient(config_web_class.mail_base);

            mg.Subject = "Your password";
            mg.From = new MailAddress(config_web_class.mail_address);


            mg.To.Add(us);

            mg.IsBodyHtml = false;
            mg.Body = "Your password in \" Master Forex Shahin \" App is : " + sc.Decryption(dr1["password"].ToString());

            sm.Port = 25;
            sm.Credentials = new NetworkCredential(config_web_class.mail_address, config_web_class.mail_pass);
            sm.EnableSsl = false;
            sm.Send(mg);


            #endregion

            Response.Write(sc.Encryption("ok"));
        }
        else
        {
            Response.Write(sc.Encryption("wrong_info"));
        }


        con1.Close();
    }
}