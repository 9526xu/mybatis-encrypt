package org.demo;


import org.demo.mapper.BankCardMapper;
import org.demo.mapper.BankCardXmlMapper;
import org.demo.pojo.BankCardDO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisDemoApplicationTests {

    @Autowired
    BankCardMapper bankCardMapper;

    @Autowired
    BankCardXmlMapper bankCardXmlMapper;

    @Test
    public void test() {
        BankCardDO bankCardDO = BankCardDO
                .builder()
                .card_no("64321231231")
                .name("测试卡")
                .id_no("1231231231")
                .phone("13567891234").build();

        bankCardMapper.insertBankCard(bankCardDO);

        // 查询数据
        BankCardDO result = bankCardMapper.queryById(bankCardDO.getId());

        Assertions.assertEquals(bankCardDO.getCard_no(), result.getCard_no());
        Assertions.assertEquals(bankCardDO.getName(), result.getName());
        Assertions.assertEquals(bankCardDO.getId_no(), result.getId_no());
        Assertions.assertEquals(bankCardDO.getPhone(), result.getPhone());
    }

    @Test
    public void testXml() {
        BankCardDO bankCardDO = BankCardDO
                .builder()
                .card_no("64321231231")
                .name("测试卡")
                .id_no("1231231231")
                .phone("13567891234").build();

        bankCardXmlMapper.insertBankCard(bankCardDO);

        // 查询数据
        BankCardDO result = bankCardXmlMapper.queryById(bankCardDO.getId());

        Assertions.assertEquals(bankCardDO.getCard_no(), result.getCard_no());
        Assertions.assertEquals(bankCardDO.getName(), result.getName());
        Assertions.assertEquals(bankCardDO.getId_no(), result.getId_no());
        Assertions.assertEquals(bankCardDO.getPhone(), result.getPhone());
    }

    @Test
    public void testQueryByPhone(){
        BankCardDO bankCardDO = BankCardDO
                .builder()
                .card_no("64321231231")
                .name("测试卡")
                .id_no("1231231231")
                .phone("13567891234").build();

        bankCardXmlMapper.insertBankCard(bankCardDO);

        // 查询数据
        BankCardDO result = bankCardXmlMapper.queryByPhone("13567891234");

        Assertions.assertEquals(bankCardDO.getCard_no(), result.getCard_no());
        Assertions.assertEquals(bankCardDO.getName(), result.getName());
        Assertions.assertEquals(bankCardDO.getId_no(), result.getId_no());
        Assertions.assertEquals(bankCardDO.getPhone(), result.getPhone());


    }





}
