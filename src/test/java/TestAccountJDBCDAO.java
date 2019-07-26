
import com.test.testapp.DAO.AccountJDBCDAO;
import com.test.testapp.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestAccountJDBCDAO {

    @Mock
    AccountJDBCDAO accountJDBCDAO = new AccountJDBCDAO();

    @Test
    public void testUpdateSurname(){
        when(accountJDBCDAO.updateSurname("Ivan", "Polikov")).thenReturn(1);

        // проверяем действие сложения
        assertEquals(accountJDBCDAO.updateSurname("Ivan", "Polikov"), 1, 0);
        // проверяем выполнение действия
        verify(accountJDBCDAO).updateSurname("Ivan", "Polikov");
    }

    @Test
    public void testFindAccount(){
        Account account = new Account();
        account.setName("Ivan");
        account.setSurname("Polikov");
        account.setEmail("ivan@mail.ru");
        account.setPassword("password");

        when(accountJDBCDAO.getByName("Ivan")).thenReturn(account);

        // проверяем действие сложения
        assertEquals(accountJDBCDAO.getByName("Ivan"),account);
        // проверяем выполнение действия
        verify(accountJDBCDAO).getByName("Ivan");
    }
}
