using System;
using System.Globalization;
using System.Collections.Generic;
//using System.Linq;
using System.Data.SqlClient;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class prv_asfsadfs_personal_information_sdfgdfgdfgdsafjadsjf_mnggwergwer_asdfgsdggregedfknkefwe_pnl_sdfkgus_serkneg : System.Web.UI.Page
{
    //مدیریت کاربران

    sec_Class_1 sc = new sec_Class_1();
    protected void Page_Load(object sender, EventArgs e)
    {

        try
        {
            if (Session["kdxfgnskgergkjdfgneeifwefjogreigenrgbeugbergerierirewt3487438257uirthreb34r734834fu34c"].ToString() == "login_sdfrgernikgvabaiwenaksjdvaskv_us")
            {
            }
            else
            {
                Response.Redirect("~/prv/asfsadfs_personal_information_sdfgdfgdfgdsafjadsjf/mnggwergwer_asdfgsdggregedfknkefwe_pnl/dfasdfasd.aspx");
            }

        }
        catch
        {
            Session.RemoveAll();
            Response.Redirect("~/prv/asfsadfs_personal_information_sdfgdfgdfgdsafjadsjf/mnggwergwer_asdfgsdggregedfknkefwe_pnl/dfasdfasd.aspx");
        }


        btn_add.Text = "Add";

        Session.Timeout = 12000;

        try
        {
            if (Session["mode"].ToString() != "edit")
            {
                try
                {
                    if (Request.Form["opr"].ToString() == "edit")
                    {
                        edit_signal_mode();
                    }
                    else
                    {
                        Session["mode"] = "insert";
                    }
                }
                catch
                {
                    Session["mode"] = "insert";
                }
            }
            else
            {
                //Session["mode"] = "insert";
            }
        }
        catch
        {
            Session["mode"] = "insert";
        }


        try
        {
            if (Request.Form["opr"].ToString() == "edit")
            {
                edit_signal_mode();
            }
        }
        catch
        {
        }


    }



    void edit_signal_mode()
    {
        btn_add.Text = "OK";

        sec_Class_1 sc = new sec_Class_1();

        Session["mode"] = "edit";
        Session["r"] = Request.Form["r"].ToString();
        //////////////////////////
        SqlConnection con = new SqlConnection(config_web_class.connection_String);
        con.Open();
        //////////////////////////
        SqlCommand com_sg = new SqlCommand("Select * From tbl_users Where (r='" + Session["r"].ToString() + "')", con);
        SqlDataReader dr = com_sg.ExecuteReader();
        //////////////////////////
        while (dr.Read())
        {
            txt_Expire.Text = (Convert.ToDouble(sc.Decryption(dr["expire_number"].ToString()))-get_days_time.get_days_YourTimes()).ToString();


            txt_license.Text = dr["license_money"].ToString();
            txt_mail.Text = dr["email"].ToString();
            txt_password.Text = sc.Decryption(dr["password"].ToString());
            drp_status.SelectedValue = dr["status"].ToString();
            txt_Phone_Number.Text = dr["phone_number"].ToString();
        }
        dr.Dispose();
        dr.Close();
        //////////////////////////
        com_sg.Dispose();
        con.Close();
    }


    protected void btn_Exit_Click(object sender, EventArgs e)
    {
        Session.RemoveAll();
        Response.Redirect("~/prv/asfsadfs_personal_information_sdfgdfgdfgdsafjadsjf/mnggwergwer_asdfgsdggregedfknkefwe_pnl/dfasdfasd.aspx");
    }


    protected void btn_add_Click(object sender, EventArgs e)
    {


        txt_Expire.Text = Convert.ToString(get_days_time.get_days_YourTimes() + Convert.ToDouble(txt_Expire.Text));

    

        if (Session["mode"].ToString() == "insert")
        {
            String sort_num = get_seconds_time.get_seconds_YourTimes().ToString();

            SqlConnection con = new SqlConnection(config_web_class.connection_String);
            con.Open();

            SqlCommand com_sg = new SqlCommand("Insert Into [tbl_users] ([email],[password],[license_money],[phone_number],[expire_number],[status]) Values ('" + txt_mail.Text + "','" + sc.Encryption(txt_password.Text) + "','" + txt_license.Text + "','" + txt_Phone_Number.Text + "','" + sc.Encryption(txt_Expire.Text) + "','" + drp_status.SelectedValue.ToString() + "')", con);
            com_sg.ExecuteNonQuery();

            com_sg.Dispose();
            con.Close();
        }
        else if (Session["mode"].ToString() == "edit")
        {
            //Button1.Text = Session["r"].ToString();
            //return;

            String sort_num = get_seconds_time.get_seconds_YourTimes().ToString();

            SqlConnection con2 = new SqlConnection(config_web_class.connection_String);
            con2.Open();

            SqlCommand com = new SqlCommand("Update [tbl_users] Set [email]='" + txt_mail.Text + "',[password]='" + sc.Encryption(txt_password.Text) + "',[license_money]='"+txt_license.Text+"',[phone_number]='"+txt_Phone_Number.Text+"',[expire_number]='"+sc.Encryption(txt_Expire.Text)+"',[status]='"+drp_status.SelectedValue.ToString()+"' Where ([r]='" + Session["r"].ToString() + "')", con2);
            com.ExecuteNonQuery();

            com.Dispose();
            con2.Close();
        }
        Session.RemoveAll();
        //Session["mode"] = "insert";
        Response.Redirect(Request.Url.ToString());



    }
    protected void btn_red_signals_Click(object sender, EventArgs e)
    {
        Response.Redirect("dsfasdfasdf.aspx");
    }
}