package org.demo.mapper;

import org.demo.pojo.BankCardDO;

/**
 * @author andyXu xu9529@gmail.com
 * @date 2020/4/1
 */
public interface BankCardXmlMapper {

    BankCardDO queryById(int id);

    BankCardDO queryByPhone(String phone);

    void insertBankCard(BankCardDO cardDO);

}
