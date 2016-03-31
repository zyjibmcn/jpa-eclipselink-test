package jpa.eclipselink.test.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "USERNAME" }) })
public class UserObj extends GenericEntity implements Comparable<UserObj>
{
  @NotNull
  private String username;

  /**
   * Required for various persistence mechanisms.
   */
  protected UserObj()
  {
    super();
  }

  public UserObj(final String username)
  {
    super();
    this.username = username;
  }

  @Override
  public int compareTo(final UserObj other)
  {
    if (this == other)
    {
      return 0;
    }

    if (other == null)
    {
      return -1;
    }

    if (username != null)
    {
      return username.compareTo(other.getUsername());
    }

    return other.getUsername() == null ? 0 : -1;
  }

  @Override
  public boolean equals(final Object obj)
  {
    if (this == obj)
    {
      return true;
    }

    if (!(obj instanceof UserObj))
    {
      return false;
    }

    final UserObj other = (UserObj) obj;
    return username.equals(other.getUsername());
  }

  public String getUsername()
  {
    return username;
  }

  @Override
  public int hashCode()
  {
    return username.hashCode();
  }

  public void setUsername(final String username)
  {
    this.username = username;
  }

  @Override
  public String toString()
  {
    return "UserObj [username=" + username + "]";
  }
}
