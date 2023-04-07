using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class prv_asfsadfs_personal_information_sdfgdfgdfgdsafjadsjf_mnggwergwer_asdfgsdggregedfknkefwe_pnl_rmt_post : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        try
        {
            if(Request.QueryString["opr"]=="edit")
            {
                RemotePostData.RemotePost rmt = new RemotePostData.RemotePost("dsfasdfasdf.aspx");
                rmt.Add("opr", "edit");
                rmt.Add("r", Request.QueryString["r"].ToString());
                rmt.Post();
            }
            else if (Request.QueryString["opr"] == "edit_s")
            {
                RemotePostData.RemotePost rmt = new RemotePostData.RemotePost("sdfkgus_serkneg.aspx");
                rmt.Add("opr", "edit");
                rmt.Add("r", Request.QueryString["r"].ToString());
                rmt.Post();
            }
        }
        catch
        {

        }
    }
}