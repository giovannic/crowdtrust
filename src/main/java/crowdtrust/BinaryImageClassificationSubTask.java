package crowdtrust;

public class BinaryImageClassificationSubTask extends SubTask
{
  private bool result;

  public String display()
  {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getMedia().display());
    sb.append("<br><form name=\"binary_answer_form\" action=\"\" method=\"\">");
    sb.append("Question Id: <input type=\"text\" name=\"binary_question_id\"><br>");
    sb.append("Question: <input type=\"text\"    name=\"binary_question\"><br>");
    sb.append("<input type=\"checkbox\" name=\"bin_result\" value=\"bin_true\">yes");
    sb.append("<input type=\"checkbox\" name=\"bin_result\" value=\"bin_false\">no <br>");
    sb.append("<input type=\"submit\" value=\"Submit\">");
    sb.append("</form>");

    return html;
  }

  public void setResult(bool result)
  {
    this.result = result;
  }

  public bool getResult()
  {
    return this.result;
  }

}
