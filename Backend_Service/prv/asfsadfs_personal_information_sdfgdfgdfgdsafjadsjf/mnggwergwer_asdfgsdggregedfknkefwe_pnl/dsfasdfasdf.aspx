<%@ Page Language="C#" AutoEventWireup="true" CodeFile="dsfasdfasdf.aspx.cs" Inherits="prv_asfsadfs_personal_information_sdfgdfgdfgdsafjadsjf_mnggwergwer_asdfgsdggregedfknkefwe_pnl_dsfasdfasdf" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>مدیریت سیگنال ها</title>
<style>
@media(min-width:1000px)
{
.right
{
    float:right;
}
}
.rtl
{
direction:rtl;
}
    #form1
    {
        direction: ltr;
    }
</style>
</head>
<body>
    <form id="form1" runat="server">
    <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" 
        DataKeyNames="r" DataSourceID="SqlDataSource1" 
        EmptyDataText="There are no data records to display." 
        EnableModelValidation="True" CssClass="right rtl" BackColor="White" 
        BorderColor="#999999" BorderStyle="None" BorderWidth="1px" CellPadding="3" 
        GridLines="Vertical" AllowPaging="True" AllowSorting="True" PageSize="20">
        <AlternatingRowStyle BackColor="#DCDCDC" />
        <Columns>
            <asp:BoundField DataField="r" HeaderText="ردیف" ReadOnly="True" 
                SortExpression="r" />
            <asp:BoundField DataField="type" HeaderText="نوع" SortExpression="type" />
            <asp:BoundField DataField="buy_sel" HeaderText="خرید یا فروش" 
                SortExpression="buy_sel" />
            <asp:BoundField DataField="time" HeaderText="زمان" SortExpression="time" />
            <asp:BoundField DataField="date" HeaderText="تاریخ" SortExpression="date" />
            <asp:BoundField DataField="is_signal" HeaderText="وجود سیگنال" 
                SortExpression="is_signal" />
            <asp:CommandField DeleteText="حذف" ShowDeleteButton="True" />
            <asp:HyperLinkField DataNavigateUrlFields="r" 
                DataNavigateUrlFormatString="rmt_post.aspx?r={0}&amp;opr=edit" Text="ویرایش" />
        </Columns>
        <FooterStyle BackColor="#CCCCCC" ForeColor="Black" />
        <HeaderStyle BackColor="#000084" Font-Bold="True" ForeColor="White" />
        <PagerStyle BackColor="#999999" ForeColor="Black" HorizontalAlign="Center" />
        <RowStyle BackColor="#EEEEEE" ForeColor="Black" />
        <SelectedRowStyle BackColor="#008A8C" Font-Bold="True" ForeColor="White" />
    </asp:GridView>
    <br />
    <br />
    <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
        ConnectionString="<%$ ConnectionStrings:masterfxshahinConnectionString1 %>" 
        DeleteCommand="DELETE FROM [tbl_signals] WHERE [r] = @r" 
        InsertCommand="INSERT INTO [tbl_signals] ([r], [type], [buy_sel], [tp], [sl], [time], [buy], [date], [is_signal], [sort_num]) VALUES (@r, @type, @buy_sel, @tp, @sl, @time, @buy, @date, @is_signal, @sort_num)" 
        ProviderName="<%$ ConnectionStrings:masterfxshahinConnectionString1.ProviderName %>" 
        SelectCommand="SELECT [r], [type], [buy_sel], [tp], [sl], [time], [buy], [date], [is_signal], [sort_num] FROM [tbl_signals] ORDER BY [sort_num] DESC" 
        UpdateCommand="UPDATE [tbl_signals] SET [type] = @type, [buy_sel] = @buy_sel, [tp] = @tp, [sl] = @sl, [time] = @time, [buy] = @buy, [date] = @date, [is_signal] = @is_signal, [sort_num] = @sort_num WHERE [r] = @r">
        <DeleteParameters>
            <asp:Parameter Name="r" Type="Int32" />
        </DeleteParameters>
        <InsertParameters>
            <asp:Parameter Name="r" Type="Int32" />
            <asp:Parameter Name="type" Type="String" />
            <asp:Parameter Name="buy_sel" Type="Int32" />
            <asp:Parameter Name="tp" Type="String" />
            <asp:Parameter Name="sl" Type="String" />
            <asp:Parameter Name="time" Type="Int32" />
            <asp:Parameter Name="buy" Type="String" />
            <asp:Parameter Name="date" Type="String" />
            <asp:Parameter Name="is_signal" Type="Int32" />
            <asp:Parameter Name="sort_num" Type="String" />
        </InsertParameters>
        <UpdateParameters>
            <asp:Parameter Name="type" Type="String" />
            <asp:Parameter Name="buy_sel" Type="Int32" />
            <asp:Parameter Name="tp" Type="String" />
            <asp:Parameter Name="sl" Type="String" />
            <asp:Parameter Name="time" Type="Int32" />
            <asp:Parameter Name="buy" Type="String" />
            <asp:Parameter Name="date" Type="String" />
            <asp:Parameter Name="is_signal" Type="Int32" />
            <asp:Parameter Name="sort_num" Type="String" />
            <asp:Parameter Name="r" Type="Int32" />
        </UpdateParameters>
    </asp:SqlDataSource>
    <div style="direction: ltr">
    
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
    
        <br />
        <br />
        <asp:TextBox ID="TXT_type" runat="server" CssClass="right" placeholder="Type"></asp:TextBox>
        <asp:TextBox ID="TXT_buy" runat="server" CssClass="right" placeholder="Buy or Sell Number"></asp:TextBox>
        <asp:TextBox ID="TXT_sl" runat="server" CssClass="right" placeholder="Sl"></asp:TextBox>
        <asp:TextBox ID="TXT_tp" runat="server" CssClass="right" placeholder="TP"></asp:TextBox>
        <asp:TextBox ID="TXT_Date" runat="server" CssClass="right" Width="186px" placeholder="Date"></asp:TextBox>
        <br />
        <br />
        <br />
        <asp:DropDownList ID="drp_buy_sel" runat="server">
            <asp:ListItem Value="0">Sell</asp:ListItem>
            <asp:ListItem Value="1">Buy</asp:ListItem>
            <asp:ListItem Value="2">Sell Stop</asp:ListItem>
            <asp:ListItem Value="3">Buy Stop</asp:ListItem>
            <asp:ListItem Value="4">Sell Limit</asp:ListItem>
            <asp:ListItem Value="5">Buy Limit</asp:ListItem>
        </asp:DropDownList>
        <asp:DropDownList ID="drp_time" runat="server">
            <asp:ListItem Value="0">Short</asp:ListItem>
            <asp:ListItem Value="1">Long</asp:ListItem>
        </asp:DropDownList>
        <asp:DropDownList ID="drp_is_signal" runat="server">
            <asp:ListItem Value="0">No Signal</asp:ListItem>
            <asp:ListItem Value="1">Signal</asp:ListItem>
        </asp:DropDownList>
        <br />
        <br />
        <br />
        <asp:Button ID="Button1" runat="server" Height="75px" Text="Add" Width="99px" 
            onclick="Button1_Click" />
        <br />
        <br />
        <asp:Button ID="btn_red_us" runat="server" Height="75px" Text="کاربران" Width="99px" 
            onclick="btn_red_us_Click" />
        <br />
    
    </div>
    <p style="direction: ltr">
        &nbsp;</p>
    <asp:Button ID="btn_Exit" style="background:red;" runat="server" Height="59px" Text="خروج" 
        Width="69px" onclick="btn_Exit_Click" />
    </form>
    </body>
</html>
