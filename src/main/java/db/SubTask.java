package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubTask
{

  public crowdtrust.SubTask getNext( int accountId, int taskId ) throws SQLException
  {
    StringBuilder sql = new StringBuilder();
    sql.append( "SELECT subtasks.id, name, question, media, media_handler " );
    sql.append( "FROM assignments JOIN subtasks JOIN tasks JOIN types " );
    sql.append( "WHERE account = ? AND task = ? AND response IS NULL " );
    sql.append( "LIMIT 1" );

    PreparedStatement preparedStatement = DbAdaptor.connect().prepareStatement( sql.toString() );
    preparedStatement.setInt( 1, accountId );
    preparedStatement.setInt( 2, taskId );
   	ResultSet resultSet = preparedStatement.executeQuery();
    if( !resultSet.first() )
    {
      return null;
    }

    Class mediaHandler = Class.forName( resultSet.getString( "media_handler" ) );
    Class[] constructorArgs = new Class[ 1 ];
    constructorArgs[ 0 ] = InputStream.class;
    Constructor constructor = mediaHandler.getConstructor( constructorArgs );
    Object[] newInstanceArgs = new Object[ 1 ];
    newInstanceArgs[ 0 ] = ResultSet.getBinaryStream( "media" );
    Media media = ( Media ) constructor.newInstance( newInstanceArgs );

    int id = resultSet.getInt( "subtasks.id" );
    String name = resultSet.getString( "name" );
    String question = resultSet.getString( "question" );

    return new crowdtrust.SubTask( id, name, question, media );

  }

};
