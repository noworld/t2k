package red.arpanet.t2k.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import red.arpanet.t2k.util.CopyrightArpanet;

/**
 * Entity implementation class for Entity: Campaign
 *
 */
@Entity
@Table(name="t2k_campaign")
@CopyrightArpanet
public class T2kCampaign implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="description", nullable=false)
	private String description;
	
	@OneToOne
	@Column(name="game_master", nullable=false)
	private T2kUser gameMaster;
	
	@Column(name="last_update_timestamp")
	private Timestamp lastUpdateTimestamp;
	
	@Column(name="created_timestamp", nullable=false)
	private Timestamp createdTimestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public T2kUser getGameMaster() {
		return gameMaster;
	}

	public void setGameMaster(T2kUser gameMaster) {
		this.gameMaster = gameMaster;
	}

	public Timestamp getLastUpdateTimestamp() {
		return lastUpdateTimestamp;
	}

	public void setLastUpdateTimestamp(Timestamp lastUpdateTimestamp) {
		this.lastUpdateTimestamp = lastUpdateTimestamp;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}


}
