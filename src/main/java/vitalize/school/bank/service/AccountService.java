package vitalize.school.bank.service;

import vitalize.school.bank.entity.Account;
import vitalize.school.bank.entity.Client;
import vitalize.school.bank.entity.Transaction;
import vitalize.school.bank.repository.AccountRepository;
import vitalize.school.bank.searchform.AccountSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  /**
   *  口座 一覧
   */
  public List<Account> findAll() {
    return accountRepository.findAll();
  }
  public Account findOne(Long id) {
    return accountRepository.findById(id).orElse(null);
  }
  public List<Account> findClientId(Integer accountClientId) {
    return accountRepository.findByClientId(accountClientId);
  }
  /**
   *  口座 登録
   */
  public Account save(Account account) {
    return accountRepository.save(account);
  }
  /**
   *  口座 削除
   */
  public void delete(Long id) {
    accountRepository.deleteById(id);
  }
  /**
   *  口座の内容とページネーションを全検索
   */
  public Page<Account> getAll(Pageable pageable, AccountSearchForm searchForm) {
    String branchCode = searchForm.getBranchCode() == null ? searchForm.getBranchCode() : searchForm.getBranchCode().replaceAll("　", "").replaceAll(" ", "");

    try {
      // idを文字列から数字変換できるか判定
      Integer.parseInt(searchForm.getId());
      Integer.parseInt(searchForm.getAccountNumber());

      Specification<Account> spec = Specification
              .where(idEqual(searchForm.getId() == null ? searchForm.getId() : searchForm.getId().replaceAll("　", "").replaceAll(" ", "")))
              .and(numberEqual(searchForm.getAccountNumber() == null ? searchForm.getAccountNumber() : searchForm.getAccountNumber().replaceAll("　", "").replaceAll(" ", "")))
              .and(branchCodeContains(branchCode));
      return accountRepository.findAll(spec, pageable);
    } catch(NumberFormatException e) {
      Specification<Account> spec = Specification.where(branchCodeContains(branchCode));
      return accountRepository.findAll(spec, pageable);
    }
  }

  /**
   *  口座ID 検索
   */
  private static Specification<Account> idEqual(String id) {
    // ラムダ式で記述すると、引数のデータ型の指定が省略できる
    return id == "" || Objects.isNull(id) ? null : (root, query, cb) -> {
      return cb.equal(root.get("id"),  id);
    };
  }

  /**
   *  口座番号 検索
   */
  private static Specification<Account> numberEqual(String accountNumber) {
    // ラムダ式で記述すると、引数のデータ型の指定が省略できる
    return accountNumber == "" || Objects.isNull(accountNumber) ? null : (root, query, cb) -> {
      return cb.equal(root.get("accountNumber"), accountNumber);
    };
  }

  /**
   *  支店名 検索
   */
  private static Specification<Account> branchCodeContains(String branchCode) {
    // ラムダ式で記述すると、引数のデータ型の指定が省略できる
    return branchCode == "" || Objects.isNull(branchCode) ? null : (root, query, cb) -> {
      return cb.like(root.get("branchCode"), "%" + branchCode + "%");
    };
  }

  /**
   *  口座番号 検索
   */
  public List<Account>  findAccount(Integer accountNumber) {
    return accountRepository.findByAccountNumber(accountNumber);
  }
}
