using System;

namespace Anti_Injection
{
   public class Anti_SQL_Injection
    {
        public String Filter_Text(String Value_Text)
        {
            Value_Text = Value_Text.Replace("\"","");
            Value_Text = Value_Text.Replace("`", "");
            Value_Text = Value_Text.Replace("'", "");

            return Value_Text;
        }
    }
}
