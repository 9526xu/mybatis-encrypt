package org.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author andyXu xu9529@gmail.com
 * @date 2020/3/30
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder()
@Data
public class BankCardDO {


    private int id;

    private Date gmt_create;

    private Date gmt_update;

    private String card_no;

    private String phone;

    private String name;

    private String id_no;
}
