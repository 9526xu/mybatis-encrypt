package org.demo.mapper;


import org.apache.ibatis.annotations.*;
import org.demo.pojo.BankCardDO;
import org.demo.type.CryptTypeHandler;

/**
 * @author andyXu xu9529@gmail.com
 * @date 2020/3/30
 */
@Mapper
public interface BankCardMapper {

    @Results(id = "bankCard", value = {
            // 通过指定 typeHandler 进行解密
            @Result(column = "card_no", property = "card_no", typeHandler = CryptTypeHandler.class),
            // 通过指定 javaType 进行解密
            @Result(column = "phone", property = "phone", typeHandler = CryptTypeHandler.class),
            @Result(column = "name", property = "name", typeHandler = CryptTypeHandler.class),
            @Result(column = "id_no", property = "id_no", typeHandler = CryptTypeHandler.class),
    })
    @Select("select * from bank_card where id=#{id}")
    BankCardDO queryById(int id);

    // 获取自增 id
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO bank_card (card_no, phone,name,id_no) " +
            "VALUES (#{card.card_no,javaType=crypt}, #{card.phone,typeHandler=org.demo.type.CryptTypeHandler},#{card.name,javaType=crypt},#{card.id_no,javaType=crypt})")
    void insertBankCard(@Param("card") BankCardDO cardDO);
}
