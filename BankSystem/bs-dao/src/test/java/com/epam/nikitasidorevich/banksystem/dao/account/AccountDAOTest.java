package com.epam.nikitasidorevich.banksystem.dao.account;

import com.epam.nikitasidorevich.banksystem.entity.account.AccountTO;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AccountDAOTest {
    private IDatabaseTester databaseTester;
    private AccountDAO accountDAO = AccountDAOImpl.getInstance();

    @Before
    public void setUp() throws Exception {
        databaseTester = new JdbcDatabaseTester(
            "org.postgresql.Driver",
            "jdbc:postgresql://localhost:5432/BANKSYSTEM_TEST",
            "postgres",
            "postgres"
        );
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(getDataSet("src/test/resources/com/epam/nikitasidorevich/banksystem/dao/account/account.xml"));
        databaseTester.onSetup();
    }

    private IDataSet getDataSet(String fileName) throws Exception {
        return new FlatXmlDataSetBuilder().build(new File(fileName));
    }

    @Test
    public void testSelectAccountsWithCorrectArgs() throws Exception {
        List<AccountTO> accountTOs = accountDAO.selectAccounts(1L, 1L);
        for (AccountTO accountTO : accountTOs) {
            assertNotNull(accountTO.getId());
            assertNotNull(accountTO.getBankId());
            assertNotNull(accountTO.getPersonId());
            assertNotNull(accountTO.getCurrencyId());
            assertNotNull(accountTO.getTotalCash());
        }
        assertTrue(accountTOs.size() == 1);
    }

    @Test
    //negative test, sorta
    public void testSelectAccountsWithIncorrectArgs() throws Exception {
        List<AccountTO> accountTOs = accountDAO.selectAccounts(-1L, 1L);
        assertTrue(accountTOs.size() == 0);
    }

    @Test
    public void testUpdateWithCorrectValue() throws Exception {
        Double newTotal = 666D;
        accountDAO.updateAccount(1L, 1L, 1L, 1L, newTotal);
        List<AccountTO> accountTOs = accountDAO.selectAccounts(1L, 1L);
        for (AccountTO accountTO : accountTOs) {
            if (accountTO.getCurrencyId() == 1L) {
                assertEquals(accountTO.getTotalCash(), newTotal);
            }
        }
        assertEquals(accountTOs.size(), 1);
    }
}
