package media;

import java.io.InputStream;

import java.utils.List;

import javax.servlet.HttpServletRequest;

import web.WebElement;

public interface MediaHandler
{

  public static WebElement displayUploadForm();
  public static List<String> upload( HttpServletRequest );
  public static WebElement display( InputStream );

};

