<%@ Page Language="C#" AutoEventWireup="true" CodeFile="sdfkgus_serkneg.aspx.cs" Inherits="prv_asfsadfs_personal_information_sdfgdfgdfgdsafjadsjf_mnggwergwer_asdfgsdggregedfknkefwe_pnl_sdfkgus_serkneg" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>مدیریت کاربران</title>
    <style media="screen">
@media(max-width:3000px)
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
    <div>
    
        <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" 
            DataKeyNames="r" DataSourceID="SqlDataSource1" 
            EmptyDataText="There are no data records to display." 
            EnableModelValidation="True" BackColor="White" BorderColor="#DEDFDE" 
            BorderStyle="None" BorderWidth="1px" CellPadding="4" CssClass="rtl right" 
            ForeColor="Black" GridLines="Vertical" AllowPaging="True" 
            AllowSorting="True">
            <AlternatingRowStyle BackColor="White" />
            <Columns>
                <asp:BoundField DataField="r" HeaderText="ردیف" ReadOnly="True" 
                    SortExpression="r" />
                <asp:BoundField DataField="email" HeaderText="ایمیل" SortExpression="email" />
                <asp:BoundField DataField="license_money" HeaderText="شناسه پرفکت مانی" 
                    SortExpression="license_money" />
                <asp:BoundField DataField="phone_number" HeaderText="تلفن" 
                    SortExpression="phone_number" />
                <asp:BoundField DataField="status" HeaderText="وضعیت" 
                    SortExpression="status" />
                <asp:HyperLinkField DataNavigateUrlFields="r" 
                    DataNavigateUrlFormatString="rmt_post.aspx?r={0}&amp;opr=edit_s" 
                    Text="ویرایش" />
            </Columns>
            <FooterStyle BackColor="#CCCC99" />
            <HeaderStyle BackColor="#6B696B" Font-Bold="True" ForeColor="White" />
            <PagerStyle BackColor="#F7F7DE" ForeColor="Black" HorizontalAlign="Right" />
            <RowStyle BackColor="#F7F7DE" />
            <SelectedRowStyle BackColor="#CE5D5A" Font-Bold="True" ForeColor="White" />
        </asp:GridView>
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
            ConnectionString="<%$ ConnectionStrings:masterfxshahinConnectionString1 %>" 
            DeleteCommand="DELETE FROM [tbl_users] WHERE [r] = @r" 
            InsertCommand="INSERT INTO [tbl_users] ([email], [password], [license_money], [phone_number], [expire_number], [status]) VALUES (@email, @password, @license_money, @phone_number, @expire_number, @status)" 
            ProviderName="<%$ ConnectionStrings:masterfxshahinConnectionString1.ProviderName %>" 
            SelectCommand="SELECT [r], [email], [password], [license_money], [phone_number], [expire_number], [status] FROM [tbl_users]" 
            UpdateCommand="UPDATE [tbl_users] SET [email] = @email, [password] = @password, [license_money] = @license_money, [phone_number] = @phone_number, [expire_number] = @expire_number, [status] = @status WHERE [r] = @r">
            <DeleteParameters>
                <asp:Parameter Name="r" Type="Int32" />
            </DeleteParameters>
            <InsertParameters>
                <asp:Parameter Name="email" Type="String" />
                <asp:Parameter Name="password" Type="String" />
                <asp:Parameter Name="license_money" Type="String" />
                <asp:Parameter Name="phone_number" Type="String" />
                <asp:Parameter Name="expire_number" Type="String" />
                <asp:Parameter Name="status" Type="String" />
            </InsertParameters>
            <UpdateParameters>
                <asp:Parameter Name="email" Type="String" />
                <asp:Parameter Name="password" Type="String" />
                <asp:Parameter Name="license_money" Type="String" />
                <asp:Parameter Name="phone_number" Type="String" />
                <asp:Parameter Name="expire_number" Type="String" />
                <asp:Parameter Name="status" Type="String" />
                <asp:Parameter Name="r" Type="Int32" />
            </UpdateParameters>
        </asp:SqlDataSource>
    
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
    
    </div>
    <asp:TextBox ID="txt_mail" runat="server" CssClass="right rtl" placeholder="ایمیل"></asp:TextBox>
    <asp:TextBox ID="txt_password" runat="server" CssClass="right rtl" placeholder="پسورد"></asp:TextBox>
    <asp:TextBox ID="txt_license" runat="server" CssClass="right rtl" placeholder="لایسنس وب مانی"></asp:TextBox>
    <asp:TextBox ID="txt_Phone_Number" runat="server" CssClass="right rtl" placeholder="تلفن"></asp:TextBox>
    <asp:TextBox ID="txt_Expire" runat="server" placeholder="تاریخ شارژ" 
        CssClass="rtl right"></asp:TextBox>
    <asp:DropDownList ID="drp_status" runat="server" CssClass="right">
        <asp:ListItem Value="1">تأیید شده</asp:ListItem>
        <asp:ListItem Value="0">تأیید نشده</asp:ListItem>
        <asp:ListItem Value="2">معلق</asp:ListItem>
    </asp:DropDownList>
    <p style="direction: ltr">
        &nbsp;</p>
        <asp:Button ID="btn_add" runat="server" Height="75px" Text="OK" 
        Width="99px" onclick="btn_add_Click"/>
    <br />
    <br />
        <asp:Button ID="btn_red_signals" runat="server" Height="75px" Text="سیگنال" 
        Width="99px" onclick="btn_red_signals_Click"/><br /><br /><br />
    <asp:Button ID="btn_Exit" style="background:red;" runat="server" Height="59px" Text="خروج" 
        Width="69px" onclick="btn_Exit_Click" />
    </form>
    <p style="direction: ltr">
        &nbsp;</p>
    <p style="direction: ltr">
        &nbsp;</p>
</body>
</html>
