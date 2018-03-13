package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rewards database table.
 * 
 */
@Entity
@Table(name="rewards")
@NamedQuery(name="Reward.findAll", query="SELECT r FROM Reward r")
public class Reward implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int rewardId;

	private String rwdDesc;

	private String rwdName;

	public Reward() {
	}

	public int getRewardId() {
		return this.rewardId;
	}

	public void setRewardId(int rewardId) {
		this.rewardId = rewardId;
	}

	public String getRwdDesc() {
		return this.rwdDesc;
	}

	public void setRwdDesc(String rwdDesc) {
		this.rwdDesc = rwdDesc;
	}

	public String getRwdName() {
		return this.rwdName;
	}

	public void setRwdName(String rwdName) {
		this.rwdName = rwdName;
	}

}