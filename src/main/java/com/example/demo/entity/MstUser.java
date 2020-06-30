package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 情報 Entity
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mst_user")
public class MstUser implements Serializable {
  /**
   * 社員ID
   */
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 社員名
   */
  @Column(name = "user_name")
  @NotNull
  private String userName;

  /**
   * 社員コード
   */
  @Column(name = "user_code")
  @NotNull
  private Integer userCode;

  /**
   * パスワード
   */
  @Column(name = "password")
  @NotNull
  @Size(min = 4)
  private String password;

  /**
   * ステータス
   */
  @Column(name = "status")
  @NotNull
  private Integer status;

  /**
   * 支店コード
   */
  @Column(name = "branch_code")
  private Integer branchCode;

  /**
   * 役職コード
   */
  @Column(name = "position_code")
  private String positionCode;

  /**
   * 業務コード
   */
  @Column(name = "business_code")
  private String businessCode;

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
   * 更新日時
   */
  @Column(name = "update_date")
  private Date updateDate;

  /**
   * 削除日時
   */
  @Column(name = "delete_date")
  private Date deleteDate;

  private int insertId;

  public void setInsertId(int inserId) {
    this.insertId = insertId;
  }

  @PrePersist
  public void onPrePersist() {
    setInsertDate(new Date());
    setUpdateDate(new Date());
  }

  @PreUpdate
  public void onPreUpdate() {
    setUpdateDate(new Date());
  }

  public void addObject(String column, String column1) {
  }

  public void setUpdateId(int i) {
  }
}