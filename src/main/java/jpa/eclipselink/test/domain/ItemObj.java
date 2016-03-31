package jpa.eclipselink.test.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class ItemObj extends GenericEntity
{
  @NotNull
  private String        summary;

  @ManyToMany
  @JoinTable(name = "itemobj_owners", joinColumns = @JoinColumn(name = "itemobj_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "owners_id", referencedColumnName = "ID"))
  private List<UserObj> owners       = new LinkedList<>();

  @ManyToMany
  @JoinTable(name = "itemobj_participants", joinColumns = @JoinColumn(name = "itemobj_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "paticipants_Id", referencedColumnName = "ID"))
  private List<UserObj> participants = new LinkedList<>();

  /* required by various persistence mechanisms */
  protected ItemObj()
  {
    super();
  }

  public ItemObj(final String summary)
  {
    this(summary, new LinkedList<UserObj>());
  }

  public ItemObj(final String summary, final UserObj user)
  {
    this(summary, Collections.singletonList(user), new LinkedList<UserObj>());
  }

  public ItemObj(final String summary, final List<UserObj> owners)
  {
    this(summary, owners, new LinkedList<UserObj>());
  }

  public ItemObj(final String summary, final List<UserObj> owners,
      final UserObj user)
  {
    this(summary, owners, Collections.singletonList(user));
  }

  public ItemObj(final String summary, final List<UserObj> owners,
      final List<UserObj> participants)
  {
    this.summary = summary;
    this.owners = owners;
    this.participants = participants;
  }

  public List<UserObj> getOwners()
  {
    return owners;
  }

  public List<UserObj> getParticipants()
  {
    return participants;
  }

  public String getSummary()
  {
    return summary;
  }

  public void setOwners(final List<UserObj> owners)
  {
    if (owners == null)
    {
      this.owners = null;
    }

    else
    {
      this.owners = new LinkedList<>(owners);
    }
  }

  public void setParticipants(final List<UserObj> participants)
  {
    if (participants == null)
    {
      this.participants = null;
    }

    else
    {
      this.participants = new LinkedList<>(participants);
    }
  }

  public void setSummary(final String summary)
  {
    this.summary = summary;
  }
}
