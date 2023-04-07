using System;
using System.Globalization;
using System.Collections.Generic;
//using System.Linq;
using System.Data.SqlClient;
using System.Web;

public partial class prv_asfsadfs_personal_information_sdfgdfgdfgdsafjadsjf_mnggwergwer_asdfgsdggregedfknkefwe_pnl_dsfasdfasdf : System.Web.UI.Page
{
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


        Button1.Text = "Add";

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

        string dt = (DateTime.Now.Year) + "-" + (DateTime.Now.Month) + "-" + (DateTime.Now.Day);


        TXT_Date.Text = dt;

    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        String sort_num = get_seconds_time.get_seconds_YourTimes().ToString();

        if (Session["mode"].ToString() == "insert")
        {

            SqlConnection con = new SqlConnection(config_web_class.connection_String);
            con.Open();

            SqlCommand com_sg = new SqlCommand("Insert into [tbl_signals] ([type],[buy_sel],[tp],[sl],[time],[buy],[date],[is_signal],[sort_num]) Values ('" + TXT_type.Text + "','" + drp_buy_sel.SelectedValue + "','" + sc.Encryption(TXT_tp.Text) + "','" + sc.Encryption(TXT_sl.Text) + "','" + drp_time.SelectedValue + "','" + sc.Encryption(TXT_buy.Text) + "','" + TXT_Date.Text + "','" + drp_is_signal.SelectedValue + "','" + sort_num + "')", con);
            com_sg.ExecuteNonQuery();

            com_sg.Dispose();
            con.Close();
        }
        else if (Session["mode"].ToString() == "edit")
        {
            Button1.Text = Session["r"].ToString();
            //return;


            SqlConnection con2 = new SqlConnection(config_web_class.connection_String);
            con2.Open();

            SqlCommand com = new SqlCommand("Update [tbl_signals] Set type='" + TXT_type.Text + "',buy_sel=" + drp_buy_sel.SelectedValue + ",tp='" + sc.Encryption(TXT_tp.Text) + "',sl='" + sc.Encryption(TXT_sl.Text) + "',time=" + drp_time.SelectedValue + ",buy='" + sc.Encryption(TXT_buy.Text) + "',date='" + TXT_Date.Text + "',is_signal=" + drp_is_signal.SelectedValue + ",sort_num='" + sort_num + "' Where (r='" + Session["r"].ToString() + "')", con2);
            com.ExecuteNonQuery();

            com.Dispose();
            con2.Close();
        }
        Session.RemoveAll();
        //Session["mode"] = "insert";
        Response.Redirect(Request.Url.ToString());
    }



    void edit_signal_mode()
    {
        Button1.Text = "OK";

        sec_Class_1 sc = new sec_Class_1();

        Session["mode"] = "edit";
        Session["r"] = Request.Form["r"].ToString();
        //////////////////////////
        SqlConnection con = new SqlConnection(config_web_class.connection_String);
        con.Open();
        //////////////////////////
        SqlCommand com_sg = new SqlCommand("Select * From tbl_signals Where (r='" + Session["r"].ToString() + "')", con);
        SqlDataReader dr = com_sg.ExecuteReader();
        //////////////////////////
        while (dr.Read())
        {
            TXT_buy.Text = sc.Decryption(dr["buy"].ToString());
            TXT_sl.Text = sc.Decryption(dr["sl"].ToString());
            TXT_tp.Text = sc.Decryption(dr["tp"].ToString());
            TXT_type.Text = dr["type"].ToString();
            drp_buy_sel.SelectedValue = dr["buy_sel"].ToString();
            drp_time.SelectedValue = dr["time"].ToString();
            drp_is_signal.SelectedValue = dr["is_signal"].ToString();
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
    protected void btn_red_us_Click(object sender, EventArgs e)
    {
        Response.Redirect("sdfkgus_serkneg.aspx");
    }
}