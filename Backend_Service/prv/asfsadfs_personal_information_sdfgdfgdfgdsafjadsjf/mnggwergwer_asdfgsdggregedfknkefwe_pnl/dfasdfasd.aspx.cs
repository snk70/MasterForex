using System;
//using System.Collections.Generic;
//using System.Linq;
//using System.Web;
//using System.Web.UI;
//using System.Web.UI.WebControls;

public partial class prv_asfsadfs_personal_information_sdfgdfgdfgdsafjadsjf_mng_asdfknkefwe_pnl_Default : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        try
        {
            if (Session["kdxfgnskgergkjdfgneeifwefjogreigenrgbeugbergerierirewt3487438257uirthreb34r734834fu34c"].ToString() == "login_sdfrgernikgvabaiwenaksjdvaskv_us")
            {
                Response.Redirect("~/prv/asfsadfs_personal_information_sdfgdfgdfgdsafjadsjf/mnggwergwer_asdfgsdggregedfknkefwe_pnl/dsfasdfasdf.aspx");
            }
        }
        catch
        { }
    }
    protected void btn_login_Click(object sender, EventArgs e)
    {
        Session.Timeout = 120000;
        if (txt_username.Text == "Shahin" && txt_password.Text == "1397@SHAhin@shahin")
        {
            Session["kdxfgnskgergkjdfgneeifwefjogreigenrgbeugbergerierirewt3487438257uirthreb34r734834fu34c"] = "login_sdfrgernikgvabaiwenaksjdvaskv_us";
            Response.Redirect("~/prv/asfsadfs_personal_information_sdfgdfgdfgdsafjadsjf/mnggwergwer_asdfgsdggregedfknkefwe_pnl/dsfasdfasdf.aspx");
        }
    }
}