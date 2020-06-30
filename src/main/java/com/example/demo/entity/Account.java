package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


/**
 *口座機能 Entity
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account implements Serializable {
  /**
  * 顧客ID
  */
	@Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
	
	/**
   * * 口座番号
   */
	@Column(name = "account_number")
  @NotNull
  @Size(min = 1)
  private Integer accountNumber;

	/**
   *  顧客ID
   */
	@Column(name = "client_id")
  @NotNull
  private Integer clientId;

	/**
   * 支店コード
   */
	@Column(name = "branch_code")
  private String branchCode;

	/**
   * 登録者
   */
	@Column(name = "insert_user_id")
  private Integer insertUserId;

	/**
   * 更新者
   */
	@Column(name = "update_user_id")
  private Integer updateUserId;

	/**
   * 登録日時
   */
	@Column(name = "insert_date", updatable = false)
  private Date insertDate;

	/**
   * * 更新日時
   */
	@Column(name = "update_date")
  private Date updateDate;

	/**
   * 削除日時
   * */
	@Column(name = "delete_date")
  private Date deleteDate;

	@PrePersist
  public void onPrePersist() {
	    setInsertDate(new Date());
      setUpdateDate(new Date());
    }
  @PreUpdate
  public void onPreUpdate() {
    setUpdateDate(new Date());
}
}
