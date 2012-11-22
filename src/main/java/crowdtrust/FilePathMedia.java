package crowdtrust;

import java.io.InputStream;

public class FilePathMedia implements Media
{

  private final String filePath;

  public FilePathMedia( InputStream inputStream )
  {
    StringWriter writer = new StringWriter();
    IOUtils.copy( inputStream, writer );
    this.filePath = writer.toString();
  }

  public String display()
  {
    StringBuilder html = new StringBuilder();
    html.append( "<img src=\"" );
    html.append( this.filePath );
    html.append( "\"/>" );
    return html.toString();
  }

};
