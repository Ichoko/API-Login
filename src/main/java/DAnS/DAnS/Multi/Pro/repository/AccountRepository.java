package DAnS.DAnS.Multi.Pro.repository;
import DAnS.DAnS.Multi.Pro.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, String> {
}
