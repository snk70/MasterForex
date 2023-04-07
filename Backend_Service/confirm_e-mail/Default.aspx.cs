using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class config_e_mail_Default : System.Web.UI.Page
{
    string us;

    protected void Page_Load(object sender, EventArgs e)
    {

        sec_Class_1 sc = new sec_Class_1();

        try
        {
            if (Request.QueryString["v"].ToString() == "")
            {
                //Response.Write("if");
                Response.Redirect("~/error.html");
                return;
            }

            us = sc.Decryption(Request.QueryString["v"].ToString());

        }
        catch
        {
            //Response.Write("catch");
            Response.Redirect("~/error.html");
            return;
        }


        //////us = "kordestani_sina@yahoo.com";
        //////////////////////////////////////////////////////////////


        SqlConnection con1 = new SqlConnection(config_web_class.connection_String);
        con1.Open();

        SqlCommand com2 = new SqlCommand("Select r,status,email From [tbl_users] Where (email='" + us + "')", con1);
        SqlDataReader dr1 = com2.ExecuteReader();
        dr1.Read();


        if (dr1.HasRows==true && dr1["status"].ToString() == "0")
        {
            dr1.Close();
            com2.Dispose();
            com2 = new SqlCommand("Update [tbl_users] set status='1' Where (email='"+us+"')", con1);
            if (com2.ExecuteNonQuery().ToString() == "1")
            {
                dv1.Visible = true;
            }
            else
            {

            }
        }
        else
        {
            //Response.Write("else");
            Response.Redirect("~/error.html");
            return;
        }
        com2.Dispose();
        com2.Clone();
        dr1.Close();
        dr1.Dispose();
        con1.Close();

    }

}