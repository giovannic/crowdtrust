package web;

import java.utils.Set;
import java.utils.TreeSet;

public abstract class WebElement
{

  private Set<String> scripts;
  private Set<String> styles;

  public WebElement( scripts, styles )
  {
    if( scripts == null )
    {
      scripts = new TreeSet<String>();
    }
    if( styles == null )
    {
      styles = new TreeSet<String>();
    }
    this.scripts = scripts;
    this.styles = styles;
  }

  public WebElement()
  {
    this( null, null );
  }

  public Set<String> getScripts()
  {
    return this.scripts;
  }

  public Set<String> getStyles()
  {
    return this.styles;
  }

  public abstract String display();

};

